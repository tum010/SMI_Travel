package com.smi.travel.common;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Surachai
 */
public class SMITravelSQL extends JdbcDaoSupport {
    private static final Logger log = Logger.getLogger(SMITravelSQL.class.getName());
    private Document doc;
    private static final String TAG_QUERY = "query";
    private static final String TAG_ID = "id";
    private String TAG_NAME;
    private TransactionTemplate transactionTemplate;
    
    public SMITravelSQL(String TAG_NAME,DataSource datasource) {
        //setup DataSource
        super.setDataSource(datasource);
        ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-config.xml");
 
	MasterConfig config = (MasterConfig)context.getBean("beanxml");
        try {
            File fXmlFile = new File(config.getPathxml());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(fXmlFile);
            this.TAG_NAME = TAG_NAME;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.toString());
        }
        //init Transaction
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(datasource);
        transactionTemplate = new TransactionTemplate(transactionManager);
    }
    
    public String getQuery(String QueryType) {
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(TAG_QUERY);
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute(TAG_ID).equalsIgnoreCase(TAG_NAME)) {
                    if (eElement.getElementsByTagName(QueryType).item(0) != null) {
                        return eElement.getElementsByTagName(QueryType).item(0).getTextContent().trim();
                    }
                }
            }
        }
        return "";
    }
    
    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }
    
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    
    
}
