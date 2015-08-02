# SwitchView

##用来干什么?
给用户点击开关按钮的操作带来更好的视觉体验

##如何去使用
在xml中：
```
<yourpackagename.SwitchView
        android:id="@+id/view_switch"
        android:layout_width="60dip"
        android:layout_height="wrap_content" />
```
注：调整属性layout_width的值来确定SwitchView的大小，推荐使用60dip左右的值。

引入之后，SwitchView在界面上被点击将展现这样的效果：

![image](https://github.com/iielse/SwitchView/blob/HEAD/effect/1.gif)

你可以通过在代码中调用`setState()` 设置当前的状态。

也可以根据`getState()` 获得当前的状态。通常情况下值为`STATE_SWITCH_ON`或者`STATE_SWITCH_OFF`

##高级用法
* 支持开关的'延时操作'。就像这样:

![image](https://github.com/iielse/SwitchView/blob/HEAD/effect/2.gif)

为了达到这样的效果，仅仅需要在activity中：
```
final SwitchView viewSwitch = (SwitchView) findViewById(R.id.view_switch);
viewSwitch.setState(true); // 设置初始状态。true为开;false为关[默认]。
viewSwitch.setOnStateChangedListener(new OnStateChangedListener() {
	@Override public void toggleToOn() {
		// 原本为关闭的状态，被点击后
        // 执行一些耗时的业务逻辑操作
        viewSwitch.postDelayed(new Runnable() {
    		@Override public void run() {
				viewSwitch.toggleSwitch(true); //以动画效果切换到打开的状态
			}, 1000);
	}
	@Override public void toggleToOff() {
		// 原本为打开的状态，被点击后
		viewSwitch.toggleSwitch(false);
	}
});
```

`toggleSwitch()` 设置新的状态并执行过渡动画。

`setOnStateChangedListener()` 监听当状态变化时。

* 支持开关的'回滚操作'。就像这样:

![image](https://github.com/iielse/SwitchView/blob/HEAD/effect/3.gif)

在上述`toggleToOn()`代码中调用`viewSwitich.toggleSwtich(false);`即可。

##优点
 只涉及到一个类文件，异常容易集成进入你的项目。

##更加详细的源码分析和实现思路讲解
[请戳这里](http://blog.csdn.net/bfbx5173/article/details/45191147) 


##感激
感谢这位朋友

* [aigeStudio](http://blog.csdn.net/aigestudio) 

##其它
希望你喜欢我的作品。`Star`是对我的最大支持. 谢谢
