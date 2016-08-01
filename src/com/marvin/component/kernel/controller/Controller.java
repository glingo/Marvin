package com.marvin.component.kernel.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    protected Object controller;
    protected Method action;

    public Controller(Object controller, Method action) {
        super();
        this.controller = controller;
        this.action = action;
    }

    public void run() {
        synchronized (this) {
            long start = new Date().getTime();
            try {
                this.action.invoke(this.controller);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            long end = new Date().getTime();
            System.out.format("Controller executed in %s ms\n", end - start);

        }
    }

}
