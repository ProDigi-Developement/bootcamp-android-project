package crats.mvcbaseproject.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import crats.mvcbaseproject.R;
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
}
