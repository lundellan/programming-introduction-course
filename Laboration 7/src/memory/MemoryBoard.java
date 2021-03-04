package memory;

import java.awt.Image;
import java.util.Random;

public class MemoryBoard {

	private MemoryCardImage[] cardsImages;

	private boolean[][] cardMatrix;
	private MemoryCardImage[][] cardsImagesPlacement;

	
	/** Skapar ett memorybräde med size * size kort. backFileName är filnamnet 
	    för filen med baksidesbilden. Vektorn frontFileNames innehåller filnamnen 
	    för frontbilderna. */
	public MemoryBoard(int size, String backFileName, String[] frontFileNames) {
		
		cardMatrix = new boolean[size][size];
		
		createCards(backFileName, frontFileNames);
	}

	/* Skapar size * size / 2 st memorykortbilder.
	   Placerar ut varje kort på två slumpmässiga ställen på spelplanen. */
	private void createCards(String backFileName, String[] frontFileNames) {
		int amountOfCards = (cardMatrix.length * cardMatrix.length) / 2;
		
		cardsImages = new MemoryCardImage[amountOfCards];
		
		for (int i = 0; i < amountOfCards; i++)	{
			cardsImages[i] = new MemoryCardImage(frontFileNames[i], backFileName);
		}
		
		
		cardsImagesPlacement = new MemoryCardImage[cardMatrix.length][cardMatrix.length];
		
		for (int i = 0; i < amountOfCards; i++)	{
			
			Random random = new Random();
			
			int counter = 0;
			
			while (counter < 2)	{
				int r = random.nextInt(cardMatrix.length);
				int c = random.nextInt(cardMatrix.length);
				
				if  (cardsImagesPlacement[r][c] == null)	{
					cardsImagesPlacement[r][c] = cardsImages[i];
					counter++;
				}	
			}
			
		}
	}

	/** Tar reda på brädets storlek. */
	public int getSize() {
		return cardMatrix.length;
	}
	
	/** Hämtar den tvåsidiga bilden av kortet på rad r, kolonn c.
	    Raderna och kolonnerna numreras från 0 och uppåt. */
	public MemoryCardImage getCard(int r, int c) {
		return cardsImagesPlacement[r][c];
	}

	/** Vänder kortet på rad r, kolonn c. */
	public void turnCard(int r, int c) {
		if (!cardMatrix[r][c])	{
			cardMatrix[r][c] = true;
		} else {
			cardMatrix[r][c] = false;
		}
	}
	
	/** Returnerar true om kortet r, c har framsidan upp. */
	public boolean frontUp(int r, int c) {
		return cardMatrix[r][c];
	}
	
	/** Returnerar true om det är samma kort på rad r1, kolonn c2 som på rad r2, 
	    kolonn c2. */
	public boolean same(int r1, int c1, int r2, int c2) {
		if (cardsImagesPlacement[r1][c1] == cardsImagesPlacement[r2][c2])	{
			return true;
		} else {
			return false;
		}
	}

	/** Returnerar true om alla kort har framsidan upp. */
	public boolean hasWon() {
		/* En loop som söker i boolean matrisen efter false värde */
		
		for (int r = 0; r < cardMatrix.length; r++)	{
			for (int c = 0; c < cardMatrix[r].length; c++)	{
				if (!cardMatrix[r][c])	{
					return false;
				}
			}
		
		}
		
		return true;
	}	
}
