package com.marvin.bundle.console.mvc.view;

import org.fusesource.jansi.Ansi;

public class Text extends ConsoleComponent {
    
    private String value;
    
    public Text(int x, int y, String value) {
        super(x, y, 1, value.length());
        this.value = value;
    }

    @Override
    public void update() {
//        getAnsi().eraseScreen();
        getAnsi().fg(Ansi.Color.RED).a(value);
    }
}
