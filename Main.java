package com.company;

import com.company.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<StarSystem> starSystems = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        var document = builder.parse(new File("D:/epam/StarSystems/resources/CelestialBodies.xml"));

        var starSystemElements = document.getElementsByTagName("starSystem");
        for (int i = 0; i < starSystemElements.getLength(); i++) {
            Element starSystemElement = (Element) starSystemElements.item(i);

            NodeList starElements = starSystemElement.getElementsByTagName("star");
            List<Star> stars = new ArrayList<Star>();

            for (int starIndex = 0; starIndex < starElements.getLength(); starIndex++) {
                Element starElement = (Element) starElements.item(starIndex);
                NamedNodeMap starAttribute = starElement.getAttributes();
                String starName = starAttribute.getNamedItem("name").getNodeValue();
                double weight = Double.parseDouble(starAttribute.getNamedItem("weight").getNodeValue());
                NodeList planetsNodes = starElement.getElementsByTagName("planet");
                List<Planet> planets = new ArrayList<Planet>();
                for (int planetIndex = 0; planetIndex < planetsNodes.getLength(); planetIndex++) {
                    Element planetNode = (Element) planetsNodes.item(planetIndex);
                    NamedNodeMap planetAttributes = planetNode.getAttributes();
                    String planetName = planetAttributes.getNamedItem("name").getNodeValue();
                    double planetWeight = Double.parseDouble(planetAttributes.getNamedItem("weight").getNodeValue());
                    NodeList satellitesElements = planetNode.getElementsByTagName("satellite");
                    List<Satellite> satellites = new ArrayList<Satellite>();

                    for (int satelliteIndex = 0; satelliteIndex < satellitesElements.getLength(); satelliteIndex++) {
                        Element satelliteElement = (Element) satellitesElements.item(satelliteIndex);
                        NamedNodeMap satelliteAttributes = satelliteElement.getAttributes();
                        String satelliteName = satelliteAttributes.getNamedItem("name").getNodeValue();
                        double satelliteWeight = Double.parseDouble(satelliteAttributes.getNamedItem("weight").getNodeValue());
                        Satellite satellite = new Satellite(satelliteName, satelliteWeight);
                        satellites.add(satellite);
                    }

                    Planet planet = new Planet(planetName, planetWeight, satellites);
                    planets.add(planet);
                }

                Star star = new Star(starName, weight, planets);
                stars.add(star);
            }

            NamedNodeMap starSystemAttributes = starSystemElement.getAttributes();
            String starSystemName = starSystemAttributes.getNamedItem("name").getNodeValue();
            StarSystem starSystem = new StarSystem(starSystemName, stars);
            starSystems.add(starSystem);
        }

        CalculationService calculationService = new CalculationService();
        List<StarSystemCalculationsModel> calculations = starSystems.stream()
                .map(calculationService::getCalculations).collect(Collectors.toList());

        Document document1 = factory.newDocumentBuilder().newDocument();
        Element root = document1.createElement("resultsOfCalculation");
        document1.appendChild(root);

        for(int i = 0; i < starSystems.size(); i++){
            Element item = document1.createElement("starSystem");
            item.setAttribute("name", calculations.get(i).getSystemName());
            item.setAttribute("numberOfCelestialBodies", String.valueOf(calculations.get(i).getBodiesNumber()));
            item.setAttribute("totalWeight", String.valueOf(calculations.get(i).getBodiesWeight()));
            root.appendChild(item);
        }

        File file = new File("D:/epam/StarSystems/resources/Result.xml");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document1), new StreamResult(file));
    }
}
