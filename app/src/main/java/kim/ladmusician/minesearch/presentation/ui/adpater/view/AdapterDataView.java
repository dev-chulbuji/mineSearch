package kim.ladmusician.minesearch.presentation.ui.adpater.view;

import kim.ladmusician.minesearch.presentation.ui.interfaces.OnRecyclerItemClickListener;

public interface AdapterDataView {
    void refresh();
    void setItemClickListener(OnRecyclerItemClickListener listener);
}
