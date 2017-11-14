package com.marvin.bundle.console.mvc.view;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.mvc.model.Model;
import com.marvin.component.mvc.view.View;
import java.util.Map;
import org.jline.terminal.Terminal;

public abstract class ConsoleView extends View<Command, Terminal> {
    
    private Canvas canvas = new Canvas(1, 1, 30, 120);
    
    public ConsoleView(String name) {
        super(name);
    }
    
    public ConsoleView(String name, int h, int w) {
        super(name);
    }

    @Override
    public void load() throws Exception {
    }
    
    @Override
    public void render(Handler<Command, Terminal> handler, Map<String, Object> model, Command request, Terminal response) throws Exception {
        Model m = new Model(model);
        response.writer().println(getTitle(handler, m, request, response));
        response.writer().println(getBody(handler, m, request, response));
        response.flush();
    }
        
    protected String getTitle(Handler<Command, Terminal> handler, Model model, Command request, Terminal response) throws Exception {
        return "Default title";
    }
    
    protected String getBody(Handler<Command, Terminal> handler, Model model, Command request, Terminal response) throws Exception{
        return "Default body";
    }
}
    
//        OutputStream stream = new BufferedOutputStream(AnsiConsole.wrapOutputStream(response));
        
//        canvas.getAnsi().eraseScreen();
//        canvas.rect(0, 0, 30, 100, Ansi.Color.BLUE);
////        canvas.rect(10, 10, 15, 30, Ansi.Color.GREEN);
////        canvas.line(5, 5, 5, 100, Ansi.Color.CYAN);
//        canvas.write(2, 2, getTitle(handler, m, request, response), Ansi.Color.YELLOW);
//        canvas.write(13, 11, getBody(handler, m, request, response), Ansi.Color.YELLOW);
//        canvas.render(stream);
//        stream.flush();
        
//        canvas.line(100, 1, 30, 1);
//        canvas.line(30, 1, 100, 1);
//        canvas.line(2, 2, 12, 12); // "\"
//        canvas.line(12, 12, 2, 22); // "/"
//        canvas.line(2, 22, 2, 52); // "|"
//        canvas.line(1, 51, 11, 21); // "/"
//        canvas.line(11, 21, 21, 51); // "\"
//        canvas.line(21, 51, 31, 51); // "-"
//        
//        for (int i = 1; i < 100; i++) {
//            for (int j = 1; j < 100; j++) {
//                canvas.line(0, 0, i, j);
//            }
//        }
//        
//        for (int i = 1; i < 100; i++) {
//            for (int j = 1; j < 100; j++) {
//                canvas.line(i, j, 1, 1);
//            }
//        }
//        canvas.render(stream);
        
//        ProgressBar bar = new ProgressBar(10, 3, 100);
//        bar.render(stream);
//      
//        bar.advance(10);
//        bar.render(stream);
//        
//        Thread.sleep(1000);
//        bar.advance(10);
//        bar.render(stream);
//        
//        Thread.sleep(1000);
//        bar.advance(50);
//        bar.render(stream);
//    
//        Thread.sleep(1000);
//        bar.advance(30);
//        bar.render(stream);

//        Text text = new Text(10, 3, "Hello World");
//        ConsoleFrame frame = new ConsoleFrame();
//        
//        frame.addComponent(text);
//        frame.addComponent(bar);
//        frame.render(stream);
//        bar.advance(10);
//        Thread.sleep(1000);
//        frame.render(stream);
//        bar.advance(10);
//        frame.render(stream);

//        Thread.sleep(1000);
//        text.render(stream);
        
//        Ansi value = Ansi.ansi()
//            .reset()
//            .eraseScreen()
//            .newline()
//            .fg(Ansi.Color.RED).a("Hello")
//            .fg(Ansi.Color.GREEN).a(" World");
        
//        stream.write(Objects.toString(value, "null").getBytes());
//        stream.flush();
//        response.println(value);
        
//    }

//    public void refresh(Writer writer) throws Exception {
//        clear(writer);
////        Thread.sleep(10);
//        this.matrix[h / 2][w / 2] = System.currentTimeMillis() % 2 == 0 ? 'O': 'a';
////        renderBorders(writer);
//        
//        flush(writer);
//    }
    
