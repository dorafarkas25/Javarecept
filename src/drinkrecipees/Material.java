package drinkrecipees;

import java.io.Serializable;

/**Mivel az italok alapanyagokb�l �llnak, ez�rt alapanyagok(Material) az els� oszt�lyom, ahol  van egy hashCode() �s egy equals(), amely egy gener�lt f�ggv�ny  n�lam egy k�s�bbi  oszt�ly contains-�hez ugyanis sz�ks�ges hozz�. 
*Ennek az ooszt�lynak konstruktor�ban van neve �s mennyis�ge, ez�rt van egy getName() �s egy getQuantity() met�dusa, hogy azokat visszaadja meg egy String() met�dusa is, ahol form�zva ki�rja majd, hogy az alapanyag neve �s annak a mennyis�ge ml-ben ha hozz�adjuk az egyes funkci�ban.
*�gy gondoltam, hogy az italokhoz csak el�g t�nyleges italok, ez�rt a �ml� �s felesleges hozz�adni tov�bb� olyanokat, mint egy citrom, mert ugyan�gy citromlevet is lehet (ak�r kifacsarva a citromb�l). **/

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

	public double getQuantity() { //visszaadja a mennyis�get
		return quantity;
	}

	public String toString() {
		return getName() + " " + getQuantity() + " ml";
	}
}
