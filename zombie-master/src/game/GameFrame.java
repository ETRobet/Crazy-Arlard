/*
 * GameFrame.java
 *
 * Author:ZhangBinjie@Penguin
 * Created on: 2018年8月27日
 */
package game;

import javax.swing.*;

public class GameFrame extends JFrame {
    int status;
    IndexPanel indexPanel;
    RunningSceneOnePanel sceneOnePanel;
    RunningSceneTwoPanel sceneTwoPanel;
    RunningSceneThreePanel sceneThreePanel;
    GapScene gapScene;
    FailScene failScene;
    SuccessScene successScene;
    GameInfoPanel gameInfoPanel;

    public GameFrame() {
        this.setTitle("贪玩阿拉德");
        this.setSize(FrameAttributes.FRAME_WIDTH, FrameAttributes.FRAME_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
    }

    public void startProcess() {
        status = GameStatus.GAME_INDEX;
        while (true) {

            status = GameStatus.GAME_INDEX;

            indexPanel = new IndexPanel();
            this.add(indexPanel);
            this.setVisible(true);
            status = indexPanel.work();
            this.setVisible(false);
            this.remove(indexPanel);
            if (status == GameStatus.GAME_RUNNING) {
                boolean passStatus = RunningAttributes.SCENE_PASS;
                HeroCharacter heroCharacter = new HeroCharacter();
                for (int i = 1; i <= RunningAttributes.SCENE_NUMS; i++) {
                    if (i == 1) {
                        this.sceneOnePanel = new RunningSceneOnePanel(heroCharacter);
                        this.add(sceneOnePanel);
                        this.setVisible(true);
                        passStatus = sceneOnePanel.work();
                        this.setVisible(false);
                        this.remove(sceneOnePanel);
                    } else if (i == 2) {
                        this.sceneTwoPanel = new RunningSceneTwoPanel(heroCharacter);
                        this.add(sceneTwoPanel);
                        this.setVisible(true);
                        passStatus = sceneTwoPanel.work();
                        this.setVisible(false);
                        this.remove(sceneTwoPanel);
                    } else if (i == 3) {
                        this.sceneThreePanel = new RunningSceneThreePanel(heroCharacter);
                        this.add(sceneThreePanel);
                        this.setVisible(true);
                        passStatus = sceneThreePanel.work();
                        this.setVisible(false);
                        this.remove(sceneThreePanel);
                    }

                    if (passStatus == RunningAttributes.SCENE_FAIL) {
                        failScene = new FailScene();
                        this.add(failScene);
                        this.setVisible(true);
                        status = failScene.work();
                        this.setVisible(false);
                        this.remove(failScene);
                        break;
                    } else if (passStatus == RunningAttributes.SCENE_PASS) {
                        if (i != 3) {
                            gapScene = new GapScene();
                            this.add(gapScene);
                            this.setVisible(true);
                            status = gapScene.work();
                            this.setVisible(false);
                            this.remove(gapScene);
                            if (status == GameStatus.GAME_NEXT) {
                                continue;
                            } else if(status == GameStatus.GAME_INDEX) {
                                break;
                            }
                        } else {
                            successScene = new SuccessScene();
                            this.add(successScene);
                            this.setVisible(true);
                            status = successScene.work();
                            this.setVisible(false);
                            this.remove(successScene);
                            break;
                        }
                    }
                }

            } else if (status == GameStatus.GAME_INFO) {
                gameInfoPanel = new GameInfoPanel();
                this.add(gameInfoPanel);
                this.setVisible(true);
                status = gameInfoPanel.work();
                this.setVisible(false);
                this.remove(gameInfoPanel);
            } else if (status == GameStatus.GAME_EXIT){
                System.exit(0);
            }
        }
    }

}
