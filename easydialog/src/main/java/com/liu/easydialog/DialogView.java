package com.liu.easydialog;


import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DialogView extends AbsTractDialogView {

    private static final int INVALID = -1;

    View.OnKeyListener onKeyListener;

    private View customerView;
    private int customerViewResId = INVALID;

    public DialogView(int viewResId) {
        this.customerViewResId = viewResId;
    }

    public DialogView(View view){
        this.customerView = view;
    }

    @Override
    public void setBackgroundResource(int colorResource) {

    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.view_dialog, parent, false);
        ViewGroup contentView = (ViewGroup) view.findViewById(R.id.fl_dialog_content);
        contentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(null != onKeyListener){
                    return  onKeyListener.onKey(v, keyCode, event);
                }
                return false;
            }
        });

        addCustomerContent(inflater, parent, contentView);

        return view;
    }

    private void addCustomerContent(LayoutInflater inflater, ViewGroup parent, ViewGroup contentView){
        if(customerViewResId != -1){
            customerView = inflater.inflate(customerViewResId, parent, false);
        }

        contentView.addView(customerView);
    }


    @Override
    public void setOnkeyListener(View.OnKeyListener listener) {
        this.onKeyListener = listener;
    }

    @Override
    public void setDismissClickListener(View.OnClickListener listener) {

    }


}
