package kim.ladmusician.minesearch.presentation.ui.adpater.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import kim.ladmusician.minesearch.R;
import kim.ladmusician.minesearch.data.entity.Square;
import kim.ladmusician.minesearch.presentation.ui.interfaces.OnRecyclerItemClickListener;

public class MineViewHolder extends BaseViewHolder<Square> {
    @BindView(R.id.item_mine)
    CardView cardMine;
    @BindView(R.id.item_mine_txt)
    TextView txtMine;

    public MineViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(int position, Square item, OnRecyclerItemClickListener listener) {
        cardMine.setOnClickListener(v -> {
            listener.onItemClick(cardMine.getId(), position);
        });

        if (item.isCheck()) {
            if (item.isMine())
                txtMine.setText("*");
            else
                txtMine.setText(item.getMineNum() + "");
        } else {
            txtMine.setText("");
        }
    }
}
