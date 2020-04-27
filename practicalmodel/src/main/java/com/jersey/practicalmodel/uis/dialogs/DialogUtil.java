package com.jersey.practicalmodel.uis.dialogs;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.jersey.practicalmodel.R;


/**
 * 对话框工具
 */
public class DialogUtil {

    private Activity mActivity = null;

    public DialogUtil(Activity activity) {
        this.mActivity = activity;
    }

    public Dialog alert(CharSequence title, CharSequence message) {

        Builder builder = new Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public Dialog alert(int title, int message) {

        Builder builder = new Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public Dialog confirm(CharSequence title, CharSequence message, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener) {
        Builder builder = new Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(message);

        try {
            builder.setPositiveButton("确定", confirmListener);
        } catch (Exception e) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        try {
            builder.setNegativeButton("取消", cancelListener);
        } catch (Exception e) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public Dialog confirm(int title, int message, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener) {
        Builder builder = new Builder(mActivity);
        builder.setTitle(title);
        builder.setMessage(message);
        try {
            builder.setPositiveButton("确定", confirmListener);
        } catch (Exception e) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        try {
            builder.setNegativeButton("取消", cancelListener);
        } catch (Exception e) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    // 弹出自定义的窗体
    public Dialog showView(CharSequence title, View view, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener) {
        Builder builder = new Builder(mActivity);
        if (title != "") {
            builder.setTitle(title);
        }
        builder.setView(view);

        try {
            builder.setPositiveButton("确定", confirmListener);
        } catch (Exception e) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        try {
            builder.setNegativeButton("取消", cancelListener);
        } catch (Exception e) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public Dialog showView(int title, View view, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener) {
        Builder builder = new Builder(mActivity);
        if (title != 0) {
            builder.setTitle(title);
        }
        builder.setView(view);

        try {
            builder.setPositiveButton("确定", confirmListener);
        } catch (Exception e) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        try {
            builder.setNegativeButton("取消", cancelListener);
        } catch (Exception e) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    // 弹出自定义的信息
    public Dialog showMsg(CharSequence title, CharSequence message) {
        Builder builder = new Builder(mActivity);
        if (title != "") {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public Dialog showMsg(int title, int message) {
        Builder builder = new Builder(mActivity);
        if (title != 0) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public Dialog showLoading(String message) {
        WaitProgressDialog dialog = new WaitProgressDialog(mActivity, R.style.dialogloading);
        TextView txt = dialog.getmTxtMsg();
        if (message != null && txt != null) {
            txt.setText(message);
        }
        if (mActivity != null && !mActivity.isFinishing()) {
            dialog.show();
        }
        dialog.setCancelable(false);
        return dialog;
    }

    public Dialog showLoading() {
        WaitProgressDialog dialog = new WaitProgressDialog(mActivity, R.style.dialogloading);
        dialog.setCancelable(false);
        return dialog;
    }

    public Dialog showLoading(boolean cancelable) {
        WaitProgressDialog dialog = new WaitProgressDialog(mActivity, R.style.dialogloading);
        dialog.setCancelable(cancelable);
        return dialog;
    }

    public Dialog customDialog(View view) {
        Dialog dialog = new Dialog(mActivity);
        dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setContentView(view);
        setWindowManager(dialog);
        return dialog;
    }

    /**
     * 设置弹窗的大小
     */
    private void setWindowManager(Dialog dialog) {
        try {
            WindowManager m = mActivity.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
            p.width = (int) (d.getWidth() * 0.8); // 宽度设置
            dialog.getWindow().setAttributes(p);
            dialog.setCancelable(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
