package drinkrecipees;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**L�trehozok egy Recipebook objektumot az elej�n, hogy az �t tartalmaz� f�ggv�nyeket, hogy megtudjam h�vni rajta.**/
public class Programoperations {
	static Recipebook rec = new Recipebook();
	
	public static void Materiallistadd() {
		rec.addMaterlist();
	}
	public static void Drinklistadd() {
		rec.addDrinkrec();
		try {
			rec.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**A bemenetr�l k�ri a nev�t �s a mennyis�g�t, ha befejezte a bevitelt egy exit sz�t �r�s�val lehet kil�pni. B viszi az adatot a felhaszn�l� n�v �s mennyis�g sorrendben sz�k�zzel elv�lasztva, amennyiben nem ezt teszi, nem fogja hozz�adni �s nem t�rt�nik semmi. Ha mindkett�t le�rja, akkor hozz� fogja adni a Recipebook add f�ggv�ny�vel �s a v�g�n a listMat() f�ggv�nnyel kilist�zza a hozz�adottakat a legv�g�n. **/
	public static void addMater(Scanner newmaterial) {
		System.out.println("K�rem az alapot  �s a mennyis�g�t!\nAmennyiben befejezte a hozz�ad�st �rjon exit-et!");
		while (true) {
			String mate = newmaterial.nextLine();		//bek�ri a nev�t �s sz�m�t sz�k�zzel �s le v�gja �s megt�rdeli
			String[] mat = mate.split(" ");
			if (mat.length == 0 || mat[0].equals("exit")) { // alapanayag hozz�ad�s�hoz f�ggv�ny
				break;
			} else {
				if (mat.length > 1) {		//l�trehozza alapanyagot �s hozz�adja a receptk�nyvh�z
					Material ma = new Material(mat[0], Double.parseDouble(mat[1]));
					rec.add(ma);
				}
			rec.listMat();
			}
		}
	}
	/**Els�k�nt bek�ri az ital nev�t. 
	 * *Leellen�rzi, hogy van-e alapanyag felv�ve, ha nincs, akkor k�ri, hogy vegy�l fel egyet �s felhozza a funkci�kat, amenyiben ide �rja �exit�et ki is lehet l�pni alternat�v�nak, amennyiben nem ezt akarta az ember h�vni ez ut�n ki is �rja, hogy miket lehet helyette. 
	 * *HA van alapanyag, felhozza �ket �s sz�mmal lehet v�lasztani bel�le, egym�s ut�n kell t�bbet �rni teh�t pl. 0,1 sz�m�t akarjuk, akkor a 0  1 sz�mot kell �rni sz�k�z�kkel. 
	 * *Ha a felhaszn�l� nem sz�mot �r vagy nagyobb sz�mot �r, mint amennyi alapanyag van (itt ki van emelve a felhaszn�l�nak, hogy 0-t�l van sz�mozva, de ha nem onnant�l sz�molja nem fog elsz�llni) akkor lekezeli ezeket a hib�kat. 
	 * *Akkor mer�lhet fel probl�ma, ha nem rak sz�k�zt, akkor sem hib�ra fut, csak lehet, hogy rosszul adja hozz�, de ebben az esetben tudja t�r�lni �s �jra hozz�adja, vagy ugyanazt a nevet be�rva j� sz�mokat be�rva hozz�adja �s k�s�bbiekben t�r�lhet�ek a rontott receptek.
	 * *A recipebook addDrink f�ggv�ny�vel adja hozz� ez�rt, ha m�r tartalmazza azt az italt �Ital m�r tartalmazza� sz�veg jelenik meg a felhaszn�l�nak (mennyis�gben �s n�vben is megegyezik). A legv�g�n kilist�zza a drinkslistet �s a Drink string() met�dusa szerint jelennek meg az alapanyagok hozz�. A legv�g�n pedig ki�rja f�jlba az italokat a save() met�dussal. Az�rt gondoltam �gy ezt a f�ggv�nyt, hogy leellen�rizze a hib�kat valamint a felhaszn�l�nak k�nnyebb legyen, �s pontosan le van �rva, hogy mit kell tennie a kimeneten. Ha m�gsem �gy t�rt�nik, akkor se legyen neh�z orvosolni a probl�m�kat. A ki�rat�s az�rt t�rt�nik, hogy ha �jra ind�tjuk a programot, kil�p�s ut�n az eddigiek ne sz�lljanak el. 
	 * *A f�jlkezel�ses hib�t is lekezeli. **/
	public static void addRecipe(Scanner newdrinke)  {
		System.out.println("Milyen italt szeretne? �rja le a nev�t!"); // nev�t bek�ri
		while (true) {
			String wdri = newdrinke.nextLine();
			if (wdri.length() == 0 || wdri.equals("exit")) { // recept hozz�ad�s�hoz f�ggv�ny
				break;
			}
			if (rec.getMateriallist().size() == 0) {
				System.out.println("El�bb vegy�l fel alapanyagot");
				break;
			}
			System.out.println(
					"Milyen alapanyagokb�l �ll az ital?\nSz�mokkal tud v�lasztani bel�l�k (Figyelem!!0 az els� �s �gy tov�bb, K�rem ne �rjon magasabb  sz�mot, mint ah�ny alapanyag van!)\nAz el�rhet� alapnyagok: ");
			rec.listMat();		//kilist�zza alapanyagot
			String drin = newdrinke.nextLine();		//bek�ri ital nev�t
			String[] wanted = drin.split(" ");
			ArrayList<Material> materialo = new ArrayList<Material>();
			try {
				for (int i = 0; i < wanted.length; i++) {
					int materialsnumber = Integer.parseInt(wanted[i]); // lista indexel kell v�lasztani, hogy melyiket
																		// szeretn�

					materialo.add(rec.getMateriallist().get(materialsnumber));
				} /**
					 * kikell v�lasztani a list�b�l index szerint melyik alapanyagot kell hozz�adni
					 * �s azt be�rni sz�k�z�kkel
					 **/
			} catch (Exception ValueError) {
				System.out.print(
						"Kisebb sz�mot vagy sz�mot �rj be, nincs anyag ehhez a sz�mhoz vagy bet�t �rt�l be �s �rd be a nev�t �jra\n Ha kiakarsz l�pni nyomj entert");
				continue;
			}

			Drink d = new Drink(wdri, materialo); // �j ital j�n l�tre belerakom receptk�nyyvbe kiprintelem addig
													// csin�lja m�g nem nyomok entert legv�g�n

			rec.addDrink(d);
			System.out.println(d);

		}
		rec.listDri();
		try {
			rec.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	/**Itt megk�rdezi, hogy melyik receptet szeretn�nk t�r�lni �s a neve bevitel�vel t�r�lhet�ek ezek. Bet�lti az eddig hozz�adott recepteket, hogy az el�z�eket is lehessen t�r�lni. 
	 * Ha megegyezik a neve az italoknak, akkor a sorban az els�t fogja t�r�lni. 
	 * A receptk�nyv t�rl�si f�ggv�ny�vel megh�vva  t�rli �s ki�rja, hogy ital t�r�lve, ha tudja t�r�lni, ha nem, akkor nincs mit t�r�lnie �s egyb�l a recept list�t hozza be, amelyek t�r�lhet�ek. Ha a sz�k�z a v�g�n beesik, akkor csak �gy fogja t�r�lni, ha azt is m�g� �rja a felhaszn�l�, ha nem, akkor nem csin�l semmit.  Err�l t�j�koztatva van a felhaszn�l� is a kimeneten. Ha nincs mit t�r�lni, ki�rja, hogy �Nincsenek italok�. El is menti a v�g�n �s ki�rja f�jlba a t�rl�seket. 
	 * A f�jlkezel�ses hib�t lekezeli. **/
	public static void removeRecipe(Scanner newdrinke) {
		System.out.println(
				"Melyik receptet szeretn� t�r�lni? �rja le a nev�t a bevitellel megegyez�en!\nAmennyiben nem t�rli, lehet a v�g�re rakott egy plusz entert ezt is �rja hozz�!");
		try {
			rec = Recipebook.load();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} //beolvassa a recepteket
		while (true) {
			rec.listDri();		//kilist�zza a ital list�t
			String wantdrink = newdrinke.nextLine();		//bek�ri melyiket akarja t�r�lni
			if (wantdrink.length() == 0 || wantdrink.equals("exit")) { // recept t�rl�s�hez
				break;
			}
			if (rec.getDrinkslist().size() == 0) {		//megn�zi, hogy �res-e az ital, ha igen azt adja vissza, hogy nincsenek italok
				System.out.println("Nincsenek italok");
				break;
			}
			rec.removeDrink(wantdrink);
			try {
				rec.save();
			} catch (IOException e) {
				e.printStackTrace();
			}}}
			
	/**Ebben a f�ggv�nyben ki�rja, hogy milyen italnevek vannak, melyik receptet lehet megn�zni �s ennek nev�t be�rva a bemenetbe fogja megjelen�teni annak az italnak a hozz�val�it.
	 * Az�rt gondoltam, hogy leh�vom milyen italnevek vannak, hogy a felhaszn�l� tudja, hogy mib�l lehet v�lasztani �s tudja melyik nev� receptre lehet k�v�ncsi. Ha nincsenek italok, akkor ki�rja �s megk�r, hogy vegy�l fel �s hol tudsz. 
	 * Ez a keres�s a Recipebook objektum searcherDrink f�ggv�ny�vel fut(kikeresi a drinkslistb�l a megfelel� italt �s majd visszaadja azt). Bet�lti itt is a f�jlb�l milyen italok vannak jelenleg, ezt lekezeli, ha nincs ilyen f�jl. **/
	public static void searchRecipe(Scanner searching)  {
		System.out.println("Melyik ital receptj�t szeretn�d megtal�lni? �rd le a nev�t! Kil�p�shez nyomj egy entert");
		try {
			rec = Recipebook.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//beolvassa a recepteket amiket hozz�adtak m�r
		System.out.println("Az el�rhet� receptek:\n"); //ki�rja  az �sszes el�rhet�receptet
		for (int i = 0; i < rec.getDrinkslist().size(); i++) {
			if (rec.getDrinkslist().get(i) != null) {
				System.out.println(rec.getDrinkslist().get(i).getName());
			}
		}
		while (true) {		//megadja mit akar keresni ha 0 vagy exit kil�p ha �res a lista megk�r vegy�lfel
			String wantdrinks = searching.nextLine();
			if (wantdrinks.length() == 0 || wantdrinks.equals("exit")) { // recept keres�shez
				break;
			}
			if (rec.getDrinkslist().size() == 0) {
				System.out.println("Nincsenek italok, Vegy�l fel �jakat a 2 sz�mmal");
				break;
			}
			rec.searcherDrink(wantdrinks);		//ki�rja a keresett italt hozz�val�kkal
		}
	}
}
