package com.marvin.component.kernel.dialog.event;

import com.marvin.component.kernel.dialog.Request;
import com.marvin.component.kernel.dialog.RequestHandler;

public class FinishRequestEvent extends RequestHandlerEvent {

    public FinishRequestEvent(RequestHandler handler, Request request) {
        super(handler, request);
    }

}
