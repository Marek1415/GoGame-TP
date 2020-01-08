package database;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/*****************************
 * moves
 * INT game_id
 * INT move
 * INT position
 * VARCHAR(1) player
 *****************************/

@Entity
@Table(name="moves")
public class Move implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3797773694033560493L;
	
	@Id
	@Column(name="move")
    private int move;
    
    @Column(name="player")
    private char player;
	
    @Column(name="position")
    private int position;

    //fetch = FetchType.LAZY,
    @ManyToOne(targetEntity = Game.class)
    @JoinColumn(name = "game_id")
    private Game game;
    	
    public Move() {
		super();
	}

	public Move(Game game, int move, char player, int position) {
		super();
		this.game = game;
		this.move = move;
		this.player = player;
		this.position = position;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public char getPlayer() {
		return player;
	}

	public void setPlayer(char player) {
		this.player = player;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
