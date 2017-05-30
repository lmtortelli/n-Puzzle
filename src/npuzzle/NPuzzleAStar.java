/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle;

import br.ufpel.npuzzle.algorithms.ISolution;
import br.ufpel.npuzzle.algorithms.impl.AStar;
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
public class NPuzzleAStar {
    
    public static void main(String[] args){
        NPuzzleAStar npuzzle = new NPuzzleAStar();
        IBoard tabuleiroInicial;
    
        ISolution aStarH1;
        ISolution aStarH2;
        ISolution profundidadeIterativa =new DFSI();
        List<String> resultDfsi = new ArrayList<>();
        List<String> resultAStarH1 = new ArrayList<>();
        List<String> resultAStarH2 = new ArrayList<>();

        IBoard result;
        List<IBoard> tabuleiros = new ArrayList<>();
        long tempoInicial,tempoFinal;
        
        // RESOLUCAO 5 PASSOS  - DOS TRES METODOS
        for(int iterations = 10000 ; iterations <= 10000 ; iterations+=100){
            System.out.println("#########################################################");
            tabuleiros = npuzzle.makeBoards(iterations);

            System.out.println("A* H1 - "+iterations+" PASSOS");
            for(int index = 0 ; index < 30 ; index++){
                // -- Ampltiude
                aStarH1 = new AStar(true);
                tempoInicial = System.currentTimeMillis();
                result = aStarH1.resolutionGame(tabuleiros.get(index));
                tempoFinal = System.currentTimeMillis() - tempoInicial;
                //System.out.println(result);
                resultAStarH1.add(tempoFinal+";"+aStarH1.getMaxSizeList()+";"+result.getProfundidade()+";"+aStarH1.getIteracoes());
            }

            for(String info : resultAStarH1){
                System.out.println(info);
            }
            resultAStarH1.clear();

            System.out.println("A* H2 - "+iterations+" PASSOS");
            for(int index = 0 ; index < 30 ; index++){
                // -- Ampltiude
                aStarH2 = new AStar(false);
                tempoInicial = System.currentTimeMillis();
                result = aStarH2.resolutionGame(tabuleiros.get(index));
                tempoFinal = System.currentTimeMillis() - tempoInicial;
                //System.out.println(result);
                resultAStarH2.add(tempoFinal+";"+aStarH2.getMaxSizeList()+";"+result.getProfundidade()+";"+aStarH2.getIteracoes());
            }

            for(String info : resultAStarH2){
                System.out.println(info);
            }
            resultAStarH2.clear();

            
            
            
            System.out.println(" ");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            tabuleiros.clear();
            
        }
        

            
        
    }
    
    
    private List<IBoard> makeBoards(int i) {
        List<IBoard> boards = new ArrayList<>();
        
        for (int k = 0; k < 50 ; k++){
            boards.add(new Board(3,i));
        }
        return boards;
    }
    
    
}
