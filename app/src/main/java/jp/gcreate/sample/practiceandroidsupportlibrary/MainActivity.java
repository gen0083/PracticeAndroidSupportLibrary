package jp.gcreate.sample.practiceandroidsupportlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import jp.gcreate.sample.practiceandroidsupportlibrary.activities.AppBarFlexibleSpaceWithImage;

public class MainActivity extends AppCompatActivity {
    private List<ActivityListData> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mData = new ArrayList<ActivityListData>( );
        mData.add(new ActivityListData("AppBarFlexibleSpaceWithImage",
                "AppBar with image that collapsing by contents scrolled."));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SampleAdapter(this, mData));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class SampleViewHolder extends RecyclerView.ViewHolder{
        static final int LAYOUT_ID = R.layout.component_three_line_list;
        public final TextView title;
        public final TextView description;

        public SampleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text);
            description = (TextView) itemView.findViewById(R.id.secondary_text);
        }
    }

    private static class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder>{
        private final LayoutInflater mInflater;
        private List<ActivityListData> mData;

        public SampleAdapter(Context context, List<ActivityListData> data){
            mInflater = LayoutInflater.from(context);
            mData = data;
        }

        @Override
        public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            SampleViewHolder viewHolder = new SampleViewHolder(
                    mInflater.inflate(SampleViewHolder.LAYOUT_ID, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SampleViewHolder holder, int position) {
            final ActivityListData data = mData.get(position);
            holder.title.setText(data.getActivityName());
            holder.description.setText(data.getDescription());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Class<?> clazz = Class.forName(getClass().getPackage() + ".activities." + data.getActivityName());
                        Method method = clazz.getMethod("createIntent", null);
                        Intent intent = (Intent) method.invoke(null, null);
                        Log.d("test", "intent is " + intent);
//                        MainActivity.this.startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

    }

    private static class ActivityListData{
        private final String activityName;
        private final String description;

        public ActivityListData(String title, String description){
            this.activityName = title;
            this.description = description;
        }

        public String getActivityName(){
            return activityName;
        }

        public String getDescription(){
            return description;
        }
    }
}
