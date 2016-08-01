/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.bundles.test.TestBundle;
import com.marvin.bundle.framework.FrameWorkBundle;
import com.marvin.component.kernel.Kernel;
import com.marvin.component.kernel.bundle.Bundle;

/**
 *
 * @author Dr.Who
 */
public class AppKernel extends Kernel {

    @Override
    protected Bundle[] registerBundles() {
        return new Bundle[]{
            new FrameWorkBundle(),
            new TestBundle(),
        };
    }
    
}
