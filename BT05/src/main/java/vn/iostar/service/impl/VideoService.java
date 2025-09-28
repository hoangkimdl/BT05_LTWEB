package vn.iostar.service.impl;
import vn.iostar.entity.Video;
import vn.iostar.service.IVideoService;

import java.util.List;

import vn.iostar.dao.IVideoDao;
import vn.iostar.dao.impl.VideoDaoImpl;
public class VideoService implements IVideoService {
	IVideoDao videoDao = new VideoDaoImpl();

	@Override
	public void delete(String id) {
		videoDao.delete(id);
		
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
		
	}

	@Override
	public void insert(Video video) {
		videoDao.insert(video);
		
	}

	@Override
	public Video findById(String id) {
		
		return videoDao.findById(id);
	}

	@Override
	public List<Video> findAll() {
		
		return videoDao.findAll();
	}
	
	@Override
	public List<Video> findByTitle(String keyword) {
	    return videoDao.findByTitle(keyword);
	}
	
}
