package com.marvin.old.dependency;

/**
 *
 * @author Dr.Who
 */
public class Reference {
    
    protected String id;

    public Reference(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
