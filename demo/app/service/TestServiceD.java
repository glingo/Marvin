/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import java.util.Collection;

/**
 *
 * @author Dr.Who
 */
public class TestServiceD extends TestServiceB {
    
    protected Object[] collection;

    /**
     * 
     * @param reference
     * @param name
     * @param age
     * @param collection 
     */
    public TestServiceD(IService reference, String name, Integer age, Object[] collection) {
        super(reference, name, age);
        this.collection = collection;
    }
    
    @Override
    public void sayHello(){
        super.sayHello();
        System.out.format("- collection: %s\n", this.collection);
    }
    
}
