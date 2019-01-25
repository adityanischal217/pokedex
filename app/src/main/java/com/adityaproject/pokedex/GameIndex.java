package com.adityaproject.pokedex;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameIndex implements Serializable, Parcelable
{

    @SerializedName("game_index")
    @Expose
    private Integer gameIndex;
    @SerializedName("version")
    @Expose
    private Version version;
    public final static Parcelable.Creator<GameIndex> CREATOR = new Creator<GameIndex>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GameIndex createFromParcel(Parcel in) {
            return new GameIndex(in);
        }

        public GameIndex[] newArray(int size) {
            return (new GameIndex[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6785933789929847620L;

    protected GameIndex(Parcel in) {
        this.gameIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.version = ((Version) in.readValue((Version.class.getClassLoader())));
    }

    public GameIndex() {
    }

    public Integer getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(Integer gameIndex) {
        this.gameIndex = gameIndex;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(gameIndex);
        dest.writeValue(version);
    }

    public int describeContents() {
        return 0;
    }

}