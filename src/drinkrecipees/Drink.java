package drinkrecipees;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Az�rt v�lasztottam az italokat, mert ital recepteket kell k�sz�teni. Ez
 * tartalmazza az italokat, amelyeknek nev�k van valami hozz�val�ik, amelyek
 * arraylistek, hogy mikor kettes funkci�n�l l�trehozza az italokat, hozz�
 * lehessen adni t�bb k�l�nb�z� alapanyagot is. Ennek van hashcode() �s
 * equals()-a egy k�s�bbi contains f�ggv�nyhez ugyan�gy, valamint van getName()
 * getMateriallist()-je, hogy vissszaadja �ket. Van egy String() met�dusa, hogy
 * a 2 funkci� v�g�n ki�rja az italt �s hozz�val�it, amelyek hozz�vannak adva
 * m�r miut�n hozz�adtuk.
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

	public Drink(String n, ArrayList<Material> d) {		//n�v �s alapanyaok list�j�b�l �ll
		this.name = n;
		this.materiallist = d;
	}

	public String getName() {	//visszaadja a nevet
		return name;
	}

	public ArrayList<Material> getMaterialList() {	//visszaadja az alapayagok list�j�t
		return materiallist;
	}

	public String toString() {
		return name + "\n " + "Hozz�val�k: " + materiallist;
	}
}
