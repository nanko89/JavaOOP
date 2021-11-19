package Archive.AnimalFarm;

public class Chicken {
    private int age;
    private String name;


    public Chicken(String name, int age) {
        setAge(age);
        setName(name);
    }

    private void setAge(int age) {
        if (age< 0 || age > 15){
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    private void setName(String name) {
        if (name == null || name.trim().length() == 0){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    private double calculateProductPerDay(){
        if (this.age >= 0 && this.age <= 5){
            return 2;
        }else if (this.age >= 6 && this.age <= 11){
            return 1;
        }else return 0.75;
    }

    public double productPerDay(){
        return calculateProductPerDay();
    }
}
