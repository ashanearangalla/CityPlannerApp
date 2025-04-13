/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

/**
 *
 * @author Ashane
 */
public class HeritageDecorator extends StructureDecorator{
    private String heritage;
    private String material;
    private boolean buildable = true;
    private String reason = "";
    
    public HeritageDecorator(Structure newStructure, String material, String heritage) {
        super(newStructure);
        this.material = material;
        this.heritage = heritage;
        
    }
    
    @Override
    public boolean isBuildable() {
            
        if(!(heritage).equals(material)) {
            buildable = false;
            reason += "Heritage rule: must use"+ heritage + "as material instead of " + material;
        }

        return tempStructure.isBuildable() && buildable;
    }
    
    @Override
    public String getReason() {
        if(buildable && tempStructure.isBuildable()) {
            return tempStructure.getReason();
        }
        return tempStructure.getReason() + reason;
    }
    
    
    
    
}
