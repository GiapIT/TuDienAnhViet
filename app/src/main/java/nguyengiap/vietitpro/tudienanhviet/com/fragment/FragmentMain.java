package nguyengiap.vietitpro.tudienanhviet.com.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nguyengiap.vietitpro.tudienanhviet.com.IClickListener;
import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.activity.VerbActivity;
import nguyengiap.vietitpro.tudienanhviet.com.adapter.AdaptetListSearch;
import nguyengiap.vietitpro.tudienanhviet.com.common.Common;
import nguyengiap.vietitpro.tudienanhviet.com.model.DictEntity;
import nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch.searchview.SearchAdapter;
import nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch.searchview.SearchHistoryTable;
import nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch.searchview.SearchItem;
import nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch.searchview.SearchView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment implements IClickListener, View.OnClickListener {


    RecyclerView lvListSearch;
    AdaptetListSearch mListSearchAdapter;
    View mMainLayout;
    LinearLayout layout_verb;
    LinearLayout layout_windown;
    LinearLayout layout_face;
    LinearLayout layout_rate;
    LinearLayout layout_feedback;
    protected SearchView mSearchView = null;
    private SearchHistoryTable mHistoryDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainLayout = inflater.inflate(R.layout.fragment_fragment_main, container, false);
        initView();
        initListSearch();
        setSearchView();
        return mMainLayout;
    }

    private void initView() {
        lvListSearch = (RecyclerView) mMainLayout.findViewById(R.id.lvWord);
        lvListSearch.setHasFixedSize(true);
        lvListSearch.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL));

        layout_verb = (LinearLayout) mMainLayout.findViewById(R.id.layout_verb);
        layout_windown = (LinearLayout) mMainLayout.findViewById(R.id.layout_windown);
        layout_face = (LinearLayout) mMainLayout.findViewById(R.id.layout_face);
        layout_rate = (LinearLayout) mMainLayout.findViewById(R.id.layout_rate);
        layout_feedback = (LinearLayout) mMainLayout.findViewById(R.id.layout_feedback);
        layout_verb.setOnClickListener(this);
        layout_windown.setOnClickListener(this);
        layout_face.setOnClickListener(this);
        layout_rate.setOnClickListener(this);
        layout_feedback.setOnClickListener(this);
        mSearchView = (SearchView)mMainLayout.findViewById(R.id.searchView);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initListSearch() {
        ArrayList<DictEntity> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new DictEntity(i, "Love", "lav", "tinh yêu,tinh thương"));
        }
        mListSearchAdapter = new AdaptetListSearch(getActivity(), list);
        lvListSearch.setAdapter(mListSearchAdapter);
        mListSearchAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(View view, DictEntity engVietDict) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_verb:
                Intent intent=new Intent(getActivity(), VerbActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_windown:
                break;
            case R.id.layout_face:
                Common.shareFacebook(getActivity());
                break;
            case R.id.layout_rate:
                Common.showApp(getActivity(),getActivity().getPackageName());
                break;
            case R.id.layout_feedback:
                Common.sendFeedBack(getActivity());
                break;
        }
    }


    protected void setSearchView() {
        mHistoryDatabase = new SearchHistoryTable(getContext());

        if (mSearchView != null) {
            mSearchView.setVersion(SearchView.VERSION_TOOLBAR);
            mSearchView.setVersionMargins(SearchView.VERSION_MARGINS_TOOLBAR_BIG);
            mSearchView.setHint(R.string.search);
            mSearchView.setTextSize(16);
            mSearchView.setHint("Nhập từ cần tra");
            mSearchView.setDivider(false);
            mSearchView.setVoice(true);
            mSearchView.setVoiceText("Set permission on Android 6+ !");
            mSearchView.setAnimationDuration(SearchView.ANIMATION_DURATION);
            mSearchView.setShadowColor(ContextCompat.getColor(getContext(), R.color.search_shadow_layout));
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    mSearchView.close(false);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            mSearchView.setOnOpenCloseListener(new SearchView.OnOpenCloseListener() {
                @Override
                public void onOpen() {

                }

                @Override
                public void onClose() {

                }
            });

            List<SearchItem> suggestionsList = new ArrayList<>();
            suggestionsList.add(new SearchItem("search1"));
            suggestionsList.add(new SearchItem("search2"));
            suggestionsList.add(new SearchItem("search3"));

            SearchAdapter searchAdapter = new SearchAdapter(getContext(), suggestionsList);
            searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    mSearchView.close(false);
                    TextView textView = (TextView) view.findViewById(R.id.textView_item_text);

                     String query = textView.getText().toString();
                }
            });
            mSearchView.setAdapter(searchAdapter);
        }
    }




}
