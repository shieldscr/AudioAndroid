package com.shields.audioandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.shields.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoopListViewAdapter extends RecyclerView.Adapter<LoopListViewAdapter.LoopListViewHolder> {
    private ArrayList dataset;
    private Context context;
    private int lastPosition = -1;

    public LoopListViewAdapter(ArrayList dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public LoopListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loop_text_view, null);
        LoopListViewHolder mh = new LoopListViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(LoopListViewHolder holder, int position) {
        holder.loopTitle.setText(dataset.get(position).toString());
        setAnimation(holder.loopTitle, position);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public String getLoopListItem(int index) {
        return dataset.get(index).toString();
    }

    public void removeLoopListItem(int index) {
        dataset.remove(index);
        this.notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    protected class LoopListViewHolder extends RecyclerView.ViewHolder {
        protected TextView loopTitle;

        public LoopListViewHolder(View view) {
            super(view);
            this.loopTitle = (TextView) view.findViewById(R.id.loop_text_view);
        }
    }
}
