package com.marvin.old.manager;

//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map.Entry;

public abstract class Manager<T> {
    
    protected abstract T create(String name);
    
    protected abstract void delete(T ressource) throws Exception;

    protected abstract void update(T ressource) throws Exception;

    protected abstract void save(T ressource) throws Exception;

//    protected HashMap<String, T> objects = new HashMap<>();

//    protected abstract void add(T ressource);
    
//    protected abstract T load(String ressource);

//    public void update() throws IOException {
//        for (Entry<String, T> entry : objects.entrySet()) {
//            update(entry.getValue());
//        }
//    }

//    public final T get(String name) {
//        System.out.println("Getting : " + name);
//        T ressource = this.objects.get(name);
//        return ressource;
//    }

}
