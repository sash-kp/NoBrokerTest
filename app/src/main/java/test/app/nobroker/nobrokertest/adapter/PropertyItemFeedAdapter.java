package test.app.nobroker.nobrokertest.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.app.nobroker.nobrokertest.R;
import test.app.nobroker.nobrokertest.model.FeedIPropertyItem;

/**
 * Created by HP on 12-06-2017.
 */

public class PropertyItemFeedAdapter extends BaseAdapter {
    private AppCompatActivity context;
    private LayoutInflater inflater;
    private List<FeedIPropertyItem> feedItems;


    public PropertyItemFeedAdapter(AppCompatActivity context, List<FeedIPropertyItem> feedItems) {
        this.context = context;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        feedItems.clear();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.property_list_item, null);


        ImageView feedPropertyImage = (ImageView) view.findViewById(R.id.iv_property_image);

        final FeedIPropertyItem item = feedItems.get(position);

        TextView propertyTitle = (TextView) view.findViewById(R.id.tv_property_name);
        TextView propertyAddress = (TextView) view.findViewById(R.id.tv_property_address);
        TextView propertyPrice = (TextView) view.findViewById(R.id.tv_property_price);
        TextView propertyDesc = (TextView) view.findViewById(R.id.tv_property_desc);
        TextView numOfBathrooms = (TextView) view.findViewById(R.id.tv_num_of_bathrooms);
        TextView propertyArea = (TextView) view.findViewById(R.id.tv_property_area);

//        TextView propertyAddress = (TextView) view.findViewById(R.id.tv_property_address);
//        ImageView removeButton = (ImageView) view.findViewById(R.id.button_remove_from_cart);
        propertyTitle.setText(item.getPropertyName());
        propertyAddress.setText(item.getPropertyAddress());
        propertyPrice.setText(" ₹ " + item.getPropertyRent());
        propertyDesc.setText(item.getPropertyFurnishing() + " Furnished");
        numOfBathrooms.setText(item.getNumberOfBathrooms() + " Bathrooms");
        propertyArea.setText(item.getPropertySize() + " Sq. ft");

        Picasso.with(context)
                .load(item.getPropertyImage())
                .placeholder(R.drawable.placeholder)
                .into(feedPropertyImage);


        return view;
    }


}
