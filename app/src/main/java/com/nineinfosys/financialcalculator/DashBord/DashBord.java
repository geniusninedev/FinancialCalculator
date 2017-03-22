package com.nineinfosys.financialcalculator.DashBord;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nineinfosys.financialcalculator.MainActivityDrawer;
import com.nineinfosys.financialcalculator.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Dev on 12-01-2017.
 */

public class DashBord extends Fragment {
    public NewsAdapter newsAdapter;
    private ListView listViewNews;
    private ArrayList<String> headlines;
    private ArrayList<String> links;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dashbord, null);

     /*   MobileAds.initialize(getActivity(), getString(R.string.ads_app_id));
        AdView mAdView = (AdView) v.findViewById(R.id.adViewMainPageNews);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

        ((MainActivityDrawer) getActivity()).toolbar.setTitle("Feeds");

        doSomething();
        newsAdapter = new NewsAdapter(getActivity(), R.layout.row_list_category );
        listViewNews = (ListView)v.findViewById(R.id.list);
        listViewNews.setAdapter(newsAdapter);
        listViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = links.get(position);
                Intent news = new Intent(getActivity(), ShowFeeds.class);
                news.putExtra("url", item) ;
                startActivity(news);
            }
        });


        return v;
    }

    private AsyncTask<Void, Void, Void> doSomething(){
        headlines = new ArrayList<>();
        links = new ArrayList<>();

        Log.e("doSomething", "Parsing");

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try{

                    try {
                        URL url = new URL("http://www.womenshealthandfitness.com.au/component/obrss/weight-loss");
                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        factory.setNamespaceAware(false);
                        final XmlPullParser xpp = factory.newPullParser();
                        xpp.setInput(getInputStream(url), "UTF_8");
                        boolean insideItem = false;
                        int eventType = xpp.getEventType();
                        while(eventType!= XmlPullParser.END_DOCUMENT){

                            if (eventType == XmlPullParser.START_TAG) {
                                if (xpp.getName().equalsIgnoreCase("item")) {
                                    insideItem = true;
                                } else if (xpp.getName().equalsIgnoreCase("title")) {
                                    if (insideItem){
                                        try {
                                            final String headline=(xpp.nextText()); ////////////
                                            Log.e("Headlines ", headline);
                                            headlines.add(headline);
                                            try{
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        newsAdapter.add(headline);

                                                    }
                                                });
                                            }catch (Exception e){
                                                Log.e("TAG2 ", e.toString());
                                            }


                                        }catch(Exception e){
                                            Log.e("ERROR_PARSER", e.toString());
                                        }



                                    }
                                    //extract the headline
                                } else if (xpp.getName().equalsIgnoreCase("link")) {
                                    if (insideItem){
                                        try {
                                            final String link=(xpp.nextText());
                                            Log.e("Link ", link);
                                            links.add(link);
                                        }catch(Exception e){
                                            Log.e("ERROR_PARSER", e.toString());
                                        }
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {


                                            }
                                        });
                                    }

                                }
                            }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                                insideItem=false;
                            }
                            eventType = xpp.next();
                        }



                    }catch (Exception e){
                        Log.e("ERROR_PARSER", e.toString());
                    }


                }catch (Exception e){
                    Log.e("getFeedsOnline ", e.toString());
                }
                return null;
            }
        };
        return runAsyncTask(task);
    }

    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }
    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            Log.e("doSomething.. ", e.toString());
            return null;
        }
    }



}