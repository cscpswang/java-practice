package com.hz.constantine.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author hzxianghaibo
 * @desc:
 * @since 2017/9/28
 */
public class ReflecttEST {

    @Test
    public void cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Cat.class);
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                    throws Throwable {
                System.out.println(method.getName());
                System.out.println("i'm eating");
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        Cat a = (Cat) enhancer.create();
        a.action();
    }

    static class Cat {
        public void action() {
            System.out.println("i'm running");
        }

    }
}
