package kim.ladmusician.minesearch.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import kim.ladmusician.minesearch.presentation.presenter.BasePresenter;

/**
 * Created by ladmusician on 2017. 5. 19..
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected BasePresenter basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        initView();
    }
    public abstract int getLayoutID();
    public abstract void initView();
}
