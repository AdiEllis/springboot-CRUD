package com.example.demo.Controllers;

import com.example.demo.DatabaseConnection;
import com.example.demo.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public void add(String lastName, String firstName, String address, String city) {
        System.out.println("*** START - add rest ***");
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement stmt = con.prepareStatement("insert into persons (lastName,firstName,address,city) values (?,?,?,?)");
            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setString(3, address);
            stmt.setString(4, city);

            int j = stmt.executeUpdate();
            System.out.println(j + " record inserted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("*** END - add rest ***");
    }

    @RequestMapping(value = "/person/delete", method = RequestMethod.POST)
    public void delete(@RequestParam int id) {
        System.out.println("*** START delete rest ***");
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement stmt = con.prepareStatement("delete from project5.persons where personID=?");
            stmt.setInt(1, id);

            int j = stmt.executeUpdate();
            System.out.println(j + " record deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("*** END delete rest ***");
    }

    @RequestMapping(value = "/person/update", method = RequestMethod.POST)
    public void update(String lastName, String firstName, String address, String city, int personIDToUpdate) {
        System.out.println("*** START - update rest ***");
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement stmt = con.prepareStatement("update project5.persons set lastName=?, firstName=?, address=?, city=? where personID=?");
            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setString(3, address);
            stmt.setString(4, city);
            stmt.setInt(5, personIDToUpdate);

            int j = stmt.executeUpdate();
            System.out.println(j + " record updated");
        } catch (Exception e) {
            throw new RuntimeException();
        }
        System.out.println("*** END - update rest ***");
    }

    @RequestMapping(value = "/person/getAll", method = RequestMethod.GET)
    public List<Person> getAll() {
        System.out.println("*** START get all rest ***");
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement stmt = con.prepareStatement("select * from project5.persons");
            ResultSet resultSet = stmt.executeQuery();
            List<Person> personList = new ArrayList<>();
            while (resultSet.next()) {
                Person person = new Person(resultSet.getInt("personID"), resultSet.getString("lastName"),
                        resultSet.getString("firstName"), resultSet.getString("address"), resultSet.getString("city"));
                personList.add(person);
            }
            System.out.println(personList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        System.out.println("*** END get all rest ***");
        return null;
    }
}
