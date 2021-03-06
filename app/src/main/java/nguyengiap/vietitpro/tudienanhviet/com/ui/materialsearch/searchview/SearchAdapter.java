package nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch.searchview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nguyengiap.vietitpro.tudienanhviet.com.R;
import nguyengiap.vietitpro.tudienanhviet.com.model.EVEntity;

// TODO file:///E:/Android/SearchView/sample/build/outputs/lint-results-debug.html
// TODO file:///E:/Android/SearchView/searchview/build/outputs/lint-results-debug.html
// TODO voice click result
// TODO ARROW / HAMBURGER / BEHAVIOR / SingleTask / DIVIDER BUG
// TODO E/RecyclerView: No adapter attached; skipping layout when search
// W/IInputConnectionWrapper: getTextBeforeCursor on inactive InputConnection
    /*
    I'm using your SearchView library (thanks btw)
    and it seems to have a problem on filters with no results.
    When i type texts that don't have a match in the history, all of it is displayed as suggestions.
    */
@SuppressWarnings("WeakerAccess")
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ResultViewHolder> implements Filterable {

    protected final SearchHistoryTable mHistoryDatabase;
    protected String key = " ";
    protected List<EVEntity> mResultList = new ArrayList<>();
    protected List<EVEntity> mSuggestionsList = new ArrayList<>();
    protected OnItemClickListener mItemClickListener;

    @SuppressWarnings("unused")
    public SearchAdapter(Context context) {// getContext();
        mHistoryDatabase = new SearchHistoryTable(context);
    }

    public SearchAdapter(Context context, List<EVEntity> suggestionsList) {
        mSuggestionsList = suggestionsList;
        mHistoryDatabase = new SearchHistoryTable(context);
    }

    @SuppressWarnings("unused")
    public List<EVEntity> getSuggestionsList() {
        return mSuggestionsList;
    }

    @SuppressWarnings("unused")
    public void setSuggestionsList(List<EVEntity> suggestionsList) {
        mSuggestionsList = suggestionsList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if (!TextUtils.isEmpty(constraint)) {
                    key = constraint.toString().toLowerCase(Locale.getDefault());

                    List<EVEntity> results = new ArrayList<>();
                    List<EVEntity> history = new ArrayList<>();
                    if (!mHistoryDatabase.getAllItems().isEmpty()) {
                        history.addAll(mHistoryDatabase.getAllItems());
                    }
                    history.addAll(mSuggestionsList);

                    for (EVEntity str : history) {
                        String string = str.getWord().toLowerCase(Locale.getDefault());
                        if (string.contains(key)) {
                            results.add(str);
                        }
                    }

                    if (results.size() > 0) {
                        filterResults.values = results;
                        filterResults.count = results.size();
                    }
                } else {
                    key = " ";
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mResultList.clear();

                if (results.values != null) {
                    List<?> result = (ArrayList<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof EVEntity) {
                            mResultList.add((EVEntity) object);
                        }
                    }
                } else {
                    if (!mHistoryDatabase.getAllItems().isEmpty()) {
                        mResultList = mHistoryDatabase.getAllItems();
                    }
                }

                notifyDataSetChanged();
            }
        };
    }

    @Override
    public ResultViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.search_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder viewHolder, int position) {
        EVEntity item = mResultList.get(position);

        viewHolder.icon_left.setImageResource(R.drawable.ic_abs__ic_search);
        viewHolder.icon_left.setColorFilter(SearchView.getIconColor(), PorterDuff.Mode.SRC_IN);
        viewHolder.text.setTextColor(SearchView.getTextColor());

        String string = item.getWord().toString().toLowerCase(Locale.getDefault());

        if (string.contains(key)) {
            SpannableString s = new SpannableString(string);
            s.setSpan(new ForegroundColorSpan(SearchView.getTextHighlightColor()), string.indexOf(key), string.indexOf(key) + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.text.setText(s, TextView.BufferType.SPANNABLE);
        } else {
            viewHolder.text.setText(item.getWord());
        }
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView icon_left;
        final TextView text;

        ResultViewHolder(View view) {
            super(view);
            icon_left = (ImageView) view.findViewById(R.id.imageView_item_icon_left);
            text = (TextView) view.findViewById(R.id.textView_item_text);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }

}

/*else {
            s.removeSpan(new ForegroundColorSpan(SearchView.getTextColor()));
            viewHolder.text.setText(s, TextView.BufferType.SPANNABLE);
}*/