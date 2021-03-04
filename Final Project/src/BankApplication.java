import java.util.ArrayList;
import java.util.Scanner;

public class BankApplication {
	public static void main(String[] args) {
		meny();
	}

	public static void meny() {
		Scanner scan = new Scanner(System.in);
		Bank bank = new Bank();
		
		bank.addAccount("Anna B", 1234);
		bank.addAccount("Anna B", 5678);
		bank.addAccount("Anna B", 1234);
		bank.addAccount("Jesper Z", 1313);
		bank.addAccount("Anders A", 1212);
		
		while (true)	{
			System.out.println("");
			System.out.println("--Meny--");
			System.out.println("1. Hitta konto utifrån innehavare");
			System.out.println("2. Sök kontoinnehavare utifrån (del av) namn");
			System.out.println("3. Sätt in");
			System.out.println("4. Ta ut");
			System.out.println("5. Överföring");
			System.out.println("6. Skapa konto");
			System.out.println("7. Ta bort konto");
			System.out.println("8. Skriv ut konton");
			System.out.println("9. Avsluta");
			System.out.print("Ditt val: ");
			int choice = scan.nextInt();
			scan.nextLine();
			System.out.println("");
			switch (choice) {
			case 1: // hitta konto utifrån innehavare
				System.out.println("--Hitta konto utifrån innehavare--");
				System.out.print("Ange ditt id: ");
				long id = scan.nextLong();
				
				ArrayList<BankAccount> idList = bank.findAccountsForHolder(id);
				
				if (idList.isEmpty())	{
					System.out.println("Personen med id-numret har inga bankkonton");
				} else {
					for (int i = 0; i < idList.size(); i++)	{
						System.out.println(idList.get(i));
					}
				}
				break;
			case 2: // sök kontoinnehavares namn
				System.out.println("--Sök kontoinnehavare utifrån (del av) namn--");
				System.out.print("Ange (del av) namn: ");
				String name = scan.nextLine();
				
				ArrayList<Customer> customerList = bank.findByPartofName(name);
				
				if (customerList.isEmpty()) {
                    System.out.println("Finns inga konton.");
                    break;
                } else {
                    for (int i = 0; i < customerList.size(); i++) {
                        System.out.println(customerList.get(i));
                    }
                    break;
                }
			case 3: // sätt in pengar
				System.out.println("--Sätt in pengar--");
				System.out.print("Kontonummer: ");
				int konto = scan.nextInt();
				System.out.print("Belopp: ");
				double belopp = scan.nextDouble();
				if (bank.findByNumber(konto) == null)	{
					System.out.println("Kontot finns inte");
					break;
				} if (belopp < 0)	{
					System.out.println("Negativa belopp inte tillåtna");
					break;
				} else	{
					bank.findByNumber(konto).deposit(belopp); 
					System.out.println(bank.findByNumber(konto));
					break;
				}
			case 4: // ta ut pengar
				System.out.println("--Ta ut pengar--");
				System.out.print("Kontonummer: ");
				int konto1 = scan.nextInt();
				System.out.print("Belopp: ");
				double belopp1 = scan.nextDouble();
				if (bank.findByNumber(konto1) == null)	{
					System.out.println("Kontot finns inte");
					break;
				} if (bank.findByNumber(konto1).getAmount() < belopp1)	{
					System.out.println("Kontot har för lågt saldo (" + (bank.findByNumber(konto1).getAmount() + "kr)"));
					break;
				} else	{
					bank.findByNumber(konto1).withdraw(belopp1); 
					System.out.println(bank.findByNumber(konto1));
					break;
				}
			case 5: // överföring
				System.out.println("--Överför pengar--");
				System.out.print("Från kontonummer: ");
				int kontoFrom = scan.nextInt();
				System.out.print("Till kontonummer: ");
				int kontoTill = scan.nextInt();
				System.out.print("Belopp: ");
				double beloppOverforing = scan.nextDouble();
				
				if (bank.findByNumber(kontoFrom) == null || bank.findByNumber(kontoTill) == null)	{
					System.out.println("Något av kontona finns inte");
					break;
				} if (bank.findByNumber(kontoFrom).getAmount() < beloppOverforing)	{
					System.out.println("Kontot har för lågt saldo (" + (bank.findByNumber(kontoFrom).getAmount() + "kr)"));
					break;
				} else	{
					bank.findByNumber(kontoFrom).withdraw(beloppOverforing); 
					bank.findByNumber(kontoTill).deposit(beloppOverforing); 
					System.out.println(bank.findByNumber(kontoFrom));
					System.out.println(bank.findByNumber(kontoTill));
					break;
				}
			case 6: // skapar konto
				System.out.println("--Skapa nytt konto--");
				System.out.print("Ange ditt namn: ");
				String namn = scan.nextLine();
				System.out.print("Ange ditt ID nummer: ");
				long idNr = scan.nextLong();
				int kontonr = bank.addAccount(namn, idNr);
				System.out.println("Kontonummer: " + kontonr);
				break;
			case 7: // tar bort konto
				System.out.println("--Ta bort konto--");
				System.out.print("Ange ditt kontonummer: ");
				int removeKonto = scan.nextInt();
				
				if (bank.removeAccount(removeKonto))	{
					System.out.println("Konto borttaget: " + removeKonto);
					break;
				} else	{
					System.out.println("Kontot finns inte");
					break;
				}
			case 8: // skriv ut alla konton
				System.out.println("--Alla konton--");
				for (int i = 0; i < bank.getAllAccounts().size(); i++){
			    	System.out.println(bank.getAllAccounts().get(i));
			    }
				break;
			case 9:
				System.exit(0);
				break;
			default:
				System.out.println(choice + " är inte ett menyalternativ");
				break; 
			}
		}
		
	}

}
