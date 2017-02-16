package com.example.lolipop.materialbannerpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {
    SliderLayout sliderShow;
    public ArrayList<SlideItem> slideItemArrayList = new ArrayList<>();
    public static String url = "http://banglahdnatok.com/timer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sliderShow = (SliderLayout) findViewById(R.id.slider);


        jsonCall();
    }

    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    public void jsonCall(){
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("", response.toString());


                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                SlideItem shopping = new SlideItem();
                                shopping.setTitle(obj.getString("title"));
                                shopping.setLink(obj.getString("link"));
                                slideItemArrayList.add(shopping);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                        callSlidingPanel(slideItemArrayList);


                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        //adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
            }
        });


        movieReq.setShouldCache(false);
        AppController.getInstance().getRequestQueue().getCache().remove(url);
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }


    public void callSlidingPanel(ArrayList<SlideItem> slideItemArrayList){
        int number = slideItemArrayList.size();

        Toast.makeText(getApplicationContext(), ""+number, Toast.LENGTH_SHORT).show();
        for (int i =0 ; i<slideItemArrayList.size();i++) {
            SlideItem slideItem1 = slideItemArrayList.get(i);

            TextSliderView textSliderView = new TextSliderView(getApplicationContext());
            textSliderView
                    .description(slideItem1.getTitle())
                    .image(slideItem1.getLink())
                    .setOnSliderClickListener(MainActivity.this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", slideItem1.getTitle());

            sliderShow.addSlider(textSliderView);

        }

    }
}
