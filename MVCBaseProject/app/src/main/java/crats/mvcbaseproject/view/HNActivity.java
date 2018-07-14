package crats.mvcbaseproject.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import crats.mvcbaseproject.R;

public class HNActivity extends AppCompatActivity {

    ListView listview;
    ArrayList<String> listItems;
    ArrayList<String> titles;
    URL url;
    InputStream instream = null;
    InputStreamReader inReader = null;
    BufferedReader reader;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hn);

        httpRequest();

        listItems = new ArrayList<String>();
        titles = new ArrayList<String>();
        listview = (android.widget.ListView)findViewById(R.id.listview);



        adapter = new ArrayAdapter<String>(HNActivity.this,
                android.R.layout.simple_list_item_1,
                titles);

        listview.setAdapter(adapter);



                System.out.println("before: "+ titles.size());

                


                //adapter.notifyDataSetChanged();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("lllllll"+ position);
            }
        });




    }


    public void httpRequest(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here

                try {
                // Create URL
                url = new URL("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");

                // Create connection
                HttpsURLConnection myConnection =
                        (HttpsURLConnection) url.openConnection();


                myConnection.setRequestProperty("User-Agent", "my-restful-api-v0.1");


                    if (myConnection.getResponseCode() == 200) {

                        instream = myConnection.getInputStream();
                        inReader =
                                new InputStreamReader(instream, "UTF-8");



                        reader = new BufferedReader(inReader);

                        String line;

                        while ((line = reader.readLine()) != null) {
                            JSONArray ja = new JSONArray(line);

                            for (int i = 0; i < ja.length(); i++) {

                                //JSONObject jObj = (JSONObject) ja.get(i);
                                //listItems.add(jObj.getString("text"));

                                String value = ja.getString(i);

                                getDetails(value);

                               // listItems.add(value);



                                System.out.println("mmmmmmmm"+ value);
                            }



                    }

                        instream.close();
                }




            } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }




    public void getDetails(final String val){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/"+val+".json?print=pretty");

                    // Create connection
                    HttpsURLConnection myConnection2 =
                            (HttpsURLConnection) url.openConnection();

                    if(myConnection2.getResponseCode() == 200){
                        instream = myConnection2.getInputStream();
                        inReader =
                                new InputStreamReader(instream, "UTF-8");
                        reader = new BufferedReader(inReader);
                        StringBuilder sb = new StringBuilder();
                        String line;
                        String result = null;

                        while ((line = reader.readLine()) != null) {
                            //System.out.println("line");

                            //JSONArray ja = new JSONArray(line);
                            sb.append(line + "\n");



                            //for (int i = 0; i < ja.length(); i++) {

                                //JSONObject jObj = new JSONObject(line);
                               // System.out.println("line"+ jObj.getString("title:"));
                           // }

                        }
                        instream.close();
                        result = sb.toString();
                        JSONObject jObj = new JSONObject(result);
                        String st = jObj.getString("title");

                        titles.add(st.toString());

                        System.out.println("line: "+ titles.size());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                adapter.notifyDataSetChanged();
                            }
                        });


                        //finish();
                        //startActivity(getIntent());

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution

                finish();
                Intent i = new Intent(HNActivity.this, ProjectLists.class);
                startActivity(i);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
