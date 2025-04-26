/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

/**
 *
 * @author Ashane
 */
// Abstract Decorator
public abstract class StructureDecorator implements Structure{
    Structure tempStructure;
    
    
    public StructureDecorator(Structure newStructure) {
        tempStructure = newStructure;
    }

    @Override
    public boolean isBuildable() {
        return tempStructure.isBuildable();
    }

    @Override
    public String getReason() {
        return tempStructure.getReason();
    }

    @Override
    public double getCost() {
        return tempStructure.getCost();
    }
    
    
    
}
