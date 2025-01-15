package com.example.ProductionManagementSystem.Service.User;

import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.Role;
import com.example.ProductionManagementSystem.Model.User.User;
import com.example.ProductionManagementSystem.Exception.ErrorResponse;
import com.example.ProductionManagementSystem.Repo.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CurrentService {

    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(CurrentService.class);

    public User getCurrentUser() throws ServiceException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserEmail;

        if (principal instanceof UserDetails) {
            currentUserEmail = ((UserDetails) principal).getUsername();
        } else {
            throw new ServiceException(ErrorResponse.UNAUTHORIZED);
        }

        logger.info("Current user email: {}", currentUserEmail);

        return userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_CUSTOMER));
    }

    public void checkUserRole(User currentUser, Role requiredRole) throws ServiceException {
        if (!currentUser.getRole().equals(requiredRole)) {
            logger.error("Access denied for user: {}", currentUser.getEmail());
            throw new ServiceException(ErrorResponse.CANNOT_ACCESS);
        }
    }

//    public void checkIfUserIsAdmin(User currentUser) throws ServiceException {
//        if (!currentUser.getRole().equals(Role.ADMIN)) {
//            logger.error("Access denied for user: {}", currentUser.getEmail());
//            throw new ServiceException(ErrorResponse.CANNOT_ACCESS);
//        }
//    }
//
//    public void checkIfUserIsManager(User currentUser) throws ServiceException {
//        if (!currentUser.getRole().equals(Role.MANAGER)) {
//            logger.error("Access denied for user: {}", currentUser.getEmail());
//            throw new ServiceException(ErrorResponse.CANNOT_ACCESS);
//        }
//    }
//
//    public void checkIfUserIsUser(User currentUser) throws ServiceException {
//        if (!currentUser.getRole().equals(Role.USER)) {
//            logger.error("Access denied for user: {}", currentUser.getEmail());
//            throw new ServiceException(ErrorResponse.CANNOT_ACCESS);
//        }
//    }
}
