package soundlly.slack;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by soundlly on 2017. 9. 21..
 */

public class Post extends AppCompatActivity {



    public static void post(String getSMSName, String getSMSText, Context context) {

        //시간 받아오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String formatDate = sdfNow.format(date);

        //RequestQueue에 MainActivity에서 받아온 context값 전달
        RequestQueue queue = Volley.newRequestQueue(context);

        String url =  "https://hooks.slack.com/services/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";

        //JsonObject에 필요한 값들 put
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("username", getSMSName);
            jsonParam.put("icon_emoji",":credit_card:");
            jsonParam.put("attachments", "");
            jsonParam.put("color", "#36a64f");
            jsonParam.put("pretext",formatDate);
            jsonParam.put("text",getSMSText);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonParam, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response: ", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
            }
        });


        queue.add(request);

    }

}
