package mock.services;

import java.util.Collection;

public class MockServiceC extends MockServiceB {
    
    protected Collection collection;

    public MockServiceC(IMockService ref, String name, Integer age, Collection collection) {
        super(ref, name, age);
        this.collection = collection;
    }
    
    @Override
    public void mockMethod(){
        super.mockMethod();
        System.out.format(toString());
    }

    @Override
    public String toString() {
        return "MockServiceC{parent:" + super.toString() + "collection=" + collection + '}';
    }
}
