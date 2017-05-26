package kim.ladmusician.minesearch.presentation.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;

import kim.ladmusician.minesearch.R;
import kim.ladmusician.minesearch.databinding.DialogCustomBinding;

public class CustomDialog extends Dialog {

    private Context mContext = null;
    private DialogCustomBinding mBinding = null;
    private DialogModel mModel;
    private OnClickListener onBtnClickListener;
    private boolean mIsShowing;

    public CustomDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        mModel = new DialogModel();
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mContext = context;
        mModel = new DialogModel();
    }

    public CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        mModel = new DialogModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DialogCustomBinding.inflate(inflater);
        mBinding.setDialog(this);
        mBinding.setItem(mModel);
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public CustomDialog setTitleText(String title) {
        mModel.setTitle(title);
        return this;
    }

    public CustomDialog setTitleText(@StringRes int titleId) {
        mModel.setTitle(mContext.getString(titleId));
        return this;
    }

    public CustomDialog setMessageText(String message) {
        mModel.setMessage(message);
        return this;
    }

    public CustomDialog setMessageText(@StringRes int messageId) {
        mModel.setMessage(mContext.getString(messageId));
        return this;
    }

    public CustomDialog setBtn(String text, OnClickListener onClickListener) {
        mModel.setBtnTxt(text);
        onBtnClickListener = onClickListener;
        return this;
    }

    public void onBtnClick(View view) {
        int which = view.getId() == R.id.dialog_btn_ok ? BUTTON_POSITIVE : BUTTON_NEGATIVE;
        switch (view.getId()) {
            case R.id.dialog_btn_ok:
                onBtnClickListener.onClick(this, which);
                break;
        }
    }

    @Override
    public void show() {
        mIsShowing = true;
        super.show();
    }

    @Override
    public void dismiss() {
        mIsShowing = false;
        super.dismiss();
    }

    @Override
    public void cancel() {
        mIsShowing = false;
        super.cancel();
    }

    @Override
    public boolean isShowing() {
        return mIsShowing;
    }
}
