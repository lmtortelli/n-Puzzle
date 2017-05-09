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

    @Override
    public IBoard resolutionGame(IBoard board) {
        DFS depthFirstSearch;
        Boolean findSolution = false;
        Integer limit = 0;
        IBoard finalState = null;
        
        while(!findSolution){
            depthFirstSearch = new DFS();
            finalState = depthFirstSearch.resolutionGameLimited(board,limit);
            
            limit++;
            if(finalState!=null && finalState.isFinalState()){
                findSolution = true;
            }
        }
        
        return finalState;
        
    }
    
    public static void main(String[] args){
        ISolution dfsi = new DFSI();
        IBoard tabuleiroInicial = new Board(3);
        tabuleiroInicial = dfsi.resolutionGame(tabuleiroInicial);
        System.out.println(tabuleiroInicial);
        //System.out.println("TEMPO TOTAL: "+dfsi.tempoTotal);
        //System.out.println("QUANTIDADE DE ITERACOES: "+dfsi.qtdIterations);
        System.out.println("PROFUNDIDADE DA ARVORE: "+tabuleiroInicial.getProfundidade());  
        
    }
    
}
