package lk.ijse.hibernate.dto;

import com.jfoenix.controls.JFXButton;

public class KeyPaymentDTO {
    private String resId;
    private String status;
    private String room_Type_id;
    private String type;
    private String student_id;
    private String name;
    private JFXButton btn;

    public KeyPaymentDTO(String resId, String status, String room_Type_id, String type, String student_id, String name, JFXButton btn) {
        this.resId = resId;
        this.status = status;
        this.room_Type_id = room_Type_id;
        this.type = type;
        this.student_id = student_id;
        this.name = name;
        this.btn = btn;
    }

    public KeyPaymentDTO(String resId, String status, String room_Type_id, String type, String student_id, String name) {
        this.resId = resId;
        this.status = status;
        this.room_Type_id = room_Type_id;
        this.type = type;
        this.student_id = student_id;
        this.name = name;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoom_Type_id() {
        return room_Type_id;
    }

    public void setRoom_Type_id(String room_Type_id) {
        this.room_Type_id = room_Type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
