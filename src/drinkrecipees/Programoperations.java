package drinkrecipees;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**Létrehozok egy Recipebook objektumot az elején, hogy az õt tartalmazó függvényeket, hogy megtudjam hívni rajta.**/
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
	
	/**A bemenetrõl kéri a nevét és a mennyiségét, ha befejezte a bevitelt egy exit szót írásával lehet kilépni. B viszi az adatot a felhasználó név és mennyiség sorrendben szóközzel elválasztva, amennyiben nem ezt teszi, nem fogja hozzáadni és nem történik semmi. Ha mindkettõt leírja, akkor hozzá fogja adni a Recipebook add függvényével és a végén a listMat() függvénnyel kilistázza a hozzáadottakat a legvégén. **/
	public static void addMater(Scanner newmaterial) {
		System.out.println("Kérem az alapot  és a mennyiségét!\nAmennyiben befejezte a hozzáadást írjon exit-et!");
		while (true) {
			String mate = newmaterial.nextLine();		//bekéri a nevét és számát szóközzel és le vágja és megtördeli
			String[] mat = mate.split(" ");
			if (mat.length == 0 || mat[0].equals("exit")) { // alapanayag hozzáadásához függvény
				break;
			} else {
				if (mat.length > 1) {		//létrehozza alapanyagot és hozzáadja a receptkönyvhöz
					Material ma = new Material(mat[0], Double.parseDouble(mat[1]));
					rec.add(ma);
				}
			rec.listMat();
			}
		}
	}
	/**Elsõként bekéri az ital nevét. 
	 * *Leellenõrzi, hogy van-e alapanyag felvéve, ha nincs, akkor kéri, hogy vegyél fel egyet és felhozza a funkciókat, amenyiben ide írja „exit”et ki is lehet lépni alternatívának, amennyiben nem ezt akarta az ember hívni ez után ki is írja, hogy miket lehet helyette. 
	 * *HA van alapanyag, felhozza õket és számmal lehet választani belõle, egymás után kell többet írni tehát pl. 0,1 számút akarjuk, akkor a 0  1 számot kell írni szóközökkel. 
	 * *Ha a felhasználó nem számot ír vagy nagyobb számot ír, mint amennyi alapanyag van (itt ki van emelve a felhasználónak, hogy 0-tól van számozva, de ha nem onnantól számolja nem fog elszállni) akkor lekezeli ezeket a hibákat. 
	 * *Akkor merülhet fel probléma, ha nem rak szóközt, akkor sem hibára fut, csak lehet, hogy rosszul adja hozzá, de ebben az esetben tudja törölni és újra hozzáadja, vagy ugyanazt a nevet beírva jó számokat beírva hozzáadja és késõbbiekben törölhetõek a rontott receptek.
	 * *A recipebook addDrink függvényével adja hozzá ezért, ha már tartalmazza azt az italt „Ital már tartalmazza” szöveg jelenik meg a felhasználónak (mennyiségben és névben is megegyezik). A legvégén kilistázza a drinkslistet és a Drink string() metódusa szerint jelennek meg az alapanyagok hozzá. A legvégén pedig kiírja fájlba az italokat a save() metódussal. Azért gondoltam így ezt a függvényt, hogy leellenõrizze a hibákat valamint a felhasználónak könnyebb legyen, és pontosan le van írva, hogy mit kell tennie a kimeneten. Ha mégsem így történik, akkor se legyen nehéz orvosolni a problémákat. A kiíratás azért történik, hogy ha újra indítjuk a programot, kilépés után az eddigiek ne szálljanak el. 
	 * *A fájlkezeléses hibát is lekezeli. **/
	public static void addRecipe(Scanner newdrinke)  {
		System.out.println("Milyen italt szeretne? Írja le a nevét!"); // nevét bekéri
		while (true) {
			String wdri = newdrinke.nextLine();
			if (wdri.length() == 0 || wdri.equals("exit")) { // recept hozzáadásához függvény
				break;
			}
			if (rec.getMateriallist().size() == 0) {
				System.out.println("Elõbb vegyél fel alapanyagot");
				break;
			}
			System.out.println(
					"Milyen alapanyagokból áll az ital?\nSzámokkal tud választani belõlük (Figyelem!!0 az elsõ és így tovább, Kérem ne írjon magasabb  számot, mint ahány alapanyag van!)\nAz elérhetõ alapnyagok: ");
			rec.listMat();		//kilistázza alapanyagot
			String drin = newdrinke.nextLine();		//bekéri ital nevét
			String[] wanted = drin.split(" ");
			ArrayList<Material> materialo = new ArrayList<Material>();
			try {
				for (int i = 0; i < wanted.length; i++) {
					int materialsnumber = Integer.parseInt(wanted[i]); // lista indexel kell választani, hogy melyiket
																		// szeretné

					materialo.add(rec.getMateriallist().get(materialsnumber));
				} /**
					 * kikell választani a listából index szerint melyik alapanyagot kell hozzáadni
					 * és azt beírni szóközökkel
					 **/
			} catch (Exception ValueError) {
				System.out.print(
						"Kisebb számot vagy számot írj be, nincs anyag ehhez a számhoz vagy betût írtál be és írd be a nevét újra\n Ha kiakarsz lépni nyomj entert");
				continue;
			}

			Drink d = new Drink(wdri, materialo); // új ital jön létre belerakom receptkönyyvbe kiprintelem addig
													// csinálja míg nem nyomok entert legvégén

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
	/**Itt megkérdezi, hogy melyik receptet szeretnénk törölni és a neve bevitelével törölhetõek ezek. Betölti az eddig hozzáadott recepteket, hogy az elõzõeket is lehessen törölni. 
	 * Ha megegyezik a neve az italoknak, akkor a sorban az elsõt fogja törölni. 
	 * A receptkönyv törlési függvényével meghívva  törli és kiírja, hogy ital törölve, ha tudja törölni, ha nem, akkor nincs mit törölnie és egybõl a recept listát hozza be, amelyek törölhetõek. Ha a szóköz a végén beesik, akkor csak úgy fogja törölni, ha azt is mögé írja a felhasználó, ha nem, akkor nem csinál semmit.  Errõl tájékoztatva van a felhasználó is a kimeneten. Ha nincs mit törölni, kiírja, hogy „Nincsenek italok”. El is menti a végén és kiírja fájlba a törléseket. 
	 * A fájlkezeléses hibát lekezeli. **/
	public static void removeRecipe(Scanner newdrinke) {
		System.out.println(
				"Melyik receptet szeretné törölni? Írja le a nevét a bevitellel megegyezõen!\nAmennyiben nem törli, lehet a végére rakott egy plusz entert ezt is írja hozzá!");
		try {
			rec = Recipebook.load();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} //beolvassa a recepteket
		while (true) {
			rec.listDri();		//kilistázza a ital listát
			String wantdrink = newdrinke.nextLine();		//bekéri melyiket akarja törölni
			if (wantdrink.length() == 0 || wantdrink.equals("exit")) { // recept törléséhez
				break;
			}
			if (rec.getDrinkslist().size() == 0) {		//megnézi, hogy üres-e az ital, ha igen azt adja vissza, hogy nincsenek italok
				System.out.println("Nincsenek italok");
				break;
			}
			rec.removeDrink(wantdrink);
			try {
				rec.save();
			} catch (IOException e) {
				e.printStackTrace();
			}}}
			
	/**Ebben a függvényben kiírja, hogy milyen italnevek vannak, melyik receptet lehet megnézni és ennek nevét beírva a bemenetbe fogja megjeleníteni annak az italnak a hozzávalóit.
	 * Azért gondoltam, hogy lehívom milyen italnevek vannak, hogy a felhasználó tudja, hogy mibõl lehet választani és tudja melyik nevû receptre lehet kíváncsi. Ha nincsenek italok, akkor kiírja és megkér, hogy vegyél fel és hol tudsz. 
	 * Ez a keresés a Recipebook objektum searcherDrink függvényével fut(kikeresi a drinkslistbõl a megfelelõ italt és majd visszaadja azt). Betölti itt is a fájlból milyen italok vannak jelenleg, ezt lekezeli, ha nincs ilyen fájl. **/
	public static void searchRecipe(Scanner searching)  {
		System.out.println("Melyik ital receptjét szeretnéd megtalálni? Írd le a nevét! Kilépéshez nyomj egy entert");
		try {
			rec = Recipebook.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//beolvassa a recepteket amiket hozzáadtak már
		System.out.println("Az elérhetõ receptek:\n"); //kiírja  az összes elérhetõreceptet
		for (int i = 0; i < rec.getDrinkslist().size(); i++) {
			if (rec.getDrinkslist().get(i) != null) {
				System.out.println(rec.getDrinkslist().get(i).getName());
			}
		}
		while (true) {		//megadja mit akar keresni ha 0 vagy exit kilép ha üres a lista megkér vegyélfel
			String wantdrinks = searching.nextLine();
			if (wantdrinks.length() == 0 || wantdrinks.equals("exit")) { // recept kereséshez
				break;
			}
			if (rec.getDrinkslist().size() == 0) {
				System.out.println("Nincsenek italok, Vegyél fel újakat a 2 számmal");
				break;
			}
			rec.searcherDrink(wantdrinks);		//kiírja a keresett italt hozzávalókkal
		}
	}
}
