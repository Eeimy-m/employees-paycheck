import java.util.HashMap;
import java.util.Map;

public class RegisterEmployeeService {
    private FakeRepository<String, Employee> fakeRepository = new FakeRepository<>();

    public RegisterEmployeeService(FakeRepository<String, Employee> fakeRepository) {
        this.fakeRepository = fakeRepository;
    }
}
