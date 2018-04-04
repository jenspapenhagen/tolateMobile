package eu.papenhagen.tolatemobile.rest;

import java.util.ArrayList;
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

import eu.papenhagen.tolatemobile.enitiy.Delay;

public class RestProvider {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String BASE_URL = "http://localhost/tolate/api.php/tolate?transform=1";
    private static final String PUBLIC_BASE_URL = "http://phptestfield.byethost10.com/rest/tolate?transform=1";

    OkHttpClient client = new OkHttpClient();

    public List<Delay> getList() {
        RestProvider example = new RestProvider();
        String response = "";
        try {
            response = example.run(PUBLIC_BASE_URL);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        if(response.isEmpty()){
            return new ArrayList<>();
        }
        
        //TODO get strange javascript is needed error
        // <html><body><script type="text/javascript" src="/aes.js" ></script><script>function toNumbers(d){var e=[];d.replace(/(..)/g,function(d){e.push(parseInt(d,16))});return e}function toHex(){for(var d=[],d=1==arguments.length&&arguments[0].constructor==Array?arguments[0]:arguments,e="",f=0;f<d.length;f++)e+=(16>d[f]?"0":"")+d[f].toString(16);return e.toLowerCase()}var a=toNumbers("f655ba9d09a112d4968c63579db590b4"),b=toNumbers("98344c2eee86c3994890592585b49f80"),c=toNumbers("6685b7529b067f024fbb99e0e572bd8c");document.cookie="__test="+toHex(slowAES.decrypt(c,2,a,b))+"; expires=Thu, 31-Dec-37 23:55:55 GMT; path=/"; location.href="http://phptestfield.byethost10.com/rest/tolate?transform=1&i=1";</script><noscript>This site requires Javascript to work, please enable Javascript in your browser or use a browser with Javascript support</noscript></body></html>
        System.out.println("AUSGABE: " + response);
        
        
        //convert to a List
        Type listType = new TypeToken<ArrayList<Delay>>(){}.getType();
        List<Delay> list = new Gson().fromJson(response, listType);
        
        return list;
    }

    public boolean addDelay(Delay delay) {
        RestProvider example = new RestProvider();
        String json = delay.toJSON();
        String response = "";
        try {
            response = example.post(PUBLIC_BASE_URL, json);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return !response.isEmpty();

    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response != null) {
                return response.body().string();
            }
        }
        return "";
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

}
