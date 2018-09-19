/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.practice.tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: xiangji
 * @date: 2018/6/15 下午3:02
 * @version: V1.0.0
 */
public class FilterFromJsonFile {

    private String getJsonString() throws IOException {
        return new String(Files.readAllBytes(Paths.get("/Users/xiangji/Downloads", "t.out")), "utf-8");
    }

    public void deal() throws IOException {
        String jsonStr = getJsonString();
        JSONArray array = JSONObject.parseObject(jsonStr).getJSONObject("body").getJSONArray("data");
        Object[] objects =  array.parallelStream().filter(obj -> {
            JSONObject jsonObject = (JSONObject) obj;
            String dbTypeDesc = jsonObject.getString("dbTypeDesc");
            if (dbTypeDesc.equalsIgnoreCase("oracle:main")) {
                return true;
            }
            return false;
        }).filter(obj -> {
            JSONObject jsonObject = (JSONObject) obj;
            String sqlText = jsonObject.getString("sqlText");
            if (sqlText.contains("select")) {
                return true;
            }
            return false;
        }).toArray();

        System.out.println("=============="+objects.length+"===!!! ");

        Arrays.asList(objects).forEach(obj -> {
            JSONObject jsonObject = (JSONObject) obj;
            String sqlText = jsonObject.getString("sqlText");
            System.out.println(sqlText);
        });
    }

    @Test
    public void call() throws IOException {
        new FilterFromJsonFile().deal();
    }
}