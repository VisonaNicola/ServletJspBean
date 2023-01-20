package org.negoziocd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.negoziocd.beans.CD;
import org.negoziocd.beans.CDMarket;

/**
 * This servlet is used to create the CDMarket bean, adding all the cds.
 * The CDMarket is then sent to the correct jsp so that the market can be visualized by the user.
 */
@WebServlet("/")
public class ServletCD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET /ServletCD");
		HttpSession session = request.getSession();
		
		CDMarket cm = (CDMarket) session.getAttribute("cdmarket");
		if(cm == null || cm.getCdlist().size()==0) {
			cm = new CDMarket();
			cm.addCD(new CD(1,"titolo1","autore1",4.99));
			cm.addCD(new CD(2,"titolo2","autore2",5.99));
			cm.addCD(new CD(3,"titolo3","autore3",6.99));
			cm.addCD(new CD(4,"titolo4","autore4",7.99));
		}				
		session.setAttribute("cdmarket",cm);
		request.getRequestDispatcher("market.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST /ServletCD");
		doGet(request, response);
	}

}
