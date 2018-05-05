package com.doctoriales.mi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.doctoriales.mi.model.Article;
import com.doctoriales.mi.model.Student;

public class ArticleDAOImpl implements ArticleDAO {

	private JdbcTemplate jdbcTemplate;

	public ArticleDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public int saveOrUpdate(Article article) {
		int ret=-1;
//		System.out.println("1");
		System.out.println(article);
		if (article.getArticleId() > 0) {
//			System.out.println("++++++1");
			String sql = "UPDATE article SET ArticleTitle=?,ArticleAbstract=?,ArticleName=?,ArticleKeywords=?"
					+ "WHERE ArticleId=?";
			try{
//				System.out.println("++++++2");
				jdbcTemplate.update(sql, article.getArticleTitle(),article.getArticleAbstract(),
						article.getArticleName(),article.getArticleKeywords(),article.getArticleId());
				ret= 1;
			}catch(Exception e){
//				System.out.println("++++++3");
				ret= 2;
				e.printStackTrace();
			}finally{
				return ret;
			}
		} else {
//			System.out.println("++++++10");
			String sql = "INSERT INTO article (ArticleId,ArticleTitle,ArticleAbstract, ArticleName,"
					+ " ArticleKeywords,UserId) VALUES (?, ?, ?, ?,?,?)";
			
			try{
//				System.out.println(article);
				int i=jdbcTemplate.update(sql, article.getArticleId(),article.getArticleTitle(),article.getArticleAbstract(),
						article.getArticleName(),article.getArticleKeywords(),article.getUserId());
				ret= 3;
			}catch(Exception e){
				ret= 4;
				e.printStackTrace();
			}finally {
				return ret;
			}
		}
	}

	@Override
	public void delete(int articleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article get(int userId) {
		String sql = "SELECT * FROM article WHERE userId=" + userId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Article>() {

			@Override
			public Article extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					Article article = new Article();
					article.setArticleId(rs.getInt("ArticleId"));
					article.setArticleAbstract(rs.getString("ArticleAbstract"));
					article.setArticleKeywords(rs.getString("ArticleKeywords"));
					article.setArticleName(rs.getString("ArticleName"));
					article.setArticleTitle(rs.getString("ArticleTitle"));
					article.setUserId(rs.getInt("UserId"));
					System.out.println("Article from BD++++++++++"+article);
					return article;
				}

				return null;
			}

		});
	}

	@Override
	public List<Article> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
