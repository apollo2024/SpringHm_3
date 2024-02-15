package Repository;

import Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> getUsers(){
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i)->{
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };
        return jdbc.query(sql, userRowMapper);
    }
    public User save(User user){
        String sql = "INSERT INTO users (userAge, userName, email) VALUES (?,?,?)";
        jdbc.update(sql,user.getAge(), user.getName(),user.getEmail());
        return user;
    }
}
