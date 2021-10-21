package com.stackroute.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public boolean saveUser(User user) throws UserNotFoundException, UserAlreadyExistsException {

		Optional<User> users = userRepo.findById(user.getUserId());
		if (users.isPresent()) {
			throw new UserAlreadyExistsException("User already exists");
		}
		userRepo.save(user);
		return true;
	}

	@Override
		public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		
			User user=userRepo.findByUserIdAndPassword(userId, password);
			
			if(user==null)
			{
				throw new UserNotFoundException("Oops !!! User doesn't exist");
			}
			return user;
		}
}
