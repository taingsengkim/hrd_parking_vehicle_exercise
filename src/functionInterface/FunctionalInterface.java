package functionInterface;
@java.lang.FunctionalInterface
interface Service{
    void operate();
}

public class FunctionalInterface {
    private static void work(Service service){
        service.operate();
    }

    static void main() {
        Service service = ()->{
            System.out.println("Demo");
            System.out.println("This is called from functional interface");
        };

        work(service);

    }
}
