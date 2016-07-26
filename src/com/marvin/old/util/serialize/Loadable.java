package com.marvin.old.util.serialize;

import java.io.ObjectInputValidation;
import java.io.Serializable;

import com.marvin.old.util.serialize.strategy.ILoadStrategy;

public interface Loadable extends Serializable, ObjectInputValidation {

	void load(ILoadStrategy strategy);
	
}
