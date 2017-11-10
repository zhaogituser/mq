package com.test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by ${zhao} on 2017/11/9 0009.
 */
public class TestList {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("小名");
        linkedList.add("小花");
        linkedList.add("悟空");
        linkedList.add("悟能");

        String name = new String();
        ArrayList<String> arrayList = new ArrayList<String>();
        System.out.println(linkedList);
        System.out.println(name);
    }

}
