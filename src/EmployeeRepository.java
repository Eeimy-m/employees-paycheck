import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class EmployeeRepository implements Repository<Employee, String> {
    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO Employee VALUES (?,?,?,?,?)";

        try (final Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getJobTitle());
            statement.setDouble(4, employee.getSalary());
            statement.setString(5, employee.getDateOdEmployment().toString());
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
            statement.setString(4, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Employee employee) {
        String sql = "DELETE FROM Employee WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> findOne(String id) {
        String sql = "SELECT * FROM Employee WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next())
                return Optional.of(new Employee
                        (rs.getString("id"),
                                rs.getString("name"),
                                rs.getString("job_title"),
                                rs.getDouble("salary"),
                                LocalDate.parse(rs.getString("date_of_employment"))
                        ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
