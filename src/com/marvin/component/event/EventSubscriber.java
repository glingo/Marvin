package com.marvin.component.event;

import com.marvin.component.event.subscriber.SubscriberInterface;

public abstract class EventSubscriber<T extends Event> implements SubscriberInterface<T> {

}
