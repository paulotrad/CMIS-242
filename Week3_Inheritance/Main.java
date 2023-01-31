public class Main {
    public static void main(String[] args) {


        System.out.println("CMIS 242 6385\nPaul Otradovec\nWeek3Dsc\nInheritance");


        //instiante objects/characters
        Warrior warrior1= new Warrior("Kevin", Character.typeClass.warrior,60);

        Archer archer1= new Archer("Archie", Character.typeClass.archer);



        ///inherited method being called using them both
        warrior1.takeDamage(archer1.baseDamage);

        archer1.takeDamage(warrior1.baseDamage);


        //checking hp of each character

        System.out.println(warrior1.style+ " has "+warrior1.getHealth());

        System.out.println( archer1.style + " has "+archer1.getHealth());




        //unique methods for classes
        archer1.arrowDance(warrior1);


        warrior1.finalBlow(archer1);
    }
}