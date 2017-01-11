
package com.example.shreesha.basecode.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Avatars extends RealmObject{

    @SerializedName("default")
    @Expose
    private Default _default;
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("small")
    @Expose
    private Small small;
    @SerializedName("tiny")
    @Expose
    private Tiny tiny;

    public Default getDefault() {
        return _default;
    }

    public void setDefault(Default _default) {
        this._default = _default;
    }

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {
        this.large = large;
    }

    public Small getSmall() {
        return small;
    }

    public void setSmall(Small small) {
        this.small = small;
    }

    public Tiny getTiny() {
        return tiny;
    }

    public void setTiny(Tiny tiny) {
        this.tiny = tiny;
    }

}
