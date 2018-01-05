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
    public void newInstance() {
        Assert.assertNull(new ClassUnderTest().eat());
    }

    @Test
    public void staticMethod() {
        Assert.assertNull(ClassUnderTest.eat());
    }

    @Test
    public void testedAction() {
        Assert.assertNull(classUnderTest.action());
    }

}