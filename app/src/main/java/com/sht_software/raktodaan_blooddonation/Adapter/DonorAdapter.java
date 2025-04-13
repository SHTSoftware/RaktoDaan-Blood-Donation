package com.sht_software.raktodaan_blooddonation.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sht_software.raktodaan_blooddonation.Model.Donor;
import com.sht_software.raktodaan_blooddonation.R;

import java.util.List;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorViewHolder> {
    private List<Donor> donorList;


    public DonorAdapter(List<Donor> donorList) {
        this.donorList = donorList;
    }

    @NonNull
    @Override
    public DonorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donor_detail, parent, false);
        return new DonorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorViewHolder holder, int position) {
        Donor donor = donorList.get(position);

        // Set text with proper colors
        holder.tvName.setText("নাম: " + donor.getFullName());
        holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.text_primary));

        holder.tvPhone.setText("মোবাইল: " + formatPhoneNumber(donor.getPhoneNumber()));
        holder.tvPhone.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.text_secondary));

        holder.btnCallDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = holder.tvPhone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                view.getContext().startActivity(intent);
            }
        });

        holder.tvBloodGroup.setText("রক্তের গ্রুপ: " + donor.getBloodGroup());
        holder.tvBloodGroup.setBackgroundResource(R.drawable.blood_group_bg);
        holder.tvBloodGroup.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.text_white));

        holder.tvLastDonate.setText("সর্বশেষ রক্তদান: " + donor.getLastDonate());
        holder.tvLastDonate.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.last_donate_date));

        // Location components with colors
        holder.tvDivision.setText("বিভাগ: " + donor.getDivision());
        holder.tvDivision.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.location_text));

        holder.tvDistrict.setText("জেলা: " + donor.getDistrict());
        holder.tvDistrict.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.location_text));

        holder.tvUpazila.setText("উপজেলা: " + donor.getUpazila());
        holder.tvUpazila.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.location_text));

        holder.tvArea.setText("এলাকা/গ্রাম: " + donor.getAreaVillage());
        holder.tvArea.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.location_text));

        holder.tvRegDate.setText("নিবন্ধনের তারিখ: " + donor.getRegistrationDate());
        holder.tvRegDate.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.location_text));
    }

    private String formatPhoneNumber(String phone) {
        if (phone.length() == 11) {
            return phone.substring(0, 3)  + phone.substring(3, 7)  + phone.substring(7);
        }
        return phone;
    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

    static class DonorViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvBloodGroup, tvLastDonate;
        TextView tvDivision, tvDistrict, tvUpazila, tvArea;
        TextView tvRegDate;
        Button btnCallDonor;

        public DonorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvDonorName);
            tvPhone = itemView.findViewById(R.id.tvDonorPhone);
            tvBloodGroup = itemView.findViewById(R.id.tvBloodGroup);
            tvLastDonate = itemView.findViewById(R.id.tvLastDonate);
            btnCallDonor = itemView.findViewById(R.id.btnCallDonor);
            // Location components
            tvDivision = itemView.findViewById(R.id.tvDivision);
            tvDistrict = itemView.findViewById(R.id.tvDistrict);
            tvUpazila = itemView.findViewById(R.id.tvUpazila);
            tvArea = itemView.findViewById(R.id.tvArea);

            tvRegDate = itemView.findViewById(R.id.tvRegDate);
        }
    }
}