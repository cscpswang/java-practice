/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.annotation;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.Injectable;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Description: 标识只有一个指定的被测对象的内部变量被mock<br/>
 *               其他和被mock实例采用相同对象的实例不受影响。<br/>
 * @author: xiangji
 * @date: 2018/1/3 上午9:02
 * @version: V1.0.0
 */
public class InjectableAnnotationTest {

    @Injectable
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
        Assert.assertEquals(new ClassUnderTest().eat(),ClassUnderTest.EATING);
    }

    @Test
    public void staticMethod() {
        Assert.assertEquals(ClassUnderTest.eat(),ClassUnderTest.EATING);
    }
}