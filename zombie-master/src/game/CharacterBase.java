/*
 * CharacterBase.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月28日
 */
package game;

import java.awt.*;

public class CharacterBase {
    Image img;
    int object;
    int HP;
    int attackPower;
    int x;
    int y;
    int xMove;
    int yMove;
    boolean movable;
    boolean isAttacking;
    int attackOriented;
    int attackMethod;
    int oriented;
    boolean attackable;
    Image[] commonAttackLeftImg = new Image[CharacterAttributes.COMMON_ATTACK_NUM];
    Image[] commonAttackRightImg = new Image[CharacterAttributes.COMMON_ATTACK_NUM];
    Image[] walkLeftImg  = new Image[5];
    Image[] walkRightImg  = new Image[5];
    Image noMoveLeftImg;
    Image noMoveRightImg;
    int imageMoveRightIndex;
    int imageMoveLeftIndex;
    int imageCommonAttackIndex;
    int move_X_Min;
    int move_X_Max;
    int move_Y_Min;
    int move_Y_Max;
    int move_Speed_X;
    int move_Speed_Y;

    public CharacterBase() {
    	this.object=ObjectType.DEFAULTOBJ;
        this.imageMoveRightIndex = 0;
        this.imageMoveLeftIndex = 0;
        this.movable = true;
        this.isAttacking = false;
        this.attackable = true;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public void setMovebalData(int xMin, int xMax, int yMin, int yMax) {
        this.move_X_Min = xMin;
        this.move_X_Max = xMax;
        this.move_Y_Min = yMin;
        this.move_Y_Max = yMax;
    }

    public void initPosition(int xPosition, int yPosition) {
        this.x = xPosition;
        this.y = yPosition;
        this.xMove = RunningAttributes.MOVE_DIRECTION_NO_MOVE;
        this.yMove = RunningAttributes.MOVE_DIRECTION_NO_MOVE;
    }

    public void move() {
        if (this.movable == true) {
            if (xMove == RunningAttributes.MOVE_DIRECTION_LEFT) {
                if (this.x > this.move_X_Min) {
                    this.x -= move_Speed_X;
                }
                imageMoveLeftIndex ++;
                imageMoveLeftIndex %= walkLeftImg.length * 5;
                img = walkLeftImg[imageMoveLeftIndex / 5];
                oriented = RunningAttributes.ORIENTED_LEFT;
            } else if (xMove == RunningAttributes.MOVE_DIRECTION_RIGHT) {
                if (this.x < this.move_X_Max) {
                    this.x += move_Speed_X;
                }
                imageMoveRightIndex ++;
                imageMoveRightIndex %= walkRightImg.length * 5;
                img = walkRightImg[imageMoveRightIndex / 5];
                oriented = RunningAttributes.ORIENTED_RIGHT;
            }

            if (yMove == RunningAttributes.MOVE_DIRECTION_UP) {
                if (this.y > this.move_Y_Min) {
                    this.y -= move_Speed_Y;
                }
            } else if (yMove == RunningAttributes.MOVE_DIRECTION_DOWN) {
                if (this.y < this.move_Y_Max) {
                    this.y += move_Speed_Y;
                }
            }

            if (xMove == RunningAttributes.MOVE_DIRECTION_NO_MOVE && yMove != RunningAttributes.MOVE_DIRECTION_NO_MOVE) {
                imageMoveRightIndex ++;
                imageMoveRightIndex %= walkLeftImg.length * 5;
                img = walkRightImg[imageMoveRightIndex / 5];
            }

            if (xMove == RunningAttributes.MOVE_DIRECTION_NO_MOVE && yMove == RunningAttributes.MOVE_DIRECTION_NO_MOVE) {
                if (this.oriented == RunningAttributes.ORIENTED_LEFT) {
                    this.img = noMoveLeftImg;
                } else if (this.oriented == RunningAttributes.ORIENTED_RIGHT) {
                    this.img = noMoveRightImg;
                }
            }
        }

    }

    public void beHurt(int damageAmount) {
        if (this.attackable == true) {
            this.HP -= damageAmount;
        }
    }

}
