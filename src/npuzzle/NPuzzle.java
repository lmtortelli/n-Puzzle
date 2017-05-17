/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle;

import br.ufpel.npuzzle.algorithms.ISolution;
import br.ufpel.npuzzle.algorithms.impl.BFS;
import br.ufpel.npuzzle.algorithms.impl.DFS;
import br.ufpel.npuzzle.algorithms.impl.DFSI;
import br.ufpel.npuzzle.facade.IBoard;
import br.ufpel.npuzzle.facade.impl.Board;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmtor_000
 */
public class NPuzzle {
    
    public static void main(String[] args){
        IBoard tabuleiroInicial;
    
        ISolution amplitude;
        ISolution profundidade = new DFS();
        ISolution profundidadeIterativa =new DFSI();
        DFS profundidadeLimitada = new DFS();
        List<String> timeAmplitude = new ArrayList<>();
        List<String> timeProfundidade = new ArrayList<>();
        List<String> timeProfundidadeLimitada = new ArrayList<>();
        List<String> timeProfundidadeIterativa = new ArrayList<>();
        IBoard result;
        List<IBoard> tabuleiros = new ArrayList<>();
        long tempoInicial,tempoFinal;
        for(int index = 0 ; index < 30 ; index++){
            // -- Ampltiude
            amplitude = new BFS();
            System.out.println((index+1));
            tabuleiroInicial = new Board(3);
            tabuleiros.add(tabuleiroInicial);
            tempoInicial = System.currentTimeMillis();
            result = amplitude.resolutionGame(tabuleiroInicial);
            tempoFinal = System.currentTimeMillis() - tempoInicial;
            //System.out.println(result);
            timeAmplitude.add(tempoFinal+";"+amplitude.getMaxSizeList()+";"+result.getProfundidade()+";"+amplitude.getIteracoes());
        }
        
        for(String info : timeAmplitude){
            System.out.println(info);
        }
//        
//        for(int index = 0 ; index < 20 ; index++){
//            //- Profundidade Limitada --
//            System.out.println(index+1);
//            tabuleiroInicial = tabuleiros.get(index);
//         
//            tempoInicial = System.currentTimeMillis();
//            result = profundidadeLimitada.resolutionGameLimited(tabuleiroInicial,20);
//            tempoFinal = System.currentTimeMillis() - tempoInicial;
//            timeProfundidadeLimitada.add(tempoFinal+";"+profundidade.getMaxSizeList()+";"+result.getProfundidade());
//        }
//        
//        for(String info : timeProfundidadeLimitada){
//            System.out.println(info);
//        }
        
        
        
        
        for(int index = 0 ; index < 30 ; index++){
            System.out.println(index+1);
            tabuleiroInicial = tabuleiros.get(index);
            profundidadeIterativa = new DFSI();
            //- Profundidade Limitada --
            tempoInicial = System.currentTimeMillis();
            result = profundidadeIterativa.resolutionGame(tabuleiroInicial);
            tempoFinal = System.currentTimeMillis() - tempoInicial;
            timeProfundidadeIterativa.add(tempoFinal+";"+profundidadeIterativa.getMaxSizeList()+";"+result.getProfundidade()+";"+profundidadeIterativa.getIteracoes());
        
        }
        
        for(String info : timeProfundidadeIterativa){
            System.out.println(info);
        }
        
        for(int index = 0 ; index < 20 ; index++){
            System.out.println(index+1);
            tabuleiroInicial = tabuleiros.get(index);
            profundidade = new DFS();
            //Profundidade
            tempoInicial = System.currentTimeMillis();
            result = profundidade.resolutionGame(tabuleiroInicial);
            tempoFinal = System.currentTimeMillis() - tempoInicial;
            timeProfundidade.add(tempoFinal+";"+profundidade.getMaxSizeList()+";"+result.getProfundidade()+";"+profundidade.getIteracoes());
        }
        
        for(String info : timeProfundidade){
            System.out.println(info);
        }
    }
    
    
    
    
//    tabuleiroInicial = solucaoAmplitude.resolutionGame(tabuleiroInicial);
//    System.out.println(tabuleiroInicial);
//    System.out.println("TEMPO TOTAL: "+solucaoAmplitude.tempoTotal);
//    System.out.println("QUANTIDADE DE ITERACOES: "+solucaoAmplitude.qtdIterations);
//    System.out.println("PROFUNDIDADE DA ARVORE: "+tabuleiroInicial.getProfundidade());
    
    
}
