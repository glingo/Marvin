package com.marvin.component.mvc.controller.argument;

import com.marvin.component.mvc.controller.ControllerReference;
import java.util.List;

@FunctionalInterface
public interface ArgumentResolverInterface extends ArgumentMetadataFactory  {
    List<Object> getArguments(Object request, Object response, ControllerReference controller);
}
