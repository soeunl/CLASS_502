package test0501;

public class Company {
    private static Company instance = new Company();

    private Company () {};

    public static Company getInstance() {
        if(instance == null) {
            Company instance = new Company();
        }
        return instance;
    }
}
