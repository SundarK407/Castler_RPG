import java.util.Random;
import java.util.Scanner;

public class kingdom{
    int x;
    int y;
    int type;
    boolean clear;

    public String[] entity={"Base","Enraged","Magical","Ordained"};
    Scanner console = new Scanner(System.in);
    Random random = new Random();
    public kingdom(int hp, int dp, int armor, String name) {
        super();
    }
    public kingdom() {

    }



    public void Dungeon(kingdom[][] darkness, character player, int qx, int qy,
                        character dragon, character griffin, character foul){
        if(darkness[qx][qy].type == 100){
            System.out.println("You made it to the end!");
            boolean val =true;
            while(val){
                val = finalDung(player);
                if(val){
                    System.out.println("Would you like your stats to increase? (y)");
                    String upgrade = console.next();
                    if(upgrade.equals("y")){
                        player.hp+=50;
                        player.dp+=25;
                        player.armor+=10;
                    }



                }



            }


        }
        if(darkness[qx][qy].type<0){
            System.out.println("You beat this room!");

        }
        if(darkness[qx][qy].type < 25 && darkness[qx][qy].type >=0){ //25%
            int val = dragonFight(player, dragon);
            if( val>0){
                darkness[qx][qy].type=-1;
                System.out.println("You beat this room!");

            }else{
                System.out.println("You couldn't kill it!");
                player.hp+=40;
                player.dp+=25;
            }
        }
        if(darkness[qx][qy].type >=25 && darkness[qx][qy].type <45){ //20% now 45%
            int val = griffinFight(player, griffin);
            if( val>0){
                darkness[qx][qy].type=-1;
                System.out.println("You beat this room!");

            }else{
                System.out.println("You couldn't kill it!");
                player.hp+=60;
                player.dp+=35;
            }
        }if(darkness[qx][qy].type >=45 && darkness[qx][qy].type <55){
            System.out.println("Welcome to the map room! This is a place where you can" +
                    "all the rooms in this dungeon!");
            System.out.println("Would you like to look at the map? (y)");
            String choice = console.next();
            if(choice.equals("y")){
                for(int x=0; x<7;x++){
                    System.out.println();
                    for(int y=0; y<7; y++)   {
                        char roomType = 'E';
                        if(darkness[x][y].type == -1){
                            roomType = 'E';
                        }
                        if(darkness[x][y].type == 100){
                            roomType = 'X';
                        }

                        if(darkness[x][y].type < 25){
                            roomType = 'D';
                        }if(darkness[x][y].type >= 25 && darkness[x][y].type < 45){
                            roomType = 'G';
                        }if(darkness[x][y].type >= 45 && darkness[x][y].type < 55){
                            roomType = 'M';
                        }if(darkness[x][y].type >= 55 && darkness[x][y].type < 75){
                            roomType = 'P';
                        }if(darkness[x][y].type >= 75 && darkness[x][y].type < 85){
                            roomType = 'T';
                        }if(darkness[x][y].type >= 85 && darkness[x][y].type < 100){
                            roomType='H';
                        }if(darkness[x][y].type == darkness[qx][qy].type){
                            roomType='*';
                        }
                        System.out.print(" ["+roomType+"] ");

                    }


                }
                System.out.println();
            }

        }if(darkness[qx][qy].type >=55 && darkness[qx][qy].type <75){
            int val = trolls(player, foul);
            if( val>0){
                darkness[qx][qy].type=-1;
                System.out.println("You beat this room!");

            }else{
                System.out.println("You couldn't kill it!");
                player.hp+=35;
                player.dp+=20;
            }
        }if(darkness[qx][qy].type >=75 && darkness[qx][qy].type <85){
            int val = treasureRoom();
            if (val>0){
                darkness[qx][qy].type=-1;
                System.out.println("You chose correctly and the treasure has magical powers!");
                player.hp+=50;
                player.dp+=30;
            }
            else{
                System.out.println("The room collapsed and hurt you!");
                darkness[qx][qy].type=-1;
                player.hp-=30;
                player.dp-=20;
            }

        }if(darkness[qx][qy].type >=85 && darkness[qx][qy].type <100){
            int val = hydra(player, griffin);
            if( val>0){
                darkness[qx][qy].type=-1;
                System.out.println("You beat this room!");

            }else{
                System.out.println("You couldn't kill it!");
                player.hp+=50;
                player.dp+=30;
            }

        }
    }

