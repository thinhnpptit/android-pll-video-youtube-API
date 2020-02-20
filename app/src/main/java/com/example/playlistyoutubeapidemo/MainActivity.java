package com.example.playlistyoutubeapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url ="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PL7qO0n6T9DDPXiawZgOOhI8ej2B5JCtWX&key=AIzaSyANz06T_PO9LSYiMhXdcMe-XHxbXOl21jo&maxResults=50";
    public  static String KEY_API ="AIzaSyANz06T_PO9LSYiMhXdcMe-XHxbXOl21jo";
    public static String PLL_ID = "PL7qO0n6T9DDPXiawZgOOhI8ej2B5JCtWX";

    ListView listView;
    ArrayList<VideoYoutube> listVideos;
    PlaylistAdapter playlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // anh xa
        listView = findViewById(R.id.listviewPlaylist);
        listVideos = new ArrayList<>();
        playlistAdapter = new PlaylistAdapter(this, R.layout.row_playlist, listVideos);
        listView.setAdapter(playlistAdapter);

        getJSONPlaylist(url);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PlayVideoActivity.class);
                intent.putExtra("idVideo", listVideos.get(position).getIdVideo());
                startActivity(intent);
            }
        });

    }

    private void getJSONPlaylist(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArrayItems = response.getJSONArray("items");
                            for (int i = 0; i < jsonArrayItems.length(); i++) {
                                JSONObject jsonObjectItem = jsonArrayItems.getJSONObject(i);
                                JSONObject jsonObjectSnippet = jsonObjectItem.getJSONObject("snippet");
                                String title = jsonObjectSnippet.getString("title");
                                // get Thumbnail
                                JSONObject jsonObjectThumb = jsonObjectSnippet.getJSONObject("thumbnails");
                                JSONObject jsonObjectDefault = jsonObjectThumb.getJSONObject("default");
                                String thumb = jsonObjectDefault.getString("url");
                                // get id Video
                                JSONObject jsonObjectResource = jsonObjectSnippet.getJSONObject("resourceId");
                                String videoId = jsonObjectResource.getString("videoId");

                                listVideos.add(new VideoYoutube(title, videoId, thumb));
                            }
                            playlistAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error+"", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

}
