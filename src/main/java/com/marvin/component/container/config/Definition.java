package com.marvin.component.container.config;

import com.marvin.component.util.ObjectUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class Definition {

    protected String scope;

    protected String className;

    protected String factoryName;

    protected String factoryMethodName;

    protected String parentName;

    protected String description;

    protected Boolean deprecated;

    protected String[] aliases;

    protected Object[] arguments;

    protected Tag[] tags;

    protected LinkedHashMap<String, List<Object[]>> calls;

    public Definition() {
        this.arguments = new Object[]{};
    }

    public Definition(String className) {
        this.className = className;
        this.arguments = new Object[]{};
    }
    
    public Definition(String scope, String className) {
        this.scope = scope;
        this.className = className;
        this.arguments = new Object[]{};
    }

    public Definition(String scope, String className, Object[] arg) {
        this.scope = scope;
        this.className = className;
        this.arguments = new Object[]{};
    }

//    public static DefinitionBuilderInterface builder() {
//        return scope -> className -> new Definition(scope, className);
//    }


    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFactoryMethodName() {
        return this.factoryMethodName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeprecated() {
        return this.deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public Object[] getArguments() {
        return this.arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public void replaceArgument(int index, Object argument) {

        if (getArguments() == null) {
            setArguments(new Object[index]);
        }

        if (index < getArguments().length - 1) {
            this.arguments[index] = argument;
        }
    }

    public void addArgument(Object argument) {

        if (arguments == null) {
            setArguments(new Object[0]);
        }

        this.arguments = ObjectUtils.addObjectToArray(arguments, argument);
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryName() {
        return this.factoryName;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public Tag[] getTags() {
        return this.tags;
    }

    public void addTag(Tag tag) {
        if (this.tags == null) {
            this.tags = new Tag[0];
        }

        this.tags = ObjectUtils.addObjectToArray(this.tags, tag);
    }

    public boolean hasTag(String name) {
        if (getTags() == null) {
            return false;
        }

        for (Tag tag : getTags()) {
            if (name.equals(tag.getName())) {
                return true;
            }
        }

        return false;
    }

    public void setCalls(LinkedHashMap<String, List<Object[]>> calls) {
        this.calls = calls;
    }

    public LinkedHashMap<String, List<Object[]>> getCalls() {
        return this.calls;
    }

    public void addCall(String name, Object... args) {
        if (getCalls() == null) {
            setCalls(new LinkedHashMap<>());
        }

        if (!getCalls().containsKey(name)) {
            getCalls().put(name, new ArrayList<>());
        }

        getCalls().get(name).add(args);
    }

    public boolean hasCall() {
        return this.calls != null;
    }

    @Override
    public String toString() {
        return "Definition{" + "scope=" + scope + ", className=" + className + ", factoryName=" + factoryName + ", factoryMethodName=" + factoryMethodName + ", parentName=" + parentName + ", description=" + description + ", deprecated=" + deprecated + ", aliases=" + aliases + ", arguments=" + arguments + ", tags=" + tags + ", calls=" + calls + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.scope);
        hash = 71 * hash + Objects.hashCode(this.className);
        hash = 71 * hash + Objects.hashCode(this.factoryName);
        hash = 71 * hash + Objects.hashCode(this.factoryMethodName);
        hash = 71 * hash + Objects.hashCode(this.parentName);
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + Objects.hashCode(this.deprecated);
        hash = 71 * hash + Arrays.deepHashCode(this.aliases);
        hash = 71 * hash + Arrays.deepHashCode(this.arguments);
        hash = 71 * hash + Arrays.deepHashCode(this.tags);
        hash = 71 * hash + Objects.hashCode(this.calls);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Definition other = (Definition) obj;
        if (!Objects.equals(this.scope, other.scope)) {
            return false;
        }
        if (!Objects.equals(this.className, other.className)) {
            return false;
        }
        if (!Objects.equals(this.factoryName, other.factoryName)) {
            return false;
        }
        if (!Objects.equals(this.factoryMethodName, other.factoryMethodName)) {
            return false;
        }
        if (!Objects.equals(this.parentName, other.parentName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.deprecated, other.deprecated)) {
            return false;
        }
        if (!Arrays.deepEquals(this.aliases, other.aliases)) {
            return false;
        }
        if (!Arrays.deepEquals(this.arguments, other.arguments)) {
            return false;
        }
        if (!Arrays.deepEquals(this.tags, other.tags)) {
            return false;
        }
        if (!Objects.equals(this.calls, other.calls)) {
            return false;
        }
        return true;
    }
}
