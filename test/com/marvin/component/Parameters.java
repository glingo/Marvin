/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.component;

import java.util.ArrayList;

/**
 *
 * @author cdi305
 */
public abstract class Parameters {
    
    public static final Object[] serviceA = new Object[]{"serviceA", 0};
    public static final Object[] serviceB = new Object[]{Services.serviceA, "serviceB", 1};
    public static final Object[] serviceC = new Object[]{Services.serviceA, "serviceB", 1, new ArrayList<>()};
}
