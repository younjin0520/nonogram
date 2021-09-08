package edu.skku.map.pa2;

public class DataModel {
    private String lastBuildDate;
    private String total;
    private String start;
    private String display;
    public ItemProperty[] items;
}

class ItemProperty{
    private String title;
    private String link;
    private String thumbnail;
    private String sizeheight;
    private String sizewidth;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getSizeheight() {
        return sizeheight;
    }

    public String getSizewidth() {
        return sizewidth;
    }
}