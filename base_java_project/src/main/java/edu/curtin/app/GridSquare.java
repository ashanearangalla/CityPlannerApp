/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ashane
 */
public class GridSquare {

    
    private String terrain;
    private Map<String, String> zoningRules;
    private boolean buildable;
    private int floors;
    private String material;
    private String foundation;
    
    public GridSquare(String terrain) {
        this.terrain = terrain;
        this.zoningRules = new HashMap<>();
    }
    
    public String getTerrain() {
        return terrain;
    }
    
    public void addZoningRule(String ruleType, String rule) {
        this.zoningRules.put(ruleType, rule);
    }
    
    public Map<String, String> getZoningRules() {
        return zoningRules;
    }
    
    public boolean getBuildable() {
        return buildable;
    }
    
    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }

    public int getFloors() {
        return floors;
    }


    public void setFloors(int floors) {
        this.floors = floors;
    }


    public String getMaterial() {
        return material;
    }


    public void setMaterial(String material) {
        this.material = material;
    }


    public String getFoundation() {
        return foundation;
    }


    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }
    
}
