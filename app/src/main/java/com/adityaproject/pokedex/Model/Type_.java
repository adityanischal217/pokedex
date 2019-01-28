package com.adityaproject.pokedex.Model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type_ implements Serializable, Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    public final static Parcelable.Creator<Type_> CREATOR = new Creator<Type_>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Type_ createFromParcel(Parcel in) {
            return new Type_(in);
        }

        public Type_[] newArray(int size) {
            return (new Type_[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1211675094014748444L;

    protected Type_(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Type_() {
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