    public boolean finalDung(character player){
        boolean returner=true;
        int finalHP =1000, finalDP=100;
        int voidhp=player.hp;
        Scanner console = new Scanner(System.in);
        int round = 0;
        int win=0;
        while(finalHP>0 && voidhp>0){
            finalDragon(voidhp,finalHP);
            //Scanner console = new Scanner(System.in);
            System.out.println("Do you want to block (0 or any), attack (1), or dodge (2)?");
            int move = Integer.parseInt(console.next());
            int choice = (int) (Math.random() * 3);

            //0 is block 1 is attack 2 is parry
            //0 wing pushback 1 is fire 2 is bite
            //p:c  (w):  0>2    2>1 1>0
            if (move == 1) {
                if (choice == 0) {
                    System.out.println(player.name + "'s attack was slightly blocked by the Dragon's wings!");
                    finalHP -= player.dp;
                }
                if (choice == 1) {
                    System.out.println("You hit the Dragon, but it slashed you!");
                    finalHP -= player.dp;
                    voidhp -= (finalDP - player.armor);
                } if(choice==2) {
                    System.out.println(player.name + "'s sword was caught in the Dragon's mouth");
                    voidhp -= finalDP;
                }

            }
            if (move == 2) {
                if (choice == 1) {
                    System.out.println(player.name + " perfectly dodge the Dragon's mouths, and were able to hit him!");
                    finalHP -= player.dp;


                }
                if (choice == 2) {
                    System.out.println("You got bitten, however your sword caught the Dragon's wing!");
                    finalHP -= player.dp;
                    voidhp -= (finalDP - player.armor);


                } if(choice==0) {
                    System.out.println(player.name + " got pushed back by a gust of wind made by the Dragon's fire");
                    voidhp -= finalDP;

                }

            } if(move==0) {
                if (choice == 1) {
                    System.out.println(player.name + " slightly blocked the tail!");
                    voidhp -= (finalDP - player.armor);

                }
                if (choice == 2) {
                    System.out.println(player.name + " you stopped the Drago's bite and were able to get a hit on it!");
                    finalHP -= player.dp;

                } if(choice==0) {
                    System.out.println("You blocked the wind gust!");

                }

            }

            System.out.println("That was the end of round " + round);
            System.out.println("You have " + voidhp + " HEALTH and your opponent has " + finalHP + " HEALTH");
            round++;
            if(finalHP<=0){
                break;
            }



        }
        if(voidhp>finalHP){
            returner= false;
        }




        return returner;
    }

    private void finalDragon(int voidhp, int finalHP) {
        System.out.println("            \t\t\t\t\t\t"+"                                             __----~~~~~~~~~~~------___");
        System.out.println("            \t\t\t\t\t\t"+"                                  .  .   ~~//====......          __--~ ~~");
        System.out.println("            \t\t\t\t\t\t"+"                  -.            \\_|//     |||\\\\  ~~~~~~::::... /~");
        System.out.println("            \t\t\t\t\t\t"+"               ___-==_       _-~o~  \\/    |||  \\\\            _/~~-");
        System.out.println("            \t\t\t\t\t\t"+"       __---~~~.==~||\\=_    -_--~/_-~|-   |\\\\   \\\\        _/~");
        System.out.println("            \t\t\t\t\t\t"+"   _-~~     .=~    |  \\\\-_    '-~7  /-   /  ||    \\      /");
        System.out.println("            \t\t\t\t\t\t"+" .~       .~       |   \\\\ -_    /  /-   /   ||      \\   /");
        System.out.println("            \t\t\t\t\t\t"+"/  ____  /         |     \\\\ ~-_/  /|- _/   .||       \\ /");
        System.out.println("  |         \t\t\t\t\t\t"+"|~~    ~~|--~~~~--_ \\     ~==-/   | \\~--===~~        .\\");
        System.out.println("  |         \t\t\t\t\t\t"+"         '         ~-|      /|    |-~\\~~       __--~~");
        System.out.println("  + \\        \t\t\t\t\t\t"+"                     |-~~-_/ |    |   ~\\_   _-~            /\\");
        System.out.println("  \\\\.G_.*=.  \t\t\t\t\t\t"+"                          /  \\     \\__   \\/~                \\__");
        System.out.println("   `(#'/.\\|  \t\t\t\t\t\t"+"                      _--~ _/ | .-~~____--~-/                  ~~==.");
        System.out.println("    .>' (_--.\t\t\t\t\t\t"+"                     ((->/~   '.|||' -_|    ~~-/ ,              . _||");
        System.out.println(" _=/d   ,^\\  \t\t\t\t\t\t"+"                                -_     ~\\      ~~---l__i__i__i--~~_/");
        System.out.println("~~ \\)-'   '  \t\t\t\t\t\t"+"                                _-~-__   ~)  \\--______________--~~");
        System.out.println("   / |       \t\t\t\t\t\t"+"                              //.-~~~-~_--~- |-------~~~~~~~~");
        System.out.println("  '  '       \t\t\t\t\t\t"+"                                     //.-~~~--\\");
        System.out.println("      "+ voidhp +"  \t\t\t\t\t\t"+"         "+finalHP+"   ");


    }

    private int treasureRoom() {
        System.out.println("You found a treasure room! Pick the right object to pass!");
        int randomObject =random.nextInt(3)+1;
        System.out.println("Which do you want to choose:" +
                "\n Golden Cup (1), Emerald Locket (2), or Diamond Crown (3)");
        int checker= console.nextInt();
        if(checker == randomObject){
            System.out.println("You were right!");
            return 1;
        }else{
            System.out.println("That was the wrong choice!");
            return -1;
        }

    }

