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

// rocky terrain
public class RockyDecorator extends StructureDecorator{
    private static final Logger logger = Logger.getLogger(RockyDecorator.class.getName());
    
    public RockyDecorator(Structure newStructure) {
        super(newStructure);
        logger.info("Rocky decorator is added");
    }
    
    
    
    @Override
    public double getCost() {
        return tempStructure.getCost() + 50000.00;
    }
    
    
}
