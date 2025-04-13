package com.sht_software.raktodaan_blooddonation.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sht_software.raktodaan_blooddonation.AboutUs;
import com.sht_software.raktodaan_blooddonation.R;
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


        return binding.getRoot();
    }
}