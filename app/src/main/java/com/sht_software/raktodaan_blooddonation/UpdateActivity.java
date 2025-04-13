package com.sht_software.raktodaan_blooddonation;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.sht_software.raktodaan_blooddonation.databinding.ActivityUpdateBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        binding.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateActivity.this, "Update date", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateActivity.this, "Update Address", Toast.LENGTH_SHORT).show();
            }
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

        binding.lastDonateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        binding.lastDonateInputLayout.setEndIconOnClickListener(view -> {
            showDatePicker();
        });

    } // On Create bundle

    private void showDatePicker() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            // Convert the selected timestamp to a readable date
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            String formattedDate = sdf.format(new Date(selection));

            // Set the date in the EditText
            ((TextInputEditText) UpdateActivity.this.findViewById(R.id.lastDonateEditText)).setText(formattedDate);
        });

        datePicker.show(UpdateActivity.this.getSupportFragmentManager(), "DATE_PICKER");
    }
}