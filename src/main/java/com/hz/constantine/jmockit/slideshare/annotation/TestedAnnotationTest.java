/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.annotation;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.hz.constantine.jmockit.dependency.ClassUnderTest.*;

/**
 * @Description: 测试 annotation[tested] Tested，标识一个被测对象的实例。
 *               <p>
 *               通过 Tested标签标识的被测对象实例，将会在测试方法执行之前被初始化。<br/>
 *               可以通过Inject注解对被测对象的构造函数进行改写。如果没有@Inject，则采用原有代码进行初始化。<br/>
 *               </p>
 * @author: xiangji
 * @date: 2018/1/2 下午8:00
 * @version: V1.0.0
 */
public class TestedAnnotationTest {

    @Tested
    ClassUnderTest testedUnit;

    @Test
    public void tested() {
        Assert.assertTrue(testedUnit instanceof ClassUnderTest);
        Assert.assertNull(testedUnit.getEye());
        Assert.assertNull(testedUnit.getRepository());
    }

    @Test
    public void construct(){
        Assert.assertEquals(testedUnit.getName().name,DEFAULT_NAME);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testedAction() {
        testedUnit.action();
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