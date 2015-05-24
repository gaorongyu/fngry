package com.gry.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gaorongyu on 15/4/14.
 */
public class RegexPattern {


    public static void main(String[] args) throws Exception {
        // 货位
        Pattern p = Pattern.compile("A-[0-9]{2}-[0-9]{2}");
        Matcher m = p.matcher("A-01-01");
        boolean b = m.matches();

        System.out.println(b);

        // 货架  平面货位
        p = Pattern.compile("A[0-9]{2}");
        m = p.matcher("A01");
        b = m.matches();
        System.out.println(b);

        // 区域
        p = Pattern.compile("[A-Z]{1}");
        m = p.matcher("A");
        b = m.matches();
        System.out.println(b);

    }

}
