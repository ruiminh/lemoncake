package web.commands;

import business.entities.Basket;
import business.entities.BasketEntry;
import business.entities.Bottom;
import business.entities.Topping;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BasketCommand extends CommandUnprotectedPage
{
    public BasketCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        int toppingId;
        int bottomId;
        int amount;
        try{
            toppingId= Integer.parseInt(request.getParameter("topping"));
            bottomId= Integer.parseInt(request.getParameter("bottom"));
            amount = Integer.parseInt(request.getParameter("amount"));

        }catch(NumberFormatException ex){
            throw new UserException("fejl i valgning cupcake");
        }

        List<Topping> toppingList=(List<Topping>)request.getServletContext().getAttribute("toppingList");
        List<Bottom> bottomList=(List<Bottom>)request.getServletContext().getAttribute("bottomList");

        HttpSession session= request.getSession();

        Basket basket = (Basket) session.getAttribute("basket");
        //session.setAttribute("basket", basket);

        if(basket ==null){
            basket =new Basket();
        }

        Topping topping = getToppingFromId(toppingList,toppingId);

        Bottom bottom = getBottomFromId(bottomList,bottomId);

        BasketEntry basketEntry=new BasketEntry(topping,bottom,amount);

        basket.addToCart(basketEntry);

        session.setAttribute("basket",basket);

        return pageToShow;
    }
    private Bottom getBottomFromId(List<Bottom> bottomList, int bottomId) {
        for(Bottom bottom:bottomList){
            if(bottom.getBottomId()==bottomId){
                return bottom;
            }
        }
        return null;
    }

    private Topping getToppingFromId(List<Topping> toppingList, int toppingId) {
        for(Topping topping : toppingList){
            if(topping.getToppingId()==toppingId){
                return topping;

            }
        }
        return null;
    }

}
