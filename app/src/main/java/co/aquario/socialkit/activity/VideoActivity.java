package co.aquario.socialkit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.liveo.adapter.AdapterVideos;
import br.liveo.model.Video;
import br.liveo.navigationviewpagerliveo.R;

public class VideoActivity extends Activity {



    public AQuery aq;
    private int page;
    String url = "http://api.vdomax.com/search/video/%E0%B9%82%E0%B8%AA%E0%B8%94?from=0&limit=20";
    ArrayList<Video> list = new ArrayList<Video>();

    AdapterVideos adapterVideos;
    ListView ls;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_videos);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Sam Savek (@samsavek)");
        //BusProvider.getInstance().register(this);



        aq = new AQuery(this);
        adapterVideos = new AdapterVideos(this, list);
        ls = (ListView) findViewById(R.id.list_youtube);
        ls.setAdapter(adapterVideos);

        adapterVideos.SetOnItemClickListener(new AdapterVideos.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                String idYoutube = list.get(1).getYoutubeId();
                String title = list.get(1).getTitle();
                String description = list.get(1).getDesc();
                String userProfile = list.get(1).getpName();

                Intent i = new Intent(getApplication(),ActivityVideoPlaying.class);
                i.putExtra("url",idYoutube);
                i.putExtra("title",title);
                i.putExtra("description",description);
                i.putExtra("userProfile",userProfile);
                startActivity(i);
            }
        });



        aq.ajax(url, JSONObject.class, this, "getjson");

    }

    public void getjson(String url, JSONObject jo, AjaxStatus status)
            throws JSONException {
        AQUtility.debug("jo", jo);

        if (jo != null) {
            JSONArray ja = jo.getJSONArray("result");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject obj = ja.getJSONObject(i);

                String mediaType = obj.optString("media_type");

                if(mediaType.equals("youtube")) {
                    String postId = obj.optString("post_id");
                    String title = obj.optString("youtube_title");
                    String desc = obj.optString("youtube_description");
                    String youtubeId = obj.optString("youtube_video_id");
                    String text = obj.optString("text");
                    String timestamp = obj.optString("time");
                    String view = obj.optString("view");

                    JSONObject publisher = obj.optJSONObject("publisher");
                    String pUserId = publisher.optString("id");
                    String pName = publisher.optString("username");
                    //JSONObject cover = publisher.optJSONObject("cover");
                    String pAvatar = publisher.optString("avatar_url");

                    Video item = new Video(postId,title,desc,youtubeId,text,timestamp,view,pUserId,pName,pAvatar);
                    list.add(item);
                }




                /*

                JSONObject youtube = obj.getJSONObject("youtube");
                String idUserYourTube = youtube.optString("id");
                String titleUserYouTube = youtube.optString("title");
                String description = youtube.optString("description");
                String thumbnailYouTube = youtube.optString("thumbnail");

                String shortMessage;
                if (description.length() > 200)
                    shortMessage = description.substring(0, 70);
                else
                    shortMessage = description;

                JSONObject author = obj.getJSONObject("author");
                String imageUser = author.optString("avatar");
                String imageTitleUser = "https://www.vdomax.com/" + imageUser + "";

                Youtube list_item = new Youtube(idUserYourTube, titleUserYouTube, shortMessage, thumbnailYouTube,imageTitleUser);

                */



            }
            adapterVideos.notifyDataSetChanged();
            AQUtility.debug("done");

        } else {
            AQUtility.debug("error!");
        }
    }

}