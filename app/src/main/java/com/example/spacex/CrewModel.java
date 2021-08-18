package com.example.spacex;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crew")
public class CrewModel {

    @PrimaryKey
    @NonNull
    public String id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "agency")
    public String agency;
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name = "wikipedia")
    public String wikipedia;
    @ColumnInfo(name = "status")
    public String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}