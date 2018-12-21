/**
 * Copyright (c) 2014-2018, NetEase, Inc. All Rights Reserved.
 */
package com.hz.constantine.algorithm.greddy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.google.common.collect.Sets;

/**
 * @Description: 贪婪算法,求解广播覆盖问题
 * @author: xiangji
 * @date: 2018/11/27 9:43 AM
 * @version: V1.0.0
 */
public class GreedyAlgorithm {

    public Set<String> calculate(Set<String> states, Map<String, Set<String>> stations) {
        Set<String> result = Sets.newHashSet();

        Set<String> stateNeed = new HashSet<>(states);

        while (stateNeed.size()>0) {
            String bestState = "";
            Integer bestIntersection = 0;
            for (Map.Entry<String, Set<String>> entry : stations.entrySet()) {
                Set<String> intersection = new HashSet<>(entry.getValue());
                intersection.retainAll(stateNeed);
                if ("".equals(bestState)) {
                    bestState = entry.getKey();
                    bestIntersection = intersection.size();
                    continue;
                }

                if (intersection.size() > bestIntersection) {
                    bestState = entry.getKey();
                    bestIntersection = intersection.size();
                }
            }
            result.add(bestState);
            stateNeed.removeAll(stations.get(bestState));
        }

        return result;
    }

    @Test
    public void test() {
        Set<String> states = Sets.newHashSet("mt", "wa", "or", "id", "nv", "ut", "ca", "az");
        Map<String, Set<String>> stations = Maps.newHashMap();
        stations.put("kOne", Sets.newHashSet("id", "nv", "ut"));
        stations.put("kTwo", Sets.newHashSet("wa", "id", "mt"));
        stations.put("kThree", Sets.newHashSet("or", "nv", "ca"));
        stations.put("kFour", Sets.newHashSet("nv", "ut"));
        stations.put("kFive", Sets.newHashSet("ca", "az"));

        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm();
        Set<String> result = greedyAlgorithm.calculate(states, stations);
        Assert.assertTrue(Sets.newHashSet("kTwo", "kThree", "kFour", "kFive").containsAll(result)
                || Sets.newHashSet( "kOne", "kTwo", "kThree", "kFive" ).containsAll(result));

    }

}