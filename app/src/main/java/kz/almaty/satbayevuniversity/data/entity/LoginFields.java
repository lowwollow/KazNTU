package kz.almaty.satbayevuniversity.data.entity;

public class LoginFields {
    private String username;
    private String password;

    public LoginFields() {
    }

    public LoginFields(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginFields{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
