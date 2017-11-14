package com.marvin.bundle.security.resources.view.error.console;

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
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

public class LoginView extends ConsoleView {

    public LoginView(String name) {
        super(name);
    }

    @Override
    protected String getTitle(Handler<Command, Terminal> handler, Model model, Command request, Terminal response) throws Exception {
        return "Login";
    }
    
    @Override
    protected String getBody(Handler<Command, Terminal> handler, Model model, Command request, Terminal terminal) throws Exception {
        AttributedStringBuilder builder = new AttributedStringBuilder()
                .append(AttributedString.NEWLINE);
        
        return builder.toAnsi(terminal);
    }
}
