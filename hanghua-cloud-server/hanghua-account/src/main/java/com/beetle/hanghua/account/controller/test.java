package com.beetle.hanghua.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;

public class test {

    public static void main(String[] args) {
        Class clazz = test.class;
        Annotation annotation = clazz.getAnnotation(RequestMapping.class);
        System.out.println(annotation);


    }
}
