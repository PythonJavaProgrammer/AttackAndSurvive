/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.*;

/**
 *
 * @author csbot
 */
public class Player {

    static String name;
    static String statType;

    static NPC playerMP = new NPC(1, "", "", "");

    static int hp;
    static int damage;
    static int defence;
    static int exp = 0;

    static String ending;
    
    static boolean HasMP = false;
    static boolean HasGoneHome = false;
    static boolean DiscoveredSecret = false;

    static int courage = 0;
    static int money = 0;

    public static void action(NPC enemy) {
        Area.options.clear();

        Area.options.add("Attack");
        Area.options.add("Buff");
        Area.options.add("Heal");

        switch (Menu.optionMenu(Area.options)) {
            case 1:
                playerMP.attack(playerMP, enemy);
                break;
            case 2:
                playerMP.buff(playerMP);
                break;
            case 3:
                playerMP.heal(playerMP);
                break;
            default:
                throw new AssertionError();
        }

    }

    public static void setMPStats() { 

        playerMP.damage = playerMP.baseStats[1];
        playerMP.defence = playerMP.baseStats[2];
        
        while (playerMP.hp >= playerMP.baseStats[0]) {
            playerMP.hp--;
        }
        
        int level = 1;
        
        while (getLevel(level) <= playerMP.exp) {
            level++;
        }
        while (level > 20) {
            level--;
        }
        
        
        playerMP.level = level;
        
        playerMP = NPC.setAttributes(playerMP);
    }
    
    public static int getLevel(int x) {
        double a, b;
        int y;
        
        a = 1.7;
        b = 2;
        
        y = (int) (a * Math.pow(b, x));
        
        return y;
    }

}
