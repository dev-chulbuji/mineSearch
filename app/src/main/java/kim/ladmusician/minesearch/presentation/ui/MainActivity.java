package kim.ladmusician.minesearch.presentation.ui;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import kim.ladmusician.minesearch.R;
import kim.ladmusician.minesearch.data.SquareManager;
import kim.ladmusician.minesearch.presentation.ui.adpater.MineAdapter;
import kim.ladmusician.minesearch.presentation.ui.base.BaseActivity;
import kim.ladmusician.minesearch.presentation.ui.dialog.CustomDialog;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_mine)
    RecyclerView lvMine;
    @BindView(R.id.main_timer)
    TextView txtTime;
    @BindView(R.id.main_toolbar_start)
    TextView txtStart;

    enum GameEnum {
        INIT,
        RUN,
        STOP
    }

    GameEnum currentState = GameEnum.INIT;
    MineAdapter adapter = null;
    long baseTime;
    long pauseTime;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.main_toolbar_start, R.id.main_toolbar_new_game, R.id.main_toolbar_show_answer})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_toolbar_new_game:
                setInitGame();
                setTimerInitialize();
                txtStart.setClickable(true);
                adapter.clearAndAddAll(SquareManager.getInstance().getSquareList());
                break;
            case R.id.main_toolbar_show_answer:
                setTimerStop();
                txtStart.setClickable(false);
                SquareManager.getInstance().setAllSqureOpen();
                adapter.clearAndAddAll(SquareManager.getInstance().getSquareList());
                break;
            case R.id.main_toolbar_start:
                switch (currentState) {
                    case INIT:
                        setTimerStart();
                        break;
                    case RUN:
                        setTimerStop();
                        break;
                    case STOP:
                        setTimerReStart();
                        break;
                }

                break;
        }
    }

    @Override
    public void initView() {
        adapter = new MineAdapter(this);
        lvMine.setAdapter(adapter);
        lvMine.setHasFixedSize(true);
        lvMine.setLayoutManager(new GridLayoutManager(this, 10));

        setInitGame();

        adapter.addAll(SquareManager.getInstance().getSquareList());
        adapter.setOnRecyclerItemClickListener((id, position) -> {
            switch (id) {
                case R.id.item_mine:
                    if (currentState == GameEnum.RUN) {
                        adapter.getItem(position).setCheck(true);
                        SquareManager.getInstance().setCheck(position);
                        adapter.notifyItemChanged(position);

                        if (SquareManager.getInstance().isSuccess()) {
                            setTimerStop();
                            openDialog(R.string.success_game_title, R.string.success_game_content);
                        }

                        if (adapter.getItem(position).isMine()) {
                            setTimerStop();
                            openDialog(R.string.finish_game_title, R.string.finish_game_content);
                        }
                    }
                    break;
            }
        });
    }

    private void setInitGame() {
        SquareManager.getInstance().setInitState();
    }

    Handler gameTimer = new Handler(){
        public void handleMessage(Message msg){
            txtTime.setText(getTimeOut());
            gameTimer.sendEmptyMessage(0);
        }
    };

    String getTimeOut(){
        long now = SystemClock.elapsedRealtime();
        long outTime = now - baseTime;
        String easy_outTime = String.format("%02d:%02d:%02d", outTime/1000 / 60, (outTime/1000)%60,(outTime%1000)/10);
        return easy_outTime;

    }

    private void openDialog(int titleRes, int contentRes) {
        CustomDialog customDialog = new CustomDialog(this);
        DialogInterface.OnClickListener onClickListener = (dialog, which) ->  {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    setInitGame();
                    setTimerInitialize();
                    txtStart.setClickable(true);
                    adapter.clearAndAddAll(SquareManager.getInstance().getSquareList());
                    break;
            }
            dialog.dismiss();
        };
        customDialog.setTitleText(titleRes)
                .setMessageText(contentRes)
                .setBtn(getString(R.string.dialog_btn_txt), onClickListener)
                .show();
    }

    private void setTimerInitialize() {
        baseTime = 0;
        pauseTime = 0;
        txtTime.setText("00:00:00");
        currentState = GameEnum.INIT;
        txtStart.setText(R.string.tb_start);
        gameTimer.removeMessages(0);
    }

    private void setTimerStop() {
        gameTimer.removeMessages(0);
        pauseTime = SystemClock.elapsedRealtime();
        txtStart.setText(R.string.tb_start);
        currentState = GameEnum.STOP;
    }

    private void setTimerStart() {
        baseTime = SystemClock.elapsedRealtime();
        txtStart.setText(R.string.tb_stop);
        gameTimer.sendEmptyMessage(0);
        txtStart.setText(R.string.tb_stop);
        currentState = GameEnum.RUN;
    }

    private void setTimerReStart() {
        long now = SystemClock.elapsedRealtime();
        gameTimer.sendEmptyMessage(0);
        baseTime += (now- pauseTime);
        txtStart.setText(R.string.tb_start);
        currentState = GameEnum.RUN;
    }
}
