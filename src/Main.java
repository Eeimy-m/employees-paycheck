public class Main {
    static void main() {
        FakeRepository repository = new FakeRepository();
        RegisterEmployeeService service = new RegisterEmployeeService(repository);

        for(int i = 0; i < 10; i++) {
            Employee employee = new Employee("emp" + (i + 1), "Employee" + (i + 1), "Job title" + (i + 1), 1_000);
            repository.save(employee);
        }
    }
}
