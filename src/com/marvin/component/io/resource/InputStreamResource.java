package com.marvin.component.io.resource;

import com.marvin.component.io.Resource;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamResource extends Resource {

    private final InputStream inputStream;

    private final String description;

    private boolean read = false;

    /**
     * Create a new InputStreamResource.
     *
     * @param inputStream the InputStream to use
     */
    public InputStreamResource(InputStream inputStream) {
        this(inputStream, "resource loaded through InputStream");
    }

    /**
     * Create a new InputStreamResource.
     *
     * @param inputStream the InputStream to use
     * @param description where the InputStream comes from
     */
    public InputStreamResource(InputStream inputStream, String description) {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream must not be null");
        }
        this.inputStream = inputStream;
        this.description = (description != null ? description : "");
    }

    @Override
    public String getDescription() {
        return "InputStream resource [" + this.description + "]";
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (this.read) {
            throw new IllegalStateException("InputStream has already been read - "
                    + "do not use InputStreamResource if a stream needs to be read multiple times");
        }
        this.read = true;
        return this.inputStream;
    }

    /**
     * This implementation compares the underlying InputStream.
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        return (obj == this
                || (obj instanceof InputStreamResource && ((InputStreamResource) obj).inputStream.equals(this.inputStream)));
    }

    /**
     * This implementation returns the hash code of the underlying InputStream.
     * @return 
     */
    @Override
    public int hashCode() {
        return this.inputStream.hashCode();
    }

    @Override
    public String toString() {
        return "InputStreamResource{" + "inputStream=" + inputStream + ", description=" + description + ", read=" + read + '}';
    }
}
