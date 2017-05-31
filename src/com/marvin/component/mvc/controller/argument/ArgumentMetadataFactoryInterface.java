package com.marvin.component.mvc.controller.argument;

import com.marvin.component.mvc.controller.ControllerReference;
import java.util.List;

public interface ArgumentMetadataFactoryInterface {
    
    List<ArgumentMetadata> createArgumentMetadata(ControllerReference reference);
}
