/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tolatemobile.enitiy;

import java.util.List;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
/**
 *
 * @author jay
 */
public class JsonListHelper{
    public static final <T> List<T> getList(String json) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Type typeOfList = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(json, typeOfList);
    }
}
