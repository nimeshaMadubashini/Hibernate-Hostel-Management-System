package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;

   /* @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "room")
    private ArrayList<Reservation> roomList=new ArrayList<>();
*/

    public Room(String room_type_id, String type, String key_money, int qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

    public Room() {

    }

    public String getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(String room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey_money() {
        return key_money;
    }

    public void setKey_money(String key_money) {
        this.key_money = key_money;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
