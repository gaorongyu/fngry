package com.gry.test.jdi;


/**
 *
 * 测试基于JDI的调试工具
 *
 * Created by gaorongyu on 14-9-25.
 *
 */
public class TestJDI {

    public static void main(String[] args) throws Exception {

        MyDebugger myDebuger = new MyDebugger();

        myDebuger.debug();
    }

}
