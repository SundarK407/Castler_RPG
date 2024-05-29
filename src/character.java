import java.util.Scanner;

public class character{
    int hp;
    int dp;

    int armor;
    String name;

    int stage=0;

    public String printER(){return name +", squire\n Here are your stats: \n\t"+ hp+" HEALTH\n\t"
            +dp+" DAMAGE\n\t"+armor+" ARMOR";}

    public character(int hp, int dp, int armor, String name){
        this.name=name;
        this.hp = hp;
        this.dp = dp;

    }

    public int getAp(){
        Scanner console = new Scanner(System.in);
        int a0=0,a1=0,a2=0,a3=0,a4=0;
        a0=(int) (Math.random()*15);
        a1=(int) (Math.random()*10);
        a2=(int) (Math.random()*25);
        a3=(int) (Math.random()*20);
        a4=(int) (Math.random()*30);
        System.out.println("Would you like a leather tunic (1), embroidered metal (2), " +
                "chain-mail armor(3), gothic armor (4), or standard mail (0)?");



        int armorchoice= Integer.parseInt(console.next());
        if(armorchoice>4|| armorchoice==0){
            armor+=a0;
        }if(armorchoice==1){
            armor+=a1;
        }
        if(armorchoice==2){
            armor+=a2;
        }
        if(armorchoice==3){
            armor+=a3;
        }
        if(armorchoice==4){
            armor+=a4;
        }
        return armor;
    }

    public int getHp(){
        Scanner console = new Scanner(System.in);
        int a0=0,a1=0,a2=0,a3=0,a4=0;
        a0=(int) (Math.random()*15);
        a1=(int) (Math.random()*10);
        a2=(int) (Math.random()*25);
        a3=(int) (Math.random()*20);
        a4=(int) (Math.random()*30);
        int armorchoice= Integer.parseInt(console.next());
        if(armorchoice>4|| armorchoice==0){
            armor+=a0;
        }if(armorchoice==1){
            armor+=a1;
        }
        if(armorchoice==2){
            armor+=a2;
        }
        if(armorchoice==3){
            armor+=a3;
        }
        if(armorchoice==4){
            armor+=a4;
        }

        return hp;
    }

    public int getDp(){
        Scanner console = new Scanner(System.in);
        int a0=0,a1=0,a2=0,a3=0,a4=0;
        a0=(int) (Math.random()*15);
        a1=(int) (Math.random()*10);
        a2=(int) (Math.random()*25);
        a3=(int) (Math.random()*20);
        a4=(int) (Math.random()*30);

        System.out.println("Would you like a straight sword (1), metal spear (2), " +
                "battle axe (3), long sword (4), or standard dagger (0)?");
        int armorchoice= Integer.parseInt(console.next());
        if(armorchoice>4|| armorchoice==0){
            dp+=a0;
        }if(armorchoice==1){
            dp+=a1;
        }
        if(armorchoice==2){
            dp+=a2;
        }
        if(armorchoice==3){
            dp+=a3;
        }
        if(armorchoice==4){
            dp+=a4;
        }
        return dp;

    }







}
