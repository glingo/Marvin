
import java.io.IOException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;


public class TestJLine3 {
    
    public static void main(String[] args) throws IOException {
        
        Terminal terminal = TerminalBuilder.builder()
                .system(false)
                .streams(System.in, System.out)
//                .signalHandler(Terminal.SignalHandler.SIG_IGN)
                .build();
//        terminal.trackMouse(Terminal.MouseTracking.Any);
        LineReader lineReader = LineReaderBuilder.builder()
            .terminal(terminal)
            .appName("Hello")
            .build();
        String line = lineReader.readLine();
        terminal.writer().format("Vous avez entré : %s", line);
//        terminal.flush();
        terminal.close();
    }
}
