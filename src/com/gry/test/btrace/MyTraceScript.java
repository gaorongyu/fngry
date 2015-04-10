package com.gry.test.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 *
 * Created by gaorongyu on 14-9-29.
 */
@BTrace
public class MyTraceScript {

    @OnMethod(
            clazz = "com.gry.test.server.Simulator",
            method = "process",
            location=@Location(Kind.RETURN)
    )
    public static void inspectProcess(Integer times, @Return Integer second) {
        BTraceUtils.println("test Simulator process traced...");

        BTraceUtils.println("#### current thread: ");
        BTraceUtils.println(BTraceUtils.currentThread());

        BTraceUtils.println("#### stacks: ");
        BTraceUtils.jstack();

        BTraceUtils.println("#### heap usage: ");
        BTraceUtils.println(BTraceUtils.heapUsage());

        BTraceUtils.print("$$$$ 参数 is: time = ");
        BTraceUtils.println(times);

        BTraceUtils.print("$$$$ 返回值 is: second = ");
        BTraceUtils.println(second);

        BTraceUtils.println("    ");
    }

}
