import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;


public class Pictures {
	static Image platform, ball;
	URL url;
	static StartingPoint sp;
	static AudioClip music, bounce;
	static int level = 1;
	
	
	public Pictures(StartingPoint sp) {
		try{
			url = sp.getDocumentBase();
			
		}catch (Exception e){
			
		}
		music = sp.getAudioClip(url, "Music/junglerun.au");
		bounce = sp.getAudioClip(url, "Music/bounce.au");
		platform = sp.getImage(url, "images/movingplatform.png");
		this.sp = sp;
	}

}
