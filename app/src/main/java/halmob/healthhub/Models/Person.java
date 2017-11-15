package halmob.healthhub.Models;

/**
 * Created by RIDVAN SIRMA on 11/9/2017.
 */

import java.util.Map;

public class Person {
    private String photoUrl;
    private String displayName;
    private String uid;
    private String email;

    private String userType;
    private Map<String, Object> following;

    public Person() {
        userType = "Healthman";
    }

    public Person(String displayName, String profile_picture) {
        this.displayName = displayName;
        this.photoUrl = photoUrl;
        userType = "Healthman";
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getFollowing() {
        return following;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}