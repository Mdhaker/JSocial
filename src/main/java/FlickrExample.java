
import java.util.Scanner;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.datacollection.config.Config;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public final class FlickrExample {

    private static final String PROTECTED_RESOURCE_URL = "https://api.flickr.com/services/rest/";

    private FlickrExample() {
    }

    public static void main(String... args) throws IOException, InterruptedException, ExecutionException {
        // Replace these with your own api key and secret
        final String apiKey = "a8a3eca8e81064cfdac2baf7c445e569";
        final String apiSecret = "c2abea55d4932e70";
        final OAuth10aService service = new ServiceBuilder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance());
        final Scanner in = new Scanner(System.in);

        System.out.println("=== Flickr's OAuth Workflow ===");
        System.out.println();

        // Obtain the Request Token
        System.out.println("Fetching the Request Token...");
        final OAuth1RequestToken requestToken = service.getRequestToken();
        System.out.println("Got the Request Token!");
        System.out.println();

        System.out.println("Now go and authorize ScribeJava here:");
        final String authorizationUrl = service.getAuthorizationUrl(requestToken);
        System.out.println(authorizationUrl + "&perms=read");
        System.out.println("And paste the verifier here");
        System.out.print(">>");
        final String oauthVerifier = in.nextLine();
        System.out.println();

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, oauthVerifier);
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken
                + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL, service);
        request.addQuerystringParameter("method", "flickr.test.login");
        service.signRequest(accessToken, request);
        final Response response = request.send();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getBody());

        System.out.println();
        System.out.println("Thats it man! Go and build something awesome with ScribeJava! :)");
    }
}