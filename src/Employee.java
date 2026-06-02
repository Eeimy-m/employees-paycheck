import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Employee {
    private String id;
    private String name;
    private String jobTitle;
    private double salary;
    private LocalDate dateOdEmployment;

    private List<Paycheck> paychecks = new ArrayList<>();

    public Employee(String id, String name, String jobTitle, double salary) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.dateOdEmployment = LocalDate.now();
    }

    public double getYearsOfService() {
        return (double) Period.between(dateOdEmployment, LocalDate.now()).toTotalMonths()/12;
    }

    public void addPaycheck(LocalDate payDay) {
        paychecks.add(new Paycheck(payDay, salary));
    }

    public void removePaycheck(Paycheck paycheck) {
        paychecks.remove(paycheck);
    }

    public Iterator<Paycheck> iteratorPaycheck() {
        return paychecks.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                ", dateOdEmployment=" + dateOdEmployment +
                ", paychecks=" + paychecks +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getDateOdEmployment() {
        return dateOdEmployment;
    }

    public List<Paycheck> getPaychecks() {
        return paychecks;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
