package drinkrecipees;

import java.util.ArrayList;
import java.util.Scanner;


/**Ez az oszt�ly a fut�s�rt felel�s egyb�l behozza a funkci�kat, amelyet a  dokument�ci� elej�n m�r ismertettem. Lekezeli, ha nem sz�mot �r be a felhaszn�l�, ha pedig nagyobb sz�mot �r be megk�r, hogy kisebb sz�mot �rjon be, 5. sz�mmal ki lehet l�pni. 
 * Ha kiv�lasztottuk, akkor a program a Programoperations megfelel� f�ggv�ny�t megh�vja, amelyek a Recipebook objektumokon futnak �s v�grehajt�dik a k�rt men�pont. **/
/**Felhaszn�l�i dokument�ci�
 * A f�program fel�let�r�l sz�mokkal lehet v�lasztani 1-5 k�z�tt. 
 * Amennyiben hozz� szeretne adni, akkor v�lassza a 1 sz�mot, ha italt szeretne hozz�adni 2 sz�mot, ha italt t�r�lni a 3 sz�mot, ha italt keresne a 4 sz�mot, amennyiben kil�pne a programb�l, az 5 sz�mot kell v�lasztania. 
 * Az els� pontban meg kell adni a hozz�val� nev�t �s mennyis�g�t (ez egy sz�m lehet).  Csak itallevet lehet hozz�adni, amennyiben ki akarn� eg�sz�teni citrom vagy egy�b gy�m�lcsterm�kkel, akkor annak a lev�t kell hozz�adnia (citroml�, narancsl� stb. ebben az esetben egys�get maga v�laszt, de lehet az 1) is.
 * A kettes pontban el�sz�r az ital nev�t kell megadni (figyeljen a sz�k�z�kre!) majd enter nyom�s�val megjelennek az alapanyagok, amelyek v�laszthat�ak. Ha m�g nincs alapanyag hozz�adva k�rem el�sz�r ezt tegye meg. Ha van akkor sz�mokkal v�laszthat�ak melyik alapanyag sz�ks�ges (0-val kezd�dik az els� alapanyag sz�moz�sa). 
 * Ezeket egyszerre kell bevinni enterrel elv�lasztva. Ha m�gis elrontja, akkor nyomjon entert, majd v�lassza a h�rmas funkci�t �s adja meg az ital nev�t, amelyet t�r�lhet. 
 * Ezut�n �jra visszat�rhet a hozz�ad�shoz �s helyesen megadhatja az adatokat. Amennyiben csak t�r�lni szeretne egy italt, amelyet m�r nem szeretne, �rja be a nev�t, majd megjelenik a tov�bbiakban t�r�lhet� elemek, ez akkor is megjelenik, ha elrontja az ital nev�t. 
 * Amennyiben egy sz�k�zt tett a v�g�re nem fogja t�r�lni, csak ha ugyanazt a sz�k�zt, hozz�teszi �s a n�v teljesen megegyezik.
 * Amennyiben keresni szeretne az a n�gyes sz�mmal �rhet� el. Ezt megnyomva megjelennek, hogy milyen italok vannak a receptt�rban �s tekinthet�ek meg azok alapanyagai. Az �t�s gombbal pedig kil�phet abb�l, majd �jra visszal�phet. 
 * Az elmentett italok �s hozz�val�k nem fognak elveszni. **/
public class Main {
	public static void main(String[] args) {
		String function = ("V�laszthat� funkci�k:\n 1.�j alapanyag hozz�ad�sa" + "\n 2.�j recept hozz�ad�sa \n"
				+ " 3.Recept t�rl�se\n" + " 4.Keres�s a receptek k�z�tt\n" + " 5.Kil�p�s");
				
		System.out.println(function); // v�laszthat� opci�k
		Programoperations.Materiallistadd();
		Programoperations.Drinklistadd();
		int choose = 0;
		String line;
		Scanner which = new Scanner(System.in);
		while (which.hasNextLine() && !(line = which.nextLine()).equals("5")) // lekezeli ha nem sz�mot �r be
		{
			try {		//elkapja ha nem sz�mot adsz neki 
				choose = Integer.parseInt(line);
			} catch (Exception ValueError) { //�rt�k probl�ma
				System.out.print("Sz�mot kell �rnod a k�vetkez�kb�l v�laszthatsz:" + function);
				continue;
			} 
			if (choose == 1) {
				System.out.println("�j alapanyag hozz�ad�sa ");
				Programoperations.addMater(which);
				System.out.println(function); // alapanyag hozz�ad�s
			}
			if (choose == 2) {
				System.out.println("�j recept hozz�ad�sa ");
				Programoperations.addRecipe(which); // hozz�ad receptet
				System.out.println(function);

			}
			if (choose == 3) {
				System.out.println("Recept t�rl�se"); // t�r�l
				Programoperations.removeRecipe(which);
				System.out.println(function);

			}
			if (choose == 4) {
				System.out.println("Keres�s a receptek k�z�tt");
				Programoperations.searchRecipe(which);
				System.out.println(function); // list�z�s
			}
			if (choose == 5) {
				System.out.println("Kil�p�s"); // kil�p a programb�l
				break;
			}
			if (choose > 5) {
				System.out.println("Kisebb sz�mot �rj be, Ilyen funkci� nincs!");		//ha nagyobb sz�mot �rsz ki�rja, hogy kisebb kell
			}
		}

	}
}