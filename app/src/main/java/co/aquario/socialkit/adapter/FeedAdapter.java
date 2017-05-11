package co.aquario.socialkit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.aquario.socialkit.R;
import co.aquario.socialkit.model.PostStory;
import co.aquario.socialkit.widget.RoundedTransformation;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private ArrayList<PostStory> list = new ArrayList<>();
    private static Context context;

    private OnItemClickListener mItemClickListener;
    private OnItemClickListener mItemLove;
    private OnItemClickListener mItemShare;

    public FeedAdapter(Context context, ArrayList<PostStory> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("onCreateViewHolder","onCreateViewHolder");
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostStory item = list.get(position);

        //Log.e("onBindViewHolder",item.getText());

        holder.month.setText(item.getAuthor().name);

        holder.date.setText(item.getTimestamp());
        holder.nLove.setText(item.getLoveCount() + "");
        holder.nComment.setText(item.getCommentCount() + "");
        holder.nShare.setText(item.getShareCount() + "");

        if(item.getText() != null) {
            if(item.getText().trim().toString().length() < 200)
                holder.msg.setText(Html.fromHtml("<strong><em>" + item.getText() + "</em></strong>"));
            else
                holder.msg.setText(Html.fromHtml("<strong><em>" + item.getText().substring(0,200) + " ..." + "</em></strong>"));
        } else {
            holder.msg.setVisibility(View.GONE);
        }



        Picasso.with(context)
                .load(item.getAuthor().getAvatarPath())
                .centerCrop()
                .resize(100, 100)
                .transform(new RoundedTransformation(50, 4))
                .into(holder.avatar);

        Picasso.with(context)
                .load(item.getMedia().getThumbUrl())
                .fit().centerCrop()
                .into(holder.thumb);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView month;
        TextView date;
        TextView nLove;
        TextView nComment;
        TextView nShare;
        TextView msg;
        ImageView avatar;
        ImageView thumb;

        ButtonFlat btnLove;
        ButtonFlat btnComment;
        ButtonFlat btnShare;

        public ViewHolder(View view) {
            super(view);
            month = (TextView) view.findViewById(R.id.Aung);
            date = (TextView) view.findViewById(R.id.day);
            nLove = (TextView) view.findViewById(R.id.number1);
            nComment = (TextView) view.findViewById(R.id.number2);
            nShare = (TextView) view.findViewById(R.id.number3);
            msg = (TextView) view.findViewById(R.id.messng);
            avatar = (ImageView) view.findViewById(R.id.imageView);
            thumb = (ImageView) view.findViewById(R.id.image_center);

            btnComment = (ButtonFlat) view.findViewById(R.id.btn_comment);
            btnLove = (ButtonFlat) view.findViewById(R.id.btn_love);
            btnShare = (ButtonFlat) view.findViewById(R.id.btn_share);

            thumb.setOnClickListener(this);
            avatar.setOnClickListener(this);
            btnComment.setOnClickListener(this);
            btnLove.setOnClickListener(this);
            btnShare.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.image_center:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                    break;
                case R.id.btn_comment:

                   // Intent i = new Intent(context, ActivityComment.class);
                   // context.startActivity(i);
                    break;
                case R.id.imageView:
                    //Intent intent = new Intent(context, MainProfileFriends.class);
                    //context.startActivity(intent);
                    break;
                case R.id.btn_love:
                    if (mItemLove != null) {
                        mItemLove.onItemClick(v, getPosition());
                    }
                    break;
                case R.id.btn_share:
                    if (mItemShare != null) {
                        mItemShare.onItemClick(v, getPosition());
                    }
                    break;


            }
        }

    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemLoveClick {
        public void onItemClick(View view, int position);
    }
    public void OnItemLoveClick(final OnItemClickListener mItemLove) {
        this.mItemLove = mItemLove;
    }


    public interface OnItemShareClick {
        public void onItemClick(View view, int position);
    }
    public void OnItemShareClick(final OnItemClickListener mItemShare) {
        this.mItemShare = mItemShare;
    }
}