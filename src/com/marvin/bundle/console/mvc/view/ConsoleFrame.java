package com.marvin.bundle.console.mvc.view;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ConsoleFrame extends ConsoleComponent {
    
    private List<ConsoleComponent> components = new ArrayList<>();
    
    public ConsoleFrame() {
        super(0, 0, 30, 100);
    }
    
    public ConsoleFrame(int h, int w) {
        super(0, 0, h, w);
    }

    @Override
    public void update() {
        super.update();
        this.components.stream().forEach(ConsoleComponent::update);
    }

    @Override
    public void render(OutputStream writer) throws Exception {
        getAnsi().eraseScreen();
        super.render(writer);
        this.components.stream().forEach((component) -> {
            try {
                component.render(writer);
            } catch(Exception e) {
                ///
            }
        });
//        writer.flush();
    }
    
    public List<ConsoleComponent> getComponents() {
        return components;
    }

    public void setComponents(List<ConsoleComponent> components) {
        this.components = components;
    }
    
    public void addComponent(ConsoleComponent component) {
        this.components.add(component);
    }
}
