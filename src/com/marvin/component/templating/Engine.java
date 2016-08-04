/**
 * *****************************************************************************
 * This file is part of Pebble.
 * <p>
 * Copyright (c) 2014 by Mitchell Bösecke
 * <p>
 * For the full copyright and license information, please view the LICENSE file
 * that was distributed with this source code.
 *****************************************************************************
 */
package com.marvin.component.templating;

import com.marvin.component.templating.extension.Extension;
import com.marvin.component.templating.extension.ExtensionRegistry;
import com.marvin.component.templating.extension.core.CoreExtension;
import com.marvin.component.templating.extension.escaper.EscaperExtension;
import com.marvin.component.templating.extension.escaper.EscapingStrategy;
import com.marvin.component.templating.extension.i18n.I18nExtension;
import com.marvin.component.templating.lexer.Lexer;
import com.marvin.component.templating.lexer.Syntax;
import com.marvin.component.templating.loader.ClasspathLoader;
import com.marvin.component.templating.loader.DelegatingLoader;
import com.marvin.component.templating.loader.FileLoader;
import com.marvin.component.templating.loader.Loader;
import com.marvin.component.templating.node.RootNode;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.TokenStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

/**
 * The main class used for compiling templates. The PebbleEngine is responsible
 * for delegating responsibility to the lexer, parser, compiler, and template
 * cache.
 *
 * @author Mitchell
 */
public class Engine {

    private final Loader<?> loader;

    private final Syntax syntax;

    private final boolean strictVariables;

    private final Locale defaultLocale;

//    private final Cache<BaseTagCacheKey, Object> tagCache;
    private final ExecutorService executorService;

//    private final Cache<Object, PebbleTemplate> templateCache;
    private final ExtensionRegistry extensionRegistry;

    /**
     * Constructor for the Pebble Engine given an instantiated Loader. This
     * method does only load those userProvidedExtensions listed here.
     *
     * @param loader The template loader for this engine
     * @param syntax the syntax to use for parsing the templates.
     * @param extensions The userProvidedExtensions which should be loaded.
     */
    private Engine(Loader<?> loader, Syntax syntax, boolean strictVariables, Locale defaultLocale, ExecutorService executorService, Collection<? extends Extension> extensions) {

        this.loader = loader;
        this.syntax = syntax;
        this.strictVariables = strictVariables;
        this.defaultLocale = defaultLocale;
//        this.tagCache = tagCache;
        this.executorService = executorService;
//        this.templateCache = templateCache;
        this.extensionRegistry = new ExtensionRegistry(extensions);
    }

    /**
     * Loads, parses, and compiles a template into an instance of PebbleTemplate
     * and returns this instance.
     *
     * @param templateName The name of the template
     * @return Template The compiled version of the template
     * @throws Exception Thrown if an error occurs while parsing the template.
     */
    public Template getTemplate(final String templateName) throws Exception {

        /*
         * template name will be null if user uses the extends tag with an
         * expression that evaluates to null
         */
        if (templateName == null) {
            return null;
        }

        if (this.loader == null) {
            throw new Exception("Loader has not yet been specified.");
        }

        final Engine self = this;
        final Object cacheKey = this.loader.createCacheKey(templateName);

        Lexer lexer = new Lexer(syntax, extensionRegistry.getUnaryOperators().values(), extensionRegistry.getBinaryOperators().values());
        Reader templateReader = self.retrieveReaderFromLoader(self.loader, cacheKey);
        TokenStream tokenStream = lexer.tokenize(templateReader, templateName);

        Parser parser = new Parser(extensionRegistry.getUnaryOperators(), extensionRegistry.getBinaryOperators(), extensionRegistry.getTokenParsers());
        RootNode root = parser.parse(tokenStream);

        Template instance = new Template(self, root, templateName);

        extensionRegistry.getNodeVisitors().stream().forEach((visitorFactory) -> {
            visitorFactory.createVisitor(instance).visit(root);
        });

        return instance;
    }

