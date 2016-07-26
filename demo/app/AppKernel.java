/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.app;

import com.marvin.bundle.framework.FrameWorkBundle;
import test.app.bundles.test.TestBundle;
import com.marvin.old.kernel.Kernel;
import com.marvin.old.kernel.bundle.Bundle;

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
