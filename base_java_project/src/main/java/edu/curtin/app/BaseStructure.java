/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.Map;

/**
 *
 * @author Ashane
 */
public class BaseStructure implements Structure{
    
    protected int floors;
    protected String material;
    protected String foundation;
    private boolean buildable = true;
    private String reason = "";
    
    public BaseStructure (int floors, String foundation, String material) {
        this.floors = floors;
        this.foundation = foundation;
        this.material = material;
    } 

    @Override
    public boolean isBuildable() {
        return buildable;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public double getCost() {
        int costPerFloor = 0;
        switch(material) {
            case "wood":  
                costPerFloor = 10000;
                break;
            case "stone":  
                costPerFloor = 50000;
                break;
            case "brick":  
                costPerFloor = 30000;
                break;
            case "concrete":  
                costPerFloor = 20000;
                break;
            default : 
                buildable = false;
                reason = "Unknown material.";
        }
        return floors * costPerFloor;
    }

    
    
    
}
