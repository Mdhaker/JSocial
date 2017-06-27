package com.datacollection.services;

import com.datacollection.interfaces.Facebook;
import com.datacollection.interfaces.Flickr;
import com.datacollection.interfaces.Google;
import com.datacollection.interfaces.Instagram;
import com.datacollection.interfaces.Linkedin;
import com.datacollection.interfaces.Twitter;
import com.datacollection.interfaces.Youtube;

public final class Provider {
	
	public static Twitter TWITTER = TwitterAPI.build();
	public static Facebook FACEBOOK = FacebookAPI.build();
	public static Linkedin LINKEDIN = LinkedinAPI.build();
	public static Google GOOGLE = GoogleAPI.build();
	public static Flickr FLICKR = FlickrAPI.build();
	public static Instagram INSTAGRAM = InstagramAPI.build();
	public static Youtube YOUTUBE = YoutubeAPI.build();

}
