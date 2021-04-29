package web.commands;

import business.entities.Basket;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.CakeMapper;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckOutCommand extends CommandProtectedPage
{
    public CheckOutCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        UserFacade userFacade = new UserFacade(database);
        CakeMapper cakeMapper = new CakeMapper(database);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int id = user.getId();
        Basket basket =(Basket)session.getAttribute("basket");
        int newBalance =user.getBalance()-basket.totalPrice();
        userFacade.updateUserBalance(id,newBalance);
        cakeMapper.insertOrder(id,basket.totalPrice(),basket.getCupcakeList());
        return pageToShow;
    }
}
