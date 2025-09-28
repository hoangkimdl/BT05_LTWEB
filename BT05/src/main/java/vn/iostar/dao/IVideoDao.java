package vn.iostar.dao;

import java.util.List;

import vn.iostar.entity.Video;

public interface IVideoDao {

	void delete(String id);

	void update(Video video);

	void insert(Video video);

	Video findById(String id);

	List<Video> findAll();
	
	List<Video> findByTitle(String keyword);

}
