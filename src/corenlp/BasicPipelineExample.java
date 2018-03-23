package corenlp;

import edu.stanford.nlp.coref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import edu.stanford.nlp.util.TypesafeMap;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class BasicPipelineExample {

	public static int i = 0;
	public static int k = 0;
	public static BasicPipelineExample test;

	public static String [] tabverb;


	public static void main(String[] args) {
		test = new BasicPipelineExample();
		SelectApp app = new SelectApp(test);

		System.out.println("Write your sentence : ");
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();


		String [] testsentence = text.split(" ");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(
				PropertiesUtils.asProperties(
						"annotators", "tokenize,ssplit,pos,lemma,parse,natlog",
						"ssplit.isOneSentence", "true",
						"parse.model", "edu/stanford/nlp/models/srparser/englishSR.ser.gz",
						"tokenize.language", "en"));



		// create a document object
		CoreDocument document = new CoreDocument(text);
		// annnotate the document
		pipeline.annotate(document);
		// examples

		// 10th token of the document
		//		CoreLabel token = document.tokens().get(6);
		//		System.out.println("Example: token");
		//		System.out.println(token);
		//		System.out.println();

		// text of the first sentence
		String sentenceText = document.sentences().get(0).text();
		System.out.println("Example: sentence");
		System.out.println(sentenceText);
		System.out.println();

		// second sentence
		CoreSentence sentence = document.sentences().get(0);
		tabverb = new String[testsentence.length];

		// list of the part-of-speech tags for the second sentence
		List<String> posTags = sentence.posTags();

		System.out.println("Example: pos tags");
		System.out.println(posTags);
		System.out.println();

		while (i<testsentence.length) {
			if (posTags.get(i).equals("VBP") ||  posTags.get(i).equals("VBN") || posTags.get(i).equals("VB") || posTags.get(i).equals("IN")){
				//System.out.println(testsentence[i] + " est un " + posTags.get(i) + "\n");
				
				String verbe = testsentence[i];
				verbe = "'" + verbe + "'";
				//tabverb[k] = verbe;
				app.selectAll(verbe);
				//k++;
				i++;
			}
			else {
				//System.out.println("Ce verbe ne correspond à rien");
				i++;}
		}
//		SelectApp app = new SelectApp(test);
//	
//		
//		int j = 0;
//		while(j!=tabverb.length) {
//			//System.out.println(tabverb[j]);
//			app.selectAll(tabverb[j]);
//			j++;
//		}
//		// constituency parse for the second sentence
//		Tree constituencyParse = sentence.constituencyParse();
//		System.out.println("Example: constituency parse");
//		System.out.println(constituencyParse);
//		System.out.println();
//
//		// dependency parse for the second sentence
//		SemanticGraph dependencyParse = sentence.dependencyParse();
//		System.out.println("Example: dependency parse");
//		System.out.println(dependencyParse);
//		System.out.println();



		//    // kbp relations found in fifth sentence
		//    List<RelationTriple> relations =
		//        document.sentences().get(0).relations();
		//    System.out.println("Example: relation");
		//    System.out.println(relations);
		//    System.out.println();
		//
		//    // entity mentions in the second sentence
		//    List<CoreEntityMention> entityMentions = sentence.entityMentions();
		//    System.out.println("Example: entity mentions");
		//    System.out.println(entityMentions);
		//    System.out.println();
		//
		//    // coreference between entity mentions
		////    CoreEntityMention originalEntityMention = document.sentences().get(1).entityMentions().get(0);
		////    System.out.println("Example: original entity mention");
		////    System.out.println(originalEntityMention);
		////    System.out.println("Example: canonical entity mention");
		////    System.out.println(originalEntityMention.canonicalEntityMention().get());
		////    System.out.println();
		//
		//    // get document wide coref info
		//    Map<Integer, CorefChain> corefChains = document.corefChains();
		//    System.out.println("Example: coref chains for document");
		//    System.out.println(corefChains);
		//    System.out.println();
		//
		//    // get quotes in document
		//    List<CoreQuote> quotes = document.quotes();
		//    CoreQuote quote = quotes.get(0);
		//    System.out.println("Example: quote");
		//    System.out.println(quote);
		//    System.out.println();
		//
		//    // original speaker of quote
		//    // note that quote.speaker() returns an Optional
		//    System.out.println("Example: original speaker of quote");
		//    System.out.println(quote.speaker().get());
		//    System.out.println();
		//
		//    // canonical speaker of quote
		//    System.out.println("Example: canonical speaker of quote");
		//    System.out.println(quote.canonicalSpeaker().get());
		//    System.out.println();

	}

	//	 private Connection connect() {
	//	        // SQLite connection string
	//	    	
	//	        try {
	//	           
	//	        } catch (SQLException e) {
	//	            System.out.println(e.getMessage());
	//	        }
	//	        return conn;
	//	    }

	public String[] getTab() {
		return tabverb;
	}


}



