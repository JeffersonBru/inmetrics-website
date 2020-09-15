package suporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LeitorProperties {
	
	private File arquivo;
	
	public LeitorProperties(String arq){
		arquivo = new File(arq);
	}
	/**
	 * Método para obter properties do arquivo
	 */
	public Properties getProperties() {
		Properties props = new Properties();
		try{
			FileInputStream fis = new FileInputStream(arquivo.getAbsolutePath());
			props.load(fis);
			fis.close();
		}catch(IOException e){
			System.out.println(e);
		}
		return props;
	}
}
