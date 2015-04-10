package com.gry.test.instrument;

import com.gry.test.server.Simulator;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 *
 * 植入代码代理入口
 *
 * Created by gaorongyu on 14/12/29.
 *
 */
public class AgentMain {

    public static void agentmain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException,
            InterruptedException {
        System.out.println("Agent Main Start...");

//        retransformClass(agentArgs, inst);
        redifineClass(agentArgs, inst);

        System.out.println("Agent Main Done...");
    }

    /**
     * 方式一  retransformClasses
     *
     */
    private static void retransformClass(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
        inst.addTransformer(new Transformer(), true);
        inst.retransformClasses(Simulator.class);
    }

    /**
     * 方式二  redefineClasses
     *
     */
    private static void redifineClass(String agentArgs, Instrumentation inst)
            throws UnmodifiableClassException, ClassNotFoundException {
        ClassDefinition def = new ClassDefinition(Simulator.class, Transformer
                .getBytesFromFile("/Users/gaorongyu/dev/idea/javatest/out/production/javatest/com/gry/test/server/Simulator.class"));
        inst.redefineClasses(new ClassDefinition[] { def });
    }

}
