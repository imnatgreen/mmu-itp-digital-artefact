package me.nathangreen.digitalartefact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CustomerDao implements Dao<Customer> {

    private final Connection conn;

    private Customer resultSetToCustomer(ResultSet resultSet) {
        try {
            return new Customer(
                resultSet.getString("id"),
                resultSet.getString("phone"),
                resultSet.getString("fname"),
                resultSet.getString("lname"),
                new Address(
                    resultSet.getString("add_l1"),
                    resultSet.getString("add_l2"),
                    resultSet.getString("add_town"),
                    resultSet.getString("add_postcode")
                )
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Customer> getById(String id) {
        String query = "select id, fname, lname, phone, add_l1, add_l2, add_town, add_postcode from customer where id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                Customer result = resultSetToCustomer(resultSet);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        String query = "select id, fname, lname, phone, add_l1, add_l2, add_town, add_postcode from customer";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Customer> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(resultSetToCustomer(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Customer customer) {
        String query = "insert into customer (id, fname, lname, phone, add_l1, add_l2, add_town, add_postcode) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getPhoneNumberFormatted());
            statement.setString(5, customer.getAddress().getLine1());
            statement.setString(6, customer.getAddress().getLine2());
            statement.setString(7, customer.getAddress().getTown());
            statement.setString(8, customer.getAddress().getPostcode());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Customer customer) {
        String query = "update customer set fname = ?, lname = ?, phone = ?, add_l1 = ?, add_l2 = ?, add_town = ?, add_postcode = ? where id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPhoneNumberFormatted());
            statement.setString(4, customer.getAddress().getLine1());
            statement.setString(5, customer.getAddress().getLine2());
            statement.setString(6, customer.getAddress().getTown());
            statement.setString(7, customer.getAddress().getPostcode());
            statement.setString(8, customer.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Customer customer) {
        String query = "delete from customer where id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customer.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
