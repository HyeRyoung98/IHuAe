package com.example.ihuae.Item;

import androidx.annotation.NonNull;

public class GuideCardItem {
    public GuideCardItem(){}
    public int id = 0;
    public int DateID = 0;
    public String Content = "";
    public int Image = 0;

    @Override
    public String toString() {
        return "GuideCardItem{" +
                "id=" + id +
                ", DateID=" + DateID +
                ", Content='" + Content + '\'' +
                ", Image=" + Image +
                '}';
    }
}
