
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a “de facto” standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    Console console;
    Game game;
    Tablet tablet;
    Laptop laptop;
    Speaker speaker;
    Phone phone;
    Tracker tracker;
    FitnessWatch fitnesswatch;
    SmartWatch smartwatch;
    HeadPhone headphone;
    VirtualReality virtualreality;
    Accessory accessory;
    static HashMap<String,Console> consoles;
    static HashMap<String,Game> games;
    static HashMap<String,Tablet> tablets;
    static HashMap<String,Laptop> laptops;
    static HashMap<String,Speaker> speakers;
    static HashMap<String,Phone> phones;
    static HashMap<String,Tracker> trackers;
    static HashMap<String,FitnessWatch> fitnesswatches;
    static HashMap<String,SmartWatch> smartwatches;
    static HashMap<String,HeadPhone> headphones;
    static HashMap<String,VirtualReality> virtualrealities;
    static HashMap<String,Accessory> accessories;
    String consoleXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
    consoles = new HashMap<String, Console>();
	games=new  HashMap<String, Game>();
	tablets=new HashMap<String, Tablet>();
	laptops=new HashMap<String, Laptop>();
	speakers=new HashMap<String, Speaker>();
	phones=new HashMap<String, Phone>();
	trackers=new HashMap<String, Tracker>();
	fitnesswatches=new HashMap<String, FitnessWatch>();
	smartwatches=new HashMap<String, SmartWatch>();
	headphones=new HashMap<String, HeadPhone>();
	virtualrealities=new HashMap<String, VirtualReality>();
	accessories=new HashMap<String, Accessory>();
	accessoryHashMap=new HashMap<String,String>();
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("console")) 
		{
			currentElement="console";
			console = new Console();
            console.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("tablet"))
		{
			currentElement="tablet";
			tablet = new Tablet();
            tablet.setId(attributes.getValue("id"));
        }        

        if (elementName.equalsIgnoreCase("laptop"))
		{
			currentElement="laptop";
			laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
        }

        if (elementName.equalsIgnoreCase("speaker"))
		{
			currentElement="speaker";
			speaker = new Speaker();
            speaker.setId(attributes.getValue("id"));
        }

        if (elementName.equalsIgnoreCase("phone"))
		{
			currentElement="phone";
			phone = new Phone();
            phone.setId(attributes.getValue("id"));
        }

        if (elementName.equalsIgnoreCase("tracker"))
		{
			currentElement="tracker";
			tracker = new Tracker();
            tracker.setId(attributes.getValue("id"));
        }

        if (elementName.equalsIgnoreCase("fitnesswatch"))
		{
			currentElement="fitnesswatch";
			fitnesswatch = new FitnessWatch();
            fitnesswatch.setId(attributes.getValue("id"));
        }

        if (elementName.equalsIgnoreCase("smartwatch"))
		{
			currentElement="smartwatch";
			smartwatch = new SmartWatch();
            smartwatch.setId(attributes.getValue("id"));
        }

        if (elementName.equalsIgnoreCase("headphone"))
		{
			currentElement="headphone";
			headphone = new HeadPhone();
            headphone.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("virtualreality"))
		{
			currentElement="virtualreality";
			virtualreality = new VirtualReality();
            virtualreality.setId(attributes.getValue("id"));
        }

        if (elementName.equalsIgnoreCase("game"))
		{
			currentElement="game";
			game= new Game();
            game.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory") &&  !currentElement.equals("laptop") )
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }


    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("console")) {
			consoles.put(console.getId(),console);
			return;
        }
 
        if (element.equals("tablet")) {	
			tablets.put(tablet.getId(),tablet);
			return;
        }
        if (element.equals("laptop")) {	
			laptops.put(laptop.getId(),laptop);
			return;
        }
        if (element.equals("speaker")) {	
			speakers.put(speaker.getId(),speaker);
			return;
        }
        if (element.equals("phone")) {	
			phones.put(phone.getId(),phone);
			return;
        }
        if (element.equals("tracker")) {	
			trackers.put(tracker.getId(),tracker);
			return;
        }

        if (element.equals("fitnesswatch")) {	
			fitnesswatches.put(fitnesswatch.getId(),fitnesswatch);
			return;
        }
        if (element.equals("smartwatch")) {	
			smartwatches.put(smartwatch.getId(),smartwatch);
			return;
        }
        if (element.equals("headphone")) {	
			headphones.put(headphone.getId(),headphone);
			return;
        }
        if (element.equals("virtualreality")) {	
			virtualrealities.put(virtualreality.getId(),virtualreality);
			return;
        }
        if (element.equals("game")) {	  
			games.put(game.getId(),game);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
		if (element.equals("accessory") && currentElement.equals("laptop")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("laptop")) {
			laptop.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("console"))
				console.setImage(elementValueRead);
        	if(currentElement.equals("game"))
				game.setImage(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setImage(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setImage(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setImage(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setImage(elementValueRead);
			if(currentElement.equals("tracker"))
				tracker.setImage(elementValueRead);
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setImage(elementValueRead);
			if(currentElement.equals("smartwatch"))
				smartwatch.setImage(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setImage(elementValueRead);
			if(currentElement.equals("virtualreality"))
				virtualreality.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("console"))
				console.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("game"))
				game.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("tablet"))
				tablet.setDiscount(Double.parseDouble(elementValueRead));
			 if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("speaker"))
				speaker.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("tracker"))
				tracker.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartwatch"))
				smartwatch.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("headphone"))
				headphone.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("virtualreality"))
				virtualreality.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("console"))
				console.setCondition(elementValueRead);
        	if(currentElement.equals("game"))
				game.setCondition(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setCondition(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setCondition(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setCondition(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setCondition(elementValueRead);
			if(currentElement.equals("tracker"))
				tracker.setCondition(elementValueRead);
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setCondition(elementValueRead);
			if(currentElement.equals("smartwatch"))
				smartwatch.setCondition(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setCondition(elementValueRead);
			if(currentElement.equals("virtualreality"))
				virtualreality.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("console"))
				console.setRetailer(elementValueRead);
        	if(currentElement.equals("game"))
				game.setRetailer(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setRetailer(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setRetailer(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setRetailer(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setRetailer(elementValueRead);
			if(currentElement.equals("tracker"))
				tracker.setRetailer(elementValueRead);
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setRetailer(elementValueRead);
			if(currentElement.equals("smartwatch"))
				smartwatch.setRetailer(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setRetailer(elementValueRead);
			if(currentElement.equals("virtualreality"))
				virtualreality.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("console"))
				console.setName(elementValueRead);
        	if(currentElement.equals("game"))
				game.setName(elementValueRead);
            if(currentElement.equals("tablet"))
				tablet.setName(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setName(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setName(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setName(elementValueRead);
			if(currentElement.equals("tracker"))
				tracker.setName(elementValueRead);
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setName(elementValueRead);
			if(currentElement.equals("smartwatch"))
				smartwatch.setName(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setName(elementValueRead);
			if(currentElement.equals("virtualreality"))
				virtualreality.setName(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("console"))
				console.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("game"))
				game.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("tablet"))
				tablet.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("speaker"))
				speaker.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("phone"))
				phone.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("tracker"))
				tracker.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartwatch"))
				smartwatch.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("headphone"))
				headphone.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("virtualreality"))
				virtualreality.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
			return;
        }

         if(element.equalsIgnoreCase("quantity")){
         	if(currentElement.equals("laptop"))
				laptop.setQuantity(Double.parseDouble(elementValueRead));
			if(currentElement.equals("speaker"))
				speaker.setQuantity(Double.parseDouble(elementValueRead));
			if(currentElement.equals("phone"))
				phone.setQuantity(Double.parseDouble(elementValueRead));
			if(currentElement.equals("tracker"))
				tracker.setQuantity(Double.parseDouble(elementValueRead));
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setQuantity(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartwatch"))
				smartwatch.setQuantity(Double.parseDouble(elementValueRead));
			if(currentElement.equals("headphone"))
				headphone.setQuantity(Double.parseDouble(elementValueRead));
			if(currentElement.equals("virtualreality"))
				virtualreality.setQuantity(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setQuantity(Double.parseDouble(elementValueRead));          
			return;
         }

         if (element.equalsIgnoreCase("manufacturerRebate")) {
			if(currentElement.equals("laptop"))
				laptop.setManufacturerRebate(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setManufacturerRebate(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setManufacturerRebate(elementValueRead);
			if(currentElement.equals("tracker"))
				tracker.setManufacturerRebate(elementValueRead);
			if(currentElement.equals("fitnesswatch"))
				fitnesswatch.setManufacturerRebate(elementValueRead);
			if(currentElement.equals("smartwatch"))
				smartwatch.setManufacturerRebate(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setManufacturerRebate(elementValueRead);
			if(currentElement.equals("virtualreality"))
				virtualreality.setManufacturerRebate(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setManufacturerRebate(elementValueRead);          
			return;
	    }


	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Tutorial_1\\ProductCatalog.xml");
    } 
}
