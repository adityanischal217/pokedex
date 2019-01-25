package com.adityaproject.pokedex;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoveLearnMethod implements Serializable, Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    public final static Parcelable.Creator<MoveLearnMethod> CREATOR = new Creator<MoveLearnMethod>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MoveLearnMethod createFromParcel(Parcel in) {
            return new MoveLearnMethod(in);
        }

        public MoveLearnMethod[] newArray(int size) {
            return (new MoveLearnMethod[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1936799904265972382L;

    protected MoveLearnMethod(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MoveLearnMethod() {
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