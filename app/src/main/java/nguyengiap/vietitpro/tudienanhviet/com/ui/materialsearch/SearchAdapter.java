package nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.app.Application;
import nguyengiap.vietitpro.tudienanhviet.com.data.DictionarySQL;
import nguyengiap.vietitpro.tudienanhviet.com.model.VerbEntity;


/**
 * @author Miguel Catalan Bañuls
 */
public class SearchAdapter extends BaseAdapter implements Filterable {

    private ArrayList<String> data;

    private ArrayList<String> typeAheadData;

    private Drawable suggestionIcon;

    LayoutInflater inflater;
    ICallSearch iCallSearch;

    DictionarySQL db;


    public SearchAdapter(Context context, ArrayList<String> typeAheadData, Drawable suggestionIcon, ICallSearch iCallSearch) {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
        this.iCallSearch = iCallSearch;
        this.typeAheadData = typeAheadData;
        this.suggestionIcon = suggestionIcon;
        db=new DictionarySQL(Application.getHKApplicationContext());
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (!TextUtils.isEmpty(constraint)) {
                    // Retrieve the autocomplete results.
                    ArrayList<String> searchData = new ArrayList<>();

                    for (String str : typeAheadData) {
                        if (str.toLowerCase(Locale.getDefault()).contains(constraint.toString().trim())) {
                            searchData.add(str);
                        }
                    }

                    // Assign the data to the FilterResults
                    filterResults.values = searchData;
                    filterResults.count = searchData.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.values != null) {
                    data = (ArrayList<String>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
        return filter;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.suggest_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final String currentListData = (String) getItem(position);
        VerbEntity verbEntity=db.getVerbEntitySearch(currentListData);

        mViewHolder.SimplePresent.setText(verbEntity.getSimplePresent());
        mViewHolder.SimplePast.setText(verbEntity.getSimplePast());
        mViewHolder.PastPraticiple.setText(verbEntity.getPastPraticiple());
        mViewHolder.PresentParticiple.setText(verbEntity.getPresentParticiple());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallSearch.OntextChange(currentListData);
            }
        });


        return convertView;
    }

    private class MyViewHolder {
        TextView SimplePresent,SimplePast,PastPraticiple,PresentParticiple;
        ImageView imageView;

        public MyViewHolder(View convertView) {
            SimplePresent = (TextView) convertView.findViewById(R.id.SimplePresent);
            SimplePast = (TextView) convertView.findViewById(R.id.SimplePast);
            PastPraticiple = (TextView) convertView.findViewById(R.id.PastPraticiple);
            PresentParticiple = (TextView) convertView.findViewById(R.id.PresentParticiple);

            if (suggestionIcon != null) {
                imageView = (ImageView) convertView.findViewById(R.id.suggestion_icon);
                imageView.setImageDrawable(suggestionIcon);
            }
        }
    }
}