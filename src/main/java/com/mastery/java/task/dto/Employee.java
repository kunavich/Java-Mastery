package com.mastery.java.task.dto;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@ApiModelProperty(notes = "id of the employee",name="id",required=true)
    private long id;

    @ApiModelProperty(notes = "Name of the employee",name="name",required=true)
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @ApiModelProperty(notes = "gender of the employee",name="gender",required=true)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    public Employee(){}
    public Employee (Long id,String name,Gender gender){
        this.id=id;
        this.firstName=name;
        this.gender=gender;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public Gender getGender() {
        return gender;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) &&
                gender == employee.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, gender);
    }
}