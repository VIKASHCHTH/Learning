package code;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import bsh.ParseException;

public class RandomCodeGenerator {

	public static String splitDateAndTime(String fieldName) {

		String dateAndTimeToBeSplitted[] = fieldName.split(" ", 2);
		String date = dateAndTimeToBeSplitted[0];
		return date;
	}


	public static String dateGenerator(String dateToBeAdded)
	{  String exp=null;

	Date date = new Date();

	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	cal.setTime(date);
	if(dateToBeAdded.contains("oldDate:")) {
		String[]day=dateToBeAdded.split(":");
		String daytoBeadded=day[1];
		int dayToBeadded=Integer.parseInt(daytoBeadded);	
		cal.add(Calendar.DATE, -dayToBeadded); 

		date = cal.getTime();


		exp= dateFormat.format(date).toString();
	}
	else if(dateToBeAdded.contains("futureDate:")) {
		String[]day=dateToBeAdded.split(":");
		String daytoBeadded=day[1];
		int dayToBeadded=Integer.parseInt(daytoBeadded);	
		cal.add(Calendar.DATE,dayToBeadded); // add 10 days
		date = cal.getTime();
		exp= dateFormat.format(date).toString();
	}
	else if(dateToBeAdded.contains("oldYear:")) {
		String[]day=dateToBeAdded.split(":");
		String daytoBeadded=day[1];
		int dayToBeadded=Integer.parseInt(daytoBeadded);	
		cal.add(Calendar.YEAR, -dayToBeadded); // TO subtract years

		date = cal.getTime();


		exp= dateFormat.format(date).toString();
	}
	else if(dateToBeAdded.contains("now")) {
		exp= dateFormat.format(date).toString();

	}

	return exp;

	}

