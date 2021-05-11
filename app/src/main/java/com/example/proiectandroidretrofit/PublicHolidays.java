package com.example.proiectandroidretrofit;

import com.google.gson.annotations.SerializedName;

public class PublicHolidays {
    @SerializedName("date")
    private String date;
    @SerializedName("localName")
    private String localName;
//    @SerializedName("name")
//    private String name;
//    @SerializedName("countryCode")
//    private String countryCode;
//    @SerializedName("fixed")
//    private boolean fixed;
//    @SerializedName("global")
//    private boolean global;
//    @SerializedName("counties")
//    private Map<Integer,String> counties;
////    @SerializedName("launchYear")
////    private int launchYear;
////    @SerializedName("type")
////    private String type;

    public PublicHolidays(String date, String localName) {
        this.date = date;
        this.localName = localName;
    }

//    public PublicHolidays(String date, String localName, String name, String countryCode, boolean fixed, boolean global, Map<Integer, String> counties) {
//        this.date = date;
//        this.localName = localName;
//        this.name = name;
//        this.countryCode = countryCode;
//        this.fixed = fixed;
//        this.global = global;
//        this.counties = counties;
////        this.launchYear = launchYear;
////        this.type = type;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCountryCode() {
//        return countryCode;
//    }
//
//    public void setCountryCode(String countryCode) {
//        this.countryCode = countryCode;
//    }
//
//    public boolean isFixed() {
//        return fixed;
//    }
//
//    public void setFixed(boolean fixed) {
//        this.fixed = fixed;
//    }
//
//    public boolean isGlobal() {
//        return global;
//    }
//
//    public void setGlobal(boolean global) {
//        this.global = global;
//    }
//
//    public Map<Integer, String> getCounties() {
//        return counties;
//    }
//
//    public void setCounties(Map<Integer, String> counties) {
//        this.counties = counties;
//    }

//    public int getLaunchYear() {
//        return launchYear;
//    }
//
//    public void setLaunchYear(int launchYear) {
//        this.launchYear = launchYear;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    @Override
    public String toString() {
        return localName + '\'' + date + '\''
//                ", name='" + name + '\'' +
//                ", countryCode='" + countryCode + '\'' +
//                ", fixed=" + fixed +
//                ", global=" + global +
//                ", counties=" + counties +
//                ", launchYear=" + launchYear +
//                ", type='" + type + '\'' +
                ;
    }
}
