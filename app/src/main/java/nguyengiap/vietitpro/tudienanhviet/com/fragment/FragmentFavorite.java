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
import nguyengiap.vietitpro.tudienanhviet.com.adapter.AdaptetListSearch;
import nguyengiap.vietitpro.tudienanhviet.com.model.DictEntity;

public class FragmentFavorite extends Fragment {

    RecyclerView lvListSearch;
    AdaptetListFavorite mAdaptetListFavorite;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mMainLayout=inflater.inflate(R.layout.fragment_fragment_favorite, container, false);
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
        mAdaptetListFavorite = new AdaptetListFavorite(getActivity(), list);
        lvListSearch.setAdapter(mAdaptetListFavorite);
        mAdaptetListFavorite.notifyDataSetChanged();
    }

}