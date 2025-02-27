package dk.easv.ticketmanager.dal.DataAccessObjects;

import dk.easv.ticketmanager.be.Customer;
import dk.easv.ticketmanager.dal.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private DBConnection con = new DBConnection();

    public List<Customer> getUsers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection c = con.getConnection()) {
            String sql = "SELECT * FROM customer";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            int phoneNumber  = rs.getInt("password");
            customers.add(new Customer(id, firstName, lastName, email, phoneNumber));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users", e);
        }
        return customers;
    }
//
//    public Customer add(Customer customer) throws SQLServerException {
//        try (Connection c = con.getConnection()) {
//            String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";
//            PreparedStatement stmt = c.prepareStatement(sql);
//            stmt.setInt(1, customer.getID());
//            stmt.setString(2, customer.getFirstName());
//            stmt.setString(3, customer.getLastName());
//            stmt.setString(4, customer.getEmail());
//            stmt.setInt(5, customer.getPhoneNumber());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return customer;
//    }

}
