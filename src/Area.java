import java.util.*;
import javax.swing.*;

public class Area {

    static ArrayList<String> options = new ArrayList<>();
    static int AreaNum;
    static int AreaMaxLevel;
    static int AreaMinLevel;

    static boolean inSpecialArea = false;
    static boolean inBattle = false;

    /**
     * Displays a menu for the player to choose options in the Jot village.
     *
     */
    public static void village2() {
        options.clear();
        inSpecialArea = false;
        inBattle = false;

        options.add("go to Next Area");
        options.add("go to MP center");
        options.add("walk around the village");
        options.add("GO to facility");

        JOptionPane.showMessageDialog(null, "You are in Jot village");

        switch (Menu.optionMenu(options)) {
            case 1:
                nextArea();
                break;
            case 2:
                mpCenter();
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Have you heard about the ruines they found recently");
                JOptionPane.showMessageDialog(null, "You mean the old ones?");
                JOptionPane.showMessageDialog(null, "All runes are old.");
                JOptionPane.showMessageDialog(null, "Yeah but those are really old");
                JOptionPane.showMessageDialog(null, "Yeah, pretty much. But they say somethings escaped from it");
                JOptionPane.showMessageDialog(null, "Sureee...");
                JOptionPane.showMessageDialog(null, "It's true");
                JOptionPane.showMessageDialog(null, "Nothing else notable is heard");
                break;
            case 4:
                facility();
                break;
        }

    }

    /**
     * This method is responsible for handling the gym area in the game.
     * It clears the options list, sets the inSpecialArea and inBattle flags to false,
     * and checks if the Data.gSiegfried object is defeated.
     * If it is not defeated, it displays two messages and initiates a battle with the Data.gSiegfried object.
     * If it is defeated, it displays a message indicating that the gym is empty and the player has won.
     * If Data.gSiegfried is defeated and Data.mKripted is not defeated, it displays three messages,
     * including a blinding light that blinds the player and ends the game.
     * If Data.gSiegfried is defeated and Data.mKripted is defeated, it displays two messages,
     * including a feeling of immense accomplishment that falls over the player.
     * After displaying the messages, it sets the Player.ending variable and calls the Main.ending method.
     *
     */
    public static void gym() {
        options.clear();
        inSpecialArea = false;
        inBattle = false;

        if (!Data.gSiegfried.defeated) {
            JOptionPane.showMessageDialog(null, "Oh you're approaching me");
            JOptionPane.showMessageDialog(null, "Well then, let's battle");

            Main.Battle(Data.gSiegfried);
        } else {
            JOptionPane.showMessageDialog(null, "The gym is empty, you have won");
        }

        if (Data.gSiegfried.defeated && !Data.mKripted.defeated) {
            JOptionPane.showMessageDialog(null, "You have won, well done");
            JOptionPane.showMessageDialog(null, "As you go to receive your prize");
            JOptionPane.showMessageDialog(null, "You hear rumbling");
            JOptionPane.showMessageDialog(null, "A blinding light, blinds you. The end");

            Player.ending = "The end of the world";
            Main.ending();
        } else if (Data.gSiegfried.defeated && Data.mKripted.defeated) {
            JOptionPane.showMessageDialog(null, "You have won, well done");
            JOptionPane.showMessageDialog(null, "As you go to receive your prize");
            JOptionPane.showMessageDialog(null, "A feeling of immense accomplishment falls over you");

            Player.ending = "The warrior to end all warriors";
            Main.ending();
        }

    }

