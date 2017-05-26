package kim.ladmusician.minesearch.presentation.ui.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import kim.ladmusician.minesearch.R;
import kim.ladmusician.minesearch.data.entity.Square;
import kim.ladmusician.minesearch.presentation.ui.adpater.viewholder.MineViewHolder;
import kim.ladmusician.minesearch.presentation.ui.interfaces.OnRecyclerItemClickListener;

public class MineAdapter extends BaseAdapter<Square, MineViewHolder> {
    private static final String TAG = MineAdapter.class.getSimpleName();

    public MineAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public void add(Square item) {
        Log.e(TAG, "add item");
        addItem(item);
    }

    @Override
    public void setItemClickListener(OnRecyclerItemClickListener listener) {

    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Square model) {
        return null;
    }

    @Override
    public void remove(int position) {
        removeItem(position);
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public MineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine, parent, false));
    }
}
