package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Buddhiprabha Erabadda
 *
 * Steps in Reporting
 */
public class Steps extends Activity {

    @Bind(R.id.steps_recycler)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_steps);
        ButterKnife.bind(this);
        toolbar.setTitle(getResources().getString(R.string.reporting_steps));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        List<Spanned> strings =  new ArrayList<>();
        strings.add(Html.fromHtml(getResources().getString(R.string.reporting_step1)));
        strings.add(Html.fromHtml(getResources().getString(R.string.reporting_step2)));
        strings.add(Html.fromHtml(getResources().getString(R.string.reporting_step3)));
        strings.add(Html.fromHtml(getResources().getString(R.string.reporting_step4)));
        strings.add(Html.fromHtml(getResources().getString(R.string.reporting_step5)));
        strings.add(Html.fromHtml(getResources().getString(R.string.reporting_step6)));
        StepsAdapter stepsAdapter = new StepsAdapter(strings,this);
        recyclerView.setAdapter(stepsAdapter);

    }

    class StepsAdapter extends RecyclerView.Adapter<StepsVh>{

        List<Spanned> steps;
        Context context;

        public StepsAdapter(List<Spanned> steps, Context context) {
            this.steps = steps;
            this.context = context;
        }

        @Override
        public StepsVh onCreateViewHolder(ViewGroup parent, int viewType) {
            final ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_recycler_item, parent, false);
            return new StepsVh(view);
        }

        @Override
        public void onBindViewHolder(StepsVh holder, int position) {
            holder.setData(steps.get(position));
        }

        @Override
        public int getItemCount() {
            return steps.size();
        }
    }

    class StepsVh extends RecyclerView.ViewHolder{

        @Bind(R.id.tv)
        TextView textView;

        public StepsVh(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void setData(Spanned s){
            textView.setText(s);
        }
    }

}
