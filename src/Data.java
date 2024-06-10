/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
/**
 *
 * @author bothac
 */
public class Data {

    //opel: Defence, dungar: damage, soloon: health, kripted: all
    static NPC damien = new NPC(1, "Damien", "Dungar", "Attack");

    static NPC tChristiaan = new NPC(5, "Christiaan", "Opel", "Defence");
    static NPC tJirah = new NPC(8, "Jirah", "Dungar", "Attack");
    static NPC tLiam = new NPC(10, "Liam", "Soloon", "Health");
    static NPC tDave = new NPC(15, "David", "Soloon", "Defence");

    static NPC gSiegfried = new NPC(18, "SeigFry", "Opel", "Attack");
    
    static NPC mKripted = new NPC(20, "Kripted", "Kripted", "All");

    public static void runData() {

        damien = NPC.setAttributes(damien);
        tChristiaan = NPC.setAttributes(tChristiaan);
        tJirah = NPC.setAttributes(tJirah);
        tLiam = NPC.setAttributes(tLiam);
        tDave = NPC.setAttributes(tDave);
        gSiegfried = NPC.setAttributes(gSiegfried);
        mKripted = NPC.setAttributes(mKripted);
        
        Player.setMPStats();
    }
    
    public static void printData(NPC npc) {
        JOptionPane.showMessageDialog(null, "Name: " + npc.name + "\nLevel: " + npc.level + "/20\nHP: " + npc.hp + "/" + npc.baseStats[0] + "\nATK: " + npc.damage + "\nDEF: " + npc.defence);
    }
}