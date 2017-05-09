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
public class BFS implements ISolution{

    private Integer qtdIterations=0;
    private Integer depthTree = 0;
    private List<IBoard> oldStates;
    private long tempoTotal=0;
    
    public BFS(){
        this.oldStates = new ArrayList<>();
    }
    
    @Override
    public IBoard resolutionGame(IBoard board) {
        long tempoInicio = System.currentTimeMillis();
        List<IBoard> states = new ArrayList<>();
        states.add(board);
        this.oldStates.add(board);
        IBoard currentState = null;
        
        while(!states.isEmpty()){
            this.qtdIterations++;
            
            currentState = states.remove(0);
            if(currentState.isFinalState()){
                states.clear();
            }   
            else{
                states.addAll(this.optimization(currentState.makeNewStates()));
                currentState = null;
            }
        }
        this.tempoTotal = System.currentTimeMillis()-tempoInicio;
        
        return currentState;
    }
    
    private List<IBoard> optimization(List<IBoard> newStates){
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
    
    public static void main(String[] args){
        IBoard tabuleiroInicial = new Board(4);
        BFS solucaoAmplitude = new BFS();
        tabuleiroInicial = solucaoAmplitude.resolutionGame(tabuleiroInicial);
        System.out.println(tabuleiroInicial);
        System.out.println("TEMPO TOTAL: "+solucaoAmplitude.tempoTotal);
        System.out.println("QUANTIDADE DE ITERACOES: "+solucaoAmplitude.qtdIterations);
        System.out.println("PROFUNDIDADE DA ARVORE: "+tabuleiroInicial.getProfundidade());        
        
    }
}
