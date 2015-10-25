package jp.gcreate.sample.practiceandroidsupportlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;

/**
 * Thanks to http://qiita.com/ushi3_jp/items/ba0df2df6b0b77278f14
 */
public class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> implements View.OnClickListener{
    private final LayoutInflater mInflater;
    private final List<ActivityListData> mData;
    private RecyclerView mRecyclerView;
    private OnItemClickListener mListener;

    public SampleAdapter(Context context, List<ActivityListData> data){
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SampleViewHolder viewHolder = new SampleViewHolder(
                mInflater.inflate(SampleViewHolder.LAYOUT_ID, parent, false));
        viewHolder.itemView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        final ActivityListData target = mData.get(position);
        holder.title.setText(target.getActivityName());
        holder.description.setText(target.getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mRecyclerView == null){
            return;
        }
        if (mListener != null){
            int position = mRecyclerView.getChildAdapterPosition(v);
            ActivityListData data = mData.get(position);
            mListener.onItemClick(this, position, data);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(SampleAdapter adapter, int position, ActivityListData data);
    }

}
