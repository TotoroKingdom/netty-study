package com.totoro.pojo;

import lombok.Data;
import java.util.Date;

/**
 * @author:totoro
 * @createDate:2022/8/25
 * @description:
 */
@Data
public class User {

    private String userName;

    private String method;

    private Date date;
}
