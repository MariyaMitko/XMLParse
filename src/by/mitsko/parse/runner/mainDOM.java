package by.mitsko.parse.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import by.mitsko.parse.bean.Book;
import by.mitsko.parse.logic.DOM;

public class mainDOM {

	public static void main(String[] args) {
		DOM domBuilder = new DOM();
		domBuilder.buildSetBooks("resources/library.xml");
		domBuilder.buildSetNewspapers("resources/library.xml");
		domBuilder.buildSetMagazines("resources/library.xml");
		System.out.println(domBuilder.getBook());
		System.out.println(domBuilder.getNewspaper());
		System.out.println(domBuilder.getMagazine());
	}

}
