package business.persistence;

import business.exceptions.UserException;
import business.entities.User;

import java.sql.*;

public class UserMapper
{
    private Database database;

    public UserMapper(Database database)
    {
        this.database = database;
    }

    public void createUser(User user) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public User login(String email, String password) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT id, role,saldo FROM users WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String role = rs.getString("role");
                    int balance =rs.getInt("saldo");
                    int id = rs.getInt("id");
                    User user = new User(email, password, role,balance);
                    user.setId(id);
                    return user;
                } else
                {
                    throw new UserException("Could not validate user");
                }
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException("Connection to database could not be established");
        }
    }

    public int updateUserBalance(int id,int newBalance) throws UserException
    {
        try (Connection connection = database.connect()) {

            String sql = "UPDATE users SET saldo = ? WHERE id=? ";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1,newBalance);
                ps.setInt(2,id);
                int rowsUpdated = ps.executeUpdate();
                return rowsUpdated;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
