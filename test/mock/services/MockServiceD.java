package mock.services;

import java.util.Arrays;

public class MockServiceD extends MockServiceB {
    
    protected Object[] array;

    public MockServiceD(IMockService ref, String name, Integer age, Object[] array) {
        super(ref, name, age);
        this.array = array;
    }
    
    @Override
    public void mockMethod(){
        super.mockMethod();
        System.out.format(toString());
    }

    @Override
    public String toString() {
        return "MockServiceD{parent=" + super.toString() + "array=" + Arrays.toString(array) + '}';
    }
    
}
