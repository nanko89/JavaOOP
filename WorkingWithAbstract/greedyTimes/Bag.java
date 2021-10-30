package greedyTimes;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currentWeight;
    private long totalCash;
    private Map<String, Long> cash;
    private long totalGem;
    private Map<String, Long> gem;
    private long totalGold;
    private boolean addGold;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.currentWeight = 0;
        this.totalCash = 0;
        this.cash = new HashMap<>();
        this.totalGem = 0;
        this.gem = new HashMap<>();
        this.totalGold = 0;
        this.addGold = false;
    }

    public void addCash(String currency, long quantity) {
        if (hasFreeSpace(quantity) && this.totalGem >=this.totalCash + quantity) {
            if (!cash.containsKey(currency)) {
                this.cash.put(currency, quantity);
            } else {
                this.cash.put(currency, this.cash.get(currency) + quantity);
            }
            this.totalCash += quantity;
            this.currentWeight += quantity;
        }
    }

    public void addGems(String gems, long quantity){
        if (hasFreeSpace(quantity) && this.totalGem + quantity <= this.totalGold){
            if (!this.gem.containsKey(gems)) {
                this.gem.put(gems, quantity);
            } else {
                this.gem.put(gems, this.gem.get(gems) + quantity);
            }
            this.totalGem +=  quantity;
            this.currentWeight += quantity;
        }
    }

    public void addGold(long quantity){
        if (hasFreeSpace(quantity)){
            this.totalGold += quantity;
            this.currentWeight += quantity;
            addGold = true;
        }
    }

    private boolean hasFreeSpace(long quantity) {
        return this.currentWeight + quantity <= this.capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (addGold) {
            sb.append("<Gold> $")
                    .append(totalGold)
                    .append(System.lineSeparator());
            sb.append("##Gold - ")
                    .append(totalGold)
                    .append(System.lineSeparator());
        }

        if (gem.size() > 0){
            sb.append("<Gem> $").append(totalGem).append(System.lineSeparator());
            gem.entrySet().stream().sorted((a,b) -> {
                int result = b.getKey().compareTo(a.getKey());
                if (result == 0){
                    result = b.getValue().compareTo(a.getValue());
                }
                return result;
            }).forEach(entry -> sb.append("##")
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue())
                    .append(System.lineSeparator()));
        }

        if (cash.size() > 0){
            sb.append("<Cash> $").append(totalCash).append(System.lineSeparator());
            cash.entrySet().stream().sorted((a,b) -> {
                int result = b.getKey().compareTo(a.getKey());
                if (result == 0){
                    result = b.getValue().compareTo(a.getValue());
                }
                return result;
            }).forEach(entry -> sb.append("##")
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue())
                    .append(System.lineSeparator()));
        }
        return sb.toString().trim() ;
    }
}
