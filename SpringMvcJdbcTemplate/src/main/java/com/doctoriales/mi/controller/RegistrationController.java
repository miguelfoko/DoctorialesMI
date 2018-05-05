package com.doctoriales.mi.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctoriales.mi.dao.StudentDAO;
import com.doctoriales.mi.dao.TeacherDAO;
import com.doctoriales.mi.model.Student;
import com.doctoriales.mi.model.Teacher;
import com.doctoriales.mi.model.User;


@Controller

public class RegistrationController {

	private static final String SMTP_HOST_NAME = "smtp.mail.yahoo.com";
	private static final int SMTP_HOST_PORT = 587;//465,587,25
	private static final String SMTP_AUTH_USER = "doctorialesnoreply@yahoo.com";
	private static final String SMTP_AUTH_PWD  = "";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String,String>();
	public String getResultat() {
		return resultat;
	} 
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	String operationResult=null;
	@Autowired
	StudentDAO studentDAO;
	@Autowired
	TeacherDAO teacherDAO;
	static private int code=0;
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String uploadArticleGet(Model model) {
		System.out.println("/registration get");

		return "registration";
	}
	@RequestMapping(value = "/operationEnds", method = RequestMethod.GET)
	public String uploadArticleGetA(Model model, HttpServletRequest req) {
		System.out.println("/operationEnds get");
		operationResult="Opération réussie, consultez votre boite mail pour voir vos information sur notre plateforme"
				+ "<br>Il est recommendé de mettre à jour vos informations pour donner plus d'amples informations "
				+ "vous concernant";
		req.setAttribute("operationResult", operationResult);
		return "registration";
	}
	@RequestMapping(value = "/operationEndsBut", method = RequestMethod.GET)
	public String uploadArticleGetAA(Model model, HttpServletRequest req) {
		System.out.println("/operationEnds get");
		operationResult="Opération réussie, <br>Il est recommendé de mettre à jour vos informations pour donner "
				+ "plus d'amples informations vous concernant ";
		req.setAttribute("operationResult", operationResult);
		return "registration";
	}

	@RequestMapping(value = "/operationFalse", method = RequestMethod.GET)
	public String uploadArticleGetB(Model model, HttpServletRequest req) {
		System.out.println("/operationEnds get");
		operationResult="Echec de l'opération, cet utilisateur existe déjà";
		req.setAttribute("operationResult", operationResult);
		return "registration";
	}

	@RequestMapping(value = "/operationFalseOther", method = RequestMethod.GET)
	public String uploadArticleGetBB(Model model, HttpServletRequest req) {
		System.out.println("/operationEnds get");
		operationResult="Echec de l'opération Pour des raisons liées à la connexion à la Base de données ";
		req.setAttribute("operationResult", operationResult);
		return "registration";
	}

	@RequestMapping(value = "/updateEnds", method = RequestMethod.GET)
	public String uploadArticleGetC(Model model, HttpServletRequest req) {
		System.out.println("/operationEnds get");
		operationResult="Mise à jour réussie, consultez votre boite mail pour voir vos information sur notre plateforme";
		req.setAttribute("operationResult", operationResult);
		return "interUpdate";
	}

	@RequestMapping(value = "/updateEndsBut", method = RequestMethod.GET)
	public String uploadArticleGetE(Model model, HttpServletRequest req) {
		System.out.println("/operationEnds get");
		operationResult="Mise à jour réussie ";
		req.setAttribute("operationResult", operationResult);
		return "interUpdate";
	}

