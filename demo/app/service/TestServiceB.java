/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

/**
 *
 * @author Dr.Who
 */
public class TestServiceB extends TestServiceA {
    
    protected IService reference;

    /**
     * 
     * @param reference
     * @param name
     * @param age 
     */
    public TestServiceB(IService reference, String name, Integer age) {
        super(name, age);
        this.reference = reference;
    }
    
    @Override
    public void sayHello(){
        super.sayHello();
        System.out.println("Je peux appeler le service A :");
        this.reference.sayHello();
    }
    
}
