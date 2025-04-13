/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.curtin.app;

/**
 *
 * @author Ashane
 */
public class ContaminatedDecorator extends StructureDecorator{
    
    public ContaminatedDecorator(Structure newStructure) {
        super(newStructure);
    }
    
    @Override
    public double getCost() {
        return tempStructure.getCost() * 1.5;
    }
    
}
