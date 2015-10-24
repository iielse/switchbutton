# SwitchView

##用来干什么?  What is it used for?
给用户点击开关按钮的操作带来更好的视觉体验  
For bringing a better visual experience when clicking the on/off button

##如何去使用 How to use
在xml中：within xml

```
<yourpackagename.SwitchView
        android:id="@+id/view_switch"
        android:layout_width="60dip"
        android:layout_height="wrap_content" />
```
注：调整属性layout_width的值来确定SwitchView的大小，推荐使用60dip左右的值。

note：adjust the volume of width under the attribut subject to decide the size, recommending size shall be 60dip up or down. 

引入之后，SwitchView在界面上被点击将展现这样的效果：

After inserting, the effect of SwitchView will present it like followings:

![image](https://github.com/iielse/SwitchView/blob/HEAD/effect/1.gif)

你可以通过在代码中调用`setState()` 设置当前的状态。

you can set up the current status by invoking `setState()` within the codes

也可以根据`getState()` 获得当前的状态。通常情况下值为`STATE_SWITCH_ON`或者`STATE_SWITCH_OFF`

you can also acquire the status according to `getState()`.   Generally, the volume shall be `STATE_SWITCH_ON` or `STATE_SWITCH_OFF`.

##高级用法 Advanced usage
* 支持开关的'延时操作'。就像这样: support the delay operation of the switch. like this:

![image](https://github.com/iielse/SwitchView/blob/HEAD/effect/2.gif)

为了达到这样的效果，仅仅需要在activity中：
in order to get the wanted effect, you need open the activity:
```
final SwitchView viewSwitch = (SwitchView) findViewById(R.id.view_switch);
// 设置初始状态。true为开;false为关[默认]。set up original status. true for open and false for close[default]
viewSwitch.setState(true); 
viewSwitch.setOnStateChangedListener(new OnStateChangedListener() {
    @Override public void toggleToOn(View view) {
    	// 原本为关闭的状态，被点击后 originally present close status after clicking
		
        // 执行一些耗时的业务逻辑操作 implement some time-consuming logic operation
        viewSwitch.postDelayed(new Runnable() {
    		@Override public void run() {
				viewSwitch.toggleSwitch(true); //以动画效果切换到打开的状态 through changing animation effect to open status
			}, 1000);
	}
	@Override public void toggleToOff(View view) {
		// 原本为打开的状态，被点击后 originally present the status of open after clicking
		viewSwitch.toggleSwitch(false);
	}
});
```

`toggleSwitch()` 设置新的状态并执行过渡动画。for seting up new status and implement transition animation.

`setOnStateChangedListener()` 监听当状态变化时。monitoring change of status.

* 支持开关的'回滚操作'。就像这样: support the rolling back action, like this:

![image](https://github.com/iielse/SwitchView/blob/HEAD/effect/3.gif)

在上述`toggleToOn()`代码中调用`viewSwitich.toggleSwtich(false);`即可。

invoking `viewSwitch.toggleSwitch(false)` in the `toggleToOn()` code


##优点 Advantages
只涉及到一个类文件，异常容易集成进入你的项目。
only one class file, very easy to integrate into your program 


##更加详细的源码分析和实现思路讲解
[请戳这里](http://blog.csdn.net/bfbx5173/article/details/45191147) 


##感激 Appreciation
感谢这些朋友 Gratitude to all friends involved

* [aigeStudio](http://blog.csdn.net/aigestudio) 
* 太志Dog (Readme翻译提供者)

##其它 Others
希望你喜欢我的作品。`Star`是对我的最大支持. 谢谢

hope you like my work. `Star` support me a lot. thanks
