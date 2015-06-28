package com.shields.audioandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shields.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LoopListViewAdapter extends RecyclerView.Adapter {

    ArrayList<String> dataSet;
    private LayoutInflater mInflater;

    public LoopListViewAdapter(ArrayList<String> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LoopListViewHolder(
                mInflater.inflate(R.layout.main, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
//        viewHolder.s.setText(mDataset[position]);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    private class LoopListViewHolder extends RecyclerView.ViewHolder {
        public LoopListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
