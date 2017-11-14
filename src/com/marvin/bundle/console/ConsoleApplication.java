package com.marvin.bundle.console;

import com.marvin.bundle.console.command.Command;
import com.marvin.bundle.framework.Application;
import com.marvin.component.kernel.Kernel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

import org.jline.keymap.KeyMap;
import org.jline.reader.Binding;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.Macro;
import org.jline.reader.ParsedLine;
import org.jline.reader.Reference;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp;

public abstract class ConsoleApplication extends Application {
    
    public static int RATE = 0;

    public ConsoleApplication(String env, boolean debug) {
        super(env, debug);
//        AnsiConsole.systemInstall();
    }
    
    protected String getBrand() {
        return new AttributedStringBuilder()
            .style(AttributedStyle.DEFAULT.background(AttributedStyle.BLUE))
            .append("Marvin (")
            .append(Kernel.VERSION)
            .append(") - console")
            .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN))
            .append("(" + getEnvironment() + ")")
            .style(AttributedStyle.DEFAULT).toAnsi();
    }
    
    protected String getPrompt() {
        return new AttributedStringBuilder()
            .style(AttributedStyle.DEFAULT.background(AttributedStyle.GREEN))
            .append("marvin")
            .style(AttributedStyle.DEFAULT)
            .append("-")
            .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN))
            .append("console")
            .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN))
            .append("(" + getEnvironment() + ")")
            .style(AttributedStyle.DEFAULT)
            .append("> ").toAnsi();
    }
    
    @Override
    public void ready() {
        super.ready();
        start();
    }
    
    private void start() {
        String prompt = getPrompt();
        String rPrompt = new AttributedStringBuilder()
            .style(AttributedStyle.DEFAULT.background(AttributedStyle.RED))
            .append(LocalDate.now().format(DateTimeFormatter.ISO_DATE))
            .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.RED | AttributedStyle.BRIGHT))
            .append(LocalTime.now().format(new DateTimeFormatterBuilder()
                    .appendValue(ChronoField.HOUR_OF_DAY, 2)
                    .appendLiteral(':')
                    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                    .toFormatter()))
            .toAnsi();
        
        try {
            Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .streams(System.in, System.out)
                .build();
            
            terminal.writer().println(getBrand());

//            terminal.puts(InfoCmp.Capability.clear_screen);
//            terminal.puts(InfoCmp.Capability.carriage_return);
            
            LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
             while (true) {
                String line = null;
                try {
                    line = reader.readLine(prompt, rPrompt, null, null);
                } catch (UserInterruptException e) {
                    // Ignore
                } catch (EndOfFileException e) {
                    return;
                }

                if (line == null) {
                    continue;
                }

                line = line.trim();

                if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                    break;
                }
                
                ParsedLine parsed = reader.getParser().parse(line, 0);
                
                switch(parsed.word()) {
                    case "cls":
                        terminal.puts(InfoCmp.Capability.clear_screen);
                        terminal.flush();
                        break;
                    default:
//                        terminal.puts(InfoCmp.Capability.clear_screen);
//                        terminal.flush();
                        Command command = new Command(line);
                        getHandler().handle(command, terminal, true);
                        break;
                }
//                parsed.words().forEach(terminal.writer()::print);
//                terminal.writer().println(AttributedString
//                        .fromAnsi("\u001B[33m======>\u001B[0m\"" + line + "\"")
//                        .toAnsi(terminal));
                
//                terminal.flush();
//                
//                Command command = new Command(line);
//                getHandler().handle(command, terminal, true);
             }
            
        } catch (Exception ex) {
            throw new Error("Something went wrong ! ", ex);
        }
    }
    
//    private String read() throws IOException {
//        if (System.console() != null) {
//            return System.console().readLine();
//        }
//        Reader reader = new InputStreamReader(System.in);
//        BufferedReader bf = new BufferedReader(reader);
//        
//        return bf.readLine();
//    }
//    
//    private void print(String what) throws IOException {
//        System.out.println(what);
//    }
    
    
    
            
//            reader.isSet(LineReader.Option.MOUSE);
//            reader.setOpt(LineReader.Option.MOUSE);
            
//            MouseSupport.trackMouse(terminal, Terminal.MouseTracking.Any);
            
//            reader.getWidgets().put(LineReader.CALLBACK_INIT, () -> {
////                terminal.trackMouse(Terminal.MouseTracking.Any);
//                terminal.trackMouse(Terminal.MouseTracking.Button);
//                return true;
//            });
            
//            terminal.trackMouse(Terminal.MouseTracking.Any);
            
