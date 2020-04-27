package example.abhiandriod.tablelayoutexample.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String displayName;
    private String email;
    private String password;
    private int privilege;

    public User(String userId, String displayName, String email, String password, int privilege) {
        this.userId = userId;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.privilege = privilege;
    }

    public User() {
        this.userId = "";
        this.displayName = "";
        this.email = "";
        this.password = "";
        this.privilege = 0;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public int getPrivilege() {
        return this.privilege;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
