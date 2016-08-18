package com.marvin.bundle.framework.javafx;

import com.marvin.component.kernel.Kernel;
import com.marvin.component.templating.Engine;
import com.marvin.component.templating.template.Template;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApplication {
    
    private final Kernel kernel;

    public JavaFXApplication(Kernel kernel) {
        this.kernel = kernel;
        kernel.boot();
        
//        ResourceLoader loader = new ClassPathResourceLoader(FrameworkBundle.class);
//        IResource resource = loader.load("resources/view/fxml/test.fxml.view");
//        
//        try {
//            System.out.println(resource.getURL());
//        } catch (IOException ex) {
//            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        try {
//            Parent load = FXMLLoader.load(resource.getURL());
//        } catch (IOException ex) {
//            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        try {
//            URL myUrl = new URL("file:/test?test=true");
//            System.out.println(myUrl.getQuery());
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
//    private Parent load(String name) {
//        try {
//            File tmp = File.createTempFile("javafx.template." + name, ".fxml");
//            tmp.deleteOnExit();
//            
//            FileWriter writer = new FileWriter(tmp);
//            
//            Engine engine = kernel.getContainer().get("templating_engine", Engine.class);
//            Template template = engine.getTemplate("com/marvin/bundle/framework/resources/view/fxml/" + name + ".fxml.view");
//
//            HashMap context = new HashMap();
//            template.evaluate(writer, context);
//            
//            return FXMLLoader.load(tmp.toURI().toURL());
//        } catch(Exception ex) {
//            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return null;
//    }
    
//    private Parent load(String name) {
//        try {
//            ResourceLoader loader = new ClassPathResourceLoader(FrameworkBundle.class);
//            IResource resource = loader.load("resources/view/fxml/"+name+".fxml.view");
//            return FXMLLoader.load(resource.getURL());
//        } catch(Exception ex) {
//            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return null;
//    }
    
    private Parent load(String name) {
        try {
            StringWriter writer = new StringWriter();
            Engine engine = this.kernel.getContainer().get("templating_engine", Engine.class);
            Template template = engine.getTemplate("com/marvin/bundle/framework/resources/view/fxml/" + name + ".fxml.view");

            HashMap context = new HashMap();
            template.evaluate(writer, context);
            
            InputStream stream = new ByteArrayInputStream(writer.toString().getBytes());
            FXMLLoader loader = new FXMLLoader();
            return loader.load(stream);
        } catch(Exception ex) {
            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void display(Stage stage, String name) {
        Parent root = load(name);
        
        Scene scene = new Scene(root, 300, 250);
        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
