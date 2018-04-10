package com.tolatemobile.rest;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;

import com.tolatemobile.enitiy.Delay;
import com.tolatemobile.enitiy.JsonListHelper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;

public class RestProvider {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(RestProvider.class);

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //private static final String BASE_URL = "http://localhost/tolate/api.php/tolate?transform=1";
    private static final String BASE_URL = "https://www.whatismy.name/rest/api.php/tolate/?transform=1";

    private OkHttpClient client = new OkHttpClient();

    private Gson gson = new Gson();

    public List<Delay> getList() {
        String response = "";
        String filter = "&filter=date,eq,"; //&filter=date,eq,2017-02-15

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String today = formatter.format(LocalDate.now());
        System.out.println("the URL: " + BASE_URL + filter + today);

        try {
            response = run(BASE_URL + filter + today);
            LOG.debug(BASE_URL + filter + today);

            if (response.isEmpty()) {
                LOG.error("empty LIST check URL" + BASE_URL);
                return new ArrayList<>();
            }
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
        }

        //This JSON is expected {"tolate":[{"id":1,"date":"2017-02-15","name":"Jens","delaytime":15,"ursache":"testeintrag","entschuldigt":1}, .....
        LOG.debug("AUSGABE: " + response);
        JsonListHelper warpper = gson.fromJson(response, JsonListHelper.class);
        List<Delay> list = warpper.getTolate();

        return list;
    }

    public boolean addDelay(Delay delay) {
        String json = delay.toJSON();
        String response = "";
        try {
            response = post(BASE_URL, json);
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
        }

        return !response.isEmpty();
    }

    public int lastId() {
        String response = "";
        String filter = "&order=id,desc&columns=id&page=1,1";
        try {
            response = run(BASE_URL + filter);
            LOG.debug(BASE_URL + filter);
            LOG.debug(response);
            if (response.isEmpty()) {
                LOG.error("LastID do not get a Response form the Server");
                //there is no feedback form the API give back 1
                return 1;
            }
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
        }

        //This JSON is expected {"tolate":[{"id":2}],"_results":2}
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(response);

        List<Integer> tempList = new ArrayList<>();
        while (matcher.find()) {
            tempList.add(Integer.parseInt(matcher.group()));
        }

        if (tempList.isEmpty()) {
            LOG.error("ERROR list is empty");
        }

        return tempList.get(1);
    }

    private String run(String url) throws IOException {
        String output = "";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            output = response.body().string();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
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
            LOG.error(ex.getMessage());
        }
        return output;
    }

}
