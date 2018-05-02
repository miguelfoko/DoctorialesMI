package com.doctoriales.mi.dao;

import java.util.List;

import com.doctoriales.mi.model.Article;
import com.doctoriales.mi.model.Student;
import com.doctoriales.mi.model.User;

public interface ArticleDAO {
	public int saveOrUpdate(Article article);/*1=Update Success, 2=Update failed, 3=Insertion Success and 
	4=Insertion Failed*/

	public void delete(int articleId);

	public Article get(int 	articleId);
	public List<Article> list();
}
