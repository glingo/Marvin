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
public abstract class Service implements IService {
    
    public Service() {
        super();
    }
    
    @Override
    public abstract void sayHello();
}
