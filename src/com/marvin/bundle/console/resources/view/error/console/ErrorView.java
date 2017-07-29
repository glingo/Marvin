package com.marvin.bundle.console.resources.view.error.console;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.console.mvc.view.Canvas;
import com.marvin.bundle.console.mvc.view.ConsoleView;
import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.mvc.model.Model;
import java.io.OutputStream;
import java.util.Map;
import java.util.Objects;
import org.fusesource.jansi.Ansi;
import org.jline.terminal.Terminal;

public class ErrorView extends ConsoleView {

    public ErrorView(String name) {
        super(name);
    }

    @Override
    protected String getTitle(Handler<Command, Terminal> handler, Model model, Command request, Terminal response) throws Exception {
        return "Error";
    }
    
    @Override
    protected String getBody(Handler<Command, Terminal> handler, Model model, Command request, Terminal writer) throws Exception {
        Canvas canvas = new Canvas(11, 11, 29, 99);
        canvas.write(11, 11, "Exception : ", Ansi.Color.RED);
        canvas.write(12, 15, Objects.toString(model.get("exception"), "null"), Ansi.Color.RED);
        canvas.write(13, 15, Objects.toString(model.get("result"), "null"), Ansi.Color.RED);
        canvas.write(14, 15, Objects.toString(model.get("request"), "null"), Ansi.Color.RED);
        return canvas.getAnsi().toString();
    }
}
