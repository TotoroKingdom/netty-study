package com.totoro.test2.demo2;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class CannonShooter implements Observer{

    private Subject cmder;

    public CannonShooter(Subject cmder) {
        this.cmder = cmder;
    }

    public Subject getCmder() {
        return cmder;
    }

    public void setCmder(Subject cmder) {
        this.cmder = cmder;
    }

    public void fireCannon(int targetPlace){
        System.out.println(this.getClass().getSimpleName() + ": fired on target(id:)"+ targetPlace + ") by Cannon");
    }

    @Override
    public void update() {
        fireCannon(cmder.getState());
    }
}
