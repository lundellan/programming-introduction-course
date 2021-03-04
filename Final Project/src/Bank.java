import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> banklist;

	/** Skapar en ny bank utan konton. */
	public Bank() {
		banklist = new ArrayList<BankAccount>();
	}

	/**
	 * Öppna ett nytt konto i banken. Om det redan finns en kontoinnehavare med de
	 * givna uppgifterna ska inte en ny Customer skapas, utan istället den
	 * befintliga användas. Det nya kontonumret returneras.
	 */
	public int addAccount(String holderName, long idNr) {		
		for (int i = 0; i < banklist.size(); i++) {
			if (idNr == banklist.get(i).getHolder().getIdNr()) {
				banklist.add(new BankAccount(banklist.get(i).getHolder()));
				return banklist.get(banklist.size() - 1).getAccountNumber();
			}
		}
		banklist.add(new BankAccount(holderName, idNr));
		return banklist.get(banklist.size() - 1).getAccountNumber();
	}

	/**
	 * Returnerar den kontoinnehavaren som har det givna id-numret, eller null om
	 * ingen sådan finns.
	 */
	public Customer findHolder(long idNr) {
		for (int i = 0; i < banklist.size(); i++)	{
			if (banklist.get(i).getHolder().getIdNr() == idNr)	{
				return banklist.get(i).getHolder();
			}
		}
		return null;
	}

	/**
	 * Tar bort konto med nummer ’number’ från banken. Returnerar true om kontot
	 * fanns (och kunde tas bort), annars false.
	 */
	public boolean removeAccount(int number) {
		for (int i = 0; i < banklist.size(); i++)	{
			if (banklist.get(i).getAccountNumber() == number)	{
				banklist.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returnerar en lista innehållande samtliga bankkonton i banken. Listan är
	 * sorterad på kontoinnehavarnas namn.
	 */
	public ArrayList<BankAccount> getAllAccounts() {
		
		/* ArrayList<BankAccount> sortedBankList = new ArrayList<BankAccount>();
		
		for (int i = 0; i < banklist.size(); i++)	{
			sortedBankList.add(banklist.get(i));
		}
		
		for (int i = 0; i < banklist.size(); i++)	{
			
			String min = sortedBankList.get(i).getHolder().getName();
			int minIndex = -1;
			
			for (int k = i; k < banklist.size(); i++)	{
				if (min.compareTo(banklist.get(i).getHolder().getName()) > 0)	{
					min = banklist.get(i).getHolder().getName().charAt(0);
					minIndex = k;
				}
			}
			
			sortedBankList.add(banklist.get(i));
			
		}
			
		return sortedBankList; */
		
		ArrayList<BankAccount> allList = new ArrayList<BankAccount>();
		for (BankAccount b : banklist) {
			allList.add(b);
		}
		for (int i = 0; i < allList.size() - 1; i++) {
			int index = i;
           	String min = allList.get(i).getHolder().getName();
           	for (int k = i + 1; k < allList.size(); k++) {
           		if (min.compareTo(allList.get(k).getHolder().getName()) > 0) {
           			index = k;
           			min = allList.get(k).getHolder().getName();
           		}
           	}
           	BankAccount temp = allList.get(index);
           	allList.set(index, allList.get(i));
           	allList.set(i, temp);
		}
		return allList;
	}
	

	/**
	 * Söker upp och returnerar bankkontot med kontonummer ’accountNumber’.
	 * Returnerar null om inget sådant konto finns.
	 */
	public BankAccount findByNumber(int accountNumber) {
		for (int i = 0; i < banklist.size(); i++)	{
			if (banklist.get(i).getAccountNumber() == accountNumber)	{
				return banklist.get(i);
			}
		}
		return null;
	}

	/**
	 * Söker upp alla bankkonton som innehas av kunden med id-nummer ’idNr’. Kontona
	 * returneras i en lista. Kunderna antas ha unika id-nummer.
	 */
	public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> accountsForHolder = new ArrayList<BankAccount>();
		
		for (int i = 0; i < banklist.size(); i++)	{
			if (banklist.get(i).getHolder().getIdNr() == idNr)	{
				accountsForHolder.add(banklist.get(i));
			}
		}
		return accountsForHolder;
	}

	/**
	 * Söker upp kunder utifrån en sökning på namn eller del av namn. Alla personer
	 * vars namn innehåller strängen ’namePart’ inkluderas i resultatet, som
	 * returneras som en lista. Samma person kan förekomma flera gånger i
	 * resultatet. Sökningen är "case insensitive", det vill säga gör ingen skillnad
	 * på stora och små bokstäver.
	 */
	public ArrayList<Customer> findByPartofName(String namePart) {
		String name = namePart.toLowerCase();
		ArrayList<Customer> partOfNameList = new ArrayList<Customer>();
		
		for (int i = 0; i < banklist.size(); i++)	{
			if (banklist.get(i).getHolder().getName().toLowerCase().contains(name))	{
				partOfNameList.add(banklist.get(i).getHolder());
			}
		}
		
		return partOfNameList;
	}

}
