package com.jersey.practicalmodel.uis.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jersey.practicalmodel.R;


/**
 * 等待框
 */
public class WaitProgressDialog extends Dialog {
    Context context;
    private TextView mTxtMsg = null;
    private ImageView mPbCircle = null;

    private AnimationDrawable aniDraw;

    public TextView getmTxtMsg() {
        return mTxtMsg;
    }

    public void setmTxtMsg(TextView mTxtMsg) {
        this.mTxtMsg = mTxtMsg;
    }

    public void setMsg(String msg) {
        if (mTxtMsg != null && msg != null) {
            mTxtMsg.setText(msg);
        }
    }

    public WaitProgressDialog(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public WaitProgressDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.dialog_custom_loading, null);
        mTxtMsg = (TextView) view.findViewById(R.id.progressDialog_tv);
        mPbCircle = (ImageView) view.findViewById(R.id.progressDialog_progress);
        aniDraw = (AnimationDrawable) mPbCircle.getBackground();
        this.setContentView(view);
        WindowManager m = ((Activity) context).getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = this.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getWidth() * 0.3); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.3); // 宽度设置为屏幕的0.65
        this.getWindow().setAttributes(p);
    }

    @Override
    public void show() {
        try {
            super.show();
            if (aniDraw != null) {
                aniDraw.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dismiss() {
        try {
            if (isShowing()) {
                super.dismiss();
                if (aniDraw != null) {
                    aniDraw.stop();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
