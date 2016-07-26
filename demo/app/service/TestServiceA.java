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
public class TestServiceA extends Service {
    
    protected Integer age;
    protected String name;

    /**
     * 
     * @param name
     * @param age 
     */
    public TestServiceA(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }
    
    @Override
    public void sayHello(){
        System.out.format("Bonjour je suis le service %s\n", this.name);
        System.out.format("j'ai %s ans\n", this.age);
    }
    
}
