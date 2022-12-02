package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tResult;
    EditText input;
    String textApi="";
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void callApi(View view) {

        tResult = (TextView) findViewById(R.id.t2);
        input = (EditText) findViewById(R.id.phone);
        //Log.e("input : ", input.getText().toString());

        String url = "https://run.mocky.io/v3/9e82820e-c477-486f-b789-d097004b0e74";
        String url2 = "https://moderation.logora.fr/predict?text="+input.getText().toString();
        //Log.e("rel2 : ", url2);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url2,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response : ", response.toString());
                        textApi = response.toString() ;
                        try {
                            JSONObject emp=(new JSONObject(response.toString())).getJSONObject("prediction");
                            name = emp.getString("0");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // fetch JSONObject named employee
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest error : ", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);
        if(name.length() > 0) {
            tResult.setText("prediction : "+name);
        }
        tResult.setText("prediction : "+name);
        Log.e("input : ", input.getText().toString());
        Log.e("prediction : ", name);
    }
}