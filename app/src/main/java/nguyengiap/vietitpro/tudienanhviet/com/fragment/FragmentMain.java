package nguyengiap.vietitpro.tudienanhviet.com.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import nguyengiap.vietitpro.tudienanhviet.com.IClickListener;
import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.activity.MainActivitDefault;
import nguyengiap.vietitpro.tudienanhviet.com.adapter.AdaptetListSearch;
import nguyengiap.vietitpro.tudienanhviet.com.model.DictEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment implements IClickListener {


    RecyclerView lvListSearch;
    AdaptetListSearch mListSearchAdapter;
    View mMainLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainLayout = inflater.inflate(R.layout.fragment_fragment_main, container, false);
        lvListSearch = (RecyclerView) mMainLayout.findViewById(R.id.lvWord);
        lvListSearch.setHasFixedSize(true);
        lvListSearch.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL));
        initListSearch();



        return mMainLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initListSearch() {
        ArrayList<DictEntity> list = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add(new DictEntity(i, "Love","lav","tinh yêu,tinh thương"));
        }
        mListSearchAdapter = new AdaptetListSearch(getActivity(), list);
        lvListSearch.setAdapter(mListSearchAdapter);
        mListSearchAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(View view, DictEntity engVietDict) {

    }
}