    public int hydra (character player, character griffin){
        int voidhp=player.hp;
        int randEnt=random.nextInt(10);//9
        int e =0;
        if(randEnt %2 ==1){
            // odd is base
            griffin.hp = 400;
            griffin.dp = 94;
        }if(randEnt==2||randEnt==4){
            griffin.hp = 420;
            griffin.dp = 100;
            e =1;
            //enraged
        }        if(randEnt==6||randEnt==8){
            griffin.hp = 320;
            griffin.dp = 135;
            e =2;
            //mag
        }if(randEnt==0){
            griffin.hp = 500;
            griffin.dp = 150;
            e =1;
            //ord
        }System.out.println("It is a "+entity[e]+ " Hydra!");






        //System.out.println("nun");

        Scanner console = new Scanner(System.in);

        int blockeddp= player.dp /2;
        int round = 0;
        int win=0;

        while(griffin.hp>0 && voidhp>0) {
            hydraPrinter(griffin, voidhp);
            //Scanner console = new Scanner(System.in);
            System.out.println("Do you want to block (0 or any), attack (1), or dodge (2)?");
            int move = Integer.parseInt(console.next());
            int choice = (int) (Math.random() * 3);

            //0 is block 1 is attack 2 is parry
            //0 wing pushback 1 is fire 2 is bite
            //p:c  (w):  0>2    2>1 1>0
            if (move == 1) {
                if (choice == 0) {
                    System.out.println(player.name + "'s attack was slightly blocked by the Hydra's neck!");
                    griffin.hp -= blockeddp;
                }
                if (choice == 1) {
                    System.out.println("You hit the hydra, but it slashed you!");
                    griffin.hp -= player.dp;
                    voidhp -= (griffin.dp - player.armor);
                } if(choice==2) {
                    System.out.println(player.name + "'s sword was caught in the Hydra's mouth");
                    voidhp -= griffin.dp;
                }

            }
            if (move == 2) {
                if (choice == 1) {
                    System.out.println(player.name + " perfectly dodge the Hydra's mouths, and were able to hit him!");
                    griffin.hp -= player.dp;


                }
                if (choice == 2) {
                    System.out.println("You got bitten, however your sword caught the Hydra's wing!");
                    griffin.hp -= blockeddp;
                    voidhp -= (griffin.dp - player.armor);


                } if(choice==0) {
                    System.out.println(player.name + " got pushed back by a gust of wind made by the hydra's wings");
                    voidhp -= griffin.dp;

                }

            } if(move==0) {
                if (choice == 1) {
                    System.out.println(player.name + " slightly blocked the neck!");
                    voidhp -= (griffin.dp - player.armor);

                }
                if (choice == 2) {
                    System.out.println(player.name + " you stopped the hydra's bite and were able to get a hit on it!");
                    griffin.hp -= player.dp;

                } if(choice==0) {
                    System.out.println("You blocked the wind gust!");

                }

            }

            System.out.println("That was the end of round " + round);
            System.out.println("You have " + voidhp + " HEALTH and your opponent has " + griffin.hp + " HEALTH");
            round++;


        }

        if(voidhp<griffin.hp){
            System.out.println(player.name+" you lost!");
        }if(griffin.hp<voidhp){
            System.out.println(player.name+" you won!");
            win+=1;
        }
        if(griffin.hp==voidhp){
            System.out.println("You just killed it!");
            win+=1;
        }
        return win;
    }

