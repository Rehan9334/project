package service;

import dao.UserDao;
import model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public Optional<AppUser> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public int save(AppUser user) {
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(appUser.getName())
                .password(appUser.getPassword())
                .roles(appUser.getRoles().replace("ROLE_", ""))
                .build();
    }
}
