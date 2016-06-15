package nguyengiap.vietitpro.tudienanhviet.com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.adapter.AdaptetListSearch;
import nguyengiap.vietitpro.tudienanhviet.com.model.DictEntity;

public class MainActivitDefault extends AppCompatActivity {
    RecyclerView lvListSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activit_default);
        lvListSearch = (RecyclerView) findViewById(R.id.lvWord);
        initListSearch();
    }

    private void initListSearch() {
        ArrayList<DictEntity> list = new ArrayList<>();
        list.add(new DictEntity(1, "Love"));
        list.add(new DictEntity(1, "Love"));
        list.add(new DictEntity(1, "Love"));
        list.add(new DictEntity(1, "Love"));
        list.add(new DictEntity(1, "Love"));
        list.add(new DictEntity(1, "Love"));
        list.add(new DictEntity(1, "Love"));
        list.add(new DictEntity(1, "Love"));
        list.add(new DictEntity(1, "Love"));
        AdaptetListSearch mListSearchAdapter = new AdaptetListSearch(this, list);
        lvListSearch.setHasFixedSize(true);
        lvListSearch.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL));
        lvListSearch.setAdapter(mListSearchAdapter);
        mListSearchAdapter.notifyDataSetChanged();
    }
}
