package com.grandcircus.models;

import javax.persistence.*;

/**
 * Created by PAS8 on 5/23/2017.
 */
@Entity
@Table(name = "Users", schema = "RideShare", catalog = "")
public class UsersEntity {
    private int userId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String homePhone;

    @Id
    @Column(name = "UserID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "FirstName", nullable = false, length = -1)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = false, length = -1)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "Address", nullable = false, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "City", nullable = false, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "State", nullable = false, length = -1)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "PostalCode", nullable = false, length = -1)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "Country", nullable = true, length = -1)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "HomePhone", nullable = true, length = -1)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userId != that.userId) return false;
        if (firstName != null ? !firstName.equals ( that.firstName ) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals ( that.lastName ) : that.lastName != null) return false;
        if (address != null ? !address.equals ( that.address ) : that.address != null) return false;
        if (city != null ? !city.equals ( that.city ) : that.city != null) return false;
        if (state != null ? !state.equals ( that.state ) : that.state != null) return false;
        if (postalCode != null ? !postalCode.equals ( that.postalCode ) : that.postalCode != null) return false;
        if (country != null ? !country.equals ( that.country ) : that.country != null) return false;
        if (homePhone != null ? !homePhone.equals ( that.homePhone ) : that.homePhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName != null ? firstName.hashCode () : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode () : 0);
        result = 31 * result + (address != null ? address.hashCode () : 0);
        result = 31 * result + (city != null ? city.hashCode () : 0);
        result = 31 * result + (state != null ? state.hashCode () : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode () : 0);
        result = 31 * result + (country != null ? country.hashCode () : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode () : 0);
        return result;
    }
}
