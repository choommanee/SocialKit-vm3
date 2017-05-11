package co.aquario.socialkit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.liveo.model.Post;
import br.liveo.navigationviewpagerliveo.R;


public class FriendAdapter extends BaseAdapter {

    Context context;


    public ArrayList<Post> list = new ArrayList<Post>();

    public FriendAdapter(Context context, ArrayList<Post> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = mInflater.inflate(R.layout.item_friends, parent, false);
        ButtonRectangle btn;
        TextView month;
        TextView date;
        CircularImageView ImageUrl;
        Post item = list.get(position);
        month = (TextView) row.findViewById(R.id.item_title);
        btn = (ButtonRectangle) row.findViewById(R.id.btn_f);
        //date = (TextView) row.findViewById(R.id.textView2);
        ImageUrl = (CircularImageView) row.findViewById(R.id.item_img);

        month.setText(item.getName());
        //date.setText(item.getTxt_friends());

        Picasso.with(context)
                .load(item.getImageProfileUrl())
                .fit().centerCrop()
                .into(ImageUrl);

        return row;
    }
}