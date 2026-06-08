public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        Repository<Employee, String> employeesRepo = new EmployeeRepository();

        String id = "SC3050742";
        Employee eimy = new Employee(id, "Elisa Eimy Miura", "Back end dev", 5_000);

        for(int i = 0; i < 5; i++) {
            int count = i + 1;
            Employee employee = new Employee("SC" + count, "employee" + count, "JobTitle" + count, 1_000);
        }

//        employeesRepo.save(eimy);
        employeesRepo.findOne(id).ifPresentOrElse(System.out::println, () -> System.out.println("Employee not found"));
    }
}
