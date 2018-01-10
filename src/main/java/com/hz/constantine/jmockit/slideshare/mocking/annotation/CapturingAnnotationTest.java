/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking.annotation;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Capturing;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 标识一个被Mock的对象，从该对象派生的子类也被Mock了。<br/>
 *               可以通过maxInstances方法来制定最大有多少层子类也被mock<br/>
 * @author: xiangji
 * @date: 2018/1/3 上午9:50
 * @version: V1.0.0
 */
public class CapturingAnnotationTest {
    @Capturing
    public ClassUnderTest classUnderTest;

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

    final class ClassUnderTestSub extends ClassUnderTest {

    }

    /**
     * 影响到子类,返回了null
     */
    @Test
    public void inherit() {
        Assert.assertNull(new ClassUnderTestSub().eat());
    }
}