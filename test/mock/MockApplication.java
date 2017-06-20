package mock;

import com.marvin.bundle.debug.DebugBundle;
import com.marvin.bundle.framework.Application;
import com.marvin.bundle.framework.FrameworkBundle;
import com.marvin.component.kernel.bundle.Bundle;
import java.util.Arrays;
import java.util.List;

public class MockApplication extends Application {

    public MockApplication(String env, boolean debug) {
        super(env, debug);
    }
    
    @Override
    protected List<Bundle> registerBundles() {
        return Arrays.asList(
            new DebugBundle(),
            new FrameworkBundle()
        );
    }
    
    public static void main(String[] args) {
        Application.launch(MockApplication.class, args);
    }
}
