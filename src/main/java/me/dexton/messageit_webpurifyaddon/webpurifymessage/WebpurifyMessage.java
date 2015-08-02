package me.dexton.messageit_webpurifyaddon.webpurifymessage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import me.dexton.messageit_webpurifyaddon.WebpurifyAddon;

public class WebpurifyMessage {
	
	private WebpurifyAddon plugin;
	private String message;
	private boolean isAppropriate;
	
	public WebpurifyMessage(WebpurifyAddon plugin, String message) {
		this.plugin = plugin;
		this.message = message;
		checkMessage();
	}
	
	public String getMessage() {
		return message;
	}

	public boolean isAppropriate() {
		return isAppropriate;
	}
	
	private void checkMessage() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(generateUrl(message
					.replace(" ", "%20")
					.replace("!", "%21")
					.replace("\"", "%22")
					.replace("#", "%23")
					.replace("$", "%24")
					.replace("%", "%25")
					.replace("&", "%26")
					.replace("'", "%27")
					.replace("(", "%28")
					.replace(")", "%29")
					.replace("*", "%2A")
					.replace("+", "%2B")
					.replace(",", "%2C")
					.replace("-", "%2D")
					.replace(".", "%2E")
					.replace("/", "%2F")
					.replace(":", "%3A")
					.replace(";", "%3B")
					.replace("<", "%3C")
					.replace("=", "%3D")
					.replace(">", "%3E")
					.replace("?", "%3F")
					.replace("@", "%40")
					.replace("{", "%7B")
					.replace("|", "%7C")
					.replace("}", "%7D")
					.replace("~", "%7E")
					.replace("`", "%80")
					));
			Node node = doc.getElementsByTagName("found").item(0);
			String i = node.getTextContent().toString();
			if(!i.equalsIgnoreCase("0")) {
				isAppropriate = false;
			} else {
				isAppropriate = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Blame webpurify because their servers are not responding.");
		}
	}
	
	private String generateUrl(String message) {
		String apiKey = plugin.getConfig().getWebpurifyKey();
		String url = ("https://api1.webpurify.com/services/rest/?api_key=" + apiKey + "&method=webpurify.live.check&text=" + message + "&cdata=1");
		return url;
	}
}
