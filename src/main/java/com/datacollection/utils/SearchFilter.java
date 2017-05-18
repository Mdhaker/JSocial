package com.datacollection.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SearchFilter {

		public static SearchFilter.TwitterFilter buildTwitter()
		{
		return (new SearchFilter()).new TwitterFilter();
		}
	
		public static SearchFilter.GoogleFilter buildGoogle()
		{
			
			return (new SearchFilter()).new GoogleFilter();
		}
	
	
	
	public class TwitterFilter
	{
		private String exactPhrase="",since,until;
		private Set<String> oneOfWords,allWords,noneWords,hashtags,sentFrom,sentTo,mentionAccount,urlWords;
		private boolean safe,media,nativeVideo,periscopeVideo,vineMedia,
						imageLinks,link,positiveAttitude,negativeAttitude,question;
		
		private TwitterFilter()
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
		public TwitterFilter havingExactPhrase(String exact) 
		{
			this.exactPhrase = exact;
			return this;
		}

		/**
		 * Filter for tweets since the date
		 */
		public TwitterFilter since(Date date) 
		{
			this.since = date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
			return this;
		}

		/**
		 * Filter for tweets until the date
		 */
		public TwitterFilter until(Date date) 
		{
			this.until = date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
			return this;
		}
		/**
		 * Filter tweets with any of the mentioned words
		 */
		public TwitterFilter havingOneOf(String...words) 
		{
			for(String word:words)
			{
				this.oneOfWords.add(word);
			}
		return this;
		}
		/**
		 * Filter tweets with all of the mentioned words
		 */
		public TwitterFilter havingAllWords(String... allWords) 
		{
			for(String word :allWords)
				this.allWords.add(word);
			return this;
		}
		/**
		 * Filter tweets with none of the mentioned words
		 */
			public TwitterFilter havingNoneOf(String... noneWords) 
			{
				for(String word:noneWords)
					this.noneWords.add(word);
				return this;
			}
			/**
			 * Filter tweets with the mentioned Hashtags
			 */
		public TwitterFilter havingHashtags(String... hashtags) 
		{
			for(String word:hashtags)
				this.hashtags.add(word);
		return this;
		}
			/**
			 * Filter tweets sent from mentioned accounts
			 */
		public TwitterFilter sentFrom(String... sentFrom) 
		{
			for(String account:sentFrom)
			this.sentFrom.add(account);
		return this;
		}
		/**
		 * Filter tweets sent to mentioned accounts
		 */
		public TwitterFilter sentTo(String... sentTo) 
		{
			for(String account:sentTo)
				this.sentTo.add(account);
		return this;
		}
		
		/**
		 * Filter tweets having mentioned accounts
		 */
		public TwitterFilter havingAccounts(String... mentionAccounts) 
		{
			for(String account:mentionAccounts)
				this.mentionAccount.add(account);
		return this;
		}

		/**
		 * Filter tweets having urls with mentioned words
		 */
		public TwitterFilter havingUrlWords(String... urlWords) 
		{
			for(String urlWord:urlWords)
				this.urlWords.add(urlWord);
		return this;
		}

		/**
		 * Filter tweets having sensitive content
		 */
		public TwitterFilter havingSafeContent(boolean sensitive) 
		{
			this.safe = sensitive;
		return this;
		}
		
		/**
		 * Filter tweets having medias (image or video)
		 */
		
		public TwitterFilter havingMedia(boolean media) 
		{
			this.media = media;
		return this;}

		/**
		 * Filter tweets having native videos
		 */
		
		public TwitterFilter havingNativeVideo(boolean nativeVideo) {
			this.nativeVideo = nativeVideo;
		return this;}

		/**
		 * Filter tweets having periscope videos
		 */
		
		public TwitterFilter havingPeriscope(boolean periscopeVideo) {
			this.periscopeVideo = periscopeVideo;
		return this;}

		/**
		 * Filter tweets having vine videos
		 */
		
		public TwitterFilter havingVineMedia(boolean vineMedia) {
			this.vineMedia = vineMedia;
		return this;}

		/**
		 * Filter tweets having image links
		 */
		
		public TwitterFilter havingImageLinks(boolean imageLinks) {
			this.imageLinks = imageLinks;
		return this;}

		/**
		 * Filter tweets having url
		 */
		
		public TwitterFilter havingUrl(boolean url) {
			this.link = url;
		return this;}

		/**
		 * Filter tweets having positive attitude
		 */
		
		public TwitterFilter havingPositiveAttitude(boolean positiveAttitude) {
			this.positiveAttitude = positiveAttitude;
		return this;}

		/**
		 * Filter tweets having negative attitude
		 */
		
		public TwitterFilter havingNegativeAttitude(boolean negativeAttitude) 
		{
			this.negativeAttitude = negativeAttitude;
		return this;
		}

		/**
		 * Filter tweets asking question
		 */
		
		public TwitterFilter havingQuestion(boolean question) 
		{
			this.question = question;
		return this;}

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
			return "TwitterFilter [exactPhrase=" + exactPhrase + ", since=" + since + ", until=" + until + ", oneOfWords="
					+ oneOfWords + ", allWords=" + allWords + ", noneWords=" + noneWords + ", hashtags=" + hashtags
					+ ", sentFrom=" + sentFrom + ", sentTo=" + sentTo + ", mentionAccount=" + mentionAccount + ", urlWords="
					+ urlWords + ", safe=" + safe + ", media=" + media + ", nativeVideo=" + nativeVideo
					+ ", periscopeVideo=" + periscopeVideo + ", vineMedia=" + vineMedia + ", imageLinks=" + imageLinks
					+ ", url=" + link + ", positiveAttitude=" + positiveAttitude + ", negativeAttitude=" + negativeAttitude
					+ ", question=" + question + "]";
		}
		
	}
	
	/**
	 * filter for google activities 
	 * @author dhaker
	 *
	 */
	
	public class GoogleFilter{
		
		private String language,orderBy,query;
		
		public GoogleFilter()
		{
			
		}
		
		public String getLanguage() {
			return language;
		}

		public String getOrderBy() {
			return orderBy;
		}
		public String getQuery() {
			return query;
		}
		/**
		 * Set query code
		 */
		public GoogleFilter setQuery(String query)
		{
			this.query=query;
			return this;
		};
		/**
		 * Set language code
		 */
		public GoogleFilter setLang(String lang)
		{
			this.language=lang;
			return this;
		};
		/**
		 * 
		 * @param Recent or Best
		 */
		public GoogleFilter setOrder(String orderby)
		{
			this.orderBy=orderby;
			return this;
		};
	}
	

}
