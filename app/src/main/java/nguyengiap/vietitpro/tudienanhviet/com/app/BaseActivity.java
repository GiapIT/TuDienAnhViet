//package nguyengiap.vietitpro.tudienanhviet.com.app;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//
//import nguyengiap.vietitpro.tudienanhviet.com.R;
//
///**
// * Created by GiapNN on 6/17/2016.
// */
//public abstract class BaseAdmob  {
//    protected abstract int getlayout();
//
//
//
//    AdView mAdView;
//
//    public abstract String getADID();
//
//
//
//
//
//
//
//    void loadAds() {
//        try {
//            AdView   mAdView = new AdView(this);
//            mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
//            mAdView.setAdSize(AdSize.SMART_BANNER);
//            final RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
//
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                    RelativeLayout.LayoutParams.MATCH_PARENT,
//                    RelativeLayout.LayoutParams.WRAP_CONTENT);
//            layout.addView(mAdView, params);
//            mAdView.setAdListener(new AdListener() {
//                @Override
//                public void onAdLoaded() {
//                    super.onAdLoaded();
//                    layout.setVisibility(RelativeLayout.VISIBLE);
//                }
//            });
//
//            if (mAdView != null) {
//                mAdView.loadAd(new AdRequest.Builder().build());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
