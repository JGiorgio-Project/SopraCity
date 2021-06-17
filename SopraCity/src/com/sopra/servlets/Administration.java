package com.sopra.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sopra.dao.DaoFactory;
import com.sopra.dao.UtilisateurDao;

/**
 * Servlet implementation class Administration
 */
@WebServlet("/Administration")
public class Administration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
       
	
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Administration() {
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
			this.getServletContext().getRequestDispatcher("/Configuration").forward(request, response);
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/administration.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//Récupération des saisies de l'utilisateur
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		//Vérification de la longueur du login et du mot de passe
		if(login.length() < 8 || login.length() > 12 || login.length() == 0 ) {
			request.setAttribute("message", "Le login doit être compris entre 8 et 12 caractéres!");
			this.getServletContext().getRequestDispatcher("/WEB-INF/administration.jsp").forward(request, response);
		}
		if(password.length() < 8 || password.length() > 15 || password.length() == 0 ) {
			request.setAttribute("message", "Le mot de passe doit être compris entre 8 et 12 caractéres!");
			this.getServletContext().getRequestDispatcher("/WEB-INF/administration.jsp").forward(request, response);
		}
		
		//Récupération de l'autorisation de connexion
		boolean autorisation = false;
		autorisation = utilisateurDao.autorisationConnection(request);
		request.setAttribute("autorisation", autorisation);
		
		//Traitement de l'autorisation
		if(autorisation) {
			HttpSession session = request.getSession();
		
			session.setAttribute("autorisation", autorisation);
			
			this.getServletContext().getRequestDispatcher("/Configuration").forward(request, response);
			
		}else {
			request.setAttribute("message", "Mauvais login ou mot de passe!");
			this.getServletContext().getRequestDispatcher("/WEB-INF/administration.jsp").forward(request, response);
		}
	}
}
