package com.class10th.mathematics.Recycler;

public class Model {
    String id, chapter, img, pdfLink, videoLink;
    boolean banner, interstitial;

    public Model() {
    }

    public Model(String id, String chapter, String img, String pdfLink, String videoLink, boolean banner, boolean interstitial) {
        this.id = id;
        this.chapter = chapter;
        this.img = img;
        this.pdfLink = pdfLink;
        this.videoLink = videoLink;
        this.banner = banner;
        this.interstitial = interstitial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public boolean isBanner() {
        return banner;
    }

    public void setBanner(boolean banner) {
        this.banner = banner;
    }

    public boolean isInterstitial() {
        return interstitial;
    }

    public void setInterstitial(boolean interstitial) {
        this.interstitial = interstitial;
    }
}
