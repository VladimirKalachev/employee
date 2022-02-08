package com.employee.model;

import com.opencsv.bean.CsvBindByPosition;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @CsvBindByPosition(position = 0)
    private Long id;

    @Column(name = "first_name")
    @CsvBindByPosition(position = 1)
    private String firstName;

    @Column(name = "last_name")
    @CsvBindByPosition(position = 2)
    private String lastName;

    @Column(name = "company_id")
    @CsvBindByPosition(position = 3)
    private int companyId;

    @Column(name = "role")
    @CsvBindByPosition(position = 4)
    private String role;

    public User() {
    }

    public User(String firstName, String lastName, int companyId, String role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyId;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyId=" + companyId +
                ", role='" + role + '\'' +
                '}';
    }
}
