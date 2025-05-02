import java.util.Scanner;

public class Xenogen {
    public static void main(String[] args) {
        int alienDnaLength = 128;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of Alien population: ");
        int alienPopulation = sc.nextInt();

        String[][] alienDNAs = generateAlienDNAs(alienPopulation, alienDnaLength);
        System.out.println("Simulating Alien Species: ");
        System.out.println("---------------------------");
        for (int i = 0; i < alienPopulation; i++) {
            System.out.print("ID: " + (i+1) + ", ");
            System.out.print(getAlienGender(alienDNAs[i]) + ", ");
            System.out.println("Health: " + getAlienHealth(alienDNAs[i]));
        }
        System.out.println("---------------------------");
        System.out.println("Alien Species Simulation Complete!");
        System.out.println(" ");


        while (true){
            int maleCount = 0;
            int femaleCount = 0;
            System.out.println(" ");
            System.out.println("Choose an options:\n" +
                    "(1)Mate two compAliens\n" +
                    "(2)Randomly mate a set of compAliens\n" +
                    "(3)Show statistics\n" +
                    "(4)Mate All compAliens\n" +
                    "(5)Mate compAliens have health greater than ...");
            int option = sc.nextInt();

            switch (option){
                case 1:
                    System.out.print("Enter the ID of the first compAlien: ");
                    int firstAlienIndex = sc.nextInt();
                    System.out.print("Enter the ID of the second compAlien: ");
                    int secondAlienIndex = sc.nextInt();
                    String[] child = mateAliens(alienDNAs[firstAlienIndex-1], alienDNAs[secondAlienIndex-1]);
                    System.out.print("compAlien " + firstAlienIndex+" " + getAlienGender(alienDNAs[firstAlienIndex-1]) + " and compAlien " + secondAlienIndex +" "+ getAlienGender(alienDNAs[secondAlienIndex-1]) + " Mate: Offspring chance "+ ((getAlienHealth(alienDNAs[firstAlienIndex-1]) + getAlienHealth(alienDNAs[secondAlienIndex-1]))*100) / (alienDnaLength/3)+"%" );
                    if(child[0] == null){
                        System.out.println(" No child");
                    }else{
                        System.out.println(". They have 1 offspring");
                        alienDNAs = addChild(alienDNAs, child);
                        alienPopulation++;
                    }

                    break;
                case 2:
                    System.out.print("Enter the number of compAliens to mate: ");
                    int alienCount = sc.nextInt();
                    for (int i = 0; i < alienCount; i++) {
                        firstAlienIndex = (int)(Math.random()*(alienPopulation-1));
                        secondAlienIndex =(int)(Math.random()*(alienPopulation-1));
                        String[] firstAlien = alienDNAs[firstAlienIndex];
                        String[] secondAlien = alienDNAs[secondAlienIndex];
                        child = mateAliens(firstAlien, secondAlien);

                        System.out.print("compAlien " + firstAlienIndex+ " "+ getAlienGender(firstAlien) + " and compAlien " + secondAlienIndex +" " + getAlienGender(secondAlien) + " Mate: Offspring chance "+ ((getAlienHealth(firstAlien) + getAlienHealth(secondAlien))*100) / (alienDnaLength/3) +"%" );

                        if(child[0] == null){
                            System.out.println(" No child");
                        }else{
                            System.out.println(" They have 1 offspring");
                            alienDNAs = addChild(alienDNAs, child);
                            alienPopulation++;
                        }

                    }
                    break;
                case 3:
                    System.out.println("compAlien Population Statistics");
                    System.out.println("---------------------------");
                    femaleCount  = 0;
                    maleCount = 0;
                    for (int i = 0; i < alienPopulation; i++) {
                        if(getAlienGender(alienDNAs[i]).equals("Male")){
                            maleCount++;
                        }else{
                            femaleCount++;
                        }
                    }
                    System.out.println("FEMALE population =" + (femaleCount*100/alienPopulation) + "%");
                    System.out.println("MALE population =" + (maleCount*100/alienPopulation) + "%");
                    System.out.print("Enter an health threshold: ");
                    int healthThreshold = sc.nextInt();
                    int healthyAlienCount = 0;
                    for (int i = 0; i < alienPopulation; i++) {
                        if(getAlienHealth(alienDNAs[i]) > healthThreshold){
                            healthyAlienCount++;
                        }
                    }
                    System.out.println("Healthy population =" + (healthyAlienCount*100/alienPopulation));
                    break;
                case 4:
                    maleCount = 0;
                    femaleCount = 0;
                    for (int i = 0; i < alienPopulation; i++) {
                        if(getAlienGender(alienDNAs[i]).equals("Male")){
                            maleCount++;
                        }else {
                            femaleCount++;
                        }
                    }

                    int[] femaleIDs = new int[femaleCount];
                    int[] maleIDs = new int[maleCount];
                    String[][] females = new String[femaleCount][alienDnaLength];
                    String[][] males = new String[maleCount][alienDnaLength];

                    int maleIndex = 0;
                    int femaleIndex = 0;
                    for (int i = 0; i < alienPopulation; i++) {
                        if(getAlienGender(alienDNAs[i]).equals("Male")){
                            males[maleIndex] = alienDNAs[i];
                            maleIDs[maleIndex] = i;
                            maleIndex++;
                        }else {
                            females[femaleIndex] = alienDNAs[i];
                            femaleIDs[femaleIndex] = i;
                            femaleIndex++;
                        }
                    }

                    int forLimit = femaleCount;
                    if(maleCount<femaleCount){
                        forLimit = maleCount;
                    }

                    for (int i = 0; i < forLimit; i++) {
                        String[] childALien = mateAliens(females[i], males[i]);
                        System.out.print("compAlien " + femaleIDs[i] + " " +getAlienGender(females[i]) + " and compAlien " + maleIDs[i] + " " + getAlienGender(males[i]) + " Mate: Offspring chance "+ ((getAlienHealth(females[i]) + getAlienHealth(males[i]))*100) / (alienDnaLength/6.0) +"%");
                        if(childALien[0] == null){
                            System.out.println("No child");
                        }else{
                            System.out.println(". They have 1 offspring");
                            alienDNAs = addChild(alienDNAs, childALien);
                            alienPopulation++;
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter an health threshold: ");
                    healthThreshold = sc.nextInt();
                    String[][] healthyAliens = getAliensHealthGreaterThan(alienDNAs, healthThreshold);
                    maleCount = 0;
                    femaleCount = 0;
                    for (int i = 0; i < healthyAliens.length; i++) {
                        if(getAlienGender(healthyAliens[i]).equals("Male")){
                            maleCount++;
                        }else {
                            femaleCount++;
                        }
                    }

                    femaleIDs = new int[femaleCount];
                    maleIDs = new int[maleCount];
                    String[][] maleHealthyAliens = new String[maleCount][alienDnaLength];
                    String[][] femaleHealthyAliens = new String[femaleCount][alienDnaLength];

                    maleIndex = 0;
                    femaleIndex = 0;
                    for (int i = 0; i < healthyAliens.length; i++) {
                        if(getAlienGender(healthyAliens[i]).equals("Male")){
                            maleHealthyAliens[maleIndex] = healthyAliens[i];
                            maleIDs[maleIndex] = i;
                            maleIndex++;
                        }else {
                            femaleHealthyAliens[femaleIndex] = healthyAliens[i];
                            femaleIDs[femaleIndex] = i;
                            femaleIndex++;
                        }
                    }

                    int healthyForLimit = femaleCount;
                    if(maleCount<femaleCount){
                        healthyForLimit = maleCount;
                    }

                    for (int i = 0; i < healthyForLimit; i++) {
                        String[] childALien = mateAliens(femaleHealthyAliens[i], maleHealthyAliens[i]);
                        System.out.print("compAlien " + femaleIDs[i] + " " +getAlienGender(femaleHealthyAliens[i]) + " and compAlien " + maleIDs[i] + " " + getAlienGender(maleHealthyAliens[i]) + " Mate: Offspring chance "+ ((getAlienHealth(femaleHealthyAliens[i]) + getAlienHealth(maleHealthyAliens[i]))*100) / (alienDnaLength/3) );
                        if(childALien[0] == null){
                            System.out.println("No child");
                        }else{
                            System.out.println(". They have 1 offspring");
                            alienDNAs = addChild(alienDNAs, childALien);
                            alienPopulation++;
                        }
                    }
                    break;
            }
        }
    }

    public static String[][] getAliensHealthGreaterThan(String[][] alienDNAs, int health){
        int count = 0;
        for (int i = 0; i < alienDNAs.length; i++) {
            if(getAlienHealth(alienDNAs[i]) >= health){
                count++;
            }
        }

        String[][] aliens = new String[count][alienDNAs[0].length];
        int index = 0;
        for (int i = 0; i < alienDNAs.length; i++) {
            if(getAlienHealth(alienDNAs[i]) >= health){
                aliens[index] = alienDNAs[i];
                index++;
            }
        }
        return aliens;
    }

    public static String[][] addChild(String[][] alienDNAs, String[] child){
        String[][] newAlienDNAs = new String[alienDNAs.length+1][];
        for (int i = 0; i < alienDNAs.length; i++) {
            newAlienDNAs[i] = alienDNAs[i];
        }
        newAlienDNAs[alienDNAs.length] = child;
        return newAlienDNAs;
    }

    public static int getAlienHealth(String[] alienDNA){
        int alienHealth = 0;
        for (int i = 0; i < alienDNA.length-3; i++) {
            if (alienDNA[i].equals("C")){
                if(alienDNA[i+1].equals("S")){
                    if (alienDNA[i+2].equals("E")){
                        alienHealth++;
                    }
                }
            }
        }

        return alienHealth;
    }

    public static String getAlienGender(String[] alienDNA){
        if(alienDNA[alienDNA.length-1].equals("S")){
            return "Male";
        }else{
            return "Female";
        }
    }

    public static String[][] generateAlienDNAs(int alienPopulation, int alienDnaLength){
        String[][] alienDnas = new String[alienPopulation][alienDnaLength];

        for (int i = 0; i < alienPopulation; i++) {
            alienDnas[i] = generateSingleDNA(alienDnaLength);
        }

        return alienDnas;
    }

    public static String[] generateSingleDNA(int alienDnaLength){
        String[] singleDNA = new String[alienDnaLength];

        for (int i = 0; i < alienDnaLength; i++) {
            int dna = (int) (Math.random() * 3);
            if (dna == 0) {
                singleDNA[i] = "C";
            } else if (dna == 1) {
                singleDNA[i] = "S";
            } else if (dna == 2) {
                singleDNA[i] = "E";
            }
        }

        return singleDNA;
    }

    public static String[] mateAliens(String[] firstAlien, String[] secondAlien){
        String[] child = new String[firstAlien.length];

        if(getAlienGender(firstAlien).equals(getAlienGender(secondAlien))){
            System.out.println(" ");
            System.out.println("same gender");

            return child;
        }

        double p_reproduce = (getAlienHealth(firstAlien) + getAlienHealth(secondAlien)) / (firstAlien.length/6.0);
        if (p_reproduce > 0.5) {
            child = generateSingleDNA(firstAlien.length);
        }

        return child;
    }
}