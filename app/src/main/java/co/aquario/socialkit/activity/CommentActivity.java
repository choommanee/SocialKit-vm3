package co.aquario.socialkit.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.liveo.adapter.AdapterComment;
import br.liveo.model.Comment;
import br.liveo.model.Post;
import br.liveo.navigationviewpagerliveo.R;
import br.liveo.widget.RoundedTransformation;

public class CommentActivity extends ActionBarActivity {

    ArrayList<Comment> list = new ArrayList<Comment>();
    AdapterComment adapterComment;
    String url3 = "http://ihdmovie.xyz/feed3.json";
    public AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        aq = new AQuery(getApplicationContext());
        adapterComment = new AdapterComment(getBaseContext(), list);

        Picasso.with(getBaseContext())
                .load("https://www.vdomax.com/photos/2014/12/5YU6Z_60109_4473d870b5e31faa40d2c45e1ff6dc27.jpg")
                .centerCrop()
                .resize(100, 100)
                .transform(new RoundedTransformation(40, 4))
                .into(imageView);

        ImageView imageComment = (ImageView) findViewById(R.id.image_center);

        if(imageComment != null){
            imageComment.setVisibility(View.GONE);
        }


        RecyclerView recList = (RecyclerView) findViewById(R.id.comment_list);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        recList.setAdapter(adapterComment);
        aq.ajax(url3, JSONObject.class, this, "getJson");
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    public void getJson(String url, JSONObject jo, AjaxStatus status)
            throws JSONException {
        AQUtility.debug("jo", jo);
        Log.d("Check_Feed:", "Test1");
        if (jo != null) {
            JSONArray ja = jo.getJSONArray("posts");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject obj = ja.getJSONObject(i);

                JSONObject media = obj.getJSONObject("media");
                String avatarId = media.getString("id");
                String imagePhotoUrl = media.getString("url");
                String extension = media.getString("extension");

                String imagePhotoFullUrl = "https://www.vdomax.com/" + imagePhotoUrl + "." + extension + "";
                Log.i(".......", imagePhotoFullUrl);

                String imageAvatarUrl = "https://graph.facebook.com/v2.1/" + avatarId + "/picture?type=large";


                JSONObject author = obj.getJSONObject("author");
                String name = author.getString("name");

                //Log.d("Check",obj.toString());

                String name_title = obj.getString("type1");
                String loveCount = obj.getString("love_count");
                String number2 = obj.getString("follow_count");
                String commentCount = obj.getString("comment_count");
                String viewCount = obj.getString("view");
                String message = obj.getString("text");
                String date = obj.getString("timestamp");

//                String view = obj.getString("view");
//                String image_messen = obj.getString("image_messen");
//                String number4 = obj.getString("number4");
//              String date = obj.getString("date");

                String shortMessage;
                if (message.length() > 200)
                    shortMessage = message.substring(0, 50);
                else
                    shortMessage = message;


                ArrayList<Comment> comments = new ArrayList<>();
                if (Integer.parseInt(commentCount) > 0) {
                    JSONArray commentJsonArray = obj.getJSONArray("comment");

                    for (int a = 0; a < commentJsonArray.length(); a++) {
                        JSONObject commentJsonObject = commentJsonArray.getJSONObject(a);
                        String commentText = commentJsonObject.optString("text");
                        JSONObject accountJsonObject = commentJsonObject.getJSONObject("account");
                        String commentId = accountJsonObject.optString("id");
                        String commentName = accountJsonObject.optString("name");


                        Comment comment = new Comment(imageAvatarUrl, commentName, null, null, commentText, commentId);
                        comments.add(comment);

                        list.add(comment);
                    }

                }

                // Use view_count instead of share_count (share_count data is empty now)
                Post post = new Post(imageAvatarUrl, name, date, loveCount, commentCount, viewCount
                        , message, shortMessage, viewCount, imagePhotoFullUrl);
                post.setComments(comments);

                // post.setComments();


//                Post list_item = new Post();
//                list_item.setImageUrl(Avatra);
//                list_item.setName(name);
//                list_item.setDate(day);
//                list_item.setLoveCount(number1);
//                list_item.setCommentCount(number2);
//                list_item.setSherdCount(number3);
//                list_item.setMessage(sub);
//                list_item.setImage_messen(photo);
//                list_item.setView(view);
//                Log.d("Check", ImageUrl);
//
//

            }
            adapterComment.notifyDataSetChanged();
            AQUtility.debug("done");

        } else {
            AQUtility.debug("error!");
        }
    }
}
