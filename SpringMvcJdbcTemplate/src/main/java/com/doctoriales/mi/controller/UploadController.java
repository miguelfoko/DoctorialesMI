package com.doctoriales.mi.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.doctoriales.mi.dao.ArticleDAO;
import com.doctoriales.mi.dao.StudentDAO;
import com.doctoriales.mi.model.Article;
import com.doctoriales.mi.model.User;

@Controller
@MultipartConfig(fileSizeThreshold=1024*1024*2,maxFileSize=1024*1024*10,maxRequestSize=1024*1024*50)
public class UploadController {

	private static final String SMTP_HOST_NAME = "smtp.mail.yahoo.com";
	private static final int SMTP_HOST_PORT = 587;//465,587,25
	private static final String SMTP_AUTH_USER = "doctorialesnoreply@yahoo.com";
	private static final String SMTP_AUTH_PWD  = "";

	String operationResult=null;
	@Autowired 
	ArticleDAO articleDAO;
	@Autowired
	StudentDAO studentDAO;

	private static final String SAVE_DIR=File.separator+"var"+File.separator+"lib"+File.separator+"tomcat7"+File.separator+"webapps"+File.separator+"Doc";
//		private static final String SAVE_DIR="E:"+File.separator+"Disque D"+File.separator+"Work"+File.separator+"tomcat"+File.separator+"webapps"+File.separator+"Doc";
	static private int codes=0;

	@RequestMapping(value = "/interUpload", method = RequestMethod.GET)
	public String updateParameterGet1(Model model, HttpServletRequest req) {
		System.out.println("interUpdate get");
		operationResult="Cet utilisateur n'existe pas";
		req.setAttribute("operationResult", operationResult);
		return "interUpload";
	}

	@RequestMapping(value = "/uploadArticle", method = RequestMethod.GET)
	public String uploadArticleGet(Model model) {
		System.out.println("/interUpload get");

		return "interUpload";
	}

	@RequestMapping(value = "/uploadArticle1", method = RequestMethod.POST)
	public String uploadArticlePost1(Model model, HttpServletRequest req) {
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String quality = req.getParameter("quality");

		User user=studentDAO.getByEmailAndPassword(email, password,quality);
		if(user!=null){
			req.setAttribute("user", user);
			return "uploadArticle";
		}
		else{
			return "redirect:/interUpload";
		}
	}

