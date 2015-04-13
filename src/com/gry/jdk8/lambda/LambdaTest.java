package com.gry.jdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * lambda 表达式
 *
 * Created by gaorongyu on 15/4/11.
 */
public class LambdaTest {

    public static void main(String[] args) {
        LambdaTest test = new LambdaTest();
        test.processNames();
        test.accumulate();
    }

    private String joinString(List<String> names) {
        return names.stream()
                .filter(name -> name.length() > 4)
                .map(name -> capitalize(name))
                .collect(Collectors.joining(","));
    }

    private Integer mapReduce(List<String> names) {
        return names.stream()
                .filter(name -> name.length() > 4)
                .map(name -> name.length())
                .reduce(0, (a, b) -> a + b);
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }

    public void processNames() {
        System.out.println("joinString...");
        List<String> names = Arrays.asList("join", "james", "white", "jenkins");
        System.out.println(joinString(names));

        System.out.println("mapReduce...");
        System.out.println(mapReduce(names));
    }

    public void accumulate() {
        System.out.println("accumulate...");
        List<Integer> integers = Arrays.asList(1, 2, 3);
        Integer sum = integers.stream().reduce(0, (a, b) -> a + b);
//        Integer sum = integers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }

}
