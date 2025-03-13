package models;

import jakarta.persistence.*;

@Entity(name="Address")
@Table(name="address")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String street;
    protected String city;
    protected String state;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_cne", referencedColumnName = "cne")
    protected Student student;

    public Address() {
    }

    public Address(int id, String street, String city, String state) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
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


    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

}
