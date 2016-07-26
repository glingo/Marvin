package com.marvin.old.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileManager extends Manager<File> {

    protected static final File[] ROOTS = File.listRoots();
    
    @Override
    protected File create(String name) {
        return new File(name);
    }
    
    @Override
    protected void delete(File ressource) throws IOException {
        
        System.out.println("Updating : " + ressource.getAbsolutePath());

        if (ressource.isDirectory()) {
            File[] files = ressource.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    delete(subFile);
                }
            }
        }
        
        ressource.delete();
    }

    @Override
    protected void update(File ressource) throws IOException {
        
        if (ressource == null){
            return;
        }
        
        save(ressource);

        if (ressource.isDirectory()) {
            File[] files = ressource.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    update(subFile);
                }
            }
        }
        
        ressource.setLastModified(new Date().getTime());
    }

    @Override
    protected void save(File ressource) throws IOException {
        
        if (ressource == null){
            return;
        }
        
        if(ressource.exists()){
            return;
        }
        
        System.out.println("Saving : " + ressource.getAbsolutePath());

        File parent = ressource.getParentFile();
        
        if (parent != null) {
            parent.mkdirs();
        }
        
        ressource.createNewFile();
    }


    public static void main(String[] args) {
        FileManager manager = new FileManager();
        File test = manager.create("/tmp/test.txt");
        
        try {
            manager.update(test);
            FileWriter writer = new FileWriter(test);
            writer.write("Bonjour");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
