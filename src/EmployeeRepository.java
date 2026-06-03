import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EmployeeRepository {
    public void save(Employee employee) {
        String sql = "INSERT INTO employee VALUES (? ? ? ? ?)";

        try (final Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getKey());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getJobTitle());
            statement.setDouble(4, employee.getSalary());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, jobTitle = ?, salary = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getJobTitle());
            statement.setDouble(3, employee.getSalary());
            statement.setString(4, employee.getKey());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Employee employee) {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getKey());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
