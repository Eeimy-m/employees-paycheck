import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public static void main(String[] args) throws SQLException {
        DatabaseBuilder builder = new DatabaseBuilder();
        builder.build();
    }

    public void build() throws SQLException {
        String sqlPaycheck = """
            CREATE TABLE Paycheck (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            employee_id TEXT NOT NULL,
            pay_day TEXT NOT NULL,
            salary REAL NOT NULL,
        
            FOREIGN KEY (employee_id)
                REFERENCES Employee(id)
        );
        """;
        String sqlEmployee = """
            CREATE TABLE Employee (
            id TEXT PRIMARY KEY,
            name TEXT NOT NULL,
            job_title TEXT NOT NULL,
            salary REAL NOT NULL,
            date_of_employment TEXT NOT NULL
        );
        """;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
             final Statement statement = connection.createStatement()) {
            statement.execute(sqlEmployee);
            statement.execute(sqlPaycheck);
        }
    }
}
