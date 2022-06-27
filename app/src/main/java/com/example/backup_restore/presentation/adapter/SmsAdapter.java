package com.example.backup_restore.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backup_restore.databinding.ItemSmsBinding;
import com.example.backup_restore.model.Sms;

import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.SmsViewHolder> {
    List<Sms> lstSms;

    public SmsAdapter(List<Sms> lstSms) {
        if (this.lstSms != null && this.lstSms.size() > 0) {
            this.lstSms.clear();
        }
        this.lstSms = lstSms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSmsBinding binding = ItemSmsBinding.inflate(layoutInflater, parent, false);
        return new SmsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SmsViewHolder holder, int position) {
        holder.bind(lstSms.get(position));
    }

    @Override
    public int getItemCount() {
        if (lstSms.size() > 0) {
            return lstSms.size();
        }
        return 0;
    }

    public class SmsViewHolder extends RecyclerView.ViewHolder {
        ItemSmsBinding mBinding;

        public SmsViewHolder(@NonNull ItemSmsBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        void bind(Sms sms) {
            mBinding.setSms(sms);
        }
    }
}
