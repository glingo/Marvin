package com.marvin.component.io;

import com.marvin.component.util.Assert;
import com.marvin.component.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class Resource implements IResource {

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean isReadable() {
        return true;
    }

    /**
     * This implementation always returns {@code false}.
     */
    @Override
    public boolean isOpen() {
        return false;
    }

    /**
     * This implementation checks whether a File can be opened, falling back to
     * whether an InputStream can be opened. This will cover both directories
     * and content resources.
     */
    @Override
    public boolean exists() {
        // Try file existence: can we find the file in the file system?
        try {
            return getFile().exists();
        } catch (IOException ex) {
            // Fall back to stream existence: can we open the stream?
            try {
                InputStream is = getInputStream();
                is.close();
                return true;
            } catch (Throwable isEx) {
                return false;
            }
        }
    }

    /**
     * This implementation builds a URI based on the URL returned by
     * {@link #getURL()}.
     * @throws java.io.IOException
     */
    @Override
    public URI getURI() throws IOException {
        URL url = getURL();
        try {
            return ResourceUtils.toURI(url);
        } catch (URISyntaxException ex) {
            throw new IOException("Invalid URI [" + url + "]", ex);
        }
    }

    /**
     * This implementation throws a FileNotFoundException, assuming that the
     * resource cannot be resolved to a URL.
     * @throws java.io.IOException
     */
    @Override
    public URL getURL() throws IOException {
        throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
    }

    /**
     * This implementation throws a FileNotFoundException, assuming that the
     * resource cannot be resolved to an absolute file path.
     * @throws java.io.IOException
     */
    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path");
    }

    /**
     * This implementation reads the entire InputStream to calculate the content
     * length. Subclasses will almost always be able to provide a more optimal
     * version of this, e.g. checking a File length.
     *
     * @return 
     * @throws java.io.IOException
     * @see #getInputStream()
     * @throws IllegalStateException if {@link #getInputStream()} returns null.
     */
    @Override
    public long contentLength() throws IOException {
        InputStream is = getInputStream();
        Assert.state(is != null, "Resource InputStream must not be null");
        try {
            long size = 0;
            byte[] buf = new byte[255];
            int read;
            while ((read = is.read(buf)) != -1) {
                size += read;
            }
            return size;
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
            }
        }
    }
    
}
