/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.facade;

import br.ufpel.npuzzle.facade.impl.Position;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Tortelli
 */
public interface IBoard  extends Comparable {
    public Boolean isFinalState();
    public Position getEmptyPosition();
    public List<IBoard> makeNewStates();
    public Integer[][] getBoard();
    public Integer getSizeBoard();
    public Position getPosition();
    public Integer getProfundidade();
    public Integer getH();
    public void setH(Integer h);
    
    
}
