package com.doctoriales.mi.controller;

public class ValidationField {
	
	public void validationName(String name)throws Exception{
		if ( name == null || name.trim().length() < 3 ) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.") ;	
		}
	}
	public void validationFirstName(String firstName)throws Exception{
	
		if ( firstName == null || firstName.trim().length() < 2 ) {
			throw new Exception("le prenom ne doit pas etre vide et doit avoir au moins 2 caracteres") ;	
		}
	}
	public void validationGrade(String grade)throws Exception{
		
		if ( grade == null || grade.trim().length() < 2) {
			throw new Exception("Merci de saisir un votre grade.") ;
		}
	}
public void validationLevel(String level)throws Exception{
		
		if ( level == null || level.trim().length() < 1 ) {
			throw new Exception("Merci de saisir un votre niveau.") ;
		}
	}
	public void validationEmail(String email)throws Exception{
		if ( email == null || !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			
			throw new Exception("Merci de saisir une adresse mail valide.") ;
			}
		
	}
	public void validationPassword( String motDePasse ) throws Exception {
		if ( motDePasse != null &&  motDePasse.length() < 3) {

		throw new Exception( "Merci de  saisir un mot de passe avec au moinsb 4 lettres" );
		}

		}
	

}
