package com.adityaproject.pokedex;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionGroupDetail implements Serializable, Parcelable
{

    @SerializedName("level_learned_at")
    @Expose
    private Integer levelLearnedAt;
    @SerializedName("move_learn_method")
    @Expose
    private MoveLearnMethod moveLearnMethod;
    @SerializedName("version_group")
    @Expose
    private VersionGroup versionGroup;
    public final static Parcelable.Creator<VersionGroupDetail> CREATOR = new Creator<VersionGroupDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VersionGroupDetail createFromParcel(Parcel in) {
            return new VersionGroupDetail(in);
        }

        public VersionGroupDetail[] newArray(int size) {
            return (new VersionGroupDetail[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6376061140359357873L;

    protected VersionGroupDetail(Parcel in) {
        this.levelLearnedAt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.moveLearnMethod = ((MoveLearnMethod) in.readValue((MoveLearnMethod.class.getClassLoader())));
        this.versionGroup = ((VersionGroup) in.readValue((VersionGroup.class.getClassLoader())));
    }

    public VersionGroupDetail() {
    }

    public Integer getLevelLearnedAt() {
        return levelLearnedAt;
    }

    public void setLevelLearnedAt(Integer levelLearnedAt) {
        this.levelLearnedAt = levelLearnedAt;
    }

    public MoveLearnMethod getMoveLearnMethod() {
        return moveLearnMethod;
    }

    public void setMoveLearnMethod(MoveLearnMethod moveLearnMethod) {
        this.moveLearnMethod = moveLearnMethod;
    }

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(levelLearnedAt);
        dest.writeValue(moveLearnMethod);
        dest.writeValue(versionGroup);
    }

    public int describeContents() {
        return 0;
    }

}
