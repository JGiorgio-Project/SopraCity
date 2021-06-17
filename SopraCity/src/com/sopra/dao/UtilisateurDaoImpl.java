package com.sopra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sopra.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
	private DaoFactory daoFactory;
	

	public UtilisateurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Fonction qui permet de tester l'autorisation de connexion d'un utilisateur en fonction du login et du mot de passe saisie.
	 * @return boolean
	 */
	@Override
	public boolean autorisationConnection(HttpServletRequest request) {
		boolean autorisation = false;
		String passwordBdd = null;
		String passwordSaisie = request.getParameter("password");
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT login, password FROM Utilisateur WHERE login = ?;");
			preparedStatement.setString(1, request.getParameter("login"));
			
			resultat = preparedStatement.executeQuery();
			
			
			while (resultat.next()) {
				passwordBdd = resultat.getString("password").toString();
			}

			if(passwordBdd != null) {
				if(passwordSaisie.equals(passwordBdd)) {
					autorisation = true;
				}else {
					autorisation = false;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return autorisation;
	}

}
