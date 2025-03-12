package org.example.jpa1;

import dao.StudentDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Address;
import models.Courses;
import models.Filiere;
import models.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student");
        EntityManager em = emf.createEntityManager();
        StudentDao studentDao = new StudentDao();


        Student student = new Student();
        student.setCne(1);
        student.setName("Maliki Nada");
        student.setEmail("nadamaliki2105@gmail.com");
        student.setBirthday(LocalDate.of(2003, 5, 21));
        student.setNiveau(2);
        student.setNote(15.5);

        Student student2 = new Student();
        student2.setCne(2);
        student2.setName("Maliki ahmed");
        student2.setEmail("ahmed@gmail.com");
        student2.setBirthday(LocalDate.of(2008, 7, 11));
        student2.setNiveau(2);
        student2.setNote(15.5);

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);

        Address address = new Address();
        address.setStreet("mhamid");
        address.setState("Marrakech");
        address.setCity("Marrakech");

        Address address2 = new Address();
        address2.setStreet("Gueliz");
        address2.setState("Marrakech");
        address2.setCity("Marrakech");

        Courses course1 = new Courses();
        course1.setName("Java");
        course1.setDescription("Java programming language");

        Courses course2 = new Courses();
        course2.setName("C++");
        course2.setDescription("C++ programming language");

        List<Courses> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        Filiere filiere = new Filiere();
        filiere.setName("GI");
        filiere.setDescription("GI programming language");
        filiere.setStudents(students);

        address.setStudent(student);
        address2.setStudent(student2);
        filiere.setStudents(students);
        student2.setAddress(address2);
        student.setFiliere(filiere);
        student2.setFiliere(filiere);
        student.setCourses(courses);
        student.setAddress(address);
        studentDao.add_Student(student);
        studentDao.add_Student(student2);

        emf.close();
    }
}
