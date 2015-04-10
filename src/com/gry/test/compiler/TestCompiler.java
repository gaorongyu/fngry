package com.gry.test.compiler;

import com.sun.btrace.BTraceUtils;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by gaorongyu on 14/12/29.
 */
public class TestCompiler {

    public static void main(String[] args) throws Exception{

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        int result = compiler.run(null, null, null, "/Users/gaorongyu/dev/idea/javatest/src/com/gry/test/compiler/Demon.java");

        System.out.println("Result code: " + result);


//        Runtime run = Runtime.getRuntime();
//
//        Process p = run.exec("java /Users/gaorongyu/dev/idea/javatest/src/com/gry/test/compiler/Demon");
//        BufferedInputStream bis = new BufferedInputStream(p.getInputStream());
//        BufferedReader br = new BufferedReader(new InputStreamReader(bis));
//
//        String s = null;
//        while((s = br.readLine()) != null) {
//            System.out.println(s);
//        }

    }

}
