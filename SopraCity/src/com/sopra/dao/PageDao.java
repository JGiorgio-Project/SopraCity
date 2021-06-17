package com.sopra.dao;

import com.sopra.beans.Page;

public interface PageDao {
	void ajouterPage( Page page );
    Page lastPage();
}
