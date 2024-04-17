package tn.enicarthage.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.entity.UserEntity;
import tn.enicarthage.model.User;
import tn.enicarthage.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
    	 List<UserEntity> userEntities = userRepository.findAll();
    	    List<User> users = userEntities
    	            .stream()
    	            .map(userEntity -> {
    	                User user = new User();
    	                user.setId(userEntity.getId());
    	                user.setFirstName(userEntity.getFirstName());
    	                user.setLastName(userEntity.getLastName());
    	                user.setEmailId(userEntity.getEmailId());
    	                user.setDepartement(userEntity.getDepartement());
    	                return user;
    	            })
    	            .collect(Collectors.toList());
    	    return users;
    }
    @Override
    public User getUserById(Long id) {
    	UserEntity userEntity =userRepository.findById(id).get();
    	User user = new User();
    	BeanUtils.copyProperties(userEntity, user);
    			return user;
    }

	@Override
	public boolean deleteUser(Long id) {
		UserEntity user = userRepository.findById(id).get();
		userRepository.delete(user);
		return true;
	}

	@Override
	public User updateUser(Long id, User user) {
		UserEntity userEntity = userRepository.findById(id).get();
		userEntity.setEmailId(user.getEmailId());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setDepartement(user.getDepartement());
		
		
		userRepository.save(userEntity);
		return user;
	}
	
	
	
	
	
}

