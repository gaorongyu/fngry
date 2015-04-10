package com.gry.test.instrument;

import com.sun.tools.attach.VirtualMachine;

/**
 *
 * 测试Instrument API 向目标虚拟机植入class
 *
 * Created by gaorongyu on 14-9-29.
 *
 */
public class TestInstrument {

    private static final String AGENT_PATH = "/Users/gaorongyu/dev/idea/javatest/temp/MyAgent.jar";

    public static void main(String[] args) throws Exception {
        // 连接目标虚拟机
        VirtualMachine vm = VirtualMachine.attach("4853");
        // 加载代理jar包
        vm.loadAgent(AGENT_PATH);

        Thread.sleep(5000);
        vm.detach();
    }

}
