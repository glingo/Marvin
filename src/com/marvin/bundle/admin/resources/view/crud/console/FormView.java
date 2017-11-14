package com.marvin.bundle.admin.resources.view.crud.console;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.console.mvc.view.ConsoleView;
import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.form.FormTypeInterface;
import com.marvin.component.mvc.model.Model;
import com.marvin.component.util.ReflectionUtils;
import com.marvin.component.util.StringUtils;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Consumer;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;

public class FormView extends ConsoleView {
    
    public FormView(String name) {
        super(name);
    }

    @Override
    protected String getTitle(Handler<Command, Terminal> handler, Model model, Command request, Terminal response) throws Exception {
        Optional<FormTypeInterface> form = model.get("form", FormTypeInterface.class);
        
        if (!form.isPresent()) {
            throw new IllegalArgumentException("'form' sould not be null");
        }
        
        return form.get().getLabel();
    }

    @Override
    protected String getBody(Handler<Command, Terminal> handler, Model model, Command request, Terminal response) throws Exception {
        Optional<FormTypeInterface> form = model.get("form", FormTypeInterface.class);
        
        if (!form.isPresent()) {
            throw new IllegalArgumentException("'form' sould not be null");
        }
        
        LineReader reader = LineReaderBuilder.builder().terminal(response).build();
        Object value = form.get().getData();
        
        form.get().visit(new Consumer<FormTypeInterface>() {
            @Override
            public void accept(FormTypeInterface child) {
                String fieldValue = reader.readLine(child.getLabel() + " : ");
                String setterName = String.join("set", StringUtils.capitalize(child.getName()));
                Field field = ReflectionUtils.findField(form.get().getClass(), setterName);
                ReflectionUtils.setField(field, value, fieldValue);
            }
        });
        
        String title = reader.readLine(form.get().getLabel());

        return "Success";
    }
    
}
