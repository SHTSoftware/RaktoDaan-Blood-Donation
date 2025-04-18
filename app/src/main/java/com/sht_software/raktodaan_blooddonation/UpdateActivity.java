package com.sht_software.raktodaan_blooddonation;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.sht_software.raktodaan_blooddonation.databinding.ActivityUpdateBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    public static ActivityUpdateBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Force Light Mode before setting content
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String[] Division = {"প্রথমে বিভাগ সিলেক্ট করুন", "ঢাকা বিভাগ", "চট্টগ্রাম বিভাগ", "রাজশাহী বিভাগ", "খুলনা বিভাগ",
                "বরিশাল বিভাগ", "সিলেট বিভাগ", "রংপুর বিভাগ", "ময়মনসিংহ বিভাগ"};
        setDivisionAdapter(Division);



        binding.spinnerDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected = adapterView.getItemAtPosition(position).toString();

                // Clear previous selections
                String[] empty = {""};
                ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(UpdateActivity.this,
                        com.google.android.material.R.layout.support_simple_spinner_dropdown_item, empty);
                binding.spinnerDistrict.setAdapter(emptyAdapter);
                binding.spinnerUpazila.setAdapter(emptyAdapter);

                // Clear previous selections
                String[] defaultDistrict = {"জেলা সিলেক্ট করুন"};
                setDistrictAdapter(defaultDistrict);

                String[] defaultUpazila = {"উপজেলা সিলেক্ট করুন"};
                setUpazilaAdapter(defaultUpazila);


                if (selected.equals("ঢাকা বিভাগ")) {
                    String[] districts = {
                            "ঢাকা", "ফরিদপুর", "গাজীপুর", "গোপালগঞ্জ", "কিশোরগঞ্জ",
                            "মাদারীপুর", "মানিকগঞ্জ", "মুন্সীগঞ্জ", "নারায়ণগঞ্জ",
                            "নরসিংদী", "রাজবাড়ী", "শরীয়তপুর", "টাঙ্গাইল"
                    };
                    setDistrictAdapter(districts);

                    // Dhaka Division Upazilas
                    binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String district = adapterView.getItemAtPosition(i).toString();

                            if (district.equals("ঢাকা")) {
                                String[] upazilas = {"সাভার", "ধামরাই", "কেরানীগঞ্জ", "নবাবগঞ্জ", "দোহার"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("গাজীপুর")) {
                                String[] upazilas = {"গাজীপুর সদর", "কালীগঞ্জ", "কালিয়াকৈর", "কাপাসিয়া", "শ্রীপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নারায়ণগঞ্জ")) {
                                String[] upazilas = {"নারায়ণগঞ্জ সদর", "রূপগঞ্জ", "আড়াইহাজার", "সোনারগাঁও", "বন্দর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নরসিংদী")) {
                                String[] upazilas = {"নরসিংদী সদর", "পলাশ", "মনোহরদী", "শিবপুর", "বেলাবো", "রায়পুরা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মুন্সীগঞ্জ")) {
                                String[] upazilas = {"মুন্সীগঞ্জ সদর", "লৌহজং", "গজারিয়া", "সিরাজদিখান", "শ্রীনগর", "টঙ্গীবাড়ি"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মানিকগঞ্জ")) {
                                String[] upazilas = {"মানিকগঞ্জ সদর", "সিঙ্গাইর", "শিবালয়", "সাটুরিয়া", "হরিরামপুর", "দৌলতপুর", "ঘিওর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ফরিদপুর")) {
                                String[] upazilas = {"ফরিদপুর সদর", "আলফাডাঙ্গা", "ভাঙ্গা", "বোয়ালমারী", "চরভদ্রাসন", "মধুখালী", "নগরকান্দা", "সদরপুর", "সালথা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("রাজবাড়ী")) {
                                String[] upazilas = {"রাজবাড়ী সদর", "বালিয়াকান্দি", "পাংশা", "গোয়ালন্দ", "কালুখালী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("গোপালগঞ্জ")) {
                                String[] upazilas = {"গোপালগঞ্জ সদর", "কাশিয়ানী", "কোটালীপাড়া", "মুকসুদপুর", "টুঙ্গিপাড়া"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মাদারীপুর")) {
                                String[] upazilas = {"মাদারীপুর সদর", "রাজৈর", "কালকিনি", "শিবচর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("শরীয়তপুর")) {
                                String[] upazilas = {"শরীয়তপুর সদর", "নড়িয়া", "জাজিরা", "গোসাইরহাট", "ভেদরগঞ্জ", "ডামুড্যা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("টাঙ্গাইল")) {
                                String[] upazilas = {"টাঙ্গাইল সদর", "কালিহাতী", "দেলদুয়ারে", "মধুপুর", "ভুঞাপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("কিশোরগঞ্জ")) {
                                String[] upazilas = {"কিশোরগঞ্জ সদর", "আক্কেলপুর", "হোসেনপুর", "কটিয়াদী", "ইটনা"};
                                setUpazilaAdapter(upazilas);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (selected.equals("চট্টগ্রাম বিভাগ")) {
                    String[] districts = {"চট্টগ্রাম", "কক্সবাজার", "রাঙ্গামাটি", "বান্দরবান", "খাগড়াছড়ি",
                            "ফেনী", "লক্ষ্মীপুর", "চাঁদপুর", "নোয়াখালী", "ব্রাহ্মণবাড়িয়া", "কুমিল্লা"};
                    setDistrictAdapter(districts);

                    // Chittagong Division Upazilas
                    binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String district = adapterView.getItemAtPosition(i).toString();

                            if (district.equals("চট্টগ্রাম")) {
                                String[] upazilas = {"সন্দ্বীপ", "সীতাকুণ্ড", "মীরসরাই", "পটিয়া", "রাঙ্গুনিয়া", "বাঁশখালী", "আনোয়ারা", "লোহাগাড়া", "চন্দনাইশ", "হাটহাজারী", "পাঁচলাইশ", "চট্টগ্রাম সদর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("কক্সবাজার")) {
                                String[] upazilas = {"কক্সবাজার সদর", "কুতুবদিয়া", "উখিয়া", "মহেশখালী", "টেকনাফ", "চকরিয়া", "পেকুয়া", "রামু"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("খাগড়াছড়ি")) {
                                String[] upazilas = {"খাগড়াছড়ি সদর", "দিঘীনালা", "মাটিরাঙ্গা", "পানছড়ি", "রামগড়", "মানিকছড়ি", "লক্ষীছড়ি", "গুইমারা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("রাঙ্গামাটি")) {
                                String[] upazilas = {"রাঙ্গামাটি সদর", "বাঘাইছড়ি", "বরকল", "লংগদু", "জুরাছড়ি", "বিলাইছড়ি", "কাপ্তাই", "রাজস্থলী", "নানিয়াচর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("বান্দরবান")) {
                                String[] upazilas = {"বান্দরবান সদর", "থানচি", "লামা", "রুমা", "রোয়াংছড়ি", "আলী কদম", "নাইক্ষ্যংছড়ি"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ব্রাহ্মণবাড়িয়া")) {
                                String[] upazilas = {"ব্রাহ্মণবাড়িয়া সদর", "আশুগঞ্জ", "আখাউড়া", "সরাইল", "নাসিরনগর", "নবীনগর", "বাঞ্ছারামপুর", "কসবা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("কুমিল্লা")) {
                                String[] upazilas = {"কুমিল্লা সদর", "মেঘনা", "দাউদকান্দি", "লাকসাম", "চান্দিনা", "মনোহরগঞ্জ", "নাঙ্গলকোট", "বুড়িচং", "মুরাদনগর", "তিতাস", "হোমনা", "লালমাই", "দেবীদ্বার"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নোয়াখালী")) {
                                String[] upazilas = {"নোয়াখালী সদর", "চাটখিল", "সোনাইমুড়ী", "বেগমগঞ্জ", "কোম্পানীগঞ্জ", "হাতিয়া", "সুবর্ণচর", "কবিরহাট"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ফেনী")) {
                                String[] upazilas = {"ফেনী সদর", "পরশুরাম", "ছাগলনাইয়া", "দাগনভূঁঞা", "সোনাগাজী", "ফুলগাজী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("লক্ষ্মীপুর")) {
                                String[] upazilas = {"লক্ষ্মীপুর সদর", "রামগঞ্জ", "রামগতি", "কমলনগর", "রায়পুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("চাঁদপুর")) {
                                String[] upazilas = {"চাঁদপুর সদর", "হাজীগঞ্জ", "শাহরাস্তি", "মতলব উত্তর", "মতলব দক্ষিণ", "কচুয়া", "ফরিদগঞ্জ", "চাঁদপুর পৌরসভা"};
                                setUpazilaAdapter(upazilas);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                } else if (selected.equals("রাজশাহী বিভাগ")) {
                    String[] districts = {"রাজশাহী", "নাটোর", "নওগাঁ", "পাবনা", "বগুড়া",
                            "জয়পুরহাট", "চাঁপাইনবাবগঞ্জ", "সিরাজগঞ্জ"};
                    setDistrictAdapter(districts);

                    // Rajshahi Division Upazilas
                    binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String district = adapterView.getItemAtPosition(i).toString();

                            if (district.equals("রাজশাহী")) {
                                String[] upazilas = {"বাগমারা", "বাঘা", "চারঘাট", "মোহনপুর", "পবা", "পুঠিয়া", "গোদাগাড়ী", "তানোর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নাটোর")) {
                                String[] upazilas = {"নাটোর সদর", "সিংড়া", "বড়াইগ্রাম", "গুরুদাসপুর", "লালপুর", "বাগাতিপাড়া", "নলডাঙ্গা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নওগাঁ")) {
                                String[] upazilas = {"নওগাঁ সদর", "পত্নীতলা", "ধামইরহাট", "মহাদেবপুর", "মান্দা", "আত্রাই", "রানীনগর", "নিয়ামতপুর", "পোরশা", "সাপাহার", "বদলগাছী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("চাঁপাইনবাবগঞ্জ")) {
                                String[] upazilas = {"চাঁপাইনবাবগঞ্জ সদর", "গোমস্তাপুর", "নাচোল", "ভোলাহাট", "শিবগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("জয়পুরহাট")) {
                                String[] upazilas = {"জয়পুরহাট সদর", "আক্কেলপুর", "কালাই", "পাঁচবিবি", "ক্ষেতলাল"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("বগুড়া")) {
                                String[] upazilas = {"বগুড়া সদর", "শাজাহানপুর", "নন্দিগ্রাম", "ধুনট", "সারিয়াকান্দি", "শেরপুর", "দুপচাঁচিয়া", "কাহালু", "গাবতলী", "সোনাতলা", "আদমদীঘি", "শিবগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("পাবনা")) {
                                String[] upazilas = {"পাবনা সদর", "সাঁথিয়া", "বেড়া", "চাটমোহর", "ফরিদপুর", "ভাঙ্গুড়া", "ঈশ্বরদী", "আটঘরিয়া", "সুজানগর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("সিরাজগঞ্জ")) {
                                String[] upazilas = {"সিরাজগঞ্জ সদর", "বেলকুচি", "চৌহালী", "কামারখন্দ", "কাজীপুর", "রায়গঞ্জ", "শাহজাদপুর", "তাড়াশ", "উল্লাপাড়া"};
                                setUpazilaAdapter(upazilas);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                } else if (selected.equals("খুলনা বিভাগ")) {
                    String[] districts = {"খুলনা", "বাগেরহাট", "সাতক্ষীরা", "যশোর", "ঝিনাইদহ",
                            "মাগুরা", "কুষ্টিয়া", "মেহেরপুর", "চুয়াডাঙ্গা", "নড়াইল"};
                    setDistrictAdapter(districts);

                    // Khulna Division Upazilas
                    binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String district = adapterView.getItemAtPosition(i).toString();

                            if (district.equals("যশোর")) {
                                String[] upazilas = {"যশোর সদর", "কেশবপুর", "চৌগাছা", "ঝিকরগাছা", "মণিরামপুর", "বাঘারপাড়া", "সাতক্ষীরা", "শার্শা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মেহেরপুর")) {
                                String[] upazilas = {"মেহেরপুর সদর", "মুজিবনগর", "গাংনী", "মেহেরপুর পৌরসভা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("খুলনা")) {
                                String[] upazilas = {"ডুমুরিয়া", "দাকোপ", "তেরখাদা", "বটিয়াঘাটা", "ফুলতলা", "পাইকগাছা", "রূপসা", "খুলনা সদর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("বাগেরহাট")) {
                                String[] upazilas = {"বাগেরহাট সদর", "চিতলমারী", "ফকিরহাট", "কচুয়া", "মোল্লাহাট", "মোড়েলগঞ্জ", "রামপাল", "শরণখোলা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("সাতক্ষীরা")) {
                                String[] upazilas = {"আশাশুনি", "দেবহাটা", "কলারোয়া", "সাতক্ষীরা সদর", "শ্যামনগর", "তালা", "কালিগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ঝিনাইদহ")) {
                                String[] upazilas = {"ঝিনাইদহ সদর", "হরিনাকুন্ডু", "শৈলকুপা", "কালীগঞ্জ", "কোটচাঁদপুর", "মহেশপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("কুষ্টিয়া")) {
                                String[] upazilas = {"কুষ্টিয়া সদর", "কুমারখালী", "খোকসা", "ভেড়ামারা", "দৌলতপুর", "মিরপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("চুয়াডাঙ্গা")) {
                                String[] upazilas = {"চুয়াডাঙ্গা সদর", "আলমডাঙ্গা", "দামুড়হুদা", "জীবননগর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মাগুরা")) {
                                String[] upazilas = {"মাগুরা সদর", "শালিখা", "মহম্মদপুর", "সিনিয়ার"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নড়াইল")) {
                                String[] upazilas = {"নড়াইল সদর", "লোহাগড়া", "কালিয়া"};
                                setUpazilaAdapter(upazilas);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (selected.equals("বরিশাল বিভাগ")) {
                    String[] districts = {"বরিশাল", "পটুয়াখালী", "ভোলা", "পিরোজপুর", "বরগুনা", "ঝালকাঠি"};
                    setDistrictAdapter(districts);

                    // Barishal Division Upazilas
                    binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String district = adapterView.getItemAtPosition(i).toString();

                            if (district.equals("বরিশাল")) {
                                String[] upazilas = {"বরিশাল সদর", "বাকেরগঞ্জ", "বাবুগঞ্জ", "গৌরনদী", "আগৈলঝাড়া", "মেহেন্দিগঞ্জ", "হিজলা", "মুলাদী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ভোলা")) {
                                String[] upazilas = {"ভোলা সদর", "দৌলতখান", "বোরহানউদ্দিন", "চরফ্যাশন", "মনপুরা", "লালমোহন", "তজুমদ্দিন"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("বরগুনা")) {
                                String[] upazilas = {"বরগুনা সদর", "আমতলী", "পাথরঘাটা", "বেতাগী", "বামনা", "তালতলি"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("পটুয়াখালী")) {
                                String[] upazilas = {"পটুয়াখালী সদর", "মির্জাগঞ্জ", "বাউফল", "দুমকি", "দশমিনা", "গলাচিপা", "কলাপাড়া", "রাঙ্গাবালী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("পিরোজপুর")) {
                                String[] upazilas = {"পিরোজপুর সদর", "নাজিরপুর", "নেছারাবাদ (স্বরুপকাঠী)", "ভান্ডারিয়া", "মঠবাড়িয়া", "কাউখালী", "ইন্দুরকানী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ঝালকাঠি")) {
                                String[] upazilas = {"ঝালকাঠি সদর", "নলছিটি", "কাঠালিয়া", "রাজাপুর"};
                                setUpazilaAdapter(upazilas);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                } else if (selected.equals("সিলেট বিভাগ")) {
                    String[] districts = {"সিলেট", "মৌলভীবাজার", "হবিগঞ্জ", "সুনামগঞ্জ"};
                    setDistrictAdapter(districts);

                    // Sylhet Division Upazilas
                    binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String district = adapterView.getItemAtPosition(i).toString();

                            if (district.equals("সিলেট")) {
                                String[] upazilas = {"সিলেট সদর", "বিয়ানীবাজার", "গোলাপগঞ্জ", "বিশ্বনাথ", "ফেঞ্চুগঞ্জ", "জৈন্তাপুর", "বালাগঞ্জ", "কোম্পানীগঞ্জ", "কানাইঘাট", "ওসমানীনগর", "জকিগঞ্জ", "দক্ষিণ সুরমা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মৌলভীবাজার")) {
                                String[] upazilas = {"মৌলভীবাজার সদর", "কুলাউড়া", "কমলগঞ্জ", "রাজনগর", "শ্রীমঙ্গল", "জুড়ী", "বড়লেখা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("হবিগঞ্জ")) {
                                String[] upazilas = {"হবিগঞ্জ সদর", "নবীগঞ্জ", "বানিয়াচং", "আজমিরীগঞ্জ", "লাখাই", "চুনারুঘাট", "মাধবপুর", "বাহুবল"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("সুনামগঞ্জ")) {
                                String[] upazilas = {"সুনামগঞ্জ সদর", "ছাতক", "জগন্নাথপুর", "দিরাই", "শাল্লা", "তাহিরপুর", "জামালগঞ্জ", "ধর্মপাশা", "বিশ্বম্ভরপুর", "দোয়ারাবাজার", "দক্ষিণ সুনামগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                } else if (selected.equals("রংপুর বিভাগ")) {
                    String[] districts = {"রংপুর", "দিনাজপুর", "নীলফামারী", "গাইবান্ধা", "কুড়িগ্রাম",
                            "লালমনিরহাট", "পঞ্চগড়", "ঠাকুরগাঁও"};
                    setDistrictAdapter(districts);

                    // Rangpur Division Upazilas
                    binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String district = adapterView.getItemAtPosition(i).toString();

                            if (district.equals("রংপুর")) {
                                String[] upazilas = {"পীরগঞ্জ", "কাউনিয়া", "বদরগঞ্জ", "মিঠাপুকুর", "গংগাচড়া", "তারাগঞ্জ", "রংপুর সদর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("দিনাজপুর")) {
                                String[] upazilas = {"বিরামপুর", "বিরল", "বোচাগঞ্জ", "চিরিরবন্দর", "ফুলবাড়ী", "ঘোড়াঘাট", "হাকিমপুর", "দিনাজপুর সদর", "নবাবগঞ্জ", "পার্বতীপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নীলফামারী")) {
                                String[] upazilas = {"নীলফামারী সদর", "ডোমার", "ডিমলা", "জলঢাকা", "কিশোরগঞ্জ", "সৈয়দপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("কুড়িগ্রাম")) {
                                String[] upazilas = {"কুড়িগ্রাম সদর", "ভুরুঙ্গামারী", "রাজারহাট", "চিলমারী", "উলিপুর", "নাগেশ্বরী", "নামারচর", "ফুলবাড়ী", "রৌমারী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("লালমনিরহাট")) {
                                String[] upazilas = {"লালমনিরহাট সদর", "আদিতমারী", "হাতীবান্ধা", "কালীগঞ্জ", "পাটগ্রাম"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ঠাকুরগাঁও")) {
                                String[] upazilas = {"ঠাকুরগাঁও সদর", "বালিয়াডাঙ্গী", "রাণীশংকৈল", "হরিপুর", "পীরগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("পঞ্চগড়")) {
                                String[] upazilas = {"পঞ্চগড় সদর", "আটোয়ারী", "বোদা", "দেবীগঞ্জ", "তেতুলিয়া"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("গাইবান্ধা")) {
                                String[] upazilas = {"গাইবান্ধা সদর", "সাদুল্লাপুর", "পলাশবাড়ী", "সাঘাটা", "গোবিন্দগঞ্জ", "সুন্দরগঞ্জ", "ফুলছড়ি"};
                                setUpazilaAdapter(upazilas);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                } else if (selected.equals("ময়মনসিংহ বিভাগ")) {
                    String[] districts = {"ময়মনসিংহ", "নেত্রকোণা", "জামালপুর", "শেরপুর"};
                    setDistrictAdapter(districts);

                    // Mymensingh Division Upazilas
                    binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String district = adapterView.getItemAtPosition(i).toString();

                            if (district.equals("ময়মনসিংহ")) {
                                String[] upazilas = {"ভালুকা", "ধোবাউড়া", "ফুলবাড়ীয়া", "গফরগাঁও", "গৌরীপুর", "হালুয়াঘাট", "ঈশ্বরগঞ্জ", "ত্রিশাল", "নান্দাইল", "মুক্তাগাছা", "ময়মনসিংহ সদর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নেত্রকোণা")) {
                                String[] upazilas = {"আটপাড়া", "বারহাট্টা", "কলমাকান্দা", "কেন্দুয়া", "মোহনগঞ্জ", "দুর্গাপুর", "খালিয়াজুরি", "মদন", "নেত্রকোণা সদর", "পূর্বধলা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("জামালপুর")) {
                                String[] upazilas = {"বকশীগঞ্জ", "দেওয়ানগঞ্জ", "ইসলামপুর", "মেলান্দহ", "মাদারগঞ্জ", "সরিষাবাড়ী", "জামালপুর সদর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("শেরপুর")) {
                                String[] upazilas = {"শেরপুর সদর", "নকলা", "নালিতাবাড়ী", "ঝিনাইগাতী", "শ্রীবর্দি"};
                                setUpazilaAdapter(upazilas);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        binding.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndUpdate();
            }
        });

        binding.buttonAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLocation();
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

    private void setDivisionAdapter (String[] divisions) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateActivity.this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, divisions);
        binding.spinnerDivision.setAdapter(adapter);
    }
    private void setDistrictAdapter(String[] districts) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateActivity.this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, districts);
        binding.spinnerDistrict.setAdapter(adapter);
    }

    private void setUpazilaAdapter(String[] upazilas) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateActivity.this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, upazilas);
        binding.spinnerUpazila.setAdapter(adapter);
    }

    private void showDatePicker() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Donation Date")
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    .format(new Date(selection));
            binding.lastDonateEditText.setText(date);
        });

        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }
    private void clearForm() {
        binding.edPhone1.setText("");
        binding.lastDonateEditText.setText("");
        binding.areaVillageEditText2.setText("");
        binding.spinnerDivision.setSelection(0);
        binding.spinnerDistrict.setSelection(0);
        binding.spinnerUpazila.setSelection(0);
    }

    private void validateAndUpdate() {
        String phone = binding.edPhone1.getText().toString().trim();
        String date = binding.lastDonateEditText.getText().toString().trim();

        if (phone.isEmpty()) {
            binding.edPhone1.setError("Enter phone number");
            return;
        }

        if (date.isEmpty()) {
            binding.lastDonateEditText.setError("Select donation date");
            return;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Create JSON request body
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("phone", phone);
            jsonBody.put("last_donate", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://shtacademy.com/Software/Blood/update_last_donate.php",
                jsonBody,
                response -> {
                    progressDialog.dismiss();
                    try {
                        String status = response.getString("status");
                        String message = response.getString("message");

                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                        if (status.equals("success")) {
                            // Clear fields on success
                            binding.edPhone1.setText("");
                            binding.lastDonateEditText.setText("");
                        }
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                },
                error -> {
                    progressDialog.dismiss();
                    if (error.networkResponse != null && error.networkResponse.data != null) {
                        try {
                            String errorData = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                            JSONObject errorJson = new JSONObject(errorData);
                            String errorMsg = errorJson.getString("message");
                            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(this, "Network error occurred", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Network error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Add request to queue
        Volley.newRequestQueue(this).add(request);
    }
    private void updateLocation() {
        String phone = binding.phoneNumberEditText2.getText().toString().trim();
        String areaVillage = binding.areaVillageEditText2.getText().toString().trim();
        String division = binding.spinnerDivision.getSelectedItem().toString();
        String district = binding.spinnerDistrict.getSelectedItem().toString();
        String upazila = binding.spinnerUpazila.getSelectedItem().toString();

        if (phone.isEmpty()) {
            binding.phoneNumberEditText2.setError("Enter registered phone number");
            return;
        }
        if (division.equals("প্রথমে বিভাগ সিলেক্ট করুন")) {
            Toast.makeText(getApplicationContext(), "Please select division", Toast.LENGTH_SHORT).show();
            return;
        }
        if (district.equals("জেলা সিলেক্ট করুন")) {
            Toast.makeText(getApplicationContext(), "Please select district", Toast.LENGTH_SHORT).show();
            return;
        }
        if (upazila.equals("উপজেলা সিলেক্ট করুন")) {
            Toast.makeText(getApplicationContext(), "Please select upazila", Toast.LENGTH_SHORT).show();
            return;
        }
        if (areaVillage.isEmpty()) {
            binding.areaVillageEditText2.setError("Enter Village Name");
            return;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating location...");
        progressDialog.show();

        // Create JSON request body
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("phone", phone);
            jsonBody.put("area_village", areaVillage);
            jsonBody.put("division", division);
            jsonBody.put("district", district);
            jsonBody.put("upazila", upazila);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://shtacademy.com/Software/Blood/update_donor_location.php",
                jsonBody,
                response -> {
                    progressDialog.dismiss();
                    try {
                        String status = response.getString("status");
                        String message = response.getString("message");

                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                        if (status.equals("success")) {
                            finish(); // Close activity on success
                        }
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error processing response", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    progressDialog.dismiss();
                    if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                        Toast.makeText(this, "Phone number not found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Volley.newRequestQueue(this).add(request);
    }
}