package com.liu.easydialog;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;


public class EasyDialog {

    private final AbsTractDialogView dialogView;
    private final ViewGroup decorView;
    private final ViewGroup rootView;
    private final ViewGroup rootContent;

    private final Animation outAnim;
    private final Animation inAnim;
    private boolean isDismissing;

    private EasyDialogBuilder mBuilder;

    public EasyDialog(EasyDialogBuilder builder){
        Activity activity = (Activity)builder.getContext();

        mBuilder = builder;
        dialogView = builder.getDialogView();

        LayoutInflater layoutInflater = LayoutInflater.from(builder.getContext());

        decorView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.view_root, decorView, false);

        rootContent = (ViewGroup) rootView.findViewById(R.id.fl_root_content);
        rootContent.setLayoutParams(builder.getContentParams());



        rootView.setLayoutParams(builder.getRootLayoutParams());

        rootContent.addView(createView(layoutInflater));

        outAnim = builder.getOutAnimation();
        inAnim = builder.getInAnimation();

        initCancelable();

    }

    private void initCancelable(){
        View view = rootView.findViewById(R.id.fl_easydialog_main_root);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return false;
            }
        });
    }

    private View createView(LayoutInflater inflater){

        if(dialogView instanceof MenuDialogView){
            MenuDialogView menuDialogView = (MenuDialogView) dialogView;
            menuDialogView.setOnMenuClickListener(mBuilder.getOnMenuClickListener());
            menuDialogView.setMenuNames(mBuilder.getMenuNames());
            menuDialogView.setIsShowCanCel(mBuilder.isShowCanCel());
            menuDialogView.setCancelText(mBuilder.getCancelText());
            menuDialogView.setMenuTextColor(mBuilder.getMenuTextColor());
            menuDialogView.setMenuTextSize(mBuilder.getMenuTextSize());
            menuDialogView.setMenuBackground(mBuilder.getMenuBackground());
            menuDialogView.setCancelBackground(mBuilder.getCancelBackground());
        }
        View view = dialogView.getView(inflater, rootView);
        return view;
    }

    public void show(){
        onAttached(rootView);
    }

    public void dismiss(){
        if(isDismissing){
            return;
        }
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                decorView.post(new Runnable() {
                    @Override
                    public void run() {
                        decorView.removeView(rootView);
                        isDismissing = false;
                    }
                });
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        rootContent.startAnimation(outAnim);
        isDismissing = true;
    }

    private void onAttached(ViewGroup view) {

        decorView.addView(view);

        rootContent.startAnimation(inAnim);
        rootContent.requestFocus();

        dialogView.setOnkeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getAction()) {
                    case KeyEvent.ACTION_UP:
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            dismiss();
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        dialogView.setDismissClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    public static EasyDialogBuilder newBuilder(Context context) {
        return new EasyDialogBuilder(context);
    }

}

