import java.util.ArrayList;


public class LDA {

	
	public static void main() {
		
		ArrayList URls = new ArrayList();
		ArrayList wordsBag= new ArrayList();
		wordsBag.add("technology");
		wordsBag.add("sports");
		wordsBag.add("music");
		wordsBag.add("politics");
		wordsBag.add("arts");
		wordsBag.add("misc");
		wordsBag.add("java");
		wordsBag.add("code");
		wordsBag.add("tech");
		wordsBag.add("computer");
		wordsBag.add("android");
		wordsBag.add("apple");
		wordsBag.add("techcrunch");
		wordsBag.add("ios");
		wordsBag.add("apple");
		wordsBag.add("google");
		wordsBag.add("facebook");
		
		URls.add("www.techcrunch.com");
		URls.add("www.cricinfo.com");
		URls.add("www.microsoft.com");
		URls.add("http://www.youtube.com/watch?feature=player_embedded&v=mhAg0COnqds");
		URls.add("http://www.eventbrite.com");
		URls.add("www.projecteuler.com");
		
		int [][] array = new int[wordsBag.size()][URls.size()];
		
		
		
		
		
	}
}
