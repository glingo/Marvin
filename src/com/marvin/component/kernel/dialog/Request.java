package com.marvin.component.kernel.dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
    
    private HashMap<String, String> headers;
    private HashMap<String, Object> queryParameters;
    private HashMap<String, Object> parameters;
    private HashMap<String, Object> attributes;
    
    //[scheme:][//authority][path][?query][#fragment]
    private URI uri;

    public Request(URI uri) {
        this.uri = uri;
    }
    
    public static Request build(String path, HashMap<String, Object> queryParameters, HashMap<String, String> headers) {
        String sheme = "";
        String authority = "";
//        String path;
        String query = "";
        String fragment = "";
        
//        URI.create(path)
        URI uri = null;
        try {
            uri = new URI(sheme, authority, path, query, fragment);
        } catch (URISyntaxException ex) {
            System.out.println("uri non valable");
        }
        
        Request request = new Request(uri);
        request.addHeaders(headers);
        request.addQueryParameters(queryParameters);
        return request;
    }
    
    public static Request build(String path) {
        return new Request(URI.create(path));
    }
    
    public static void build(Reader reader) throws Exception {
        build(new BufferedReader(reader));
    }

    public static void build(InputStream in) throws Exception {
        build(new InputStreamReader(in));
    }

    public static Request build(BufferedReader br) {
        
        String line;
        
        try {
            line = br.readLine();
            
            Matcher matcher = Pattern.compile("^([A-Z]+) (\\p{Graph}+) ?((HTTP/[0-9\\.]+)?)$").matcher(line);
            
            if(matcher.matches()) {
                line = matcher.group(2);
            }
            
            matcher = Pattern.compile("^(\\p{Graph}+)$").matcher(line);
            
            if(matcher.matches()) {
                line = matcher.group();
            }
            
            URI uri = URI.create(line);
            Request request = new Request(uri);
        } catch (IOException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Request(URI.create("/"));
    }

//        String request = "";
//        
//        while (line != null && !"".equals(line) && !System.lineSeparator().equals(line)) {
//            
//            request += "\n" + line;
//            
//            this.handle(line, writer);
//            
//            line = br.readLine();
//        }
//        System.out.println("Request :");
//        System.out.println(request);
//        String sheme = "";
//        String authority = "";
////        String path;
//        String query = "";
//        String fragment = "";
//        
////        URI.create(path)
//        URI uri = null;
//        try {
//            uri = new URI(sheme, authority, path, query, fragment);
//        } catch (URISyntaxException ex) {
//            System.out.println("uri non valable");
//        }
//        
//        Request request = new Request(uri);
//        request.addHeaders(headers);
//        request.addQueryParameters(queryParameters);
//        return request;
//    }

    public URI getUri() {
        return uri;
    }

    public HashMap<String, String> getHeaders() {
        if(this.headers == null) {
            this.headers = new HashMap<>();
        }
        return headers;
    }
    
    public void addHeader(String key, String value) {
        getHeaders().put(key, value);
    }
    
    public void addHeaders(HashMap<String, String> headers) {
        if(headers != null) {
            headers.forEach(this::addHeader);
        }
    }
    
    public HashMap<String, Object> getQueryParameters() {
        if(this.queryParameters == null) {
            this.queryParameters = new HashMap<>();
        }
        return queryParameters;
    }
    
    public void addQueryParameter(String key, Object value) {
        getQueryParameters().put(key, value);
    }
    
    public void addQueryParameters(HashMap<String, Object> query) {
        if(query != null) {
            query.forEach(this::addQueryParameter);
        }
    }
    
    
    public HashMap<String, Object> getParameters() {
        if(this.parameters == null) {
            this.parameters = new HashMap<>();
        }
        return parameters;
    }
    
    public void addParameter(String key, Object value) {
        getParameters().put(key, value);
    }
    
    public void addParameters(HashMap<String, Object> parameters) {
        if(parameters != null) {
            parameters.forEach(this::addParameter);
        }
    }
    
    public <T> T getParameter(String key) {
         return (T) getParameters().get(key);
    }
    
    public HashMap<String, Object> getAttributes() {
        if(this.attributes == null) {
            this.attributes = new HashMap<>();
        }
        return attributes;
    }
    
    public void addAttribute(String key, Object value) {
        getAttributes().put(key, value);
    }
    
    public void addAttributes(HashMap<String, Object> attributes) {
        if(attributes != null) {
            attributes.forEach(this::addAttribute);
        }
    }
    
    public <T> T getAttribute(String key) {
         return (T) getAttributes().get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb
            .append("---")
            .append(super.toString())
            .append("---\n\t")
            .append("headers : \n\t\t")
            .append(getHeaders())
            .append("\n\tattributes : \n\t\t")
            .append(getAttributes())
            .append("\n\tparameters : \n\t\t")
            .append(getParameters())
            .append("\n");
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
