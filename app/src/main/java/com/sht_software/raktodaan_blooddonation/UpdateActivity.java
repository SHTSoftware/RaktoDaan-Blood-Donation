package com.sht_software.raktodaan_blooddonation;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sht_software.raktodaan_blooddonation.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    public static ActivityUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Set initial button colors
        binding.btnDonationDate.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_red));
        binding.btnAddress.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));

        binding.btnDonationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show donation layout, hide address layout
                binding.layoutLastDonate.setVisibility(View.VISIBLE);
                binding.layoutAddress.setVisibility(View.GONE);

                // Update button colors
                binding.btnDonationDate.setBackgroundColor(ContextCompat.getColor(UpdateActivity.this, R.color.primary_red));
                binding.btnAddress.setBackgroundColor(ContextCompat.getColor(UpdateActivity.this, R.color.gray));
            }
        });

        binding.btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show address layout, hide donation layout
                binding.layoutLastDonate.setVisibility(View.GONE);
                binding.layoutAddress.setVisibility(View.VISIBLE);

                // Update button colors
                binding.btnDonationDate.setBackgroundColor(ContextCompat.getColor(UpdateActivity.this, R.color.gray));
                binding.btnAddress.setBackgroundColor(ContextCompat.getColor(UpdateActivity.this, R.color.primary_red));
            }
        });

        // You can also add your other initialization code here
        // For example, setting up the date picker for lastDonateEditText
        // or initializing the spinners
    }
}