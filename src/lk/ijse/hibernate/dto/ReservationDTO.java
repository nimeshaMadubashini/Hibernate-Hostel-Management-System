package lk.ijse.hibernate.dto;

import java.time.LocalDate;

public class ReservationDTO {
    private String resId;
    private LocalDate date;
    private StudentDTO student;
    private RoomDTO room;
    private String status;

    public ReservationDTO(String resId, LocalDate date, StudentDTO student, RoomDTO room, String status) {
        this.resId = resId;
        this.date = date;
        this.student = student;
        this.room = room;
        this.status = status;
    }

    public ReservationDTO() {
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
