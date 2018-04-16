package by.mitsko.parse.logic;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import by.mitsko.parse.bean.Book;
import by.mitsko.parse.bean.Magazine;
import by.mitsko.parse.bean.Newspaper;
import by.mitsko.parse.bean.TagName;


public class SaxHandler  extends DefaultHandler {
	private List<Book> bookList = new ArrayList<Book>();
	private Book book;
	private List<Newspaper> newspaperList = new ArrayList<Newspaper>();
	private Newspaper newspaper;
	private List<Magazine> magazineList = new ArrayList<Magazine>();
	private Magazine magazine;
	private StringBuilder text;
	
	public List<Book> getBookList() {
		return bookList;
	}

	public List<Newspaper> getNewspaperList() {
		return newspaperList;
	}

	public List<Magazine> getMagazineList() {
		return magazineList;
	}

	public void startDocument() throws SAXException {
		System.out.println("Parsing started.");
	}

	public void endDocument() throws SAXException {
		System.out.println("Parsing ended.");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("startElement -> " + "uri: " + uri + ", localName:" + " " + localName + ", qName: " + qName);
		
		text = new StringBuilder();
		if (qName.equals("book")) {
			book = new Book();
			book.setId((attributes.getValue("id")));
		}else if(qName.equals("newspaper")){
			newspaper = new Newspaper();
			newspaper.setId((attributes.getValue("id")));
		}else if(qName.equals("magazine")){
			magazine = new Magazine();
			magazine.setId((attributes.getValue("id")));
		}
	}
	public void characters(char[] buffer, int start, int length) {
		text.append(buffer, start, length);
	}
	
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		TagName tag = TagName.valueOf(qName.toUpperCase().replace("-", "_"));
		switch (tag) {
		case BOOK:
			endBook(uri, localName, qName);
			bookList.add(book);
			book = null;
			break;
		case NEWSPAPER:
			endNewspaper(uri, localName, qName);
			newspaperList.add(newspaper);
			newspaper = null;
			break;
		case MAGAZINE:
			endMagazine(uri, localName, qName);
			magazineList.add(magazine);
			magazine = null;
			break;
			}
		}
	public void endBook(String uri, String localName, String qName)
			throws SAXException {
		TagName tag = TagName.valueOf(qName.toUpperCase().replace("-", "_"));
		switch (tag) {
		case TITLE:
			book.setTitle(text.toString());
			break;
		case AVAILABILITY:
			book.setAvailability(text.toString());
			break;
		case AUTHOR:
			book.setAuthor(text.toString());
			break;
		case YEAR:
			book.setYear(text.toString());
			break;
	}
	}
	public void endNewspaper(String uri, String localName, String qName)
			throws SAXException {
		TagName tag = TagName.valueOf(qName.toUpperCase().replace("-", "_"));
		switch (tag) {
		case TITLE:
			newspaper.setTitle(text.toString());
			break;
		case AVAILABILITY:
			newspaper.setAvailability(text.toString());
			break;
		case DATE:
			newspaper.setDate(text.toString());
			break;
		case DAY:
			newspaper.setDay(text.toString());
			break;
}
	}
	public void endMagazine(String uri, String localName, String qName)
			throws SAXException {
		TagName tag = TagName.valueOf(qName.toUpperCase().replace("-", "_"));
		switch (tag) {
		case TITLE:
			magazine.setTitle(text.toString());
			break;
		case AVAILABILITY:
			magazine.setAvailability(text.toString());
			break;
		case DATE:
			magazine.setDate(text.toString());
			break;
		case VOLUME:
			magazine.setVolume(text.toString());
			break;
}
	}
	public void warning(SAXParseException exception) {
		System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
	}

	public void error(SAXParseException exception) {
		System.err.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		System.err.println("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
		throw (exception);
	}
}


	