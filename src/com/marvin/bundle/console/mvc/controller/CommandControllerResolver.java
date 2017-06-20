package com.marvin.bundle.console.mvc.controller;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.framework.mvc.controller.ContainerControllerResolver;
import com.marvin.bundle.framework.mvc.controller.ControllerNameParser;
import com.marvin.component.mvc.controller.ControllerReference;

public class CommandControllerResolver extends ContainerControllerResolver<Command> {

    public CommandControllerResolver(ControllerNameParser parser) {
        super(parser);
    }
    
    @Override
    public ControllerReference resolve(Command command) throws Exception {
        Object controller = command.getParameter("_controller");
        return castController(controller);
    }
}
