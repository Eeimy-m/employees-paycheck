import java.time.LocalDate;
import java.util.Objects;

public class Paycheck {
    private final int id;
    private final LocalDate payDay;
    private final double salary;

    public Paycheck(int id, LocalDate payDay, double salary) {
        this.payDay = payDay;
        this.salary = salary;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Paycheck paycheck = (Paycheck) o;
        return id == paycheck.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}
