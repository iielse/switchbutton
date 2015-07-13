# SwitchView

![image](https://github.com/iielse/SwitchView/blob/HEAD/effect.gif)

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
注：调整属性layout_width的值来确定按钮的大小，推荐使用60dip左右的值。

在activity中：
```
final SwitchView viewSwitch = (SwitchView) findViewById(R.id.view_switch);
viewSwitch.setState(true); // 设置初始状态。true为开;false为关。不带有切换动画
viewSwitch.setOnStateChangedListener(new OnStateChangedListener() {
	@Override public void toggleToOn() {
		// 原本为关闭的状态，被点击后
		viewSwitch.toggleSwitch(true); //以动画效果切换到打开的状态
	}
	@Override public void toggleToOff() {
		// 原本为打开的状态，被点击后
		viewSwitch.toggleSwitch(false);
	}
});
```

##常用到的接口
`getState()` 获得当前的状态。通常情况下值为`STATE_SWITCH_ON`或者`STATE_SWITCH_OFF`

`setState()` 设置当前的状态。  

`toggleSwitch()` 设置当前的状态并显示过渡动画。

`setOnStateChangedListener()` 监听当状态变化时。

##优点
* 异常容易集成进入你的项目。
* 支持开关操作的失败后的'回滚'。
    * 比如说你将上述代码的`toggleToOn()`中逻辑修改为`viewSwitch.toggleSwitch(false);`
    那么你将会获得试图将按钮打开但是不成功的效果。
* 接口调用简单清爽。

##更加详细的源码分析
[请戳这里](http://blog.csdn.net/bfbx5173/article/details/45191147) 

##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流  
* QQ: 517309507

##感激
感谢这位朋友

* [aigeStudio](http://blog.csdn.net/aigestudio) 

##其它
在兴趣的驱动下,写一个`免费`的东西，有欣喜，也还有汗水，希望你喜欢我的作品，同时也能支持一下。
