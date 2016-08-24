package com.marvin.component.kernel.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    protected Object holder;
    protected Method action;

    public Controller(Object holder, Method action) {
        super();
        this.holder = holder;
        this.action = action;
    }

    public void run() {
        synchronized (this) {
            long start = new Date().getTime();
            
            try {
                
                this.action.invoke(this.holder);
                
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            long end = new Date().getTime();
            
            System.out.format("Controller (%s, %s) executed in %s ms\n", holder, action, end - start);

        }
    }

    public Object getHolder() {
        return holder;
    }

    public Method getAction() {
        return action;
    }

    public void setAction(Method action) {
        this.action = action;
    }

    public void setHolder(Object holder) {
        this.holder = holder;
    }
}
