package eu.papenhagen.tolatemobile.rest;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestProvider {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String BASE_URL = "http://localhost/tolate/api.php/tolate?transform=1";
    private static final String PUBLIC_BASE_URL = "http://phptestfield.byethost10.com/rest/tolate?transform=1";

    OkHttpClient client = new OkHttpClient();

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String bowlingJson(Delay delay) {
        //TODO build JSON
        String output = "{"
                + "}";

        return output;
    }

    public String getList() {
        RestProvider example = new RestProvider();
        String response = "";
        try {
            response = example.run(PUBLIC_BASE_URL);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return response;
    }

    public boolean addDelay(Delay delay) {
        RestProvider example = new RestProvider();
        String json = bowlingJson(delay);
        String response = "";
        try {
            response = example.post(PUBLIC_BASE_URL, json);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return !response.isEmpty();

    }

}
