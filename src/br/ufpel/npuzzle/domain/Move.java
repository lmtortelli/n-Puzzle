/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.domain;

/**
 *
 * @author lmtor_000
 */
public enum Move {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);
    
    public int movementValue;
    
    Move(int value){
        this.movementValue = value;
    }
    
    public int getMovementValue(){
        return movementValue;
    }
}