	public static String panNoGenerator() {
		String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers="1234567890";
		String panNumber=null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstFiveChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char firstFiveChars = tempFirstFiveChars[random.nextInt(tempFirstFiveChars.length)];
			sb.append(firstFiveChars);
		}

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 4; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}

		char[] tempLastChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char lastChar = tempLastChar[random.nextInt(tempLastChar.length)];
			sb.append(lastChar);
		}

		panNumber = sb.toString();	
		return panNumber;

	}

	public static String tanNoGenerator() {
		String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers="1234567890";
		String tanNumber=null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstFourChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			char firstFourChars= tempFirstFourChars[random.nextInt(tempFirstFourChars.length)];
			sb.append(firstFourChars);
		}

		char[] tempFiveNumbers = numbers.toCharArray();
		for (int i = 0; i < 5; i++) {
			char fiveNumbers = tempFiveNumbers[random.nextInt(tempFiveNumbers.length)];
			sb.append(fiveNumbers);
		}

		char[] tempLastChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char lastChar = tempLastChar[random.nextInt(tempLastChar.length)];
			sb.append(lastChar);
		}

		tanNumber = sb.toString();
		return tanNumber;
	}

	public static String petitionNoGenerator() {
		String numbers="1234567890";
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String petitionNumber=null;

		StringBuilder sb=new StringBuilder();

		char[] tempPetitionNumber =numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char petitionNumberGen = tempPetitionNumber[random.nextInt(tempPetitionNumber.length)];
			sb.append(petitionNumberGen);
		}

		sb.append("/");
		sb.append(year);

		petitionNumber=sb.toString();
		return petitionNumber;
	}

	public static String autoNameGenerator() {
		String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb=new StringBuilder();
		sb.append("AUTO");

		char[] tempChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 7; i++) {
			char fName = tempChars[random.nextInt(tempChars.length)];
			sb.append(fName);
		}

		String autoGenFirstName=sb.toString();
		return autoGenFirstName;

	}

	public static String calenderDateGenerator(String dateToBeAdded){
		String exp=null;
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
		cal.setTime(date);
		if(dateToBeAdded.contains("oldDate:")){
			String[]day=dateToBeAdded.split(":");
			String daytoBeadded=day[1];
			int dayToBeadded=Integer.parseInt(daytoBeadded);	
			cal.add(Calendar.DATE, -dayToBeadded); // add 10 days
			date = cal.getTime();
			exp= dateFormat.format(date).toString();
		}
		else if(dateToBeAdded.contains("futureDate:")){
			String[]day=dateToBeAdded.split(":");
			String daytoBeadded=day[1];
			int dayToBeadded=Integer.parseInt(daytoBeadded);	
			cal.add(Calendar.DATE,dayToBeadded); // add 10 days
			date = cal.getTime();
			exp= dateFormat.format(date).toString();
		}
		else if(dateToBeAdded.contains("now")){
			exp= dateFormat.format(date).toString();

		}

		return exp;

	}

	public static String getCurrentTimeOfApplication(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		String currentTime = sdf.format(new Date()); 
		return currentTime;
	}

	public static String getPinCode(){
		String numbers="1234567890";
		StringBuilder sb = new StringBuilder();
		String pinCode=null;
		Random random = new Random();
		char[] tempSixNumbers = numbers.toCharArray();
		for (int i = 0; i < 6; i++) {
			char fiveNumbers = tempSixNumbers[random.nextInt(tempSixNumbers.length)];
			sb.append(fiveNumbers);
		}
		pinCode=sb.toString();
		return pinCode;
	}

	public static String getMembershipNo(){
		String numbers="1234567890";
		StringBuilder sb = new StringBuilder();
		String membershipNo=null;
		Random random = new Random();
		char[] tempSixNumbers = numbers.toCharArray();
		for (int i = 0; i < 6; i++) {
			char fiveNumbers = tempSixNumbers[random.nextInt(tempSixNumbers.length)];
			sb.append(fiveNumbers);
		}
		membershipNo=sb.toString();
		return membershipNo;
	}

	public static String timeGenerator(){ 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeToBeAdded=sdf.format(cal.getTime());
		return timeToBeAdded;
	}


	public static String timeGeneratorForClaimAdmission(String timeOfIntimation) throws ParseException, Throwable{ 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date time=sdf.parse(timeOfIntimation);
		
		cal.setTime(time);
		cal.add(Calendar.MINUTE,-2);
		String timeToBeAdded=sdf.format(cal.getTime());
		return timeToBeAdded;
	}


	public static String uniqueIdentificationCodeForPolicyNoGenerator(String uniqueCode,String uniqueIdProductCode) {
		String numbers="1234567890";
		String uniqueIdentificationCode=null;
		StringBuilder sb=new StringBuilder();
		sb.append(uniqueCode);
		sb.append(130100);
		sb.append(uniqueIdProductCode);
		sb.append(0);
		char[] tempuniqueIdentificationCodeNumber =numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char uniqueIdentificationCodeGen = tempuniqueIdentificationCodeNumber[random.nextInt(tempuniqueIdentificationCodeNumber.length)];
			sb.append(uniqueIdentificationCodeGen);
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		int currentyear= Integer.parseInt(sdf.format(date));
		int nextyear=currentyear+1;
		sb.append(currentyear);
		sb.append(nextyear);
		uniqueIdentificationCode=sb.toString();
		return uniqueIdentificationCode;
	}


	public static boolean determineLeapYear( int year) {
		if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
			return true;
		else
			return false;
	}
	
	public static String formatNumber(int decimals, double number) {
		StringBuilder sb = new StringBuilder(decimals + 2);
		sb.append("#.");
		for(int i = 0; i < decimals; i++) {
			sb.append("0");
		}
		return new DecimalFormat(sb.toString()).format(number);
	}

	public static String serviceTaxCalculation1(String grossPremium,String CGST,String SGST,String IGST,String UGST,String Cess) throws ParseException { 
		double grossPremiumValue = Double.parseDouble(grossPremium);
		double serviveTax,decimal;
		String CALCCGST,CALCSGST,CALCCESS,CALCIGST,CALCUGST;
		String calculatedServiveTax="0.00";
		double TempCALCCGST=grossPremiumValue*(Double.parseDouble(CGST)/100);
		 CALCCGST= new DecimalFormat("0.00").format(TempCALCCGST);
		String[] convert = CALCCGST.split("\\.");
		int firstCgst = Integer.parseInt(convert[0]);
		int lastCgst = Integer.parseInt(convert[1]);
		if(lastCgst<50){
			CALCCGST = firstCgst+"";
		}else if(lastCgst>50){
			CALCCGST = firstCgst+1+"";
		}
		double TempCALCSGST=grossPremiumValue*(Double.parseDouble(SGST)/100);
		CALCSGST= new DecimalFormat("0.00").format(TempCALCSGST);
		String[] convert1 = CALCSGST.split("\\.");
		int firstSgst = Integer.parseInt(convert1[0]);
		int lastSgst = Integer.parseInt(convert1[1]);
		if(lastSgst<50){
			CALCSGST = firstSgst+"";
		}else if(lastSgst>50){
			CALCSGST = firstSgst+1+"";
		}

		double TempCALCCESS=grossPremiumValue*(Double.parseDouble(Cess)/100);
			CALCCESS= new DecimalFormat("0.00").format(TempCALCCESS);
			String[] convert2 = CALCCESS.split("\\.");
			int firstCess = Integer.parseInt(convert2[0]);
			int lastCess = Integer.parseInt(convert2[1]);
			if(lastCess<50){
				CALCCESS = firstCess+"";
			}else if(lastCess>50){
				CALCCESS = firstCess+1+"";
			}
		double TempCALCIGST=grossPremiumValue*(Double.parseDouble(IGST)/100);
			CALCIGST= new DecimalFormat("0.00").format(TempCALCIGST);
			String[] convert3 = CALCIGST.split("\\.");
			int firstIgst = Integer.parseInt(convert3[0]);
			int lastIgst = Integer.parseInt(convert3[1]);
			if(lastIgst<50){
				CALCIGST = firstIgst+"";
			}else if(lastIgst>50){
				CALCIGST = firstIgst+1+"";

		}
		double TempCALCUGST=grossPremiumValue*(Double.parseDouble(UGST)/100);
			CALCUGST= new DecimalFormat("0.00").format(TempCALCUGST);
			String[] convert4 = CALCUGST.split("\\.");
			int firstUgst = Integer.parseInt(convert4[0]);
			int lastUgst = Integer.parseInt(convert4[1]);
			if(lastUgst<50){
				CALCUGST = firstUgst+"";
			}else if(lastUgst>50){
				CALCUGST = firstUgst+1+"";
			}
		serviveTax=Double.parseDouble(CALCCGST)+Double.parseDouble(CALCSGST)+Double.parseDouble(CALCCESS)+Double.parseDouble(CALCIGST)+Double.parseDouble(CALCUGST);
		serviveTax= Math.round(serviveTax);
		decimal=serviveTax % 1;
		if((decimal*100)<=49){
			calculatedServiveTax = RandomCodeGenerator.formatNumber(2,Math.floor(serviveTax));
		}
		else{
			calculatedServiveTax = RandomCodeGenerator.formatNumber(2,Math.ceil(serviveTax));
		}
		return calculatedServiveTax;
	}

	
	
	public static String serviceTaxCalculation(String grossPremium,String CGST,String SGST,String IGST,String UGST,String Cess) throws ParseException { 
	
	double grossPremiumValue = Double.parseDouble(grossPremium);
	double serviceTax,decimal;
	String CALCCGST,CALCSGST,CALCCESS,CALCIGST,CALCUGST;
	String calculatedServiveTax="0.00";
	double TempCALCCGST=grossPremiumValue*(Double.parseDouble(CGST)/100);
	 CALCCGST= new DecimalFormat("0.00").format(TempCALCCGST);
	String[] convert = CALCCGST.split("\\.");
	int firstCgst = Integer.parseInt(convert[0]);
	int lastCgst = Integer.parseInt(convert[1]);

	double TempCALCSGST=grossPremiumValue*(Double.parseDouble(SGST)/100);
	CALCSGST= new DecimalFormat("0.00").format(TempCALCSGST);
	String[] convert1 = CALCSGST.split("\\.");
	int firstSgst = Integer.parseInt(convert1[0]);
	int lastSgst = Integer.parseInt(convert1[1]);
	
	double TempCALCCESS=grossPremiumValue*(Double.parseDouble(Cess)/100);
		CALCCESS= new DecimalFormat("0.00").format(TempCALCCESS);
		String[] convert2 = CALCCESS.split("\\.");
		int firstCess = Integer.parseInt(convert2[0]);
		int lastCess = Integer.parseInt(convert2[1]);
		
	double TempCALCIGST=grossPremiumValue*(Double.parseDouble(IGST)/100);
		CALCIGST= new DecimalFormat("0.00").format(TempCALCIGST);
		String[] convert3 = CALCIGST.split("\\.");
		int firstIgst = Integer.parseInt(convert3[0]);
		int lastIgst = Integer.parseInt(convert3[1]);
	
	double TempCALCUGST=grossPremiumValue*(Double.parseDouble(UGST)/100);
		CALCUGST= new DecimalFormat("0.00").format(TempCALCUGST);
		String[] convert4 = CALCUGST.split("\\.");
		int firstUgst = Integer.parseInt(convert4[0]);
		int lastUgst = Integer.parseInt(convert4[1]);
		
	serviceTax=Double.parseDouble(CALCCGST)+Double.parseDouble(CALCSGST)+Double.parseDouble(CALCCESS)+Double.parseDouble(CALCIGST)+Double.parseDouble(CALCUGST);
	serviceTax= Math.round(serviceTax);
	decimal=serviceTax % 1;
	if((decimal*100)<=49){
		calculatedServiveTax = RandomCodeGenerator.formatNumber(2,Math.floor(serviceTax));
	}
	else{
		calculatedServiveTax = RandomCodeGenerator.formatNumber(2,Math.ceil(serviceTax));
	}
	
	
	return calculatedServiveTax;
}

}
