import java.time.LocalDate;
import java.util.Objects;

public class Paycheck {
    private LocalDate payDay;
    private double salary;

    public Paycheck(LocalDate payDay, double salary) {
        this.payDay = payDay;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Paycheck paycheck = (Paycheck) o;
        return Double.compare(salary, paycheck.salary) == 0 && Objects.equals(payDay, paycheck.payDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payDay, salary);
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public double getSalary() {
        return salary;
    }
}
