package com.sht_software.raktodaan_blooddonation.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sht_software.raktodaan_blooddonation.R;
import com.sht_software.raktodaan_blooddonation.databinding.FragmentUpdateInfoBinding;

public class UpdateInfo extends Fragment {

    private FragmentUpdateInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateInfoBinding.inflate(inflater, container, false);





        return binding.getRoot();
    }
}