	String upload(MultipartFile file,String name,String option)throws Exception{
		String ret=null;
		//		try {
		byte[] bytes = file.getBytes();

		// Creating the directory to store file
		String rootPath = System.getProperty("catalina.home");
		//			File dir = new File(rootPath + File.separator + SAVE_DIR+File.separator+option);
		File dir = new File(SAVE_DIR+File.separator+option);
		if (!dir.exists())
			dir.mkdirs();
		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath()
				+ File.separator + name);
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
		ret=serverFile.getAbsolutePath();
		return ret;

	}

	@RequestMapping(value = "/uploadArticle", method = RequestMethod.POST)
	public String uploadArticlePost(HttpServletRequest req,@RequestParam("file") MultipartFile file,
			@RequestParam("userOption") String option, @RequestParam("userId") int userId1,
			@RequestParam("userLastName") String userLastName, 
			@RequestParam("userFirstName") String userFirstName,@RequestParam("title") String title,
			@RequestParam("abstract") String abstractArticle,
			@RequestParam("keywords") String keywords) throws UnsupportedEncodingException {
		System.out.println("/uploadArticle post");

		 userFirstName= new String(userFirstName.getBytes(),"UTF-8");
		 userLastName= new String(userLastName.getBytes(),"UTF-8");
		 abstractArticle= new String(abstractArticle.getBytes(),"UTF-8");
		 option= new String(option.getBytes(),"UTF-8");
		 title= new String(title.getBytes(),"UTF-8");
		 keywords= new String(keywords.getBytes(),"UTF-8");
		
		
		// Sender's email ID needs to be mentioned
		String from = "doctorialesnoreply@yahoo.com";
		String pass ="docto1234";
		String host = "smtp.mail.yahoo.com";

		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", pass);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		/*Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		// properties.put("mail.smtp.host", "smtp-relay.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.starttls.required", "false");
		properties.put("mail.smtp.connectiontimeout", "5000");
		properties.put("mail.smtp.timeout", "5000");
		properties.put("mail.smtp.writetimeout", "5000");
		Session session = Session.getInstance(properties, null);*/

		int userId=new Integer(userId1);
		String subject1 = "Article Soumis ";
		List<String> emailAdress= new ArrayList<String>();
		emailAdress.add("miguelfoko@gmail.com");
		emailAdress.add("sapphiremfogo@gmail.com");
		String articleName=userId+"_"+userLastName+"_"+userFirstName+"_"+option+".pdf";
		Article article=new Article(0, title, abstractArticle, SAVE_DIR+File.separator+option+File.separator+ articleName, keywords, userId);
		String content1 = "Article soumis avec succes, ci dessous, les informations le concernant  \n"
				+ article  ;
		try {
			String fileNam=file.getOriginalFilename();
			String extension=fileNam.substring(fileNam.length()-4, fileNam.length());
			if(extension.equals(".pdf")){
				String ret=upload(file,articleName,option);
				if(ret!=null){
					int res=articleDAO.saveOrUpdate(article);
					if(res==3){//Succès de l'opération
						String s=null;
						try{
							for(String email:emailAdress){

								MimeMessage message = new MimeMessage(session);
								// Set From: header field of the header.
								message.setFrom(new InternetAddress(from));
								// Set To: header field of the header.
								message.addRecipient(Message.RecipientType.TO,
										new InternetAddress(email));
								// Set Subject: header field
								message.setSubject(subject1);
								message.setSentDate(new Date());
								message.setText(content1);

//								Multipart multipart = new MimeMultipart();
//								MimeBodyPart messageBodyPart = new MimeBodyPart();
//								DataSource source = new FileDataSource(articleName);
//								messageBodyPart.setDataHandler(new DataHandler(source));
//								messageBodyPart.setFileName(articleName.replaceAll(".*_/", ""));
//								multipart.addBodyPart(messageBodyPart);
//								message.setContent(multipart);
								message.setSentDate(new Date());
								// Send message
								Transport transport = session.getTransport("smtp");
								transport.connect(host, from, pass);
								System.out.println("connect");
								transport.sendMessage(message, message.getAllRecipients());
								transport.close();
								System.out.println("Sent message successfully....");
								return "redirect:/uploadEnds";
							}

						}catch(Exception e){
							return "redirect:/uploadEndsBut";
						}
					}else{
						if(res==4){
							return "redirect:/uploadFalse";
						}else{
							return "redirect:/uploadFalseOther";
						}
					}
				}else{
					return "redirect:/uploadFalseUnable";
				}
			}else{
				return "redirect:/uploadNotPDF";
			}

		} catch (Exception e) {
			return "redirect:/uploadFalsedownload?message="+e.getMessage();
			// TODO: handle exception
		}




		return "redirect:/uploadFalse";
	}
	@RequestMapping(value = "/uploadEnds", method = RequestMethod.GET)
	public String uploadArticleGetC(Model model, HttpServletRequest req) {
		System.out.println("/uploadEnds get");
		operationResult="Upload réussie, consultez votre boite mail pour voir les informations relatives à votre "
				+ "soumission";
		req.setAttribute("operationResult", operationResult);
		return "interUpload";
	}
	@RequestMapping(value = "/uploadEndsUpload", method = RequestMethod.GET)
	public String uploadArticleGetCdd(Model model, HttpServletRequest req) {
		System.out.println("/uploadEnds get");
		operationResult="Mise à jour réussie réussie, consultez votre boite mail pour voir les informations relatives à votre "
				+ "soumission";
		req.setAttribute("operationResult", operationResult);
		return "interUpdateArticle";
	}

	@RequestMapping(value = "/uploadNotPDF", method = RequestMethod.GET)
	public String uploadArticleGetCCC(Model model, HttpServletRequest req) {
		System.out.println("/uploadEnds get");
		operationResult="Impossible de télécharger le fichier, il doit être un PDF ";
		req.setAttribute("operationResult", operationResult);
		return "interUpload";
	}

	@RequestMapping(value = "/uploadEndsBut", method = RequestMethod.GET)
	public String uploadArticleGetCC(Model model, HttpServletRequest req) {
		System.out.println("/uploadEndsBut get");
		String message=req.getParameter("message");
		operationResult="Upload réussie ";
		req.setAttribute("operationResult", operationResult);
		return "interUpload";
	}
	
	@RequestMapping(value = "/uploadEndsUpdateBut", method = RequestMethod.GET)
	public String uploadArticleGetCCd(Model model, HttpServletRequest req) {
		System.out.println("/uploadEndsBut get");
		String message=req.getParameter("message");
		operationResult="Mise à jour réussie ";
		req.setAttribute("operationResult", operationResult);
		return "interUpdateArticle";
	}

	@RequestMapping(value = "/uploadFalse", method = RequestMethod.GET)
	public String uploadArticleGetD(Model model, HttpServletRequest req) {
		System.out.println("/uploadFalse get");
		operationResult="Echec de l'opération, Problème interne à la base de données";
		req.setAttribute("operationResult", operationResult);
		return "interUpload";
	}

	@RequestMapping(value = "/uploadFalseOther", method = RequestMethod.GET)
	public String uploadArticleGetDD(Model model, HttpServletRequest req) {
		System.out.println("/uploadFalseOther get");
		operationResult="Echec de l'opération, Problème de connexion à la Base de Données";
		req.setAttribute("operationResult", operationResult);
		return "interUpload";
	}
	@RequestMapping(value = "/uploadFalsedownload", method = RequestMethod.GET)
	public String uploadArticleGetDDDD(Model model, HttpServletRequest req) {
		System.out.println("/uploadFalsedownload get");
		String message=req.getParameter("message");
		operationResult="Echec de l'opération, Erreur deu téléchargement du fichier: "+message;
		req.setAttribute("operationResult", operationResult);
		return "interUpload";
	}


	@RequestMapping(value = "/uploadFalseUnable", method = RequestMethod.GET)
	public String uploadArticleGetDDD(Model model, HttpServletRequest req) {
		System.out.println("/uploadFalseOther get");
		operationResult="Echec de l'opération, Téléchargement du fichier impossible";
		req.setAttribute("operationResult", operationResult);
		return "interUpload";
	}
	// Modifier l'article

	@RequestMapping(value = "/updateArticle", method = RequestMethod.GET)
	public String updateArticleGet(Model model) {
		System.out.println("/updateArticle get");

		return "interUpdateArticle";
	}


	@RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
	public String updateArticlePost(Model model, HttpServletRequest req,
			@RequestParam("file") MultipartFile file, 
			@RequestParam("userOption") String option, @RequestParam("userId") int userId1,
			@RequestParam("userLastName") String userLastName, 
			@RequestParam("userFirstName") String userFirstName,@RequestParam("title") String title,
			@RequestParam("abstract") String abstractArticle,
			@RequestParam("articleId") String id,
			@RequestParam("keywords") String keywords) throws UnsupportedEncodingException {
		System.out.println("/updateArticle post");

		 userFirstName= new String(userFirstName.getBytes(),"UTF-8");
		 userLastName= new String(userLastName.getBytes(),"UTF-8");
		 abstractArticle= new String(abstractArticle.getBytes(),"UTF-8");
		 option= new String(option.getBytes(),"UTF-8");
		 title= new String(title.getBytes(),"UTF-8");
		 keywords= new String(keywords.getBytes(),"UTF-8");
		
		
		// Sender's email ID needs to be mentioned
		String from = "doctorialesnoreply@yahoo.com";
		String pass ="docto1234";
		String host = "smtp.mail.yahoo.com";

		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", pass);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		/*Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		// properties.put("mail.smtp.host", "smtp-relay.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.starttls.required", "false");
		properties.put("mail.smtp.connectiontimeout", "5000");
		properties.put("mail.smtp.timeout", "5000");
		properties.put("mail.smtp.writetimeout", "5000");
		Session session = Session.getInstance(properties, null);*/

		int userId=new Integer(userId1);
		String subject1 = "Article Soumis ";
		List<String> emailAdress= new ArrayList<String>();
		emailAdress.add("miguelfoko@gmail.com");
		emailAdress.add("sapphiremfogo@gmail.com");
		int articleId=new Integer(id);
		String articleName=userId+"_"+userLastName+"_"+userFirstName+"_"+option+".pdf";
		Article article=new Article(articleId, title, abstractArticle, SAVE_DIR+File.separator+option+File.separator+ articleName, keywords, userId);
		String content1 = "Article soumis avec succes, ci dessous, les informations le concernant  \n"
				+ article  ;
		try {
			String fileNam=file.getOriginalFilename();
			String extension=fileNam.substring(fileNam.length()-4, fileNam.length());
			if(extension.equals(".pdf")){
				String ret=upload(file,articleName,option);
				if(ret!=null){
					int res=articleDAO.saveOrUpdate(article);
					if(res==1){//Succès de l'opération
						String s=null;
						try{
							for(String email:emailAdress){

								MimeMessage message = new MimeMessage(session);
								// Set From: header field of the header.
								message.setFrom(new InternetAddress(from));
								// Set To: header field of the header.
								message.addRecipient(Message.RecipientType.TO,
										new InternetAddress(email));
								// Set Subject: header field
								message.setSubject(subject1);
								message.setSentDate(new Date());
								message.setText(content1);

//								Multipart multipart = new MimeMultipart();
//								MimeBodyPart messageBodyPart = new MimeBodyPart();
//								DataSource source = new FileDataSource(articleName);
//								messageBodyPart.setDataHandler(new DataHandler(source));
//								messageBodyPart.setFileName(articleName.replaceAll(".*_/", ""));
//								multipart.addBodyPart(messageBodyPart);
//								message.setContent(multipart);
								message.setSentDate(new Date());
								// Send message
								Transport transport = session.getTransport("smtp");
								transport.connect(host, from, pass);
								System.out.println("connect");
								transport.sendMessage(message, message.getAllRecipients());
								transport.close();
								System.out.println("Sent message successfully....");
								return "redirect:/uploadEndsUpdate";
							}

						}catch(Exception e){
							return "redirect:/uploadEndsUpdateBut";
						}
					}else{
						if(res==4){
							return "redirect:/uploadFalse";
						}else{
							return "redirect:/uploadFalseOther";
						}
					}
				}else{
					return "redirect:/uploadFalseUnable";
				}
			}else{
				return "redirect:/uploadNotPDF";
			}

		} catch (Exception e) {
			return "redirect:/uploadFalsedownload?message="+e.getMessage();
			// TODO: handle exception
		}




		return "redirect:/uploadFalse";
	}
	@RequestMapping(value = "/interUpdateArticle", method = RequestMethod.GET)
	public String updateParameterGet1A(Model model, HttpServletRequest req) {
		System.out.println("interUpdateArticle get");
		operationResult="Cet utilisateur n'existe pas";
		req.setAttribute("operationResult", operationResult);
		return "interUpdateArticle";
	}
	
	@RequestMapping(value = "/updateArticle1", method = RequestMethod.POST)
	public String updateParameterPost(Model model, HttpServletRequest req, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("quality") String quality) {
		System.out.println("updateArticle1 post");
		
		User user=studentDAO.getByEmailAndPassword(email, password,quality);
		if(user!=null){
			Article article=articleDAO.get(user.getInscriptionCode());
			if(article!=null){
				req.setAttribute("user", user);
//				System.out.println("article++++"+article);
				req.setAttribute("article", article);
				return "updateArticle";
			}else{
				return "redirect:/interUpdateArticleNotExist";
			}
			
		}
		else{
			return "redirect:/interUpdateArticle";
		}
	}
	
	@RequestMapping(value = "/interUpdateArticleNotExist", method = RequestMethod.GET)
	public String updateParameterGet1Aa(Model model, HttpServletRequest req) {
		System.out.println("interUpdate get");
		operationResult="Vous n'avez pas encore soumis d'article";
		req.setAttribute("operationResult", operationResult);
		return "interUpdateArticle";
	}
}
