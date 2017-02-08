package mock.controller;

import com.marvin.bundle.framework.controller.Controller;
import java.util.HashMap;
import java.util.Map;

public class MockWelcomeController extends Controller {

    public Object welcomeAction(String type) {
        return this.render("welcome", new HashMap<>(), type);
    }

    public Object welcomeHTMLAction() {
        return this.render("welcome", new HashMap<>(), "html");
    }

    public Object welcomeTEXTAction() {
        return this.render("welcome", new HashMap<>(), "txt");
    }

    public Object welcomeFXMLAction() {
        return this.render("welcome", new HashMap<>(), "fxml");
    }

    @Override
    protected Object render(String name, Map context, String suffix) {
        context.put("kernel", get("kernel"));
        name = "marvin/demo/app/resources/view/" + name;
        return super.render(name, context, suffix);
    }
}
