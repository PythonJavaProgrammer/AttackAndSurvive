/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;

/**
 *
 * @author csbot
 */
public class Main {

    static boolean hasSave;

    Main() {
        
        Data.runData();
        retrieveData();
        
        startGame();
        
        
    }

    public static void Battle(NPC npc) {

        Area.inBattle = true;
        boolean npcTurn = false;

        Data.printData(Player.playerMP);
        Data.printData(npc);

        while (Player.playerMP.hp > 0 && npc.hp > 0) {

            if (npcTurn == true) {
                npc.npcAction(npc);
                npcTurn = false;
            } else {
                Player.action(npc);
                npcTurn = true;
            }

            Data.printData(Player.playerMP);
            Data.printData(npc);

        }

        if (Player.playerMP.hp <= 0) {
            JOptionPane.showMessageDialog(null, Player.playerMP.name + " fainted");
            gameOver();
        } else {
            npc.defeated = true;
            Player.money += npc.money;
            Player.playerMP.exp += npc.exp;

            JOptionPane.showMessageDialog(null, "You succesfully defeated " + npc.name);
            JOptionPane.showMessageDialog(null, "You recieve R" + npc.money);
            JOptionPane.showMessageDialog(null, "You receive " + npc.exp + "EXP");
        }
        
        npc = NPC.setAttributes(npc);
        Player.setMPStats();
    }

    public static void startGame() {

        Area.options.clear();

        if (Menu.yesNoMenu("PlayGame?", "Would you like to play MonsterPocket") != 0) {
            System.exit(0);
        }

        if (hasSave == true) {

            switch (Menu.yesNoMenu("UseSave?", "A save as been detected, Load Save?")) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Welcome " + Player.name);
                    
                    Area.leave();
                    break;
                case 1:
                    hasSave = false;

                    do {
                        Player.name = JOptionPane.showInputDialog("Please enter player name");
                    } while (Menu.yesNoMenu("Sure?", "You have chosen " + Player.name + " " + "will this be your name") != 0);

                    JOptionPane.showMessageDialog(null, "Welcome " + Player.name);

                    Area.village();

                    break;
            }

        } else {

            do {
                Player.name = JOptionPane.showInputDialog("Please enter player name");
            } while (Menu.yesNoMenu("Sure?", "You have chosen " + Player.name + "Will this be your name") != 0);

            JOptionPane.showMessageDialog(null, "Welcome " + Player.name);

            saveData();
            Area.village();

        }

    }

    public static void gameOver() {
        
        if (Menu.yesNoMenu("Start?", "Will you continue") != 0) {
            saveData();
            System.exit(0);
        }
        else {
            JOptionPane.showMessageDialog(null, "May you win this time");
            Area.mpCenter();
        }
        
    }
    
    public static void ending() {
        JOptionPane.showMessageDialog(null, "You have received the [" + Player.ending.toUpperCase() + "] Ending");
        
    }
    
    public static void retrieveData() {
        //this will retrieve any save data
    }

    public static void saveData() {
        //this will save data
    }
    
    public static void deleteData() {
        //this will delete all data
    }

    public static int random(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + min);
        return num;
    }

    public static void main(String[] args) {
        new Main();
    }
}
