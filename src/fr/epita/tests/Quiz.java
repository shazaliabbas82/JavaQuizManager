/**
 * 
 */
package fr.epita.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.epita.datamodel.*;
import fr.epita.services.dao.QuestionXMLDAO;

/**
 * @author Persistence
 *
 */
public class Quiz {

	private static String user = "";
	private static int score = 0;
	private static int type = 0;
	private static Scanner scan;
	private static Document doc;
	private static QuestionXMLDAO dao;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialisation 
		scan = new Scanner(System.in);
		dao = new QuestionXMLDAO();
		welcomeMenu();

	}

	public static void welcomeMenu() {
		System.out.println("__________" + "QUIZ" + "__________");
		System.out.print("Enter your name Please:");
		user = scan.nextLine();
		System.out.println("Select the quiz type by inputing a number:");
		System.out.println("1-Quiz By topics");
		System.out.println("2-Normal quiz");
		try {
			List<Question> questions = new ArrayList<Question>();
			type = scan.nextInt();
			scan.nextLine();
			if (type == 1) {
				questions = buildByTopic();
			} else {
				questions = buildNormal();
			}
			for (int i = 0; i < questions.size(); i++) {
				showQuestion(i, questions.get(i));
				System.out.println("");
			}
		
			System.out.println("_______________" + "END" + "________________");
			System.out.println(user + " Your Score is " + score + " out of " + questions.size() + " questions");
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void showQuestion(int index, Question question) {
		System.out.println((index + 1) + "-" + question.getQuestion());
		if (!question.getChoices().isEmpty()) {
			System.out.println("Your choice: ");
			System.out.println("");
			for (int i = 0; i < question.getChoices().size(); i++) {
				System.out.println("-->" + (i + 1) + "-" + question.getChoices().get(i).getChoice());
			}
			int choice = scan.nextInt();
			scan.nextLine();
			if (question.getChoices().get(choice - 1).isValid()) {
				score++;
			}
		} else {
			System.out.print("Input your answer via Keyboard: ");
			String reponse = scan.nextLine();
			if (question.getAnswer() != null && question.getAnswer().getText().equalsIgnoreCase(reponse)) {
				score++;
			}
		}
	}

	public static void loadFile() throws SAXException {
		try {
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			
			// parsing  XML file using builder
			builder = fact.newDocumentBuilder();
			doc = builder.parse(new File("questions.xml"));
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Question> buildNormal()
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		List<Question> questions = new ArrayList<Question>();
		questions = dao.getAllQuestions();
		return questions;
	}

	public static List<Question> buildByTopic() throws ParserConfigurationException, SAXException, IOException {
		List<Question> questions = new ArrayList<Question>();
		System.out.println("Select the topic please:");
		List<String> topics = dao.getAllTopics();
		for (int i = 0; i < topics.size(); i++) {
			System.out.println((i + 1) + "-" + topics.get(i));
		}
		int sout = scan.nextInt();
		questions = dao.getAllQuestionsByTopic(topics.get(sout-1));
		
		// System.out.println("Questions " + questions);
		
		return questions;
	}

}
