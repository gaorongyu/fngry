package com.gry.jdk8.lambda.supplier;

import java.util.function.Supplier;

/**
 *
 * 自然数序列
 *
 * Created by gaorongyu on 15/4/27.
 */
public class NaturalSupplier implements Supplier<Long> {

    long value = 0;

    @Override
    public Long get() {
        this.value = this.value + 1;
        return this.value;
    }
}
