package halmob.healthhub.Models;

/**
 * Created by RIDVAN SIRMA on 11/9/2017.
 */

import java.util.Map;

public class Person {
    private String photoUrl;
    private String displayName;
    private String uid;
    private String eMail;
    private Map<String, Object> following;

    public Person() {

    }

    public Person(String displayName, String profile_picture) {
        this.displayName = displayName;
        this.photoUrl = photoUrl;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Map<String, Object> getFollowing() {
        return following;
    }
}