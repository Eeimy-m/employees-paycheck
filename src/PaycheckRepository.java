import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class PaycheckRepository implements Repository<Paycheck, Integer> {
    public void save(Paycheck paycheck) {
        String sql = "INSERT INTO paycheck VALUES (?,?)";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, paycheck.getSalary());
            statement.setString(2, paycheck.getPayDay().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Paycheck paycheck) {
        String sql = "UPDADE paycheck SET salary = ?, payDay = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, paycheck.getSalary());
            statement.setString(2, paycheck.getPayDay().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Paycheck paycheck) {
        String sql = "DELETE FROM payheck WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, paycheck.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Paycheck> findOne(Integer id) {
        String sql = "SELECT FROM paycheck WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();

            ResultSet rs = statement.getResultSet();
            if(rs.next())
                return Optional.of(new Paycheck(rs.getInt("id"), LocalDate.parse(rs.getString("payDay")), rs.getDouble("salary")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
