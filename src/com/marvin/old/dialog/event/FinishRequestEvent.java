package com.marvin.old.dialog.event;

import com.marvin.old.dialog.Request;
import com.marvin.old.dialog.RequestHandler;

public class FinishRequestEvent extends RequestHandlerEvent {

    public FinishRequestEvent(RequestHandler handler, Request request) {
        super(handler, request);
    }

}
