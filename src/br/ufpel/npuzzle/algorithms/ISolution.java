/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.algorithms;

import br.ufpel.npuzzle.facade.IBoard;

/**
 *
 * @author lmtor_000
 */
public interface ISolution {
    public IBoard resolutionGame(IBoard board);
    public void setMaxSizeList(Integer size);
    public Integer getMaxSizeList();
    public Integer getIteracoes();
    
}
