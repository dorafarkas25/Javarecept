package drinkrecipees;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Azért választottam az italokat, mert ital recepteket kell készíteni. Ez
 * tartalmazza az italokat, amelyeknek nevük van valami hozzávalóik, amelyek
 * arraylistek, hogy mikor kettes funkciónál létrehozza az italokat, hozzá
 * lehessen adni több különbözõ alapanyagot is. Ennek van hashcode() és
 * equals()-a egy késõbbi contains függvényhez ugyanúgy, valamint van getName()
 * getMateriallist()-je, hogy vissszaadja õket. Van egy String() metódusa, hogy
 * a 2 funkció végén kiírja az italt és hozzávalóit, amelyek hozzávannak adva
 * már miután hozzáadtuk.
 **/

public class Drink implements Serializable {
	private String name;
	private ArrayList<Material> materiallist;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((materiallist == null) ? 0 : materiallist.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Drink other = (Drink) obj;
		if (materiallist == null) {
			if (other.materiallist != null)
				return false;
		} else if (!materiallist.equals(other.materiallist))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Drink(String n, ArrayList<Material> d) {		//név és alapanyaok listájából áll
		this.name = n;
		this.materiallist = d;
	}

	public String getName() {	//visszaadja a nevet
		return name;
	}

	public ArrayList<Material> getMaterialList() {	//visszaadja az alapayagok listáját
		return materiallist;
	}

	public String toString() {
		return name + "\n " + "Hozzávalók: " + materiallist;
	}
}
