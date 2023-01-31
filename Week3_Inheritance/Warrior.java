
/**
 * @para Warrior subclass of character melee attack heavy
 * */
public class Warrior extends Character{

    int physicalWeaponDamage;
    public Warrior(String charName,typeClass type, int physicalWeaponDamage){
        this.charName=charName;
        this.style=type;
        this.physicalWeaponDamage=physicalWeaponDamage;
    }
    /**
     * Leaps in the air and smashes the target dealing (Weapon Damage * 10)
     * */
    public void finalBlow(Character character){
        int finalHit=this.physicalWeaponDamage*10;
        System.out.println("The final blow damage is " +finalHit + " damage");

        //using base class so it is unversial to every character
        character.takeDamage(finalHit);
        System.out.println(character.charName + " has " + character.health+" health");
    }

    @Override
    public void specialSkill1() {

        System.out.println("Whirlwind");
        //math goes here
    }

    @Override
    public void specialSkill2() {

        System.out.println("Siesmic Slam");
        //math goes here
    }

    @Override
    public void specialSkill3() {

        System.out.println("Grappling Hook");
        //math goes here
    }

    @Override
    public void specialSkill4() {

        System.out.println("Thunderclap");

        //math goes here

    }
}
