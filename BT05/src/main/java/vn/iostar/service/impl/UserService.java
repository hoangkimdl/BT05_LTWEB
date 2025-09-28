package vn.iostar.service.impl;

import java.util.List;
import vn.iostar.dao.IUserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.entity.User;
import vn.iostar.service.IUserService;

public class UserService implements IUserService{

	IUserDao userDao = new UserDaoImpl();

	@Override
	public void delete(int id) {
		userDao.delete(id);
		
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
		
	}

	@Override
	public User findById(int id) {
		
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}
	
	@Override
	public List<User> findByUsername(String keyword) {
	    return userDao.findByUsername(keyword);
	}
	
	@Override
	public List<User> findAll(int page, int size) {
	    return userDao.findAll(page, size);
	}

	@Override
	public int count() {
	    return userDao.count();
	}
}
