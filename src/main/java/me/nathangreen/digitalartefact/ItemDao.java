package me.nathangreen.digitalartefact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDao implements Dao<Item> {

    private final Connection conn;

    private Item resultSetToItem(ResultSet resultSet) {
        try {
            return new Item(
                    resultSet.getString("id"),
                    resultSet.getDouble("price"),
                    resultSet.getString("name"),
                    resultSet.getInt("stock"),
                    resultSet.getInt("rating")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ItemDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Item> getById(String id) {
        String query = "select id, price, name, stock, rating from item where id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                Item result = resultSetToItem(resultSet);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getAll() {
        String query = "select id, price, name, stock, rating from item";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Item> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(resultSetToItem(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Item item) {
        String query = "insert into item (id, price, name, stock, rating) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, item.getId());
            statement.setDouble(2, item.getPrice().getNumber().doubleValue());
            statement.setString(3, item.getName());
            statement.setInt(4, item.getStock());
            statement.setInt(5, item.getRating());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Item item) {
        String query = "update item set price = ?, name = ?, stock = ?, rating = ? where id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDouble(1, item.getPrice().getNumber().doubleValue());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getStock());
            statement.setInt(4, item.getRating());
            statement.setString(5, item.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Item item) {
        String query = "delete from item where id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, item.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
