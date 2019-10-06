package com.mkyong;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestGoogleSea {

  private static Pattern patternDomainName;
  private Matcher matcher;
  private static final String DOMAIN_NAME_PATTERN 
	= "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
  static {
	patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
  }
	
//  public static void main(String[] args) {
//
//	TestGoogleSea obj = new TestGoogleSea();
//	Set<String> result = obj.getDataFromGoogle("mario");
//	for(String temp : result){
//		System.out.println(temp);
//	}
//	System.out.println(result.size());
//  }

  public String getDomainName(String url){
		
	String domainName = "";
	matcher = patternDomainName.matcher(url);
	if (matcher.find()) {
		domainName = matcher.group(0).toLowerCase().trim();
	}
	return domainName;
		
  }
	
  @SuppressWarnings("empty-statement")
  public Set<String> getDataFromGoogle(String query) {
	Set<String> result = new HashSet<>();	
	String request = "https://www.google.com/search?q=" + query + "&num=20";
	System.out.println("Sending request..." + request);
    	try {

		// need http protocol, set this as a Google bot agent :)
            
            Document doc = Jsoup
			.connect(request)
			.userAgent(
			  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
			.timeout(5000).get();
//                String data[][] = null;
//		String cols[] = {"Link"};
                // get all links
		Elements links = doc.select("a[href]");
                for (Element link : links) {
                        int i=0;
			String temp = link.attr("href");		
			if(temp.startsWith("/url?q=")){
                                //use regex to get domain name
//				result.add(getDomainName(temp));
                            result.add(temp);
//                            data[i][0] = getDomainName(temp);
//                            i++;
                 	}

		}
             
//            JTable linksTable = new JTable(data,cols);
            
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result;
  }

}