package by.mitsko.parse.runner;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.mitsko.parse.bean.Book;
import by.mitsko.parse.bean.Magazine;
import by.mitsko.parse.bean.Newspaper;
import by.mitsko.parse.logic.SaxHandler;


public class mainSax {

	public static void main(String[] args) throws ParserConfigurationException,SAXException, IOException {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		SaxHandler handler = new SaxHandler();
		reader.setContentHandler(handler);
		reader.parse(new InputSource("resources/library.xml"));
		
		reader.setFeature("http://xml.org/sax/features/validation", true);
		
		reader.setFeature("http://xml.org/sax/features/namespaces", true);
		
		reader.setFeature("http://xml.org/sax/features/string-interning", true);
		
		reader.setFeature("http://apache.org/xml/features/validation/schema", false);
		
		List<Book> catalog1 = handler.getBookList();
		List<Newspaper> catalog2 = handler.getNewspaperList();
		List<Magazine> catalog3 = handler.getMagazineList();
		
		for (Book b : catalog1) {
		System.out.println(b.getId()+ ", " + b.getTitle() + ", " +b.getAvailability()+ ", " + b.getAuthor()+ ", " + b.getYear());
		}
		
		for (Newspaper n : catalog2) {
			System.out.println(n.getId()+ ", " +n.getTitle() + ", " +n.getAvailability()+ ", " + n.getDate()+ ", " + n.getDay());
			}
		
		for (Magazine m : catalog3) {
			System.out.println(m.getId()+ ", " +m.getTitle() + ", " + m.getAvailability()+ ", " + m.getDate()+ ", " + m.getVolume());
			}
		
		
		}

}
