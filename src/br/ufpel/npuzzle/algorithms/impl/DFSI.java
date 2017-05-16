/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.algorithms.impl;

import br.ufpel.npuzzle.algorithms.ISolution;
import br.ufpel.npuzzle.facade.IBoard;
import br.ufpel.npuzzle.facade.impl.Board;

/**
 *
 * @author lmtor_000
 */
public class DFSI implements ISolution{

    private Integer maxSizeList=0;
    private Integer qtdIterations = 0;
    
    @Override
    public IBoard resolutionGame(IBoard board) {
        DFS depthFirstSearch;
        Boolean findSolution = false;
        Integer limit = 0;
        IBoard finalState = null;
        
        while(!findSolution){
            depthFirstSearch = new DFS();
            finalState = depthFirstSearch.resolutionGameLimited(board,limit);
            this.setMaxSizeList(depthFirstSearch.getMaxSizeList());
            this.qtdIterations = depthFirstSearch.getMaxSizeList();
            limit++;
            if(finalState!=null && finalState.isFinalState()){
                findSolution = true;
            }
        }
        
        return finalState;
        
    }
    
    @Override
    public void setMaxSizeList(Integer size) {     
        if(size.compareTo(this.maxSizeList)==1){
            this.maxSizeList = size;
        }
    }

    @Override
    public Integer getMaxSizeList() {
        return this.maxSizeList;
    }

    @Override
    public Integer getIteracoes() {
        return this.qtdIterations;
    }
    
}
