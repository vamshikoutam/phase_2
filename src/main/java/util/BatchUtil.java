package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchUtil {
    public static List<Map<String, Object>> getBatches() {
        List<Map<String, Object>> batches = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phase_2", "root", "Vamshi@1112");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT b.batchId, b.schedule, b.instructor, c.batchName FROM batches b JOIN batch_category c ON b.categoryId = c.categoryId ORDER BY b.batchId;");

            while (resultSet.next()) {
                Map<String, Object> batch = new HashMap<>();
                batch.put("batchId", resultSet.getInt("batchId"));
                batch.put("batchName", resultSet.getString("batchName"));
                batches.add(batch);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }
    
    public static List<Map<String, Object>> getCategories() {
        List<Map<String, Object>> categories = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phase_2", "root", "Vamshi@1112");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM batch_category");

            while (resultSet.next()) {
                Map<String, Object> category = new HashMap<>();
                category.put("categoryId", resultSet.getInt("categoryId"));
                category.put("batchName", resultSet.getString("batchName"));
                categories.add(category);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}