package drinkrecipees;

import java.io.*;
import java.util.ArrayList;

/**Elsõként létrehoztam egy Arraylistet(továbbiakban és drinkslist) materiallist a két elõzõ osztálynak, amely üres, hogy bele lehessen rakni az alapanyagokat meg az italokat.  Ezek mind a Programoperaionshöz kellenek ezen az objektumon vannak meghívva a függvények.
 * *Ez az osztály tartalmazza a materialokat és a drinkslisteket és azzal kapcsolatos mûveleteket is. pl. hozzáadás, törlés, kereséshez szükséges függvények. **/

public class Recipebook implements Serializable {
	
	ArrayList<Drink> drinkslist = new ArrayList<Drink>();
	ArrayList<Material> materiallist = new ArrayList<Material>(); // listája az alapanyagoknak

	Material m1=new Material("cola", 150);
	Material m2=new Material("rum", 4);
	Drinkreceipt d1=new Drinkreceipt("Rumcola", materiallist);
	
	public void addMaterlist() {
	materiallist.add(m1);
	materiallist.add(m2);} //hozzáad két alapanyagot (cola,rum)
	/**Ez a függvény megnézi, hogy tartalmazza-e(contains) már a materiallist azt  az alapanyagot, amelyet bemenetrõl olvasunk majd be . **/
	public void add(Material material) { // új italalapanyag hozzáadása 1.pont
		if (this.materiallist.contains(material)) {
			System.out.println("Ezt az alapanyagot már tartalmazza");
		} else {
			materiallist.add(material);		//hozzáadja az alapanyagot ha nincs mégg benne
			System.out.println("Alapanyag hozzáadva.");
		}
	}

	/**Visszaadja a materiallist listáját.**/
	public ArrayList<Material> getMateriallist() {	//alapanyaglista visszadása
		return materiallist;
	}
	
	/**Ez kiírja az egész materiallistet a legvégén, hogy látható legyen miket raktunk bele és milyen alapanyagokat tartalmaz alkalmazva az egyes funkció legvégén. Elõzõ függvényen megy végig és visszaadja a hozzáadottakat. Elsõ funkcióhoz szükséges**/
	public void listMat() { // kiprinteli az alapanyagot

		for (int i = 0; i < materiallist.size(); i++) {
			if (materiallist.get(i) != null) {
				System.out.println(materiallist.get(i));
			}
		}
	}
	
	public void addDrinkrec() {
		drinkslist.add(d1); //hozzáadja a rumcolát
	}
/**Hozzáadja a drinkslist-hez az italokat, amennyiben még nem tartalmazza,
 *amelyeket igen azt már nem tudják hozzáadni, kettes funkcióhoz szükséges **/
	public void addDrink(Drink drink) {
		if (drinkslist.contains(drink)) {		//megnézi tartalmazza-e az italt ha nem hozzáadja
			System.out.println("Ital Már tartalmazza");
		} else {
			drinkslist.add(drink);
			System.out.println("Ital Hozzáadva.");
		}
	}
	/**Ez kiírja az egész drinkslistet a legvégén és az összes hozzáadott drinket.**/
	public void listDri() { // kiprinteli a recepteket

		for (int i = 0; i < drinkslist.size(); i++) {	//ha nem üres akkor kiírja az itallistát
			if (drinkslist.get(i) != null) {
				System.out.println(drinkslist.get(i));
			}
		}
	}
	/**Visszaadja a drinkslist listáját. **/
	public ArrayList<Drink> getDrinkslist() {	//visszaadja ital listát
		return drinkslist;
	}
	/**Ital törlésére alkalmas függvény.
	 * *Megnézi, hogy tartalmazza-e a drinkslist az italt és ha igen akkor törli azt. **/
	public void removeDrink(String drinks) {
		for (int i = 0; i < drinkslist.size(); i++) {
			if (drinkslist.get(i).getName().equals(drinks)) { // megnézi, hogy a listában van-e a bekért
				drinkslist.remove(i);
				System.out.println("Ital törölve");
			}
		}
	}
	/**Ha a keresésben bevitt ital megtalálható a drinkslistben, akkor behozza azt az italt. **/
	public void searcherDrink(String drink) {
		for (int i = 0; i < drinkslist.size(); i++) {		//megnézi ugyanaz szerepel-e az italban és vissza adja azt (amit írt felhasználó)
			if (drinkslist.get(i).getName().equals(drink)) {
				System.out.println(drinkslist.get(i));
			}
		}
	}
	/**Fájlkezelésre alkalmas függvények kiíratásra és beolvasásra.**/
	public void save() throws IOException {
		FileOutputStream writer = new FileOutputStream("italok.txt");		//kiír
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
