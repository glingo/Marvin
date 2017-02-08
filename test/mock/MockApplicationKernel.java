package mock;

import com.marvin.bundle.debug.DebugBundle;
import com.marvin.bundle.framework.FrameworkBundle;
import com.marvin.component.kernel.Kernel;
import com.marvin.component.kernel.bundle.Bundle;

public class MockApplicationKernel extends Kernel {

    public MockApplicationKernel(String environment, boolean debug) {
        super(environment, debug);
    }
    
    @Override
    protected Bundle[] registerBundles() {
        return new Bundle[]{
            new DebugBundle(),
            new FrameworkBundle()
        };
    }
    
    public static void main(String[] args) {
        Kernel kernel = new MockApplicationKernel("dev", true);
        kernel.boot();
    }
    
}
