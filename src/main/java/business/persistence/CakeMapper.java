package business.persistence;

import business.entities.Bottom;
import business.entities.Topping;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
