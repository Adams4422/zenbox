package corenlp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.PropertiesUtils;

public class MyThread extends Thread{

	public static int i;
	public static int k;
	public static int j;
	public BasicPipelineExample test;
	public static String message;
	public static Socket socket;
	public static NodeJSEcho serveur;
	public static List<String> listverb;
	public static List<String> listnom;
	public static boolean boucle = true;
	public static int demarrer = 0;
	public CoreDocument document;
	public String text;
	public static SelectApp app;
	public StanfordCoreNLP pipeline;


	public MyThread(String text,BasicPipelineExample test) {
		i = 0;
		k = 0;
		j = 0;
		this.text = text;
		this.test = test;
		serveur = new NodeJSEcho();
		try {
			serveur.socketConnect("127.0.0.1", 6969);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties("annotators", "tokenize,ssplit,pos", "coref.algorithm","deterministic"));

		// create a document object
		document = new CoreDocument(text);

		// annnotate the document
		pipeline.annotate(document);
	}

	public void run() {

		listverb = new ArrayList<String>();
		listnom = new ArrayList<String>();

		app = new SelectApp(test);


		//text of the first sentence
		String sentenceText = document.sentences().get(0).text();
		System.out.println("Example: sentence");
		System.out.println(sentenceText);
		System.out.println();
		String[] testsentence = sentenceText.split(" ");

		CoreSentence sentence = document.sentences().get(0);

		// list of the part-of-speech tags for the second sentence
		List<String> posTags = sentence.posTags();

		System.out.println("Example: pos tags");
		System.out.println(posTags);
		System.out.println();

		while (i < testsentence.length) {
			if (posTags.get(i).equals("VBP") || posTags.get(i).equals("VBN") || posTags.get(i).equals("VB") || posTags.get(i).equals("IN")) {

				String verbe = testsentence[i];
				// verbe = "'" + verbe + "'";

				listverb.add(verbe);
				// app.selectAll(verbe);
				i++;
			}
			if (posTags.get(i).equals("NN") || posTags.get(i).equals("NNS") || posTags.get(i).equals("NNP") || posTags.get(i).equals("NNPS")) {

				String nom = testsentence[i];
				// nom = "'" + nom + "'";

				listnom.add(nom);
				// app.selectAll(verbe);
				j++;
				i++;
			} else {
				i++;
			}
		}


		int n = 0;
		int t = 0;
		while (n != listnom.size()) {
			if (listnom.get(n).equals("light") || listnom.get(n).equals("lamp") || listnom.get(n).equals("beacon") || listnom.get(n).equals("illuminant")) {
				while (t != listverb.size()) {
					app.selectAll(listverb.get(t));
					t++;
				}
				n++;
			} else {
				n++;
			}
		}

		message = app.getName();

		serveur.echo(message);

	
	}


}
