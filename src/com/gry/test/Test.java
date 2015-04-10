package com.gry.test;

import java.net.URLDecoder;

/**
 * Created by gaorongyu on 15/3/26.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        String str = URLDecoder.decode("companyName%C3%A6%C2%B0%C2%B8%C3%A9%C2%A1%C2%BApage1size20", "ANSI");
        System.out.println(str);
    }

}
