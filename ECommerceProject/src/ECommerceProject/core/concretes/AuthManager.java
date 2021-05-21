package ECommerceProject.core.concretes;

import ECommerceProject.business.abstracts.UserService;
import ECommerceProject.core.abstracts.AuthService;
import ECommerceProject.core.abstracts.UserValidationService;
import ECommerceProject.core.abstracts.VerificationService;
import ECommerceProject.entities.concretes.User;

public class AuthManager implements AuthService {
	
	UserService userService;
	UserValidationService userValidationService;
	VerificationService verificationService;
	
	public AuthManager() {
		
	}
	
	public AuthManager(UserService userService,VerificationService verificationService,UserValidationService userValidationService) {
		super();
		this.userService = userService;
		this.verificationService=verificationService;
		this.userValidationService=userValidationService;

	}
	
	
	@Override
	public void register(int id, String firstName, String lastName, String email, String password) {
		
		User userToRegister = new User(id, firstName, lastName, email, password);
		if(!this.userValidationService.registerValidate(userToRegister))
		{
			System.out.println("Bilgilerinizi kontrol ediniz.");
		}
		
		if(!checkIfUserExists(email))
		{
			System.out.println("Bu mail adresi ile kay�t mevcut. L�tfen baska bir mail adresi giriniz.");
			return;
		}
		if (!this.verificationService.verificate(userToRegister)) {
			System.out.println("�yelik dogrulama i�lemi ba�ar�s�z, kay�t yap�lamad�.");
			return;
		}

		userService.add(userToRegister);
		
	}

	@Override
	public void login(String email, String password) {
		
		if(!this.userValidationService.loginValidate(email, password))
		{
			System.out.println("Giri� bilgilerinizi kontrol ediniz.");
			return;
		}
		User userToLogin=userService.getByEmailAndPassword(email, password);
		
		if (userToLogin == null) {
			System.out.println("E-posta veya �ifrenizi kontrol ediniz.");
			return;
		}
		if(!checkIfUserVerified(userToLogin)) 
		{
			System.out.println("�yelik dogrulama i�lemi ba�ar�s�z, giri� yap�lamad�.");
			return;
		}
		System.out.println("Giri� ba�ar�l�. " + userToLogin.getFirstName() + " " + userToLogin.getLastName());
		
	}

	private boolean checkIfUserExists(String email)
	{
		return userService.getByMail(email)==null;
	}
	
	private boolean checkIfUserVerified(User user)
	{
		return verificationService.verificate(user);
	}
	
}