    /**
     * This method calls the loader and fetches the reader. We use this method
     * to handle the generic cast.
     *
     * @param loader the loader to use fetch the reader.
     * @param cacheKey the cache key to use.
     * @return the reader object.
     * @throws Exception thrown when the template could not be loaded.
     */
    private <T> Reader retrieveReaderFromLoader(Loader<T> loader, Object cacheKey) throws Exception {
        // We make sure within getTemplate() that we use only the same key for
        // the same loader and hence we can be sure that the cast is safe.
        @SuppressWarnings("unchecked")
        T casted = (T) cacheKey;
        return loader.getReader(casted);
    }

    /**
     * Returns the loader
     *
     * @return The loader
     */
    public Loader<?> getLoader() {
        return loader;
    }

    /**
     * Returns the template cache
     *
     * @return The template cache
     */
//    public Cache<Object, Template> getTemplateCache() {
//        return templateCache;
//    }

    /**
     * Returns the strict variables setting
     *
     * @return The strict variables setting
     */
    public boolean isStrictVariables() {
        return strictVariables;
    }

    /**
     * Returns the default locale
     *
     * @return The default locale
     */
    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Returns the executor service
     *
     * @return The executor service
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Returns the syntax which is used by this PebbleEngine.
     *
     * @return the syntax used by the PebbleEngine.
     */
    public Syntax getSyntax() {
        return this.syntax;
    }

    /**
     * Returns the extension registry.
     *
     * @return The extension registry
     */
    public ExtensionRegistry getExtensionRegistry() {
        return extensionRegistry;
    }

    /**
     * Returns the tag cache
     *
     * @return The tag cache
     */
//    public Cache<BaseTagCacheKey, Object> getTagCache() {
//        return this.tagCache;
//    }

    /**
     * A builder to configure and construct an instance of a PebbleEngine.
     */
    public static class Builder {

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
        public Builder() {

        }

        /**
         * Sets the loader used to find templates.
         *
         * @param loader A template loader
         * @return This builder object
         */
        public Builder loader(Loader<?> loader) {
            this.loader = loader;
            return this;
        }

        /**
         * Adds an extension, can be safely invoked several times to add
         * different extensions.
         *
         * @param extensions One or more extensions to add
         * @return This builder object
         */
        public Builder extension(Extension... extensions) {
            this.userProvidedExtensions.addAll(Arrays.asList(extensions));
            return this;
        }

        /**
         * Sets the syntax to be used.
         *
         * @param syntax The syntax to be used
         * @return This builder object
         */
        public Builder syntax(Syntax syntax) {
            this.syntax = syntax;
            return this;
        }

        /**
         * Changes the <code>strictVariables</code> setting of the PebbleEngine.
         * The default value of this setting is "false".
         * <p>
         * The following examples will all print empty strings if
         * strictVariables is false but will throw exceptions if it is true:
         * </p>
         * {{ nonExistingVariable }} {{ nonExistingVariable.attribute }} {{
         * nullVariable.attribute }} {{ existingVariable.nullAttribute.attribute
         * }} {{ existingVariable.nonExistingAttribute }} {{ array[-1] }}
         *
         * @param strictVariables Whether or not strict variables is used
         * @return This builder object
         */
        public Builder strictVariables(boolean strictVariables) {
            this.strictVariables = strictVariables;
            return this;
        }

        /**
         * Sets the Locale passed to all templates constructed by this
         * PebbleEngine.
         * <p>
         * An individual template can always be given a new locale during
         * evaluation.
         *
         * @param defaultLocale The default locale
         * @return This builder object
         */
        public Builder defaultLocale(Locale defaultLocale) {
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
        public Builder executorService(ExecutorService executorService) {
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
        public Builder autoEscaping(boolean autoEscaping) {
            escaperExtension.setAutoEscaping(autoEscaping);
            return this;
        }

        /**
         * Sets the default escaping strategy of the built-in escaper extension.
         *
         * @param strategy The name of the default escaping strategy
         * @return This builder object
         */
        public Builder defaultEscapingStrategy(String strategy) {
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
        public Builder addEscapingStrategy(String name, EscapingStrategy strategy) {
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
         * Creates the PebbleEngine instance.
         *
         * @return A PebbleEngine object that can be used to create
         * PebbleTemplate objects.
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
}
