package lk.ijse.hibernate.dto;

public class UserDto {
private String nic;
private String name;
private String userName;
private String password;

    public UserDto(String nic, String name, String userName, String password) {
        this.nic = nic;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }
    public UserDto(String nic, String userName, String password) {
        this.nic = nic;
        this.userName = userName;
        this.password = password;
    }
    public UserDto() {
    }

    public UserDto(String username, String password) {
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
