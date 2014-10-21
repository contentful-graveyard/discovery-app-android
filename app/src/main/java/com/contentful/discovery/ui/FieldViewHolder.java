package com.contentful.discovery.ui;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.contentful.discovery.R;

/**
 * Field View Holder.
 */
public class FieldViewHolder {
  public @InjectView(R.id.tv_title) CFTextView tvTitle;
  public @InjectView(R.id.tv_value) CFTextView tvValue;
  public @InjectView(R.id.iv_arrow) ImageView ivArrow;

  public FieldViewHolder(View v) {
    ButterKnife.inject(this, v);
  }
}
