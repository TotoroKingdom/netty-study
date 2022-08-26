package com.totoro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.totoro.pojo.User;
import com.totoro.serialize.impl.JSONSerializer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:totoro
 * @createDate:2022/8/25
 * @description:
 */
@Slf4j
public class HttpHelloWorldServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private HttpHeaders headers;
    private HttpRequest request;
    private FullHttpRequest fullRequest;

    private static final String FAVICON_ICO = "/favicon.iso";
    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    private static final AsciiString CONNECTION = AsciiString.cached("Connection");
    private static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        User user = new User();
        user.setUserName("totoro");
        user.setDate(new Date());
        if ( msg instanceof HttpRequest){

            request = (HttpRequest) msg;
            headers = request.headers();
            String uri = request.uri();
            log.info("http uri: " + uri);
            if (uri.equals(FAVICON_ICO)){
                return;
            }
            HttpMethod method = request.method();
            if (method.equals(HttpMethod.GET)){
                QueryStringDecoder queryDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
                Map<String, List<String>> uriAttributes = queryDecoder.parameters();

                for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()){
                    for (String attrVal : attr.getValue()) {
                        log.info(attr.getKey() + "=" + attrVal);
                    }
                }
                user.setMethod("get");
            } else if (method.equals(HttpMethod.POST)){
                fullRequest = (FullHttpRequest) msg;
                dealWithContentType();
                user.setMethod("post");
            }

            JSONSerializer jsonSerializer = new JSONSerializer();
            byte[] content = jsonSerializer.serialize(user);

            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

            boolean keepAlive = HttpUtil.isKeepAlive(request);

            if (!keepAlive){
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            }else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }

    private void dealWithContentType(){
        String contentType = getContentType();
        if ("application/json".equals(contentType)){
            String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            JSONObject obj = JSON.parseObject(jsonStr);
            for (Map.Entry<String, Object> item : obj.entrySet()){
                log.info(item.getKey() + "=" +item.getValue().toString());
            }

        }else if (contentType.equals("application/x-www-form-urlencoded")) {
            String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            QueryStringDecoder queryDecoder = new QueryStringDecoder(jsonStr, false);
            Map<String, List<String>> uriAttributes = queryDecoder.parameters();
            for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
                for (String attrVal : attr.getValue()) {
                    log.info(attr.getKey() + "=" + attrVal);
                }
            }
        }else if(contentType.equals("multipart/form-data")){
            //文件上传
        }

    }

    private String getContentType(){
        String typeStr = headers.get("Content-Type").toString();
        String[] list = typeStr.split(";");
        return list[0];
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
