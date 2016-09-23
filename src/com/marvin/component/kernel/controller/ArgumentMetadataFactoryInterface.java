package com.marvin.component.kernel.controller;

import java.util.List;

public interface ArgumentMetadataFactoryInterface {
    
    List<ArgumentMetadata> createArgumentMetadata(ControllerReference controller);
}
