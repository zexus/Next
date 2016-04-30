package com.zexus.next.xml;

public class XmlInfomation {
    private String mRssTitle;
    private String mRssImageUrl;
    private String mXmlTitile;
    private String mXmlLink;
    private String mXmlDescription;

    public XmlInfomation(String mRssTitle, String mRssImageUrl, String mXmlTitile, String mXmlLink, String mXmlDescription) {
        this.mRssTitle = mRssTitle;
        this.mRssImageUrl = mRssImageUrl;
        this.mXmlTitile = mXmlTitile;
        this.mXmlLink = mXmlLink;
        this.mXmlDescription = mXmlDescription;
    }

    public String getRssTitle() {
        return mRssTitle;
    }

    public String getmRssImageUrl() {
        return mRssImageUrl;
    }

    public String getXmlTitle() {
        return mXmlTitile;
    }

    public String getXmlLink() {
        return mXmlLink;
    }

    public String getXmlDescription() {
        return mXmlDescription;
    }
}
