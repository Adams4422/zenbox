package corenlp;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

public class BasicPipelineExample {

	public static BasicPipelineExample test;
	public static String text;
	public static boolean boucle = true;
	public static MyThread t;
	public static int demarrer = 0;

	public static void main(String[] args) throws UnknownHostException, IOException {
		test = new BasicPipelineExample();

		System.out.println("Write your sentence : ");

		while (boucle == true) {
			Scanner sc = new Scanner(System.in);
			String lasttext = getText();
			text = sc.nextLine();
			if (text != lasttext) {
				t = new MyThread(text, test);
				t.start();
				}		
		}

	}
	
	public static String getText() {
		return text;
	}
	
	public BasicPipelineExample getBasic() {
		return test;
	}
}
