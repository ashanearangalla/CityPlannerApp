/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

/**
 *
 * @author Ashane
 */
public class HeightLimitDecorator extends StructureDecorator{
    private int floors;
    private int maxFloors;
    private boolean buildable = true;
    private String reason = "";
    
    public HeightLimitDecorator(Structure newStructure, int floors, int maxFloors) {
        super(newStructure);
        this.floors = floors;
        this.maxFloors = maxFloors;
    }
    
    @Override
    public boolean isBuildable() {
            
        if(!(maxFloors < floors)) {
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
