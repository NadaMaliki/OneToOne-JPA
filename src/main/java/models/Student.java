package models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name="Student")
@Table(name="student")
public class Student {
    @Id
    @Column(updatable = false)
    private int cne;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;
    private LocalDate birthday;

    @Transient
    private int age;
    private int niveau;
    private double note;

    private Address address;



    public Student() {}

    public Student(int cne, String name, String email, LocalDate birthday, int niveau, double Note) {
        this.cne = cne;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.niveau = niveau;
        this.note = Note;
    }

    public Student(int cne, String name, String email, LocalDate birthday) {
        this.cne = cne;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public int getCne() {
        return cne;
    }

    public void setCne(int cne) {
        this.cne = cne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getNiveau() {
        return niveau;
    }
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    public double getNote() {
        return note;
    }
    public void setNote(double Note) {
        this.note = Note;
    }

    @OneToOne(mappedBy = "student")
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        if (birthday != null) {
            return Period.between(birthday, LocalDate.now()).getYears();
        }
        return 0;
    }


}
