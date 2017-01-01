package com.corporation.po;

import java.util.Date;

public class CorporationJoinApply {
    private Long id;

    private String reason;

    private Long userID;

    private Long corporationID;

    private String status;

    private Date applyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getCorporationID() {
        return corporationID;
    }

    public void setCorporationID(Long corporationID) {
        this.corporationID = corporationID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
}