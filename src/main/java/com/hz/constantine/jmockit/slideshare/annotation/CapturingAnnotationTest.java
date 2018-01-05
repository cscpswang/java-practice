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

    @Test
    public void tested() {
        Assert.assertTrue(classUnderTest instanceof ClassUnderTest);
        Assert.assertTrue(classUnderTest.getEye() instanceof ClassUnderTest.Eye);
        Assert.assertTrue(classUnderTest.getName() instanceof ClassUnderTest.Name);
        Assert.assertTrue(classUnderTest.getRepository() instanceof ClassUnderTest.Repository);
    }

    @Test
    public void construct(){
        Assert.assertNull(classUnderTest.getName().name);
    }

    @Test
    public void testedAction() {
        Assert.assertNull(classUnderTest.action());
    }

    @Test
    public void newInstance() {
        Assert.assertNull(new ClassUnderTest().eat());
    }

    @Test
    public void staticMethod() {
        Assert.assertNull(ClassUnderTest.eat());
    }

    final class ClassUnderTestSub extends ClassUnderTest {

    }

    @Test
    public void inherit() {
        Assert.assertNull(new ClassUnderTestSub().eat());
    }
}