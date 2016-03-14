# EasyDialog
###EasyDialog两种模式 仿QQ退出向上菜单，自定义向上菜单
===========================
<img src='https://github.com/hellosliu/EasyDialog/blob/master/images/menu.gif' height='400'/> 
<img src='https://github.com/hellosliu/EasyDialog/blob/master/images/customer.gif' height='400'/>

### Usage
使用build创建dialog
#####仿QQ退出时的向上菜单
```java
//添加菜单显示项
List<String> menu = new ArrayList<String>();
menu.add("版本更新");
menu.add("反馈");
menu.add("退出");

MenuDialogView menuDialogView = new MenuDialogView();
//菜单点击listener
OnMenuClickListener onMenuClickListener = new OnMenuClickListener() {
  @Override
  public void onClick(int position, String menuItem) {
    Log.d("TAG", "NNNN=====>position:" + position + "====>menuItem:" + menuItem);
  }
};
//创建EasyDialog
EasyDialog easyDialog = EasyDialog.newBuilder(this)
  .setDialogView(menuDialogView)
  .setMenuNames(menu)
  .setOnMenuClickListener(onMenuClickListener)
  //.setShowCanCel(false)         //设置是否显示取消按钮，默认显示
  //.setCancelText("我要取消")     //设置取消按钮文字
  //.setMenuTextSize(22)          //设置菜单文字大小
  //.setMenuTextColor(Color.WHITE)    //设置菜单文字颜色
  //.setMenuBackground(Color.BLUE)    //设置菜单背景颜色
  //.setCancelBackground(Color.RED)   //设置取消按钮背景颜色
  .create();
easyDialog.show();
```

#####自定义向上菜单
```java
View view = LayoutInflater.from(this).inflate(R.layout.customer_dialog, null);
DialogView dialogView = new DialogView(view);
easyDialog = EasyDialog.newBuilder(this)
            .setDialogView(dialogView).create();
easyDialog.show();

//隐藏对话框
easyDialog.dismiss();
```
