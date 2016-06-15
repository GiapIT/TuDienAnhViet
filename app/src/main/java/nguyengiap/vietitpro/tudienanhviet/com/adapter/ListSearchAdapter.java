package nguyengiap.vietitpro.tudienanhviet.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nguyengiap.vietitpro.tudienanhviet.com.IClickListener;
import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.model.DictEntity;

/**
 * Created by Gordon Wong on 7/18/2015.
 * <p>
 * Adapter for the all items screen.
 */
public class ListSearchAdapter extends RecyclerView.Adapter<ListSearchAdapter.ViewHolder> {
    static Context context;
    static IClickListener itemClickListener;

    public ListSearchAdapter(Context context, IClickListener ionItemClickListener,ArrayList<DictEntity> list) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.itemClickListener = ionItemClickListener;
        this.list=list;
    }

    LayoutInflater inflater;



    public ArrayList<DictEntity> list;

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public ListSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item_search, parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DictEntity item = list.get(position);
        holder.txt_word.setText(item.getSword());

    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView txt_word;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_word = (TextView) itemView.findViewById(R.id.word);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }

        @Override
        public boolean onLongClick(View view) {

            return true;
        }
    }


}
