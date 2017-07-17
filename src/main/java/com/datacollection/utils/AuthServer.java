package com.datacollection.utils;

import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;

import com.datacollection.config.Config;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

public class AuthServer implements HttpHandler {

	private String code="";
	
	public String getCode()
	{
		return this.code;
	}
	private HttpsServer server ;
	public AuthServer()
	{
		try {
            // initialise the HTTPS server

			this.server = HttpsServer.create(new InetSocketAddress(9000), 0);
			SSLContext sslContext = SSLContext.getInstance("TLS");
			// initialise the keystore
			
            char[] password = Config.KEYSOTRE_PASSWORD.toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(Config.SSL_KEYSOTRE_FILE);
            ks.load(fis, password);

            // setup the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            // setup the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            // setup the HTTPS context and parameters
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            this.server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    try 
                    {
                        // initialise the SSL context
                        SSLContext c = SSLContext.getDefault();
                        SSLEngine engine = c.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());

                       
                    } catch (Exception ex) {
                        System.out.println("Failed to create HTTPS port");
                    }
                }
            });
            this.server.createContext("/", this);
            this.server.setExecutor(null); // creates a default executor
            this.server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static AuthServer start()
	{
		AuthServer instance = new AuthServer();
		instance.code="";
		if(instance.server != null)
		{
			System.out.println("Server Started ...");
			
		}
		return instance;
	}
	/**
	 * simple method to open browser with a target url (authorization) url, in manuel oauth test
	 * @param url
	 */
	public static void openBrowser(String url) 
	{
		if (!Config.Debug)
		{
			Config.hideDebug();
		}
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
          
                try {
					desktop.browse(new URI(url));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
           
        }else{
            Runtime runtime = Runtime.getRuntime();
            try 
            {
                runtime.exec("xdg-open " + url).getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Config.hideDebug();
    }

	@Override
	public void handle(HttpExchange t) throws IOException {
		String response = "<html><body><center>Authentificated succesfully \n<br><img src='https://www.r-project.org/logo/Rlogo.png' heigth='100' width='100'></center>";
        //HttpsExchange httpsExchange = (HttpsExchange) t;
       
     // parse request
        Map<String, Object> parameters = new HashMap<String, Object>();
        URI requestedUri = t.getRequestURI();
        String query = requestedUri.getRawQuery();
        parseQuery(query, parameters);
        if(parameters.get("code") != null)
        {
        	this.code = parameters.get("code").toString();
        	 response = response.concat("<center><br><b>oAuth2</b></center>");
        	 response = response.concat("<center><br><b>code</b></center>");
        }
        else
        {
        	this.code = parameters.get("oauth_verifier").toString();
        	response = response.concat("<center><br><b>oAuth1</b></center>");
        	response = response.concat("<center><br><b>oauth_verifier</b></center>");
        }
       
   	 	response = response.concat("<center><br><b>"+this.code+"</b></center></body></html>");
        
        t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
        this.server.stop(0);
		
	}
	public static void parseQuery(String query, Map<String, 
			Object> parameters) throws UnsupportedEncodingException {

		         if (query != null) {
		                 String pairs[] = query.split("[&]");
		                 for (String pair : pairs) {
		                          String param[] = pair.split("[=]");
		                          String key = null;
		                          String value = null;
		                          if (param.length > 0) {
		                          key = URLDecoder.decode(param[0], 
		                          	System.getProperty("file.encoding"));
		                          }

		                          if (param.length > 1) {
		                                   value = URLDecoder.decode(param[1], 
		                                   System.getProperty("file.encoding"));
		                          }

		                          if (parameters.containsKey(key)) {
		                                   Object obj = parameters.get(key);
		                                   if (obj instanceof List<?>) {
		                                            List<String> values = (List<String>) obj;
		                                            values.add(value);

		                                   } else if (obj instanceof String) {
		                                            List<String> values = new ArrayList<String>();
		                                            values.add((String) obj);
		                                            values.add(value);
		                                            parameters.put(key, values);
		                                   }
		                          } else {
		                                   parameters.put(key, value);
		                          }
		                 }
		         }
		}

}