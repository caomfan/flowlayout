package com.mophan.imooc_flowlayout2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mophan.imooc_flowlayout2.R;

import java.util.List;

/**
 * Create by CMF on 2019/9/1.
 */
public class FlexBoxLayoutManagerAdapter extends RecyclerView.Adapter<FlexBoxLayoutManagerAdapter.ViewHolder> {

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public FlexBoxLayoutManagerAdapter(Context context,List<String> datas){
        mContext=context;
        mDatas=datas;
        mInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_tag,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTag.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTag=itemView.findViewById(R.id.tv);
        }
    }
}
