package test.app.nobroker.nobrokertest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import test.app.nobroker.nobrokertest.R;
import test.app.nobroker.nobrokertest.adapter.PropertyItemFeedAdapter;
import test.app.nobroker.nobrokertest.model.FeedIPropertyItem;
import test.app.nobroker.nobrokertest.util.AppController;
import test.app.nobroker.nobrokertest.util.Constants;

import static test.app.nobroker.nobrokertest.util.Constants.BASE_URL_FOR_IMAGES;
import static test.app.nobroker.nobrokertest.util.Constants.TIMEOUT_MS;
import static test.app.nobroker.nobrokertest.util.Constants.URL_TO_FETCH_PROPERTY_ITEMS;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<FeedIPropertyItem> feedItems;
    private PropertyItemFeedAdapter listAdapter;
    private int pageNumber;
    public static int REQUEST_CODE_FILTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Properties near me");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FILTER);
            }
        });
        pageNumber = 1;
        listView = (ListView) findViewById(R.id.lv_property);
        feedItems = new ArrayList<FeedIPropertyItem>();
        listAdapter = new PropertyItemFeedAdapter(this, feedItems);
        fetchPropertiesData(Constants.URL_TO_FETCH_PROPERTY_ITEMS + pageNumber);
        listView.setAdapter(listAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (visibleItemCount % 21 == 0) {
                    fetchPropertiesData(URL_TO_FETCH_PROPERTY_ITEMS + pageNumber++);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FILTER && resultCode == Activity.RESULT_OK) {
            String returnValue;
            pageNumber = 1;
            listAdapter.clear();
            if (data.getStringExtra("type") != null) {
                returnValue = data.getStringExtra("type");
                Log.d("ReturnVal: ",URL_TO_FETCH_PROPERTY_ITEMS + pageNumber + "&type=" + returnValue);
                fetchPropertiesData(URL_TO_FETCH_PROPERTY_ITEMS + pageNumber + "&type=" + returnValue);
            } else if (data.getStringExtra("buildingType") != null) {
                returnValue = data.getStringExtra("buildingType");
                fetchPropertiesData(URL_TO_FETCH_PROPERTY_ITEMS + pageNumber + "&buildingType=" + returnValue);
            } else if (data.getStringExtra("furnishing") != null) {
                returnValue = data.getStringExtra("furnishing");
                fetchPropertiesData(URL_TO_FETCH_PROPERTY_ITEMS + pageNumber + "&furnishing=" + returnValue);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchPropertiesData(String urlToFetchPropertyItems) {
// making fresh volley request and getting json
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                urlToFetchPropertyItems, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //Log.d("Response", response.toString());
                parseJsonFeed(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {

                }
            }
        }) {
            //Set a retry policy in case of SocketTimeout & ConnectionTimeout Exceptions.
            //Volley does retry for you if you have specified the policy.
            @Override
            public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
                retryPolicy = new DefaultRetryPolicy(
                        TIMEOUT_MS,
                        2,
                        2.0F);
                return super.setRetryPolicy(retryPolicy);
            }
        };
        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(jsonReq);
    }

    private void parseJsonFeed(JSONObject response) {

        try {
            JSONArray feedArray = response.getJSONArray("data");
            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                FeedIPropertyItem item = new FeedIPropertyItem();
                item.setPropertyId(feedObj.getString("id"));
                item.setPropertyName(feedObj.getString("propertyTitle"));
                item.setPropertyAddress(feedObj.getString("secondaryTitle"));
                JSONArray photosArray = feedObj.getJSONArray("photos");
                if (!feedObj.isNull("photoAvailable") && feedObj.getString("photoAvailable") != null && photosArray.length() != 0) {
                    for (int j = 0; j < 1; j++) {
                        JSONObject photoObj = (JSONObject) photosArray.get(j);
                        JSONObject imagesMap = photoObj.getJSONObject("imagesMap");
                        item.setPropertyImage(BASE_URL_FOR_IMAGES + item.getPropertyId() + "/" + imagesMap.getString("original"));
                    }
                } else {
                    item.setPropertyImage(null);
                }

                Log.d("PhotoUrl: ", item.getPropertyImage() + "");

                item.setNumberOfBathrooms(feedObj.getInt("bathroom"));
                item.setPropertyFurnishing(feedObj.getString("furnishingDesc"));
                item.setPropertyRent(feedObj.getInt("rent"));
                item.setPropertySize(feedObj.getInt("propertySize"));


                feedItems.add(item);
            }
            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
