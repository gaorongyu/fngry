package com.gry.test.instrument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 *
 * 读取class字节码替换目标虚拟机运行中的class
 *
 * Created by gaorongyu on 14/12/29.
 *
 */
public class Transformer implements ClassFileTransformer {

    private static final String TRANSFORM_CLASS = "com/gry/test/server/Simulator.class";

    private static final String TRANSFORM_CLASS_NAME = "com/gry/test/server/Simulator";


    public static byte[] getBytesFromFile(String fileName) {
        try {
            // precondition
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            long length = file.length();
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset <bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
            is.close();
            return bytes;
        } catch (Exception e) {
            System.out.println("error occurs in _ClassTransformer!"
                    + e.getClass().getName());
            return null;
        }
    }

    public byte[] transform(ClassLoader l, String className, Class<?> c,
            ProtectionDomain pd, byte[] b) throws IllegalClassFormatException {
        System.out.println("transform executed...");
        System.out.println("enter transform className is : " + className);
        if (!className.equals(TRANSFORM_CLASS_NAME)) {
            return null;
        }
        System.out.println("before transform class is : " + TRANSFORM_CLASS);
        return getBytesFromFile(TRANSFORM_CLASS);
    }

}
