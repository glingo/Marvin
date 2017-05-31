package com.marvin.component.container.compiler;

import com.marvin.component.container.compiler.passes.CompilerPassInterface;
import com.marvin.component.container.compiler.passes.MergeExtensionCompilerPass;
import java.util.ArrayList;
import java.util.List;

public class PassConfig {
    
    public final static String AFTER_REMOVING       = "after_removing";
    public final static String BEFORE_REMOVING      = "before_removing";
    public final static String BEFORE_OPTIMIZATION  = "before_optimization";
    public final static String REMOVING             = "removing";
    public final static String OPTIMIZATION         = "optimization";
    
    protected CompilerPassInterface mergePass;
    
    protected List<CompilerPassInterface> beforeOptimizationPasses;
    protected List<CompilerPassInterface> optimizationPasses;
    protected List<CompilerPassInterface> beforeRemovingPasses;
    protected List<CompilerPassInterface> removingPasses;
    protected List<CompilerPassInterface> afterRemovingPasses;

    public PassConfig() {
        this.mergePass = new MergeExtensionCompilerPass();
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
    
    public void addOptimizationPass(CompilerPassInterface pass){
        
        if(this.optimizationPasses == null) {
            this.optimizationPasses = new ArrayList<>();
        }
        
        this.optimizationPasses.add(pass);
    }
    
    public void addRemovingPass(CompilerPassInterface pass){
        
        if(this.removingPasses == null) {
            this.removingPasses = new ArrayList<>();
        }
        
        this.removingPasses.add(pass);
    }
    
    public void addAfterRemovingPass(CompilerPassInterface pass){
        
        if(this.afterRemovingPasses == null) {
            this.afterRemovingPasses = new ArrayList<>();
        }
        
        this.afterRemovingPasses.add(pass);
    }
    
    public void addBeforeRemovingPass(CompilerPassInterface pass){
        
        if(this.beforeRemovingPasses == null) {
            this.beforeRemovingPasses = new ArrayList<>();
        }
        
        this.beforeRemovingPasses.add(pass);
    }
    
    public void addBeforeOptimizationPass(CompilerPassInterface pass){
        
        if(this.beforeOptimizationPasses == null) {
            this.beforeOptimizationPasses = new ArrayList<>();
        }
        
        this.beforeOptimizationPasses.add(pass);
    }
    
    public void addPass(CompilerPassInterface pass, String type) throws Exception {
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
                throw new Exception(msg);
        }
    }
    
    private List<CompilerPassInterface> controlPasses(List<CompilerPassInterface> passes) {
        
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