	@RequestMapping(value = "/updateFalse", method = RequestMethod.GET)
	public String uploadArticleGetD(Model model, HttpServletRequest req) {
		System.out.println("/operationEnds get");
		operationResult="Echec de l'opération, Pour des raisons liées à l'exécution de la requête ";
		req.setAttribute("operationResult", operationResult);
		return "interUpdate";
	}
	@RequestMapping(value = "/updateFalseOther", method = RequestMethod.GET)
	public String uploadArticleGetDD(Model model, HttpServletRequest req) {
		System.out.println("/operationEnds get");
		operationResult="Echec de l'opération, Pour des raisons liées à la connexion à la Base de données";
		req.setAttribute("operationResult", operationResult);
		return "interUpdate";
	}
	@RequestMapping(value = "/bdFailure", method = RequestMethod.GET)
	public String uploadArticleGetDDDD(Model model, @RequestParam("message") String message,
			@RequestParam("ret") String ret, HttpServletRequest req) {
		System.out.println("/uploadFalsedownload get");
		int rete=new Integer(ret);
		if(rete==1){
			operationResult="Echec de l'opération, Base de données indisponible: "+message;
			req.setAttribute("operationResult", operationResult);
			return "interUpdate";
		}else{
			operationResult="OK "+message;
			req.setAttribute("operationResult", operationResult);
			return "interUpload";
		}


	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrationPost(Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		System.out.println("registration post");

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


		String firstName= new String(req.getParameter("firstName").getBytes(),"UTF-8");
		String lastName= new String(req.getParameter("lastName").getBytes(),"UTF-8");
		String universityName= new String(req.getParameter("universityName").getBytes(),"UTF-8");
		String option= new String(req.getParameter("option").getBytes(),"UTF-8");
		String email= new String(req.getParameter("email").getBytes(),"UTF-8");
		String quality= new String(req.getParameter("quality").getBytes(),"UTF-8");
		String sexe= new String(req.getParameter("sexe").getBytes(),"UTF-8");
		String labo= new String(req.getParameter("laboratoire").getBytes(),"UTF-8");
		String login = lastName+firstName;
		String password = new String(req.getParameter("password").getBytes(),"UTF-8");
		
		ValidationField valid = new ValidationField();
		try {
			valid.validationEmail( email );
		} catch ( Exception e ) {
			erreurs.put( "email", e.getMessage() );
		}
		try {
			valid.validationPassword( password );
		} catch ( Exception e ) {
			erreurs.put( "password", e.getMessage() );
		}

		try {
			valid.validationName( lastName );
		} catch ( Exception e ) {
			erreurs.put( "name", e.getMessage() );
		}
		try {
			valid.validationFirstName( firstName );
		} catch ( Exception e ) {
			erreurs.put( "firsNname", e.getMessage() );
		}

		String subject1 = "Registration Information";
//		if(erreurs.isEmpty()){
			if(quality.equalsIgnoreCase("enseignant")){

				String grade= "Enseignant";

				try{
					MimeMessage message = new MimeMessage(session);
					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));
					// Set To: header field of the header.
					message.addRecipient(Message.RecipientType.TO,
							new InternetAddress(email));
					// Set Subject: header field
					message.setSubject(subject1);
					message.setSentDate(new Date());

					Teacher teacher=new Teacher(0,lastName, firstName, universityName, email, 
							option, quality, password, labo, sexe);
					teacher.setGrade(grade);
					System.out.println(teacher);
					String content1 = "Compte créé avec succès, vos informations se présentent comme suit:  \n"
							+teacher;
					// Now set the actual message
					message.setText(content1);
					req.setAttribute("user", teacher);
					//Teacher s=teacherDAO.getByMail(teacher.getUserEmail());
					int res=teacherDAO.saveOrUpdate(teacher);
					System.out.println(teacher);
					if(res==3){//Succès de l'opération
						String s=null;
						try{
							// Send message
							Transport transport = session.getTransport("smtp");
							transport.connect(host, from, pass);
							System.out.println("connect");
							transport.sendMessage(message, message.getAllRecipients());
							transport.close();
							System.out.println("Sent message successfully....");

							return "redirect:/operationEnds";
						}catch(Exception e){
							//mex.printStackTrace();
							return "redirect:/operationEndsBut";
						}
					}else{
						if(res==4){
							return "redirect:/operationFalse";
						}else{
							return "redirect:/operationFalseOther";
						}
					}
				}catch(Exception e){
					return "redirect:/bdFailure?message="+e.getMessage()+"&ret=1";
				}

			}else 
				if(quality.equalsIgnoreCase("etudiant")){

					String level= "Doctorant";

					try{
						MimeMessage message = new MimeMessage(session);
						// Set From: header field of the header.
						message.setFrom(new InternetAddress(from));
						// Set To: header field of the header.
						message.addRecipient(Message.RecipientType.TO,
								new InternetAddress(email));
						// Set Subject: header field
						message.setSubject(subject1);
						message.setSentDate(new Date());

						Student student=new Student(0, lastName, firstName, universityName, email, 
								option, quality, password,labo,sexe);
						student.setStudentLevel(level);
						String content1 = "Compte créé avec succès, vos informations se présentent comme suit:  \n"
								+student;
						message.setText(content1);
						req.setAttribute("user", student);
						System.out.println("Insertion en attente+++++++++++++++++++++++++++++++");
						int res=studentDAO.saveOrUpdate(student);
						if(res==3){//Succès de l'opération
							String s=null;
							try{
								// Send message
								Transport transport = session.getTransport("smtp");
								transport.connect(host, from, pass);
								System.out.println("connect");
								transport.sendMessage(message, message.getAllRecipients());
								transport.close();
								System.out.println("Sent message successfully....");
								System.out.println("Insertion réussie-----------------------------------");
								return "redirect:/operationEnds";
							}catch(Exception e){
								return "redirect:/operationEndsBut";
							}
						}else{
							if(res==4){
								return "redirect:/operationFalse";
							}else{
								return "redirect:/operationFalseOther";
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
//		}else{
//			req.setAttribute("erreurs", erreurs);
//		}
		return "redirect:/registration";
	}

	//Modification des parametres

	@RequestMapping(value = "/updateParameter", method = RequestMethod.GET)
	public String updateParameterGet(Model model, HttpServletRequest req) {
		System.out.println("interUpdate get");
		return "interUpdate";
	}

	@RequestMapping(value = "/interUpdate", method = RequestMethod.GET)
	public String updateParameterGet1(Model model, HttpServletRequest req) {
		System.out.println("interUpdate get");
		operationResult="Cet utilisateur n'existe pas";
		req.setAttribute("operationResult", operationResult);
		return "interUpdate";
	}



	@RequestMapping(value = "/updateParameter", method = RequestMethod.POST)
	public String updateParameterPost(Model model, HttpServletRequest req) {
		System.out.println("updateParameter post");
		//		String codeRegistration = req.getParameter("codeRegistration");

		//chercher l'utilisateur ayant le codeReistration dans la bd et retourner a la vu 
		// avec model.addAllAttribute("Users" users) ou users est le dit utilisateur
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String quality = req.getParameter("quality");

		User user=studentDAO.getByEmailAndPassword(email, password,quality);
		if(user!=null){
			req.setAttribute("user", user);
			return "updateParameter";
		}
		else{
			return "redirect:/interUpdate";
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateParameterPost2(Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		System.out.println("update post");
		Properties properties = new Properties();
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
		Session session = Session.getInstance(properties, null);


		int userCode=new Integer(req.getParameter("userCode"));
		String firstName= new String(req.getParameter("firstName").getBytes(),"UTF-8");
		String lastName= new String(req.getParameter("lastName").getBytes(),"UTF-8");
		String universityName= new String(req.getParameter("universityName").getBytes(),"UTF-8");
		String option= new String(req.getParameter("option").getBytes(),"UTF-8");
		String email= new String(req.getParameter("email").getBytes(),"UTF-8");
		String quality= new String(req.getParameter("quality").getBytes(),"UTF-8");
		String sexe= new String(req.getParameter("sexe").getBytes(),"UTF-8");
		String labo= new String(req.getParameter("laboratoire").getBytes(),"UTF-8");
		String password = new String(req.getParameter("password").getBytes(),"UTF-8");
		


		String subject1 = "Update Information";

		if(quality.equalsIgnoreCase("enseignant")){

			String grade= req.getParameter("grade");

			try{
				MimeMessage msg = new MimeMessage(session);
				msg.setRecipients(MimeMessage.RecipientType.TO, email);
				System.out.println(email);
				msg.setSubject(subject1);

				msg.setSentDate(new Date());
				Teacher teacher=new Teacher(userCode,lastName, firstName, universityName, email, 
						option, quality, password, labo, sexe);
				teacher.setGrade(grade);
				String content1 = "Mise à jour réussie de votre compte, vos nouvelles informations se présentent comme suit:  \n"
						+teacher;
				msg.setText(content1);
				int res=teacherDAO.saveOrUpdate(teacher);
				if(res==1){//Succès de l'opération
					String s=null;
					try{
						Transport transport = session.getTransport("smtp");
						transport.connect("smtp.gmail.com", "saphirmfogo@gmail.com", "best1234");
						transport.sendMessage(msg, msg.getAllRecipients());
						transport.close();
						return "redirect:/updateEnds";
					}catch(Exception e){
						return "redirect:/updateEndsBut";
					}
				}else{
					if(res==2){
						return "redirect:/updateFalse";
					}else{
						return "redirect:/updateFalseOther";
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}else if(quality.equalsIgnoreCase("etudiant")){

			String level= req.getParameter("level");

			try{
				MimeMessage msg = new MimeMessage(session);
				//msg.setFrom(new InternetAddress(form));
				msg.setRecipients(MimeMessage.RecipientType.TO, email);
				System.out.println(email);
				msg.setSubject(subject1);
				msg.setSentDate(new Date());

				Student student=new Student(userCode, lastName, firstName, universityName, email, 
						option, quality, password,labo,sexe);
				student.setStudentLevel(level);
				String content1 = "Mise à jour réussie de votre compte, vos nouvelles informations se présentent comme suit:  \n"
						+student;
				msg.setText(content1);
				int res=studentDAO.saveOrUpdate(student);
				if(res==1){//Succès de l'opération
					String s=null;
					try{
						Transport transport = session.getTransport("smtp");
						transport.connect("smtp.gmail.com", "saphirmfogo@gmail.com", "best1234");
						transport.sendMessage(msg, msg.getAllRecipients());
						transport.close();
						return "redirect:/updateEnds";
					}catch(Exception e){
						return "redirect:/updateEndsBut";
					}
				}else{
					if(res==2){
						return "redirect:/updateFalse";
					}else{
						return "redirect:/updateFalseOther";
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return "redirect:/registration";
	}

}
