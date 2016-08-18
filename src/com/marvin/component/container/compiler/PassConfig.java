package com.marvin.component.container.compiler;

import com.marvin.component.container.compiler.passes.CompilerPassInterface;
import com.marvin.component.container.compiler.passes.MergeExtensionCompilerPass;
import java.util.ArrayList;
import java.util.List;

public class PassConfig {
    
    protected CompilerPassInterface mergePass;
    
    protected List<CompilerPassInterface> optimizationPasses;
    protected List<CompilerPassInterface> removingPasses;
    
    protected List<CompilerPassInterface> beforeRemovingPasses;
    protected List<CompilerPassInterface> afterRemovingPasses;
    
    protected List<CompilerPassInterface> beforeOptimizationPasses;

    public PassConfig() {
        this.mergePass = new MergeExtensionCompilerPass();
        
        this.optimizationPasses = new ArrayList<>();
        this.removingPasses = new ArrayList<>();
        
        this.beforeRemovingPasses = new ArrayList<>();
        this.afterRemovingPasses = new ArrayList<>();
        this.beforeOptimizationPasses = new ArrayList<>();
    }
    
    public List<CompilerPassInterface> getPasses() {
        
        List<CompilerPassInterface> passes = new ArrayList<>();
        
        passes.add(this.mergePass);
        passes.addAll(getBeforeOptimizationPasses());
        passes.addAll(getOptimizationPasses());
        passes.addAll(getBeforeRemovingPasses());
        passes.addAll(getRemovingPasses());
        passes.addAll(getAfterRemovingPasses());
        
        return passes;        
    }
    
    public List<CompilerPassInterface> getOptimizationPasses(){
        return controlPasses(this.optimizationPasses);
    }
    
    public List<CompilerPassInterface> getRemovingPasses(){
        return controlPasses(this.removingPasses);
    }
    
    public List<CompilerPassInterface> getAfterRemovingPasses(){
        return controlPasses(this.afterRemovingPasses);
    }
    
    public List<CompilerPassInterface> getBeforeRemovingPasses(){
        return controlPasses(this.beforeRemovingPasses);
    }
    
    public List<CompilerPassInterface> getBeforeOptimizationPasses(){
        return controlPasses(this.beforeOptimizationPasses);
    }
    
    private List<CompilerPassInterface> controlPasses(List<CompilerPassInterface> passes) {
        
        if(passes == null) {
            return new ArrayList<>();
        }
        
        return passes;
    }
    
//    private CompilerPassInterface[] sortPasses(CompilerPassInterface[] passes) {
//        
//        if(passes == null || passes.length == 0) {
//            return new CompilerPassInterface[]{};
//        }
//        
//        // we need to implements comparable !
//        Arrays.sort(passes);
//        
//        return passes;
//    }
    
    public static void main(String[] args) {
        PassConfig config = new PassConfig();
        System.out.println("lenght : " + config.getPasses().size());
        System.out.println(config.getPasses());
    }
    
}
