package com.marvin.component.dialog;

public class Response {
    
    Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb
            .append("---")
            .append(super.toString())
            .append("---")
            .append("\n\tContent type:\n\t\t")
            .append(getContent() != null ? getContent().getClass() : null)
            .append("\n\tContent:\n")
            .append(getContent());
        
        return sb.toString();
    }
    
}
