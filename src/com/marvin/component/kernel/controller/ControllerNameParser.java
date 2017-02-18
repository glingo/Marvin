package com.marvin.component.kernel.controller;

import com.marvin.component.kernel.Kernel;
import com.marvin.component.kernel.bundle.Bundle;
import java.util.Set;

public class ControllerNameParser {
    
    private static final String CONTROLLER_PATTERN = "%s.controller.%sController::%s";
    private final Kernel kernel;

    public ControllerNameParser(Kernel kernel) {
        this.kernel = kernel;
    }
    
    public int levenshtein(CharSequence lhs, CharSequence rhs) {      
        int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];        
                                                                                 
        for (int i = 0; i <= lhs.length(); i++) {                                 
            distance[i][0] = i;                   
        }
        
        for (int j = 1; j <= rhs.length(); j++) {        
            distance[0][j] = j;   
        }                                                                       
                                                   
        for (int i = 1; i <= lhs.length(); i++) {
            for (int j = 1; j <= rhs.length(); j++){                 
                distance[i][j] = Math.min(
                    Math.min(
                            distance[i - 1][j] + 1,
                            distance[i][j - 1] + 1
                    ) , distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1)
                );
            }  
        }     
        
        return distance[lhs.length()][rhs.length()];                           
    }
    
    private String findAlternative(String nonExistant){
        Set<String> names = this.kernel.getBundles().keySet();
        
        String alternative = null;
        Integer shortest = null;
        for (String name : names) {
            if(name.contains(nonExistant)) {
                // there is a partial match return it !
                return name;
            }
            
            int lev = levenshtein(nonExistant, name);
            
            if(lev <= nonExistant.length() / 3 && (alternative == null || lev < shortest)) {
                alternative = name;
                shortest = lev;
            }
            
        }
        
        return alternative;
    }

    public String parse(String controller) throws Exception {
        String original = controller;
        
        String[] fragments = controller.split(":");
        
        if(fragments.length != 3) {
            String msg = String.format("The '%s' controller is not a valid 'a:b:c' controller string.", controller);
            throw new Exception(msg);
        }
        
        String name = fragments[0];
        String ctrl = fragments[1];
        String action = fragments[2];
        
        Bundle bundle = this.kernel.getBundle(name);
        
        if(bundle == null) {
            String msg = String.format(
                "The '%s' (from the _controller value '%s') does not exist or is not enabled in your kernel!.", 
                fragments[0],
                original
            );
            
            String alternative = findAlternative(name);
            
            if(alternative != null) {
                msg += String.format("\nDid you mean '%s:%s:%s' ?\n", alternative, ctrl, action);
            }
            
            throw new Exception(msg);
        }
        
        return String.format(CONTROLLER_PATTERN, bundle.getNamespace(), ctrl, action);
    }
    
}
