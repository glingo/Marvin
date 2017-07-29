package com.marvin.bundle.console.mvc.view;

public class ProgressBar extends ConsoleComponent {
    
    private int max = 50;
    private int current = 0;
    private boolean done = false;
    
    public ProgressBar() {
        this(10);
    }
    
    public ProgressBar(int w) {
        this(0, 0, w);
    }
    
    public ProgressBar(int x, int y, int w) {
        super(x, y, 1, w + 3);
        this.max = w;
    }
    
    public void advance(int distance) {
        this.current += distance;
        
        if (this.current >= max - 1) {
            this.current = this.max - 1;
            this.done = true;
        }
    }
    
    public void end() {
        advance(this.max - this.current);
    }

    @Override
    public void update() {
        set('[', 0, 0);
        for (int i = 1; i <= this.current; i++) {
            set('=', 0, i);
        }
        set('>', 0, this.current + 1);
        set(']', 0, this.max);
    }
}
