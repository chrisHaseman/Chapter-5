package com.emerjbl.followAlong;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;


import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends ListActivity{

    private TwitterAsyncTask twitterFetcher;
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.list_activity);

        ListAdapter adapter = new TwitterJSONAdapter();
        setListAdapter(adapter);

        twitterFetcher = new TwitterAsyncTask();
        twitterFetcher.execute("https://api.twitter.com/1/statuses/user_timeline.json?include_entities=false&include_rts=false&screen_name=peachpit&count=20");
    } 

    @Override
    public void onListItemClick(ListView lv, 
            View clickedView, 
            int position, long id)
    {
        super.onListItemClick(lv, clickedView, position, id);
        TextView tv = (TextView)clickedView;
        Toast.makeText(getApplicationContext(), 
                "List Item "+tv.getText()+" was clicked!", 
                Toast.LENGTH_SHORT).show();

    }

    private class TwitterAsyncTask extends AsyncTask<String, Integer, JSONArray>{

        private String getURL(String url_string) {
            try {
                URL url = new URL(url_string);
                HttpURLConnection httpCon = 
                    (HttpURLConnection)url.openConnection();
                if(httpCon.getResponseCode() != 200)
                    throw new Exception("Failed to connect");
                InputStreamReader r = new InputStreamReader(httpCon.getInputStream(), "UTF-8");
                StringBuilder response = new StringBuilder();
                char[] buf = new char[2048];
                while(true) {
                    int n = r.read(buf);
                    if(n < 0) break;
                    response.append(buf, 0, n);
                }
                return response.toString();
            } catch (Exception e) { Log.e("TwitterAsyncTask", "", e); return null; }
        }
        @Override
        protected JSONArray doInBackground(String... params) {
            try {
                String response = getURL(params[0]);
                return new JSONArray(response.toString());
            } catch (Exception e) {
                Log.e("TwitterAsyncTask", "", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONArray response){
            if(response == null)
                return;

            try{
                ((TwitterJSONAdapter)getListAdapter())
                    .setData(response);
                getListView().setVisibility(View.VISIBLE);
            }catch(Exception e){
                Log.e("TwitterFeed","Failed to set Adapter");
            }
        }
    }

    private class TwitterJSONAdapter extends BaseAdapter{
        JSONArray data;

        //Must be called on the main Thread	
        private void setData(JSONArray data){
            this.data = data;	
            this.notifyDataSetChanged();
        }
        @Override
        public int getCount() {

            if(data==null)
                return 0;
            else
                return data.length();
        }
        @Override
        public Object getItem(int position) {
            if(data==null)
                return null;
            try{
                JSONObject element = (JSONObject)data.get(position);
                return element;
            }catch(Exception e){
                return null;
            }
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            JSONObject node = (JSONObject)getItem(position);
            ViewGroup listView = null;

            //Reduce, Reuse, Recycle!
            if(convertView == null)
                listView =
                    (ViewGroup)getLayoutInflater().inflate
                    (R.layout.twitter_list_item, null);
            else
                listView = (ViewGroup)convertView;		
            try{
                boolean retweeted = node.getInt("retweet_count") > 0;
                TextView tv = 
                    (TextView)listView.findViewById(R.id.text_one);
                tv.setText(node.getString("text"));
                if(retweeted)
                    tv.setTextColor(0xFFFF0000);
                else
                    tv.setTextColor(0xFFFFFFFF);

                tv = (TextView)listView.findViewById(R.id.text_two);
                tv.setText(node.getString("created_at"));
                if(retweeted)
                    tv.setTextColor(0xFFFF0000);
                else
                    tv.setTextColor(0xFFFFFFFF);
            }catch(JSONException e){
                Log.e("TwitterView","Failed to set list item",e);
            }
            return listView;
        }
    }
}
