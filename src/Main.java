public class Main {
    static void main() {
        Company company = new Company();
        Repository<Employee> employeesRepo = new EmployeeRepository();

        Employee eimy = new Employee("SC3050742", "Elisa Eimy Miura", "Back end dev", 5_000);

        for(int i = 0; i < 5; i++) {
            int count = i + 1;
            Employee employee = new Employee("SC" + count, "employee" + count, "JobTitle" + count, 1_000);
        }

        employeesRepo.save(eimy);
        employeesRepo.findOne(eimy).ifPresentOrElse(System.out::println, () -> System.out.println("Employee not found"));
    }
}
