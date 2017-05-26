package kim.ladmusician.minesearch.presentation.ui.adpater.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import kim.ladmusician.minesearch.presentation.ui.interfaces.OnRecyclerItemClickListener;

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public abstract void bindView(
            int position, M item, OnRecyclerItemClickListener listener);
}
