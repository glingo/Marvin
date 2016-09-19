package com.marvin.component.dialog.event;

import com.marvin.component.dialog.Request;
import com.marvin.component.dialog.RequestHandler;

public class FinishRequestEvent extends RequestHandlerEvent {

    public FinishRequestEvent(RequestHandler handler, Request request) {
        super(handler, request);
    }

}
