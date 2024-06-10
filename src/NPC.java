/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.*;

/**
 *
 * @author csbot
 */
public class NPC {

    int level;
    int money;
    int exp;
    String statType;
    String name;
    String mpName;

    boolean defeated = false;

    int hp, damage, defence;

    int[] baseStats = new int[3];

    public NPC(int level, String name, String mpName, String statType) {
        this.level = level;
        this.name = name;
        this.mpName = mpName;
        this.statType = statType;

    }

    public void npcAction(NPC npc) {

        int action = Main.random(0, npc.level + 2);

        if (action >= 14) {
            npc.heal(npc);
        } else if (action > 7 && action <= 9) {
            npc.buff(npc);
        } else {
            npc.attack(npc, Player.playerMP);
        }
    }

    public static void randomEncounter() {

        NPC mMP = null;

        boolean startBattle = false;
        String statType = "";

        switch (Main.random(1, 3)) {
            case 1:
                statType = "Attack";
                break;
            case 2:
                statType = "Health";
                break;
            case 3:
                statType = "Defence";
                break;
        }

        switch (Main.random(1, 3)) {
            case 1:
                mMP = new NPC(Main.random(Area.AreaMinLevel, Area.AreaMaxLevel), "Dungar", "Dungar", statType);
                break;
            case 2:
                mMP = new NPC(Main.random(Area.AreaMinLevel, Area.AreaMaxLevel), "Soloon", "Soloon", statType);
                break;
            case 3:
                mMP = new NPC(Main.random(Area.AreaMinLevel, Area.AreaMaxLevel), "Opel", "Opel", statType);
                break;
        }

        mMP = setAttributes(mMP);
        mMP.money = 0;

        if (Main.random(0, 100) <= 85) {
            startBattle = true;
        }

        if (startBattle == true) {
            JOptionPane.showMessageDialog(null, "You encountered a wild " + mMP.name);
            Main.Battle(mMP);
        } else {
            JOptionPane.showMessageDialog(null, "You encountered nothing");
            Area.leave();
        }
    }

    public static NPC setAttributes(NPC npc) {

        int hp = 10;
        int damage = 3;
        int defence = 0;

        

        switch (npc.statType) {
            case "Health":
                hp = 15;
                damage = 2;
                break;
            case "Attack":
                damage = 5;
                hp = 8;
                break;
            case "Defence":
                defence = 1;
                hp = 9;
                break;
            case "All":
                hp = 15;
                damage = 5;
                defence = 2;
                break;
        }

        switch (npc.mpName) {
            case "Dungar":
               damage += 1;
                break;
            case "Opel":
                defence++;
                break;
            case "Soloon":
                hp += 3;
                break;
            default:
        }

        
        npc.hp = hp + (npc.level/3);
        npc.damage = damage + (npc.level/4);
        npc.defence = defence + ((npc.level/10)*3);
        
        if (npc != Player.playerMP) {
            npc.money = npc.level * 1000;
            npc.exp = npc.level * 100;
        }

        npc.baseStats[0] = npc.hp;
        npc.baseStats[1] = npc.damage;
        npc.baseStats[2] = npc.defence;

        return npc;
    }

    public void attack(NPC npc, NPC enemy) {

        JOptionPane.showMessageDialog(null, npc.name + " attacks");

        int damage = Main.random(npc.damage - 2, npc.damage);

        if (enemy.defence < damage) {

            damage -= enemy.defence;
            enemy.hp -= damage;

            JOptionPane.showMessageDialog(null, damage + " damage was dealt");

        } else {

            JOptionPane.showMessageDialog(null, enemy.name + "'s defence was too high and 0 damage was dealt");

        }

    }

    public void buff(NPC npc) {
        JOptionPane.showMessageDialog(null, npc.name + " buffed their " + npc.statType);

        switch (npc.statType) {
            case "Attack":
                npc.damage = npc.baseStats[1] + npc.damage / 10;
                break;
            case "Defence":
                npc.defence = npc.baseStats[2] + npc.defence / 10;
                break;
            case "Health":
                npc.hp = npc.baseStats[0] + npc.hp / 10;
                break;
            default:
                System.out.println("You mispelled something");
        }

    }

    public void heal(NPC npc) {

        JOptionPane.showMessageDialog(null, npc.name + " healed themselves");

        int healAmount = npc.hp - Main.random(npc.level, npc.hp / 2);
        npc.hp += healAmount;

        JOptionPane.showMessageDialog(null, healAmount + " hp was gained");

        while (npc.hp > npc.baseStats[0]) {
            npc.hp--;
        }
    }
}