    private void hydraPrinter(character griffin, int voidhp) {
        System.out.println("            \t\t\t\t\t\t"+"⠄⠄⣴⣶⣤⡤⠦⣤⣀⣤⠆⠄⠄⠄⠄⠄⣈⣭⣭⣿⣶⣿⣦⣼⣆⠄⠄⠄⠄⠄⠄⠄⠄");
        System.out.println("            \t\t\t\t\t\t"+"⠄⠄⠄⠉⠻⢿⣿⠿⣿⣿⣶⣦⠤⠄⡠⢾⣿⣿⡿⠋⠉⠉⠻⣿⣿⡛⣦⠄⠄⠄⠄⠄⠄");
        System.out.println("            \t\t\t\t\t\t"+"⠄⠄⠄⠄⠄⠈⠄⠄⠄⠈⢿⣿⣟⠦⠄⣾⣿⣿⣷⠄⠄⠄⠄⠻⠿⢿⣿⣧⣄⠄⠄⠄⠄");
        System.out.println("            \t\t\t\t\t\t"+"⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣸⣿⣿⢧⠄⢻⠻⣿⣿⣷⣄⣀⠄⠢⣀⡀⠈⠙⠿⠄⠄⠄⠄");
        System.out.println("  |         \t\t\t\t\t\t"+"⠄⠄⢀⠄⠄⠄⠄⠄⠄⢠⣿⣿⣿⠈⠄⠄⠡⠌⣻⣿⣿⣿⣿⣿⣿⣿⣛⣳⣤⣀⣀⠄⠄");
        System.out.println("  |         \t\t\t\t\t\t"+"⠄⠄⢠⣧⣶⣥⡤⢄⠄⣸⣿⣿⠘⠄⠄⢀⣴⣿⣿⡿⠛⣿⣿⣧⠈⢿⠿⠟⠛⠻⠿⠄⠄");
        System.out.println("  + \\        \t\t\t\t\t\t"+"⠄⣰⣿⣿⠛⠻⣿⣿⡦⢹⣿⣷⠄⠄⠄⢊⣿⣿⡏⠄⠄⢸⣿⣿⡇⠄⢀⣠⣄⣾⠄⠄⠄");
        System.out.println("  \\\\.G_.*=.  \t\t\t\t\t\t"+"⣠⣿⠿⠛⠄⢀⣿⣿⣷⠘⢿⣿⣦⡀⠄⢸⢿⣿⣿⣄⠄⣸⣿⣿⡇⣪⣿⡿⠿⣿⣷⡄⠄");
        System.out.println("   `(#'/.\\|  \t\t\t\t\t\t"+"⠙⠃⠄⠄⠄⣼⣿⡟⠌⠄⠈⠻⣿⣿⣦⣌⡇⠻⣿⣿⣷⣿⣿⣿⠐⣿⣿⡇⠄⠛⠻⢷⣄");
        System.out.println("    .>' (_--.\t\t\t\t\t\t"+"⠄⠄⠄⠄⠄⢻⣿⣿⣄⠄⠄⠄⠈⠻⣿⣿⣿⣷⣿⣿⣿⣿⣿⡟⠄⠫⢿⣿⡆⠄⠄⠄⠁");
        System.out.println(" _=/d   ,^\\  \t\t\t\t\t\t"+"⠄⠄⠄⠄⠄⠄⠻⣿⣿⣿⣿⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣀⣤⣾⡿⠃⠄⠄⠄⠄");
        System.out.println("~~ \\)-'   '  \t\t\t\t\t\t"+"⠄⠄⠄⠄⠄⠄⠻⣿⣿⣿⣿⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣀⣤⣾⡿⠃⠄⠄⠄⠄");
        System.out.println("   / |       \t\t\t\t\t\t"+"⠄⠄⠄⠄⠄⠄⠻⣿⣿⣿⣿⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣀⣤⣾⡿⠃⠄⠄⠄⠄");
        System.out.println("  '  '       \t\t\t\t\t\t"+"`⠄⠄⠄⠄⠄⠄⠻⣿⣿⣿⣿⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣀⣤⣾⡿⠃⠄⠄⠄⠄");

        System.out.println("      "+ voidhp +"  \t\t\t\t\t\t"+"         "+griffin.hp+"   ");
    }



    public void Land(kingdom[][] king, int px, int py, Items knight, character player,
                     character griffin, character dragon, character foul){
        //System.out.println(player.hp + " "+player.dp+ " "+player.armor);
        if (king[px][py].type <= 40){
            System.out.println("These villages, hills, and rivers are clear of danger thou knight");
            if(king[px][py].type!=-1){
                System.out.println("Would you like to upgrade hp(1),dp(2), or armor(0) ");
                int t = console.nextInt();
                if (t == 1) {
                    player.hp += 65;
                    king[px][py].type=-1;
                }
                if (t == 2) {
                    player.dp += 45;
                    king[px][py].type=-1;
                }
                if (t == 0) {
                    player.armor += 25;
                    king[px][py].type=-1;
                }

            }

        }
        if(king[px][py].type == 100){
            System.out.println("You have returned back to the castle");
            castlePrinter();
            //Put a castle method here to play
            if(knight.kp>=15){
                System.out.println("There is a terrible curse in the dungeon. Navigate and beat it!");
                //finalDungeon[][] dungeon = new finalDungeon[3][3];
                clear= true;

                while(clear){
                    clear = start();

                }
                System.out.println("You have now entered the dungeon!");







            }



        }if(king[px][py].type >40 && king[px][py].type<=65){
            //25% chance
            int x=0;
            System.out.println("You ran into a group of rogue knights who have taken over a village!");
            x+=knightFight(player, foul);
            if(x>0){
                knight.kp+=2;
                king[px][py].type=-1;
                System.out.println("You have saved this village!");
            }if(x==0){
                System.out.println("They beat you for now but you can always come back!");
            }

        }if(king[px][py].type >65 && king[px][py].type<=85){
            System.out.println("A dragon has taken over this mountain!");
            x=0;
            x+=dragonFight(player, dragon);
            if(x>0){
                knight.kp+=2;
                king[px][py].type=-1;
                System.out.println("You have cleared this mountain!");
            }if(x==0){
                System.out.println("They beat you for now but you can always come back!");
            }

        }if(king[px][py].type >85 && king[px][py].type<=98){
            //14%
            System.out.println("This is a fight with griffin, watch out they are very strong" +
                    " but will give you lots of knight points");
            x=0;
            x+=griffinFight(player, griffin);
            if(x>0){
                knight.kp+=3;
                king[px][py].type=-1;
                System.out.println("You beat one of the strongest beasts!");
            }if(x==0){
                System.out.println("You can come back and kill it!");
            }


        }
        if(king[px][py].type == 99){
            System.out.println("You have met one a lucky dragon!\n He offers you plus 250 in any stat" +
                    "(0 for attack, 1 for hp, 2 for all) any for even distribution");
            int t = console.nextInt();
            luckyDragon();
            if (t == 1) {
                player.hp += 250;
                king[px][py].type=0;
            }
            if (t == 0) {
                player.dp += 250;
                king[px][py].type=0;
            }
            else{
                player.dp+=100;
                player.armor+=50;
                player.hp+=100;
                king[px][py].type=0;
            }

            

        }
        if(knight.kp<15){
            System.out.println("You have "+knight.kp+ " knight points!");
        }
        if(knight.kp>=15){
            System.out.println("Time to return to the castle to complete final dungeon! " +
                    "You have "+knight.kp+ " knight points!");
        }




    }

