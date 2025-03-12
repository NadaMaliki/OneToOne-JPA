package org.example.jpa1;

import models.Address;
import models.Student;
import dao.StudentDao;
import dao.AddressDao;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.sort;

public class StudentBean {
    private Student student = new Student();
    private List<Student> students;
    private final StudentDao studentDao = new StudentDao();
    private final AddressDao addressDao = new AddressDao();
    private Student editingStudent;
    private Address address = new Address();
    private String Recherche;

    public StudentBean() {
        loadStudents();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getRecherche() {
        return Recherche;
    }
    public void setRecherche(String recherche) {
        Recherche = recherche;
    }

    public void addStudent() {
        if (student != null && address != null) {
            student.setAddress(address);
            address.setStudent(student);

            addressDao.add_address(address);

            loadStudents();
            student = new Student();
        }
    }

    public String startEditing(Student stud) {
        this.editingStudent = new Student(stud.getCne(),stud.getName(), stud.getEmail(), stud.getBirthday());
        return null;
    }

    public String saveEditingStudent() {
        if (editingStudent == null) {
            return null;
        }

        try {
            Student dbStudent = studentDao.getStudent(editingStudent.getCne());
            if (dbStudent != null) {
                studentDao.updateStudent(dbStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            editingStudent = null;
            loadStudents();
        }

        return null;
    }

    public String cancelEditing() {
        editingStudent = null;
        loadStudents();
        return null;
    }

    public void deleteStudent(int stud_cne) {
        Student studentToDelete = studentDao.getStudent(stud_cne);
        if (studentToDelete != null) {
            studentDao.removeStudent(studentToDelete);
            loadStudents();
        }
    }

    private void loadStudents() {
        this.students = studentDao.getAllStudents();
        for (Student student : students) {
            System.out.println(student.getName());
            System.out.println(student.getAddress());
        }
    }

    public void FinAnnee(Double note) {
        List <Student> Students = studentDao.getStudentByNote(note);
        for (Student student : Students) {
            student.setNiveau(student.getNiveau() + 1);
            studentDao.updateStudent(student);
        }
        loadStudents();
    }

    public void getStudentsByName(String nom) {
        List<Student> students = studentDao.getStudentsByName(nom);
    }
}