//    protected void renderBorders(Writer writer) throws IOException {
//        for (int i = 0; i < w; i++) {
//            this.matrix[0][i] = '+';
//            this.matrix[h - 1][i] = '+';
//        }
//        
//        for (int i = 0; i < h; i++) {
//            this.matrix[i][0] = '+';
//            this.matrix[i][w - 1] = '+';
//        }
//    }
    
//    protected void flush(Writer writer) throws IOException {
//        for (int i = 0; i < h; i++) {
//            for (int j = 0; j < w; j++) {
//                writer.write(this.matrix[i][j]);
//            }
//            writer.write('\n');
//        }
//        writer.flush();
//    }
    
//    protected void clear(Writer writer) throws IOException {
//        for (int i = 0; i < h; i++) {
////            for (int j = 0; j < w; j++) {
////                writer.write('\b');
////                writer.write('\r');
////            }
//            writer.write('\r');
//        }
////        writer.flush();
//    }
    
//    protected void render(char c, int h, int w) throws IOException {
//        this.matrix[h][w] = c;
//    }
    
//    protected abstract void renderTitle(Handler<Command, OutputStream> handler, Model model, Command request, OutputStream response) throws Exception;
//    protected abstract void renderBody(Handler<Command, OutputStream> handler, Model model, Command request, OutputStream response) throws Exception;
    
//    protected void title(String title, Terminal writer) throws IOException {
//        writer.write('\n');
////        center(this.w, title, writer);
//        writer.write('\n');
//    }
    
//    protected void line(String body, Terminal writer) throws IOException {
//        
////        String[] split = body.split(String.format("(.{%s})", this.w));
////        String collect = Arrays.stream(split).collect(Collectors.joining("\n"));
////        writer.write(collect);
//        writer.write('\n');
//    }
    
//    protected void repeat(char c, int w, Terminal writer) throws IOException {
//        for (int i = 0; i < w; i++) {
//            writer.write(c);
//        }
//    }
    
//    protected void erase(int number, OutputStream writer) throws IOException {
//        //  stream.write("\033[H\033[2J".getBytes());
//        for (int i = 0; i < number; i++) {
//            writer.write('\b');
//        }
//        writer.flush();
//    }
    
//    protected void writeThenErase(char value, OutputStream writer, long delay) throws Exception {
//        writer.write(value);
//        writer.flush();
//        Thread.sleep(delay);
//        erase(1, writer);
//    }
//    
//    protected void writeThenErase(String value, OutputStream writer, long delay) throws Exception {
//        writer.write(value.getBytes());
//        writer.flush();
//        Thread.sleep(delay);
//        erase(value.length(), writer);
//    }
//    
//    protected void center(int w, String value, OutputStream writer) throws IOException {
//        int center = ((w/2) - (value.length() / 2) - 1);
//        for (int i = 0; i < center; i++) {
//            writer.write(' ');
//        }
//        writer.write(value.getBytes());
//        for (int i = 0; i < center; i++) {
//            writer.write(' ');
//        }
//    }
//    
//    protected void spinner(OutputStream writer, long millisec) throws Exception {
//        spinner(writer, millisec, 100);
//    }
//    
//    protected void spinner(OutputStream writer, long millisec, long delay) throws Exception {
//        Instant start = Instant.now();
//        Instant stop = start.plusMillis(millisec);
//        char[] parts = new char[]{'-', '/', '|', '\\'};
//        
//        while (stop.isAfter(start)) {   
//            for (char part : parts) {
//                writeThenErase(part, writer, delay);
//            }
//            start = Instant.now();
//        }
//    }
//    
//    protected <E> E input(String label, OutputStream writer, InputStream in, Class<E> type) throws IOException {
//        writer.write(label.getBytes());
//        writer.write("(".concat(type.getSimpleName()).concat(") : ").getBytes());
//        writer.flush();
//        String value = read();
//        return type.cast(value);
//    }

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

