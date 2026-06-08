import java.time.LocalDate;
import java.util.*;

public class Company {
    private Map<String, Employee> employees = new HashMap<>();

    public void hire(String id, String name, String jobTitle, double salary) {
        employees.put(id ,new Employee(id, name, jobTitle, salary));
    }

    public void fire(String id) {
        employees.remove(id);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    public List<Employee> getEmployees(String jobTitle) {
        return employees.values().stream()
                .filter(e -> e.getJobTitle().equals(jobTitle))
                .toList();
    }

    public void pay(String id) {
        if(!employees.containsKey(id)) throw new IllegalArgumentException("Employee with id " + id + " does not exist");
//        employees.get(id).addPaycheck(LocalDate.now());
    }

    public void increaseSalary(String id, double salary) {
        if(!employees.containsKey(id)) throw new IllegalArgumentException("Employee with id " + id + " does not exist");
        employees.get(id).setSalary(salary);
    }

    public double averageSalary(String jobTitle) {
        return employees.values().stream()
                .filter(e -> e.getJobTitle().equals(jobTitle))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
    }

    public double averageSalary(LocalDate startDate, LocalDate endDate) {
        return employees.values().stream()
                .map(Employee::getPaychecks)
                .flatMap(Collection::stream)
                .filter(p -> p.getPayDay().isAfter(startDate)
                        && p.getPayDay().isBefore(endDate))
                .mapToDouble(Paycheck::getSalary)
                .average()
                .orElse(0);
    }
}
