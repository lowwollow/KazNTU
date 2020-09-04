package kz.almaty.satbayevuniversity.data;

public class User {
    public String access_token;
    public String username;
    public String fullName;
    public User(String access_token, String username, String fullName) {
        this.access_token = access_token;
        this.username = username;
        this.fullName = fullName;
    }
}
