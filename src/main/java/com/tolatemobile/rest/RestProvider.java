package com.tolatemobile.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.tolatemobile.enitiy.Delay;
import java.util.Collection;

public class RestProvider {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String BASE_URL = "http://localhost/tolate/api.php/tolate?transform=1";
    private static final String PUBLIC_BASE_URL = "https://www.whatismy.name/rest/api.php/tolate/?transform=1";

    private OkHttpClient client = new OkHttpClient();

    private Gson gson = new Gson();

    public List<Delay> getList() {
        String response = ""; //
        try {
            response = run(PUBLIC_BASE_URL);
            if (response.isEmpty()) {
                return new ArrayList<>();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("AUSGABE: " + response);

        
        Delay[] coll = gson.fromJson(response, Delay[].class);
        List<Delay> list = new ArrayList<>(Arrays.asList(coll));


        
//        //get Array of items and than transform it to a list for better handling
//        Type listType = new TypeToken<ArrayList<Delay>>() {
//        }.getType();
//        Collection<Delay> coll = gson.fromJson(response, listType);
//        
//        //transform Collection back to a List
//        List list;
//        if (coll instanceof List) {
//            list = (List) coll;
//        }else{
//            list = new ArrayList(coll);
//        }
        
        return list;
    }

    public boolean addDelay(Delay delay) {
        String json = delay.toJSON();
        String response = "";
        try {
            response = post(PUBLIC_BASE_URL, json);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return !response.isEmpty();
    }

    public int lastId() {
        String response = "";
        String filter = "&order=id,desc&columns=id&page=1,1";
        try {
            response = run(PUBLIC_BASE_URL + filter);
            System.out.println(response);
            if (response.isEmpty()) {
                //there is no feedback form the API give back 1
                return 1;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //This JSON is expected {"tolate":[{"id":2}],"_results":2}
        //remove all none Numbers
        response.replaceAll("[^-?0-9]+", " ");
        List<String> asList = Arrays.asList(response.trim().split(" "));

        return Integer.parseInt(asList.get(0));
    }

    private String run(String url) throws IOException {
        String output = "";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            output = response.body().string();
        } catch (Exception ex) {
            //output the Exception
            output = ex.getMessage();
        }
        return output;
    }

    private String post(String url, String json) throws IOException {
        String output = "";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            output = response.body().string();
        } catch (Exception ex) {
            //output the Exception
            output = ex.getMessage();
        }
        return output;
    }

}
