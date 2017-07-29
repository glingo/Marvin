package com.marvin.bundle.console.mvc.controller.argument;

import com.marvin.bundle.console.command.Command;
import com.marvin.component.mvc.controller.argument.ArgumentMetadata;
import com.marvin.component.mvc.controller.argument.ArgumentValueResolverInterface;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jline.terminal.Terminal;

public class CommandParameterValueResolver implements ArgumentValueResolverInterface<Command, Terminal>  {
    private static final Logger LOG = Logger.getLogger(CommandParameterValueResolver.class.getName());

    @Override
    public boolean support(Command request, Terminal response, ArgumentMetadata argument) {
        return request.getParameter(argument.getName()) != null;
    }

    @Override
    public Object resolve(Command request, Terminal response, ArgumentMetadata argument) {
        Object result = request.getParameter(argument.getName());
        LOG.log(Level.INFO, String.format("Resolve %s(%s)", argument.getName(), result));
        return argument.getType().cast(result);
    }
}
