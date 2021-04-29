package web.commands;

import business.entities.Basket;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class deleteCupcakeCommand extends CommandUnprotectedPage
{
    public deleteCupcakeCommand(String pageToShow) {
        super(pageToShow);
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String deleteId = request.getParameter("delete");
        HttpSession session = request.getSession();
        Basket basket = (Basket) session.getAttribute("basket");
        basket.removeFromCart(Integer.parseInt(deleteId));
        return pageToShow;
    }
}
