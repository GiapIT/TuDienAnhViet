package nguyengiap.vietitpro.tudienanhviet.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.model.DictEntity;

/**
 * Created by Amin Ghabi on 13/09/15.
 *
 */
public class AdaptetListHistory extends RecyclerView.Adapter<AdaptetListHistory.RadioViewHolder> {

    private Context context;
    private List<DictEntity> listVoca;

    public AdaptetListHistory(Context context, List<DictEntity> listVoca) {
        this.context = context;
        this.listVoca = listVoca;
    }

    @Override
    public RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_history, parent, false);
        return new RadioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RadioViewHolder holder, final int position) {
        DictEntity item = listVoca.get(position);
        holder.txt_word.setText(item.getSword().toUpperCase());
        holder.txt_phonetic.setText("/ "+item.getSphonetic()+" /");
        holder.txt_summary.setText(item.getSsummary());

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
