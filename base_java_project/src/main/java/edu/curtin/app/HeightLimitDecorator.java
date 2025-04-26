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

// decorator class to decorate base structure
public class HeightLimitDecorator extends StructureDecorator{
    private static final Logger logger = Logger.getLogger(HeightLimitDecorator.class.getName());
    private int floors;
    private int maxFloors;
    private boolean buildable = true;
    private String reason = "";
    
    public HeightLimitDecorator(Structure newStructure, int floors, int maxFloors) {
        super(newStructure);
        this.floors = floors;
        this.maxFloors = maxFloors;
        logger.info("height limit decorator added");
    }
    
    @Override
    public boolean isBuildable() {
            
        if(maxFloors < floors) {
            buildable = false;
            reason += "Height-limit rule: cannot exceed max height of "+ maxFloors + " floors";
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
