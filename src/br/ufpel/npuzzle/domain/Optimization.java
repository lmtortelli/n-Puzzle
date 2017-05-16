/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.domain;

import br.ufpel.npuzzle.facade.IBoard;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hakecomp
 */
public class Optimization {
    
    private List<IBoard> oldStates;
    
    public Optimization(){
        this.oldStates = new ArrayList<>();
    }
    
    public List<IBoard> optimization(List<IBoard> newStates){
        List<IBoard> optmizationStates = new ArrayList<>();
        //System.out.println(this.oldStates.size());
        for(IBoard currentState : newStates){
            if(!this.oldStates.contains(currentState)){
                optmizationStates.add(currentState);
                this.oldStates.add(currentState);
            }
        }
        
        return optmizationStates;
    }
    
    public List<IBoard> getOldStates() {
        return oldStates;
    }

    public void setOldStates(List<IBoard> oldStates) {
        this.oldStates = oldStates;
    }
    
}
