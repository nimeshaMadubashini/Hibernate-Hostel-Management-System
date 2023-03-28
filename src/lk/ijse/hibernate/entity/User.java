package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
private String nic;
private String name;
private String userName;
private String password;

    public User() {
    }

    public User(String nic, String name, String userName, String password) {
        this.nic = nic;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
