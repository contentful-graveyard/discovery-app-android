package com.contentful.discovery.preview;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contentful.discovery.R;
import com.contentful.discovery.ui.DisplayItem;
import com.contentful.discovery.utils.Utils;
import com.contentful.java.cda.Constants;
import com.contentful.java.cda.model.CDAAsset;
import com.contentful.java.cda.model.CDAEntry;

/**
 * Array View Factory.
 */
public class ArrayViewFactory extends PreviewViewFactory<ArrayViewHolder> {
  @Override protected int getLayoutResId() {
    return R.layout.view_preview_array;
  }

  @Override protected ArrayViewHolder createViewHolder(Object factoryKey, View v) {
    return new ArrayViewHolder(factoryKey, v);
  }

  @Override protected void setViewData(ArrayViewHolder viewHolder, DisplayItem displayItem) {
    viewHolder.tvTitle.setText(displayItem.key);

    if (Constants.CDAFieldType.Link.equals(displayItem.arrayItemType)) {
      // Link
      if (Constants.CDAResourceType.Asset.equals(displayItem.arrayLinkType)) {
        // Array of Assets
        setArrayOfAssets(viewHolder, displayItem);
      } else if (Constants.CDAResourceType.Entry.equals(displayItem.arrayLinkType)) {
        // Array of Entries
        setArrayOfEntries(viewHolder, displayItem);
      }
    } else {
      // Generic type
      setArrayOfGenericType(viewHolder, displayItem);
    }
  }

  /**
   * Array of Assets.
   */
  private void setArrayOfAssets(ArrayViewHolder viewHolder, DisplayItem displayItem) {
    for (Object node : displayItem.array) {
      // Ensure Link is resolved
      if (node instanceof CDAAsset) {
        CDAAsset asset = (CDAAsset) node;
        viewHolder.wrapper.addView(createAssetView(viewHolder.rootView.getContext(), asset));
      }
    }
  }

  /**
   * Array of Entries.
   */
  private void setArrayOfEntries(ArrayViewHolder viewHolder, DisplayItem displayItem) {
    for (Object node : displayItem.array) {
      // Ensure Link is resolved
      if (node instanceof CDAEntry) {
        viewHolder.wrapper.addView(
            createTextView(viewHolder.rootView.getContext(), ((CDAEntry) node).getSys().get("id")));
      }
    }
  }

  /**
   * Array of generic type.
   */
  private void setArrayOfGenericType(ArrayViewHolder viewHolder, DisplayItem displayItem) {
    for (Object node : displayItem.array) {
      viewHolder.wrapper.addView(createTextView(viewHolder.rootView.getContext(), node));
    }
  }

  /**
   * Create view for Asset.
   */
  private View createAssetView(Context context, CDAAsset asset) {
    ImageView imageView = new ImageView(context);

    int size = context.getResources().getDimensionPixelSize(R.dimen.entry_preview_image_size);
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(size, size);
    lp.gravity = Gravity.CENTER_HORIZONTAL;
    lp.bottomMargin = context.getResources().getDimensionPixelSize(R.dimen.entry_preview_padding);
    imageView.setLayoutParams(lp);
    Utils.loadThumbnailForAssetWithSize(context, asset, imageView, size, size, true);

    return imageView;
  }

  /**
   * Create view for text.
   */
  private View createTextView(Context context, Object node) {
    TextView textView = new TextView(context);
    textView.setText(node.toString());

    textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(),
        textView.getPaddingRight(),
        context.getResources().getDimensionPixelSize(R.dimen.entry_preview_padding));

    return textView;
  }

  @Override protected int getItemViewType() {
    return EntryPreviewAdapter.VIEW_TYPE_ARRAY;
  }

  @Override public void reset(ArrayViewHolder viewHolder) {
    for (int i = viewHolder.wrapper.getChildCount() - 1; i > 0; i--) {
      View child = viewHolder.wrapper.getChildAt(i);

      if (child != null) {
        viewHolder.wrapper.removeView(child);
      }
    }
  }
}
