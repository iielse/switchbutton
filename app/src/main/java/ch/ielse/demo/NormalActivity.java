package ch.ielse.demo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import ch.ielse.view.SwitchView;

public class NormalActivity extends AppCompatActivity {

    static final String SETTINGS_LAUNCHER_VOICE = "settings_launcher_voice";
    static final String SETTINGS_PUSH_ABLE = "settings_push_able";

    private SwitchView vLauncherVoice;

    private ImageView iLoadPush;
    private SwitchView vPushAble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalActivity.this.finish();
            }
        });

        vLauncherVoice = (SwitchView) findViewById(R.id.v_launcher_voice);
        vPushAble = (SwitchView) findViewById(R.id.v_push_able);
        iLoadPush = (ImageView) findViewById(R.id.i_load_push);

        boolean isLauncherVoice = SpfsUtils.readBoolean(this, SETTINGS_LAUNCHER_VOICE, true);
        vLauncherVoice.setOpened(isLauncherVoice);
        vLauncherVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "启动问候音切换到:" + vLauncherVoice.isOpened(), Toast.LENGTH_SHORT).show();
                SpfsUtils.write(NormalActivity.this, SETTINGS_LAUNCHER_VOICE, vLauncherVoice.isOpened());
            }
        });

        boolean isPushAble = SpfsUtils.readBoolean(this, SETTINGS_PUSH_ABLE, true);
        vPushAble.setOpened(isPushAble);
        vPushAble.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                iLoadPush.setVisibility(View.VISIBLE);
                ((AnimationDrawable) iLoadPush.getBackground()).start();

                // 在此处被触发后，需要调用者根据业务逻辑确定SwitchView下一步动作。
                new PushServiceTask(new PushServiceStateChangedCallback() {
                    @Override
                    public void onPushServiceStateChanged(int state) {
                        ((AnimationDrawable) iLoadPush.getBackground()).stop();
                        iLoadPush.setVisibility(View.GONE);

                        if (state == PushServiceStateChangedCallback.STATE_LAUNCHER_SUCCESS) {
                            // 告诉SwitchView 更新UI
                            Toast.makeText(getApplicationContext(), "推送服务开启完成", Toast.LENGTH_SHORT).show();
                            vPushAble.toggleSwitch(true);
                            SpfsUtils.write(NormalActivity.this, SETTINGS_PUSH_ABLE, true);
                        } else {
                            // 开启失败
                            vPushAble.toggleSwitch(false);
                            Toast.makeText(getApplicationContext(), "推送服务开启失败，请稍后重新尝试", Toast.LENGTH_SHORT).show();
                            SpfsUtils.write(NormalActivity.this, SETTINGS_PUSH_ABLE, false);
                        }
                    }
                }).launch();
            }

            @Override
            public void toggleToOff(SwitchView view) {
                iLoadPush.setVisibility(View.VISIBLE);
                ((AnimationDrawable) iLoadPush.getBackground()).start();

                new PushServiceTask(new PushServiceStateChangedCallback() {
                    @Override
                    public void onPushServiceStateChanged(int state) {
                        ((AnimationDrawable) iLoadPush.getBackground()).stop();
                        iLoadPush.setVisibility(View.GONE);

                        if (state == PushServiceStateChangedCallback.STATE_RELEASE_AND_CLOSE) {
                            // 告诉SwitchView 更新UI
                            vPushAble.toggleSwitch(false);
                            SpfsUtils.write(NormalActivity.this, SETTINGS_PUSH_ABLE, false);
                        } else {
                            Toast.makeText(getApplicationContext(), "未知异常 关闭失败", Toast.LENGTH_SHORT).show();
                            vPushAble.toggleSwitch(true);
                            SpfsUtils.write(NormalActivity.this, SETTINGS_PUSH_ABLE, true);
                        }
                    }
                }).close();
            }
        });
    }

    interface PushServiceStateChangedCallback {
        int STATE_RELEASE_AND_CLOSE = 99;
        int STATE_LAUNCHER_FAILURE = 98;
        int STATE_LAUNCHER_SUCCESS = 1;

        void onPushServiceStateChanged(int state);
    }


    class PushServiceTask {
        PushServiceStateChangedCallback c;
        Handler handler = new Handler(Looper.getMainLooper());

        PushServiceTask(@NonNull PushServiceStateChangedCallback callback) {
            c = callback;
        }

        public void close() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            c.onPushServiceStateChanged(PushServiceStateChangedCallback.STATE_RELEASE_AND_CLOSE);
                        }
                    });
                }
            }).start();
        }

        public void launch() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (System.currentTimeMillis() % 2 == 0) {
                                c.onPushServiceStateChanged(PushServiceStateChangedCallback.STATE_LAUNCHER_FAILURE);
                            } else {
                                c.onPushServiceStateChanged(PushServiceStateChangedCallback.STATE_LAUNCHER_SUCCESS);
                            }
                        }
                    });

                }
            }).start();
        }
    }
}
