package nguyengiap.vietitpro.tudienanhviet.com.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Locale;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.model.VerbEntity;

/**
 * Created by Nguyen Giap on 9/11/2015.
 */
public class AdapterVerb extends BaseAdapter {
    public ArrayList<VerbEntity> getListsearch() {
        return listsearch;
    }

    public void setListsearch(ArrayList<VerbEntity> listsearch) {
        this.listsearch = listsearch;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    ArrayList<VerbEntity> listsearch;
    LayoutInflater layoutInflater;

    public AdapterVerb(ArrayList<VerbEntity> list, Context context) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listsearch = new ArrayList();
        listsearch.addAll(list);
    }

    ArrayList list;


    // Filter Class
    public void filter(String chartext) {
        chartext = chartext.toLowerCase(Locale.getDefault());
        list.clear();
        for (VerbEntity item : listsearch) {
            String key = item.getKeyWord();

            if (key.toLowerCase(Locale.getDefault()).contains(chartext.trim())) {
                list.add(item);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VerbEntity item = (VerbEntity) getItem(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.itemlistverb, parent, false);
        }
        ViewHodler vh = new ViewHodler();
        if (convertView.getTag() == null) {
            vh.SimplePast = (TextView) convertView.findViewById(R.id.SimplePast);
            vh.SimplePresent = (TextView) convertView.findViewById(R.id.SimplePresent);
            vh.PastPraticiple = (TextView) convertView.findViewById(R.id.PastPraticiple);
            vh.PresentParticiple = (TextView) convertView.findViewById(R.id.PresentParticiple);
        } else {
            vh = (ViewHodler) convertView.getTag();
        }
        vh.SimplePast.setText(item.getSimplePast());
        vh.SimplePresent.setText(item.getSimplePresent());
        vh.PastPraticiple.setText(item.getPastPraticiple());
        vh.PresentParticiple.setText(item.getPresentParticiple());
        return convertView;
    }

    class ViewHodler {
        TextView SimplePresent, SimplePast, PastPraticiple, PresentParticiple;

    }
}
