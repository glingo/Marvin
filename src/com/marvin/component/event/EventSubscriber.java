package com.marvin.component.event;

import com.marvin.component.event.subscriber.SubscriberInterface;
import java.util.logging.Logger;

public abstract class EventSubscriber<T extends Event> implements SubscriberInterface<T> {

    protected final Logger logger = Logger.getLogger(getClass().getName());

}
