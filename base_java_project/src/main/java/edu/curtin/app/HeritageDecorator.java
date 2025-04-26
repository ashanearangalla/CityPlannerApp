/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.logging.Logger;

/**
 *
 * @author Ashane
 */
public class HeritageDecorator extends StructureDecorator{
    private static final Logger logger = Logger.getLogger(HeritageDecorator.class.getName());
    private String heritage;
    private String material;
    private boolean buildable = true;
    private String reason = "";
    
    public HeritageDecorator(Structure newStructure, String material, String heritage) {
        
        super(newStructure);
        logger.info("HeritageDecorator added");
        this.material = material;
        this.heritage = heritage;
        
    }
    
    @Override
    public boolean isBuildable() {
            
        if(!(heritage).equals(material)) {
            buildable = false;
            reason += "Heritage rule: must use "+ heritage + " as material instead of " + material;
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
