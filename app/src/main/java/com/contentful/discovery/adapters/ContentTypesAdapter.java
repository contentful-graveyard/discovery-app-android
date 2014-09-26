package com.contentful.discovery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.contentful.discovery.R;
import com.contentful.discovery.api.ContentTypeWrapper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Content Types Adapter.
 */
public class ContentTypesAdapter extends BaseAdapter {
    private final Context context;

    private ArrayList<ContentTypeWrapper> data = new ArrayList<ContentTypeWrapper>();

    public ContentTypesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ContentTypeWrapper getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.view_content_type, parent, false);

            convertView.setTag(vh = new ViewHolder(convertView));
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        ContentTypeWrapper contentTypeWrapper = getItem(position);
        convertView.setTag(R.id.tag_content_type, contentTypeWrapper);

        vh.tvName.setText(contentTypeWrapper.getContentType().getName());
        vh.tvDescription.setText(contentTypeWrapper.getContentType().getUserDescription());
        vh.tvEntriesCount.setText(context.getString(R.string.num_of_entries,
                contentTypeWrapper.getEntriesCount()));

        return convertView;
    }

    public void setData(ArrayList<ContentTypeWrapper> data) {
        this.data = data;
    }

    /**
     * View Holder
     */
    class ViewHolder {
        @InjectView(R.id.tv_name) TextView tvName;
        @InjectView(R.id.tv_description) TextView tvDescription;
        @InjectView(R.id.tv_entries_count) TextView tvEntriesCount;

        ViewHolder(View v) {
            ButterKnife.inject(this, v);
        }
    }
}
