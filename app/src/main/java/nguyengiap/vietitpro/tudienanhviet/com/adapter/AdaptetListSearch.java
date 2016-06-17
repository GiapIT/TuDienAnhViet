package nguyengiap.vietitpro.tudienanhviet.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.model.EVEntity;

/**
 * Created by Amin Ghabi on 13/09/15.
 *
 */
public class AdaptetListSearch extends RecyclerView.Adapter<AdaptetListSearch.RadioViewHolder> {

    private Context context;
    private List<EVEntity> listVoca;

    public AdaptetListSearch(Context context, List<EVEntity> listVoca) {
        this.context = context;
        this.listVoca = listVoca;
    }

    @Override
    public RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search, parent, false);
        return new RadioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RadioViewHolder holder, final int position) {
        EVEntity item = listVoca.get(position);
        holder.txt_word.setText(item.getWord().toUpperCase());
        if(!TextUtils.isEmpty(item.getPhonic())) {
            holder.txt_phonetic.setText(item.getPhonic());
        }
        if(!TextUtils.isEmpty(item.getSimpleMeans())) {
            holder.txt_summary.setText(item.getSimpleMeans());
        }
    }

    @Override
    public int getItemCount() {
        return listVoca.size();
    }

    public class RadioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_word;
        TextView txt_phonetic;
        TextView txt_summary;

        public RadioViewHolder(View itemView) {
            super(itemView);
            txt_word= (TextView) itemView.findViewById(R.id.word);
            txt_phonetic= (TextView) itemView.findViewById(R.id.phonetic);
            txt_summary= (TextView) itemView.findViewById(R.id.txtsummary);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Toast.makeText(context,"aaa",Toast.LENGTH_LONG).show();
        }
    }
}
