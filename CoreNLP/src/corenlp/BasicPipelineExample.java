package corenlp;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import corenlp.JSON;

@Path("CoreNLP")

public class BasicPipelineExample {

	public static BasicPipelineExample test;
	public static String text;
	public static boolean boucle = true;
	public static MyThread t;
	public static int demarrer = 0;
	static JSON commande = new JSON("");
	private final static String ORDRE = "ordre";

	public static void main(String[] args) throws UnknownHostException, IOException {
		test = new BasicPipelineExample();
	}
	
	@Path("Commande")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSON PostJSON(HashMap<String, String> switchParams) { 
		
			String ordre = switchParams.get(ORDRE);
			
			if (ordre == null) {
				commande.setText(commande.getText());
			}
			else{
				commande.setText(ordre);
			}
			
			t = new MyThread(commande.getText(), test);
			t.start();
			return commande;
}
	
	
	public static String getText() {
		return commande.getText();
	}
	
	public BasicPipelineExample getBasic() {
		return test;
	}
}
