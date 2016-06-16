package nguyengiap.vietitpro.tudienanhviet.com.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.data.DictionarySQL;
import nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch.ICallSearch;
import nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch.MaterialSearchView;

public class VerbActivity extends AppCompatActivity implements ICallSearch,AdapterView.OnItemClickListener {
    Toolbar toolbar;
    MaterialSearchView searchView;
    ListView lvdata;
    AdapterVerb adapterVerb;
    ProgressBar prBar;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verb);
        initView();
        setupToolBar();
        createsearch();
        setDataList();
    }

    private void setDataList() {
        prBar.setVisibility(View.VISIBLE);
        lvdata.setVisibility(View.GONE);
        adapterVerb = new AdapterVerb(getDataListVerb(), this);
        lvdata.setAdapter(adapterVerb);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterVerb.notifyDataSetChanged();
                        prBar.setVisibility(View.GONE);
                        lvdata.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        thread.start();
    }


    private void initView() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        lvdata = (ListView)findViewById(R.id.lvdata);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        prBar = (ProgressBar) findViewById(R.id.prBar);
        lvdata.setOnItemClickListener(this);
    }

    private void setupToolBar() {
        TextView txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText(getString(R.string.title_verb));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private ArrayList getDataListVerb() {
        DictionarySQL db = new DictionarySQL(this);
        ArrayList list = db.getListVerb();

        return list;
    }

    // khoi tao mtarial search
    private void createsearch() {
        searchView.setVoiceSearch(false, this);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setSuggestions(getdata());
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                try {
                    //Do some magic
                    searchView.setSuggestions(getdata());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

    }


    @Override
    public void OntextChange(String str) {

    }

    public ArrayList<String> getdata() {
        DictionarySQL db = new DictionarySQL(this);
        return db.getListVerbSearch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onDestroy() {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            thread = null;
        }
        super.onDestroy();
    }
}
