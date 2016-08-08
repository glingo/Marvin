package com.marvin.component.templating.extension.i18n;

import com.marvin.component.templating.extension.AbstractExtension;
import com.marvin.component.templating.extension.Function;
import java.util.HashMap;
import java.util.Map;

public class I18nExtension extends AbstractExtension {

    @Override
    public Map<String, Function> getFunctions() {
        Map<String, Function> functions = new HashMap<>();
        functions.put("i18n", new i18nFunction());
        return functions;
    }

}
