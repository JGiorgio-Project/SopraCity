package com.sopra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.sopra.beans.Page;

public class PageDaoImpl implements PageDao {
	private DaoFactory daoFactory;
	

	public PageDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Methode permettant d'ajouter une nouvelle page en BDD.
	 */
	@Override
	public void ajouterPage(Page page) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO Page(titre, nbHabitants, contenu, date) VALUES(?, ?, ?, ?);");
			preparedStatement.setString(1, page.getTitre());
			preparedStatement.setInt(2, page.getNbHabitants());
			preparedStatement.setString(3, page.getContenu());
			preparedStatement.setDate(4, new java.sql.Date(page.getDate().getTime()) );
			
			
			preparedStatement.executeUpdate();
			
			connexion.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Fonction qui permet de recupérer la derniére page en BDD.
	 * @return Page
	 */
	@Override
	public Page lastPage() {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Page pageResult = null;
		
        try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT titre, nbHabitants, contenu, date FROM Page WHERE id_page = (SELECT max(id_page) FROM Page);");
			
			while (resultat.next()) {
				String titre = resultat.getString("titre");
	            int nbHabitants = resultat.getInt("nbHabitants");
	            String contenu = resultat.getString("contenu");
	            Date date = resultat.getDate("date");

	            pageResult = new Page();
	            pageResult.setTitre(titre);
	            pageResult.setNbHabitants(nbHabitants);
	            pageResult.setContenu(contenu);
	            pageResult.setDate(date);
			}
			

           
      			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageResult;
	}

}
