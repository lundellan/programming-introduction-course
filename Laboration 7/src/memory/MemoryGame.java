package memory;

public class MemoryGame {
	public static void main(String[] args) {
		String[] frontFileNames = {"can.jpg", "flopsy_mopsy_cottontail.jpg",
				"friends.jpg", "mother_ladybird.jpg", "mr_mcgregor.jpg",
				"mrs_rabbit.jpg", "mrs_tittlemouse.jpg", "radishes.jpg" };
		
		String backFileName = "back.jpg";
		
		// Fyll i egen kod här
		
		// Skapar ett memoryBoard ()
		
		MemoryBoard memoryBoard = new MemoryBoard(4, backFileName, frontFileNames);
		MemoryWindow window = new MemoryWindow(memoryBoard);
		window.drawBoard();
		
		int clicks = 0;
		
		while (!memoryBoard.hasWon())	{
			
			int counter = 0;
			int[] mouseRows = new int[2];
			int[] mouseCols = new int[2];
			
			while (counter < 2)	{
				window.waitForMouseClick();
				
				mouseRows[counter] = window.getMouseRow();
				mouseCols[counter] = window.getMouseCol();
				
				if (!memoryBoard.frontUp(mouseRows[counter], mouseCols[counter]))	{
					memoryBoard.turnCard(mouseRows[counter], mouseCols[counter]);
					window.drawCard(mouseRows[counter], mouseCols[counter]);
					
					counter++;
				}
			}
			
			if (!memoryBoard.same(mouseRows[0], mouseCols[0], mouseRows[1], mouseCols[1]))	{
				window.delay(1000);
				
				for (int i = 0; i < 2; i++)	{
					memoryBoard.turnCard(mouseRows[i], mouseCols[i]);
					window.drawCard(mouseRows[i], mouseCols[i]);
				}
			}
		
			clicks++;
			
		}
		
		System.out.print(clicks);
		
		
		
	}
}
