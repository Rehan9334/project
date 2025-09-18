package dao;

import model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<AppUser> findByUsername(String username) {
        String sql = "SELECT id, name, password, roles FROM users WHERE name = ?";
        try {
            AppUser user = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{username},
                    new BeanPropertyRowMapper<>(AppUser.class)
            );
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(AppUser user) {
        String sql = "INSERT INTO users (name, password, roles) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getRoles());
    }
}
