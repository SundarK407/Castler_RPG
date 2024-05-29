import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        int win=0;
        int gamePhase=0;
        int hp=100, dp=15, armor=0;
        String name = null;
        int upoints=0;
        //0 = intro 1= examination 2=joust 3=cutscene (end or final) 4= dragon 5=end
        character griffin;
        griffin = new character(hp, dp,armor, name);
        character foul;
        foul = new character(hp, dp,armor, name);
        character dragon;
        dragon = new character(hp, dp,armor, name);
        character player = new character (hp, dp, armor, name);


        while(gamePhase==0){
            System.out.println("Enter your name: ");
            name= console.next();
            player.name=name;

            System.out.println("You are a Squire trying to become a Knight! You will got through 3 trials" +
                    " to do this.\n First let us get your gear sorted! Each weapon and armor set has random stats " +
                    "which you can upgrade. Choose wisely!");


            player.getDp();
            player.getHp();
            player.getAp();
            System.out.println("Did you finish the tutorial and became a knight? (y)");
            String checkT=console.next();

            gamePhase=1;
            if(checkT.equals("y")){
                gamePhase=4;
            }
        }

        while(gamePhase==1){
            int correct=0;
            System.out.println("You will take your knight exam. While correct answers don't matter you will get additional hp for each answer you get write!");
            String nun = console.next();
            correct=testScene();
            if(correct==3){
                hp+=25;

            }if(correct==2){
                hp+=20;

            }if(correct==1){
                hp+=15;

            }

            System.out.println("Know that you finished your exam lets go to your trial!");

            gamePhase=2;
        }

        while(gamePhase==2){
            System.out.println("You will Joust know let's begin!");
            win+=joustScene(hp,dp,armor,name);
            if(win>0){
                System.out.println("Well done know lets get ready before your final task!");
                gamePhase+=1;
                upoints=armory(upoints,hp,armor,dp);
                boolean arm=true;
                while(arm) {
                    if (upoints <= 0) {
                        System.out.println("You have no points!");
                        arm=false;
                    }
                    if (upoints > 0) {
                        System.out.println("Would you like to upgrade hp(1),dp(2), or armor(0) ");
                        int t = console.nextInt();
                        if (t == 1) {
                            hp += 15;
                            upoints -= 1;
                        }
                        if (t == 2) {
                            dp += 15;
                            upoints -= 1;
                        }
                        if (t == 0) {
                            armor += 15;
                            upoints -= 1;
                        }
                        System.out.println("Would you like too keep upgrading (no=0)");
                        int ch=console.nextInt();
                        if(ch==0){
                            arm=false;
                        }
                    }
                }
            }
            if(win<=0){
                System.out.println("That didn't go too well do you want to go and gear up(0) or face the " +
                        "joust again? ");
                int cho= Integer.parseInt(console.next());
                if(cho>0){
                    System.out.println("Rematch it is!");
                }
                if(cho<=0){
                    System.out.println("Let us go to the armory!");
                    upoints=armory(upoints,hp,armor,dp);
                    boolean arm=true;
                    while(arm) {
                        if (upoints <= 0) {
                            System.out.println("You have no points!");
                            arm=false;
                        }
                        if (upoints > 0) {
                            System.out.println("Would you like to upgrade hp(1),dp(2), or armor(0) ");
                            int t = console.nextInt();
                            if (t == 1) {
                                hp += 15;
                                upoints -= 1;
                            }
                            if (t == 2) {
                                dp += 15;
                                upoints -= 1;
                            }
                            if (t == 0) {
                                armor += 15;
                                upoints -= 1;
                            }
                            System.out.println("Would you like too keep upgrading (no=0)");
                            int ch=console.nextInt();
                            if(ch==0){
                                arm=false;
                            }
                        }
                    }
                }
            }



        }



        while(gamePhase==3){

            System.out.println("For your final task you face a Dragon!");
            
            win+=dragonScene(hp,dp,armor,name);

            if(win>1){
                System.out.println("Congrats on beating the dragon! Now let's go to your ceremony!");
                String nun= console.next();
                gamePhase=4;
            }if(win==1){
                  System.out.println("That didn't go to well do you want to go and gear up(0) or face the " +
                        "dragon again? ");
                int cho= Integer.parseInt(console.next());
                if(cho>0){
                    System.out.println("Rematch it is!");
                }
                if(cho<=0){
                    System.out.println("Let us go to the armory!");
                    upoints=armory(upoints,hp,armor,dp);
                    boolean arm=true;
                    while(arm) {
                        if (upoints <= 0) {
                            System.out.println("You have no points!");
                            arm=false;
                        }
                        if (upoints > 0) {
                            System.out.println("Would you like to upgrade hp(1),dp(2), or armor(0) ");
                            int t = console.nextInt();
                            if (t == 1) {
                                hp += 15;
                                upoints -= 1;
                            }
                            if (t == 2) {
                                dp += 15;
                                upoints -= 1;
                            }
                            if (t == 0) {
                                armor += 15;
                                upoints -= 1;
                            }
                            System.out.println("Would you like too keep upgrading (no=0)");
                            int ch=console.nextInt();
                            if(ch==0){
                                arm=false;
                            }
                        }
                    }
                }
            }





        }

        while(gamePhase==4){
            knightPrinter();
            System.out.println(name+ " you are know a knight!");


            gamePhase+=1;
        }

        System.out.println("Travel through the kingdom and complete 15 missions before facing your " +
                "task!");
        int kp = 0;
        boolean dungeonKey = false;
        Items knight = new Items(kp, dungeonKey);
        int a = 0;
        kingdom[][] king = new kingdom[10][10];
        kingdom[][] darkness = new kingdom[10][10];
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                Random r = new Random();
                boolean clear = false;
                int type = r.nextInt(100); //0-99
                //System.out.println(type);
                kingdom k = new kingdom(x,y,type,name);
                king[x][y] = k;
                king[x][y].type=type;
            }
        }
        int px = 5; //castle location
        int py = 5;
        king[px][py].type = 100;
        //print out map/kingdom
        while(knight.kp < 15){
            int direction;
            System.out.println("Would you like to go " +
                    "North (0), West (1), South (2), or East(3)?");
            direction=console.nextInt();
            if(direction==0){
                if(px==0){
                    px=9;
                }
                else{
                    px-=1;
                }

            }if(direction==1){
                if(py==0){
                    py=9;
                }
                else{
                    py-=1;
                }
            }if(direction==2){
                if(px==9){
                    px=0;
                }
                else{
                    px+=1;
                }
            }if(direction==3){
                if(py==9){
                    py=0;
                }
                else{
                    py+=1;
                }
            }
            //System.out.println(px+ " " +py);
            king[px][py].Land(king,px,py,knight,player,griffin,dragon,foul);
        }

        System.out.println("This will be your last and hardest challenge. " +
                "Return to the castle and defeat what lays" +
                " in the dungeons and the King will grant you Lordship! Good luck!");
        boolean castleCheck=true;
        while(castleCheck){
            if(px==5 && py==5){
                castleCheck=false;
                break;
            }
            int direction;
            System.out.println("Would you like to go " +
                    "North (0), West (1), South (2), or East(3)?");
            direction=console.nextInt();
            if(direction==0){
                if(px==0){
                    px=9;
                }
                else{
                    px-=1;
                }

            }if(direction==1){
                if(py==0){
                    py=9;
                }
                else{
                    py-=1;
                }
            }if(direction==2){
                if(px==9){
                    px=0;
                }
                else{
                    px+=1;
                }
            }if(direction==3){
                if(py==9){
                    py=0;
                }
                else{
                    py+=1;
                }
            }
            //System.out.println(px+ " " +py);
            king[px][py].Land(king,px,py,knight,player,griffin,dragon,foul);
        }


        for(int LnR = 0; LnR < 7; LnR++){
            for(int UnD = 0; UnD < 7; UnD++){
                Random r = new Random();
                boolean clear = false;
                int type = r.nextInt(100); //0-99
                //System.out.println(type);
                kingdom k = new kingdom(LnR,UnD,type,name);
                darkness[LnR][UnD] = k;
                darkness[LnR][UnD].type=type;
            }
        }
        darkness[6][6].type = 100;
        int qx=0,qy=0;
        boolean dungeonChecker=true;
        while(dungeonChecker){

            Random r = new Random();
            int doors = r.nextInt(4)+1;
            int direction=0;

            if(doors==1){
                System.out.println("The only door open right now is: East ");
                direction=console.nextInt();
                direction =3;
            }
            if(doors==2){
                System.out.println("The only doors open right now is: East(1) and North(0) ");
                direction=console.nextInt();
                direction = direction%2;
                if(direction==1) {
                    direction = 3;
                }
            }
            if(doors==3){
                System.out.println("The only doors open right now is: East(1) South (2) and North(0) ");
                direction=console.nextInt();
                direction = direction%3;
                if(direction==1) {
                    direction = 3;
                }

            }if(doors==4) {

                System.out.println("Would you like to go " +
                        "North (0), West (1), South (2)? ");
                direction=console.nextInt();
            }

            if(direction==0){
                if(qx==0){
                    qx=6;
                }
                else{
                    qx-=1;
                }

            }if(direction==1){
                if(qy==0){
                    qy=6;
                }
                else{
                    qy-=1;
                }
            }if(direction==2){
                if(qx==6){
                    qx=0;
                }
                else{
                    qx+=1;
                }
            }if(direction==3){
                if(qy==6){
                    qy=0;
                }
                else{
                    qy+=1;
                }
            }
            System.out.println(qx+ " " +qy+ " TYPE: "+ darkness[qx][qy].type);
            darkness[qx][qy].Dungeon(darkness,player, qx,qy,dragon,griffin,foul);
            if(darkness[qx][qy].type==100){
                dungeonChecker=false;
            }
        }


        System.out.println("Congrats on finishing! Thank you for playing "+ player.name+ "!");
        System.out.println("Your stats: Health: "+ player.hp + "\n Damage: "+player.dp+ "\n Armor: "
                +player.armor);




    }
    private static void knightPrinter() {
        System.out.println("         *_   _   _   _   _   _ *");
        System.out.println(" ^       | `_' `-' `_' `-' `_' `|       ^");
        System.out.println(" |       | You Are Now a Knight |       |");
        System.out.println(" |  (*)  |_   _   _   _   _   _ |  \\^/  |");
        System.out.println(" | _<\">_ | `_' `-' `_' `-' `_' `| _(#)_ |");
        System.out.println("o+o \\ / \\0                      0/ \\ / (=)");
        System.out.println(" 0'\\ ^ /\\/                      \\/\\ ^ /`0");
        System.out.println("   /_^_\\ |                        | /_^_\\");
        System.out.println("   || || |                        | || ||");
        System.out.println("   d|_|b_T______________________T_d|_|b");
    }

    public static int armory(int upoints, int hp, int dp, int armor) {
        boolean lock=true;

        Scanner console = new Scanner(System.in);
        while(lock){
            System.out.println("Welcome to the armory you have "+upoints+" upgrade points.");
            System.out.println("Would you like to get more points (0) or exit(1): ");
            int cho= Integer.parseInt(console.next());

            if(cho==0){
                System.out.println("You can earn upgrade points base on" +
                        " how much you roll. However, if you hit a 6 then all your upgrade points will be gone!");
                System.out.println("Would you like to roll or exit(0): ");
                int choice= console.nextInt();

                if(choice>0){
                    int roll;
                    roll=(int) (Math.random()*5);
                    if(roll==0){
                        System.out.println("You rolled a 6 and lose all your points!");
                        upoints=0;
                    }if(roll>0){
                        System.out.println("You rolled a "+roll);
                        upoints+=roll;
                    }
                }
            }
            if(cho==1){
                lock =false;
            }



        }
        return upoints;
    }

    private static void dragonPrinter(character dragon){
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

        System.out.println("Dragon DP:"+dragon.dp+"  \t\t\t\t\t\t"+"         "+dragon.hp+"   ");

    }

    public static int dragonScene(int hp, int dp, int armor, String name) {
        character dragon;
        dragon = new character(hp, dp,armor, name);
        dragon.hp = 250;
        dragon.dp = 40;
        //System.out.println("nun");

        Scanner console = new Scanner(System.in);

        int blockeddp= dp /2;
        int round = 0;
        int win=0;

        while(dragon.hp>0 && hp>0) {
            dragonPrinter(dragon);
            //Scanner console = new Scanner(System.in);
            System.out.println("Do you want to block (0 or any), attack (1), or dodge (2)?");
            int move = Integer.parseInt(console.next());
            int choice = (int) (Math.random() * 3);

            //0 is block 1 is attack 2 is parry
            //0 wing pushback 1 is fire 2 is bite
            //p:c  (w):  0>2    2>1 1>0
            if (move == 1) {
                if (choice == 0) {
                    System.out.println(name + "'s attack was slightly blocked by the dragon's scale!");
                    dragon.hp -= blockeddp;
                }
                if (choice == 1) {
                    System.out.println("You hit the dragon, but it blew fire on you!");
                    dragon.hp -= dp;
                    hp -= (dragon.dp - armor);
                } if(choice==2) {
                    System.out.println(name + "'s sword was caught in the dragon's mouth");
                    hp -= dragon.dp;
                }

            }
            if (move == 2) {
                if (choice == 1) {
                    System.out.println(name + " perfectly dodge the dragon's fire, and were able to hit him!");
                    dragon.hp -= dp;


                }
                if (choice == 2) {
                    System.out.println("You got bitten, however your sword caught the dragon's mouth!");
                    dragon.hp -= blockeddp;
                    hp -= (dragon.dp - armor);


                } if(choice==0) {
                    System.out.println(name + " got pushed back by a gust of wind made by the dragon's wings");
                    hp -= dragon.dp;

                }

            } if(move==0) {
                if (choice == 1) {
                    System.out.println(name + " slightly blocked the fire!");
                    hp -= (dragon.dp - armor);

                }
                if (choice == 2) {
                    System.out.println(name + " you stopped the dragon's bite and were able to get a hit on it!");
                    dragon.hp -= dp;

                } if(choice==0) {
                    System.out.println("You blocked the wind gust!");

                }

            }

            System.out.println("That was the end of round " + round);
            System.out.println("You have " + hp + " HEALTH and your opponent has " + dragon.hp + " HEALTH");
            round++;


        }

        if(hp<dragon.hp){
            System.out.println(name+" you lost!");
        }if(dragon.hp<hp){
            System.out.println(name+" you won!");
            win+=3;
        }
        if(dragon.hp==hp){
            System.out.println("You drew!");
            win+=2;
        }
        return win;

    }
    public static int joustScene(int hp, int dp, int armor, String name) {

        Scanner console = new Scanner(System.in);
        character joust;
        joust = new character(hp, dp,armor, name);
        joust.hp=100;
        joust.dp=25;
        int blockeddp= joust.dp /2;
        int round = 0;
        int win=0;

        while(joust.hp>0 && hp>0) {
            joustPrinter(hp,joust);
            //Scanner console = new Scanner(System.in);
            System.out.println("Do you want to block (0 or any), attack (1), or parry (2)?");
            int move = Integer.parseInt(console.next());
            int choice = (int) (Math.random() * 3);

            //0 is block 1 is attack 2 is parry
            //p:c  (w):  0>2    2>1 1>0
            if (move == 1) {
                if (choice == 0) {
                    System.out.println(name + "'s attack was slightly blocked!");
                    joust.hp -= blockeddp;
                }
                if (choice == 1) {
                    System.out.println("Both jousters attacked!");
                    joust.hp -= dp;
                    hp -= (joust.dp - armor);
                } if(choice==2) {
                    System.out.println(name + "'s attacked was countered!");
                    hp -= joust.dp;
                }

            }
            if (move == 2) {
                if (choice == 1) {
                    System.out.println(name + " perfectly parried!");
                    joust.hp -= dp;


                }
                if (choice == 2) {
                    System.out.println("Both opponents slightly hit each other!");
                    joust.hp -= blockeddp;
                    hp -= (joust.dp - armor);


                } if(choice==0) {
                    System.out.println(name + " got knocked off his horse!");
                    hp -= joust.dp;

                }

            } if(move==0) {
                if (choice == 1) {
                    System.out.println(name + " slightly blocked the attack!");
                    hp -= (joust.dp - armor);

                }
                if (choice == 2) {
                    System.out.println(name + " knocked his opponent off his horse!");
                    joust.hp -= dp;

                } if(choice==0) {
                    System.out.println("Both blocked");

                }

            }

            System.out.println("That was the end of round " + round);
            System.out.println("You have " + hp + " HEALTH and your opponent has " + joust.hp + " HEALTH");
            round++;


        }

        if(hp<joust.hp){
            System.out.println(name+" you lost!");
        }if(joust.hp<hp){
            System.out.println(name+" you won!");
            win+=1;
        }
        if(joust.hp==hp){
            System.out.println("You drew!");
            win+=1;
        }
        return win;
    }
    private static void joustPrinter(int hp, character joust){
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
        System.out.println("     "+hp+"  \t\t\t\t\t\t"+"         "+joust.hp+"   ");
    }
    public static int testScene() {
        int problem=1;
        int correct=0;
        String q1 = "What is water around   |\n           | a castle called?      |\n           |                       |\n           | \ta) bays            |\n   " +
                "        | \tb) moats           | " +
                " \n           | \tc) fountains       | \n           | \td) rings           |";
        String a1 = "b";

        String q2 = "Why did stone castles  |\n           | replace the motte and |\n           | bailey castles?       |\n" +
                "           |                       |\n           | a) intimidation       |\n   " +
                "        | b) resource scarcity  | " +
                " \n           | c) weather protection | \n           | d) reliability        |";
        String a2 = "d";

        String q3 = "Which of these         |\n           | animals are the most  |\n           | esteemed to catch?    |\n           |     " +
                "                  |\n           | \ta) wild dogs       |\n   " +
                "        | \tb) foxes           | " +
                " \n           | \tc) otters          | \n           | \td) badgers         |";
        String a3= "c";

        while(problem<4) {


            String answer = testPrinter(q1, q2, q3, problem);


            if (problem == 1) {
                if (Objects.equals(answer, a1)) {
                    correct += 1;
                }


            }
            if (problem == 2) {
                if (Objects.equals(answer, a2)) {
                    correct += 1;
                }

            }
            if (problem == 3) {
                if (Objects.equals(answer, a3)) {
                    correct += 1;
                }
            }
            problem += 1;
        }
        System.out.println("You got "+correct+"/3!");
        return correct;

    }
    private static String testPrinter(String q1, String q2, String q3, int problem){
        String answer = null;
        Scanner console = new Scanner(System.in);
        if(problem==1){



            System.out.println("           _________________________");
            System.out.println("         =(__     ___      __      _)=");
            System.out.println("           |"+ q1);
            System.out.println("           |                       |");
            System.out.println("           |                       |");
            System.out.println("           |   Enter your answer:  |");
            System.out.println("           |                       |");
            System.out.println("           |      X__________      |");
            System.out.println("           |__     ___   __     ___|");
            System.out.println("         =(_________________________)=");
            answer=console.nextLine();


        }if(problem==2){
            System.out.println("           _________________________");
            System.out.println("         =(__     ___      __      _)=");
            System.out.println("           |"+ q2);
            System.out.println("           |                       |");
            System.out.println("           |   Enter your answer:  |");
            System.out.println("           |                       |");
            System.out.println("           |      X__________      |");
            System.out.println("           |__     ___   __     ___|");
            System.out.println("         =(_________________________)=");
            answer=console.nextLine();

        }if(problem==3){
            System.out.println("           _________________________");
            System.out.println("         =(__     ___      __      _)=");
            System.out.println("           |"+ q3);
            System.out.println("           |                       |");
            System.out.println("           |   Enter your answer:  |");
            System.out.println("           |                       |");
            System.out.println("           |      X__________      |");
            System.out.println("           |__     ___   __     ___|");
            System.out.println("         =(_________________________)=");
            answer=console.nextLine();

        }



        return answer;
    }

}


