# SwitchView

![](https://github.com/iielse/SwitchButton/blob/master/previews/12345.png) ![](https://github.com/iielse/SwitchButton/blob/master/previews/23456.png)
![](https://github.com/iielse/SwitchButton/blob/master/previews/34567.png)
![](https://github.com/iielse/SwitchButton/blob/master/previews/45678.png)

## Download
[DemoApp.apk](https://github.com/iielse/SwitchButton/blob/master/previews/app-debug.apk)

## Usage
1. add library

`compile 'ch.ielse:switchbutton:1.0.1'`

2. xml
```
<ch.ielse.view.SwitchView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```
3. activity

![](https://github.com/iielse/SwitchButton/blob/master/previews/a.gif)

```
switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpened = switchView.isOpened();
            }
        });
```
or

![](https://github.com/iielse/SwitchButton/blob/master/previews/b.gif)
![](https://github.com/iielse/SwitchButton/blob/master/previews/c.gif)

```
switchView.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true); // or false
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false); // or true
            }
        });
```

## Attributes
| attr | description |
|:---|:---|
| hasShadow | 是否显示按钮阴影 |
| primaryColor | 开启状态背景色 |
| primaryColorDark| 开启状态按钮描边色 |
| offColor | 关闭状态描边色 |
| offColorDark | 关闭状态按钮描边色 |
| shadowColor | 按钮阴影色 |
| ratioAspect | 按钮宽高形状比率(0,1] 不推荐大幅度调整 |
| isOpened | 初始化默认状态 |



## Appreciation
* [aigeStudio](http://blog.csdn.net/aigestudio)

##Others
hope you like my work. `Star` support me a lot. thanks
