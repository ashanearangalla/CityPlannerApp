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
public class FloodRiskDecorator extends StructureDecorator{
    private static final Logger logger = Logger.getLogger(FloodRiskDecorator.class.getName());
    private int floors;
    private int risk;
    private boolean buildable = true;
    private String reason = "";
    
    // Decorator class decorated thebase structure
    public FloodRiskDecorator(Structure newStructure, int risk, int floors) {
        
        super(newStructure);
        logger.info("Flood rick decorator applied");
        this.risk = risk;
        this.floors = floors;
    }
    
    @Override
    public boolean isBuildable() {
        if(floors < 2) {
            buildable = false;
            reason += "Flood-risk rule: building must consist of more than 2 floors";
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
        return tempStructure.getCost()  * (1 + risk / 50.0);
    }
}
