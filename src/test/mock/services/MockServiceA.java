package mock.services;

public class MockServiceA extends MockService {
    
    protected Integer age;
    protected String name;

    public MockServiceA(String name, Integer age) {
        super();
        
        this.name = name;
        this.age = age;
    }
    
    @Override
    public void mockMethod(){
        System.out.format(toString());
    }
    
    public void say(String s, int i){
        System.out.println(s);
        System.out.println(i);
    }
    
    public void callService(IMockService service){
        service.mockMethod();
    }

    @Override
    public String toString() {
        return "MockServiceA{" + "age=" + age + ", name=" + name + '}';
    }
    
    public static void main(String[] args) {
        IMockService instance = new MockServiceA("test", 321);
        System.out.println(instance);
    }
}
