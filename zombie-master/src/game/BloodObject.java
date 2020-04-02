package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BloodObject extends FeedbackObjectBase{
    
    public BloodObject(CharacterBase attacker,CharacterBase victim) {//����Ϊ��ʩ�ӹ����ߣ��������ߣ�
    	
    	super(attacker, victim);
    	this.object=ObjectType.BLOODOBJ;
    	this.img_index=0;

        if(attackMethod==CharacterAttributes.ATTACKING_CLAW)
        {
            if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {//����ץ������ͼ������
            	try {
        			this.img=ImageIO.read(new File("images/feedback/bloodobj/claw/clawblood1.png"));
        		} catch (IOException e) {
        			// TODO �Զ����ɵ� catch ��
        			e.printStackTrace();
        		}
            }
        }

        
        
          else if(attackMethod==CharacterAttributes.ATTACKING_FIST){
        	  
            if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {//����ȭ������ͼ������
            	try {
        			this.img=ImageIO.read(new File("images/feedback/bloodobj/fist/fist1.png"));
        		} catch (IOException e) {
        			// TODO �Զ����ɵ� catch ��
        			e.printStackTrace();
        		}
            }
            else  {
            	try {
        			this.img=ImageIO.read(new File("images/feedback/bloodobj/fist/fist2.png"));
        		} catch (IOException e) {
        			// TODO �Զ����ɵ� catch ��
        			e.printStackTrace();
        		}
            }
             

        }
        else if(attackMethod==CharacterAttributes.ATTACKING_BLUNT)
        {
            if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {//����ۻ�����ͼ������
            	try {
        			this.img=ImageIO.read(new File("images/feedback/bloodobj/blunt/bluntblood1.png"));
        		} catch (IOException e) {
        			// TODO �Զ����ɵ� catch ��
        			e.printStackTrace();
        		}
            }
            else  {
            	try {
        			this.img=ImageIO.read(new File("images/feedback/bloodobj/sharp/bluntblood2.png"));
        		} catch (IOException e) {
        			// TODO �Զ����ɵ� catch ��
        			e.printStackTrace();
        		}
            }

        }
        else if(attackMethod==CharacterAttributes.ATTACKING_SHARP)
        {
            if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {//�����������ͼ������
            	try {
        			this.img=ImageIO.read(new File("images/feedback/bloodobj/sharp/sharp1.png"));
        		} catch (IOException e) {
        			// TODO �Զ����ɵ� catch ��
        			e.printStackTrace();
        		}
            } 
            else  {
            	try {
        			this.img=ImageIO.read(new File("images/feedback/bloodobj/sharp/sharp2.png"));
        		} catch (IOException e) {
        			// TODO �Զ����ɵ� catch ��
        			e.printStackTrace();
        		}
            }
        }
            else
                {
                	try {
            			this.img=ImageIO.read(new File("images/feedback/bloodobj/default/default1.png"));
            		} catch (IOException e) {
            			// TODO �Զ����ɵ� catch ��
            			e.printStackTrace();
            		}
                }
        
    }


}
