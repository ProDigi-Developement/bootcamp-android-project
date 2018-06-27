package crats.mvcbaseproject.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import crats.mvcbaseproject.R;
import crats.mvcbaseproject.view.MovieDetailActivity;
import crats.mvcbaseproject.view.NewsDetail;

public class NewsAdapter extends BaseAdapter {

    private final Activity context;
    private final ArrayList<News> news;
    private static LayoutInflater inflater=null;

    public NewsAdapter(Activity context, ArrayList<News> news) {

        this.context = context;
        this.news = news;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        News news;
        ImageView imageView;
        TextView title, time, overview;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final NewsAdapter.Holder holder = new NewsAdapter.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listview_movie_detail,null);
        holder.title = (TextView) rowView.findViewById(R.id.movietitleTextView);
        holder.time = (TextView) rowView.findViewById(R.id.moviereleasedateTextView);
        holder.overview = (TextView) rowView.findViewById(R.id.movieoverviewTextView);
        holder.imageView = (ImageView) rowView.findViewById(R.id.movieposterImageView);

        holder.news = news.get(position);
        holder.title.setText(holder.news.getTitle());
        holder.time.setText(holder.news.getTime());
        holder.overview.setText(holder.news.getText());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MovieDetailActivity.class);
                intent.putExtra("NewsID", holder.news.getId());
                context.startActivityForResult(intent, 2);
            }
        });
        return rowView;
    }
}
