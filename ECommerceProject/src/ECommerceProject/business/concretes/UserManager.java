package ECommerceProject.business.concretes;

import java.util.List;

import ECommerceProject.business.abstracts.UserService;
import ECommerceProject.dataAccess.abstracts.UserDao;
import ECommerceProject.entities.concretes.User;

public class UserManager implements UserService {

	UserDao userDao;
	public UserManager(UserDao userDao) {
		this.userDao=userDao;
	}
	
	@Override
	public void add(User user) {
		userDao.add(user);
		System.out.println("Kullanýcý eklendi.");
		
	}
	
	@Override
	public void delete(User user) {
		userDao.delete(user);
		System.out.println("Kullanýcý silindi");
		
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		System.out.println("Kullanýcý güncellendi.");
	}

	
	@Override
	public User getById(int id) {
		
		return userDao.getById(id);
	}

	@Override
	public User getByMail(String email) {
	
		return userDao.getByMail(email);
	}

	@Override
	public User getByEmailAndPassword(String email, String password) {
		
		return userDao.getByEmailAndPassword(email, password);
	}

	@Override
	public List<User> getAll() {
		
		return userDao.getAll();
	}
	
	
	

}
