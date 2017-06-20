package com.marvin.component.container.compiler;

import com.marvin.component.container.compiler.passes.CompilerPass;
import com.marvin.component.container.compiler.passes.MergeExtensionCompilerPass;
import java.util.ArrayList;
import java.util.List;

public class PassConfig {
    
    public final static String AFTER_REMOVING       = "after_removing";
    public final static String BEFORE_REMOVING      = "before_removing";
    public final static String BEFORE_OPTIMIZATION  = "before_optimization";
    public final static String REMOVING             = "removing";
    public final static String OPTIMIZATION         = "optimization";
    
    protected CompilerPass mergePass;
    
    protected List<CompilerPass> beforeOptimizationPasses;
    protected List<CompilerPass> optimizationPasses;
    protected List<CompilerPass> beforeRemovingPasses;
    protected List<CompilerPass> removingPasses;
    protected List<CompilerPass> afterRemovingPasses;

    public PassConfig() {
        this.mergePass = new MergeExtensionCompilerPass();
    }
    
    public List<CompilerPass> getPasses() {
        
        List<CompilerPass> passes = new ArrayList<>();
        
        passes.add(this.mergePass);
        passes.addAll(getBeforeOptimizationPasses());
        passes.addAll(getOptimizationPasses());
        passes.addAll(getBeforeRemovingPasses());
        passes.addAll(getRemovingPasses());
        passes.addAll(getAfterRemovingPasses());
        
        return passes;        
    }
    
    public List<CompilerPass> getOptimizationPasses(){
        return controlPasses(this.optimizationPasses);
    }
    
    public List<CompilerPass> getRemovingPasses(){
        return controlPasses(this.removingPasses);
    }
    
    public List<CompilerPass> getAfterRemovingPasses(){
        return controlPasses(this.afterRemovingPasses);
    }
    
    public List<CompilerPass> getBeforeRemovingPasses(){
        return controlPasses(this.beforeRemovingPasses);
    }
    
    public List<CompilerPass> getBeforeOptimizationPasses(){
        return controlPasses(this.beforeOptimizationPasses);
    }
    
    public void addOptimizationPass(CompilerPass pass){
        
        if(this.optimizationPasses == null) {
            this.optimizationPasses = new ArrayList<>();
        }
        
        this.optimizationPasses.add(pass);
    }
    
    public void addRemovingPass(CompilerPass pass){
        
        if(this.removingPasses == null) {
            this.removingPasses = new ArrayList<>();
        }
        
        this.removingPasses.add(pass);
    }
    
    public void addAfterRemovingPass(CompilerPass pass){
        
        if(this.afterRemovingPasses == null) {
            this.afterRemovingPasses = new ArrayList<>();
        }
        
        this.afterRemovingPasses.add(pass);
    }
    
    public void addBeforeRemovingPass(CompilerPass pass){
        
        if(this.beforeRemovingPasses == null) {
            this.beforeRemovingPasses = new ArrayList<>();
        }
        
        this.beforeRemovingPasses.add(pass);
    }
    
    public void addBeforeOptimizationPass(CompilerPass pass){
        
        if(this.beforeOptimizationPasses == null) {
            this.beforeOptimizationPasses = new ArrayList<>();
        }
        
        this.beforeOptimizationPasses.add(pass);
    }
    
    public void addPass(CompilerPass pass, String type) {
        switch(type) {
            case AFTER_REMOVING :
                addAfterRemovingPass(pass);
                break;
                
            case BEFORE_REMOVING :
                addBeforeRemovingPass(pass);
                break;
                
            case BEFORE_OPTIMIZATION :
                addBeforeOptimizationPass(pass);
                break;
                
            case OPTIMIZATION :
                addOptimizationPass(pass);
                break;
                
            case REMOVING :
                addRemovingPass(pass);
                break;
                
            default:
                String msg = String.format("Invalid compiler pass type '%s'", type);
                throw new IllegalArgumentException(msg);
        }
    }
    
    private List<CompilerPass> controlPasses(List<CompilerPass> passes) {
        
        if(passes == null) {
            passes = new ArrayList<>();
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
    
//    public static void main(String[] args) {
//        PassConfig config = new PassConfig();
//        System.out.println("lenght : " + config.getPasses().size());
//        System.out.println(config.getPasses());
//    }
    
}
