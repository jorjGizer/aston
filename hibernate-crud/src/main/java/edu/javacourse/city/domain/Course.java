package edu.javacourse.city.domain;

import javax.persistence.*;
import java.sql.Ref;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ro_course")
public class Course extends AbstractEntity{
    private String name;
    private int number;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
            mappedBy = "courses")
    Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
