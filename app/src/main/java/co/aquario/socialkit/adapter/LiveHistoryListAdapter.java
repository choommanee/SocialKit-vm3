package co.aquario.socialkit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import wseemann.media.fmpdemo.R;

final class LiveHistoryListAdapter extends BaseAdapter {
	private final Context context;
	// private final List<String> urls = new ArrayList<String>();
	private List<Artist> artistList = new ArrayList<Artist>();

	public LiveHistoryListAdapter(Context context, List<Artist> artistList) {
		this.context = context;
		this.artistList = artistList;

		// Collections.addAll(urls, Data.URLS);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.sample_list_detail_item, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) view.findViewById(R.id.photo);
			holder.text = (TextView) view.findViewById(R.id.name);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		String name = artistList.get(position).getName();
		holder.text.setText(name);

		// Get the image URL for the current position.
		String url = artistList.get(position).getCover();

		// Trigger the download of the URL asynchronously into the image view.
		Picasso.with(context)
				.load(url)
				.placeholder(R.drawable.placeholder)
				.error(R.drawable.error)
				.resizeDimen(R.dimen.list_detail_image_size,
						R.dimen.list_detail_image_size).centerInside()
				.into(holder.image);

		return view;
	}

	@Override
	public int getCount() {
		return artistList.size();
	}

	@Override
	public String getItem(int position) {
		return artistList.get(position).getCover();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		ImageView image;
		TextView text;
	}
}
