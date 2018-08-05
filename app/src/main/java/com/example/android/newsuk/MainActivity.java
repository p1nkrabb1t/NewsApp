package com.example.android.newsuk;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {


    // URL to find news updates for July & August 2018
    private static final String DATA_SOURCE_URL =
            "https://content.guardianapis.com/search?section=news&from-date=2018-05-06&to-date" +
                    "=2018-09-02&order-by=newest&use-date=published&show-fields=byline%2Cthumbnail" +
                    "&q=uk%20AND%20NOT%20corrections&api-key=cf6b8892-08ce-42ad-b016-b194df810122";
    public TextView noDataMessage;
    public ProgressBar progress;
    /**
     * Adapter for the list of news Articles
     */
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check for network connection
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        // Create an ArrayList of news articles.
        ArrayList<Article> articlesList = new ArrayList<>();


        // Create a new {@link ArrayAdapter} of articles
        mAdapter = new NewsAdapter(this, articlesList);

        // Find a reference to the {@link ListView} in the layout
        ListView listView = (ListView) findViewById(R.id.list);

        noDataMessage = (TextView) findViewById(R.id.empty_text);
        listView.setEmptyView(noDataMessage);

        //Set progress bar to show app info is loading
        progress = (ProgressBar) findViewById(R.id.progress_bar);

        // Set the adapter on the Listview
        listView.setAdapter(mAdapter);


        // Set a click listener on the Article item
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Article currentSelection = (Article) mAdapter.getItem(position);
                Uri url = Uri.parse(currentSelection.getUrl());

                Intent i = new Intent(Intent.ACTION_VIEW, url);

                startActivity(i);
            }
        });

        if (isConnected) {
            //Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            progress.setVisibility(View.GONE);
            noDataMessage.setText(R.string.no_connection);
        }

    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle bundle) {
        return new ArticleLoader(MainActivity.this, DATA_SOURCE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articleInfo) {
        progress.setVisibility(View.GONE);
        noDataMessage.setText(R.string.no_results);
        // Clear the adapter of previous Article data
        mAdapter.clear();

        // If there Articles are present then add them to the adapter and update
        if (articleInfo != null && !articleInfo.isEmpty()) {
            mAdapter.addAll(articleInfo);
        }


    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        mAdapter.clear();

    }


}
