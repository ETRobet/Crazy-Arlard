/*
 * BossCharacter.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月28日
 */
package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BossCharacter extends CharacterBase {
    Image[] specialAttackLeftImg = new Image[CharacterAttributes.BOSS_SPECIAL_ATTACK_NUM];
    Image[] specialAttackRightImg = new Image[CharacterAttributes.BOSS_SPECIAL_ATTACK_NUM];
    int imageSpecialAttackIndex;

    public BossCharacter() {
        super();
        try {
            noMoveLeftImg = ImageIO.read(new File("images/character/boss/walk/nomoveleft.png"));
            noMoveRightImg = ImageIO.read(new File("images/character/boss/walk/nomoveright.png"));
            for (int i = 0; i < walkRightImg.length; i++) {
                walkRightImg[i] = ImageIO.read(new File("images/character/boss/walk/moveright" + i +".png"));
            }
            for (int i = 0; i < walkLeftImg.length; i++) {
                walkLeftImg[i] = ImageIO.read(new File("images/character/boss/walk/moveleft" + i +".png"));
            }
            for (int i = 0; i < commonAttackLeftImg.length; i++) {
                commonAttackLeftImg[i] = ImageIO.read(new File("images/character/boss/attack/commonattackleft" + i +".png"));
            }
            for (int i = 0; i < commonAttackRightImg.length; i++) {
                commonAttackRightImg[i] = ImageIO.read(new File("images/character/boss/attack/commonattackright" + i +".png"));
            }
            for (int i = 0; i < specialAttackLeftImg.length; i++) {
                specialAttackLeftImg[i] = ImageIO.read(new File("images/character/boss/attack/specialattackleft" + i +".png"));
            }
            for (int i = 0; i < specialAttackRightImg.length; i++) {
                specialAttackRightImg[i] = ImageIO.read(new File("images/character/boss/attack/specialattackright" + i +".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.object=ObjectType.BOSSOBJ;
        this.img = noMoveLeftImg;
        this.HP = CharacterAttributes.BOSS_HP;
        this.attackPower = CharacterAttributes.BOSS_ATTACK_POWER;
        this.move_Speed_X = CharacterAttributes.BOSS_MOVE_SPEED_X;
        this.move_Speed_Y = CharacterAttributes.BOSS_MOVE_SPEED_Y;
        this.oriented = RunningAttributes.ORIENTED_LEFT;
    }

    public void updateDirection(HeroCharacter heroCharacter) {
        if (this.x < heroCharacter.x - 20*((int)(Math.random()*6)) + 20) {
            this.xMove = RunningAttributes.MOVE_DIRECTION_RIGHT;
        } else if (this.x > heroCharacter.x + 20*((int)(Math.random()*6)) + 20) {
            this.xMove = RunningAttributes.MOVE_DIRECTION_LEFT;
        } else {
            this.xMove = RunningAttributes.MOVE_DIRECTION_NO_MOVE;
        }

        if (this.y < heroCharacter.y - 10*((int)(Math.random()*6))) {
            this.yMove = RunningAttributes.MOVE_DIRECTION_DOWN;
        } else if (this.y > heroCharacter.y + 10*((int)(Math.random()*6))) {
            this.yMove = RunningAttributes.MOVE_DIRECTION_UP;
        } else {
            this.yMove = RunningAttributes.MOVE_DIRECTION_NO_MOVE;
        }
    }

    public boolean battle(CharacterBase character) {
        if (this.isAttacking == false) {
            this.isAttacking = inAttackRange(character);
//            System.out.println(this.isAttacking);
            if (this.isAttacking == true) {
                this.movable = false;
                attackMethod = (int)(Math.random()*2);
            }
        } else if (this.isAttacking == true) {
            if (attackMethod == CharacterAttributes.BOSS_ATTACK_METHOD_ONE) {
                imageCommonAttackIndex ++;
                if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {
                    this.img = commonAttackLeftImg[imageCommonAttackIndex / 15 % CharacterAttributes.COMMON_ATTACK_NUM];
                } else if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                    this.img = commonAttackRightImg[imageCommonAttackIndex / 15 % CharacterAttributes.COMMON_ATTACK_NUM];
                }
                if (imageCommonAttackIndex == CharacterAttributes.COMMON_ATTACK_NUM * 15) {
                    this.isAttacking = false;
                    this.movable = true;
                    if(canDoDamage(character)) {
                        character.beHurt(this.attackPower);
                        return true;
//                    System.out.println(character.HP);
                    }
                }
            } else if (this.attackMethod == CharacterAttributes.BOSS_ATTACK_METHOD_TWO) {
                imageSpecialAttackIndex ++;
                if (this.attackOriented == CharacterAttributes.ATTACKING_LEFT) {
                    this.img = specialAttackLeftImg[imageSpecialAttackIndex / 22 % CharacterAttributes.BOSS_SPECIAL_ATTACK_NUM];
                } else if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                    this.img = specialAttackRightImg[imageSpecialAttackIndex / 22 % CharacterAttributes.BOSS_SPECIAL_ATTACK_NUM];
                }
                if (imageSpecialAttackIndex == CharacterAttributes.BOSS_SPECIAL_ATTACK_NUM * 22) {
                    this.isAttacking = false;
                    this.movable = true;
                    if(canDoDamage(character)) {
                        character.beHurt(this.attackPower);
                        return true;
//                    System.out.println(character.HP);
                    }
                }
            }

        }
        return false;
    }

    boolean inAttackRange(CharacterBase character) {
//        System.out.println("boss y " + this.y);
//        System.out.println("hero y " + character.y);
        if (Math.abs((this.y + this.img.getHeight(null) / 2) - (character.y + character.img.getHeight(null) / 2)) < 30) {
//            System.out.println("boss x " + this.x + this.img.getWidth(null));
//            System.out.println("hero x " + character.x);
            if (this.oriented == RunningAttributes.ORIENTED_RIGHT && (character.x - this.x < 70)) {
                this.attackOriented = CharacterAttributes.ATTACKING_RIGHT;
                this.imageCommonAttackIndex = 0;
                this.imageSpecialAttackIndex = 0;
                return true;
            } else if (this.oriented == RunningAttributes.ORIENTED_LEFT && (this.x - this.img.getWidth(null) / 2 < character.x - 50)) {
                this.attackOriented = CharacterAttributes.ATTACKING_LEFT;
                this.imageCommonAttackIndex = 0;
                this.imageSpecialAttackIndex = 0;
                return true;
            }
        }

        return false;
    }

    boolean canDoDamage(CharacterBase character) {
        if (Math.abs((this.y + this.img.getHeight(null) / 2) - (character.y + character.img.getHeight(null) / 2)) < 20) {
            if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                if (this.x < character.x) {
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
}
