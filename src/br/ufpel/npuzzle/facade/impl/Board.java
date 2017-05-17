/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpel.npuzzle.facade.impl;

import br.ufpel.npuzzle.domain.Move;
import br.ufpel.npuzzle.facade.IBoard;
import br.ufpel.npuzzle.facade.IRules;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author lmtor_000
 */
public class Board implements IBoard{
    private Position position;
    private Integer[][] board;
    private Integer sizeBoard;
    private Integer profundidade;
    private Integer qtdEmbaralhamento=0;
    private Integer h;
    
    public Board(Integer n){
        this.board = new Integer[n][n];
        this.sizeBoard = n;
        this.init();
        this.profundidade=0;
        this.shuffle();
        this.toString();
        this.profundidade=0;
    }
    
    public Board(Integer n, Integer m){
        this.board = new Integer[n][n];
        this.sizeBoard = n;
        this.init();
        this.profundidade=0;
        this.qtdEmbaralhamento = m;
        this.shuffle();
        this.toString();
        this.profundidade=0;
    }
    
    public Board(Integer[][] board, Position p, Integer n, Integer profundidade){        
        this.position = new Position(p.getX(),p.getY());
        this.sizeBoard = n;
        this.board = this.clone(board);
        this.profundidade = profundidade+1;
    }
    
    
    private IBoard left() {
        Integer[][] novoTabuleiro;
        Position novaPosicao = new Position(this.getPosition().getX(),this.getPosition().getY());
        novoTabuleiro = this.clone(board);
        
        novoTabuleiro[this.position.getX()][this.position.getY()] = novoTabuleiro[this.position.getX()][this.position.getY()-1]; 
        novoTabuleiro[this.position.getX()][this.position.getY()-1] = 0;
        novaPosicao.setY(-1);
       
        
        return new Board(novoTabuleiro,novaPosicao,this.sizeBoard,this.profundidade);
    }

    
    private IBoard right() {
        Integer[][] novoTabuleiro;
        Position novaPosicao = new Position(this.getPosition().getX(),this.getPosition().getY());
        novoTabuleiro = this.clone(board);
        
        novoTabuleiro[this.position.getX()][this.position.getY()] = novoTabuleiro[this.position.getX()][this.position.getY()+1]; 
        novoTabuleiro[this.position.getX()][this.position.getY()+1] = 0;
        novaPosicao.setY(1);
        return new Board(novoTabuleiro,novaPosicao,this.sizeBoard,this.profundidade);
    }

    
    private IBoard up() {
        Integer[][] novoTabuleiro;
        Position novaPosicao = new Position(this.getPosition().getX(),this.getPosition().getY());
        novoTabuleiro = this.clone(board);
        
        novoTabuleiro[this.position.getX()][this.position.getY()] = novoTabuleiro[this.position.getX()-1][this.position.getY()]; 
        novoTabuleiro[this.position.getX()-1][this.position.getY()] = 0;
        novaPosicao.setX(-1);
        return new Board(novoTabuleiro,novaPosicao,this.sizeBoard,this.profundidade);
    }

    
    private IBoard down() {
        Integer[][] novoTabuleiro;
        Position novaPosicao = new Position(this.getPosition().getX(),this.getPosition().getY());
        novoTabuleiro = this.clone(board);
        
        novoTabuleiro[this.position.getX()][this.position.getY()] = novoTabuleiro[this.position.getX()+1][this.position.getY()]; 
        novoTabuleiro[this.position.getX()+1][this.position.getY()] = 0;
        novaPosicao.setX(1);
        return new Board(novoTabuleiro,novaPosicao,this.sizeBoard,this.profundidade);
    }

    
    private IBoard shuffle() {
        Random generatorRandom = new Random();
        if(this.qtdEmbaralhamento==0){
            this.qtdEmbaralhamento = generatorRandom.nextInt(40);
        }
        IRules rulesOfGame = new Rules();
        List avaibleMovements;
        Integer rule;
        Move move;
        IBoard a;
        //for (Integer index = 0; index.compareTo(qtdSteps) == -1 ; index++){
        for (Integer index = 0; index.compareTo(this.qtdEmbaralhamento) == -1 ; index++){
            avaibleMovements = rulesOfGame.check(this.position, sizeBoard-1);
            move = (Move) avaibleMovements.get(generatorRandom.nextInt(avaibleMovements.size()));
            rule = move.movementValue;
            a = this.makeMovement(rule);
            this.board = this.clone(a.getBoard());
            this.sizeBoard = a.getSizeBoard();
            this.position = a.getPosition();
            
        }
        return this;
    }

