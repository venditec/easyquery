/*
.---------------------------------------------------------------------------.
|  Software: EasyQuery                                  					|
|   Version: 1.0                                                            |
|   Contact: via sourceforge.net or ramon.sepi@gmail.com                    |
|      Info: http://sourceforge.net/projects/easyquery1/                    |
|   Support: http://sourceforge.net/projects/easyquery1/                    |
| ------------------------------------------------------------------------- |
|     Admin: Ramon Segarra Pijuan (project admininistrator)                 |
|   Author : Ramon Segarra Pijuan 											|
|   Founder: Ramon Segarra Pijuan (original founder)                        |
| Copyright (c) 2012-2013, Ramon Segarra Pijuan. All Rights Reserved.       |
| ------------------------------------------------------------------------- |
|   License: Distributed under the Creative Commons 						|
|            Attribution-NonCommercial 3.0 Unported License.     			|
|            http://creativecommons.org/licenses/by-nc/3.0/                 |
| This program is distributed in the hope that it will be useful -          |
| YOU ARE FREE: to Share — to copy, distribute and transmit the work.       |
|               to Remix — to adapt the work.								|
| UNDER THE FOLLOWING CONDITIONS: 											|
|		Noncomercial - You may not use this work for commercial purposes.	|
|		Attribution  — You must attribute the work to Ramon Segarra Pijuan	|
|                      like the author or licensor (but not in any way that |
|					   suggests that they endorse you or your use 			|
|                      of the work).										|
| ------------------------------------------------------------------------- |
|          EasyQuery by Ramon Segarra Pijuan is licensed under a 			|
|     Creative Commons Attribution-NonCommercial 3.0 Unported License.		|
'---------------------------------------------------------------------------'
*/

package easyquery.client.widget.system.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import easyquery.client.etc.engine.Redirect;
import easyquery.client.etc.lang.Text;
import easyquery.client.etc.security.SecurityCode;

public class InfoUser extends HorizontalPanel {
	
	private static InfoUser singleton;
	
	public String EmployeeNumber;
	public String Name;
	public String Surname;
	public String Mail;
	public String PrivateMail;
	public String Address;
	public String City;
	public String PostalCode;
	public String Country;
	public String Phone;
	public boolean Activated;
	
	public static InfoUser getSingleton() {
		return singleton;
	}
	
	public InfoUser(final String employeenumber){
		
		singleton = this;
		
		
		Window.addWindowClosingHandler(new ClosingHandler() {
			@Override
			public void onWindowClosing(ClosingEvent event) {
				if(InfoUser.getSingleton() != null && Activated){
					event.setMessage("If you sure to close the windows ?");
				}
			}
		});
		Window.addCloseHandler(new CloseHandler<Window>() {
				
			@Override
			public void onClose(CloseEvent<Window> event) {
				if(InfoUser.getSingleton() != null && Activated){
					RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_LockUser.php"));
					REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
					try {
						REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+EmployeeNumber+"&lock=0", new RequestCallback() {
								
							@Override
							public void onResponseReceived(Request request, Response response) {
								/* Close user session when close window */
							}
								
							@Override
							public void onError(Request request, Throwable exception) {
								Window.alert("Error en servidor :: SRVLogicService");	
							}
						});
					} catch (RequestException e) {
						e.printStackTrace();
						Window.alert("Error en servidor :: SRVLogicService");
					}
				}
			}
		});
		
		setWidth("100%");
		final Label UserName = new Label();
		UserName.setStyleName("LoginText");
		add(UserName);
		setCellHorizontalAlignment(UserName, ALIGN_RIGHT);
		setCellVerticalAlignment(UserName, ALIGN_MIDDLE);
		
		Timer removeDelay = new Timer(){
		      public void run() {
		    	  /* Refresh to 5 seconds */
		    	  	RequestBuilder REQ = new RequestBuilder(RequestBuilder.POST,URL.encode(GWT.getHostPageBaseURL() + "etc/lib/php/_private/SRV_GetInfoUser.php"));
			  		REQ.setHeader("Content-Type", "application/x-www-form-urlencoded");
			  		try {
			  			REQ.sendRequest("codeW="+new SecurityCode().getSRVSecurityCode()+"&employee="+employeenumber, new RequestCallback() {
			  				
			  				@Override
			  				public void onResponseReceived(Request request, Response response) {
			  					//Window.alert(response.getText());
			  					if(response.getText().split("\\|")[0].equals("OK")){
			  						if(response.getText().split("\\|")[10].equals("0")){
			  							Activated = false;
			  							Window.alert(new Text().getText().Action_AdminLock());
			  							Redirect.setLocation(GWT.getHostPageBaseURL());
			  						}else Activated = true;
			  						EmployeeNumber = employeenumber;
			  						Name = response.getText().split("\\|")[1];
			  						Surname = response.getText().split("\\|")[2];
			  						Mail = response.getText().split("\\|")[3];
			  						PrivateMail = response.getText().split("\\|")[4];
			  						Address = response.getText().split("\\|")[5];
			  						City = response.getText().split("\\|")[6];
			  						PostalCode = response.getText().split("\\|")[7];
			  						Country = response.getText().split("\\|")[8];
			  						Phone = response.getText().split("\\|")[9]; 
			  						UserName.setText(Name + " " + Surname);
			  					}
			  				}
			  				
			  				@Override
			  				public void onError(Request request, Throwable exception) {
			  					Window.alert("Error en servidor :: SRVLogicService");	
			  				}
			  			});
			  		} catch (RequestException e) {
			  			e.printStackTrace();
			  			Window.alert("Error en servidor :: SRVLogicService");
			  		}
			  	 /* End refresh procedure */
		      }        
	    };
	    removeDelay.scheduleRepeating(5000);
	}

}
