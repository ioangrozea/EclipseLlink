package edu.msg.ro.service;

import edu.msg.ro.boundary.UserManagement;
import edu.msg.ro.exceptions.BusinessException;
import edu.msg.ro.exceptions.ExceptionCode;
import edu.msg.ro.persistence.user.dao.UserPersistenceManagement;
import edu.msg.ro.persistence.user.entity.User;
import edu.msg.ro.transfer.UserDTO;
import edu.msg.ro.transfer.UserDTOHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Stateless
public class UserManagementBean implements UserManagement {


    private static final int MAX_LAST_NAME_LENGTH = 5;
    private static final int MIN_USERNAME_LENGTH = 6;
    private static final Logger logger = LogManager.getLogger(UserManagementBean.class);

    @EJB
    private UserPersistenceManagement userPersistenceManagement;

    @Override
    public UserDTO createUser(UserDTO userDTO) throws BusinessException {
        logger.log(Level.INFO, "Entered createUser method");
        //TODO trim firstName and lastName

        if (!isUserValidForCreation(userDTO) || !isValidEmail(userDTO.getEmail())) {
            throw new BusinessException(ExceptionCode.USER_VALIDATION_EXCEPTION);
        }

        if (!userPersistenceManagement.getByEmail(userDTO.getEmail()).isEmpty())
            throw new BusinessException(ExceptionCode.USER_VALIDATION_EXCEPTION);

        User user = UserDTOHelper.toEntity(userDTO);
        user.setStatus(true);
        user.setUsername(generateUsername(user.getFirstName(), user.getLastName()));
        userPersistenceManagement.addUser(user);
        return UserDTOHelper.fromEntity(user);
    }


    public String generateUsername(@NotNull final String firstName, @NotNull final String lastName) {
        StringBuilder username = new StringBuilder();
        if (lastName.length() >= MAX_LAST_NAME_LENGTH) {
            username.append(lastName, 0, MAX_LAST_NAME_LENGTH).append(firstName.charAt(0));
        } else if (lastName.length() + firstName.length() >= MIN_USERNAME_LENGTH) {
            username.append(lastName).append(firstName, 0, MIN_USERNAME_LENGTH - firstName.length());
        } else {
            username.append(lastName).append(firstName);
            int usernameLength = username.length();
            for (int i = 0; i < MIN_USERNAME_LENGTH - usernameLength; i++) {
                username.append("0");
            }
        }
        return username.toString().toLowerCase() + createSuffix(username.toString());
    }

    protected String createSuffix(String username) {
        List<String> usernameLike = userPersistenceManagement.getUsersNameLikeUsername(username);
        Optional<Integer> max = usernameLike
                .stream()
                .map(s -> Integer.parseInt(s.substring(6, s.length())))
                .max(Comparator.naturalOrder())
                .map(x -> x + 1);
        if (max.isPresent())
            return max.toString();
        else
            return "";
    }

    private boolean isUserValidForCreation(UserDTO userDTO) {
        return userDTO.getFirstName() != null && userDTO.getLastName() != null &&
                userDTO.getEmail() != null && userDTO.getPassword() != null &&
                userDTO.getPhoneNumber() != null;
    }

    private boolean isValidEmail(String email) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@msggroup.com$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
