package com.example.juc.code.dynamicProxy;

import java.lang.reflect.Proxy;

public class test {
             public static void main(String[] args) {

                 DynamicInterface d = new DynamicProxy();

                 MyProxy m = new MyProxy(d);

                 DynamicInterface dynamicInterface = (DynamicInterface)Proxy.newProxyInstance(d.getClass().getClassLoader(),d.getClass().getInterfaces(),m);

                 dynamicInterface.add();

             } }
