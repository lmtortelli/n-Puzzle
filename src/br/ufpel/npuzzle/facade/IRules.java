/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.facade;

import br.ufpel.npuzzle.facade.impl.Position;
import java.util.List;

/**
 *
 * @author lmtor_000
 */
public interface IRules {
    public List<Integer> check(Position p, Integer sizeBoard);
    
}
