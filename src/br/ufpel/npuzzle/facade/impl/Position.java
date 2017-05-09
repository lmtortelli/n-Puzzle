/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.facade.impl;

/**
 *
 * @author lmtor_000
 */
public class Position {
    private Integer x;
    private Integer y;

    public Position(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
    
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = this.x + x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = this.y + y;
    }
    
    
}
