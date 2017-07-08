package com.marvin.bundle.console.resources.view.error;

import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.mvc.model.Model;
import com.marvin.component.mvc.view.View;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ErrorView extends View<Object, OutputStream> {

    public ErrorView(String name) {
        super(name);
    }

    @Override
    public void load() throws Exception {
        System.out.println("Loading ErrorView ...");
    }
    
    @Override
    public void render(Handler<Object, OutputStream> handler, Map<String, Object> model, Object request, OutputStream response) throws Exception {
        Model m = new Model(model);
        int w = 100, h = 100;
        Writer writer = new OutputStreamWriter(response);
        
        // head 
        for (int i = 0; i < w; i++) {
            writer.append("-");
        }
        writer.append("\n");
        
        // title
        String title = "Error";
        int center = ((w/2) - (title.length() / 2) - 1);
        for (int i = 0; i < center; i++) {
            writer.append(" ");
        }
        writer.append(title);
        for (int i = 0; i < center; i++) {
            writer.append(" ");
        }
        writer.append("\n");
        
        // body
        Optional<Exception> exception = m.get("exception", Exception.class);
        if (exception.isPresent()) {
            writer
                .append("Exception : \n")
                .append(exception.get().getMessage())
                .append("|\n|")
                .append(Stream.of(exception.get().getStackTrace())
                    .map(StackTraceElement::toString)
                    .collect(Collectors.joining("|\n|")))
                .append("|\n|");
        }
        
        Optional<Object> result = m.get("result", Object.class);
        if (result.isPresent()) {
            writer
                .append("Result : \n")
                .append(Objects.toString(result.get(), "null"))
                .append("\n");
        }
        
        writer
            .append("Request : \n")
            .append(Objects.toString(request, "null"))
            .append("\n");
        
        // footer
        for (int i = 0; i < w; i++) {
            writer.append("-");
        }
        
        writer.flush();
    }
}
