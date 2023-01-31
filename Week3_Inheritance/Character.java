public abstract class Character {

    public int health=100;

    public int baseDamage=25;
    public String charName;
    public float moveSpeed=5f;

    public enum typeClass{
        warrior,
        archer,
        paladin
    }

    public typeClass style;
    public void takeDamage(int baseDamage){

            System.out.println("The "+ style+ " is taking "+baseDamage+" Damage");
            setHealth(health-baseDamage);
    }



    //abstract allow implementation outside the parent class since the abilities are so unique i felt this was better
    public abstract void specialSkill1();

    public abstract void specialSkill2();

    public abstract void specialSkill3();

    public abstract void specialSkill4();

    public int getHealth(){

        return health;
    }
    private void setHealth(int number){

        this.health=number;
    }

    @Override
    public String toString() {
        return "Character{" +
                "health=" + health +
                ", baseDamage=" + baseDamage +
                ", charName='" + charName + '\'' +
                ", moveSpeed=" + moveSpeed +
                ", style=" + style +
                '}';
    }
}
