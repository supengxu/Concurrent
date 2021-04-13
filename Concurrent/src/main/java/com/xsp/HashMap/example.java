package com.xsp.HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

public class example {
    public static void main(String[] args) {
        HashMap<Integer,Integer> a = new HashMap<>();
        a.put(1,1);
        a.get(1);
        Hashtable<Integer,Integer> b = new Hashtable<>();
        b.put(1,1);
    }
    public String largestNumber(int[] nums) {
        return Arrays.stream(nums).mapToObj(Integer::toString)
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))

                .reduce(String::concat)
                .filter(s->!s.startsWith("0"))
                .orElse("0");
    }
}
