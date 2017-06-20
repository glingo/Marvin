package com.marvin.bundle.templating;

import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.mvc.view.View;
import com.marvin.component.templating.Engine;
import com.marvin.component.templating.template.Template;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateView<I, O> extends View<I, O> {

    private Template template;
    private final Engine engine;

    public TemplateView(String name, Engine engine) {
        super(name);
        this.engine = engine;
    }

    @Override
    public void load() throws Exception {
        this.template = this.engine.getTemplate(this.name);
    }

    @Override
    public void render(Handler<I, O> handler, Map<String, Object> model, I request, O response) throws Exception {
        this.load();
        Writer writer = new StringWriter();
        this.template.evaluate(writer, model);
        ((OutputStream) response).write(writer.toString().getBytes());

//        this.logger.info(writer.toString());
    }
}
