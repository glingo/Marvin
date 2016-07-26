/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component.io.resource;

import com.marvin.component.io.InputStreamSource;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 *
 * @author cdi305
 */
public interface IResource extends InputStreamSource {

    /**
     * Return whether this resource actually exists in physical form.
     * <p>
     * This method performs a definitive existence check, whereas the existence
     * of a {@code Resource} handle only guarantees a valid descriptor handle.
     *
     * @return
     */
    boolean exists();

    /**
     * Return whether the contents of this resource can be read, e.g. via
     * {@link #getInputStream()} or {@link #getFile()}.
     * <p>
     * Will be {@code true} for typical resource descriptors; note that actual
     * content reading may still fail when attempted. However, a value of
     * {@code false} is a definitive indication that the resource content cannot
     * be read.
     *
     * @return
     * @see #getInputStream()
     */
    boolean isReadable();

    /**
     * Return whether this resource represents a handle with an open stream. If
     * true, the InputStream cannot be read multiple times, and must be read and
     * closed to avoid resource leaks.
     * <p>
     * Will be {@code false} for typical resource descriptors.
     *
     * @return
     */
    boolean isOpen();

    /**
     * Determine the content length for this resource.
     *
     * @return
     * @throws IOException if the resource cannot be resolved (in the file
     * system or as some other known physical resource type)
     */
    long contentLength() throws IOException;

    /**
     * Return a URL handle for this resource.
     *
     * @return
     *
     * @throws IOException if the resource cannot be resolved as URL, i.e. if
     * the resource is not available as descriptor
     */
    URL getURL() throws IOException;

    /**
     * Return a URI handle for this resource.
     *
     * @return
     *
     * @throws IOException if the resource cannot be resolved as URI, i.e. if
     * the resource is not available as descriptor
     */
    URI getURI() throws IOException;

    /**
     * Return a File handle for this resource.
     *
     * @return
     *
     * @throws IOException if the resource cannot be resolved as absolute file
     * path, i.e. if the resource is not available in a file system
     */
    File getFile() throws IOException;

    /**
     * Return a description for this resource, to be used for error output when
     * working with the resource.
     * <p>
     * Implementations are also encouraged to return this value from their
     * {@code toString} method.
     *
     * @return
     * @see Object#toString()
     */
    String getDescription();
}
