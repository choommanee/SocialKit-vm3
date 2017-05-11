package co.aquario.socialkit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;
import co.aquario.socialkit.R;
import co.aquario.socialkit.model.Live;


public class LiveHistoryRecyclerAdapter extends RecyclerView.Adapter<LiveHistoryRecyclerAdapter.ViewHolder> {


    ArrayList<Live> list = new ArrayList<Live>();
    public static Context context;

    OnItemClickListener mItemClickListener;


    public LiveHistoryRecyclerAdapter(Context context, ArrayList<Live> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.cardview_live_history, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Live item = list.get(position);

        holder.title_live_name.setText(item.getNameLive());
        holder.title_detail_name.setText(item.getMinutes());
        holder.text_time_name.setText(item.getHours());
        holder.text_duration_name.setText(item.getHours());



        Picasso.with(context)
                .load(item.getPhotoLive())
                .fit().centerCrop()
                .into(holder.image_live);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView title_live_name;
        TextView title_detail_name;
        TextView text_time_name;
        TextView text_duration_name;

        ImageView image_live;

        public ViewHolder(View view) {
            super(view);
            title_live_name = (TextView) view.findViewById(R.id.title_live_name);
            title_detail_name = (TextView) view.findViewById(R.id.title_detail_name);
            text_time_name = (TextView) view.findViewById(R.id.text_time_name);
            text_duration_name = (TextView) view.findViewById(R.id.text_duration_name);

            image_live = (ImageView) view.findViewById(R.id.image_live);





            image_live.setOnClickListener(this);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.image_center:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }

            }
        }

    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    /*
     * Snippet from http://stackoverflow.com/a/363692/1008278
     */
    public static int randInt(int min, int max) {
        final Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /* ==========This Part is not necessary========= */

}