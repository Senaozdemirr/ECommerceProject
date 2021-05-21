package ECommerceProject.core.concretes;

import java.util.Random;
import java.util.Scanner;
import ECommerceProject.core.abstracts.VerificationService;
import ECommerceProject.entities.concretes.User;

public class EmailVerificationManager implements VerificationService {
	
	public boolean verificate(User user) {
		Scanner scanner = new Scanner(System.in);
		char chosenValue = 'h';
		System.out.println(user.getEmail()
				+ " adresine bir dogrulama kodu gönderildi. Hesabýnýzý onaylamak istiyor musunuz? (E/H)");
		chosenValue = scanner.next().charAt(0);

		if (chosenValue == 'e' || chosenValue == 'E') {
			Random random = new Random();
			int randomInteger, enteredNumber, count = 0;

			do {
				randomInteger = random.nextInt(999999 - 100000 + 1) + 100000;

				System.out.println(
						"Hesabýnýzý dogrulamak  için mailinize gelen 6 haneli þifreyi giriniz:\n--> " + randomInteger);
				enteredNumber = scanner.nextInt();
				count++;
				if (count == 4) {
					System.out.println(
							"Çok sayýda hatalý giriþ yaptýnýz, hesap doðrulanamadý. \n Lütfen daha sonra tekrar deneyiniz..");
					scanner.close();
					return false;
				}
			} while (!(enteredNumber == randomInteger) && count < 4);
			System.out.println("Hesap dogrulandý. ");
			
			scanner.close();
			return true;

		}
		scanner.close();
		return false;
		
	}

}
