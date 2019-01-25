package com.adityaproject.pokedex;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PokemonData implements Serializable, Parcelable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<PokemonResult> results = null;
    public final static Parcelable.Creator<PokemonData> CREATOR = new Creator<PokemonData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PokemonData createFromParcel(Parcel in) {
            return new PokemonData(in);
        }

        public PokemonData[] newArray(int size) {
            return (new PokemonData[size]);
        }

    };
    private final static long serialVersionUID = 4118069344762627755L;

    protected PokemonData(Parcel in) {
        this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.next = ((String) in.readValue((String.class.getClassLoader())));
        this.previous = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.results, (com.adityaproject.pokedex.PokemonResult.class.getClassLoader()));
    }

    public PokemonData() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<PokemonResult> getResults() {
        return results;
    }

    public void setResults(List<PokemonResult> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(count);
        dest.writeValue(next);
        dest.writeValue(previous);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}


