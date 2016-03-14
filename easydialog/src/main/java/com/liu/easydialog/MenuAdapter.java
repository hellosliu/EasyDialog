package com.liu.easydialog;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class MenuAdapter extends BaseAdapter {

    private static final int INVALID = -1;
    private static final int INVALID_COLOR = 0;
    private List<String> menus;
    private LayoutInflater layoutInflater;
    private float menuTextSize = INVALID;
    private int menuTextColor = INVALID_COLOR;
    private int menuBackground = INVALID_COLOR;

    public MenuAdapter(LayoutInflater inflater, List<String> menus) {
        this.menus = menus;
        this.layoutInflater = inflater;
    }

    public float getMenuTextSize() {
        return menuTextSize;
    }

    public void setMenuTextSize(float menuTextSize) {
        this.menuTextSize = menuTextSize;
    }

    public void setMenuTextColor(int menuTextColor) {
        this.menuTextColor = menuTextColor;
    }

    public void setMenuBackground(int menuBackground) {
        this.menuBackground = menuBackground;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(null == convertView){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_menu, parent, false);
            holder.nameView = (TextView) convertView.findViewById(R.id.tv_menu_name);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.requestFocus();
        holder.nameView.setText(menus.get(position));

        if(menuTextSize != INVALID){
            holder.nameView.setTextSize(menuTextSize);
        }
        if(menuTextColor != INVALID_COLOR){
            holder.nameView.setTextColor(menuTextColor);
        }
        if(menuBackground != INVALID_COLOR){
            GradientDrawable drawable = (GradientDrawable)holder.nameView.getBackground();
            drawable.setColor(menuBackground);
        }

        if(position == 0){
            holder.nameView.setBackgroundResource(R.drawable.bg_menu_top);
        }else if(position == (menus.size() - 1)){
            holder.nameView.setBackgroundResource(R.drawable.bg_menu_bottom);
        }
        return convertView;
    }

    private class ViewHolder{
        public TextView nameView;
    }
}
