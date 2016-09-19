package com.marvin.component.server;

//import com.marvin.old.kernel.Kernel;
//import java.net.ServerSocket;

import com.marvin.component.kernel.Kernel;
//import java.net.InetAddress;

public class HTTPServer extends Server {

    public HTTPServer(Kernel kernel) {
        super(kernel, 8080, 8081);
    }

//    protected int port = 8080;
//    protected Kernel kernel;
//    protected ServerSocket socket;
//    protected Dispatcher<Event> dispatcher = new EventDispatcher();
//    public HTTPServer(Kernel kernel) {
//        super(kernel, 8080, 8081);
//    }

//    @Override
//    protected synchronized void init() {
//        super.init();
//        dispatcher.addSubscriber(new ServerSubscriber());
//        this.kernel.load();
//    }

////    @Override
//    public void handle(BufferedReader reader, PrintWriter writer) {
////        String response = "No response !";
////        String request = "No Request !";
//
//        String line;
//        try {
//            line = reader.readLine();
//
//            while (line != null && !"".equals(line)) {
//                Matcher matcher = Pattern.compile("^([A-Z]+) (\\p{Graph}+) ?((HTTP/[0-9\\.]+)?)$").matcher(line);
//
//                if (matcher.find()) {
//                    String method = matcher.group(1);
//                    String uri = matcher.group(2);
//                    String protocol = matcher.group(3);
//
//                    this.kernel.handleRow(uri, writer);
//                    
//                }
//
//                line = reader.readLine();
//            }
//        } catch (IOException e) {
//            writer.format("exception : \n\n\n\t%s\n\n\n", e.getMessage());
////            e.printStackTrace();
//        }
//        
//        writer.flush();
//
//    }
}
