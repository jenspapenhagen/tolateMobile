package eu.papenhagen.tolatemobile.rest;

import java.io.IOException;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

public class RestProvider {

    private static final String BASE_URL = "http://localhost/tolate/api.php/tolate?transform=1";
    private static final String PUBLIC_BASE_URL = "http://phptestfield.byethost10.com/rest/tolate?transform=1";

//    OkHttpClient client = new OkHttpClient();
//
//    String run(String url) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
//    }

    public String getList() {
        RestProvider example = new RestProvider();
        String response = "";
//        try {
//            response = example.run(PUBLIC_BASE_URL);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
        return response;
    }
}
