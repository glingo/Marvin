package com.marvin.old.util.serialize.strategy;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.marvin.old.util.introspection.AnnotationUtils;
import com.marvin.old.util.serialize.Loadable;

public abstract class PropertieLoadStrategy extends LoadStrategy {
	
	private boolean fromXML = false;
	
	public PropertieLoadStrategy() {
		super();
	}
	
	public PropertieLoadStrategy(boolean fromXML) {
		super();
		this.setFromXML(fromXML);
	}

	@Override
	public void load(InputStream str, Loadable loadable) {
		try {
			Properties p = new Properties();
			
			if (isFromXML())
				p.loadFromXML(str);
			else 
				p.load(str);

			Set<String> names = p.stringPropertyNames();
			for (Iterator<String> iterator = names.iterator(); iterator.hasNext();) {
				String property = (String) iterator.next();
				AnnotationUtils.set(property, p.getProperty(property), loadable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isFromXML() {
		return fromXML;
	}

	public void setFromXML(boolean fromXML) {
		this.fromXML = fromXML;
	}

}