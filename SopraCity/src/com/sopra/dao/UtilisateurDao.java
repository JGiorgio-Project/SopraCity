package com.sopra.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sopra.beans.Utilisateur;

public interface UtilisateurDao {
    boolean autorisationConnection(HttpServletRequest request); 
}
