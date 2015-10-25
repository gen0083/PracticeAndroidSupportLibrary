package jp.gcreate.sample.practiceandroidsupportlibrary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * 15/10/25
 */
public class SampleViewHolder extends RecyclerView.ViewHolder{
    static final int LAYOUT_ID = R.layout.component_three_line_list;
    public final TextView title;
    public final TextView description;

    public SampleViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title_text);
        description = (TextView) itemView.findViewById(R.id.secondary_text);
    }
}