    public static void cave() {
        options.clear();
        inSpecialArea = true;
        inBattle = false;

        JOptionPane.showMessageDialog(null, "You walk into the caves");
        JOptionPane.showMessageDialog(null, "It's dark, but luckily");
        JOptionPane.showMessageDialog(null, "this place doens't actually exist so you can see perfectly");
        JOptionPane.showMessageDialog(null, "Then you see it");

        Main.Battle(Data.mKripted);

        if (Data.mKripted.defeated) {
            JOptionPane.showMessageDialog(null, "You defeated the Boss, well done");

            Player.ending = "The saviour of the world";
            Main.ending();
        }
    }

/**
 * This method represents the player entering the wilderness area in the game.
 * It clears the options list, sets the inSpecialArea flag to false, and increments the Player's courage.
 * It adds two options to the options list: "Next Area" and "Roam".
 * It then displays a message to the user indicating that they have entered the wilderness.
 * Based on the Player's courage level, it determines the emotion and displays it to the user.
 * It then prompts the user to choose an option using the optionMenu method from the Menu class.
 * Depending on the user's choice, it either navigates to the next area or calls the randomEncounter method from the NPC class.
 * If the user chooses to roam and encounters an NPC, it recursively calls the wilderness method again.
 * The method assumes that the Data class has instances of enemies.
 * */
    public static void wilderness() {
        options.clear();
        inSpecialArea = false;

        Player.courage++;

        options.add("Next Area");
        options.add("Roam");

        String emotion;

        JOptionPane.showMessageDialog(null, "You walk into the wilderness");
        if (Player.courage > 10) {
            emotion = "Happy";
        } else if (Player.courage > 5) {
            emotion = "Determined";
        } else {
            emotion = "Afraid";
        }
        JOptionPane.showMessageDialog(null, "You are " + emotion);

        switch (Menu.optionMenu(options)) {
            case 1:

                if (AreaNum == 1 && !Data.tChristiaan.defeated) {
                    while (!Data.tChristiaan.defeated) {
                        JOptionPane.showMessageDialog(null, "Hey newbie you have to defeat me before passing");
                        Main.Battle(Data.tChristiaan);
                    }
                    nextArea();
                } else if (!Data.tJirah.defeated && AreaNum == 2) {
                    while (!Data.tJirah.defeated) {
                        JOptionPane.showMessageDialog(null, "You have to defeat me before passing");
                        Main.Battle(Data.tJirah);
                    }
                    nextArea();
                } else if (!Data.tLiam.defeated && AreaNum == 4) {
                    while (!Data.tLiam.defeated) {
                        JOptionPane.showMessageDialog(null, "It's dangerous out there, defeat me before passing");
                        Main.Battle(Data.tLiam);
                    }
                    nextArea();
                } else if (!Data.tDave.defeated && AreaNum == 6) {
                    while (!Data.tDave.defeated) {
                        JOptionPane.showMessageDialog(null, "You want to fight the gym leader aye, well you gotta beat me first");
                        Main.Battle(Data.tDave);
                    }
                    JOptionPane.showMessageDialog(null, "I admit it, you're strong enough, go fight");
                    nextArea();

                } else {
                    nextArea();
                }

                break;
            case 2:
                NPC.randomEncounter();
                wilderness();
                break;
        }
    }

/**
 * This function is the entry point for the MonsterPocket Center area. It sets the player's
 * MonsterPocket stats, clears the options, sets the area to special, and sets the battle flag to false.
 * It also sets the maximum and minimum levels for the area to 1. It then adds two options to the
 * options list: "Heal" and "Check MonsterPocket stats". After that, it displays two dialogs
 * to the user: "You go to the MonsterPocket Center" and "Hello, how may I help you". If the player
 * does not have a MonsterPocket, it calls the selectMP function from the Menu class. If the player
 * has a MonsterPocket, it enters a while loop that continues until the inSpecialArea flag is false.
 * Inside the loop, it displays a dialog to the user asking them to select an option. Depending on
 * the user's selection, it performs different actions. If the user selects "Heal", it heals the
 * player's pokemon by setting its hp to its base health. If the user selects "Check MonsterPocket Stats"
 * the program displays the MP's stats using the printData function from the Data class.
 * */
    public static void mpCenter() {
        Player.setMPStats();
        options.clear();
        inSpecialArea = true;
        inBattle = false;
        AreaMaxLevel = 1;
        AreaMinLevel = 1;

        options.add("Heal");
        options.add("Check MonsterPocket stats");

        JOptionPane.showMessageDialog(null, "You go to the MonsterPocket Center");
        JOptionPane.showMessageDialog(null, "Hello, how may I help you");

        if (!Player.HasMP) {
            Menu.selectMP();
        } else {
            while (inSpecialArea) {
                switch (Menu.optionMenu(options)) {
                    case 1:
                        Player.playerMP.hp = Player.playerMP.baseStats[0];
                        JOptionPane.showMessageDialog(null, "Your pokemon has been healed");
                        break;
                    case 2:
                        Data.printData(Player.playerMP);
                        break;
                }

                options.removeLast();
                options.removeLast();

            }
        }
    }

