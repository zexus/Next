package com.zexus.next.xml;

public class XmlInfomation {
    private String mXmlTitile;
    private String mXmlLink;

    public XmlInfomation(String mXmlTitile, String mXmlLink) {
        this.mXmlTitile = mXmlTitile;
        this.mXmlLink = mXmlLink;
    }


    public String getXmlTitle() {
        return mXmlTitile;
    }

    public void setXmlTitle(String title) {
        this.mXmlTitile = title;
    }

    public String getXmlLink() {
        return mXmlLink;
    }

    public void setXmlLink(String link) {
        this.mXmlLink = link;
    }
}
