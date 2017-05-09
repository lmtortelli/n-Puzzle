/*x
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.facade.impl;

import br.ufpel.npuzzle.facade.IRules;
import br.ufpel.npuzzle.domain.Move;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmtor_000
 */
public class Rules implements IRules {

    @Override
    public List<Integer> check(Position p, Integer sizeBoard) {
        List avaibleMovements  = new ArrayList<Integer>();
        Boolean top= false;
        Boolean bottom= false;
        Boolean left= false;
        Boolean right = false;
        // Esta na Borda Superior
        if(p.getX().compareTo(0)==0){
            top = true;
            avaibleMovements.add(Move.DOWN);
        }
        
        //Esta na Borda Inferior
        else if(p.getX().compareTo(sizeBoard)==0){
            bottom = true;
            avaibleMovements.add(Move.UP);
        }
        
        //Esta no canto esquerdo
        if (p.getY().compareTo(0)==0){
            left = true;
            avaibleMovements.add(Move.RIGHT);
        }
        else if(p.getY().compareTo(sizeBoard)==0){
            right  = true;
            avaibleMovements.add(Move.LEFT);
        }

        if(right){
            if(!top){
                if(!avaibleMovements.contains(Move.UP))
                    avaibleMovements.add(Move.UP);
            }
            if(!bottom){
                if(!avaibleMovements.contains(Move.DOWN))
                    avaibleMovements.add(Move.DOWN);
            }
        }
        
        else if(left){
            if(!top){
                if(!avaibleMovements.contains(Move.UP))
                    avaibleMovements.add(Move.UP);
            }
            if(!bottom){
                if(!avaibleMovements.contains(Move.DOWN))
                    avaibleMovements.add(Move.DOWN);
            }
        }
        
        if(top){
            if(!left){
                if(!avaibleMovements.contains(Move.LEFT))
                    avaibleMovements.add(Move.LEFT);
            }
            if(!right){
                if(!avaibleMovements.contains(Move.RIGHT))
                    avaibleMovements.add(Move.RIGHT);
            }
        }

        else if(bottom){
            if(!left){
                if(!avaibleMovements.contains(Move.LEFT))
                    avaibleMovements.add(Move.LEFT);
            }
            if(!right){
                if(!avaibleMovements.contains(Move.RIGHT))
                    avaibleMovements.add(Move.RIGHT);
            }
        }

        else if(!top && !bottom && !left && !right){
            avaibleMovements.add(Move.UP);
            avaibleMovements.add(Move.DOWN);
            avaibleMovements.add(Move.LEFT);
            avaibleMovements.add(Move.RIGHT);
        }


        return avaibleMovements;
    }
    
}
