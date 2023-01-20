package org.negoziocd.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.negoziocd.beans.ShoppingCart;
import org.negoziocd.beans.CD;

/**
 * This servlet is used to modify the quantity of a cd in the shopping list, using the methods made available by the ShoppingCart class.
 */
@WebServlet("/ServletModifyQuantity")
public class ServletModifyQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifyQuantity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET /ServletModifyQuantity");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST /ServletModifyQuantity");
		
		HttpSession session = request.getSession();
		ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingcart");	//get the shopping cart
		if(sc == null) {
			System.out.println("Shopping cart null");
			return;
		}
		String cdId = request.getParameter("id");	//get id of the cd
		String value = request.getParameter("value");	//get new quantity
		
//		for(CD cd : sc.getCdlist()) {
//			if(cd.getId()==Integer.parseInt(cdId)) {
//				sc.modifyQta(cd,Integer.parseInt(value));
//			}
//		}
		
		Iterator<CD> i = sc.getCdlist().iterator();
		while(i.hasNext()) {
			CD cd = i.next();
			if(cd.getId()==Integer.parseInt(cdId)) {	//find the cd with the correct id
				sc.modifyQta(cd,Integer.parseInt(value));	//modify quantity
			}
		}
		session.setAttribute("shoppingcart", sc);
		request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);	//return to shoppingcart.jsp
			
	}

}
