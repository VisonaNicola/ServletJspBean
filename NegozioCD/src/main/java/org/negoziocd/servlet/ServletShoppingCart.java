package org.negoziocd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.negoziocd.beans.CDMarket;
import org.negoziocd.beans.ShoppingCart;
import org.negoziocd.beans.CD;
/**
 * This servlet is used when the user wants to add a cd to his shopping list.
 * The servlet creates the shopping list if it does not already exists, and then it adds the selectd cd to the list.
 * The servlet then redirects to the market jsp, so that the user can add more cds if hew wants.
 */
@WebServlet("/ServletShoppingCart")
public class ServletShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET /ServletShoppingCart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST /ServletShoppingCart");
		
		
		HttpSession session = request.getSession();
		ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingcart");	//get shopping cart from session
		if(sc == null)	//create shopping cart if it does not exists
			sc = new ShoppingCart();
		
		String cdId = request.getParameter("id");	//get id of the cd to be added to shopping cart
		if(cdId==null || cdId == "" ) {
			System.out.println("id incorrect!");
			//redirect error page?
			return ;
		}
		CDMarket cm = (CDMarket) session.getAttribute("cdmarket");	//get shopping cart to get the list of cds
		if(cm==null) {	//redirect to /ServletCD if it does not exists
			System.out.println("cd market null");
			request.getRequestDispatcher("/ServletCD").forward(request, response);
		}
			
		for(CD cd : cm.getCdlist()) {
			if(cd.getId() == Integer.parseInt(cdId))//find the cd with the correct id
				sc.addCD(cd);
		}
		session.setAttribute("cdmarket",cm);
		session.setAttribute("shoppingcart", sc);
		request.getRequestDispatcher("market.jsp").forward(request, response);	//return to market.jsp
			
			
	}

}
