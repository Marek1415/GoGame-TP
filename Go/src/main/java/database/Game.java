package database;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*****************************
 * games
 * INT id
 * INT size
 * DATE date
 *****************************/

@Entity
@Table(name="games")
public class Game {
	
	@Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    
	//TODO change to timestamp
    @Column(name="date")
    private Date date;
    
    @Column(name="size")
    private int size;
    
	@OneToMany(mappedBy = "game", orphanRemoval = true)
    private List<Move> moves;
	
    public Game(int size, Date date) {
		super();
		this.size = size;
		this.date = date;
	}

	public Game() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
    public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	
	public String getIdStr() {
		return Integer.toString(id);
	}
	
	public String getSizeStr() {
		return Integer.toString(size);
	}
	
	public String getDateStr() {
		return date.toString();
	}
}
