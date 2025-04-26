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
public class SwampDecorator extends StructureDecorator{
    private static final Logger logger = Logger.getLogger(SwampDecorator.class.getName());
    private String material;
    private String foundation;
    private int floors;
    private boolean buildable = true;
    private String reason = "";
    
    public SwampDecorator(Structure newStructure,int floors, String material, String foundation) {
        
        super(newStructure);
        this.material = material;
        this.foundation = foundation;
        this.floors = floors;
        logger.info("swamp decorator is added");
    }
    
    @Override
    public boolean isBuildable() {
        if ("slab".equals(foundation)) {
            buildable = false;
            reason += "Slab foundation cannot be built on swamp land";
        }
        
        if("wood".equalsIgnoreCase(material)) {
            buildable = false;
            reason += "Wood Structure cannot be built on swamp land";
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
    
    @Override
    public double getCost() {
        return tempStructure.getCost() + 20000.00 * floors;
    }
    
}
