package com.trantorinc.synergy.admin.panel.dto;

import java.util.Date;
import java.util.Map;

public class UserDto implements Comparable<UserDto>  {

    private Long userId;
    private String userName;
    private String userTrackerId;
    private String userLoginTime;
    private String userIp;
    private String userAgent;
    private Map<String, String> userTrackerPath;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTrackerId() {
        return userTrackerId;
    }

    public void setUserTrackerId(String userTrackerId) {
        this.userTrackerId = userTrackerId;
    }

    public String getUserLoginTime() {
        return userLoginTime;
    }

    public void setUserLoginTime(String userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Map<String, String> getUserTrackerPath() {
        return userTrackerPath;
    }

    public void setUserTrackerPath(Map<String, String> userTrackerPath) {
        this.userTrackerPath = userTrackerPath;
    }

    @Override
    public int compareTo(UserDto o) {
        return o.userTrackerId.compareTo(this.userTrackerId);
    }
}