//    protected String read() throws IOException {
//        if (System.console() != null) {
//            return System.console().readLine();
//        }
//        Reader reader = new InputStreamReader(System.in);
//        BufferedReader bf = new BufferedReader(reader);
//        
//        return bf.readLine();
//    }
    
    
//    public static class ViewRenderer implements Runnable {
//        
//        @Override
//        public void run() {
//            long lastLoopTime = System.nanoTime();
//            final int TARGET_FPS = 60;
//            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
//            int lastFpsTime = 0, fps = 0;
//
//            while (true) {
//                long now = System.nanoTime();
//                long updateLength = now - lastLoopTime;
//                lastLoopTime = now;
//                double delta = updateLength / ((double)OPTIMAL_TIME);
//                lastFpsTime += updateLength;
//                fps++;
//                if (lastFpsTime >= 1000000000) {
//                    refresh(writer);
//    //                System.out.println("(FPS: "+fps+")");
//                    lastFpsTime = 0;
//                    fps = 0;
//                }
//            }
//        }
//    }
        
//        long lastLoopTime = System.nanoTime();
//        final int TARGET_FPS = 60;
//        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
//        int lastFpsTime = 0, fps = 0;
//
//        while (true) {
//           long now = System.nanoTime();
//           long updateLength = now - lastLoopTime;
//           lastLoopTime = now;
//           double delta = updateLength / ((double)OPTIMAL_TIME);
//           lastFpsTime += updateLength;
//           fps++;
//           if (lastFpsTime >= 1000000000) {
//               refresh(writer);
////                System.out.println("(FPS: "+fps+")");
//               lastFpsTime = 0;
//               fps = 0;
//           }
//       }
        
//        long lastLoopTime = System.nanoTime();
//        final int TARGET_FPS = 60;
//        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
//        int lastFpsTime = 0, fps = 0;
        
//        service.submit(new Callable<Void>() {
//            
//            long lastLoopTime = System.nanoTime();
//            final int TARGET_FPS = 60;
//            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
//            int lastFpsTime = 0, fps = 0;
//
//            @Override
//            public Void call() throws Exception {
//                 while (true) {
//                    long now = System.nanoTime();
//                    long updateLength = now - lastLoopTime;
//                    lastLoopTime = now;
//                    double delta = updateLength / ((double)OPTIMAL_TIME);
//                    lastFpsTime += updateLength;
//                    fps++;
//                    if (lastFpsTime >= 1000000000) {
//                        refresh(writer);
//        //                System.out.println("(FPS: "+fps+")");
//                        lastFpsTime = 0;
//                        fps = 0;
//                    }
//                }
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
        
        
//        while (true) {
//            long now = System.nanoTime();
//            long updateLength = now - lastLoopTime;
//            lastLoopTime = now;
//            double delta = updateLength / ((double)OPTIMAL_TIME);
//            lastFpsTime += updateLength;
//            fps++;
//            if (lastFpsTime >= 1000000000) {
//                refresh(writer);
////                System.out.println("(FPS: "+fps+")");
//                lastFpsTime = 0;
//                fps = 0;
//            }
//        }
        
//        Timer timer = new Timer();
//        long fps = 60;
//        long fps = 10;
//        long now = System.nanoTime();
//        long updateLength = now - lastLoopTime;
//        int lastFpsTime = 0;
//        lastLoopTime = now;
//        double delta = updateLength / ((double)OPTIMAL_TIME);

        // update the frame counter
//        lastFpsTime += updateLength;
//        fps++;

        // update our FPS counter if a second has passed since
        // we last recorded
//        if (lastFpsTime >= 1000000000)
//        {
//           System.out.println("(FPS: "+fps+")");
//           lastFpsTime = 0;
//           fps = 0;
//        }
//        
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    refresh(writer);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }, 0, 1000 / fps);
        
//        refresh(writer);
//        renderTitle(handler, m, request, writer);
//        repeat('-', this.w, writer);
//        writer.write('\n');
//        renderBody(handler, m, request, writer);
//        writer.write('\n');
//        repeat('-', this.w, writer);
//        writer.write('\n');
//        writer.flush();
