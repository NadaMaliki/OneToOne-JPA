package models;

import jakarta.persistence.*;

@Entity(name="Address")
@Table(name="address")
public class Address {
    protected String street;
    protected String city;
    protected String state;
    protected Student student;

    public Address() {
    }

    public Address(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    @Id
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @OneToOne
//    @JoinColumn(name = "cne", referencedColumnName = "cne")
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

}
