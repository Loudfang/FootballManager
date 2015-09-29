package mechanicsspel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReaderWriter {
	
	/**
	 * Leest een xml file in aan de hand van de path naar de xml file
	 * returned daarna xml file als document.
	 */
	public static Document read(String path) throws Exception{
		File XmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(XmlFile);
		return doc;
	}
	
	/**
	 * Aan de hand van een meegegeven document wordt een competitie gecreerd.
	 * Er wordt gekeken naar het id van een teamname aan de hand daarvan wordt 
	 * een team aangemaakt. Per team wordt er eerst een leeg team aangemaakt
	 * daarna wordt de functie createteam aangeroepen en dan wordt het team
	 * aan de competitie toegevoegd. 
	 */
	public static Competitie createCompetitie(Document doc){	
		NodeList pList = doc.getElementsByTagName("teamname");
		Competitie comp = new Competitie();
		for(int i=0; i<pList.getLength(); i++){		
			Node pNode = pList.item(i);
			if(pNode.getNodeType() == Node.ELEMENT_NODE){
				Element pElement = (Element) pNode;
				String temp = pElement.getAttribute("id");
				int p = Integer.parseInt(pElement.getAttribute("p"));
				int d = Integer.parseInt(pElement.getAttribute("d"));
				int w = Integer.parseInt(pElement.getAttribute("w"));
				int v = Integer.parseInt(pElement.getAttribute("v"));
				int g = Integer.parseInt(pElement.getAttribute("g"));
				int dv = Integer.parseInt(pElement.getAttribute("dv"));

				Team team = new Team(temp,p,d,w,v,g,dv);
				createTeam(pNode,team);
				comp.voegTeamToe(team);						
			}
		}
		return comp;
	}	
	
	public static Speelschema createSpeelschema(Document doc, Competitie comp){	
		NodeList pList = doc.getElementsByTagName("speelronde");
		Speelschema schema = new Speelschema();
		for(int i=0; i<pList.getLength(); i++){		
			Node pNode = pList.item(i);
			if(pNode.getNodeType() == Node.ELEMENT_NODE){
				Element pElement = (Element) pNode;
				int nummer = Integer.parseInt(pElement.getAttribute("id"));
				Speelronde speelronde = new Speelronde(nummer);
				NodeList sList = pNode.getChildNodes();
				for(int j = 0; j < sList.getLength(); j++){
					Node sNode = sList.item(j);
					if (sNode.getNodeType() == Node.ELEMENT_NODE){
						Element sElement = (Element) sNode;
						
						String temp = sElement.getTextContent();
						String uittekst[] = temp.split("-");
						speelronde.addWedstrijd(new Wedstrijd(comp.getTeam(uittekst[0]), comp.getTeam(uittekst[1])));
					}
				}
				schema.add(speelronde);
				
										
			}
		}
		return schema;
	}	
	
	
	/**
	 * Bij het aanroepen van de functie wordt de parentnode een een leeg
	 * team meegegeven. Van de parentnode worden alle childnodes(oftewel de spelers)
	 * aangemaakt. De verschillende attributen van een speler worden ingelezen en
	 * aan de hand hiervan wordt de speler gemaakt en daarna aan het desbetreffende
	 * team toegevoegd.
	 */
	public static void createTeam(Node pNode,Team tm){
		NodeList nList = pNode.getChildNodes();		
		for(int j=0; j<nList.getLength(); j++){
			Node nNode = nList.item(j);
			if (nNode.getNodeType() == Node.ELEMENT_NODE){				
				Element nElement = (Element) nNode;
				
				String nm = nElement.getElementsByTagName("naam").item(0).getTextContent();
				int pr = Integer.parseInt(nElement.getElementsByTagName("prijs").item(0).getTextContent());
				String tp = nElement.getElementsByTagName("type").item(0).getTextContent();
				int off = Integer.parseInt(nElement.getElementsByTagName("offensief").item(0).getTextContent());
				int def = Integer.parseInt(nElement.getElementsByTagName("defensief").item(0).getTextContent());
				int uit = Integer.parseInt(nElement.getElementsByTagName("uithoudingsvermogen").item(0).getTextContent());
				String st = nElement.getElementsByTagName("status").item(0).getTextContent();
			
				switch(tp){
				case "aanvaller":
					Aanvaller temp1 = new Aanvaller(nm,pr,tp,off,def,uit,st);
					tm.voegSpelerToe(temp1);
					break;
				case "middenvelder":
					Middenvelder temp2 = new Middenvelder(nm,pr,tp,off,def,uit,st);
					tm.voegSpelerToe(temp2);
					break;
				case "verdediger":
					Verdediger temp3 = new Verdediger(nm,pr,tp,off,def,uit,st);
					tm.voegSpelerToe(temp3);
					break;
				case "doelman":
					Doelman temp4 = new Doelman(nm,pr,tp,off,def,uit,st);
					tm.voegSpelerToe(temp4);
					break;		
				}		
			}
		}
	}
	
	/**
	 * leest een manager in aan de hand van het ingelezen xml bestand en een meegeleverde
	 * competitie (dit is nodig om team te zoeken aan de hand van teamnaam)
	 */
	public static Manager createManager(Document doc, Competitie comp){
		NodeList nList = doc.getElementsByTagName("manager");
		Node nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE){				
			Element nElement = (Element) nNode;
			
			String naam = nElement.getElementsByTagName("naam").item(0).getTextContent();
			String teamnaam = nElement.getElementsByTagName("team").item(0).getTextContent();
			int budget = Integer.parseInt(nElement.getElementsByTagName("budget").item(0).getTextContent());
			
			Team team = comp.getTeam(teamnaam);
			Manager m = new Manager(team,budget,naam);
			return m;
		}
		return null;
	}
	
	/**
	 * leest het nummer van de speelronde in
	 */
	public static int readSpeelronde(Document doc){
		NodeList nList = doc.getElementsByTagName("manager");
		Node nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE){				
			Element nElement = (Element) nNode;
			int r = Integer.parseInt(nElement.getElementsByTagName("speelrondenr").item(0).getTextContent());
			return r;
		}
		return 0;
	}
	
	public static String createNaamSlot(String path, Competitie comp) throws Exception{
		File f = new File(path);
		if(f.exists()){
			Document doc = read(path);
			NodeList nList = doc.getElementsByTagName("manager");
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE){				
				Element nElement = (Element) nNode;
			
				String naam = nElement.getElementsByTagName("naam").item(0).getTextContent();
				String teamnaam = nElement.getElementsByTagName("team").item(0).getTextContent();
				int r = Integer.parseInt(nElement.getElementsByTagName("speelrondenr").item(0).getTextContent());
				r++;
				
				return "Manager: "+naam+" Team: "+teamnaam+" Ronde: "+r;
			}
		}
		return "Leeg";
	}	
	
	/**
	 * aan de hand van de verschillende toXML methodes wordt een competitie
	 * naar de file op het opgegeven pathname geschreven 
	 */
	public static void Writer(String infile,Competitie comp, Speelschema schema, Manager mng, int r) throws IOException{	
		FileOutputStream file = new FileOutputStream(infile);
		OutputStreamWriter out = new OutputStreamWriter(file,"UTF-8");
		BufferedWriter bw = new BufferedWriter(out);
		String p = "<xml>\r\n" + comp.toXML();
		p = p + schema.toXML();
		p = p + "\r\n<manager>\r\n"+mng.toXML()+"<speelrondenr>"+r+"</speelrondenr>\r\n</manager>\r\n</xml>";
		bw.write(p);
		bw.close();
	}	
}