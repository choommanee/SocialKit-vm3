package co.aquario.socialkit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.aquario.socialkit.R;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoViewActivity extends Activity {

    /**
     * TODO: Set the path variable to a streaming video URL or a local media file
     * path.
     */
    private String path = "";
    private VideoView mVideoView;
    private EditText mEditText;
    private Button button1;
    private Button start;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video);
        mEditText = (EditText) findViewById(R.id.url);
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        button1 = (Button) findViewById(R.id.button1);
        start = (Button) findViewById(R.id.start);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideo("http://server-a.vdomax.com:8080/record/Nuchiko-260115_20:55:13.flv");
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideo("rtmp://150.107.31.12:1935/live/korrio");
            }
        });


        path = "rtmp://150.107.31.12:1935/live/korrio";
        if (path == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(VideoViewActivity.this, "Please edit VideoViewDemo Activity, and set path" + " variable to your media file URL/path", Toast.LENGTH_LONG).show();
            return;
        } else {
			/*
			 * Alternatively,for streaming media you can use
			 * mVideoView.setVideoURI(Uri.parse(URLstring));
			 */
            mVideoView.setVideoPath(path);
            mVideoView.setMediaController(new MediaController(this));
            mVideoView.requestFocus();

            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    // optional need Vitamio 4.0
                    mediaPlayer.setPlaybackSpeed(1.0f);
                }
            });
        }

    }

    public void startPlay() {
        String url = mEditText.getText().toString();
        path = url;
        if (!TextUtils.isEmpty(url)) {
            mVideoView.setVideoPath(url);
        }
    }

    public void openVideo(String mypath) {
        mVideoView.setVideoPath(mypath);
    }

}