    /**
     * Function representing the facility with various dialogues and interactions.
     */
    public static void facility() {
        options.clear();
        inSpecialArea = true;
        inBattle = false;

        options.add("I'm afraid you have the wrong person, *Takes off hat*");
        options.add("So it seems... May I come inside");

        if (Player.HasGoneHome) {

            JOptionPane.showMessageDialog(null, "Welcome back sir");

            switch (Menu.optionMenu(options)) {
                case 1:

                    JOptionPane.showMessageDialog(null, "Oh my mistake I thought you were someone else but...");
                    JOptionPane.showMessageDialog(null, "Sorry you're not allowed to go inside");

                    leave();

                    break;
                case 2:

                    JOptionPane.showMessageDialog(null, "Yes of coarse sir right this way, *Opens gate*");
                    options.clear();

                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry you're not allowed to go inside");
            leave();
        }

        options.add("I'm not the doctor, *Takes of hat*");
        options.add("Just visiting, how is IT doing?");
        options.add("addd adadfd fdofijdalk zoijf jfasd");

        JOptionPane.showMessageDialog(null, "While inside of the building you hear...");

        JOptionPane.showMessageDialog(null, "Wanna grab some coffee");
        JOptionPane.showMessageDialog(null, "Machines broken");
        JOptionPane.showMessageDialog(null, "Whaa!? Nooo! I have fifty papers to verify");
        JOptionPane.showMessageDialog(null, "Should've done it sooner");
        JOptionPane.showMessageDialog(null, "Can't you be more empathetic Sam");
        JOptionPane.showMessageDialog(null, "Nope\n End of Conversation");

        JOptionPane.showMessageDialog(null, "No no no no!");
        JOptionPane.showMessageDialog(null, "IT got out");
        JOptionPane.showMessageDialog(null, "Should I alert the higherups");
        JOptionPane.showMessageDialog(null, "No! They'll fire me... But if it gets out that I let IT escape");
        JOptionPane.showMessageDialog(null, "I'm dead\n End of conversation");

        JOptionPane.showMessageDialog(null, "Ah Doctor what brings you here today?");

        switch (Menu.optionMenu(options)) {
            case 1:
                JOptionPane.showMessageDialog(null, "That shouldn't have happened, I appologise but you must leave");
                leave();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "IT is doing fine doctor its currently being held in a cave in Satoru Village. But you should know this Doctor?");
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Is it that serious Doctor quickly we need to get to the bunker immediatly");
                JOptionPane.showMessageDialog(null, "Alarms go off in the background as you hurriedly evacuate to the bunkers underneath the facility");
                JOptionPane.showMessageDialog(null, "It's quiet");
                JOptionPane.showMessageDialog(null, "You don't sleep well the passing days");
                JOptionPane.showMessageDialog(null, "You wake up in the middle of the night, the harsh ringing of alarms returns to your hears");
                JOptionPane.showMessageDialog(null, "You hear people yelling, \"It's happening!\" ");
                JOptionPane.showMessageDialog(null, "The bunker shakes violently, you are thrown to the floor, you huddle into a small ball");
                JOptionPane.showMessageDialog(null, "It finally ends");
                JOptionPane.showMessageDialog(null, "A few days later an expidetion is sent to the surface");
                JOptionPane.showMessageDialog(null, "The comming weeks people slowly evacuate the bunkers");
                JOptionPane.showMessageDialog(null, "It's your turn this time");
                JOptionPane.showMessageDialog(null, "As your eyes adjust, the new world slowly comes into view");
                JOptionPane.showMessageDialog(null, "There's nothing left, everythings gone...");
                JOptionPane.showMessageDialog(null, "Should you have stayed home?");
                JOptionPane.showMessageDialog(null, "What was your dream again?");

                Player.ending = "Welcome to oblivion";

                Main.ending();
                break;
        }

        options.clear();

        options.add("Uh... Yes of course! I must be taking my leave now if you'll excuse me");
        options.add("I'm not the Doctor");

        switch (Menu.optionMenu(options)) {
            case 1:
                JOptionPane.showMessageDialog(null, "Oh... Good seeing you again Doctor");
                Player.DiscoveredSecret = true;
                leave();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "I'm sorry.... I'm so so sorry, but we can't have others know of this");
                Main.gameOver();
                break;
        }

    }

    /**
     * Represents the village area in the game. It initializes options, sets inSpecialArea to false,
     * displays a message indicating the player is in Satoru Village, and provides options based on player status.
     * It then prompts the user with a menu, processes the user's choice, and executes corresponding actions.
     */
    public static void village() {

        options.clear();
        inSpecialArea = false;

        JOptionPane.showMessageDialog(null, "You are in Satoru Village");

        if (!Player.HasGoneHome) {
            options.add("Go to Monsterpocket Center (progress)");
            options.add("Go to wilderness (progress)");
            options.add("Go home");
        } else if (Player.DiscoveredSecret) {
            options.add("Go to MP Center");
            options.add("Go to wilderness");
            options.add("Go to cave");
        } else {
            options.add("Go to MP Center");
            options.add("Go to wilderness");
        }

        switch (Menu.optionMenu(options)) {
            case 1:
                Player.HasGoneHome = true;
                mpCenter();

                break;

            case 2:

                if (!Player.HasMP) {

                    JOptionPane.showMessageDialog(null, "You walk into the wilderness");
                    JOptionPane.showMessageDialog(null, "It's dark, you are afraid");
                    JOptionPane.showMessageDialog(null, "Suddenly a [REDACTED] jumps out from the surrounding shrubery");
                    JOptionPane.showMessageDialog(null, "Consider bringing a Monster pocket before going outside the village");
                    JOptionPane.showMessageDialog(null, "You can get a MonsterPocket at the MP Center");
                    Main.gameOver();

                } else {
                    Player.HasGoneHome = true;
                    nextArea();

                }
                break;

            case 3:
                if (!Player.HasGoneHome) {
                    home();
                } else if (Player.DiscoveredSecret) {
                    cave();
                }

                break;
        }

    }

    /**
     * Displays a series of messages indicating that the player is going back home.
     * Prompts the player to stay or leave using a yes/no menu.
     * If the player chooses to stay, displays a message indicating that they fall into comfort and ends the game.
     * If the player chooses to leave, displays a message indicating that they pick up a hat, sets the Player's HasGoneHome flag to true, and calls the leave() method.
     */
    public static void home() {
        JOptionPane.showMessageDialog(null, "You go back home");
        JOptionPane.showMessageDialog(null, "You feel you could stay here forever");
        JOptionPane.showMessageDialog(null, "Maybe you will");

        if (Menu.yesNoMenu("Stay?", "Will you stay?") == 0) {
            JOptionPane.showMessageDialog(null, "You fall into comfort");

            Player.ending = "Peacefull Days";
            Main.gameOver();
        } else {
            JOptionPane.showMessageDialog(null, "You leave, but pick up a hat");
            Player.HasGoneHome = true;
            leave();
        }

    }

    /**
     * Increments the AreaNum and calls the corresponding method based on the new value of AreaNum.
     * If AreaNum is 0, calls the village() method.
     * If AreaNum is 1, sets AreaMaxLevel to 5 and AreaMinLevel to 2, and calls the wilderness() method.
     * If AreaNum is 2, sets AreaMaxLevel to 10 and AreaMinLevel to 4, and calls the wilderness() method.
     * If AreaNum is 3, calls the village2() method.
     * If AreaNum is 4, sets AreaMaxLevel to 14 and AreaMinLevel to 9, and calls the wilderness() method.
     * If AreaNum is 5, calls the gym() method.
     * If AreaNum is greater than 5, decrements AreaNum by 2 and recursively calls the nextArea() method.
     */
    public static void nextArea() {

        AreaNum++;

        switch (AreaNum) {
            case 0:
                village();
                break;
            case 1:
                AreaMaxLevel = 5;
                AreaMinLevel = 2;
                wilderness();
                break;
            case 2:
                AreaMaxLevel = 10;
                AreaMinLevel = 4;
                wilderness();
                break;
            case 3:
                village2();
                break;
            case 4:
                AreaMaxLevel = 14;
                AreaMinLevel = 9;
                wilderness();
                break;
            case 5:
                gym();
                break;
            default:
                AreaNum -= 2;
                nextArea();
        }

    }

    /**
     * Decrements the AreaNum and calls the corresponding method based on the new value of AreaNum.
     * If AreaNum is 0, calls the village() method.
     * If AreaNum is 1, sets AreaMaxLevel to 3 and AreaMinLevel to 2, and calls the wilderness() method.
     * If AreaNum is 2, sets AreaMaxLevel to 7 and AreaMinLevel to 4, and calls the wilderness() method.
     * If AreaNum is 3, calls the village2() method.
     * If AreaNum is 4, sets AreaMaxLevel to 13 and AreaMinLevel to 9, and calls the wilderness() method.
     * If AreaNum is 5, calls the gym() method.
     * If AreaNum is less than 0, increments AreaNum by 2 and recursively calls the prevArea() method.
     */
    public static void prevArea() {

        AreaNum--;

        switch (AreaNum) {
            case 0:
                village();
                break;
            case 1:
                AreaMaxLevel = 3;
                AreaMinLevel = 2;
                wilderness();
                break;
            case 2:
                AreaMaxLevel = 7;
                AreaMinLevel = 4;
                wilderness();
                break;
            case 3:
                village2();
                break;
            case 4:
                AreaMaxLevel = 13;
                AreaMinLevel = 9;
                wilderness();
                break;
            case 5:
                gym();
                break;
            default:
                AreaNum += 2;
                prevArea();
        }

    }

    /**
     * This method is responsible for determining which area to navigate to after leaving the current area.
     * If the current area number is less than 3, it calls the village method. Otherwise, it calls the village2 method.
     */
    public static void leave() {

        if (AreaNum < 3) {
            village();
        } else {
            village2();
        }

    }

}
