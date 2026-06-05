import java.sql.*;
import java.util.Optional;

public class EmployeeRepository implements Repository<Employee>{
    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employee VALUES (?,?,?,?,?)";

        try (final Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getKey());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getJobTitle());
            statement.setDouble(4, employee.getSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
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

    @Override
    public Optional<Employee> findOne(Employee employee) {
        String sql = "SELECT FROM employee WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getKey());
            final ResultSet rs = statement.executeQuery();
            if (rs.next())
                return Optional.of(new Employee
                        (rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("jobTitle"),
                        rs.getDouble("salary")
                        ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
