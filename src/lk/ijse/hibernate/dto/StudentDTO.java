package lk.ijse.hibernate.dto;

import java.time.LocalDate;

public class StudentDTO {
    private String StudentId;
    private String name;
    private String address;
    private String ContactNo;
    private LocalDate dob;
    private String gender;

    public StudentDTO(String studentId, String name, String address, String contactNo, LocalDate dob, String gender) {

        StudentId = studentId;
        this.name = name;
        this.address = address;
        ContactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }

    public StudentDTO() {
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
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

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
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
