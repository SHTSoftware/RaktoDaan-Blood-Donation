package com.sht_software.raktodaan_blooddonation.Fragment;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sht_software.raktodaan_blooddonation.AboutUs;
import com.sht_software.raktodaan_blooddonation.R;
import com.sht_software.raktodaan_blooddonation.UpdateActivity;
import com.sht_software.raktodaan_blooddonation.WebView;
import com.sht_software.raktodaan_blooddonation.databinding.FragmentUpdateInfoBinding;

public class UpdateInfo extends Fragment {

    private FragmentUpdateInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateInfoBinding.inflate(inflater, container, false);

        binding.updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UpdateActivity.class));
            }
        });


        binding.aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutUs.class));
            }
        });

        binding.privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView.url = "https://sites.google.com/view/raktadaan-blood-donation";
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
                rateMe(getActivity());
                Toast.makeText(getActivity(), "Rate Us", Toast.LENGTH_SHORT).show();
            }
        });

        binding.shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp(getActivity());
            }
        });




        return binding.getRoot();
    }

    private void shareApp (Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntend = new Intent();
        sendIntend.setAction(Intent.ACTION_SEND);
        sendIntend.putExtra(Intent.EXTRA_TEXT, "Install RaktoDaan Blood Donation App - Install now : https://play.google.com/store/apps/details?id="+appPackageName);
        sendIntend.setType("text/plain");
        context.startActivity(sendIntend);
    }

    public void rateMe (Context context) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+ context.getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+context.getPackageName())));
        }
    }
}