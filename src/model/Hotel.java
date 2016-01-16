package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TienDQ on 1/13/16.
 */
public class Hotel {
    @SerializedName("name")
    private String mName;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("latitude")
    private double mLatitude;

    @SerializedName("longitude")
    private double mLongitude;

    @SerializedName("voting_value")
    private float mVotingValue;

    @SerializedName("num_of_vote")
    private int mNumOfVote;

    @SerializedName("website")
    private String mWebsite;

    @SerializedName("email")
    private String mEmail;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }


    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public float getVotingValue() {
        return mVotingValue;
    }

    public void setVotingValue(float votingValue) {
        mVotingValue = votingValue;
    }

    public int getNumOfVote() {
        return mNumOfVote;
    }

    public void setNumOfVote(int numOfVote) {
        mNumOfVote = numOfVote;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        mWebsite = website;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "mName='" + mName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                ", mVotingValue=" + mVotingValue +
                ", mNumOfVote=" + mNumOfVote +
                ", mWebsite='" + mWebsite + '\'' +
                ", mEmail='" + mEmail + '\'' +
                '}';
    }
}
