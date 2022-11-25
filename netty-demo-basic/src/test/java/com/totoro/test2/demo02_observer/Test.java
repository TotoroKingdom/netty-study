package com.totoro.test2.demo02_observer;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        Commander commander = new Commander();

        CannonShooter cannonShooter = new CannonShooter(commander);

        CannonFodder cannonFodder1 = new CannonFodder(1);
        CannonFodder cannonFodder2 = new CannonFodder(2);
        CannonFodder cannonFodder3 = new CannonFodder(3);

        commander.setState(100);
        commander.attach(cannonShooter);
        commander.attach(cannonFodder1);
        commander.attach(cannonFodder2);
        commander.attach(cannonFodder3);

        commander.sNotify();

        commander.setState(108);
        commander.detach(cannonFodder3);

        commander.sNotify();

    }
}
