package com.marvin.component.kernel.controller.argument;

import com.marvin.component.kernel.controller.ControllerReference;
import java.util.List;

public interface ArgumentMetadataFactoryInterface {
    
    List<ArgumentMetadata> createArgumentMetadata(ControllerReference reference);
}
