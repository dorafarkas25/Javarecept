package drinkrecipees;

import java.util.ArrayList;
import java.util.Scanner;


/**Ez az osztály a futásért felelõs egybõl behozza a funkciókat, amelyet a  dokumentáció elején már ismertettem. Lekezeli, ha nem számot ír be a felhasználó, ha pedig nagyobb számot ír be megkér, hogy kisebb számot írjon be, 5. számmal ki lehet lépni. 
 * Ha kiválasztottuk, akkor a program a Programoperations megfelelõ függvényét meghívja, amelyek a Recipebook objektumokon futnak és végrehajtódik a kért menüpont. **/
/**Felhasználói dokumentáció
 * A fõprogram felületérõl számokkal lehet választani 1-5 között. 
 * Amennyiben hozzá szeretne adni, akkor válassza a 1 számot, ha italt szeretne hozzáadni 2 számot, ha italt törölni a 3 számot, ha italt keresne a 4 számot, amennyiben kilépne a programból, az 5 számot kell választania. 
 * Az elsõ pontban meg kell adni a hozzávaló nevét és mennyiségét (ez egy szám lehet).  Csak itallevet lehet hozzáadni, amennyiben ki akarná egészíteni citrom vagy egyéb gyümölcstermékkel, akkor annak a levét kell hozzáadnia (citromlé, narancslé stb. ebben az esetben egységet maga választ, de lehet az 1) is.
 * A kettes pontban elõször az ital nevét kell megadni (figyeljen a szóközökre!) majd enter nyomásával megjelennek az alapanyagok, amelyek választhatóak. Ha még nincs alapanyag hozzáadva kérem elõször ezt tegye meg. Ha van akkor számokkal választhatóak melyik alapanyag szükséges (0-val kezdõdik az elsõ alapanyag számozása). 
 * Ezeket egyszerre kell bevinni enterrel elválasztva. Ha mégis elrontja, akkor nyomjon entert, majd válassza a hármas funkciót és adja meg az ital nevét, amelyet törölhet. 
 * Ezután újra visszatérhet a hozzáadáshoz és helyesen megadhatja az adatokat. Amennyiben csak törölni szeretne egy italt, amelyet már nem szeretne, írja be a nevét, majd megjelenik a továbbiakban törölhetõ elemek, ez akkor is megjelenik, ha elrontja az ital nevét. 
 * Amennyiben egy szóközt tett a végére nem fogja törölni, csak ha ugyanazt a szóközt, hozzáteszi és a név teljesen megegyezik.
 * Amennyiben keresni szeretne az a négyes számmal érhetõ el. Ezt megnyomva megjelennek, hogy milyen italok vannak a recepttárban és tekinthetõek meg azok alapanyagai. Az ötös gombbal pedig kiléphet abból, majd újra visszaléphet. 
 * Az elmentett italok és hozzávalók nem fognak elveszni. **/
public class Main {
	public static void main(String[] args) {
		String function = ("Választható funkciók:\n 1.Új alapanyag hozzáadása" + "\n 2.Új recept hozzáadása \n"
				+ " 3.Recept törlése\n" + " 4.Keresés a receptek között\n" + " 5.Kilépés");
				
		System.out.println(function); // választható opciók
		Programoperations.Materiallistadd();
		Programoperations.Drinklistadd();
		int choose = 0;
		String line;
		Scanner which = new Scanner(System.in);
		while (which.hasNextLine() && !(line = which.nextLine()).equals("5")) // lekezeli ha nem számot ír be
		{
			try {		//elkapja ha nem számot adsz neki 
				choose = Integer.parseInt(line);
			} catch (Exception ValueError) { //érték probléma
				System.out.print("Számot kell írnod a következõkbõl választhatsz:" + function);
				continue;
			} 
			if (choose == 1) {
				System.out.println("Új alapanyag hozzáadása ");
				Programoperations.addMater(which);
				System.out.println(function); // alapanyag hozzáadás
			}
			if (choose == 2) {
				System.out.println("Új recept hozzáadása ");
				Programoperations.addRecipe(which); // hozzáad receptet
				System.out.println(function);

			}
			if (choose == 3) {
				System.out.println("Recept törlése"); // töröl
				Programoperations.removeRecipe(which);
				System.out.println(function);

			}
			if (choose == 4) {
				System.out.println("Keresés a receptek között");
				Programoperations.searchRecipe(which);
				System.out.println(function); // listázás
			}
			if (choose == 5) {
				System.out.println("Kilépés"); // kilép a programból
				break;
			}
			if (choose > 5) {
				System.out.println("Kisebb számot írj be, Ilyen funkció nincs!");		//ha nagyobb számot írsz kiírja, hogy kisebb kell
			}
		}

	}
}