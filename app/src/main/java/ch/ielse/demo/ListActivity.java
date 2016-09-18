package ch.ielse.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.ielse.view.SwitchView;

public class ListActivity extends AppCompatActivity {

    private ListView vList;
    private ItemListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListActivity.this.finish();
            }
        });


        vList = (ListView) findViewById(R.id.v_list);
        vList.setAdapter(adapter = new ItemListAdapter());

        adapter.set();
    }

    private static class ItemListAdapter extends BaseAdapter {

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

        private class ViewHolder implements View.OnClickListener {
            View itemView;
            TextView tTitle;
            SwitchView vSwitch;
            ItemObject itemObject;
            int pos;

            ViewHolder(View view) {
                this.itemView = view;
            }

            @Override
            public void onClick(View v) {
                if (v == vSwitch) {
                    itemObject.isOpened = vSwitch.isOpened();
                    Toast.makeText(v.getContext().getApplicationContext(), "vSwitch[" + pos + "] change to " + itemObject.isOpened, Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_content, parent, false);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            if (viewHolder == null) {
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
                viewHolder.tTitle = (TextView) convertView.findViewById(R.id.t_title);
                viewHolder.vSwitch = (SwitchView) convertView.findViewById(R.id.v_switch);
                viewHolder.vSwitch.setOnClickListener(viewHolder);
            }

            viewHolder.pos = position;
            viewHolder.itemObject = (ItemObject) getItem(position);
            viewHolder.tTitle.setText(viewHolder.itemObject.title);
            viewHolder.vSwitch.setOpened(viewHolder.itemObject.isOpened);

            viewHolder.itemView.setBackgroundColor(position % 2 != 0 ? 0xFFFFFFFF : 0xFFEEEFF3);
            return convertView;
        }
    }
}
