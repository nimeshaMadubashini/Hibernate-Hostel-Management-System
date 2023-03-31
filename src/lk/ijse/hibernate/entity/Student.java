package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;


@Entity
public class Student {
    @Id
    @Column(name = "student_id")
    private String id;
  private   String name;
   private String address;
    @Column(name = "contact_no")
 private    String contactNum;
  private   LocalDate dob;
  private   String gender;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "student")
     private ArrayList<Reservation> studentList=new ArrayList<>();

    public Student(String id, String name, String address, String contactNum, LocalDate dob, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNum = contactNum;
        this.dob = dob;
        this.gender = gender;
    }

    public Student(String id, String name, String address, String contactNum, LocalDate dob, String gender, ArrayList<Reservation> studentList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNum = contactNum;
        this.dob = dob;
        this.gender = gender;
        this.studentList = studentList;
    }

    public Student() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Reservation> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Reservation> studentList) {
        this.studentList = studentList;
    }
}
