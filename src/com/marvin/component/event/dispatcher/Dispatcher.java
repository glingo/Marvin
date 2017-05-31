package com.marvin.component.event.dispatcher;

import com.marvin.component.event.Event;
import com.marvin.component.event.handler.Handler;
import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Dispatcher<E extends Event> implements DispatcherInterface<E> {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    // subscribers are not instanciate so we don't call subscribe
    private Map<String, Map<Integer, Handler<E>>> handlers;
    
    private Predicate<E> stopCondition;

    public Dispatcher() {}
    
    public Dispatcher(Map<String, Map<Integer, Handler<E>>> handlers) {
        this.handlers = handlers;
    }
    
    public Dispatcher(Predicate<E> stopCondition) {
        this.stopCondition = stopCondition;
    }

    public Dispatcher(Map<String, Map<Integer, Handler<E>>> handlers, Predicate<E> stopCondition) {
        this.handlers = handlers;
        this.stopCondition = stopCondition;
    }
    
    public Map<String, Map<Integer, Handler<E>>> getHandlers() {
        if(this.handlers == null) {
            this.handlers = new HashMap<>();
        }
        
        return this.handlers;
    }
    
    public Map<Integer, Handler<E>> getHandlers(String name) {
        return getHandlers().computeIfAbsent(name, (handlers) -> new HashMap<>());
    }
    
    public Handler<E> getHandler(String name, Integer priority) {
        return getHandlers(name).get(priority);
    }
    
    public boolean hasStopCondition(){
        return this.stopCondition != null;
    }
    
    public void register(SubscriberInterface subscriber) {
        subscriber.subscribe(this);
    }
    
    @Override
    public void register(String name, Integer priority, Handler<E> handler) {
        
        if (getHandler(name, priority) != null) {
            priority = Integer.MAX_VALUE;
        }
        
        getHandlers(name).put(priority, handler);
    }
    
    @Override
    public void dispatch(String name, E event) throws Exception {
        if(!getHandlers().containsKey(name)) {
            this.logger.log(Level.FINE, "No handlers for {}", name);
            return;
        }
        
        String msg = String.format("%s is dispatching an event : %s ", this.getClass().getName(), name);
        this.logger.finest(msg);
        
        Collection<Handler<E>> values = getHandlers(name).values();
        
        for (Handler<E> handler : values) {
            if(hasStopCondition() && this.stopCondition.test(event)) {
                break;
            }
            
            handler.handle(event);
        }
    }
}
