package com.adityaproject.pokedex.Model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Move implements Serializable, Parcelable
{

    @SerializedName("move")
    @Expose
    private Move_ move;
    @SerializedName("version_group_details")
    @Expose
    private List<VersionGroupDetail> versionGroupDetails = null;
    public final static Parcelable.Creator<Move> CREATOR = new Creator<Move>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Move createFromParcel(Parcel in) {
            return new Move(in);
        }

        public Move[] newArray(int size) {
            return (new Move[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4383918642205566073L;

    protected Move(Parcel in) {
        this.move = ((Move_) in.readValue((Move_.class.getClassLoader())));
        in.readList(this.versionGroupDetails, (VersionGroupDetail.class.getClassLoader()));
    }

    public Move() {
    }

    public Move_ getMove() {
        return move;
    }

    public void setMove(Move_ move) {
        this.move = move;
    }

    public List<VersionGroupDetail> getVersionGroupDetails() {
        return versionGroupDetails;
    }

    public void setVersionGroupDetails(List<VersionGroupDetail> versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(move);
        dest.writeList(versionGroupDetails);
    }

    public int describeContents() {
        return 0;
    }

}