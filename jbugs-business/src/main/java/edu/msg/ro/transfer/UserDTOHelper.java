package edu.msg.ro.transfer;

import edu.msg.ro.persistence.user.entity.User;

public class UserDTOHelper {


    public static UserDTO fromEntity(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
}
