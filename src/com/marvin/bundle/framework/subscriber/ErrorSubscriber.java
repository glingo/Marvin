package com.marvin.bundle.framework.subscriber;

import com.marvin.bundle.framework.mvc.event.GetResultForExceptionEvent;
import com.marvin.bundle.framework.mvc.event.HandlerEvents;
import com.marvin.component.event.dispatcher.DispatcherInterface;
import com.marvin.component.event.handler.Handler;
import com.marvin.component.event.subscriber.Subscriber;
import com.marvin.component.mvc.ModelAndView;
import java.io.IOException;

public class ErrorSubscriber extends Subscriber {
    
    private final String errorPath;

    public ErrorSubscriber(String errorPath) {
        this.errorPath = errorPath;
    }
    
    private void onException(GetResultForExceptionEvent e) throws IOException {
        // try to get the old model ?
        e.setResult(ModelAndView.builder()
                .model("exception", e.getException())
                .model("result", e.getResult())
                .model("request", e.getRequest())
                .view(this.errorPath)
                .build());
    }
    
    public Handler<GetResultForExceptionEvent> onException() {
        return e -> onException(e);
    }

    @Override
    public void subscribe(DispatcherInterface dispatcher) {
        dispatcher.register(HandlerEvents.EXCEPTION, onException());
    }
}
