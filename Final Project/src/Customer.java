
public class Customer {
	private String name;
	private long idNr;
	private int kundnummer;
	private static int absolutKundnummer = 101;
	
	/**
	 * Skapar en kund (kontoinnehavare) med namnet ’name’ och id-nummer ’idNr’.
	 * Kunden tilldelas också ett unikt kundnummer.
	 */
	public Customer(String name, long idNr) {
		this.name = name;
		this.idNr = idNr;
		kundnummer = absolutKundnummer++;
	}

	/** Tar reda på kundens namn. */
	public String getName() {
		return name;
	}

	/** Tar reda på kundens personnummer. */
	public long getIdNr() {
		return idNr;
	}

	/** Tar reda på kundens kundnummer. */
	public int getCustomerNr() {
		return kundnummer;
	}

	/** Returnerar en strängbeskrivning av kunden. */
	public String toString() {
		return name + ", " + "id: " + idNr + ", " + "kundnummer: " + kundnummer;  
	}

}
