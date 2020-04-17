package com.example.juc.code.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {

    // 无参构造接收对象
    private Object target;

    public MyProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // 首先执行判断权限的方法（模拟）
        System.out.println("模拟判断权限");
        // Object invoke(Object proxy,Method method,Object[]
        // args)：在代理实例上处理方法调用并返回结果。
        Object ob = method.invoke(target, args);
        System.out.println("模拟日志记录");
        return ob;

    }

}

