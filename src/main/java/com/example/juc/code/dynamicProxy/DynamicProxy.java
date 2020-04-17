package com.example.juc.code.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements DynamicInterface {

     public void add() {
        System.out.println("添加功能");
    }
}

