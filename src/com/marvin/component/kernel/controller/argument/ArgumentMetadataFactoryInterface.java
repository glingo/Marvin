package com.marvin.bundle.framework.controller.argument;

import com.marvin.bundle.framework.controller.ControllerReference;
import java.util.List;

public interface ArgumentMetadataFactoryInterface {
    
    List<ArgumentMetadata> createArgumentMetadata(ControllerReference controller);
}
