package FoodShortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Buyer> buyerList = new LinkedHashMap<>();

        while (n-- > 0){
            String[] token = scanner.nextLine().split("\\s+");
            Buyer buyer = token.length== 4
                    ? new Citizen(token[0], Integer.parseInt(token[1]), token[2], token[3])
                    : new Rebel(token[0], Integer.parseInt(token[1]), token[2] );
            buyerList.putIfAbsent(token[0], buyer);
        }

        String name = scanner.nextLine();

        while (!name.equals("End")){
           if (buyerList.containsKey(name)){
               for (Map.Entry<String, Buyer> entry : buyerList.entrySet()) {
                   if (entry.getKey().equals(name)){
                       entry.getValue().buyFood();
                       break;
                   }
               }
           }
            name = scanner.nextLine();
        }
        System.out.println(buyerList.values().stream().mapToInt(Buyer::getFood).sum());
    }
}



