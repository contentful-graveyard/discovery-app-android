package discovery.contentful.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import discovery.contentful.ui.TypefaceSpan;
import discovery.contentful.utils.Utils;

public class CFFragmentActivity extends FragmentActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    CharSequence title = getTitle();

    if (title != null) {
      setTitle(title);
    }
  }

  @Override public void setTitle(CharSequence title) {
    setTitleWithCustomFont(title);
  }

  @Override public void setTitle(int titleId) {
    setTitleWithCustomFont(getText(titleId));
  }

  private void setTitleWithCustomFont(CharSequence text) {
    SpannableString s = new SpannableString(text);

    s.setSpan(new TypefaceSpan(this, Utils.FONT_LATO_REGULAR), 0, s.length(),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    ActionBar actionBar = getActionBar();

    if (actionBar != null) {
      actionBar.setTitle(s);
    }
  }
}
