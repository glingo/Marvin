/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.kernel.dialog.event;

import com.marvin.component.kernel.dialog.Request;
import com.marvin.component.kernel.dialog.RequestHandler;
import com.marvin.component.kernel.dialog.Response;

/**
 *
 * @author caill
 */
public class GetResponseForControllerResultEvent extends RequestHandlerEvent {
    
    private Object response;

    public GetResponseForControllerResultEvent(RequestHandler handler, Request request, Object response) {
        super(handler, request);
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
        this.stopEventPropagation();
    }
    
    public boolean hasResponse(){
        return this.response != null;
    }
}
