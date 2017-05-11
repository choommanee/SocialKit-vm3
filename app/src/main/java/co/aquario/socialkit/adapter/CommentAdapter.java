package co.aquario.socialkit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.liveo.model.Comment;
import br.liveo.navigationviewpagerliveo.R;
import br.liveo.widget.RoundedTransformation;


public   class CommentAdapter extends RecyclerView.Adapter<AdapterComment.ContactViewHolder>{
    public static  Context context;
    public static ArrayList<Comment> list = new ArrayList<Comment>();
    public CommentAdapter(Context context, ArrayList<Comment> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comment, parent, false);



        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Comment item = list.get(position);

        holder.name.setText(item.getName());

        holder.txtComment.setText(Html.fromHtml("<strong><em>" + item.getComment() + "</em></strong>"));
        holder.time.setText(item.getLoveCount());
        holder.loveCount.setText(item.getLoveCount());

        Picasso.with(context)
                .load(item.getTitleImage())
                .centerCrop()
                .resize(100, 100)
                .transform(new RoundedTransformation(40, 4))
                .into(holder.imageProfileUrl);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        TextView name;
        TextView txtComment;
        TextView time;
        TextView loveCount;


        ImageView imageProfileUrl;


        public ContactViewHolder(View v) {
            super(v);
//            vName =  (TextView) v.findViewById(R.id.txtName);
//            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
//            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
//            vTitle = (TextView) v.findViewById(R.id.title);


            name = (TextView) v.findViewById(R.id.txt_profile);
            txtComment = (TextView) v.findViewById(R.id.txt_comment);
            time = (TextView) v.findViewById(R.id.txt_time);
            loveCount = (TextView) v.findViewById(R.id.txt_love_count);

            // view = (TextView) v.findViewById(R.id.view);
            imageProfileUrl = (ImageView) v.findViewById(R.id.image_profile);


        }

        @Override
        public void onClick(View v) {

        }
    }

}
