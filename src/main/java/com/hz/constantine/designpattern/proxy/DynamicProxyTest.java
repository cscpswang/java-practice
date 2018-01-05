/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xianghaibo
 * @date: 2017/12/15 10:01
 * @version: V1.0.0
 */
public class DynamicProxyTest {

    private final static Logger logger = LoggerFactory.getLogger(DynamicProxyTest.class);

    interface Isubject {
        void action();
    }

    static class Concrete implements Isubject {
        @Override
        public void action() {
            logger.info(" i'm concrete");
        }
    }

    @Test
    public void dynamicProxyJdk() {
class Proxier implements InvocationHandler {
            private Object target;

            public Proxier(Object target) {
                this.target = target;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                preAction();
                Object result = method.invoke(target, args);
                postAction();
                return result;
            }

            private void preAction() {
                logger.info("pre action");
            }

            private void postAction() {
                logger.info("post action");
            }
        }

        Concrete concrete = new Concrete();
        Isubject isubject = (Isubject) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[] { Isubject.class }, new Proxier(concrete));
        isubject.action();

    }

    @Test
    public void dynamicProxyCglib() {
        class Proxier implements MethodInterceptor {

            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                    throws Throwable {
                preAction();
                Object result = methodProxy.invokeSuper(o, objects);
                postAction();
                return result;
            }

            private void preAction() {
                logger.info("pre action");
            }

            private void postAction() {
                logger.info("post action");
            }
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Concrete.class);
        enhancer.setCallback(new Proxier());
        Isubject concrete = (Isubject) enhancer.create();
        concrete.action();
    }

}