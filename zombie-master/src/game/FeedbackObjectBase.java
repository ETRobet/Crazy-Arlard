package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FeedbackObjectBase {
    int object;
	Image img;
    //����������Ƭ
    int img_index;
    //����ʱ��
    int x;
    int y;
    //����λ��
    int attackOriented;
    int oriented;
    //��ǰ���򹥻���������
//    Image[] commonAttackLeftImg = new Image[CharacterAttributes.COMMON_ATTACK_NUM];//���ͼƬС�д�
//    Image[] commonAttackRightImg = new Image[CharacterAttributes.COMMON_ATTACK_NUM];//�һ�ͼƬС�д�
//    		
    //����ͼƬ
    int move_X_Min;
    int move_X_Max;
    int move_Y_Min;
    int move_Y_Max;
    //�ɳ��ַ�Χ
    boolean movable;
    boolean isAttacking;
    //ͳһ��
    int attackMethod;
    int status;
    //������������������
    public FeedbackObjectBase() {//����Ϊ��ʩ�ӹ����ߣ��������ߣ�
    	this.object=ObjectType.DEFAULTOBJ;
    	this.status=CharacterAttributes.HPBAR_EMPTY;
        this.movable = false;
        this.isAttacking = false;
    }
    public FeedbackObjectBase(CharacterBase attacker,CharacterBase victim) {//����Ϊ��ʩ�ӹ����ߣ��������ߣ�
    	this.object=ObjectType.DEFAULTOBJ;
    	this.attackOriented=attacker.attackOriented;
    	this.status=CharacterAttributes.HPBAR_EMPTY;
    	this.attackMethod=changeMethod(attacker,attacker.attackMethod);//不同变量交接
    	this.x=victim.x;
    	this.y=victim.y;
        this.movable = false;
        this.isAttacking = false;
    }
    private int changeMethod(CharacterBase attacker, int attackMethod2) {
    	if(attacker.object==ObjectType.HEROOBJ)
    	{return CharacterAttributes.ATTACKING_SHARP;}
    	else	if(attacker.object==ObjectType.ZOMBIEOBJ)
    	{return CharacterAttributes.ATTACKING_FIST;}
    	else	if(attacker.object==ObjectType.BOSSOBJ)
    	{return CharacterAttributes.ATTACKING_CLAW;}
    	else {return CharacterAttributes.ATTACKING_DEFAULT;}
    				
		// TODO 自动生成的方法存根
	}
	public void draw(Graphics g) {
        g.drawImage(img, x, y,null);
    }
	public void draw(Graphics g,int w,int h) {
        g.drawImage(img, x, y,w,h,null);
    }

    public void setMovebalData(int xMin, int xMax, int yMin, int yMax) {
        this.move_X_Min = xMin;
        this.move_X_Max = xMax;
        this.move_Y_Min = yMin;
        this.move_Y_Max = yMax;
    }
    //���ÿɳ��ַ�Χ
    public void initPosition(int xPosition, int yPosition) {
        this.x = xPosition;
        this.y = yPosition;
    }
    //���ó�ʼλ��
    public void move() {

        }

    
}
