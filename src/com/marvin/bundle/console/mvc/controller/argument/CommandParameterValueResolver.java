package com.marvin.bundle.console.mvc.controller.argument;

import com.marvin.bundle.console.command.Command;
import com.marvin.component.mvc.controller.argument.ArgumentMetadata;
import com.marvin.component.mvc.controller.argument.ArgumentValueResolverInterface;
import java.io.OutputStream;

public class CommandParameterValueResolver implements ArgumentValueResolverInterface<Command, OutputStream>  {

    @Override
    public boolean support(Command request, OutputStream response, ArgumentMetadata argument) {
        return request.getParameter(argument.getName()) != null;
    }

    @Override
    public Object resolve(Command request, OutputStream response, ArgumentMetadata argument) {
        Object result = request.getParameter(argument.getName());
        return argument.getType().cast(result);
    }
}
