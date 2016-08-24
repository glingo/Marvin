package com.marvin.component.container.compiler;

import com.marvin.component.container.compiler.passes.CompilerPassInterface;
import com.marvin.component.container.compiler.passes.MergeExtensionCompilerPass;
import java.util.ArrayList;
import java.util.List;

public class PassConfig {
    
    
    public final static String AFTER_REMOVING = "after_removing";
    public final static String BEFORE_REMOVING = "before_removing";
    public final static String BEFORE_OPTIMIZATION = "before_optimization";
    public final static String REMOVING = "removing";
    public final static String OPTIMIZATION = "optimization";
    
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
    
    public void addOptimizationPass(CompilerPassInterface pass){
        this.optimizationPasses.add(pass);
    }
    
    public void addRemovingPass(CompilerPassInterface pass){
        this.removingPasses.add(pass);
    }
    
    public void addAfterRemovingPass(CompilerPassInterface pass){
        this.afterRemovingPasses.add(pass);
    }
    
    public void addBeforeRemovingPass(CompilerPassInterface pass){
        this.beforeRemovingPasses.add(pass);
    }
    
    public void addBeforeOptimizationPass(CompilerPassInterface pass){
        this.beforeOptimizationPasses.add(pass);
    }
    
    public void addPass(CompilerPassInterface pass, String type) throws Exception {
        switch(type) {
            case "after_removing" :
                addAfterRemovingPass(pass);
                break;
                
            case "before_removing" :
                addBeforeRemovingPass(pass);
                break;
                
            case "before_optimization" :
                addBeforeOptimizationPass(pass);
                break;
                
            case "optimization" :
                addOptimizationPass(pass);
                break;
                
            case "removing" :
                addRemovingPass(pass);
                break;
                
            default:
                String msg = String.format("Invalid compiler pass type '%s'", type);
                throw new Exception(msg);
        }
        
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
