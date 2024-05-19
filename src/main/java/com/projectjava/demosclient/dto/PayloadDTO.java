package com.projectjava.demosclient.dto;

public class PayloadDTO {
    private String action;
    private String api_version;
    private PayloadDataDTO data; // Representa el objeto data dentro del payload
    private String date_created;
    private long id;
    private boolean live_mode;
    private String type;
    private String user_id;
    public PayloadDTO() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public PayloadDataDTO getData() {
        return data;
    }

    public void setData(PayloadDataDTO data) {
        this.data = data;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLive_mode() {
        return live_mode;
    }

    public void setLive_mode(boolean live_mode) {
        this.live_mode = live_mode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return "PayloadDTO{" +
                "action='" + action + '\'' +
                ", api_version='" + api_version + '\'' +
                ", data=" + data +
                ", date_created='" + date_created + '\'' +
                ", id=" + id +
                ", live_mode=" + live_mode +
                ", type='" + type + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
