package model;

/**
 * Created by TienDQ on 1/13/16.
 */
public class Hotel {
    private String mName;
    private String mAddress;
    private String mPhone;
    private long mLatitude;
    private long mLongitude;
    private float mVotingValue;
    private float mNumOfVote;
    private String mWebsite;
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

    public long getLatitude() {
        return mLatitude;
    }

    public void setLatitude(long latitude) {
        mLatitude = latitude;
    }

    public long getLongitude() {
        return mLongitude;
    }

    public void setLongitude(long longitude) {
        mLongitude = longitude;
    }

    public float getVotingValue() {
        return mVotingValue;
    }

    public void setVotingValue(float votingValue) {
        mVotingValue = votingValue;
    }

    public float getNumOfVote() {
        return mNumOfVote;
    }

    public void setNumOfVote(float numOfVote) {
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
}
