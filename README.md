# SwitchView

![](https://github.com/iielse/SwitchButton/blob/master/previews/12345.png) ![](https://github.com/iielse/SwitchButton/blob/master/previews/23456.png)
![](https://github.com/iielse/SwitchButton/blob/master/previews/34567.png)
![](https://github.com/iielse/SwitchButton/blob/master/previews/45678.png)

## Download
[DemoApp.apk](https://github.com/iielse/SwitchButton/blob/master/previews/app-debug.apk)

## Usage
1. Include the library as local library project.

`compile 'ch.ielse:switchbutton:1.0.0'`

2. in your layout.xml
```
<ch.ielse.view.SwitchView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```
3.in your activity
```
switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpened = switchView.isOpened();
            }
        });
```
or
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


## Appreciation
* [aigeStudio](http://blog.csdn.net/aigestudio)

## Contact
QQ Group : 274306954

##Others
hope you like my work. `Star` support me a lot. thanks
