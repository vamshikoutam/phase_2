package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Participant;

public class ParticipantDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/phase_2";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Vamshi@1112";

    private static final String INSERT_PARTICIPANTS_SQL = "INSERT INTO participants"
            + "  (participantName, age, gender, batchId) VALUES " + " (?, ?, ?, ?);";

    private static final String SELECT_PARTICIPANT_BY_ID = "select participantId, participantName, age, gender, batchId from participants where participantId =?";
    private static final String SELECT_ALL_PARTICIPANTS = "SELECT p.participantId, p.participantName, p.age, p.gender, p.batchid, bc.batchName FROM participants p JOIN batches b ON p.batchId = b.batchId JOIN batch_category bc ON b.categoryId = bc.categoryId ORDER BY p.participantId";
    		
    private static final String DELETE_PARTICIPANTS_SQL = "delete from participants where participantId = ?;";
    private static final String UPDATE_PARTICIPANTS_SQL = "update participants set participantName = ?, age = ?, gender = ?, batchId = ? where participantId = ?;";

    public ParticipantDAO() {
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

    public void insertParticipant(Participant participant) throws SQLException {
        System.out.println(INSERT_PARTICIPANTS_SQL);
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PARTICIPANTS_SQL)) {
            preparedStatement.setString(1, participant.getParticipantName());
            preparedStatement.setInt(2, participant.getAge());
            preparedStatement.setString(3, participant.getGender());
            preparedStatement.setInt(4, participant.getBatchId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Participant selectParticipant(int id) {
        Participant participant = null;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARTICIPANT_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String participantName = rs.getString("participantName");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                int batchId = rs.getInt("batchId");
                //String batchName = rs.getString("batchName");
                participant = new Participant(id, participantName, age, gender, batchId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return participant;
    }

    public List<Participant> selectAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PARTICIPANTS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int participantId = rs.getInt("participantId");
                String participantName = rs.getString("participantName");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                int batchId = rs.getInt("batchId");
                String batchName = rs.getString("batchName");
                participants.add(new Participant(participantId, participantName, age, gender, batchId,batchName));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return participants;
    }

    public boolean deleteParticipant(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_PARTICIPANTS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateParticipant(Participant participant) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_PARTICIPANTS_SQL)) {
            statement.setString(1, participant.getParticipantName());
            statement.setInt(2, participant.getAge());
            statement.setString(3, participant.getGender());
            statement.setInt(4, participant.getBatchId());
            statement.setInt(5, participant.getParticipantId());

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