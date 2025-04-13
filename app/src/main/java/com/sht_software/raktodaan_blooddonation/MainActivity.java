package com.sht_software.raktodaan_blooddonation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.sht_software.raktodaan_blooddonation.Fragment.DonorFragment;
import com.sht_software.raktodaan_blooddonation.Fragment.DonorList;
import com.sht_software.raktodaan_blooddonation.Fragment.UpdateInfo;
import com.sht_software.raktodaan_blooddonation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FTransaction = FM.beginTransaction();
        FTransaction.replace(R.id.frameLayout, new DonorList());
        FTransaction.commit();


        ///////////////////////////// Bottom Navigation ///////////////////////////////////

        binding.searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTab != 1) {

                    FragmentManager FManager = getSupportFragmentManager();
                    FragmentTransaction FTransaction = FManager.beginTransaction();
                    FTransaction.replace(R.id.frameLayout, new DonorList());
                    FTransaction.commit();

                    binding.donorText.setVisibility(View.GONE);
                    binding.updateText.setVisibility(View.GONE);

                    binding.donorLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    binding.updateLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    binding.searchText.setVisibility(View.VISIBLE);
                    binding.searchImage.setImageResource(R.drawable.baseline_manage_search_24);
                    binding.searchLayout.setBackgroundResource(R.drawable.bg_search);

                    // Apply animation
                    binding.searchLayout.startAnimation(createTabScaleAnimation());

                    selectedTab = 1;

                }

            }
        });

        binding.donorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTab != 2) {

                    FragmentManager FManager = getSupportFragmentManager();
                    FragmentTransaction FTransaction = FManager.beginTransaction();
                    FTransaction.replace(R.id.frameLayout, new DonorFragment());
                    FTransaction.commit();

                    binding.searchText.setVisibility(View.GONE);
                    binding.updateText.setVisibility(View.GONE);

                    binding.searchLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    binding.updateLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    binding.donorText.setVisibility(View.VISIBLE);
                    binding.donorImage.setImageResource(R.drawable.baseline_bloodtype_24);
                    binding.donorLayout.setBackgroundResource(R.drawable.bg_donor);

                    // Apply animation
                    binding.donorLayout.startAnimation(createTabScaleAnimation());

                    selectedTab = 2;

                }

            }
        });

        binding.updateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTab != 3) {

                    FragmentManager FManager = getSupportFragmentManager();
                    FragmentTransaction FTransaction = FManager.beginTransaction();
                    FTransaction.replace(R.id.frameLayout, new UpdateInfo());
                    FTransaction.commit();

                    binding.searchText.setVisibility(View.GONE);
                    binding.donorText.setVisibility(View.GONE);

                    binding.searchLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    binding.donorLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    binding.updateText.setVisibility(View.VISIBLE);
                    binding.updateImage.setImageResource(R.drawable.icon_update);
                    binding.updateLayout.setBackgroundResource(R.drawable.bg_update);

                    // Apply animation
                    binding.updateLayout.startAnimation(createTabScaleAnimation());

                    selectedTab = 3;

                }

            }
        });

        ///////////////////////////// Bottom Navigation ///////////////////////////////////


    } // On create Bundle

    // Create a reusable animation to avoid creating new instances each time
    private ScaleAnimation createTabScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.8f, 1.0f, // Start and end scale for X
                1f, 1f,     // Start and end scale for Y (no change)
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X (center)
                Animation.RELATIVE_TO_SELF, 0.5f  // Pivot Y (center)
        );
        scaleAnimation.setDuration(200);
        scaleAnimation.setInterpolator(new OvershootInterpolator(1.0f)); // Adds a nice bounce effect
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }


}