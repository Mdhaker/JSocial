package com.datacollection.config;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SearchFilter {

	private String exactPhrase="",since,until;
	private Set<String> oneOfWords,allWords,noneWords,hashtags,sentFrom,sentTo,mentionAccount,urlWords;
	private boolean safe,media,nativeVideo,periscopeVideo,vineMedia,
					imageLinks,link,positiveAttitude,negativeAttitude,question;
	
	private static SearchFilter instance = null ;
	public static SearchFilter build()
		{
			if(instance==null)
				instance=new SearchFilter();
			return instance;
		}
	
	private SearchFilter()
	{
		// initiating boolean values
		this.safe = false;this.media=false;this.nativeVideo=false;this.periscopeVideo=false;
		this.vineMedia = false;this.imageLinks= false;this.link=false;this.positiveAttitude=false;
		this.negativeAttitude=false;this.question=false;
		// initiating Sets
		this.allWords = new HashSet<String>();
		this.oneOfWords = new HashSet<String>();
		this.noneWords = new HashSet<String>();
		this.hashtags = new HashSet<String>();
		this.sentFrom = new HashSet<String>();
		this.sentTo = new HashSet<String>();
		this.mentionAccount = new HashSet<String>();
		this.urlWords = new HashSet<String>();
	}

	/**
	 * Filter for tweets having the exact phrase
	 */
	public SearchFilter havingExactPhrase(String exact) 
	{
		this.exactPhrase = exact;
		return instance;
	}

	/**
	 * Filter for tweets since the date
	 */
	public SearchFilter since(Date date) 
	{
		this.since = date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
		return instance;
	}

	/**
	 * Filter for tweets until the date
	 */
	public SearchFilter until(Date date) 
	{
		this.until = date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
		return instance;
	}
	/**
	 * Filter tweets with any of the mentioned words
	 */
	public SearchFilter havingOneOf(String...words) 
	{
		for(String word:words)
		{
			this.oneOfWords.add(word);
		}
	return instance;
	}
	/**
	 * Filter tweets with all of the mentioned words
	 */
	public SearchFilter havingAllWords(String... allWords) 
	{
		for(String word :allWords)
			this.allWords.add(word);
		return instance;
	}
	/**
	 * Filter tweets with none of the mentioned words
	 */
		public SearchFilter havingNoneOf(String... noneWords) 
		{
			for(String word:noneWords)
				this.noneWords.add(word);
			return instance;
		}
		/**
		 * Filter tweets with the mentioned Hashtags
		 */
	public SearchFilter havingHashtags(String... hashtags) 
	{
		for(String word:hashtags)
			this.hashtags.add(word);
	return instance;
	}
		/**
		 * Filter tweets sent from mentioned accounts
		 */
	public SearchFilter sentFrom(String... sentFrom) 
	{
		for(String account:sentFrom)
		this.sentFrom.add(account);
	return instance;
	}
	/**
	 * Filter tweets sent to mentioned accounts
	 */
	public SearchFilter sentTo(String... sentTo) 
	{
		for(String account:sentTo)
			this.sentTo.add(account);
	return instance;
	}
	
	/**
	 * Filter tweets having mentioned accounts
	 */
	public SearchFilter havingAccounts(String... mentionAccounts) 
	{
		for(String account:mentionAccounts)
			this.mentionAccount.add(account);
	return instance;
	}

	/**
	 * Filter tweets having urls with mentioned words
	 */
	public SearchFilter havingUrlWords(String... urlWords) 
	{
		for(String urlWord:urlWords)
			this.urlWords.add(urlWord);
	return instance;
	}

	/**
	 * Filter tweets having sensitive content
	 */
	public SearchFilter havingSafeContent(boolean sensitive) 
	{
		this.safe = sensitive;
	return instance;
	}
	
	/**
	 * Filter tweets having medias (image or video)
	 */
	
	public SearchFilter havingMedia(boolean media) 
	{
		this.media = media;
	return instance;}

	/**
	 * Filter tweets having native videos
	 */
	
	public SearchFilter havingNativeVideo(boolean nativeVideo) {
		this.nativeVideo = nativeVideo;
	return instance;}

	/**
	 * Filter tweets having periscope videos
	 */
	
	public SearchFilter havingPeriscope(boolean periscopeVideo) {
		this.periscopeVideo = periscopeVideo;
	return instance;}

	/**
	 * Filter tweets having vine videos
	 */
	
	public SearchFilter havingVineMedia(boolean vineMedia) {
		this.vineMedia = vineMedia;
	return instance;}

	/**
	 * Filter tweets having image links
	 */
	
	public SearchFilter havingImageLinks(boolean imageLinks) {
		this.imageLinks = imageLinks;
	return instance;}

	/**
	 * Filter tweets having url
	 */
	
	public SearchFilter havingUrl(boolean url) {
		this.link = url;
	return instance;}

	/**
	 * Filter tweets having positive attitude
	 */
	
	public SearchFilter havingPositiveAttitude(boolean positiveAttitude) {
		this.positiveAttitude = positiveAttitude;
	return instance;}

	/**
	 * Filter tweets having negative attitude
	 */
	
	public SearchFilter havingNegativeAttitude(boolean negativeAttitude) 
	{
		this.negativeAttitude = negativeAttitude;
	return instance;
	}

	/**
	 * Filter tweets asking question
	 */
	
	public SearchFilter havingQuestion(boolean question) 
	{
		this.question = question;
	return instance;}

	// Generated getters
	public String getExactPhrase() 
	{
		return exactPhrase;
	}

	public String getSince() 
	{
		return since;
	}

	public String getUntil() {
		return until;
	}

	public Set<String> getOneOfWords() {
		return oneOfWords;
	}

	public Set<String> getAllWords() {
		return allWords;
	}

	public Set<String> getNoneWords() {
		return noneWords;
	}

	public Set<String> getHashtags() {
		return hashtags;
	}

	public Set<String> getSentFrom() {
		return sentFrom;
	}

	public Set<String> getSentTo() {
		return sentTo;
	}

	public Set<String> getMentionAccount() {
		return mentionAccount;
	}

	public Set<String> getUrlWords() {
		return urlWords;
	}

	public boolean isSafe() {
		return safe;
	}

	public boolean isMedia() {
		return media;
	}

	public boolean isNativeVideo() {
		return nativeVideo;
	}

	public boolean isPeriscopeVideo() {
		return periscopeVideo;
	}

	public boolean isVineMedia() {
		return vineMedia;
	}

	public boolean isImageLinks() {
		return imageLinks;
	}

	public boolean isLink() {
		return link;
	}

	public boolean isPositiveAttitude() {
		return positiveAttitude;
	}

	public boolean isNegativeAttitude() {
		return negativeAttitude;
	}

	public boolean isQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "SearchFilter [exactPhrase=" + exactPhrase + ", since=" + since + ", until=" + until + ", oneOfWords="
				+ oneOfWords + ", allWords=" + allWords + ", noneWords=" + noneWords + ", hashtags=" + hashtags
				+ ", sentFrom=" + sentFrom + ", sentTo=" + sentTo + ", mentionAccount=" + mentionAccount + ", urlWords="
				+ urlWords + ", safe=" + safe + ", media=" + media + ", nativeVideo=" + nativeVideo
				+ ", periscopeVideo=" + periscopeVideo + ", vineMedia=" + vineMedia + ", imageLinks=" + imageLinks
				+ ", url=" + link + ", positiveAttitude=" + positiveAttitude + ", negativeAttitude=" + negativeAttitude
				+ ", question=" + question + "]";
	}
	

}
