package com.sht_software.raktodaan_blooddonation;

import static android.view.View.VISIBLE;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sht_software.raktodaan_blooddonation.Adapter.DonorAdapter;
import com.sht_software.raktodaan_blooddonation.Fragment.DonorList;
import com.sht_software.raktodaan_blooddonation.Model.Donor;
import com.sht_software.raktodaan_blooddonation.databinding.ActivityShowAllDataBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ShowAllData extends AppCompatActivity {

    private ActivityShowAllDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Force Light Mode before setting content
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityShowAllDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String division = DonorList.binding.spinnerDivision.getSelectedItem().toString();
        String district = DonorList.binding.spinnerDistrict.getSelectedItem().toString();
        String upazila = DonorList.binding.spinnerUpazila.getSelectedItem().toString();
        String bloodGroup = DonorList.binding.spinnerBloodGroup.getSelectedItem().toString();

        fetchMatchingDonors(division, district, upazila, bloodGroup);
    }

    private void fetchMatchingDonors(String division, String district, String upazila, String bloodGroup) {
        ProgressDialog progressDialog = new ProgressDialog(ShowAllData.this);
        progressDialog.setMessage("Searching matching donors...");
        progressDialog.show();

        // Build URL with parameters
        String url = "https://shtacademy.com/Software/Blood/fetch_donors.php" +
                "?division=" + URLEncoder.encode(division) +
                "&district=" + URLEncoder.encode(district) +
                "&upazila=" + URLEncoder.encode(upazila) +
                "&blood_group=" + URLEncoder.encode(bloodGroup);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getInt("count") > 0) {
                            JSONArray donorsArray = jsonResponse.getJSONArray("donors");
                            List<Donor> donorList = new ArrayList<>();

                            for (int i = 0; i < donorsArray.length(); i++) {
                                JSONObject donorObj = donorsArray.getJSONObject(i);
                                Donor donor = new Donor(
                                        donorObj.getString("id"),
                                        donorObj.getString("full_name"),
                                        donorObj.getString("phone_number"),
                                        donorObj.optString("last_donate", "Never"),
                                        donorObj.optString("area_village", ""),
                                        donorObj.getString("division"),
                                        donorObj.getString("district"),
                                        donorObj.getString("upazila"),
                                        donorObj.getString("blood_group"),
                                        donorObj.optString("registration_date", "")
                                );
                                donorList.add(donor);
                            }

                            updateDonorListView(donorList);
                            Toast.makeText(ShowAllData.this, "Found " + donorList.size() + " matching donors", Toast.LENGTH_SHORT).show();
                        } else {
                            updateDonorListView(new ArrayList<>());
                            Toast.makeText(ShowAllData.this, jsonResponse.optString("message", "No matching donors found"), Toast.LENGTH_SHORT).show();
                            binding.layoutNotFoundData.setVisibility(VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ShowAllData.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    progressDialog.dismiss();
                    Toast.makeText(ShowAllData.this, "Network error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        Volley.newRequestQueue(ShowAllData.this).add(stringRequest);
    }

    private void updateDonorListView(List<Donor> donorList) {
        // Implement your RecyclerView or ListView update here
        DonorAdapter adapter = new DonorAdapter(donorList);
        binding.donorsRecyclerView.setAdapter(adapter);
    }

}