package com.ehabahmed.bayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User{
    @SerializedName("id")

    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("email")

    public String email;
    @SerializedName("email_verified_at")

    public String email_verified_at;
    @SerializedName("created_at")

    public String created_at;
    @SerializedName("updated_at")

    public String updated_at;
    @SerializedName("deleted_at")

    public String deleted_at;
    @SerializedName("region_id")

    public String region_id;
    @SerializedName("territory_id")
    public String territory_id;
    @SerializedName("brick_id")

    public String brick_id;
    @SerializedName("image")

    public String image;
    @SerializedName("phone")

    public String phone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getTerritory_id() {
        return territory_id;
    }

    public void setTerritory_id(String territory_id) {
        this.territory_id = territory_id;
    }

    public String getBrick_id() {
        return brick_id;
    }

    public void setBrick_id(String brick_id) {
        this.brick_id = brick_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}