package com.marvin.bundle.javafx;

import com.marvin.component.kernel.Kernel;
import com.marvin.component.templating.Engine;
import com.marvin.component.templating.template.Template;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JavaFXApplication {
    
    private final Kernel kernel;
//    private final FXMLLoader loader;
//    private final Stage stage;

    public JavaFXApplication(Kernel kernel) {
        this.kernel = kernel;
    }
    
    @FXML
    public void request(Event event) {
//        System.out.println(event.getSource());
        Object source = event.getSource();
        
        if(source instanceof Button) {
            Button button = (Button) source;
//            button.getScene().getWindow();
            System.out.println(button.getId());
            this.request("/" + button.getId().replace("_", "/"), (Stage) button.getScene().getWindow());
        }
//        System.out.println("request");
    }
    
//    @FXML
    public void request(String name, Stage stage) {
        this.kernel.boot();
        StringWriter writer = new StringWriter();
        
        try {
//            this.kernel.handle(name, writer);

            InputStream stream = new ByteArrayInputStream(writer.toString().getBytes());

            FXMLLoader loader = new FXMLLoader();
            loader.setController(this);
            Parent parent = loader.load(stream);
            
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            
        } catch (Exception ex) {
            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
            stage = new Stage();
            Pane pane = new FlowPane();
            pane.getChildren().add(new Label("Une erreur est survenue !"));
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }

    }
    
    
//    public void display(Stage stage, String name) {
//        Parent root = load(name);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        stage.setTitle("Hello World!");
//        stage.setScene(scene);
//        stage.requestFocus();
//        stage.show();
//    }
    
    
//    private Parent load(String name) {
//        try {
//            this.kernel.boot();
//            StringWriter writer = new StringWriter();
//            Engine engine = this.kernel.getContainer().get("templating_engine", Engine.class);
//            Template template = engine.getTemplate("com/marvin/bundle/framework/resources/view/fxml/" + name + ".fxml.view");
//
//            HashMap context = new HashMap();
//            template.evaluate(writer, context);
//            
//            InputStream stream = new ByteArrayInputStream(writer.toString().getBytes());
//            return this.loader.load(stream);
//        } catch(Exception ex) {
//            Logger.getLogger(JavaFXApplication.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return null;
//    }
    
    
        
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
    
}
