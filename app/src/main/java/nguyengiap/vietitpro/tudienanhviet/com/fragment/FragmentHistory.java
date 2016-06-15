package nguyengiap.vietitpro.tudienanhviet.com.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.adapter.AdaptetListFavorite;
import nguyengiap.vietitpro.tudienanhviet.com.adapter.AdaptetListHistory;
import nguyengiap.vietitpro.tudienanhviet.com.model.DictEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHistory extends Fragment {

    RecyclerView lvListSearch;
    AdaptetListHistory mAdaptetListHistory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mMainLayout=inflater.inflate(R.layout.fragment_fragment_history, container, false);
        lvListSearch = (RecyclerView) mMainLayout.findViewById(R.id.lvWord);
        lvListSearch.setHasFixedSize(true);
        lvListSearch.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL));
        initListSearch();
        return mMainLayout;
    }
    private void initListSearch() {
        ArrayList<DictEntity> list = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add(new DictEntity(i, "Love","lav","tinh yêu,tinh thương"));
        }
        mAdaptetListHistory = new AdaptetListHistory(getActivity(), list);
        lvListSearch.setAdapter(mAdaptetListHistory);
        mAdaptetListHistory.notifyDataSetChanged();
    }


}
