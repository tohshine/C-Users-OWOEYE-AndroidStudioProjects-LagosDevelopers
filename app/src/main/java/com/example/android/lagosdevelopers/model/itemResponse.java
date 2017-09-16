package com.example.android.lagosdevelopers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by OWOEYE on 9/13/2017.
 */

public class itemResponse {
    @SerializedName("items")
    @Expose
    private List<item>items;

    public List<item>getItems(){
        return items;
    }
    public  void setItems(List<item>items){
        this.items= items;
    }

}
