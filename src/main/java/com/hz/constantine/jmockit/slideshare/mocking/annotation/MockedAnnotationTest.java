/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking.annotation;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Mocked;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 标识一个指定的class的实例或被测对象的参数被Mock掉。
 *               <p>
 *               除了基本类型和Array，其他所有类型都可以通过@Mocked标识来被Mock掉。<br\>
 *               当一个被测对象的参数被Mock的时候，执行测试时，该参数将不再按照原有的代码来实例化，而是将实例化的工作委托给JMockit来完成 </br\>
 *               </p>
 * @author: xiangji
 * @date: 2018/1/2 下午8:48
 * @version: V1.0.0
 */
public class MockedAnnotationTest {
    @Mocked
    ClassUnderTest classUnderTest;

    /**
     * 所有成员变量都被初始化了
     */
    @Test
    public void tested() {
        Assert.assertTrue(classUnderTest instanceof ClassUnderTest);
        Assert.assertTrue(classUnderTest.getEye() instanceof ClassUnderTest.Eye);
        Assert.assertTrue(classUnderTest.getName() instanceof ClassUnderTest.Name);
        Assert.assertTrue(classUnderTest.getRepository() instanceof ClassUnderTest.Repository);
    }

    /**
     * 实例的初始化由jmockit完成，并没有调用类定义中的构造方法，所以name为null
     */
    @Test
    public void construct(){
        Assert.assertNull(classUnderTest.getName().name);
    }

    /**
     * action方法被修改为返回null
     */
    @Test
    public void testedAction() {
        Assert.assertNull(classUnderTest.action());
    }

    /**
     * 影响到其他实例,其他实例返回null
     */
    @Test
    public void newInstance() {
        Assert.assertNull(new ClassUnderTest().eat());
    }

    /**
     * 影响到静态方法,返回了null
     */
    @Test
    public void staticMethod() {
        Assert.assertNull(ClassUnderTest.eat());
    }

}