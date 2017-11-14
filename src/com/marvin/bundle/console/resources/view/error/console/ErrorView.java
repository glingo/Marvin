package com.marvin.bundle.console.resources.view.error.console;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.console.mvc.view.ConsoleView;
import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.mvc.model.Model;
import java.util.Objects;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

public class ErrorView extends ConsoleView {

    public ErrorView(String name) {
        super(name);
    }

    @Override
    protected String getTitle(Handler<Command, Terminal> handler, Model model, Command request, Terminal response) throws Exception {
        return "Error";
    }
    
    @Override
    protected String getBody(Handler<Command, Terminal> handler, Model model, Command request, Terminal terminal) throws Exception {
        AttributedStringBuilder builder = new AttributedStringBuilder()
                .style(AttributedStyle.DEFAULT.underline())
                .append("Exception : ")
                .style(AttributedStyle.DEFAULT.underlineOff())
                .append(Objects.toString(model.get("exception"), "null"))
                .append(AttributedString.NEWLINE)
                .style(AttributedStyle.DEFAULT.underline())
                .append("Message : ")
                .style(AttributedStyle.DEFAULT.underlineOff())
                .append(Objects.toString(model.get("message"), "null"))
                .append(AttributedString.NEWLINE)
                .style(AttributedStyle.DEFAULT.underline())
                .append("Result : ")
                .style(AttributedStyle.DEFAULT.underlineOff())
                .append(Objects.toString(model.get("result"), "null"))
                .append(AttributedString.NEWLINE)
                .style(AttributedStyle.DEFAULT.underline())
                .append("Request : ")
                .style(AttributedStyle.DEFAULT.underlineOff());
        return builder.toAnsi(terminal);
    }
}
