package com.zexus.next.xml;

public class XmlInfomation {
    private String mXmlTitile;
    private String mXmlLink;
    private String mXmlDescription;

    public XmlInfomation(String mXmlTitile, String mXmlLink, String mXmlDescription) {
        this.mXmlTitile = mXmlTitile;
        this.mXmlLink = mXmlLink;
        this.mXmlDescription = mXmlDescription;
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
