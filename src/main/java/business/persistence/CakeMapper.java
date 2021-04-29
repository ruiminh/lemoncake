package business.persistence;

import business.entities.BasketEntry;
import business.entities.Bottom;
import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CakeMapper {
    Database database;

    public CakeMapper(Database database) {
        this.database = database;
    }

    public List<Topping> getAllToppings() throws UserException {
        List<Topping> ToppingList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM topping";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int toppingId = rs.getInt("topping_id");
                    String topping = rs.getString("topping");
                    int price = rs.getInt("price");
                    ToppingList.add(new Topping(toppingId,topping,price));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ToppingList;
    }

    public  List<Bottom> getAllTBottoms() throws UserException {

        List<Bottom> BottomList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM bottom";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int bottom_id = rs.getInt("bottom_id");
                    String name = rs.getString("bottom");
                    int price = rs.getInt("price");
                    BottomList.add(new Bottom(bottom_id,name,price));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return BottomList;
    }
    public void insertOrder(int user_id, int price, List<BasketEntry> cupcakeList)throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO order (user_id,price) VALUES (?,?);";


            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, user_id);
                ps.setInt(2, price);
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int orderId = ids.getInt(1);

                //todo: should insert hobby to link_hobby_bmi
                for (BasketEntry basketEntry : cupcakeList) {
                    insertBasketEntry(orderId,basketEntry);

                }

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

    private void insertBasketEntry(int orderId, BasketEntry basketEntry) throws UserException
    {
        try (Connection connection = database.connect())
        {
            int amount = basketEntry.getAmount();
            int bottomId = basketEntry.getBottom().getBottomId();
            int toppingId = basketEntry.getTopping().getToppingId();
            String sql = "INSERT INTO cupcake_entry (order_id,number,bottom_id,topping_id)  VALUES (?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, orderId);
                ps.setInt(2, amount);
                ps.setInt(3, bottomId);
                ps.setInt(4, toppingId);
                ps.executeUpdate();
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

}
