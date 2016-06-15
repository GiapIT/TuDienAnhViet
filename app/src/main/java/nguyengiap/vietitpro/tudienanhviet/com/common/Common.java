package nguyengiap.vietitpro.tudienanhviet.com.common;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.app.Application;
import nguyengiap.vietitpro.tudienanhviet.com.model.TranslateEntity;


/**
 * Created by hanhphuckhicoem on 06/08/2015.
 */
public class Common {


    public static void goHome(Context cont) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        cont.startActivity(startMain);
    }

    //kiem tra xem pachage da ton tai hay chua
    public static boolean isPackageInstalled(String packagename,
                                             Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    //// link đến các ứng dụng học tiếng anh
    public static void AppEnglish(Activity activity, String packageName) {
        String urlRate = "https://play.google.com/store/apps/details?id=";
        showDetail(activity, urlRate + packageName);

    }
    /*
 * Các ký tự unicode
 */
    static Pattern a = Pattern.compile("[àáãạảăằắẵặẳâầấẫậẩ]");
    static Pattern d = Pattern.compile("[đ]");
    static Pattern e = Pattern.compile("[èéẽẹẻêềếễệể]");
    static Pattern i = Pattern.compile("[ìíĩịỉ]");
    static Pattern o = Pattern.compile("[òóõọỏôồốỗộổơờớỡợở]");
    static Pattern u = Pattern.compile("[ùúũụủưừứữựử]");
    static Pattern y = Pattern.compile("[ỳýỹỵỷ]");

    public static String replaceUnicode(String string) {
        String result = null;
        if (string != null) {
            result = string;
            result = a.matcher(result).replaceAll("a");
            result = d.matcher(result).replaceAll("d");
            result = e.matcher(result).replaceAll("e");
            result = u.matcher(result).replaceAll("u");
            result = o.matcher(result).replaceAll("o");
            result = i.matcher(result).replaceAll("i");
            result = y.matcher(result).replaceAll("y");
        }

        return result;
    }
    public static void showDetail(Activity activity, String appURL) {
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appURL)));
    }

    //
    public static boolean isNetworkAvailable() {

        ConnectivityManager connectivity = (ConnectivityManager) Application
                .getHKApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //// chia se facebook
    public static void onShareFull(Activity activity) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String urlToShare = "https://play.google.com/store/apps/details?id=" + activity.getPackageName();

        intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

        boolean facebookAppFound = false;
        List<ResolveInfo> matches = activity.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }

        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        activity.startActivity(intent);

    }

    /* //send freeback
     public static void sendFeedBack(Activity activity) {
         try {
             Intent sendFeedBack = new Intent(Intent.ACTION_SEND);
             sendFeedBack.setType("text/plain");
             PackageManager pm = activity.getPackageManager();
             List<ResolveInfo> activityList = pm.queryIntentActivities(sendFeedBack, 0);

             PackageInfo pInfo = null;
             try {
                 pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
             } catch (PackageManager.NameNotFoundException e) {
                 e.printStackTrace();
             }


             String version = pInfo.versionName;

             for (final ResolveInfo app : activityList) {
                 if ((app.activityInfo.name).contains("android.gm")) {
                     final ComponentName name = new ComponentName(app.activityInfo.applicationInfo.packageName, app.activityInfo.name);
                     sendFeedBack.putExtra(Intent.EXTRA_EMAIL, new String[]{"phamkeit@gmail.com"});
                   *//*  String strSubject = activity.getString(R.string.MailTitle);
                    strSubject = String.format(strSubject, version);
*//*

                    String strSubject = activity.getString(R.string.MailTitle);


                    DisplayMetrics displayMetrics = HKApplication.getHKApplicationContext().getResources()
                            .getDisplayMetrics();

                    int screenWidthInPix = displayMetrics.widthPixels;

                    int screenheightInPix = displayMetrics.heightPixels;
                    Log.w("width: ", " " + screenWidthInPix);
                    Log.w("height: ", " " + screenheightInPix);
                    String details = "\n Model: " + Build.MODEL + "\n Version: " + Build.VERSION.RELEASE + "\n Screen: " + screenWidthInPix + "x" + screenheightInPix;


                    strSubject = String.format(strSubject + details, version);


                    sendFeedBack.putExtra(Intent.EXTRA_SUBJECT, strSubject);
                    sendFeedBack.setComponent(name);
                    activity.startActivity(sendFeedBack);
                    break;
                }
            }
        } catch (Exception e) {
            Common.ShowNotifation(activity, activity.getString(R.string.error));
        } finally {
        }

    }
*/
    /*
       * Alert
       */
    public static void ShowNotifation(Context con, String strMessage) {

		/*
         * LayoutInflater inflater = ((Activity) con).getLayoutInflater(); View
		 * layout = inflater.inflate(R.layout.toast_layout, null);
		 */

        Toast toast = Toast.makeText(con, strMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
        // toast.setView(layout);
        toast.show();
        // Toast.makeText(con, strMessage, Toast.LENGTH_SHORT).show();
    }

    ///Rate
    private void showDetail(String appURL) {
        Application.getHKApplicationContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appURL)));
    }

    public static void showToast(String message) {
        Toast.makeText(Application.getHKApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showApp(Activity activity, String pakageName) {
        String urlRate = "https://play.google.com/store/apps/details?id=";
        showDetail(activity, urlRate + pakageName);
    }

    /*  // translator
      public static String UpdateTranslate(String strValue) {

          String strMeaning = null;
          try {
              Translate.setClientId("MicrosoftTranslatorJavaAPI");
              Translate.setClientSecret("0VHbhXQnJrZ7OwVqcoX/PDZlyLJS9co3cVev1TPr8iM=");
              strMeaning = Translate.execute(strValue, Language.ENGLISH, Language.VIETNAMESE);
          } catch (Exception e) {
              e.printStackTrace();
          }

          return strMeaning;
      }
  */
    ////  hien thi footer khi cac may k co phim cung
    public static Point getNavigationBarSize(Context context) {
        Point appUsableSize = getAppUsableScreenSize(context);
        Point realScreenSize = getRealScreenSize(context);

        // navigation bar on the right
        if (appUsableSize.x < realScreenSize.x) {
            return new Point(realScreenSize.x - appUsableSize.x, appUsableSize.y);
        }

        // navigation bar at the bottom
        if (appUsableSize.y < realScreenSize.y) {
            return new Point(appUsableSize.x, realScreenSize.y - appUsableSize.y);
        }

        // navigation bar is not present
        return new Point();
    }

    public static Point getAppUsableScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size;
        if (Build.VERSION.SDK_INT >= 14) {
            size = new Point();
            display.getSize(size);
        } else {
            size = new Point(display.getWidth(), display.getHeight());
        }

        return size;
    }

    public static Point getRealScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            }
        }

        return size;
    }

    public static String randomstring(int len) {
        String key = "=aGorPSqT5tw6BsIGnEkSBu4bqhYiBhxaa21rD=";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(key.charAt(rnd.nextInt(key.length())));
        return sb.toString();

    }

    //------------------
    public static String DecodeBase64(String strName) {
        try {
            if (strName != null && strName.trim().length() > 0) {

                return new String(Base64.decode(strName.getBytes(), 0));
            }
            return "";

        } catch (Exception e) {
            return "";
        }

    }
    public static String EncodeBase64(String strName) {
        // encoding byte array into base 64
        if (strName != null && strName.trim().length() > 0) {

            return new String(Base64.encodeToString(strName.getBytes(), 0));
        }

        return "";
    }
