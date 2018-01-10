/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.annotation;

import mockit.Injectable;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;

import mockit.Tested;

/**
 * @Description: 测试tested标签的fullyInitialized属性, tested标签的测试
 *               <code>com.hz.constantine.jmockit.slideshare.mocking.annotation.single.TestedAnnotationTest</code>
 *               <p>
 *               如果同时Tested的fullyInitialized参数被设置成了true，则被测对象的所有参数都需要被初始化成一个合适的值。
 *               </p>
 * @author: xiangji
 * @date: 2018/1/2 下午8:25
 * @version: V1.0.0
 */
public class TestedFullyInitializeAnnotationTest {
    @Tested(fullyInitialized = true)
    ClassUnderTest testedUnitFullyInitial;

    /**
    * 由于testedUnitFullyInitial的name属性构造函数不是默认的，需要注入.
    */
    @Injectable
    String alias = "testAlias";

    @Test
    public void tested() {
        Assert.assertTrue(testedUnitFullyInitial instanceof ClassUnderTest);
        Assert.assertTrue(testedUnitFullyInitial.getEye() instanceof ClassUnderTest.Eye);
        Assert.assertTrue(testedUnitFullyInitial.getName() instanceof ClassUnderTest.Name);
        Assert.assertTrue(testedUnitFullyInitial.getRepository() instanceof ClassUnderTest.Repository);

        Assert.assertEquals(testedUnitFullyInitial.getAlias().toString(), alias);
    }

    @Test
    public void testedAction() {
        ClassUnderTest classUnderTest = new ClassUnderTest();
        classUnderTest.setRepository(new ClassUnderTest.Repository());
        classUnderTest.setEye(new ClassUnderTest.Eye());
        classUnderTest.setName(new ClassUnderTest.Name());
        String expectResult = classUnderTest.action();

        String result = testedUnitFullyInitial.action();

        Assert.assertEquals(expectResult,result);
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