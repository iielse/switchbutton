package com.github.iielse.switchbutton.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.github.iielse.switchbutton.SwitchView;


public class RecyclerActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
    private RecyclerView vRecycler;
    private ItemListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerActivity.this.finish();
            }
        });


        vRecycler = (RecyclerView) findViewById(R.id.v_recycler);
        vRecycler.setLayoutManager(layoutManager = new LinearLayoutManager(vRecycler.getContext()));
        vRecycler.setAdapter(adapter = new ItemListAdapter());

        adapter.set();
    }

    private static class ItemListAdapter extends RecyclerView.Adapter {

        List<ItemObject> mDataList = new ArrayList<ItemObject>();

        public void set() {
            mDataList.clear();
            for (int i = 0; i < 50; i++) {
                ItemObject itemObject = new ItemObject();
                itemObject.title = "item[" + i + "]";
                mDataList.add(itemObject);
            }

            notifyDataSetChanged();
        }

        private class ViewHolder extends RecyclerView.ViewHolder implements SwitchView.OnStateChangedListener {
            TextView tTitle;
            SwitchView vSwitch;
            ItemObject itemObject;
            int pos;

            ViewHolder(View view) {
                super(view);
                tTitle = (TextView) view.findViewById(R.id.t_title);
                vSwitch = (SwitchView) view.findViewById(R.id.v_switch);
                vSwitch.setOnStateChangedListener(this);
            }

            @Override
            public void toggleToOn(final SwitchView view) {
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.toggleSwitch(itemObject.isOpened = true);
                    }
                }, 400);
            }

            @Override
            public void toggleToOff(final SwitchView view) {
                view.toggleSwitch(itemObject.isOpened = false);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_content, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.pos = position;
            viewHolder.itemObject = mDataList.get(position);
            viewHolder.tTitle.setText(viewHolder.itemObject.title);
            viewHolder.vSwitch.setOpened(viewHolder.itemObject.isOpened);
            viewHolder.itemView.setBackgroundColor(position % 2 != 0 ? 0xFFFFFFFF : 0xFFEEEFF3);
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }
}
