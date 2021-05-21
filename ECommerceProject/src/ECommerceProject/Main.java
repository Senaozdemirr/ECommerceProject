package ECommerceProject;

import ECommerceProject.business.abstracts.UserService;
import ECommerceProject.business.concretes.UserManager;
import ECommerceProject.core.abstracts.AuthService;
import ECommerceProject.core.concretes.AuthManager;
import ECommerceProject.core.concretes.EmailVerificationManager;
import ECommerceProject.core.concretes.UserValidationManager;
import ECommerceProject.dataAccess.concretes.HibernateUserDao;

public class Main {

	public static void main(String[] args) {
		
		UserService userService = new UserManager(new HibernateUserDao());
		
		AuthService authService = new AuthManager(userService, new EmailVerificationManager(), new UserValidationManager());
		authService.register(1, "Sena", "Özdemir", "mail@mail.com", "123456"); 
		authService.login("mail@mail.com", "12345");
		
	}

}
