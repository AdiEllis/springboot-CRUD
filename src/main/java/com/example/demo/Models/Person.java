package com.example.demo.Models;

import com.example.demo.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Person {
    private int personID;
    private String lastName;
    private String firstName;
    private String address;
    private String city;


    public void delete() {
        try (Connection con = DatabaseConnection.getInstance().getConnection()){
            PreparedStatement stmt = con.prepareStatement("delete from persons where personID=(?)");
            stmt.setInt(1,this.personID);

            int j = stmt.executeUpdate();
            System.out.println(j + " record deleted");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement stmt = con.prepareStatement("update project5.persons set personID=?, lastName=?, firstName=?, address=?, city=? where personID=?");
            stmt.setInt(1, this.getPersonID());
            stmt.setString(2, this.getLastName());
            stmt.setString(3, this.getFirstName());
            stmt.setString(4, this.getAddress());
            stmt.setString(5, this.getCity());
            stmt.setInt(6, this.getPersonID());

            int j = stmt.executeUpdate();
            System.out.println(j + " record updated");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Person(int personID, String lastName, String firstName, String address, String city) {
        this.personID = personID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.city = city;
    }

    public Person(String lastName, String firstName, String address, String city) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.city = city;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

