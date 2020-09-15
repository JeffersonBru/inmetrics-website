package funcionario.type;

import java.util.Random;

public enum TipoContratacao {
	
	CLT,
	CNPJ;
	
	 /**
	  * Obter categoria de forma randomizada
	  * @return
	  */
	 public static TipoContratacao getRandom() {
       Random random = new Random();
       return values()[random.nextInt(values().length)];
	 }

}
