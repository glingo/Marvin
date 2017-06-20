package com.marvin.bundle.console.mvc.view;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.mvc.model.Model;
import com.marvin.component.mvc.view.View;
import com.marvin.component.mvc.view.ViewInterface;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class ConsoleView extends View<Command, OutputStream> {
    
    private int w = 100;
    private int h = 100;
    
    public ConsoleView(String name) {
        super(name);
    }
    
    public ConsoleView(String name, int h, int w) {
        super(name);
        this.h = h;
        this.w = w;
    }

    @Override
    public void load() throws Exception {
    }

    @Override
    public void render(Handler<Command, OutputStream> handler, Map<String, Object> model, Command request, OutputStream response) throws Exception {
        Model m = new Model(model);
        
        title(getTitle(handler, m, request, response), response);
        body(getBody(handler, m, request, response), response);
        line(this.w, response);
        
        response.flush();
    }

    protected abstract String getTitle(Handler<Command, OutputStream> handler, Model model, Command request, OutputStream response) throws Exception;
    protected abstract String getBody(Handler<Command, OutputStream> handler, Model model, Command request, OutputStream response) throws Exception;
    
    private void title(String title, OutputStream writer) throws IOException {
        line(this.w, writer);
        center(this.w, title, writer);
        writer.write('\n');
    }
    
    private void body(String body, OutputStream writer) throws IOException {
        String[] split = body.split(String.format("(.{%s})", this.w));
        String collect = Arrays.stream(split).collect(Collectors.joining("\n")).concat("\n");
        writer.write(collect.getBytes());
    }
    
    protected void line(int w, OutputStream out) throws IOException {
        line('-', w, out);
    }
     
    protected void line(char c, int w, OutputStream out) throws IOException {
        for (int i = 0; i < w; i++) {
            out.write(c);
        }
        out.write('\n');
    }
    
    protected void center(int w, String value, OutputStream writer) throws IOException {
        int center = ((w/2) - (value.length() / 2) - 1);
        for (int i = 0; i < center; i++) {
            writer.write(' ');
        }
        writer.write(value.getBytes());
        for (int i = 0; i < center; i++) {
            writer.write(' ');
        }
    }
    
    protected void spinner(OutputStream stream, long millisec) throws IOException, InterruptedException {
        Instant start = Instant.now();
        Instant stop = start.plusMillis(millisec);
        char[] parts = new char[]{'-', '/', '|', '\\'};
        
        while (stop.isAfter(start)) {   
            for (char part : parts) {
                stream.write(part);
                stream.flush();
                Thread.sleep(500);
                stream.write('\b');
                stream.flush();
            }
            start = Instant.now();
        }
    }
    
    protected <E> E input(String label, OutputStream out, InputStream in, Class<E> type) throws IOException {
        out.write(label.getBytes());
        out.write("(".concat(type.getSimpleName()).concat(") : ").getBytes());
        out.flush();
        String value = read();
        return type.cast(value);
    }

//    public <E> E input(String label, OutputStream out, InputStream in, E value) throws IOException {
//        out.write(label.getBytes());
//        if (Objects.nonNull(value)) {
//            out.write("(".concat(value.getClass().getSimpleName()).concat(")").getBytes());
//        }
//        out.write(':');
//        out.flush();
//        String read = read();
//        
//        return type.cast(read);
//    }

    protected String read() throws IOException {
        if (System.console() != null) {
            return System.console().readLine();
        }
        Reader reader = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(reader);
        
        return bf.readLine();
    }
}
