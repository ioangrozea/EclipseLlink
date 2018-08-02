package edu.msg.ro.service;

import edu.msg.ro.boundary.UserManagement;
import edu.msg.ro.exceptions.BusinessException;
import edu.msg.ro.exceptions.ExceptionCode;
import edu.msg.ro.persistence.user.dao.UserPersistanceManagement;
import edu.msg.ro.persistence.user.entity.User;
import edu.msg.ro.transfer.UserDTO;
import edu.msg.ro.transfer.UserDTOHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class UserManagementBean implements UserManagement {


    private static final int MAX_LAST_NAME_LENGTH = 5;
    private static final int MIN_USERNAME_LENGTH = 6;
    private static Logger logger = LogManager.getLogger(UserManagementBean.class);

    @EJB
    private UserPersistanceManagement userPersistanceManagement;

    @Override
    public UserDTO createUser(UserDTO userDTO) throws BusinessException {
        logger.log(Level.INFO, "Entered createUser method");
        //TODO trim firstName and lastName

        if (!isUserValidForCreation(userDTO) || isValidEmail(userDTO.getEmail())){
            throw new BusinessException(ExceptionCode.USER_VALIDATION_EXCEPTION);
        }
        User user = UserDTOHelper.toEntity(userDTO);
        userPersistanceManagement.addUser(user);
        return UserDTOHelper.fromEntity(user);
    }


    public String generateUsername(@NotNull final String firstName, @NotNull final String lastName) {
        StringBuilder username = new StringBuilder();
        if (lastName.length() >= MAX_LAST_NAME_LENGTH) {
            username.append(lastName.substring(0, MAX_LAST_NAME_LENGTH) + firstName.charAt(0));
        } else if (lastName.length() + firstName.length() >= MIN_USERNAME_LENGTH) {
            username.append(lastName + firstName.substring(0, MIN_USERNAME_LENGTH - firstName.length()));
        } else {
            username.append(lastName + firstName);
            int usernameLength = username.length();
            for (int i = 0; i < MIN_USERNAME_LENGTH - usernameLength; i++) {
                username.append("0");
            }
        }
        return username.toString().toLowerCase();
    }

    private boolean isUserValidForCreation(UserDTO userDTO){
        return userDTO.getFirstName() != null && userDTO.getLastName() != null &&
                userDTO.getEmail() != null && userDTO.getPassword() != null &&
                userDTO.getPhoneNumber() != null;
    }

    private boolean isValidEmail(String email){
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@msggroup.com$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
}
