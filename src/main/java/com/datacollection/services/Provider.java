package com.datacollection.services;

import com.datacollection.interfaces.Facebook;
import com.datacollection.interfaces.Google;
import com.datacollection.interfaces.Linkedin;
import com.datacollection.interfaces.Twitter;

public final class Provider {
	
	public static Twitter TWITTER = TwitterAPI.build();
	public static Facebook FACEBOOK = FacebookAPI.build();
	public static Linkedin LINKEDIN = LinkedinAPI.build();
	public static Google GOOGLE = GoogleAPI.build();

}
