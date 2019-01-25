package com.adityaproject.pokedex;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat implements Serializable, Parcelable
{

    @SerializedName("base_stat")
    @Expose
    private Integer baseStat;
    @SerializedName("effort")
    @Expose
    private Integer effort;
    @SerializedName("stat")
    @Expose
    private Stat_ stat;
    public final static Parcelable.Creator<Stat> CREATOR = new Creator<Stat>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Stat createFromParcel(Parcel in) {
            return new Stat(in);
        }

        public Stat[] newArray(int size) {
            return (new Stat[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8593935757877764637L;

    protected Stat(Parcel in) {
        this.baseStat = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.effort = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stat = ((Stat_) in.readValue((Stat_.class.getClassLoader())));
    }

    public Stat() {
    }

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Stat_ getStat() {
        return stat;
    }

    public void setStat(Stat_ stat) {
        this.stat = stat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(baseStat);
        dest.writeValue(effort);
        dest.writeValue(stat);
    }

    public int describeContents() {
        return 0;
    }

}