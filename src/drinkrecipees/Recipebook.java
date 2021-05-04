package drinkrecipees;

import java.io.*;
import java.util.ArrayList;

/**Els�k�nt l�trehoztam egy Arraylistet(tov�bbiakban �s drinkslist) materiallist a k�t el�z� oszt�lynak, amely �res, hogy bele lehessen rakni az alapanyagokat meg az italokat.  Ezek mind a Programoperaionsh�z kellenek ezen az objektumon vannak megh�vva a f�ggv�nyek.
 * *Ez az oszt�ly tartalmazza a materialokat �s a drinkslisteket �s azzal kapcsolatos m�veleteket is. pl. hozz�ad�s, t�rl�s, keres�shez sz�ks�ges f�ggv�nyek. **/

public class Recipebook implements Serializable {
	
	ArrayList<Drink> drinkslist = new ArrayList<Drink>();
	ArrayList<Material> materiallist = new ArrayList<Material>(); // list�ja az alapanyagoknak

	Material m1=new Material("cola", 150);
	Material m2=new Material("rum", 4);
	Drinkreceipt d1=new Drinkreceipt("Rumcola", materiallist);
	
	public void addMaterlist() {
	materiallist.add(m1);
	materiallist.add(m2);} //hozz�ad k�t alapanyagot (cola,rum)
	/**Ez a f�ggv�ny megn�zi, hogy tartalmazza-e(contains) m�r a materiallist azt  az alapanyagot, amelyet bemenetr�l olvasunk majd be . **/
	public void add(Material material) { // �j italalapanyag hozz�ad�sa 1.pont
		if (this.materiallist.contains(material)) {
			System.out.println("Ezt az alapanyagot m�r tartalmazza");
		} else {
			materiallist.add(material);		//hozz�adja az alapanyagot ha nincs m�gg benne
			System.out.println("Alapanyag hozz�adva.");
		}
	}

	/**Visszaadja a materiallist list�j�t.**/
	public ArrayList<Material> getMateriallist() {	//alapanyaglista visszad�sa
		return materiallist;
	}
	
	/**Ez ki�rja az eg�sz materiallistet a legv�g�n, hogy l�that� legyen miket raktunk bele �s milyen alapanyagokat tartalmaz alkalmazva az egyes funkci� legv�g�n. El�z� f�ggv�nyen megy v�gig �s visszaadja a hozz�adottakat. Els� funkci�hoz sz�ks�ges**/
	public void listMat() { // kiprinteli az alapanyagot

		for (int i = 0; i < materiallist.size(); i++) {
			if (materiallist.get(i) != null) {
				System.out.println(materiallist.get(i));
			}
		}
	}
	
	public void addDrinkrec() {
		drinkslist.add(d1); //hozz�adja a rumcol�t
	}
/**Hozz�adja a drinkslist-hez az italokat, amennyiben m�g nem tartalmazza,
 *amelyeket igen azt m�r nem tudj�k hozz�adni, kettes funkci�hoz sz�ks�ges **/
	public void addDrink(Drink drink) {
		if (drinkslist.contains(drink)) {		//megn�zi tartalmazza-e az italt ha nem hozz�adja
			System.out.println("Ital M�r tartalmazza");
		} else {
			drinkslist.add(drink);
			System.out.println("Ital Hozz�adva.");
		}
	}
	/**Ez ki�rja az eg�sz drinkslistet a legv�g�n �s az �sszes hozz�adott drinket.**/
	public void listDri() { // kiprinteli a recepteket

		for (int i = 0; i < drinkslist.size(); i++) {	//ha nem �res akkor ki�rja az itallist�t
			if (drinkslist.get(i) != null) {
				System.out.println(drinkslist.get(i));
			}
		}
	}
	/**Visszaadja a drinkslist list�j�t. **/
	public ArrayList<Drink> getDrinkslist() {	//visszaadja ital list�t
		return drinkslist;
	}
	/**Ital t�rl�s�re alkalmas f�ggv�ny.
	 * *Megn�zi, hogy tartalmazza-e a drinkslist az italt �s ha igen akkor t�rli azt. **/
	public void removeDrink(String drinks) {
		for (int i = 0; i < drinkslist.size(); i++) {
			if (drinkslist.get(i).getName().equals(drinks)) { // megn�zi, hogy a list�ban van-e a bek�rt
				drinkslist.remove(i);
				System.out.println("Ital t�r�lve");
			}
		}
	}
	/**Ha a keres�sben bevitt ital megtal�lhat� a drinkslistben, akkor behozza azt az italt. **/
	public void searcherDrink(String drink) {
		for (int i = 0; i < drinkslist.size(); i++) {		//megn�zi ugyanaz szerepel-e az italban �s vissza adja azt (amit �rt felhaszn�l�)
			if (drinkslist.get(i).getName().equals(drink)) {
				System.out.println(drinkslist.get(i));
			}
		}
	}
	/**F�jlkezel�sre alkalmas f�ggv�nyek ki�rat�sra �s beolvas�sra.**/
	public void save() throws IOException {
		FileOutputStream writer = new FileOutputStream("italok.txt");		//ki�r
		ObjectOutputStream oos = new ObjectOutputStream(writer);
		oos.writeObject(this);
		oos.close();
	}

	public static Recipebook load() throws IOException, ClassNotFoundException {		//beolvas
		FileInputStream fis = new FileInputStream("italok.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Recipebook rec = (Recipebook) ois.readObject();
		ois.close();
		return rec;
	}

}
