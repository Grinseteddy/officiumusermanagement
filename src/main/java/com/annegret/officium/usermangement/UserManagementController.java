package com.annegret.officium.usermangement;

import com.annegret.officium.usermangement.Entities.Message;
import com.annegret.officium.usermangement.Entities.UserEntity;
import com.annegret.officium.usermangement.Entities.UserRepository;
import com.annegret.officium.usermangement.Entities.UserResponse;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserManagementController {

    private final UserRepository userRepository;

    UserManagementController(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @GetMapping("/users/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        List<UserEntity> allUsers=userRepository.findAll();
        return allUsers;
    }

    @PostMapping("/users")
    public UserResponse addNewUser(@RequestBody UserEntity userEntity) throws ResponseStatusException {
        try {
            UserResponse exists=checkIfExists(userEntity);
            if (exists == null) {
                return exists;
            }
            UserEntity user = new UserEntity(userEntity.getName(), userEntity.getSurname(),
                    userEntity.getUsername(), userEntity.getEmailAddress(), userEntity.getPassword(),
                    userEntity.getDefaultPicture());
            userRepository.save(user);
            Message message=new Message("USERCREATED", Message.severity.SUCCESS, "User created", UUID.randomUUID().toString());
            return new UserResponse(user, message, null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User couldn't be created.");
        }
    }

    @GetMapping("user/{userid}")
    public UserResponse getUserById(@PathVariable String userid){
        try {
            UserEntity user = userRepository.findUserEntityById(userid);
            Message message= new Message("USERFOUND", Message.severity.SUCCESS, "User found", UUID.randomUUID().toString());
            return new UserResponse(user, null, null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User couldn't be found");
        }
    }

    @PutMapping("user/{userid}")
    public UserResponse updateUserById(@PathVariable String userid, @RequestBody UserEntity userEntity) {
        try {

            if (userEntity.getId()==null) {
                Message message=new Message("USERIDMISSING", Message.severity.ERROR, "User ID for update necessary",
                        UUID.randomUUID().toString());
                return new UserResponse(null, message, null);
            }
            UserResponse exists=checkIfExists(userEntity);
            if (exists == null) {
                return exists;
            }
            UserEntity userToBeUpdated=userRepository.findUserEntityById(userid);

            userToBeUpdated.setName(userEntity.getName());
            userToBeUpdated.setSurname(userEntity.getSurname());
            userToBeUpdated.setPassword(userEntity.getPassword());
            userToBeUpdated.setDefaultPicture(userEntity.getDefaultPicture());

            userRepository.save(userToBeUpdated);
            Message message=new Message("USERPDATED", Message.severity.SUCCESS, "User updated",
                    UUID.randomUUID().toString());
            return new UserResponse(userEntity, message, null);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    public UserResponse checkIfExists(UserEntity userEntity) throws  ResponseStatusException {
        //Check on nullable
        if (userEntity.getUsername() == null || userEntity.getEmailAddress() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User name and email address cannot be null");
        }
        //Check if user name already exists
        UserEntity alreadyExists=userRepository.findUserEntityByUsername(userEntity.getUsername());
        if (alreadyExists!=null) {
            Message message= new Message("USEREXITS", Message.severity.ERROR,
                    "User with user name " + userEntity.getUsername() +" already exists",
                    UUID.randomUUID().toString());
            return new UserResponse(alreadyExists, message, null);
        }
        //Check on email address exists
        alreadyExists=userRepository.findUserEntityByEmailAddress(userEntity.getEmailAddress());
        if (alreadyExists!=null) {
            Message message= new Message("EMAILEXISTS", Message.severity.ERROR, "User with email address "+ userEntity.getEmailAddress()
                    + " already exists", UUID.randomUUID().toString());
            return new UserResponse(userEntity, message, null);
        }
        return null;
    }
}
