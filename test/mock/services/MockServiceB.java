package mock.services;

public class MockServiceB extends MockServiceA {
    
    protected IMockService reference;

    public MockServiceB(IMockService reference, String name, Integer age) {
        super(name, age);
        this.reference = reference;
    }
    
    @Override
    public void mockMethod(){
        super.mockMethod();
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "MockServiceB{" + "reference=" + reference + '}';
    }
    
}
