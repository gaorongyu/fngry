package com.gry.test.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;

import java.math.BigDecimal;

/**
 *
 * Created by gaorongyu on 14-9-29.
 */
@BTrace
public class DeliveryTraceScript {

    @OnMethod(
            clazz = "com.tqmall.saint.biz.ordertrack.impl.DeliveryServiceImpl",
            method = "getDiscount",
            location = @Location(Kind.RETURN)
    )
    public static void traceExecute(Object order, BigDecimal ret) {
        BTraceUtils.println("test Server idleMethod traced...");

        BTraceUtils.println("#### order: ");
        BTraceUtils.println(order);

        BTraceUtils.println("#### discount: ");
        BTraceUtils.println(ret);
    }

}
