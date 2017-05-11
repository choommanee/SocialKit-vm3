package co.aquario.socialkit.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.liveo.model.Video;
import br.liveo.navigationviewpagerliveo.R;
import br.liveo.widget.RoundedTransformation;


public class VideoAdapter extends BaseAdapter implements AdapterView.OnClickListener {

    Context context;
    OnItemClickListener mItemClickListener;

    public ArrayList<Video> list = new ArrayList<Video>();

    public VideoAdapter(Context context, ArrayList<Video> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        LayoutInflater mInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = mInflater.inflate(R.layout.item_videos, parent, false);
        TextView title;
        TextView detail;

        ImageView avatar;
        TextView profileName;

        ImageView videoThumb;
        TextView videoTitle;
        TextView videoDesc;

        TextView view;

        Video item = list.get(position);
        videoTitle = (TextView) row.findViewById(R.id.video_title);
        videoDesc = (TextView) row.findViewById(R.id.video_desc);
        videoThumb = (ImageView) row.findViewById(R.id.video_thumb);
        view = (TextView) row.findViewById(R.id.view);

        avatar = (ImageView) row.findViewById(R.id.avatar);
        profileName = (TextView) row.findViewById(R.id.profile_name);

        videoTitle.setText(item.getTitle());
        videoDesc.setText(Html.fromHtml(item.getDesc().substring(0,60)));
        view.setText(Html.fromHtml("<b>" + item.getView() + "+ views</b>"));

        profileName.setText(item.getpName());


        //http://img.youtube.com/vi/QPjgMkx5KyY/0.jpg
        //http://img.youtube.com/vi/QPjgMkx5KyY/maxresdefault
        Picasso.with(context)
                .load("http://img.youtube.com/vi/" + item.getYoutubeId() + "/0.jpg")
                .into(videoThumb);

        Picasso.with(context)
                .load(item.getpAvatar())
                .transform(new RoundedTransformation(50, 4))
                .resize(100, 100)
                .into(avatar);

        videoThumb.setOnClickListener(this);


        return row;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.image_title:
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(view);
                }
                break;

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }




}

