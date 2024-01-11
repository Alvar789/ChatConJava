package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class BuscaIp {
	public ArrayList<String> buscadorIp() {
		Runtime tiempoEjecucion = Runtime.getRuntime();
		ArrayList<String> texto = new ArrayList<>();
		try {
			String linea = "";
			Process proceso = tiempoEjecucion.exec("arp -a");
			InputStreamReader isr = new InputStreamReader(proceso.getInputStream(), Charset.forName("IBM00858"));
			BufferedReader br = new BufferedReader(isr);
			while((linea = br.readLine()) != null) {
				if(linea.contains("10.10.1.") && !linea.contains("Interfaz")) {
					texto.add(linea.substring(0, 13) + "\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return texto;
	}
}
