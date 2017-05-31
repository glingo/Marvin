package com.marvin.component.io.resource;

import com.marvin.component.io.Resource;
import com.marvin.component.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public abstract class FileResolvingResource extends Resource {

    /**
     * This implementation returns a File reference for the underlying class
     * path resource, provided that it refers to a file in the file system.
     *
     * @return 
     * @throws java.io.IOException
     * @see org.springframework.util.ResourceUtils#getFile(java.net.URL, String)
     */
    @Override
    public File getFile() throws IOException {
        URL url = getURL();
        return ResourceUtils.getFile(url, getDescription());
    }

    /**
     * This implementation returns a File reference for the underlying class
     * path resource, provided that it refers to a file in the file system.
     *
     * @param uri
     * @return 
     * @throws java.io.IOException 
     * @see org.springframework.util.ResourceUtils#getFile(java.net.URI, String)
     */
    protected File getFile(URI uri) throws IOException {
        return ResourceUtils.getFile(uri, getDescription());
    }
    
    @Override
    public boolean exists() {
        try {
            URL url = getURL();
            if (ResourceUtils.isFileURL(url)) {
                // Proceed with file system resolution...
                return getFile().exists();
            } else {
                // Try a URL connection content-length header...
                URLConnection con = url.openConnection();
                customizeConnection(con);
                HttpURLConnection httpCon
                        = (con instanceof HttpURLConnection ? (HttpURLConnection) con : null);
                if (httpCon != null) {
                    int code = httpCon.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        return true;
                    } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                        return false;
                    }
                }
                if (con.getContentLength() >= 0) {
                    return true;
                }
                if (httpCon != null) {
                    // no HTTP OK status, and no content-length header: give up
                    httpCon.disconnect();
                    return false;
                } else {
                    // Fall back to stream existence: can we open the stream?
                    InputStream is = getInputStream();
                    is.close();
                    return true;
                }
            }
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Customize the given {@link URLConnection}, obtained in the course of an
     * {@link #exists()}, {@link #contentLength()} or {@link #lastModified()}
     * call.
     * <p>
     * Calls {@link ResourceUtils#useCachesIfNecessary(URLConnection)} and
     * delegates to {@link #customizeConnection(HttpURLConnection)} if possible.
     * Can be overridden in subclasses.
     *
     * @param con the URLConnection to customize
     * @throws IOException if thrown from URLConnection methods
     */
    protected void customizeConnection(URLConnection con) throws IOException {
        ResourceUtils.useCachesIfNecessary(con);
        if (con instanceof HttpURLConnection) {
            customizeConnection((HttpURLConnection) con);
        }
    }

    /**
     * Customize the given {@link HttpURLConnection}, obtained in the course of
     * an {@link #exists()}, {@link #contentLength()} or {@link #lastModified()}
     * call.
     * <p>
     * Sets request method "HEAD" by default. Can be overridden in subclasses.
     *
     * @param con the HttpURLConnection to customize
     * @throws IOException if thrown from HttpURLConnection methods
     */
    protected void customizeConnection(HttpURLConnection con) throws IOException {
        con.setRequestMethod("HEAD");
    }


}
