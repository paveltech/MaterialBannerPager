package com.example.lolipop.materialbannerpager;

/**
 * Created by lolipop on 2/15/17.
 */

public class SlideItem {

    public String title;
    public String link;


    public SlideItem(String title , String link){
        this.title = title;
        this.link = link;
    }
    public SlideItem(){}

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
