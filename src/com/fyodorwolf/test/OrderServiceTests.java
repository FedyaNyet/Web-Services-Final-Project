package com.fyodorwolf.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Assert;
import org.junit.Test;

public class OrderServiceTests {
	
	private static String BASE_URI = "http://localhost:8080/Homework4/services/orders";

	String productId = "13908102915";
	String newOrder = 
	"<order>"
		+ "<orderId>"+productId+"</orderId>"
		+ "<billTo>"
			+"<name>Fyodor Wolf</name>"
			+"<street>5 Park st.</street>"
			+"<city>Boston</city>"
			+"<state>ma</state>"
			+"<zip>12345</zip>"
			+"<phone>(987)654-3210</phone>"
		+ "</billTo>"
	+ "</order>";
	String newItemName = "Some New Item";
	String newItem = 
	"<item>"
		+ "<quantity>10</quantity>"
		+ "<price>17.00</price>"
		+ "<productName>" + newItemName + "</productName>"
	+ "</item>";
	
	@Test
	public void placeOrderTest() throws IOException{
//
//		URL _orderUrl = new URL(BASE_URI + "/"+productId);
//		URL _url = new URL(BASE_URI + "/");
//		
//		System.out.println("\n*** Place a new Order ***");
//		HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
//		connection.setRequestProperty("clientId", "150");
//		connection.setDoOutput(true);
//		connection.setRequestMethod("POST");
//		connection.setRequestProperty("Content-Type", "application/xml");
//		connectionSetInput(connection, newOrder);
//		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
//		Assert.assertEquals("true", getConnectionString(connection));
//		
//		System.out.println("\n*** Get all placed Orders ***");
//		connection = (HttpURLConnection) _url.openConnection();
//		connection.setRequestProperty("clientId", "150");
//		connection.setRequestMethod("GET");
//		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
//		Assert.assertThat(getConnectionString(connection), containsString(productId));
//		
//		System.out.println("\n*** Add Item to Order ***");
//		connection = (HttpURLConnection) _orderUrl.openConnection();
//		connection.setRequestMethod("PUT");
//		connection.setDoOutput(true);
//		connection.setRequestProperty("Content-Type", "application/xml");
//		connection.setRequestProperty("clientId", "150");
//		connectionSetInput(connection, newItem);
//		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
//		Assert.assertThat(getConnectionString(connection), containsString("true"));
//	
//		System.out.println("\n**** Check Updated Order ***");
//		connection = (HttpURLConnection) _orderUrl.openConnection();
//		connection.setRequestMethod("GET");
//		connection.setRequestProperty("clientId", "150");
//		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
//		Assert.assertThat(getConnectionString(connection), containsString(productId));
//		
//		System.out.println("\n*** Delete placed Order ***");
//		connection = (HttpURLConnection) _orderUrl.openConnection();
//		connection.setRequestMethod("DELETE");
//		connection.setRequestProperty("clientId", "150");
//		Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, connection.getResponseCode());
//		
//		System.out.println("\n**** Get deleted orders ***");
//		connection = (HttpURLConnection) _orderUrl.openConnection();
//		connection.setRequestMethod("GET");
//		connection.setRequestProperty("clientId", "150");
//		Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, connection.getResponseCode());
//		
//		connection.disconnect();
	}
	
	private void connectionSetInput(HttpURLConnection connection, String input) {
		OutputStream os;
		try {
			os = connection.getOutputStream();
			os.write(input.getBytes());
			
			os.flush();
		} catch (IOException e) {
			System.out.println("connectionSetInput error");
			e.printStackTrace();
		}
	}

	private String getConnectionString(HttpURLConnection connection){
		String decodedString = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			decodedString = in.readLine();
			in.close();
		} catch (IOException e) {
			System.out.println("getConnectionString error");
			e.printStackTrace();
		}
		return decodedString;
	}
}
