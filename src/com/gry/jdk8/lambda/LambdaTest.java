package com.gry.jdk8.lambda;

import com.gry.jdk8.lambda.supplier.NaturalSupplier;
import com.gry.jdk8.lambda.vo.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * lambda 表达式
 *      语法由参数列表、箭头符号->和函数体组成
 *
 * stream: http://www.lambdafaq.org/what-is-a-stream/
 * 高阶函数: http://zh.wikipedia.org/wiki/%E9%AB%98%E9%98%B6%E5%87%BD%E6%95%B0
 *
 * Created by gaorongyu on 15/4/11.
 */
public class LambdaTest {

    /**
     * main
     */
    public static void main(String[] args) {
        LambdaTest test = new LambdaTest();
//        test.processNames();
//        test.accumulate();
        test.supplier();
        test.compare();
    }

    /**
     * filter(), map()都会产生新的流
     *
     */
    private String joinString(List<String> names) {
        return names.stream()
                .filter(name -> name.length() > 4)
                .map(name -> capitalize(name))
                .collect(Collectors.joining(","));
    }

    /**
     * map 映射
     * reduce 规约
     */
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

    /**
     *
     * stream和collection类似, 但侧重点不同:
     * collection 元素存储在内存、直接操作元素
     * stream 元素在被访问时才计算出来
     *
     * 所以stream 可以是无限的 例如NaturalSupplier
     *
     */
    public void supplier() {
        Stream<Long> natural = Stream.generate(new NaturalSupplier());
        // 函数体只有一行代码可以省去{}
        // natural.map((x) ->  { return x * x; })
        natural.map(x ->  x * x)
                .limit(10)
                .forEach(System.out::println);
    }

    public void compare() {
        //
        List<Person> personList = new ArrayList<>();
        Person p = new Person("Smith", "John");
        personList.add(p);
        p = new Person("Jenkins", "Kate");
        personList.add(p);

        // before
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        System.out.print(personList);

        // now
        personList.sort(Comparator.comparing(Person::getLastName));
        System.out.print(personList);
    }

}
