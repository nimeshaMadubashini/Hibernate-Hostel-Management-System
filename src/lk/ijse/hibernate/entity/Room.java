package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Room {
    @Id
    @Column(name = "room_type_id")
    private String roomTypeId;
    private String type;
    @Column(name = "key_money")
    private String keyMoney;
    private int qty;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "room")
    private ArrayList<Reservation> roomList=new ArrayList<>();

    public Room(String roomTypeId, String type, String keyMoney, int qty) {
        this.roomTypeId = roomTypeId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public Room(String roomTypeId, String type, String keyMoney, int qty, ArrayList<Reservation> roomList) {
        this.roomTypeId = roomTypeId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
        this.roomList = roomList;
    }

    public Room() {
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ArrayList<Reservation> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Reservation> roomList) {
        this.roomList = roomList;
    }
}
