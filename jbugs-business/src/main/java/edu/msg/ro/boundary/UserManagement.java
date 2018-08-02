package edu.msg.ro.boundary;

import edu.msg.ro.exceptions.BusinessException;
import edu.msg.ro.transfer.UserDTO;

public interface UserManagement {
    /**
     * method is used for persisting DTO from an userDTO
     * it generates the username
     * does the validations
     * @param userDTO user information
     * @return the newly created userDTO
     */
    UserDTO createUser(UserDTO userDTO) throws BusinessException;
}