//            reader.getWidgets().put(LineReader.MOUSE, () -> {
//                System.err.println("mouse");
//                MouseEvent event = terminal.readMouseEvent();
////                StringBuilder tsb = new StringBuilder();
////                Cursor cursor = terminal.getCursorPosition(c -> tsb.append((char) c));
////                reader.runMacro(tsb.toString());
//                String msg = "Message ?" + event.toString();
//                int w = terminal.getWidth();
////                terminal.puts(InfoCmp.Capability.cursor_address, 0, Math.max(0, w - msg.length()));
////                terminal.writer().append(msg);
//                terminal.writer().append("Message ?");
//                
////                terminal.puts(InfoCmp.Capability.cursor_address, cursor.getY(), cursor.getX());
//                terminal.flush();
//                return true;
//            });
            
//            while(run) {
////                Command command = new Command(cmd);
//                getHandler().handle(reader, terminal, true);
//                cmd = read();
//            }
//            Executors.newScheduledThreadPool(1)
//                        .scheduleAtFixedRate(() -> {
//                            ++ConsoleApplication.RATE;
//                            reader.callWidget(LineReader.CLEAR_SCREEN);
////                            reader.getTerminal().writer().format("(%s)", ConsoleApplication.RATE++);
//                            reader.callWidget(LineReader.REDRAW_LINE);
//                            reader.callWidget(LineReader.REDISPLAY);
//                            reader.getTerminal().writer().flush();
//                        }, 1, 1, TimeUnit.SECONDS);
            
//            Executors.newScheduledThreadPool(1)
//                        .scheduleAtFixedRate(() -> {
//                            ConsoleApplication.RATE++;
//                            reader.callWidget(LineReader.CLEAR_SCREEN);
//                            reader.callWidget(LineReader.REDRAW_LINE);
//                            reader.getTerminal().writer().format("(%s)", ConsoleApplication.RATE++);
//                            reader.callWidget(LineReader.REDISPLAY);
//                            reader.getTerminal().writer().flush();
//                        }, 2, 700, TimeUnit.MILLISECONDS);
//            
//             while (true) {
//                String line = null;
//                try {
//                    line = reader.readLine(prompt, rPrompt, null, null);
//                } catch (UserInterruptException e) {
//                    // Ignore
//                } catch (EndOfFileException e) {
//                    return;
//                }
//                if (line == null) {
//                    continue;
//                }
//
//                line = line.trim();
//                
//                terminal.writer().println(
//                            AttributedString.fromAnsi("\u001B[33m======>\u001B[0m\"" + line + "\"")
//                                .toAnsi(terminal));
//                
//                terminal.flush();
//             }
//            
//            String line = lineReader.readLine();
//            terminal.writer().format("Vous avez entré : %s", line);
//            terminal.writer().println("**************************************");
//            terminal.writer().println("**************************************");
//            terminal.writer().println("**************************************");
//            terminal.writer().println("**************************************");
//            terminal.writer().println("**************************************");
//            terminal.writer().println("**************************************");
//            terminal.flush();
////            Thread.sleep(1000);
////            lineReader.readLine();
//            lineReader.callWidget(LineReader.REDRAW_LINE);
//            lineReader.callWidget(LineReader.REDISPLAY);
////            lineReader.callWidget(LineReader.CLEAR_SCREEN);
////            lineReader.callWidget(LineReader.CLEAR);
////            lineReader.runMacro("macro");
//            terminal.flush();
//            terminal.close();


//            String quit = "quit";
//            String home = "/";
//            String referer = home;
//            String cmd = home;
//            
//            while(!cmd.equalsIgnoreCase(quit)) {
//                Command command = new Command(cmd);
//                getHandler().handle(command, System.out, true);
//                cmd = read();
//            }
            
//                        StringBuilder sb = new StringBuilder();
//            Map<String, KeyMap<Binding>> bound = reader.getKeyMaps();
//                        for (Map.Entry<String, KeyMap<Binding>> entry : bound.entrySet()) {
//                            Map<String, Binding> boundKeys = entry.getValue().getBoundKeys();
//                            boundKeys.forEach((name, value) -> {
//                                    sb.append("\"");
//                                    name.chars().forEachOrdered(c -> {
//                                        if (c < 32) {
//                                            sb.append('^');
//                                            sb.append((char) (c + 'A' - 1));
//                                        } else {
//                                            sb.append((char) c);
//                                        }
//                                    });
//                                    sb.append("\"");
//
//                                    if (value instanceof Macro) {
//                                        sb.append("\"");
//                                        ((Macro) value).getSequence().chars().forEachOrdered(c -> {
//                                            if (c < 32) {
//                                                sb.append('^');
//                                                sb.append((char) (c + 'A' - 1));
//                                            } else {
//                                                sb.append((char) c);
//                                            }
//                                        });
//                                    sb.append("\"");
//                                } else if (value instanceof Reference) {
//                                    sb.append(((Reference) value).name().toLowerCase().replace('_', '-'));
//                                } else {
//                                    sb.append(entry.getValue().getBoundKeys());
//                                }
//                                sb.append("\n");
//                            });
//                        }
//                        terminal.writer().print(sb.toString());
//                        terminal.flush();
            
}
