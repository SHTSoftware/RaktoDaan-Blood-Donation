package com.sht_software.raktodaan_blooddonation.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.sht_software.raktodaan_blooddonation.ShowAllData;
import com.sht_software.raktodaan_blooddonation.databinding.FragmentDonorListBinding;

public class DonorList extends Fragment {

    public static FragmentDonorListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDonorListBinding.inflate(inflater, container, false);


        String[] bloodGroup = {
                "Select Blood Group", "A (positive)", "A (negative)", "B (positive)", "B (negative)", "AB (positive)", "AB (negative)", "O (positive)", "O (negative)"};
        setBloodGroup(bloodGroup);



        String[] Division = {"প্রথমে বিভাগ সিলেক্ট করুন", "ঢাকা বিভাগ", "চট্টগ্রাম বিভাগ", "রাজশাহী বিভাগ", "খুলনা বিভাগ",
                "বরিশাল বিভাগ", "সিলেট বিভাগ", "রংপুর বিভাগ", "ময়মনসিংহ বিভাগ"};
        setDivisionAdapter(Division);



        binding.spinnerDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selected = adapterView.getItemAtPosition(position).toString();

                // Clear previous selections
                String[] empty = {""};
                ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(getActivity(),
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

                            if (district.equals("কিশোরগঞ্জ")) {
                                String[] upazilas = {"অষ্টগ্রাম", "বাজিতপুর", "ভৈরব", "হোসেনপুর", "ইটনা", "করিমগঞ্জ", "কটিয়াদি", "কিশোরগঞ্জ সদর", "কুলিয়ারচর", "মিঠামইন", "নিকলী", "পাকুন্দিয়া", "তাড়াইল"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("গাজীপুর")) {
                                String[] upazilas = {"গাজীপুর সদর", "কালিয়াকৈর", "কালীগঞ্জ", "কাপাসিয়া", "শ্রীপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("গোপালগঞ্জ")) {
                                String[] upazilas = {"গোপালগঞ্জ সদর", "কাশিয়ানী", "কোটালীপাড়া", "মুকসুদপুর", "টুঙ্গিপাড়া"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("টাঙ্গাইল")) {
                                String[] upazilas = {"বাসাইল", "ভুয়াপুর", "দেলদুয়ার", "ঘাটাইল", "গোপালপুর", "কালিহাতি", "মধুপুর", "মির্জাপুর", "নাগরপুর", "সখিপুর", "টাঙ্গাইল সদর", "ধনবাড়ী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ঢাকা")) {
                                String[] upazilas = {"ধামরাই", "দোহার", "কেরানীগঞ্জ", "নবাবগঞ্জ", "সাভার"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নরসিংদী")) {
                                String[] upazilas = {"বেলাবো", "মনোহরদী", "নরসিংদী সদর", "পলাশ", "রায়পুরা", "শিবপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নারায়ণগঞ্জ")) {
                                String[] upazilas = {"আড়াইহাজার", "সোনারগাঁও", "বন্দর", "নারায়ণগঞ্জ সদর", "রূপগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ফরিদপুর")) {
                                String[] upazilas = {"আলফাডাঙা", "ভাঙ্গা", "বোয়ালমারী", "চর ভদ্রাসন", "ফরিদপুর সদর", "মধুখালী", "নগরকান্দা", "সদরপুর", "সালথা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মাদারীপুর")) {
                                String[] upazilas = {"কালকিনী", "মাদারীপুর সদর", "রাজৈর", "শিবচর", "ডাসা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মানিকগঞ্জ")) {
                                String[] upazilas = {"দৌলতপুর", "ঘিওর", "হরিরামপুর", "মানিকগঞ্জ", "সাটুরিয়া", "শিবালয়", "সিঙ্গাইর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মুন্সীগঞ্জ")) {
                                String[] upazilas = {"গজারিয়া", "লৌহজং", "মুন্সীগঞ্জ সদর", "সিরাজদীখান", "শ্রীনগর", "টঙ্গীবাড়ী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("রাজবাড়ী")) {
                                String[] upazilas = {"বালিয়াকান্দি", "গোয়ালন্দ", "পাংশা", "রাজবাড়ী সদর", "কালুখালী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("শরীয়তপুর")) {
                                String[] upazilas = {"ভেদরগঞ্জ", "ডামুড্যা", "গোসাইরহাট", "নড়িয়া", "শরিয়তপুর সদর", "জাজিরা"};
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

                            if (district.equals("কক্সবাজার")) {
                                String[] upazilas = {"কক্সবাজার সদর", "রামু", "চকরিয়া", "কুতুবদিয়া", "পেকুয়া", "উখিয়া", "টেকনাফ", "মহেশখালী", "ঈদগাঁও"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("কুমিল্লা")) {
                                String[] upazilas = {"আদর্শ সদর", "সদর দক্ষিণ", "বুড়িচং", "দেবিদ্বার", "চৌদ্দগ্রাম", "চান্দিনা", "মুরাদনগর", "বরুড়া", "দাউদকান্দি", "মনোহরগঞ্জ", "লাকসাম", "নাঙ্গলকোট", "ব্রাহ্মণপাড়া", "হোমনা", "মেঘনা", "তিতাস", "লালমাই"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("খাগড়াছড়ি")) {
                                String[] upazilas = {"খাগড়াছড়ি সদর", "দীঘিনালা", "রামগড়", "মানিকছড়ি", "মহালছড়ি", "পানছড়ি", "মাটিরাঙ্গা", "লক্ষ্মীছড়ি", "গুইমারা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("চট্টগ্রাম")) {
                                String[] upazilas = {"মীরসরাই", "সীতাকুণ্ড", "ফটিকছড়ি", "রাউজান", "রাঙ্গুনিয়া", "হাটহাজারী", "সাতকানিয়া", "পটিয়া", "চন্দনাইশ", "লোহাগাড়া", "সন্দ্বীপ", "বোয়ালখালি", "আনোয়ারা", "বাঁশখালী", "কর্ণফুলী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("চাঁদপুর")) {
                                String[] upazilas = {"চাঁদপুর সদর", "মতলব উত্তর", "মতলব দক্ষিণ", "ফরিদগঞ্জ", "হাইমচর", "শাহরাস্তি", "কচুয়া", "হাজীগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নোয়াখালী")) {
                                String[] upazilas = {"নোয়াখালী সদর", "বেগমগঞ্জ", "কোম্পানিগঞ্জ", "চাটখিল", "হাতিয়া", "সুবর্ণচর", "সেনবাগ", "কবিরহাট", "সোনাইমুড়ি"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ফেনী")) {
                                String[] upazilas = {"ফেনী সদর", "ছাগলনাইয়া", "দাগনভূইঞা", "পরশুরাম", "সোনাগাজী", "ফুলগাজী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ব্রাহ্মণবাড়িয়া")) {
                                String[] upazilas = {"ব্রাহ্মণবাড়িয়া সদর", "সরাইল", "আখাউড়া", "কসবা", "নাসিরনগর", "বিজয়নগর", "আশুগঞ্জ", "নবীনগর", "বাঞ্ছারামপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("বান্দরবান")) {
                                String[] upazilas = {"বান্দরবান সদর", "রুমা", "থানচি", "নাইক্ষ্যংছড়ি", "রোয়াংছড়ি", "লামা", "আলীকদম"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("রাঙ্গামাটি")) {
                                String[] upazilas = {"রাঙ্গামাটি সদর", "কাউখালি", "নানিয়ারচর", "লংগদু", "রাজস্থলি", "বিলাইছড়ি", "বরকল", "বাঘাইছড়ি", "কাপ্তাই", "জুরাছড়ি"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("লক্ষ্মীপুর")) {
                                String[] upazilas = {"লক্ষ্মীপুর সদর", "রায়পুর", "রামগঞ্জ", "রামগতি", "কমলনগর"};
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

                            if (district.equals("চাঁপাইনবাবগঞ্জ")) {
                                String[] upazilas = {"চাঁপাইনবাবগঞ্জ সদর", "গোমস্তাপুর", "নাচোল", "ভোলাহাট", "শিবগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("জয়পুরহাট")) {
                                String[] upazilas = {"জয়পুরহাট সদর", "আক্কেলপুর", "কালাই", "ক্ষেতলাল", "পাঁচবিবি"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নওগাঁ")) {
                                String[] upazilas = {"আত্রাই", "বদলগাছী", "ধামইরহাট", "নওগাঁ সদর", "নিয়ামতপুর", "পত্নীতলা", "পোরশা", "মহাদেবপুর", "মান্দা", "রাণীনগর", "সাপাহার"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নাটোর")) {
                                String[] upazilas = {"বড়াইগ্রাম", "বাগাতিপাড়া", "গুরুদাসপুর", "লালপুর", "নটোর সদর", "নলডাঙ্গা", "সিংড়া"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("পাবনা")) {
                                String[] upazilas = {"আটঘড়িয়া", "চাটমোহর", "পাবনা সদর", "বেড়া", "ভাঙ্গুড়া", "সাঁথিয়া", "সুজানগর", "ঈশ্বরদী", "ফরিদপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("বগুড়া")) {
                                String[] upazilas = {"আদমদীঘি", "বগুড়া সদর", "ধুনট", "দুপচাঁচিয়া", "গাবতলী", "কাহালু", "নন্দীগ্রাম", "সারিয়াকান্দি", "শাজাহানপুর", "শিবগঞ্জ", "শেরপুর", "সোনাতলা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("রাজশাহী")) {
                                String[] upazilas = {"বাঘা", "বাগমারা", "চারঘাট", "দূর্গাপুর", "গোদাগাড়ী", "মোহনপুর", "পবা", "পুঠিয়া", "তানোর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("সিরাজগঞ্জ")) {
                                String[] upazilas = {"বেলকুচি", "চৌহালী", "কাজিপুর", "কামারখন্দ", "রায়গঞ্জ", "শাহজাদপুর", "সিরাজগঞ্জ সদর", "তাড়াশ", "উল্লাপাড়া"};
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
                                String[] upazilas = {"যশোর সদর", "কেশবপুর", "চৌগাছা", "ঝিকরগাছা", "মণিরামপুর", "বাঘারপাড়া", "শার্শা", "অভয়নগর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মেহেরপুর")) {
                                String[] upazilas = {"মেহেরপুর সদর", "গাংনী", "মুজিবনগর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("খুলনা")) {
                                String[] upazilas = {"রূপসা", "তেরখাদা", "দিঘলিয়া", "ফুলতলা", "ডুমুরিয়া", "বটিয়াঘাটা", "পাইকগাছা", "দাকোপ", "কয়রা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("বাগেরহাট")) {
                                String[] upazilas = {"বাগেরহাট সদর", "ফকিরহাট", "মোল্লাহাট", "কচুয়া", "চিতলমারী", "রামপাল", "মোংলা", "মোড়েলগঞ্জ", "শরণখোলা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("সাতক্ষীরা")) {
                                String[] upazilas = {"তালা", "কলারোয়া", "সাতক্ষীরা সদর", "আশাশুনি", "দেবহাটা", "কালিগঞ্জ", "শ্যামনগর"};
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
                                String[] upazilas = {"মাগুরা সদর", "শালিখা", "মোহাম্মদপুর", "শ্রীপুর"};
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

                            if (district.equals("বরগুনা")) {
                                String[] upazilas = {"আমতলী", "বরগুনা সদর", "বামনা", "বেতাগি", "পাথরঘাটা", "তালতলী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("বরিশাল")) {
                                String[] upazilas = {"আগৈলঝারা", "বাকেরগঞ্জ", "বাবুগঞ্জ", "বানারিপাড়া", "বরিশাল সদর", "গৌরনদী", "হিজলা", "মেহেন্দিগঞ্জ", "মুলাদি", "উজিরপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ভোলা")) {
                                String[] upazilas = {"ভোলা সদর", "তজমুদ্দিন", "দৌলতখান", "বোরহানউদ্দিন", "মনপুরা", "লালমোহন", "চরফ্যাশন"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ঝালকাঠি")) {
                                String[] upazilas = {"ঝালকাঠি সদর", "কাঁঠালিয়া", "নলছিটি", "রাজাপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("পটুয়াখালী")) {
                                String[] upazilas = {"পটুয়াখালী সদর", "বাউফল", "দশমিনা", "গলাচিপা", "কলাপাড়া", "মির্জাগঞ্জ", "দুমকি", "রাঙ্গাবালী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("পিরোজপুর")) {
                                String[] upazilas = {"কাউখালী", "নাজিরপুর", "নেছারাবাদ (স্বরূপকাঠি)", "পিরোজপুর সদর", "ভাণ্ডারিয়া", "মঠবাড়িয়া", "ইন্দুরকানী"};
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
                                String[] upazilas = {"সিলেট সদর", "বিয়ানীবাজার", "গোলাপগঞ্জ", "বিশ্বনাথ", "ফেঞ্চুগঞ্জ", "জৈন্তাপুর", "বালাগঞ্জ", "কোম্পানীগঞ্জ", "কানাইঘাট", "ওসমানীনগর", "জকিগঞ্জ", "দক্ষিণ সুরমা", "গোয়াইনঘাট"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("মৌলভীবাজার")) {
                                String[] upazilas = {"মৌলভীবাজার সদর", "কুলাউড়া", "কমলগঞ্জ", "রাজনগর", "শ্রীমঙ্গল", "জুড়ী", "বড়লেখা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("হবিগঞ্জ")) {
                                String[] upazilas = {"হবিগঞ্জ সদর", "নবীগঞ্জ", "বানিয়াচং", "আজমিরীগঞ্জ", "লাখাই", "চুনারুঘাট", "মাধবপুর", "বাহুবল", "শায়েস্তাগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("সুনামগঞ্জ")) {
                                String[] upazilas = {"সুনামগঞ্জ সদর", "ছাতক", "জগন্নাথপুর", "দিরাই", "শাল্লা", "তাহিরপুর", "জামালগঞ্জ", "ধর্মপাশা", "বিশ্বম্ভরপুর", "দোয়ারাবাজার", "দক্ষিণ সুনামগঞ্জ", "মধ্যনগর"};
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

                            if (district.equals("কুড়িগ্রাম")) {
                                String[] upazilas = {"উলিপুর", "চিলমারী", "ফুলবাড়ী", "ভুরুঙ্গামারী", "নাগেশ্বরী", "রাজারহাট", "রাজিবপুর", "কুড়িগ্রাম সদর", "রৌমারী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("গাইবান্ধা")) {
                                String[] upazilas = {"গোবিন্দগঞ্জ", "সাঘাটা", "পলাশবাড়ী", "গাইবান্ধা সদর", "সাদুল্যাপুর", "সুন্দরগঞ্জ", "ফুলছড়ি"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("দিনাজপুর")) {
                                String[] upazilas = {"কাহারোল", "খানসামা", "বীরগঞ্জ", "চিরিরবন্দর", "বিরামপুর", "ঘোড়াঘাট", "দিনাজপুর সদর", "নবাবগঞ্জ", "বোচাগঞ্জ", "ফুলবাড়ী", "বিরল", "পার্বতীপুর", "হাকিমপুর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নীলফামারী")) {
                                String[] upazilas = {"ডিমলা", "জলঢাকা", "সৈয়দপুর", "কিশোরগঞ্জ", "নীলফামারী সদর", "ডোমার"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("পঞ্চগড়")) {
                                String[] upazilas = {"পঞ্চগড় সদর", "তেতুলিয়া", "আটোয়ারী", "দেবীগঞ্জ", "বোদা"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("রংপুর")) {
                                String[] upazilas = {"মিঠাপুকুর", "তারাগঞ্জ", "বদরগঞ্জ", "পীরগঞ্জ", "পীরগাছা", "গংগাচড়া", "কাউনিয়া", "রংপুর সদর"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("লালমনিরহাট")) {
                                String[] upazilas = {"পাটগ্রাম", "হাতীবান্ধা", "লালমনিরহাট সদর", "আদিতমারী", "কালীগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ঠাকুরগাঁও")) {
                                String[] upazilas = {"ঠাকুরগাঁও সদর", "রানীশংকৈল", "বালয়িাডাঙ্গী", "হরিপুর", "পীরগঞ্জ"};
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

                            if (district.equals("জামালপুর")) {
                                String[] upazilas = {"জামালপুর সদর", "ইসলামপুর", "দেওয়ানগঞ্জ", "মাদারগঞ্জ", "মেলান্দহ", "সরিষাবাড়ী", "বকশীগঞ্জ"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("নেত্রকোণা")) {
                                String[] upazilas = {"নেত্রকোণা সদর", "পূর্বধলা", "দুর্গাপুর", "বারহাট্টা", "আটপাড়া", "মদন", "কেন্দুয়া", "মোহনগঞ্জ", "কলমাকান্দা", "খালিয়াজুরী"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("ময়মনসিংহ")) {
                                String[] upazilas = {"ময়মনসিংহ সদর", "ত্রিশাল", "ভালুকা", "ফুলবাড়িয়া", "মুক্তাগাছা", "গফরগাঁও", "গৌরীপুর", "ঈশ্বরগঞ্জ", "নান্দাইল", "তারাকান্দা", "ফুলপুর", "হালুয়াঘাট", "ধোবাউড়া"};
                                setUpazilaAdapter(upazilas);
                            } else if (district.equals("শেরপুর")) {
                                String[] upazilas = {"শেরপুর সদর", "নকলা", "ঝিনাইগাতী", "নালিতাবাড়ী", "শ্রীবরদী"};
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

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String division = binding.spinnerDivision.getSelectedItem().toString();
                String district = binding.spinnerDistrict.getSelectedItem().toString();
                String upazila = binding.spinnerUpazila.getSelectedItem().toString();
                String bloodGroup = binding.spinnerBloodGroup.getSelectedItem().toString();

                if (division.equals("প্রথমে বিভাগ সিলেক্ট করুন")) {
                    Toast.makeText(getContext(), "Please select division", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (district.equals("জেলা সিলেক্ট করুন")) {
                    Toast.makeText(getContext(), "Please select district", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (upazila.equals("উপজেলা সিলেক্ট করুন")) {
                    Toast.makeText(getContext(), "Please select upazila", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (bloodGroup.equals("Select Blood Group")) {
                    Toast.makeText(getContext(), "Please select blood group", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(getActivity(), ShowAllData.class));
            }
        });


        return binding.getRoot();

    } // On Create Bundle

    private void setDivisionAdapter (String[] divisions) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, divisions);
        binding.spinnerDivision.setAdapter(adapter);
    }
    private void setDistrictAdapter(String[] districts) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, districts);
        binding.spinnerDistrict.setAdapter(adapter);
    }

    private void setUpazilaAdapter(String[] upazilas) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, upazilas);
        binding.spinnerUpazila.setAdapter(adapter);
    }

    private void setBloodGroup (String[] bloodGroup) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, bloodGroup);
        binding.spinnerBloodGroup.setAdapter(adapter);
    }


}