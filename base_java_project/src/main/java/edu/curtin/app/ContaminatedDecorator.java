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
public class ContaminatedDecorator extends StructureDecorator{
    private static final Logger logger = Logger.getLogger(ContaminatedDecorator.class.getName());
    
    public ContaminatedDecorator(Structure newStructure) {
        super(newStructure);
        logger.info("Contaminated decorator applied");
    }
    
    // contaminated land multiplies the cost by 1.5
    @Override
    public double getCost() {
        return tempStructure.getCost() * 1.5;
    }
    
}
