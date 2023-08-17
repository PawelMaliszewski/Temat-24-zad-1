package com.temat24zad1;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepository {

    Connection connection;

    public TransactionRepository(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transactions", "root", "password");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int add(Transaction transaction) {
        return (transaction.getId() == null) ? addTransaction(transaction) : updateTransaction(transaction);
    }

    public Optional<Transaction> findById(Long id) {
        try {
            String sql = "SELECT * From transaction WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Math.toIntExact(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Transaction(rs.getLong(1), TransactionType.transactionTypeByString(rs.getString(2))
                        , rs.getString(3), rs.getDouble(4)
                        , LocalDate.parse(rs.getString(5))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM transaction WHERE type = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, type.getDescription());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(rs.getLong(1)
                        , TransactionType.transactionTypeByString(rs.getString(2))
                        , rs.getString(3)
                        , rs.getDouble(4)
                        , LocalDate.parse(rs.getString(5))));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return transactions;
    }

    public int delete(int id) {
        int update = 0;
        try {
            String sql = "DELETE FROM transaction WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            update = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    private int addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction (type, description, amount, date) VALUES (?, ?, ?, ?);";
        int update = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, transaction.getTransactionType().getDescription());
            ps.setString(2, transaction.getDescription());
            ps.setDouble(3, transaction.getAmount());
            ps.setString(4, transaction.getDate().toString());
            update = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }


    private int updateTransaction(Transaction transaction) {
        String sql = "UPDATE transaction SET type = ?, description = ?, amount = ?, date = ? WHERE id = ?;";
        int update = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, transaction.getTransactionType().getDescription());
            ps.setString(2, transaction.getDescription());
            ps.setDouble(3, transaction.getAmount());
            ps.setString(4, transaction.getDate().toString());
            ps.setLong(5, transaction.getId());
            update = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }
}


