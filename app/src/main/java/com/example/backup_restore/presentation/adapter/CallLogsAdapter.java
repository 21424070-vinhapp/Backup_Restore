package com.example.backup_restore.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backup_restore.databinding.ItemCallLogsBinding;
import com.example.backup_restore.model.CallLogs;

import java.util.List;

public class CallLogsAdapter extends RecyclerView.Adapter<CallLogsAdapter.CallLogsViewHolder> {
    List<CallLogs> lstCallLog;

    public CallLogsAdapter(List<CallLogs> lstCallLog) {
        if (this.lstCallLog != null && this.lstCallLog.size() > 0) {
            this.lstCallLog.clear();
        }
        this.lstCallLog = lstCallLog;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CallLogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        ItemCallLogsBinding itemCallLogsBinding=ItemCallLogsBinding.inflate(layoutInflater,parent,false);
        return new CallLogsViewHolder(itemCallLogsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogsViewHolder holder, int position) {
        holder.bind(lstCallLog.get(position));
    }

    @Override
    public int getItemCount() {
        if(lstCallLog.size()>0)
        {
            return lstCallLog.size();
        }
        return 0;
    }

    public class CallLogsViewHolder extends RecyclerView.ViewHolder {
        ItemCallLogsBinding mBinding;

        public CallLogsViewHolder(@NonNull ItemCallLogsBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        public void bind(CallLogs callLogs)
        {
            mBinding.setCallings(callLogs);
        }
    }
}
