/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking.annotation;

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

    /**
     * 只初始化了自己,成员变量并没有初始化
     */
    @Test
    public void tested() {
        Assert.assertTrue(testedUnit instanceof ClassUnderTest);
        Assert.assertNull(testedUnit.getEye());
        Assert.assertNull(testedUnit.getRepository());
    }

    /**
     * 实例的初始化并未完全托管给jmockit，调用类定义中的构造方法，所以name为正确值
     */
    @Test
    public void construct(){
        Assert.assertEquals(testedUnit.getName().name,DEFAULT_NAME);
    }

    /**
    * 因为成员变量没有初始化，抛出NPE
    */
    @Test(expectedExceptions = NullPointerException.class)
    public void testedAction() {
        testedUnit.action();
    }

    /**
     * 没有影响到其他实例,其他实例正确的返回了值
     */
    @Test
    public void newInstance() {
        Assert.assertEquals(new ClassUnderTest().eat(),ClassUnderTest.EATING);
    }

    /**
     * 没有影响到静态方法,返回了正确值
     */
    @Test
    public void staticMethod() {
        Assert.assertEquals(ClassUnderTest.eat(),ClassUnderTest.EATING);
    }
}