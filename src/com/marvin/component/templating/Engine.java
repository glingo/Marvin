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
import com.marvin.component.templating.lexer.Lexer;
import com.marvin.component.templating.lexer.Syntax;
import com.marvin.component.templating.loader.Loader;
import com.marvin.component.templating.node.RootNode;
import com.marvin.component.templating.parser.Parser;
import com.marvin.component.templating.token.TokenStream;
import java.io.Reader;
import java.util.Collection;
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
     * @param strictVariables
     * @param defaultLocale
     * @param executorService
     * @param extensions The userProvidedExtensions which should be loaded.
     */
    public Engine(Loader<?> loader, Syntax syntax, boolean strictVariables, Locale defaultLocale, ExecutorService executorService, Collection<? extends Extension> extensions) {

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

}
