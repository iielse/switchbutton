package ch.ielse.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ch.ielse.view.SwitchView;

public class APIIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_intro);

        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIIntroActivity.this.finish();
            }
        });

        final SwitchView vSwitch1 = (SwitchView) findViewById(R.id.v_switch_1);
        findViewById(R.id.b_1_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch1.setOpened(true);
            }
        });
        findViewById(R.id.b_1_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch1.setOpened(false);
            }
        });
        findViewById(R.id.b_1_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch1.toggleSwitch(true);
            }
        });

        findViewById(R.id.b_1_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch1.toggleSwitch(false);
            }
        });

        final SwitchView vSwitch2 = (SwitchView) findViewById(R.id.v_switch_2);
        findViewById(R.id.b_2_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch2.setShadow(true);
            }
        });
        findViewById(R.id.b_2_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch2.setShadow(false);
            }
        });

        final SwitchView vSwitch3 = (SwitchView) findViewById(R.id.v_switch_3);
        findViewById(R.id.b_3_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch3.setColor(0xFF4FC3F7, 0x4FC3F7);
            }
        });
        findViewById(R.id.b_3_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch3.setColor(0xFF546E7A, 0xFF455A64);
            }
        });
        findViewById(R.id.b_3_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vSwitch3.setColor(0xFFFFC400, 0xFFFFAB00);
            }
        });
    }
}
