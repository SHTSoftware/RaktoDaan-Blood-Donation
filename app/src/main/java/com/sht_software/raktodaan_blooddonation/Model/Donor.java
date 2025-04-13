package com.sht_software.raktodaan_blooddonation.Model;

public class Donor {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String lastDonate;
    private String areaVillage;
    private String division;
    private String district;
    private String upazila;
    private String bloodGroup;
    private String registrationDate;

    public Donor(String id, String fullName, String phoneNumber, String lastDonate,
                 String areaVillage, String division, String district,
                 String upazila, String bloodGroup, String registrationDate) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.lastDonate = lastDonate;
        this.areaVillage = areaVillage;
        this.division = division;
        this.district = district;
        this.upazila = upazila;
        this.bloodGroup = bloodGroup;
        this.registrationDate = registrationDate;
    }

    // Getters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getLastDonate() { return lastDonate; }
    public String getAreaVillage() { return areaVillage; }
    public String getDivision() { return division; }
    public String getDistrict() { return district; }
    public String getUpazila() { return upazila; }
    public String getBloodGroup() { return bloodGroup; }
    public String getRegistrationDate() { return registrationDate; }
}