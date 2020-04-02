/*
 * ZombieCharacter.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月28日
 */
package game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ZombieCharacter extends CharacterBase{

    public ZombieCharacter() {
        super();
        try {
            noMoveLeftImg = ImageIO.read(new File("images/character/zombie/walk/nomoveleft.png"));
            noMoveRightImg = ImageIO.read(new File("images/character/zombie/walk/nomoveright.png"));
            for (int i = 0; i < walkRightImg.length; i++) {
                walkRightImg[i] = ImageIO.read(new File("images/character/zombie/walk/moveright" + i +".png"));
            }
            for (int i = 0; i < walkLeftImg.length; i++) {
                walkLeftImg[i] = ImageIO.read(new File("images/character/zombie/walk/moveleft" + i +".png"));
            }
            for (int i = 0; i < commonAttackLeftImg.length; i++) {
                commonAttackLeftImg[i] = ImageIO.read(new File("images/character/zombie/attack/commonattackleft" + i +".png"));
            }
            for (int i = 0; i < commonAttackRightImg.length; i++) {
                commonAttackRightImg[i] = ImageIO.read(new File("images/character/zombie/attack/commonattackright" + i +".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.object=ObjectType.ZOMBIEOBJ;
        this.img = noMoveLeftImg;
        this.HP = CharacterAttributes.ZOMBINE_HP;
        this.attackPower = CharacterAttributes.ZOMBINE_ATTACK_POWER;
        this.move_Speed_X = CharacterAttributes.ZOMBINE_MOVE_SPEED_X;
        this.move_Speed_Y = CharacterAttributes.ZOMBINE_MOVE_SPPED_Y;
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
            }
        } else if (this.isAttacking == true) {
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
        }
        return false;
    }

    boolean inAttackRange(CharacterBase character) {
        if (Math.abs((this.y + this.img.getHeight(null) / 2) - (character.y + character.img.getHeight(null) / 2)) < 30) {
//            System.out.println("zombie" + this.x + this.img.getWidth(null));
//            System.out.println("hero" + character.x);
            if (this.oriented == RunningAttributes.ORIENTED_RIGHT && (this.x + 90> character.x)) {
                this.attackOriented = CharacterAttributes.ATTACKING_RIGHT;
                this.imageCommonAttackIndex = 0;
                return true;
            } else if (this.oriented == RunningAttributes.ORIENTED_LEFT && (this.x - this.img.getWidth(null) / 2 < character.x)) {
                this.attackOriented = CharacterAttributes.ATTACKING_LEFT;
                this.imageCommonAttackIndex = 0;
                return true;
            }
        }

        return false;
    }

    boolean canDoDamage(CharacterBase character) {
        if (Math.abs((this.y + this.img.getHeight(null) / 2) - (character.y + character.img.getHeight(null) / 2)) < 30) {
            if (this.attackOriented == CharacterAttributes.ATTACKING_RIGHT) {
                if (this.x + 90> character.x) {
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
