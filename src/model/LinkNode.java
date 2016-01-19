package model;

import util.Config;

import java.util.List;

/**
 * Created by TienDQ on 1/19/16.
 */
public class LinkNode {
    public static final int TYPE_NONE = 0;
    public static final int TYPE_BOOKING = 1;
    public static final int TYPE_DIACHISO = 2;
    public static final int TYPE_IVIVU = 3;

    public String mCurrentLink;
    public String mNextLink;
    public int mType;
    private List<String> mListLinksToParsing;
    private int mNumOfLinkIsParsed = 0;

    public String getCurrentLink() {
        return mCurrentLink;
    }

    public void setCurrentLink(String currentLink) {
        mCurrentLink = currentLink;
        if (currentLink != null){
            mNumOfLinkIsParsed++;
            if (mCurrentLink.contains(Config.BASE_DOMAIN_BOOKING)){
                mType = TYPE_BOOKING;
            } else if (mCurrentLink.contains(Config.BASE_DOMAIN_DIACHISO)){
                mType = TYPE_DIACHISO;
            } else if (mCurrentLink.contains(Config.BASE_DOMAIN_IVIVU)){
                mType = TYPE_IVIVU;
            }
        }
    }

    public String getNextLink() {
        return mNextLink;
    }

    public void setNextLink(String nextLink) {
        mNextLink = nextLink;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public List<String> getListLinksToParsing() {
        return mListLinksToParsing;
    }

    public void setListLinksToParsing(List<String> listLinksToParsing) {
        mListLinksToParsing = listLinksToParsing;
    }

    public int getNumOfLinkIsParsed(){
        return mNumOfLinkIsParsed;
    }
}
