package discovery.contentful.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import discovery.contentful.CFApp;

public class CFPrefs {
  private CFPrefs() {
  }

  public static final String KEY_SPACE = "SPACE";
  public static final String KEY_ACCESS_TOKEN = "TOKEN";
  public static final String KEY_DID_LOGIN = "DID_LOGIN";
  public static final String KEY_FIRST_LAUNCH = "FIRST_LAUNCH";

  public static SharedPreferences getInstance() {
    return PreferenceManager.getDefaultSharedPreferences(CFApp.getInstance());
  }

  public static Boolean didLogin() {
    return getInstance().getBoolean(KEY_DID_LOGIN, false);
  }

  public static Boolean firstLaunch() {
    return getInstance().getBoolean(KEY_FIRST_LAUNCH, true);
  }
}
