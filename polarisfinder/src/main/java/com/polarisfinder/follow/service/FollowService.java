package com.polarisfinder.follow.service;

import java.util.List;

import com.polarisfinder.follow.entity.Follow;

public interface FollowService {
	boolean createFollow(Follow Follow);
	List<Follow> getFollowById(int id, int paging);
	boolean deleteFollow(Follow Follow);
	

	List<Follow> getFollow(int id, int paging);
	List<Follow> getFollowSent(int id, int paging);
	List<Follow> getFollowStarred(int id, int paging);
	
}