    public boolean start() {
        boolean returner= false;
        System.out.println("You will need to unlock the following lock by entering a random" +
                " 3 number sequence");

        boolean[] check =new boolean[3];

        int[] randValues= new int[3];
        for(int i=0;i<3;i++){
            Random r = new Random();

            randValues[i] = r.nextInt(9)+1;
            //System.out.println(randValues[i]);
            check[i] = false;
        }
        int count=0;
        int turn =0;

        System.out.println();
        while (count<3){
            if(turn ==5){
                System.out.println("\nYou used all your guess!");
                returner=true;
                break;
            }
            System.out.println("Enter a number: ");
            //Scanner console = new Scanner(System.in);
            int move = console.nextInt();
            System.out.println();
            for (int i=0; i<3;i++){
                if( randValues[i] == move){
                    check[i]=true;
                    count+=1;
                    turn-=1;
                }


            }
            for(int l=0; l<3; l++){
                if(check[l]){
                    System.out.print(randValues[l]+ " ");
                }else{
                    System.out.print("_ ");
                }

            }
            turn+=1;
        }
        return returner;
    }



    private void luckyDragon() {
        System.out.println("                     _ _");
        System.out.println("              _     //` `\\");
        System.out.println("          _,-\"\\%   // /``\\`\\");
        System.out.println("     ~^~ >__^  |% // /  } `\\`\\");
        System.out.println("            )  )%// / }  } }`\\`\\");
        System.out.println("           /  (%/'/.\\_/\\_/\\_/\\`/");
        System.out.println("         (    '         `-._`");
        System.out.println("          \\   ,     (  \\   _`-.__.-;%>");
        System.out.println("         /_`\\ \\      `\\ \\.\" `-..-'`");
        System.out.println("        ``` /_/`\"-=-'`/_/");
        System.out.println("          ```       ```");
    }

    private int griffinFight(character player, character griffin) {

        int voidhp=player.hp;
        int randEnt=random.nextInt(10);//9
        int e =0;
        if(randEnt %2 ==1){
            // odd is base
            griffin.hp = 500;
            griffin.dp = 94;
        }if(randEnt==2||randEnt==4){
            griffin.hp = 520;
            griffin.dp = 100;
            e =1;
            //enraged
        }        if(randEnt==6||randEnt==8){
            griffin.hp = 420;
            griffin.dp = 135;
            e =2;
            //mag
        }if(randEnt==0){
            griffin.hp = 600;
            griffin.dp = 150;
            e =1;
            //ord
        }System.out.println("It is a "+entity[e]+ " Griffin!");






        //System.out.println("nun");

        Scanner console = new Scanner(System.in);

        int blockeddp= player.dp /2;
        int round = 0;
        int win=0;

        while(griffin.hp>0 && voidhp>0) {
            griffinPrinter(griffin, voidhp);
            //Scanner console = new Scanner(System.in);
            System.out.println("Do you want to block (0 or any), attack (1), or dodge (2)?");
            int move = Integer.parseInt(console.next());
            int choice = (int) (Math.random() * 3);

            //0 is block 1 is attack 2 is parry
            //0 wing pushback 1 is fire 2 is bite
            //p:c  (w):  0>2    2>1 1>0
            if (move == 1) {
                if (choice == 0) {
                    System.out.println(player.name + "'s attack was slightly blocked by the griffin's paw!");
                    griffin.hp -= blockeddp;
                }
                if (choice == 1) {
                    System.out.println("You hit the griffin, but it slashed you!");
                    griffin.hp -= player.dp;
                    voidhp -= (griffin.dp - player.armor);
                } if(choice==2) {
                    System.out.println(player.name + "'s sword was caught in the griffin's beak");
                    voidhp -= griffin.dp;
                }

            }
            if (move == 2) {
                if (choice == 1) {
                    System.out.println(player.name + " perfectly dodge the griffin's paw, and were able to hit him!");
                    griffin.hp -= player.dp;


                }
                if (choice == 2) {
                    System.out.println("You got bitten, however your sword caught the griffin's beak!");
                    griffin.hp -= blockeddp;
                    voidhp -= (griffin.dp - player.armor);


                } if(choice==0) {
                    System.out.println(player.name + " got pushed back by a gust of wind made by the griffin's wings");
                    voidhp -= griffin.dp;

                }

            } if(move==0) {
                if (choice == 1) {
                    System.out.println(player.name + " slightly blocked the claws!");
                    voidhp -= (griffin.dp - player.armor);

                }
                if (choice == 2) {
                    System.out.println(player.name + " you stopped the griffin's bite and were able to get a hit on it!");
                    griffin.hp -= player.dp;

                } if(choice==0) {
                    System.out.println("You blocked the wind gust!");

                }

            }

            System.out.println("That was the end of round " + round);
            System.out.println("You have " + voidhp + " HEALTH and your opponent has " + griffin.hp + " HEALTH");
            round++;


        }

        if(voidhp<griffin.hp){
            System.out.println(player.name+" you lost!");
        }if(griffin.hp<voidhp){
            System.out.println(player.name+" you won!");
            win+=1;
        }
        if(griffin.hp==voidhp){
            System.out.println("You just killed it!");
            win+=1;
        }
        return win;


    }

