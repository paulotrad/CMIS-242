public class Archer extends Character{

    int arrowSpeed=5;


    public Archer(String charName, Character.typeClass type){
        this.charName=charName;
        this.style=type;
    }

    //class unique special ability
    /**
     * Leaps in the air and shoots arrow down
     * dealing arrowSpeed * 100
     * */
    public void arrowDance(Character character){
        int danceAttack=arrowSpeed*100;
        System.out.println("Arrow Dance does " +danceAttack);
        //using base class so it is unversial to every character
        character.takeDamage(danceAttack);
        System.out.println(character.charName + " has " + character.health+" health");
    }
    //special attacks made abstract so individual classes are forced to use them
    @Override
    public void specialSkill1() {

        System.out.println("Slowing Arrows");
    }

    @Override
    public void specialSkill2() {

        System.out.println("Revealing Arrow");

    }

    @Override
    public void specialSkill3() {

        System.out.println("Drop Turret");

    }

    @Override
    public void specialSkill4() {

        System.out.println("Stunning Arrow");
    }
}


