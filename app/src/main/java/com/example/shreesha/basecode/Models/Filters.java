
package com.example.shreesha.basecode.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Filters extends RealmObject{

    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("exclude")
    @Expose
    private Integer exclude;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getExclude() {
        return exclude;
    }

    public void setExclude(Integer exclude) {
        this.exclude = exclude;
    }

}
