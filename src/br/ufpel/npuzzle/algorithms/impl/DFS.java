/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.algorithms.impl;

import br.ufpel.npuzzle.algorithms.ISolution;
import br.ufpel.npuzzle.facade.IBoard;
import br.ufpel.npuzzle.facade.impl.Board;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmtor_000
 */
public class DFS implements ISolution{

    private Integer qtdIterations=0;
    private Integer depthTree = 0;
    private List<IBoard> oldStates;
    private long tempoTotal=0;
    private Integer maxSizeList=0;
    
    public DFS(){
        this.oldStates = new ArrayList<>();
    }
    
    @Override
    public IBoard resolutionGame(IBoard board) {
        long tempoInicio = System.currentTimeMillis();
        List<IBoard> states = new ArrayList<>();
        List<IBoard> childrenStates = new ArrayList<>();
        
        
        states.add(board);
        this.oldStates.add(board);
        IBoard currentState = null;
        
        while(!states.isEmpty()){
            this.qtdIterations++;
            
            currentState = states.remove(states.size()-1);
            
            //System.out.println(currentState);
            if(currentState.isFinalState()){
                states.clear();
            }   
            else{
                states.addAll(this.optimization(currentState.makeNewStates()));
                this.setMaxSizeList(states.size());
                currentState = null;
            }
        }
        
        return currentState;
    }
    
    public IBoard resolutionGameLimited(IBoard board, Integer limit) {
        long tempoInicio = System.currentTimeMillis();
        List<IBoard> states = new ArrayList<>();
        
        states.add(board);
        this.oldStates.add(board);
        IBoard currentState = null;
        
        while(!states.isEmpty()){
            this.qtdIterations++;
            currentState = states.remove(states.size()-1);
            
            if(currentState.isFinalState()){
                states.clear();
            }   
            else{
                if(currentState.getProfundidade()!=limit){
                    states.addAll(this.optimization(currentState.makeNewStates()));
                    this.setMaxSizeList(states.size());
                    currentState = null;
                }
            }
        }
        
        
        return currentState;
    }
    
    private List<IBoard> optimization(List<IBoard> newStates){
        List<IBoard> optmizationStates = new ArrayList<>();
        for(IBoard currentState : newStates){
            if(!this.oldStates.contains(currentState)){
                optmizationStates.add(currentState);
                this.oldStates.add(currentState);
            }
        }
        
        return optmizationStates;
    }
    

    
    public static void main(String[] args){
        IBoard tabuleiroInicial = new Board(3);
        DFS solucaoProfundidade = new DFS();
        
        
        tabuleiroInicial = solucaoProfundidade.resolutionGameLimited(tabuleiroInicial,20);
        System.out.println(tabuleiroInicial);
        System.out.println("TEMPO TOTAL PROFUNDIDADE LIMITADA: "+solucaoProfundidade.tempoTotal);
        System.out.println("QUANTIDADE DE ITERACOES DA PROFUNDIDADE LIMITADA: "+solucaoProfundidade.qtdIterations);
        System.out.println("PROFUNDIDADE DA ARVORE DA PROFUNDIDADE LIMITADA: "+tabuleiroInicial.getProfundidade());
        
        
        tabuleiroInicial = solucaoProfundidade.resolutionGame(tabuleiroInicial);
        System.out.println(tabuleiroInicial);
        System.out.println("TEMPO TOTAL: "+solucaoProfundidade.tempoTotal);
        System.out.println("QUANTIDADE DE ITERACOES: "+solucaoProfundidade.qtdIterations);
        System.out.println("PROFUNDIDADE DA ARVORE: "+tabuleiroInicial.getProfundidade());        

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
