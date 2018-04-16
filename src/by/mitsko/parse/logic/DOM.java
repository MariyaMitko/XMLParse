package by.mitsko.parse.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import by.mitsko.parse.bean.Book;
import by.mitsko.parse.bean.Magazine;
import by.mitsko.parse.bean.Newspaper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.mitsko.parse.bean.Book;

public class DOM {
	private Set<Book> book;
	private Set<Newspaper> newspaper;
	private Set<Magazine> magazine;
	private DocumentBuilder docBuilder;
	
	public DOM(){
	this.book = new HashSet <Book>();
	this.newspaper = new HashSet <Newspaper>();
	this.magazine = new HashSet <Magazine>();
	
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	try {
		docBuilder = factory.newDocumentBuilder();
	} catch (ParserConfigurationException e) {
		System.err.println("Ошибка конфигурации парсера: " + e);
	}
	}

	public  Set<Book> getBook() {
		return book;
	}
	
	
	public Set<Newspaper> getNewspaper() {
		return newspaper;
	}

	public Set<Magazine> getMagazine() {
		return magazine;
	}

	public void buildSetBooks(String fileName) {
		Document document = null;

		try {
			document = docBuilder.parse("resources/library.xml");
			} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);e.printStackTrace();
		}
		Element root = document.getDocumentElement();
		List<Book> catalog1 = new ArrayList<Book>();
		NodeList bookNodes = root.getElementsByTagName("book");
		Book book = null;
		for (int i = 0; i < bookNodes.getLength(); i++) {
			book = new Book();
			Element bookElement = (Element) bookNodes.item(i);

			book.setId((bookElement.getAttribute("id")));
			book.setTitle(getSingleChild(bookElement, "title").getTextContent().trim());
			book.setAvailability(getSingleChild(bookElement, "availability").getTextContent().trim());
			book.setAuthor(getSingleChild(bookElement, "author").getTextContent().trim());
			book.setYear(getSingleChild(bookElement, "year").getTextContent().trim());
			catalog1.add(book);
		}

		for (Book b : catalog1) {
			System.out.println(b.getId()+ ", " +  b.getTitle() + ", " +b.getAvailability()+ ", " + b.getAuthor()+ ", " + b.getYear());
		}
	}
	public void buildSetNewspapers(String fileName) {
		Document document = null;

		try {
			document = docBuilder.parse("resources/library.xml");
			} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);e.printStackTrace();
		}
		Element root = document.getDocumentElement();
		List<Newspaper> catalog2 = new ArrayList<Newspaper>();
		NodeList newspaperNodes = root.getElementsByTagName("newspaper");
		Newspaper newspaper = null;
		for (int i = 0; i < newspaperNodes.getLength(); i++) {
			newspaper = new Newspaper();
			Element newspaperElement = (Element) newspaperNodes.item(i);

			newspaper.setId((newspaperElement.getAttribute("id")));
			newspaper.setTitle(getSingleChild(newspaperElement, "title").getTextContent().trim());
			newspaper.setAvailability(getSingleChild(newspaperElement, "availability").getTextContent().trim());
			newspaper.setDate(getSingleChild(newspaperElement, "date").getTextContent().trim());
			newspaper.setDay(getSingleChild(newspaperElement, "day").getTextContent().trim());
			catalog2.add(newspaper);
		}

		for (Newspaper n : catalog2) {
			System.out.println(n.getId()+ ", " +n.getTitle() + ", " +n.getAvailability()+ ", " + n.getDate()+ ", " + n.getDay());
		}
	}
	
	public void buildSetMagazines(String fileName) {
		Document document = null;

		try {
			document = docBuilder.parse("resources/library.xml");
			} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);e.printStackTrace();
		}
		Element root = document.getDocumentElement();
		List<Magazine> catalog3 = new ArrayList<Magazine>();
		NodeList magazineNodes = root.getElementsByTagName("magazine");
		Magazine magazine = null;
		for (int i = 0; i < magazineNodes.getLength(); i++) {
			magazine = new Magazine();
			Element magazineElement = (Element) magazineNodes.item(i);

			magazine.setId((magazineElement.getAttribute("id")));
			magazine.setTitle(getSingleChild(magazineElement, "title").getTextContent().trim());
			magazine.setAvailability(getSingleChild(magazineElement, "availability").getTextContent().trim());
			magazine.setDate(getSingleChild(magazineElement, "date").getTextContent().trim());
			magazine.setVolume(getSingleChild(magazineElement, "volume").getTextContent().trim());
			catalog3.add(magazine);
		}

		for (Magazine m : catalog3) {
			System.out.println(m.getId()+ ", " +m.getTitle() + ", " + m.getAvailability()+ ", " + m.getDate()+ ", " + m.getVolume());
		}
	}
	private static Element getSingleChild(Element element, String childName) {
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}
}