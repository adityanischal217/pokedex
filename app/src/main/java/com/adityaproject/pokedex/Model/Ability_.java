package com.adityaproject.pokedex.Model;



import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ability_ implements Serializable, Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    public final static Parcelable.Creator<Ability_> CREATOR = new Creator<Ability_>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Ability_ createFromParcel(Parcel in) {
            return new Ability_(in);
        }

        public Ability_[] newArray(int size) {
            return (new Ability_[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5213903826986593519L;

    protected Ability_(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Ability_() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(url);
    }

    public int describeContents() {
        return 0;
    }

}