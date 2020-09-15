package funcionario.type;

import java.util.Random;


public enum Sexo {
	
	INDIFERENTE("Indiferente"),
	MASCULINO("Masculino"),
	FEMININO("Feminino");

	private String value;
	
	private Sexo(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	 /**
	  * Obter categoria de forma randomizada
	  * @return
	  */
	 public static Sexo getRandom() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
	 }
	
}
