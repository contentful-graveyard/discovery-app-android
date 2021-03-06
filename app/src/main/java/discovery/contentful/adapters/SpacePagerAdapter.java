package discovery.contentful.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import discovery.contentful.R;
import discovery.contentful.fragments.AssetsFragment;
import discovery.contentful.fragments.ContentTypesFragment;

public class SpacePagerAdapter extends FragmentPagerAdapter {
  private final Context context;
  private static final int COUNT = 2;

  public static final int TAB_POS_CONTENT_TYPES = 0;
  public static final int TAB_POS_ASSETS = 1;

  private ContentTypesFragment contentTypesFragment;
  private AssetsFragment assetsFragment;

  public SpacePagerAdapter(Context context, FragmentManager fm) {
    super(fm);
    this.context = context;
  }

  @Override public int getCount() {
    return COUNT;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case TAB_POS_CONTENT_TYPES:
        if (contentTypesFragment == null) {
          contentTypesFragment = ContentTypesFragment.newInstance();
        }

        return contentTypesFragment;

      case TAB_POS_ASSETS:
        if (assetsFragment == null) {
          assetsFragment = AssetsFragment.newInstance();
        }

        return assetsFragment;

      default:
        throw new IllegalStateException("Invalid adapter count");
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getString(R.string.tab_content_types);

      case 1:
        return context.getString(R.string.tab_assets);

      default:
        throw new IllegalStateException("Invalid adapter count");
    }
  }
}
