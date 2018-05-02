package com.doctoriales.mi.model;

public class Teacher extends User{
	private String grade;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Teacher(int inscriptionCode, String userLastName, String userFirstName, String universityName,
			String userEmail, String option, String userFunction, String userPassword, String laboratoire,
			String sexe) {
		super(inscriptionCode, userLastName, userFirstName, universityName, userEmail, option, userFunction, userPassword,
				laboratoire, sexe);
		// TODO Auto-generated constructor stub
	}



	public Teacher(String grade) {
		super();
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "grade=" + grade + "\n Nom:=" + userLastName
				+ "\n Prénom:" + userFirstName + "\n Université=" + universityName + "\n"
				+ "\n Spécialité:" + option + "\n Qualité=" + userFunction + "\n Password="
				+ userPassword + "\n";
	}
	

}
