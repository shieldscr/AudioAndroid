package com.shields.audioandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shields.R;

import java.util.ArrayList;

public class LoopListViewAdapter extends RecyclerView.Adapter<LoopListViewAdapter.LoopListViewHolder> {
    private ArrayList dataset;

    public LoopListViewAdapter(ArrayList myDataset) {
        dataset = myDataset;
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
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public String getLoopListItem(int index) {
        return dataset.get(index).toString();
    }

    protected class LoopListViewHolder extends RecyclerView.ViewHolder {
        protected TextView loopTitle;

        public LoopListViewHolder(View view) {
            super(view);
            this.loopTitle = (TextView) view.findViewById(R.id.loop_text_view);
        }
    }
}
