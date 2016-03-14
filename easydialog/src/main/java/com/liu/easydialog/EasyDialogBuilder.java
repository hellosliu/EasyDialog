package com.liu.easydialog;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.liu.easydialog.listener.OnMenuClickListener;

import java.util.List;

public class EasyDialogBuilder {

    private Context mContext;
    private static final int INVALID = -1;
    private static final int INVALID_COLOR = 0;

    private AbsTractDialogView dialogView;

    private int inAnimation = INVALID;
    private int outAnimation = INVALID;

    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
    );



    private List<String> menuNames;
    private OnMenuClickListener onMenuClickListener;
    private boolean isShowCanCel = true;
    private String cancelText;
    private float menuTextSize = INVALID;
    private int menuTextColor = INVALID_COLOR;
    private int menuBackground = INVALID_COLOR;
    private int cancelBackground = INVALID_COLOR;



    public EasyDialogBuilder(Context context){
        this.mContext = context;
    }

    public EasyDialog create() {
        return new EasyDialog(this);
    }

    public EasyDialogBuilder setDialogView(AbsTractDialogView dialogView){
        this.dialogView = dialogView;
        return this;
    }

    public AbsTractDialogView getDialogView() {
        return dialogView;
    }

    public Context getContext() {
        return mContext;
    }

    public Animation getInAnimation() {
        int res = (inAnimation == INVALID) ? R.anim.slide_in_bottom:inAnimation;
        return AnimationUtils.loadAnimation(mContext, res);
    }

    public EasyDialogBuilder setInAnimation(int inAnimation) {
        this.inAnimation = inAnimation;
        return this;
    }

    public Animation getOutAnimation() {
        int res = (outAnimation == INVALID) ? R.anim.slide_out_bottom:inAnimation;
        return AnimationUtils.loadAnimation(mContext, res);
    }

    public EasyDialogBuilder setOutAnimation(int outAnimation) {
        this.outAnimation = outAnimation;
        return this;
    }

    public List<String> getMenuNames() {
        return menuNames;
    }

    public EasyDialogBuilder setMenuNames(List<String> menuNames) {
        this.menuNames = menuNames;
        return this;
    }

    public OnMenuClickListener getOnMenuClickListener() {
        return onMenuClickListener;
    }

    public EasyDialogBuilder setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
        return this;
    }

    public boolean isShowCanCel() {
        return isShowCanCel;
    }

    public EasyDialogBuilder setShowCanCel(boolean isShowCanCel) {
        this.isShowCanCel = isShowCanCel;
        return this;
    }

    public String getCancelText() {
        return cancelText;
    }

    public EasyDialogBuilder setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public int getMenuTextColor() {
        return menuTextColor;
    }

    public EasyDialogBuilder setMenuTextColor(int menuTextColor) {
        this.menuTextColor = menuTextColor;
        return this;
    }

    public float getMenuTextSize() {
        return menuTextSize;
    }

    public EasyDialogBuilder setMenuTextSize(float menuTextSize) {
        this.menuTextSize = menuTextSize;
        return this;
    }

    public int getMenuBackground() {
        return menuBackground;
    }

    public EasyDialogBuilder setMenuBackground(int menuBackground) {
        this.menuBackground = menuBackground;
        return this;
    }

    public int getCancelBackground() {
        return cancelBackground;
    }

    public EasyDialogBuilder setCancelBackground(int cancelBackground) {
        this.cancelBackground = cancelBackground;
        return this;
    }

    public FrameLayout.LayoutParams getContentParams() {

        return params;
    }

    public FrameLayout.LayoutParams getRootLayoutParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        return params;
    }




}
