package com.marvin.bundle.console.subscriber;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.framework.mvc.event.FilterRequestEvent;
import com.marvin.component.event.dispatcher.DispatcherInterface;
import com.marvin.component.event.handler.Handler;
import com.marvin.component.event.subscriber.Subscriber;

import com.marvin.component.routing.Router;

import java.util.HashMap;
import java.util.Map;

public class RouterSubscriber extends Subscriber {

    private final Router router;

    public RouterSubscriber(Router router) {
        this.router = router;
    }
    
    public Handler<FilterRequestEvent> onRequest(){
        return event -> {
            Object request = event.getRequest();

            if (request instanceof Command) {
                Command command = (Command) event.getRequest();

                String[] parts = command.getLine().split(" ");

                command.setLine(parts[0]);

                Map<String, Object> attributes = this.router.match(command.getLine());

                if(attributes == null) {
                    attributes = new HashMap<>();
                }

    //                action = attributes.entrySet().stream()
    //                        .filter((entry) -> entry.getValue() != null)
    //                        .map((entry) -> "-".concat(entry.getKey()).concat("=").concat(entry.getValue().toString()))
    //                        .map(action::concat)
    //                        .collect(Collectors.joining(" "));

                command.setParameters(attributes);

            }
        };
    }
    
    @Override
    public void subscribe(DispatcherInterface dispatcher) {
        dispatcher.register(FilterRequestEvent.class, onRequest());
    }
}
