package lk.ijse.hibernate.dto;

import java.time.LocalDate;

public class StudentDTO {
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;

    public StudentDTO(String student_id, String name, String address, String contact_no, LocalDate dob, String gender) {

       this. student_id = student_id;
        this.name = name;
        this.address = address;
      this.contact_no = contact_no;
        this.dob = dob;
        this.gender = gender;
    }

    public StudentDTO() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
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
}
