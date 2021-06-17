package com.sopra.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sopra.beans.Page;
import com.sopra.dao.DaoFactory;
import com.sopra.dao.PageDao;

/**
 * Servlet implementation class Configuration
 */
@WebServlet("/Configuration")
public class Configuration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDao pageDao;
       
	
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.pageDao = daoFactory.getPageDao();
    }
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Configuration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean autorisation = false;
		HttpSession session = request.getSession();
		if(session.getAttribute("autorisation") != null) {
			autorisation = (boolean) session.getAttribute("autorisation");
		}
		
		if(autorisation) {
			request.setAttribute("page", pageDao.lastPage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/configuration.jsp").forward(request, response);
		}else {
			this.getServletContext().getRequestDispatcher("/Administration").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean autorisation = false;
		
		if(request.getParameter("valForm") != null) {
						
			String titre = request.getParameter("titre");
			String habitantsString = request.getParameter("habitants");
			String texte = request.getParameter("texte");
			String dateString = request.getParameter("date");
			
			if(testSaisie(titre, habitantsString, texte, dateString)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = format.parse(dateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int habitants = Integer.parseInt(habitantsString);
				
				Page page = new Page();
				page.setTitre(titre);
				page.setNbHabitants(habitants);
				page.setContenu(texte);
				page.setDate(date);
				
				System.out.println("Titre : "+titre+" || Habitants : "+habitants+" || Texte : "+texte+" || Date : "+date);
				
				pageDao.ajouterPage(page);
				
				this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
			}else {
				request.setAttribute("message", "Veuillez remplir tous les champs");
				request.setAttribute("page", pageDao.lastPage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/configuration.jsp").forward(request, response);
			}
			
			
		}else {
			HttpSession session = request.getSession();
			if(session.getAttribute("autorisation") != null) {
				autorisation = (boolean) session.getAttribute("autorisation");
			}
			if(autorisation) {
				request.setAttribute("page", pageDao.lastPage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/configuration.jsp").forward(request, response);
			}else {
				this.getServletContext().getRequestDispatcher("/Administration").forward(request, response);
			}
		}
	}
	
	 boolean testSaisie(String titre, String habitantsString, String texte, String dateString) {
		boolean result = true;
				
		if(titre == "") {
			result = false;
		}
		 
		if(habitantsString == "") {
			result = false;
		}
		 
		if(texte == "") {
			result = false;
		}
		 
		if(dateString == "") {
			result = false;
		}
		
		return result;
	}
}