    private void griffinPrinter(character griffin, int voidhp) {
        System.out.println("            \t\t\t\t\t\t"+"                        ______");
        System.out.println("            \t\t\t\t\t\t"+"            ______,---'__,---'");
        System.out.println("            \t\t\t\t\t\t"+"        _,-'---_---__,---'");
        System.out.println("            \t\t\t\t\t\t"+" /_    (,  ---____',");
        System.out.println("  |         \t\t\t\t\t\t"+" /  ',,   `, ,-'");
        System.out.println("  |         \t\t\t\t\t\t"+";/)   ,',,_/,'");
        System.out.println("  + \\        \t\t\t\t\t\t"+"| /\\   ,.'//\\");
        System.out.println("  \\\\.G_.*=.  \t\t\t\t\t\t"+"`-` \\ ,,'    `.");
        System.out.println("   `(#'/.\\|  \t\t\t\t\t\t"+"     `',   ,-- `.");
        System.out.println("    .>' (_--.\t\t\t\t\t\t"+"     '/ / |      `,         _");
        System.out.println(" _=/d   ,^\\  \t\t\t\t\t\t"+"     //'',.\\_    .\\\\      ,{==>-");
        System.out.println("~~ \\)-'   '  \t\t\t\t\t\t"+"  __//   __;_`-  \\ `;.__,;'");
        System.out.println("   / |       \t\t\t\t\t\t"+"((,--,) (((,------;  `--' ");
        System.out.println("  '  '       \t\t\t\t\t\t"+"```  '   ```");

        System.out.println("      "+ voidhp +"  \t\t\t\t\t\t"+"         "+griffin.hp+"   ");
    }

    private int dragonFight(character player, character dragon) {
        int voidhp= player.hp;

        int randEnt=random.nextInt(10);//9
        int e =0;
        if(randEnt %2 ==1){
            // odd is base
            dragon.hp = 375;
            dragon.dp = 65;
        }if(randEnt==2||randEnt==4){
            dragon.hp = 400;
            dragon.dp = 65;
            e =1;
            //enraged
        }        if(randEnt==6||randEnt==8){
            dragon.hp = 315;
            dragon.dp = 95;
            e =2;
            //mag
        }if(randEnt==0){
            dragon.hp = 325;
            dragon.dp = 150;
            e =1;
            //ord
        }System.out.println("It is a "+entity[e]+ " Dragon!");

        int blockeddp= player.dp /2;
        int round = 0;
        int win=0;

        while(dragon.hp>0 && voidhp>0) {
            dragonPrinter(dragon, voidhp);
            //Scanner console = new Scanner(System.in);
            System.out.println("Do you want to block (0 or any), attack (1), or dodge (2)?");
            int move = Integer.parseInt(console.next());
            int choice = (int) (Math.random() * 3);

            //0 is block 1 is attack 2 is parry
            //0 wing pushback 1 is fire 2 is bite
            //p:c  (w):  0>2    2>1 1>0
            if (move == 1) {
                if (choice == 0) {
                    System.out.println(player.name + "'s attack was slightly blocked by the dragon's scale!");
                    dragon.hp -= blockeddp;
                }
                if (choice == 1) {
                    System.out.println("You hit the dragon, but it blew fire on you!");
                    dragon.hp -= player.dp;
                    voidhp -= (dragon.dp - player.armor);
                } if(choice==2) {
                    System.out.println(player.name + "'s sword was caught in the dragon's mouth");
                    voidhp -= dragon.dp;
                }

            }
            if (move == 2) {
                if (choice == 1) {
                    System.out.println(player.name + " perfectly dodge the dragon's fire, and were able to hit him!");
                    dragon.hp -= player.dp;


                }
                if (choice == 2) {
                    System.out.println("You got bitten, however your sword caught the dragon's mouth!");
                    dragon.hp -= blockeddp;
                    voidhp -= (dragon.dp - player.armor);


                } if(choice==0) {
                    System.out.println(player.name + " got pushed back by a gust of wind made by the dragon's wings");
                    voidhp -= dragon.dp;

                }

            } if(move==0) {
                if (choice == 1) {
                    System.out.println(player.name + " slightly blocked the fire!");
                    voidhp -= (dragon.dp - player.armor);

                }
                if (choice == 2) {
                    System.out.println(player.name + " you stopped the dragon's bite and were able to get a hit on it!");
                    dragon.hp -= player.dp;

                } if(choice==0) {
                    System.out.println("You blocked the wind gust!");

                }

            }

            System.out.println("That was the end of round " + round);
            System.out.println("You have " + voidhp + " HEALTH and your opponent has " + dragon.hp + " HEALTH");
            round++;


        }

        if(voidhp<dragon.hp){
            System.out.println(player.name+" you lost!");
        }if(dragon.hp<voidhp){
            System.out.println(player.name+" you won!");
            win+=1;
        }
        if(dragon.hp==voidhp){
            System.out.println("You just killed it!");
            win+=1;
        }
        return win;
    }

