/**
 * Copyright (c) 2014-2017, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.jmockit.slideshare.mocking;

import com.hz.constantine.jmockit.dependency.ClassUnderTest;
import mockit.*;
import mockit.internal.expectations.invocation.MissingInvocation;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @Description: record-replay-verify Mode <br/>
 *              @see <a href="http://jmockit.org/tutorial/Mocking.html#model">jmockit tutorial/a>
 * @author: xiangji
 * @date: 2017/12/20 下午9:00
 * @version: V1.0.0
 */
public class RecordReplayVerifyModeTest {
    @Tested ClassUnderTest classUnderTest;

    @Test
    public void recordReplyOnly() {
        ClassUnderTest classUnderTestInstance = newClassUnderTestInstance();

        final String data = this.getClass().getSimpleName();
        new Expectations(classUnderTestInstance.getEye(), classUnderTestInstance.getRepository()) {
            {
                classUnderTestInstance.getEye().find();
                result = data;
                classUnderTestInstance.getRepository().insert(data);
                times = 1;
            }
        };

        String result = classUnderTestInstance.action();

        Assert.assertTrue(result.contains(data));
    }

    @Test
    public void replayVerifyOnly(@Injectable ClassUnderTest.Eye eye,@Injectable
            ClassUnderTest.Repository repository){
        classUnderTest.action();

        new Verifications(){
            {
                eye.find();
                times = 1;
                repository.insert(anyString);
                times = 1;
            }
        };
    }

    @Test
    public void recordReplyVerifyInOrder(){
        ClassUnderTest classUnderTestInstance = newClassUnderTestInstance();

        final String data = this.getClass().getSimpleName();
        new Expectations(classUnderTestInstance) {
            {
                classUnderTestInstance.action();
                result = data;
            }
        };

        String actionResult = classUnderTestInstance.action();

        new VerificationsInOrder(){
            {
                classUnderTestInstance.action();
                times = 1;
                Assert.assertTrue(actionResult.contains(data));
            }
        };

    }

    @Test(expectedExceptions = MissingInvocation.class)
    public void recordReplyVerifyNotInOrder() {
        ClassUnderTest classUnderTestInstance = newClassUnderTestInstance();

        final String data = this.getClass().getSimpleName();
        new Expectations(classUnderTestInstance.getEye(), classUnderTestInstance.getRepository()) {
            {
                classUnderTestInstance.getEye().find();
                result = data;
                classUnderTestInstance.getRepository().insert(data);
                times = 1;
            }
        };

        classUnderTestInstance.action();

        new VerificationsInOrder(){
            {
                classUnderTestInstance.getRepository().insert(data);
                classUnderTestInstance.getEye().find();
            }
        };
    }

    private ClassUnderTest newClassUnderTestInstance() {
        return injectClassUnderTest(new ClassUnderTest());
    }

    private ClassUnderTest injectClassUnderTest(ClassUnderTest classUnderTest){
        ClassUnderTest.Eye eye = new ClassUnderTest.Eye();
        ClassUnderTest.Repository repository = new ClassUnderTest.Repository();
        classUnderTest.setEye(eye);
        classUnderTest.setRepository(repository);
        return classUnderTest;
    }

}