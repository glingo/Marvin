package com.marvin.component.event.subscriber;

import com.marvin.component.event.dispatcher.DispatcherInterface;
import java.util.logging.Logger;

public abstract class Subscriber implements SubscriberInterface {
    
    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public abstract void subscribe(DispatcherInterface dispatcher);
}
