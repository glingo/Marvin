package com.marvin.component.container.config;

/**
 *
 * @author cdi305
 */
public class Reference {
    
    protected String target;

    public Reference(String target) {
        this.target = target;
    }
    
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
