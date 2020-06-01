package com.asama.onlinequiz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AppUser {

    @Id
    @NotBlank
    @Length(min = 5)
    private String id;

    @Length(min = 6)
    private String password;

    @Column(name = "full_name", columnDefinition = "nvarchar(255)")
    @Length(min = 10, max = 200)
    private String fullName;

    @Length(min = 9, max = 12)
    @Column(name = "indendity_num")
    private String indendityNum;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    @NotNull
    private Boolean sex;

    @Length(min = 10, max = 11)
    private String phone;

    @Column(columnDefinition = "nvarchar(255)")
    @NotEmpty
    private String address;

    private String image;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIndendityNum() {
        return indendityNum;
    }

    public void setIndendityNum(String indendityNum) {
        this.indendityNum = indendityNum;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
