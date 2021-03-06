/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.algorithms.impl;

import br.ufpel.npuzzle.algorithms.ISolution;
import br.ufpel.npuzzle.domain.Optimization;
import br.ufpel.npuzzle.facade.IBoard;
import br.ufpel.npuzzle.facade.impl.Board;
import br.ufpel.npuzzle.facade.impl.Position;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author hakecomp
 */
public class AStar  extends Optimization implements ISolution {

    Map<Integer,Position> location;
    private Integer qtdIterations=0;
    private Integer depthTree = 0;
    private List<IBoard> oldStates;
    private long tempoTotal=0;
    private Integer maxSizeList = 0;
    private Boolean heuristicOne;
    
    public AStar(Boolean heuristicOne){
        this.location = new HashMap<>();
        this.heuristicOne = true;
    }
    
    @Override
    public IBoard resolutionGame(IBoard board) {
        this.init(board.getSizeBoard());
        //System.out.println(this.h1(board));
        Optimization opt = new Optimization();
        long tempoInicio = System.currentTimeMillis();
        List<IBoard> states = new ArrayList<>();
        List<IBoard> childrens = new ArrayList<>();
        
        board.setH(this.h1(board));
        states.add(board);
        //this.oldStates.add(board);
        opt.getOldStates().add(board);
        IBoard currentState = null;
        
        while(!states.isEmpty()){
            this.qtdIterations++;
            currentState = states.remove(0);
            if(currentState.isFinalState()){
                states.clear();
            }   
            else{
                states.addAll(opt.optimization(currentState.makeNewStates()));
                this.appH(states);
                Collections.sort(states);
                currentState = null;
                this.setMaxSizeList(states.size());

            }
        }
        this.tempoTotal = System.currentTimeMillis()-tempoInicio;
        
        return currentState;
        
    }
    
    //Manhattam
    private Integer h1(IBoard board){
        Position p;
        Integer distanceManhattan = 0;
        for(int i = 0; i < board.getSizeBoard()  ; i++){
            
            for(int k = 0 ; k < board.getSizeBoard() ; k++){
                if(board.getBoard()[i][k].compareTo(0)!=0){
                    p = this.location.get(board.getBoard()[i][k]);
                    distanceManhattan = distanceManhattan +
                            (Math.abs(p.getX() - i) + Math.abs(p.getY() - k));
                }
            }
        }
            
        
        return distanceManhattan;
    }
    
    //Hamming
    private Integer h2(IBoard board){
        Integer j = 0;
        Integer distanceHamming = 0;
        for(int i = 0 ; i < board.getSizeBoard() ; i++){
            for(int k = 0 ; k < board.getSizeBoard() ; k++){
                if(j.compareTo(board.getBoard()[i][k])!=0){
                    distanceHamming++;
                }
                j++;
            }
        }
        
        return distanceHamming;
    }
    
    private void appH(List<IBoard> childrens) {
        //Manhattan
        
        if(heuristicOne)
            for(IBoard board : childrens){
                board.setH(this.h1(board)+board.getProfundidade());
            }
        else //Hamming
            for(IBoard board : childrens){
                board.setH(this.h2(board)+board.getProfundidade());
            }
    }
    


    private void init(Integer sizeBoard) {
        Integer value = 0;
        for (int i = 0 ; i < sizeBoard ; i++){
            for(int k = 0 ; k < sizeBoard ; k++){
                this.location.put(value, new Position(i,k));
                value++;
            }
        }
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
