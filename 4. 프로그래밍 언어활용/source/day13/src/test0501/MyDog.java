package test0501;

public class MyDog {
    String name;
    String type;

    public MyDog(String type, String name) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + type;
    }
}
