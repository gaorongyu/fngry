package com.gry.test.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaorongyu on 15/5/9.
 */
public class WhatIsNull {

    public static void main(String[] args) throws Exception {

        WhatIsNull whatIsNull = new WhatIsNull();
        whatIsNull.testConvert();

//        whatIsNull.testInstaceof();
//
//        whatIsNull.testNullCompare();
//
//        whatIsNull.testAutoBoxing();

        // 值为null的对象可以调用static方法
        whatIsNull = null;
//        whatIsNull.runAnyCondition();
    }

    /**
     * 测试对象转型
     */
    public void testConvert() {
        Map<String, Object> map = getTestMap();

        String obj1 = (String) map.get("1");
        System.out.println(" obj1 is " + obj1.trim());

        // 转型不会报空指针
        String obj2 = (String) map.get("2");
        // 使用转型后的值要判空
        System.out.println(" obj2 is " + obj2.trim());
    }

    /**
     * instanceof null 不需判空  返回false
     */
    public void testInstaceof() {
        Map<String, Object> map = getTestMap();

        if(map.get("1") instanceof String) {
            System.out.println(map.get("1") + " is type of String ");
        }
        if(map.get("2") instanceof String) {
            System.out.println(map.get("2") + " is type of String ");
        } else {
            System.out.println(map.get("2") + " is not type of String ");
        }
    }

    /**
     * 任何值为null的包装类，经过java的拆箱操作时都会抛出空指针异常
     */
    public void testAutoBoxing() {
        Map<String, Object> map = new HashMap<>();
        Integer integer = (Integer) map.get("2");

        int i = 0;
        if(i == integer) { // 自动解包报错
            System.out.println(" unreachable ");
        }
    }

    /**
     * null 比较只能用 ==  !=
     */
    public void testNullCompare() {
        if(null == null) {
            System.out.println(" null == null is true ");
        }
        if(null != null) {
            System.out.println(" null != null is true ");
        }
    }

    private Map<String, Object> getTestMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", " 壹     ");

        return map;
    }

    /**
     * 值为null的对象可以调用static方法
     */
    public static void runAnyCondition() {
        System.out.println(" suprised!!!   null object can invoke method ");
    }

}