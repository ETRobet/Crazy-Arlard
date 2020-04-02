/*
 * RunningSceneThreePanel.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月29日
 */
package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class RunningSceneThreePanel extends RunningScenePanelBase implements KeyListener {

    SceneThreeBackGround sceneThreeBackGround;
    HeroCharacter heroCharacter;
    BossCharacter bossCharacter;
    
    ArrayList<BloodObject>bloodlist;//*
    ArrayList<ShadowObject>zombieshadowlist;//*
    ArrayList<ShadowObject>heroshadowlist;//*
    ShadowObject bossshadow;//*
    
    ArrayList<HPBar>hpbarlist;//*
    int hpBar_length;//*
    int hpbar_index;//心跳变换计数器
    int hpbar_index2;
    
    RunningSceneThreePanel(HeroCharacter hCharacter) {
        sceneThreeBackGround = new SceneThreeBackGround();
        heroCharacter = hCharacter;
        bossCharacter = new BossCharacter();
        heroCharacter.initPosition(SceneThreeAttributes.MOVEBAL_X_MIN + 10, SceneThreeAttributes.MOVEBAL_Y_MAX);
        heroCharacter.setMovebalData(SceneThreeAttributes.MOVEBAL_X_MIN, SceneThreeAttributes.MOVEBAL_X_MAX, SceneThreeAttributes.MOVEBAL_Y_MIN, SceneThreeAttributes.MOVEBAL_Y_MAX);
        bossCharacter.initPosition(1200 - (bossCharacter.img.getWidth(null) - 90), SceneThreeAttributes.MOVEBAL_Y_MAX);
        bossCharacter.setMovebalData(SceneThreeAttributes.MOVEBAL_X_MIN, SceneThreeAttributes.MOVEBAL_X_MAX, SceneThreeAttributes.MOVEBAL_Y_MIN, SceneThreeAttributes.MOVEBAL_Y_MAX);

        bossshadow=new ShadowObject(bossCharacter, bossCharacter);//*  
        bloodlist=new ArrayList<BloodObject>();//*
        zombieshadowlist=new ArrayList<ShadowObject>();//*
        heroshadowlist=new ArrayList<ShadowObject>();//*
        hpbarlist=new ArrayList<HPBar>();//*   
        if(heroCharacter.HP>=10)hpBar_length=heroCharacter.HP/10;
        else hpBar_length=1;//血量初始化
        hpbar_index=0;
        hpbarcreate();//*
        this.characterShadowAdd(heroCharacter,heroshadowlist);//*
        
        this.addKeyListener(this);
        try {
            bgm = Applet.newAudioClip(new File("sounds/bgm/scenetwobgm.wav").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public boolean work() {
        this.requestFocus();
        bgm.loop();
        while (loop) {
            heroCharacter.move();
            this.shadowmove(heroCharacter,0);//*
            heroCharacter.battle(bossCharacter);
            bossCharacter.updateDirection(heroCharacter);
            bossCharacter.move();
            this.shadowmove(bossCharacter,0);//*
            if(bossCharacter.battle(heroCharacter))
            {
            	BloodObject blood1=new BloodObject(bossCharacter, heroCharacter);
            	bloodlist.add(blood1);
            }//加新血迹
            this.bloodclear();//*
            hpbarupdate();//*
            this.repaint(0);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.heroCharacter.HP <= 0) {
                this.loop = false;
                this.passStatus = RunningAttributes.SCENE_FAIL;
            } else if (bossCharacter.HP <= 0) {
                this.loop = false;
                this.passStatus = RunningAttributes.SCENE_PASS;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bgm.stop();
        return passStatus;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        sceneThreeBackGround.drawDefault(g);
        heroCharacter.draw(g);
        bossCharacter.draw(g);
        for (int i = 0; i < heroshadowlist.size(); i++) {
        	heroshadowlist.get(i).draw(g);
        }//*
        bossshadow.draw(g);//*
        if (heroCharacter.isAttacking && heroCharacter.attackMethod == CharacterAttributes.HERO_ULTIMATE_SKILL) {
            heroCharacter.drawUltimateSkill(g);
        }
  
        for (int i = 0; i < bloodlist.size(); i++) {
        	bloodlist.get(i).draw(g,250,250);
        }//*
        for (int i = 0; i < hpbarlist.size(); i++) {
        	hpbarlist.get(i).draw(g);
        }//*
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            heroCharacter.xMove = RunningAttributes.MOVE_DIRECTION_LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            heroCharacter.xMove = RunningAttributes.MOVE_DIRECTION_RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            heroCharacter.yMove = RunningAttributes.MOVE_DIRECTION_UP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            heroCharacter.yMove = RunningAttributes.MOVE_DIRECTION_DOWN;
        }

        if (heroCharacter.isAttacking == false) {

            if (heroCharacter.isAttacking == false) {

                //普攻
                if (e.getKeyCode() == KeyEvent.VK_X) {
                    heroCharacter.movable = false;
                    heroCharacter.isAttacking = true;
                    heroCharacter.attackMethod = CharacterAttributes.HERO_ATTACK_METHOD_ONE;
                    heroCharacter.imageCommonAttackIndex = 0;
                    heroCharacter.imageSpecialAttackIndex = 0;
                    heroCharacter.imageUltimateSkillX = 0;
                    if (heroCharacter.oriented == RunningAttributes.ORIENTED_RIGHT) {
                        heroCharacter.attackOriented = CharacterAttributes.ATTACKING_RIGHT;
                    } else if (heroCharacter.oriented == RunningAttributes.ORIENTED_LEFT) {
                        heroCharacter.attackOriented = CharacterAttributes.ATTACKING_LEFT;
                    }
                    heroCharacter.commonAttackAudio.play();
                }

                //重击
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    heroCharacter.movable = false;
                    heroCharacter.isAttacking = true;
                    heroCharacter.attackMethod = CharacterAttributes.HERO_ATTACK_METHOD_TWO;
                    heroCharacter.imageCommonAttackIndex = 0;
                    heroCharacter.imageSpecialAttackIndex = 0;
                    heroCharacter.imageUltimateSkillX = 0;
                    if (heroCharacter.oriented == RunningAttributes.ORIENTED_RIGHT) {
                        heroCharacter.attackOriented = CharacterAttributes.ATTACKING_RIGHT;
                    } else if (heroCharacter.oriented == RunningAttributes.ORIENTED_LEFT) {
                        heroCharacter.attackOriented = CharacterAttributes.ATTACKING_LEFT;
                    }
                    heroCharacter.specialAttackAudio.play();
                }

                //大招预留
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    heroCharacter.movable = false;
                    heroCharacter.isAttacking = true;
                    heroCharacter.attackMethod = CharacterAttributes.HERO_ULTIMATE_SKILL;
                    heroCharacter.imageCommonAttackIndex = 0;
                    heroCharacter.imageSpecialAttackIndex = 0;
                    heroCharacter.imageUltimateSkillX = 0 - heroCharacter.ultimateSkillImg.getWidth(null);
                    heroCharacter.ultimateskillAudio.play();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            heroCharacter.xMove = RunningAttributes.MOVE_DIRECTION_NO_MOVE;
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            heroCharacter.yMove = RunningAttributes.MOVE_DIRECTION_NO_MOVE;
        }
    }
    private void characterShadowAdd(CharacterBase character,ArrayList<ShadowObject> list) {//参数:施加物体，目标链表(后续升级用)
		// TODO 自动生成的方法存根
        if(character.HP>0)
        {
        ShadowObject shadow1=new ShadowObject(character, character);
        list.add(shadow1);
        }
        //生成新阴影
		
	}



    private void bloodclear() {
		// TODO 自动生成的方法存根
		for(int i=0;i<bloodlist.size();i++)
		{
			BloodObject blood2=bloodlist.get(i);
			blood2.img_index++;
			if(blood2.img_index==5)//此处数字为间隔时间可调
			{
				bloodlist.remove(i);
				i--;
				blood2.img_index=0;
			}
			//判断血迹是否完成若完成则结束
		}
		
	}
	private void shadowmove(CharacterBase character,int i) {
		// TODO 自动生成的方法存根
		if(character.object==ObjectType.ZOMBIEOBJ)
		{      zombieshadowlist.get(i).x=character.x;
		zombieshadowlist.get(i).y=character.y+30;
       }
		else if(character.object==ObjectType.BOSSOBJ)
		{ bossshadow.x=character.x;
		bossshadow.y=character.y+30;}
		else	if(character.object==ObjectType.HEROOBJ)
		{heroshadowlist.get(i).x=character.x;
		heroshadowlist.get(i).y=character.y+30;}
		
		
		
	}
	
    private void hpbarcreate() {
		// TODO 自动生成的方法存根
        //生成总血量
        for(int i=0;i<CharacterAttributes.HERO_HP/10;i++)
        {
        	HPBar hpbar=new HPBar();
        	try {
				hpbar.img=ImageIO.read(new File("images/feedback/hppanel/hp0.png"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
            hpbar.x=PanelAttributes.PANEL_WIDTH-(hpbar.img.getWidth(this)+5)*(i+1);//20为可调间距
            hpbar.y=20;
           hpbarlist.add(hpbar);
        }
        hpbarupdate();

	}



	private void hpbarupdate() {
		// TODO 自动生成的方法存根
        //生成真血量
        if(heroCharacter.HP>=10)hpBar_length=heroCharacter.HP/10;
        else hpBar_length=1;
        for(int i=0;i<hpBar_length;i++)
        {
        	hpbarlist.get(i).status=CharacterAttributes.HPBAR_SMALL;
        	try {
        		hpbarlist.get(i).img=ImageIO.read(new File("images/feedback/hppanel/hp1.png"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        	
        	
        }
        heartbeat1(1);//第一种心跳


//生成空血量
        for(int i=0;i<CharacterAttributes.HERO_HP/10-hpBar_length;i++)
        {
        	hpbarlist.get(hpbarlist.size()-i-1).status=CharacterAttributes.HPBAR_EMPTY;
        	try {
        		hpbarlist.get(hpbarlist.size()-i-1).img=ImageIO.read(new File("images/feedback/hppanel/hp0.png"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }
        
	}







	private void heartbeat1(int i) {
		// TODO 自动生成的方法存根
        if(hpbar_index<hpBar_length-i)
        {
        try {
			hpbarlist.get(i+hpbar_index++).img=ImageIO.read(new File("images/feedback/hppanel/hp2.png"));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        
        }
        else
        {
            try {
    			hpbarlist.get(hpBar_length-i).img=ImageIO.read(new File("images/feedback/hppanel/hp2.png"));
    		} catch (IOException e1) {
    			// TODO 自动生成的 catch 块
    			e1.printStackTrace();
    		}
            hpbar_index=0;
        	
        }
        
	}
}
