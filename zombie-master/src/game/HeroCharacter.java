/*
 * HeroCharacter.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月28日
 */
package game;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class HeroCharacter extends CharacterBase{
    Image[] specialAttackLeftImg = new Image[CharacterAttributes.HERO_SPECIAL_ATTACK_NUM];
    Image[] specialAttackRightImg = new Image[CharacterAttributes.HERO_SPECIAL_ATTACK_NUM];
    Image ultimateSkillImg;
    int imageSpecialAttackIndex;
    int imageUltimateSkillX;
    AudioClip commonAttackAudio;
    AudioClip specialAttackAudio;
    AudioClip ultimateskillAudio;

    public HeroCharacter() {
        super();
        try {
            noMoveLeftImg = ImageIO.read(new File("images/character/hero/walk/nomoveleft.png"));
            noMoveRightImg = ImageIO.read(new File("images/character/hero/walk/nomoveright.png"));
            for (int i = 0; i < walkRightImg.length; i++) {
                walkRightImg[i] = ImageIO.read(new File("images/character/hero/walk/moveright" + i +".png"));
            }
            for (int i = 0; i < walkLeftImg.length; i++) {
                walkLeftImg[i] = ImageIO.read(new File("images/character/hero/walk/moveleft" + i +".png"));
            }
            for (int i = 0; i < commonAttackLeftImg.length; i++) {
                commonAttackLeftImg[i] = ImageIO.read(new File("images/character/hero/attack/commonattackleft" + i +".png"));
            }
            for (int i = 0; i < commonAttackRightImg.length; i++) {
                commonAttackRightImg[i] = ImageIO.read(new File("images/character/hero/attack/commonattackright" + i +".png"));
            }
            for (int i = 0; i < specialAttackLeftImg.length; i++) {
                specialAttackLeftImg[i] = ImageIO.read(new File("images/character/hero/attack/specialattackleft" + i +".png"));
            }
            for (int i = 0; i < specialAttackRightImg.length; i++) {
                specialAttackRightImg[i] = ImageIO.read(new File("images/character/hero/attack/specialattackright" + i +".png"));
            }
            ultimateSkillImg = ImageIO.read(new File("images/character/hero/attack/ultimateskill.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.object=ObjectType.HEROOBJ;
        this.img = noMoveRightImg;
        this.HP = CharacterAttributes.HERO_HP;
        this.attackPower = CharacterAttributes.HERO_ATTACK_POWER;
        this.move_Speed_X = CharacterAttributes.HERO_MOVE_SPEED_X;
        this.move_Speed_Y = CharacterAttributes.HERO_MOVE_SPEED_Y;
        this.oriented = RunningAttributes.ORIENTED_RIGHT;
        try {
            commonAttackAudio = Applet.newAudioClip(new File("sounds/hero/commonattack.wav").toURI().toURL());
            specialAttackAudio = Applet.newAudioClip(new File("sounds/hero/specialattack.wav").toURI().toURL());
            ultimateskillAudio = Applet.newAudioClip(new File("sounds/hero/ultimateskill.wav").toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void battle(ArrayList<ZombieCharacter> zombinelist) {
        if (this.isAttacking == true) {
            if (attackMethod == CharacterAttributes.HERO_ATTACK_METHOD_ONE) {
                imageCommonAttackIndex ++;
                if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {
                    this.img = commonAttackLeftImg[imageCommonAttackIndex / 4 % CharacterAttributes.COMMON_ATTACK_NUM];
                } else if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                    this.img = commonAttackRightImg[imageCommonAttackIndex / 4 % CharacterAttributes.COMMON_ATTACK_NUM];
                }
                if (imageCommonAttackIndex == CharacterAttributes.COMMON_ATTACK_NUM * 4) {
                    this.isAttacking = false;
                    this.movable = true;
                    for (int i = 0; i < zombinelist.size(); i++) {
                        CharacterBase character = zombinelist.get(i);
                        if(canDoDamage(character)) {
                            character.beHurt(this.attackPower);
//                            System.out.println(character.HP);
                        }
                    }
                }
            } else if (this.attackMethod == CharacterAttributes.HERO_ATTACK_METHOD_TWO) {
                imageSpecialAttackIndex ++;
                if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {
                    this.img = specialAttackLeftImg[imageSpecialAttackIndex / 5 % CharacterAttributes.HERO_SPECIAL_ATTACK_NUM];
                } else if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                    this.img = specialAttackRightImg[imageSpecialAttackIndex / 5 % CharacterAttributes.HERO_SPECIAL_ATTACK_NUM];
                }
                if (imageSpecialAttackIndex == CharacterAttributes.HERO_SPECIAL_ATTACK_NUM * 5) {
                    this.isAttacking = false;
                    this.movable = true;
                    for (int i = 0; i < zombinelist.size(); i++) {
                        CharacterBase character = zombinelist.get(i);
                        if(canDoDamage(character)) {
                            character.beHurt(this.attackPower);
//                            System.out.println(character.HP);
                        }
                    }
                }
            } else if (this.attackMethod == CharacterAttributes.HERO_ULTIMATE_SKILL) {
                imageUltimateSkillX += 72;
                if (imageUltimateSkillX >= 1440) {
                    this.isAttacking = false;
                    this.movable = true;
//                    System.out.println(this.isAttacking);
//                    System.out.println(this.movable);
                    for (int i = 0; i < zombinelist.size(); i++) {
                        zombinelist.get(i).beHurt(CharacterAttributes.HERO_ULTIMATE_POWER);
                    }
                }
            }

        }
    }

    public void battle(CharacterBase character) {
        if (this.isAttacking == true) {
            if (attackMethod == CharacterAttributes.HERO_ATTACK_METHOD_ONE) {
                imageCommonAttackIndex ++;
                if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {
                    this.img = commonAttackLeftImg[imageCommonAttackIndex / 4 % CharacterAttributes.COMMON_ATTACK_NUM];
                } else if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                    this.img = commonAttackRightImg[imageCommonAttackIndex / 4 % CharacterAttributes.COMMON_ATTACK_NUM];
                }
                if (imageCommonAttackIndex == CharacterAttributes.COMMON_ATTACK_NUM * 4) {
                    this.isAttacking = false;
                    this.movable = true;
                    if (canDoDamage(character)) {
                        character.beHurt(this.attackPower);
                    }
                }
            } else if (this.attackMethod == CharacterAttributes.BOSS_ATTACK_METHOD_TWO) {
                imageSpecialAttackIndex ++;
                if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {
                    this.img = specialAttackLeftImg[imageSpecialAttackIndex / 5 % CharacterAttributes.HERO_SPECIAL_ATTACK_NUM];
                } else if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                    this.img = specialAttackRightImg[imageSpecialAttackIndex / 5 % CharacterAttributes.HERO_SPECIAL_ATTACK_NUM];
                }
                if (imageSpecialAttackIndex == CharacterAttributes.HERO_SPECIAL_ATTACK_NUM * 5) {
                    this.isAttacking = false;
                    this.movable = true;
                    if (canDoDamage(character)) {
                        character.beHurt(this.attackPower);
                    }
                }
            } else if (this.attackMethod == CharacterAttributes.HERO_ULTIMATE_SKILL) {
                imageUltimateSkillX += 72;
                this.attackable = false;
                if (imageUltimateSkillX >= 1440) {
                    this.isAttacking = false;
                    this.movable = true;
                    this.attackable = true;
                    System.out.println(this.isAttacking);
                    System.out.println(this.movable);
                    character.beHurt(CharacterAttributes.HERO_ULTIMATE_POWER);
                }
            }

        }
    }

    boolean canDoDamage(CharacterBase character) {
//        System.out.println("hero y " + this.y);
//        System.out.println("enemy y " + character.y);
        if (Math.abs((this.y + this.img.getHeight(null) / 2) - (character.y + character.img.getHeight(null) / 2)) < 30) {
//            System.out.println("hero x " + this.x + this.img.getWidth(null));
//            System.out.println("enemy x " + character.x);
            if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                if (this.x > character.x - 230) {
                    return true;
                }
            } else if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {
                if (this.x - this.img.getWidth(null) / 2 < character.x) {
                    return true;
                }
            }
        }
        return  false;
    }

    public void drawUltimateSkill(Graphics g) {
        g.drawImage(ultimateSkillImg, imageUltimateSkillX, 0, null);
    }

}
