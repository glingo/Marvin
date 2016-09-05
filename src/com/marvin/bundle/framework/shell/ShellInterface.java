package com.marvin.bundle.framework.shell;

import java.io.File;

public interface ShellInterface extends ShellPromptAccessor {
    
    void promptLoop();
    
    void executeCommand(String line);
    
    void setDeveloppementMode(boolean developpementMode);
    
    boolean isDeveloppementMode();
    
    File getHome();
}
