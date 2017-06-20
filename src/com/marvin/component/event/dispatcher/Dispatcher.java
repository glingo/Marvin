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

public class Dispatcher implements DispatcherInterface {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    // subscribers are not instanciate so we don't call subscribe
    private Map<Class, Map<Integer, Handler>> handlers;
    
    private Predicate<Event> stopCondition;

    public Dispatcher() {
        this(event -> event.isPropagationStopped());
    }
    
    public Dispatcher(Map<Class, Map<Integer, Handler>> handlers) {
        this();
        this.handlers = handlers;
    }
    
    public Dispatcher(Predicate<Event> stopCondition) {
        this.stopCondition = stopCondition;
    }

    public Dispatcher(Map<Class, Map<Integer, Handler>> handlers, Predicate<Event> stopCondition) {
        this(stopCondition);
        this.handlers = handlers;
    }
    
    public Map<Class, Map<Integer, Handler>> getHandlers() {
        if(this.handlers == null) {
            this.handlers = new HashMap<>();
        }
        
        return this.handlers;
    }
    
    public Map<Integer, Handler> getHandlers(Class eventType) {
        return getHandlers().computeIfAbsent(eventType, (map) -> new HashMap<>());
    }
    
    public Handler getHandler(Class eventType, Integer priority) {
        return getHandlers(eventType).get(priority);
    }
    
    public boolean hasStopCondition(){
        return this.stopCondition != null;
    }
    
    @Override
    public void register(Class eventType, Integer priority, Handler handler) {
        if (getHandler(eventType, priority) != null) {
            priority = Integer.MAX_VALUE;
        }
        
        getHandlers(eventType).put(priority, handler);
    }
    
    @Override
    public void dispatch(Class eventType, Event event) throws Exception {
        if(!getHandlers().containsKey(eventType)) {
            this.logger.log(Level.FINE, "No handlers for {}", eventType);
            return;
        }
        
        String msg = String.format("%s is dispatching an event : %s ", this.getClass().getName(), eventType);
        this.logger.finest(msg);
        
        event.setDispatcher(this);
        Collection<Handler> values = getHandlers(eventType).values();
        for (Handler handler : values) {
            if(hasStopCondition() && this.stopCondition.test(event)) {
                break;
            }
            
            handler.handle(event);
        }
    }
}
