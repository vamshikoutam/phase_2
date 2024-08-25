package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Batch;

public class BatchDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/phase_2";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Vamshi@1112";

    private static final String INSERT_BATCH_SQL = "INSERT INTO batches"
            + "  (schedule, instructor, categoryId) VALUES " + " (?, ?, ?);";

    private static final String SELECT_BATCH_BY_ID = "SELECT b.batchId, b.schedule, b.instructor, c.batchName FROM batches b JOIN batch_category c ON b.categoryId = c.categoryId WHERE b.batchId =?";
    private static final String SELECT_ALL_BATCHES = "SELECT b.batchId, b.schedule, b.instructor, c.batchName FROM batches b JOIN batch_category c ON b.categoryId = c.categoryId ORDER BY b.batchId;";

    private static final String DELETE_BATCH_SQL = "DELETE FROM batches WHERE batchId = ?;";
    private static final String UPDATE_BATCH_SQL = "UPDATE batches SET categoryId = ?, schedule = ?, instructor = ? WHERE batchId = ?;";

    public BatchDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertBatch(Batch batch) throws SQLException {
        System.out.println(INSERT_BATCH_SQL);
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BATCH_SQL)) {
            
            preparedStatement.setString(1, batch.getSchedule());
            preparedStatement.setString(2, batch.getInstructor());
            preparedStatement.setInt(3, batch.getCategoryId());
            System.out.println("Batch: " + batch);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Batch selectBatch(int id) {
        Batch batch = null;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BATCH_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String batchName = rs.getString("batchName");
                String schedule = rs.getString("schedule");
                String instructor = rs.getString("instructor");
                batch = new Batch(id, batchName, schedule, instructor);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return batch;
    }

    public List<Batch> selectAllBatches() {
        List<Batch> batches = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BATCHES)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int batchId = rs.getInt("batchId");
                String batchName = rs.getString("batchName");
                String schedule = rs.getString("schedule");
                String instructor = rs.getString("instructor");
                batches.add(new Batch(batchId, batchName, schedule, instructor));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return batches;
    }

    public boolean deleteBatch(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BATCH_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateBatch(Batch batch) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_BATCH_SQL)) {
            statement.setInt(1, batch.getCategoryId());
            statement.setString(2, batch.getSchedule());
            statement.setString(3, batch.getInstructor());
            statement.setInt(4, batch.getBatchId());
            System.out.println(statement);
            System.out.println(batch);
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
