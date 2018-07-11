package crats.mvcbaseproject.controller;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import crats.mvcbaseproject.model.Movie;
import crats.mvcbaseproject.model.News;
import crats.mvcbaseproject.model.Person;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class NewsController implements INewsApi {
    private static NewsController instance = null;
    private ArrayList<News> newsList = new ArrayList<>();

    private INewsApi iNewsApi = null;
    private INewsController iNewsController = null;

    private RequestQueue requestQueue = null;

    private NewsController() {

    }

    @Override
    public void fetchSuccess(ArrayList<News> list) {

    }

    @Override
    public void fetchFailure(String errorMessage) {

    }
}
