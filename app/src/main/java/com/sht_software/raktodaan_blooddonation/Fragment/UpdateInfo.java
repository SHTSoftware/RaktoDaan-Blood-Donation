package com.sht_software.raktodaan_blooddonation.Fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sht_software.raktodaan_blooddonation.AboutUs;
import com.sht_software.raktodaan_blooddonation.R;
import com.sht_software.raktodaan_blooddonation.WebView;
import com.sht_software.raktodaan_blooddonation.databinding.FragmentUpdateInfoBinding;

public class UpdateInfo extends Fragment {

    private FragmentUpdateInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateInfoBinding.inflate(inflater, container, false);

        binding.aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutUs.class));
            }
        });

        binding.privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView.url = "https://sites.google.com/view/sht-kabinnama";
                startActivity(new Intent(getActivity(), WebView.class));
            }
        });

        binding.ourMoreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String developerName = "https://play.google.com/store/apps/dev?id=7635698146769202465";
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(""+developerName)));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(""+developerName)));
                }
            }
        });

        binding.rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return binding.getRoot();
    }
}