package game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ShadowObject extends FeedbackObjectBase{
	
	public ShadowObject(CharacterBase attacker, CharacterBase victim) {
		super(attacker, victim);
		// TODO �Զ����ɵĹ��캯�����
    	try {
			this.img=ImageIO.read(new File("images/feedback/shadow/shadow1.png"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
 
    	this.y=this.y+30;
    	this.object=ObjectType.SHADOWOBJ;
		
	}
	

}
