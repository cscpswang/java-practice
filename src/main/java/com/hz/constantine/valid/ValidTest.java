/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.valid;

import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/7/9 下午9:05
 * @version: V1.0.0
 */
public class ValidTest {

    @Data
    public static class Children {

        @NotNull
        private String name;

        @Min(0)
        private int ege;

    }

    @Test
    public void validChildren() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Children children = new Children();
        children.setEge(1);

        Set<ConstraintViolation<Children>> result = validator.validate(children);
        Assert.assertEquals(result.size(), 1);
    }
}