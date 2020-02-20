package com.example.playlistyoutubeapidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<VideoYoutube> listVideos;

    public PlaylistAdapter(Context context, int layout, List<VideoYoutube> listVideos) {
        this.context = context;
        this.layout = layout;
        this.listVideos = listVideos;
    }

    @Override
    public int getCount() {
        return listVideos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        ImageView imageViewThumb;
        TextView title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            // anh xa
            holder.title = convertView.findViewById(R.id.textviewTitle);
            holder.imageViewThumb = convertView.findViewById(R.id.imageviewThumb);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        VideoYoutube videoYoutube = listVideos.get(position);

        holder.title.setText(videoYoutube.getTitle());
        // Picasso
        Picasso.with(context).load(videoYoutube.getLinkThumb()).into(holder.imageViewThumb);
        return convertView;
    }
}
