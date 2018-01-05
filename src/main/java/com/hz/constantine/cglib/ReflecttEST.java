package com.hz.constantine.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.*;
import org.testng.annotations.Test;

/**
 * @author hzxianghaibo
 * @desc:
 * @since 2017/9/28
 */
public class ReflecttEST {

    @Test
    public void cglib() {
        Cat proxy = (Cat) Enhancer.create(Cat.class, null, new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if ("gender".equals(method.getName())) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }, new Callback[] { new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println(method.getName());
                return "girl";
            }
        }, NoOp.INSTANCE });
        proxy.action();
    }

    static class Cat {
        public void action() {
            System.out.println("i'm running, i'm a " + gender());
        }

        private String gender() {
            return "boy";
        }

    }
}