    @Override
    public Position getEmptyPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void init(){
        Integer cell = 0;
        this.position = new Position(0,0);
        for (Integer i=0; i.compareTo(this.sizeBoard) == -1 ;i++){
            for (Integer j=0; j.compareTo(this.sizeBoard) == -1 ;j++){
                this.board[i][j] = cell;
                cell++;
            }
        }
    }

    @Override
    public String toString() {
        for (int k = 0 ; k < this.sizeBoard ; k ++){
            for ( int j = 0 ; j < this.sizeBoard ; j++ ){
                System.out.print(this.board[k][j]+" ");
            }
            System.out.println();
        }
        
        return "";
    }
    
    private Integer[][] clone(Integer[][] board){
        Integer[][] novoTabuleiro = new Integer[this.sizeBoard][this.sizeBoard];
        for (int i = 0 ; i< this.sizeBoard; i++){
            for (int k = 0 ; k< this.sizeBoard; k++){
                novoTabuleiro[i][k] = board[i][k];
                
            }
        }
        return novoTabuleiro;
    }
    
    @Override
    public Boolean isFinalState() {
        int aux = -1;
        for (int i = 0 ; i < this.sizeBoard ; i++){
            for (int k = 0 ; k < this.sizeBoard ; k++){
                if(!(this.board[i][k] > aux)){
                    return false;
                }
                aux = this.board[i][k];
            }
        }
        
        return true;
    }

    @Override
    public List<IBoard> makeNewStates() {
        IRules rulesOfGame = new Rules();
        List<IBoard> newStates = new ArrayList<>();
        List avaibleMovements;
        Integer rule;
        Move move;
        IBoard novoEstado;
        avaibleMovements = rulesOfGame.check(this.position, sizeBoard-1);
        while(!avaibleMovements.isEmpty()){
            move = (Move) avaibleMovements.remove(0);
            newStates.add(this.makeMovement(move.getMovementValue()));
        }
        
        return newStates;
    }

    private IBoard makeMovement(Integer rule) {
        switch(rule){
                case 0:
                    return this.up();
                    
                case 1:
                    return this.down();
                    
                case 2:
                    return this.left();
                     
                case 3:
                    return this.right();
                default:
                    break;
            }
        
        return null;
        
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer[][] getBoard() {
        return board;
    }

    public void setBoard(Integer[][] board) {
        this.board = board;
    }

    public Integer getSizeBoard() {
        return sizeBoard;
    }

    public void setSizeBoard(Integer sizeBoard) {
        this.sizeBoard = sizeBoard;
    }

    @Override
    public boolean equals(Object obj) {
        IBoard b = (Board) obj;
        for (int i=0 ; i < this.sizeBoard; i++){
            for (int j=0 ; j < this.sizeBoard; j++){
                if(this.board[i][j].compareTo(b.getBoard()[i][j])!=0){
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public Integer getProfundidade(){
        return this.profundidade;
    }
    
    @Override
    public Integer getH(){
        return this.h;
    }
    
    @Override
    public void setH(Integer h){
        this.h = h;
    }

    @Override
    public int compareTo(Object o) {
        IBoard board = (Board) o;
        return this.h.compareTo(board.getH());
    }
    
    
    
}
