package com.gry.test.server;

import java.util.Random;

/**
 *
 * 模拟服务器
 *
 * Created by gaorongyu on 14/12/28.
 *
 */
public class Simulator {

    /**
     * 每次被调用时随机sleep 1-9 秒
     */
    public Integer process (Integer times) {
        System.out.println("第 " + times + " 次...");
        // 随机数
        int second = new Random().nextInt(8) + 1;
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("  重新改写编译后$$$");
        System.out.println("    每次睡的时间长是随机的 ... " + second + "秒");
        return second;
    }

}
