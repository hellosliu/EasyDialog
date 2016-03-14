package com.liu.easydialog;

import android.graphics.drawable.GradientDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.liu.easydialog.listener.OnMenuClickListener;
import com.liu.easydialog.utils.StringUtils;
import com.liu.easydialog.widget.NRollListView;

import java.util.List;


public class MenuDialogView extends AbsTractDialogView {

    private static final int INVALID = -1;
    private static final int INVALID_COLOR = 0;

    View.OnKeyListener onKeyListener;

    private MenuAdapter menuAdapter;

    private List<String> menuNames;
    private boolean isShowCanCel = true;
    private String cancelText;
    private float menuTextSize = INVALID;
    private int menuTextColor = INVALID_COLOR;
    private int menuBackground = INVALID_COLOR;
    private int cancelBackground = INVALID_COLOR;

    private View.OnClickListener setDismissClickListener;
    private OnMenuClickListener onMenuClickListener;


    public MenuDialogView(){}

    public void setMenuNames(List<String> menuNames) {
        this.menuNames = menuNames;
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public void setIsShowCanCel(boolean isShowCanCel) {
        this.isShowCanCel = isShowCanCel;
    }

    public void setMenuTextSize(float menuTextSize) {
        this.menuTextSize = menuTextSize;
    }

    public void setMenuTextColor(int menuTextColor) {
        this.menuTextColor = menuTextColor;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public void setMenuBackground(int menuBackground) {
        this.menuBackground = menuBackground;
    }

    public void setCancelBackground(int cancelBackground) {
        this.cancelBackground = cancelBackground;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_menu, parent, false);

        menuAdapter = new MenuAdapter(inflater, menuNames);
        menuAdapter.setMenuTextSize(menuTextSize);
        menuAdapter.setMenuTextColor(menuTextColor);
        menuAdapter.setMenuBackground(menuBackground);
        NRollListView listView = (NRollListView) view.findViewById(R.id.lv_menu);
        listView.setAdapter(menuAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != onMenuClickListener) {
                    onMenuClickListener.onClick(position, menuNames.get(position));
                }
                setDismissClickListener.onClick(view);
            }
        });

        listView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (null != onKeyListener) {
                    return onKeyListener.onKey(v, keyCode, event);
                }
                return false;
            }
        });

        final TextView cancelTextView = (TextView) view.findViewById(R.id.tv_cancel);
        if(menuTextSize != INVALID){
            cancelTextView.setTextSize(menuTextSize);
        }
        if(menuTextColor != INVALID_COLOR){
            cancelTextView.setTextColor(menuTextColor);
        }
        if(menuBackground != INVALID_COLOR){
            GradientDrawable drawable = (GradientDrawable)cancelTextView.getBackground();
            drawable.setColor(menuBackground);
        }
        if(cancelBackground != INVALID_COLOR){
            GradientDrawable drawable = (GradientDrawable)cancelTextView.getBackground();
            drawable.setColor(cancelBackground);
        }

        if(StringUtils.isNotBlank(cancelText)){
            cancelTextView.setText(cancelText);
        }
        if(isShowCanCel){
            cancelTextView.setVisibility(View.VISIBLE);
        }else {
            cancelTextView.setVisibility(View.GONE);
        }
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onMenuClickListener) {
                    onMenuClickListener.onClick(-1, cancelTextView.getText().toString());
                }
                setDismissClickListener.onClick(cancelTextView);
            }
        });

        return view;
    }

    @Override
    public void setBackgroundResource(int colorResource) {

    }


    @Override
    public void setOnkeyListener(View.OnKeyListener listener) {
        this.onKeyListener = listener;
    }

    @Override
    public void setDismissClickListener(View.OnClickListener listener) {
        setDismissClickListener = listener;
    }


}