    private void dragonPrinter(character dragon, int voidhp) {
        System.out.println("            \t\t\t\t\t\t"+"             ______________ ");
        System.out.println("            \t\t\t\t\t\t"+"       ,===:'.,            `-._      ");
        System.out.println("            \t\t\t\t\t\t"+"            `:.`---.__         `-._   ");
        System.out.println("            \t\t\t\t\t\t"+"              `:.     `--.         `.");
        System.out.println("  |         \t\t\t\t\t\t"+"                \\.        `.         `. ");
        System.out.println("  |         \t\t\t\t\t\t"+"        (,,(,    \\.         `.   ____,-`.,");
        System.out.println("  + \\        \t\t\t\t\t\t"+"     (,'     `/   \\.   ,--.___`.'     ");
        System.out.println("  \\\\.G_.*=.  \t\t\t\t\t\t"+"  ,  ,'  ,--.  `,   \\.;'         `");
        System.out.println("   `(#'/.\\|  \t\t\t\t\t\t"+"  `{D, {    \\  :    \\;               ");
        System.out.println("    .>' (_--.\t\t\t\t\t\t"+"    V,,'    /  /    //                ");
        System.out.println(" _=/d   ,^\\  \t\t\t\t\t\t"+"    j;;    /  ,' ,-//.    ,---.      ,         ");
        System.out.println("~~ \\)-'   '  \t\t\t\t\t\t"+"    \\;'   /  ,' /  _  \\  /  _  \\   ,'/        ");
        System.out.println("   / |       \t\t\t\t\t\t"+"          \\   `'  / \\  `'  / \\  `.' /    ");
        System.out.println("  '  '       \t\t\t\t\t\t"+"           `.___,'   `.__,'   `.__,'  ");

        System.out.println("      "+ voidhp +"  \t\t\t\t\t\t"+"         "+dragon.hp+"   ");
    }

    private int knightFight(character player, character foul) {
        int voidhp=player.hp;
        foul.hp = 150;
        foul.dp = 30;
        //System.out.println("nun");

        Scanner console = new Scanner(System.in);

        int blockeddp= player.dp /2;
        int round = 0;
        int win=0;
        while(foul.hp>0 && voidhp>0) {
            foulPrinter(foul, voidhp);
            //Scanner console = new Scanner(System.in);
            System.out.println("Do you want to block (0 or any), attack (1), or parry (2)?");
            int move = Integer.parseInt(console.next());
            int choice = (int) (Math.random() * 3);

            //0 is block 1 is attack 2 is parry
            //p:c  (w):  0>2    2>1 1>0
            if (move == 1) {
                if (choice == 0) {
                    System.out.println(player.name + "'s attack was slightly blocked!");
                    foul.hp -= blockeddp;
                }
                if (choice == 1) {
                    System.out.println("Both jousters attacked!");
                    foul.hp -= player.dp;
                    voidhp -= (foul.dp - player.armor);
                } if(choice==2) {
                    System.out.println(player.name + "'s attacked was countered!");
                    voidhp -= foul.dp;
                }

            }
            if (move == 2) {
                if (choice == 1) {
                    System.out.println(player.name + " perfectly parried!");
                    foul.hp -= player.dp;


                }
                if (choice == 2) {
                    System.out.println("Both opponents slightly hit each other!");
                    foul.hp -= blockeddp;
                    voidhp -= (foul.dp - player.armor);


                } if(choice==0) {
                    System.out.println(player.name + " got knocked off his horse!");
                    voidhp -= foul.dp;

                }

            } if(move==0) {
                if (choice == 1) {
                    System.out.println(player.name + " slightly blocked the attack!");
                    voidhp -= (foul.dp - player.armor);

                }
                if (choice == 2) {
                    System.out.println(player.name + " knocked his opponent off his horse!");
                    foul.hp -= player.dp;

                } if(choice==0) {
                    System.out.println("Both blocked");

                }

            }

            System.out.println("That was the end of round " + round);
            System.out.println("You have " + voidhp + " HEALTH and your opponent has " + foul.hp + " HEALTH");
            round++;


        }

        if(voidhp<foul.hp){
            System.out.println(player.name+" you lost!");
        }if(foul.hp<voidhp){
            System.out.println(player.name+" you won!");
            return 1;
        }
        if(foul.hp==voidhp){
            System.out.println("You drew!");
            return 1;
        }
        return 0;


    }

