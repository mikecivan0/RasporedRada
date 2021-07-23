package raspored;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Alati {

	public static Scanner scanner;
	public static final String FORMAT_DATUMA = "dd.MM.yyyy.";
	public static final String FORMAT_VREMENA = "HH:mm";

	private static Date ucitajDatum(String poruka) {

		SimpleDateFormat formatDatuma = new SimpleDateFormat(FORMAT_DATUMA);

		while (true) {
			System.out.println(poruka);
			try {
				formatDatuma.parse(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Molim unesite datum u formatu " + formatDatuma.format(new Date()));
			}
		}

	}

	private static Date ucitajVrijeme(String poruka) {

		SimpleDateFormat formatVremena = new SimpleDateFormat(FORMAT_VREMENA);

		while (true) {
			System.out.println(poruka);
			try {
				formatVremena.parse(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Molim unesite vrijeme u formatu " + formatVremena.format(new Date()));
			}
		}

	}

	public static int ucitajBroj(String poruka, String porukaGreske, int min, int max) {

		int broj = 0;

		while (true) {
			System.out.println(poruka);
			try {
				broj = Integer.parseInt(scanner.nextLine());

				if (broj < min || broj > max) {
					System.out.println("Unesite broj između " + min + " i " + max);
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println(porukaGreske);
			}
		}

		return broj;
	}

	public static String ucitajString(String poruka, String porukaGreske, int minLength, int maxLength) {

		String unos = "";

		while (true) {
			System.out.println(poruka);

			unos = scanner.nextLine();
			if (unos.length() > minLength || unos.length() > maxLength) {
				System.out.println("Unos ne smije sadržavati manje od " + minLength + " znakova, niti više od "
						+ maxLength + " znakova");
				continue;
			}
			break;
		}

		return unos;
	}
	
	public static boolean daNe(String poruka, String porukaGreske) {
		String unos;
		
		while(true) {
			System.out.println(poruka);
			
			unos = scanner.nextLine().trim().toLowerCase();
			if(unos.equals("da")) {
				return true;
			}
			if(unos.equals("ne")) {
				return false;
			}
			System.out.println(porukaGreske);
		}
	}
}