//
//    public static String encode(String str) {
//        String content = "";
//        StringBuilder bd = new StringBuilder(str);
//        bd = bd.reverse();
//        try {
//            content = AESCrypt.encrypt(HKCache.KEY, bd.toString().trim());
//        } catch (GeneralSecurityException e1) {
//            e1.printStackTrace();
//        }
//        return content;
//    }
//
//    public static String decode(String str) {
//        String content="";
//        try {
//            content  =AESCrypt.decrypt(HKCache.KeyS(),str);
//        } catch (GeneralSecurityException e1) {
//            e1.printStackTrace();
//        }
//        StringBuilder db = new StringBuilder(content);
//        db.reverse();
//
//        return db.toString().trim();
//    }
    public static String decodeTranslator(String str) {
        /*str = str.substring(6, str.length() - 8);
        str = Common.DecodeBase64(str);*/
        str = str.substring(2, str.length()-5);
        str = DecodeBase64(str);
        return str;
    }
    //------------------
    public static String encodeQS(String str) {
        StringBuilder bd = new StringBuilder(str);
        bd = bd.reverse();
        return Common.randomstring(1) + Common.EncodeBase64(bd.toString()) + Common.randomstring(12);
    }

    public static String decodeQS(String str) {
        str = str.substring(1, str.length() - 1);
        str = Common.DecodeBase64(str);

        return str;
    }
    ////

    public static String encodehtml(String str) {
        StringBuilder bd = new StringBuilder(str);
        bd = bd.reverse();
        return Common.randomstring(3) + Common.EncodeBase64(bd.toString()) + Common.randomstring(5);
    }

    public static String decodehtml(String str) {
        str = str.substring(3, str.length() - 5);
        str = Common.DecodeBase64(str);
        StringBuilder db = new StringBuilder(str);
        db.reverse();
        return db.toString();
    }
    public static TranslateEntity randomClientId() {
        Random random = new Random();
        TranslateEntity item = (TranslateEntity) getObjectTranslator().get(random.nextInt(getObjectTranslator().size()));
        return item;
    }

    public static ArrayList getObjectTranslator() {

        final Resources res = Application.getHKApplicationContext().getResources();
        String[] xmlList = res.getStringArray(R.array.Mcf);
        TranslateEntity item;
        ArrayList list = new ArrayList();
        for (int i = 0; i < xmlList.length; i++) {
            String[] arr = xmlList[i].split(",");
            item = new TranslateEntity(i, decodeTranslator(arr[0]), arr[1]);
            list.add(item);
        }
        return list;
    }
    public static String UpdateTranslate(String strValue, int id) {

        String strMeaning = null;
        try {
            TranslateEntity item = randomClientId();
            Translate.setClientId(item.getClientId());
            Translate.setClientSecret(item.getClientSecret());
            if (id==0) {
                strMeaning = Translate.execute(strValue, Language.ENGLISH, Language.VIETNAMESE);
            }else {
                strMeaning = Translate.execute(strValue, Language.VIETNAMESE, Language.ENGLISH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strMeaning;
    }



    //send freeback
    public static void sendFeedBack(Activity activity) {
        try {
            Intent sendFeedBack = new Intent(Intent.ACTION_SEND);
            sendFeedBack.setType("text/plain");
            PackageManager pm = activity.getPackageManager();
            List<ResolveInfo> activityList = pm.queryIntentActivities(sendFeedBack, 0);

            PackageInfo pInfo = null;
            try {
                pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }


            String version = pInfo.versionName;

            for (final ResolveInfo app : activityList) {
                if ((app.activityInfo.name).contains("android.gm")) {
                    final ComponentName name = new ComponentName(app.activityInfo.applicationInfo.packageName, app.activityInfo.name);
                    sendFeedBack.putExtra(Intent.EXTRA_EMAIL, new String[]{"phamkeit@gmail.com"});

                    String strSubject = activity.getString(R.string.MailTitle);


                    DisplayMetrics displayMetrics = Application.getHKApplicationContext().getResources()
                            .getDisplayMetrics();

                    int screenWidthInPix = displayMetrics.widthPixels;

                    int screenheightInPix = displayMetrics.heightPixels;
                    Log.w("width: ", " " + screenWidthInPix);
                    Log.w("height: ", " " + screenheightInPix);
                    String details = "\n Model: " + Build.MODEL + "\n Version: " + Build.VERSION.RELEASE + "\n Screen: " + screenWidthInPix + "x" + screenheightInPix;


                    strSubject = String.format(strSubject + details, version);


                    sendFeedBack.putExtra(Intent.EXTRA_SUBJECT, strSubject);
                    sendFeedBack.setComponent(name);
                    activity.startActivity(sendFeedBack);
                    break;
                }
            }
        } catch (Exception e) {
            Common.ShowNotifation(activity, activity.getString(R.string.error));
        } finally {
        }

    }
    public static void hideSoftInputFromWindow(Activity activity) {
//        InputMethodManager imm = (InputMethodManager) v.getContext()
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showSoftInputFromWindow(EditText v) {
        InputMethodManager imm = (InputMethodManager) v.getContext()
                .getSystemService((Context.INPUT_METHOD_SERVICE));
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);

    }

    public static void shareFacebook(Context _context) {
        if (_context != null) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id="
                            + _context.getPackageName());

            PackageManager pm = _context.getPackageManager();
            List<ResolveInfo> activityList = pm.queryIntentActivities(
                    shareIntent, 0);
            boolean isGotoFacebook = false;
            for (final ResolveInfo app : activityList) {

                if (app.activityInfo.name.contains("com.facebook.composer")) {
                    isGotoFacebook = true;
                    final ActivityInfo activity = app.activityInfo;
                    final ComponentName name = new ComponentName(
                            activity.applicationInfo.packageName, activity.name);
                    shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    shareIntent.setComponent(name);
                    _context.startActivity(shareIntent);
                    break;
                }
            }
            if (!isGotoFacebook) {
                for (final ResolveInfo app : activityList) {
                    if (app.activityInfo.name.contains("facebook")) {
                        isGotoFacebook = true;
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(
                                activity.applicationInfo.packageName,
                                activity.name);
                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        shareIntent.setComponent(name);
                        _context.startActivity(shareIntent);
                        break;
                    }
                }
            }
        }
    }

    public static void createShareFolder() {
        File direct = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/.com.vietitpro.nguyengiap.tudientienganh");
        if (!direct.exists()) {
            direct.mkdirs();
        }
    }
    ///
    public static Boolean isExistData() {
        File direct = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/.com.vietitpro.nguyengiap.tudientienganh/data.sqlite");
        if (direct.exists()) {
            return true;
        } else {
            return false;
        }

    }
}
