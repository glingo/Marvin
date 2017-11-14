package com.marvin.bundle.console.mvc.view;

import java.io.OutputStream;
import org.fusesource.jansi.Ansi;

public abstract class ConsoleComponent {
    
    private Ansi ansi;

    private final int x;
    private final int y;
    
    private final int width;
    private final int height;
    
    public ConsoleComponent(int x, int y, int h, int w) {
        this.x = h;
        this.y = w;
        
        this.height = h;
        this.width = w;
        
        this.ansi = Ansi.ansi();
    }
    
    public void update() {
//        ansi.eraseScreen();
//        borders();
    }
    
    protected void borders() {
        line('-', 0, height - 1);
        col('|', 0, width - 1);
    }
    
    protected void line(char c, int... lines) {
        for (int i = 0; i < this.width; i++) {
            for (int line : lines) {
                ansi.cursor(line, i).a(c);
            }
        }
    }
    
    protected void fill(char[][] chars, int x, int y) {
        for (int i = 0; i < chars.length; i++) {
            char[] line = chars[i];
            for (int j = 0; j < line.length; j++) {
                ansi.cursor(i + x, j + y).a(chars[i][j]);
            }
        }
    }
    
    protected void col(char c, int... cols) {
        for (int i = 0; i < this.height; i++) {
            for (int col : cols) {
                set(c, i, col);
            }
        }
    }

    public Ansi getAnsi() {
        return ansi;
    }
    
    public void render(OutputStream writer) throws Exception {
//        ansi.saveCursorPosition();
        update();
        writer.write(ansi.toString().getBytes());
        ansi.reset();
//        ansi.cursor(31, 49);
        writer.flush();
//        ansi.restoreCursorPosition();
    }
    
    protected void set(char c, int h, int w) {
        ansi.cursor(h, w).a(c);
    }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
