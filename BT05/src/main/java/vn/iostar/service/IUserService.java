package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.User;

public interface IUserService {
	void delete(int id);

	void update(User user);

	void insert(User user);

	User findById(int id);

	List<User> findAll();
	List<User> findByUsername(String keyword);
	List<User> findAll(int page, int size);
	int count();
}
