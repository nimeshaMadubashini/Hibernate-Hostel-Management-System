package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity

public class Reservation {
    @Id
    private String resId;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "room_type_id",referencedColumnName = "room_type_id")
    private Room room;

    private String status;

    public Reservation(String resId, LocalDate date, Student student, Room room, String status) {
        this.resId = resId;
        this.date = date;
        this.student = student;
        this.room = room;
        this.status = status;
    }

    public Reservation() {
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
