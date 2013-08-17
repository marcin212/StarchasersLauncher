package pl.starchasers.launcher.launch;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import pl.starchasers.launcher.sync.DownloadFile;
import pl.starchasers.launcher.sync.DownloadJob;
import pl.starchasers.launcher.sync.Sync;
import pl.starchasers.launcher.utils.Variable;
public class DownloadResources {
	  public DownloadResources() {
	try
	    {
	      URL resourceUrl = new URL("https://s3.amazonaws.com/Minecraft.Resources/");
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.parse(resourceUrl.openStream());
	      NodeList nodeLst = doc.getElementsByTagName("Contents");

	      for (int i = 0; i < nodeLst.getLength(); i++) {
	        Node node = nodeLst.item(i);

	        if (node.getNodeType() == 1) {
	          Element element = (Element)node;
	          String key = element.getElementsByTagName("Key").item(0).getChildNodes().item(0).getNodeValue();
	          String etag = element.getElementsByTagName("ETag") != null ? element.getElementsByTagName("ETag").item(0).getChildNodes().item(0).getNodeValue() : "-";
	          long size = Long.parseLong(element.getElementsByTagName("Size").item(0).getChildNodes().item(0).getNodeValue());
	          if (size > 0L) {
	            File file = new File(Variable.workingDir+"minecraft/", "assets/" + key);
	            if (etag.length() > 1) {
	            
	              if ((file.isFile()) && (file.length() == size)) {
	                String localMd5 = "\""+Sync.testChecksum(Variable.workingDir+"minecraft/"+"assets/" + key)+"\"";
	                if (localMd5.equals(etag)) continue;
	              }
	            }
	          DownloadJob.getList().add(
	        		  new DownloadFile("https://s3.amazonaws.com/Minecraft.Resources/"+key, Variable.workingDir+"minecraft/"+"assets/"+key.substring(0,key.contains("/")?key.lastIndexOf("/"):key.length())));
	          }
	        }
	      }
	    } catch (Exception ex) {
	      System.out.println("Couldn't download resources"+ex);
	    }
	  }
}
