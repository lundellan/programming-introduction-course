package life;

public class LifeBoard {

	/** Skapar en spelplan med rows rader och cols kolonner.
	    Spelplanen är från början tom, dvs alla rutorna är
	    tomma och generationsräknaren är 1. */	
	public LifeBoard(int rows, int cols) {
	}

	/** Undersöker om det finns en individ i rutan med index row,col, 
	    Om index row,col hamnar utanför spelplanen returneras false. */
	public boolean get(int row, int col) {
		return false;
	}

	/** Lagrar värdet val i rutan med index row,col. */
	public void put(int row, int col, boolean val) {
	}

	/** Tar reda på antalet rader. */
	public int getRows() {
		return 0;
	}

 	/** Tar reda på antalet kolonner. */
	public int getCols() {
		return 0;
	}

	/** Tar reda på aktuellt generationsnummer. */
	public int getGeneration() {
		return 0;
	}

	/** Ökar generationsnumret med ett. */
	public void increaseGeneration() {
	}
}
