package com.example.shreeda.sendrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.submitbtn);

        btn.setOnClickListener(this);
    }
        public static RequestQueue mRequestQueue;
        private void sendreq() {


            String url = "http://localhost:3000/index.html ";
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                response = response.getJSONObject("args");
                                String site = response.getString("site"),
                                        network = response.getString("network");
                                System.out.println("Site: " + site + "\nNetwork: " + network);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            queue.add(jsonRequest);

        }

        @Override
        protected void onStop() {
            super.onStop();
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {

                    return true; 
                }
            });
    }

    @Override
    public void onClick(View v) {
        if(v==btn){
            sendreq();
        }
    }
}


