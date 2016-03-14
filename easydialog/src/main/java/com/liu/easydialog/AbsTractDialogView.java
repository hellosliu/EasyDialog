package com.liu.easydialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbsTractDialogView {
    public abstract void setBackgroundResource(int colorResource);
    public abstract View getView(LayoutInflater inflater, ViewGroup parent);
    public abstract void setOnkeyListener(View.OnKeyListener listener);
    public abstract void setDismissClickListener(View.OnClickListener listener);

}
