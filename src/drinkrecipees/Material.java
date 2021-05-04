package drinkrecipees;

import java.io.Serializable;

/**Mivel az italok alapanyagokból állnak, ezért alapanyagok(Material) az elsõ osztályom, ahol  van egy hashCode() és egy equals(), amely egy generált függvény  nálam egy késõbbi  osztály contains-éhez ugyanis szükséges hozzá. 
*Ennek az oosztálynak konstruktorában van neve és mennyisége, ezért van egy getName() és egy getQuantity() metódusa, hogy azokat visszaadja meg egy String() metódusa is, ahol formázva kiírja majd, hogy az alapanyag neve és annak a mennyisége ml-ben ha hozzáadjuk az egyes funkcióban.
*Úgy gondoltam, hogy az italokhoz csak elég tényleges italok, ezért a „ml” és felesleges hozzáadni továbbá olyanokat, mint egy citrom, mert ugyanúgy citromlevet is lehet (akár kifacsarva a citromból). **/

public class Material implements Serializable {
	private String name;
	private double quantity;
	
	public Material(String name, double qua) {
		this.name = name;
		this.quantity = qua;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		return true;
	}

	public String getName() {  //visszaadja a nevet
		return name;
	}

	public double getQuantity() { //visszaadja a mennyiséget
		return quantity;
	}

	public String toString() {
		return getName() + " " + getQuantity() + " ml";
	}
}
