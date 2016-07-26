package com.marvin.old.locator;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author Dr.Who
 * @param <T>
 */
public class QueuedLocator<T> extends FileLocator {
    
    protected Deque<String> queue = new ArrayDeque<>();
    protected List<String> paths;

    public QueuedLocator(String path) {
        this.paths = Arrays.asList(path);
    }
    
    public QueuedLocator(List<String> paths) {
        this.paths = paths;
    }
    
    public QueuedLocator(String[] paths) {
        this.paths = Arrays.asList(paths);
    }

    @Override
    public File locate(String name) {
        this.queue.addAll(this.paths);
        File resource = null;
        
        do {
          resource = super.locate(queue.poll() + File.separator + name);
        } while(resource == null && !queue.isEmpty());
        
        return resource;
    }

}
