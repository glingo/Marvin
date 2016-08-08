package com.marvin.component.templating;

import com.marvin.component.templating.extension.Extension;
import com.marvin.component.templating.extension.core.CoreExtension;
import com.marvin.component.templating.extension.escaper.EscaperExtension;
import com.marvin.component.templating.extension.escaper.EscapingStrategy;
import com.marvin.component.templating.extension.i18n.I18nExtension;
import com.marvin.component.templating.lexer.Syntax;
import com.marvin.component.templating.loader.ClasspathLoader;
import com.marvin.component.templating.loader.DelegatingLoader;
import com.marvin.component.templating.loader.FileLoader;
import com.marvin.component.templating.loader.Loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author cdi305
 */
public class EngineBuilder {

    private Loader<?> loader;

    private final List<Extension> userProvidedExtensions = new ArrayList<>();

    private Syntax syntax = new Syntax();

    private boolean strictVariables = false;

    private Locale defaultLocale;

    private ExecutorService executorService;

//        private Cache<Object, PebbleTemplate> templateCache;
//        private boolean cacheActive = true;
//        private Cache<BaseTagCacheKey, Object> tagCache;
    private final EscaperExtension escaperExtension = new EscaperExtension();

    /**
     * Creates the builder.
     */
    public EngineBuilder() {

    }

    /**
     * Sets the loader used to find templates.
     *
     * @param loader A template loader
     * @return This builder object
     */
    public EngineBuilder loader(Loader<?> loader) {
        this.loader = loader;
        return this;
    }

    /**
     * Adds an extension, can be safely invoked several times to add different
     * extensions.
     *
     * @param extensions One or more extensions to add
     * @return This builder object
     */
    public EngineBuilder extension(Extension... extensions) {
        this.userProvidedExtensions.addAll(Arrays.asList(extensions));
        return this;
    }

    /**
     * Sets the syntax to be used.
     *
     * @param syntax The syntax to be used
     * @return This builder object
     */
    public EngineBuilder syntax(Syntax syntax) {
        this.syntax = syntax;
        return this;
    }

    /**
     * Changes the <code>strictVariables</code> setting of the PebbleEngine. The
     * default value of this setting is "false".
     * <p>
     * The following examples will all print empty strings if strictVariables is
     * false but will throw exceptions if it is true:
     * </p>
     * {{ nonExistingVariable }} {{ nonExistingVariable.attribute }} {{
     * nullVariable.attribute }} {{ existingVariable.nullAttribute.attribute }}
     * {{ existingVariable.nonExistingAttribute }} {{ array[-1] }}
     *
     * @param strictVariables Whether or not strict variables is used
     * @return This builder object
     */
    public EngineBuilder strictVariables(boolean strictVariables) {
        this.strictVariables = strictVariables;
        return this;
    }

    /**
     * Sets the Locale passed to all templates constructed by this PebbleEngine.
     * <p>
     * An individual template can always be given a new locale during
     * evaluation.
     *
     * @param defaultLocale The default locale
     * @return This builder object
     */
    public EngineBuilder defaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
        return this;
    }

    /**
     * Sets the executor service which is required if using one of Pebble's
     * multithreading features such as the "parallel" tag.
     *
     * @param executorService The executor service
     * @return This builder object
     */
    public EngineBuilder executorService(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    /**
     * Sets the cache used by the engine to store compiled PebbleTemplate
     * instances.
     *
     * @param templateCache The template cache
     * @return This builder object
     */
//        public Builder templateCache(Cache<Object, PebbleTemplate> templateCache) {
//            this.templateCache = templateCache;
//            return this;
//        }
    /**
     * Sets the cache used by the "cache" tag.
     *
     * @param tagCache The tag cache
     * @return This builder object
     */
//        public Builder tagCache(Cache<BaseTagCacheKey, Object> tagCache) {
//            this.tagCache = tagCache;
//            return this;
//        }
    /**
     * Sets whether or not escaping should be performed automatically.
     *
     * @param autoEscaping The auto escaping setting
     * @return This builder object
     */
    public EngineBuilder autoEscaping(boolean autoEscaping) {
        escaperExtension.setAutoEscaping(autoEscaping);
        return this;
    }

    /**
     * Sets the default escaping strategy of the built-in escaper extension.
     *
     * @param strategy The name of the default escaping strategy
     * @return This builder object
     */
    public EngineBuilder defaultEscapingStrategy(String strategy) {
        escaperExtension.setDefaultStrategy(strategy);
        return this;
    }

    /**
     * Adds an escaping strategy to the built-in escaper extension.
     *
     * @param name The name of the escaping strategy
     * @param strategy The strategy implementation
     * @return This builder object
     */
    public EngineBuilder addEscapingStrategy(String name, EscapingStrategy strategy) {
        escaperExtension.addEscapingStrategy(name, strategy);
        return this;
    }

    /**
     * Enable/disable all caches, i.e. cache used by the engine to store
     * compiled PebbleTemplate instances and tags cache
     *
     * @param cacheActive toggle to enable/disable all caches
     * @return This builder object
     */
//        public Builder cacheActive(boolean cacheActive) {
//            this.cacheActive = cacheActive;
//            return this;
//        }
    /**
     * Creates the Engine instance.
     *
     * @return A Engine object that can be used to create Template
     * objects.
     */
    public Engine build() {

        // core extensions
        List<Extension> extensions = new ArrayList<>();
        extensions.add(new CoreExtension());
        extensions.add(escaperExtension);
        extensions.add(new I18nExtension());
        extensions.addAll(this.userProvidedExtensions);

        // default loader
        if (loader == null) {
            List<Loader<?>> defaultLoadingStrategies = new ArrayList<>();
            defaultLoadingStrategies.add(new ClasspathLoader());
            defaultLoadingStrategies.add(new FileLoader());
            loader = new DelegatingLoader(defaultLoadingStrategies);
        }

        // default locale
        if (defaultLocale == null) {
            defaultLocale = Locale.getDefault();
        }

//            if (cacheActive) {
        // default caches
//                if (templateCache == null) {
//                    templateCache = CacheBuilder.newBuilder().maximumSize(200).build();
//                }
//                if (tagCache == null) {
//                    tagCache = CacheBuilder.newBuilder().maximumSize(200).build();
//                }
//            } else {
//                templateCache = CacheBuilder.newBuilder().maximumSize(0).build();
//                tagCache = CacheBuilder.newBuilder().maximumSize(0).build();
//            }
        return new Engine(loader, syntax, strictVariables, defaultLocale, executorService, extensions);
    }
}
