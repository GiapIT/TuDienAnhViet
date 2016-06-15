package nguyengiap.vietitpro.tudienanhviet.com.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gigamole.library.ntb.NavigationTabBar;

import java.util.ArrayList;
import java.util.List;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.fragment.FragmentMain;
import nguyengiap.vietitpro.tudienanhviet.com.fragment.FragmentSearchDocument;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ntb);
        initUI();
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        List<Fragment> listFramentPage = new ArrayList<>();
        listFramentPage.add(new FragmentMain());
        listFramentPage.add(new FragmentSearchDocument());
        listFramentPage.add(new FragmentSearchDocument());
        listFramentPage.add(new FragmentSearchDocument());
        listFramentPage.add(new FragmentSearchDocument());
        MyPageAdapter myPageAdapter = new MyPageAdapter(getSupportFragmentManager(),listFramentPage);
        viewPager.setAdapter(myPageAdapter);
        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_magnifier),
                        Color.parseColor(colors[0]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("Tra từ")
//                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_world),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Dịch Văn bản")
//                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_favourite),
                        Color.parseColor(colors[2]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("Yêu Thích")
//                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_hhistory),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Lịch Sử")
//                        .badgeTitle("icon")
                        .build()
        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_fifth),
//                        Color.parseColor(colors[4]))
////                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
//                        .title("Medal")
////                        .badgeTitle("777")
//                        .build()
//        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
