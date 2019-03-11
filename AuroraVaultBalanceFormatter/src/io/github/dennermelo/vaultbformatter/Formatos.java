package io.github.dennermelo.vaultbformatter;

import java.text.DecimalFormat;

public class Formatos {
	  
	  public static String decilhoes(double number)
	  {
	    DecimalFormat decilhoes = new DecimalFormat("0.0D");
	    String num7 = decilhoes.format(number / 1.0E33D);
	    return num7;
	  }
	  
	  public static String nonilhoes(double number)
	  {
	    DecimalFormat nonilhoes = new DecimalFormat("0.0N");
	    String num6 = nonilhoes.format(number / 1.0E30D);
	    return num6;
	  }
	  
	  public static String octilhoes(double number)
	  {
	    DecimalFormat octilhoes = new DecimalFormat("0.0O");
	    String num5 = octilhoes.format(number / 1.0E27D);
	    return num5;
	  }
	  
	  public static String septilhoes(double number)
	  {
	    DecimalFormat septilhoes = new DecimalFormat("0.0SS");
	    String num4 = septilhoes.format(number / 1.0E24D);
	    return num4;
	  }
	  
	  public static String sextilhoes(double number)
	  {
	    DecimalFormat sextilhoes = new DecimalFormat("0.0S");
	    String num3 = sextilhoes.format(number / 1.0E21D);
	    return num3;
	  }
	  
	  public static String quintilhoes(double number)
	  {
	    DecimalFormat quintilhoes = new DecimalFormat("0.0QQ");
	    String num2 = quintilhoes.format(number / 1.0E18D);
	    return num2;
	  }
	  
	  public static String quadrilhoes(double number)
	  {
	    DecimalFormat quadrilhoes = new DecimalFormat("0.0Q");
	    String num1 = quadrilhoes.format(number / 1.0E15D);
	    return num1;
	  }
	  
	  public static String trilhoes(double number)
	  {
	    DecimalFormat trilhoes = new DecimalFormat("0.0T");
	    String num = trilhoes.format(number / 1.0E12D);
	    return num;
	  }
	  
	  public static String bilhoes(double number)
	  {
	    DecimalFormat bilhoes = new DecimalFormat("0.0B");
	    String numb = bilhoes.format(number / 1.0E9D);
	    return numb;
	  }
	  
	  public static String milhoes(double number)
	  {
	    DecimalFormat milhoes = new DecimalFormat("0.0M");
	    String amount = milhoes.format(number / 1000000.0D);
	    return amount;
	  }
	  
	  public static String milhares(double amount)
	  {
	    DecimalFormat milhares = new DecimalFormat("#,###");
	    String numero = milhares.format(amount);
	    return numero;
	  }
	  
	  public static String centenas(double amount)
	  {
	    DecimalFormat formatter = new DecimalFormat("###.00");
	    String number = formatter.format(amount);
	    return number;
	  }

}
