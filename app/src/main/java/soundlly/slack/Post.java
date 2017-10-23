package soundlly.slack;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by soundlly on 2017. 9. 21..
 */

public class Post {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    static OkHttpClient client = new OkHttpClient();

    public static void post(String getSMSName, String getSMSText) throws IOException {

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String formatDate = sdfNow.format(date);

        String url = "https://hooks.slack.com/services/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";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", getSMSName);
            jsonObject.put("icon_emoji", ":credit_card:");
            jsonObject.put("attachments", "");
            jsonObject.put("color", "#36a64f");
            jsonObject.put("pretext", formatDate);
            jsonObject.put("text", getSMSText);
        } catch (JSONException e) {
            Log.e("JsonError", e.toString());
        }

        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("respone", response.toString());
            }
        });
    }

}