    private void foulPrinter(character foul,int voidhp) {
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("  + \\        \t\t\t\t\t\t"+"                  _");
        System.out.println("  \\\\.G_.*=.  \t\t\t\t\t\t"+"                 /-|");
        System.out.println("   `(#'/.\\|  \t\t\t\t\t\t"+"               ___  \\");
        System.out.println("    .>' (_--.\t\t\t\t\t\t"+" &___________|  /|--]");
        System.out.println(" _=/d   ,^\\  \t\t\t\t\t\t"+"               | / |__|");
        System.out.println("~~ \\)-'   '  \t\t\t\t\t\t"+"               \\ /.\\ |");
        System.out.println("   / |       \t\t\t\t\t\t"+"                '|| ||");
        System.out.println("  '  '       \t\t\t\t\t\t"+"                <_'<_'");
        System.out.println("     "+voidhp+"  \t\t\t\t\t\t"+"         "+foul.hp+"   ");
    }
    private void trollPrinter(character foul,int voidhp) {
        System.out.println("  |");
        System.out.println("  |");
        System.out.println("  + \\        \t\t\t\t\t\t"+"                  _");
        System.out.println("  \\\\.G_.*=.  \t\t\t\t\t\t"+"                 /-|");
        System.out.println("   `(#'/.\\|  \t\t\t\t\t\t"+"               ___  \\");
        System.out.println("    .>' (_--.\t\t\t\t\t\t"+" &___________|  /|--]");
        System.out.println(" _=/d   ,^\\  \t\t\t\t\t\t"+"               | / |__|");
        System.out.println("~~ \\)-'   '  \t\t\t\t\t\t"+"               \\ /.\\ |");
        System.out.println("   / |       \t\t\t\t\t\t"+"                '|| ||");
        System.out.println("  '  '       \t\t\t\t\t\t"+"                <_'<_'");
        System.out.println("     "+voidhp+"  \t\t\t\t\t\t"+"         "+foul.hp+"   ");
    }

    private void castlePrinter() {
        System.out.println("               T~~");
        System.out.println("               |");
        System.out.println("              /\"\\");
        System.out.println("      T~~     |'| T~~");
        System.out.println("  T~~ |    T~ WWWW|");
        System.out.println("  |  /\"\\   |  |  |/\\T~~");
        System.out.println(" /\"\\ WWW  /\"\\ |' |WW|");
        System.out.println("WWWWW/\\| /   \\|'/\\|/\"\\");
        System.out.println("|   /__\\/]WWW[\\/__\\WWWW");
        System.out.println("|\"  WWWW'|I_I|'WWWW'  |");
        System.out.println("|   |' |/  -  \\|' |'  |");
        System.out.println("|'  |  |LI=H=LI|' |   |");
        System.out.println("|   |' | |[_]| |  |'  |");
        System.out.println("|   |  |_|###|_|  |   |");
        System.out.println("'---'--'-/___\\-'--'---'");

    }
    private int trolls(character player, character foul) {
        int voidhp=player.hp;
        foul.hp = 150;
        foul.dp = 30;
        //System.out.println("nun");

        Scanner console = new Scanner(System.in);

        int blockeddp= player.dp /2;
        int round = 0;
        int win=0;
        while(foul.hp>0 && voidhp>0) {
            trollPrinter(foul, voidhp);
            //Scanner console = new Scanner(System.in);
            System.out.println("Do you want to block (0 or any), attack (1), or parry (2)?");
            int move = Integer.parseInt(console.next());
            int choice = (int) (Math.random() * 3);

            //0 is block 1 is attack 2 is parry
            //p:c  (w):  0>2    2>1 1>0
            if (move == 1) {
                if (choice == 0) {
                    System.out.println(player.name + "'s attack was slightly blocked!");
                    foul.hp -= blockeddp;
                }
                if (choice == 1) {
                    System.out.println("Both jousters attacked!");
                    foul.hp -= player.dp;
                    voidhp -= (foul.dp - player.armor);
                } if(choice==2) {
                    System.out.println(player.name + "'s attacked was countered!");
                    voidhp -= foul.dp;
                }

            }
            if (move == 2) {
                if (choice == 1) {
                    System.out.println(player.name + " perfectly parried!");
                    foul.hp -= player.dp;


                }
                if (choice == 2) {
                    System.out.println("Both opponents slightly hit each other!");
                    foul.hp -= blockeddp;
                    voidhp -= (foul.dp - player.armor);


                } if(choice==0) {
                    System.out.println(player.name + " got knocked off his horse!");
                    voidhp -= foul.dp;

                }

            } if(move==0) {
                if (choice == 1) {
                    System.out.println(player.name + " slightly blocked the attack!");
                    voidhp -= (foul.dp - player.armor);

                }
                if (choice == 2) {
                    System.out.println(player.name + " knocked his opponent off his horse!");
                    foul.hp -= player.dp;

                } if(choice==0) {
                    System.out.println("Both blocked");

                }

            }

            System.out.println("That was the end of round " + round);
            System.out.println("You have " + voidhp + " HEALTH and your opponent has " + foul.hp + " HEALTH");
            round++;


        }

        if(voidhp<foul.hp){
            System.out.println(player.name+" you lost!");
        }if(foul.hp<voidhp){
            System.out.println(player.name+" you won!");
            return 1;
        }
        if(foul.hp==voidhp){
            System.out.println("You drew!");
            return 1;
        }
        return 0;


    }


}
