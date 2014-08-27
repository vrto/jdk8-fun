package org.jdk8fun;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StreamFun {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        allIn2011(transactions);
        uniqueCities(transactions);
        allTradersFromCambridgeSortedByName(transactions);
        allTradersSortedAlphabetically(transactions);
        areAnyTradersBasedInMilan(transactions);
        allTransactionsValuesFromTradersLivingInCambridge(transactions);
        highestTransactionValue(transactions);
        transactionWithSmallestValue(transactions);
    }

    private static void transactionWithSmallestValue(List<Transaction> transactions) {
        System.out.println("\nTransaction with smallest value");

        Transaction smallestTransaction = transactions.stream()
                .min(comparing(Transaction::getValue))
                .get();

        System.out.println(smallestTransaction);
    }

    private static void highestTransactionValue(List<Transaction> transactions) {
        System.out.println("\nHighest value of all transactions");

        Integer max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .get();

        System.out.println(max);
    }

    private static void allTransactionsValuesFromTradersLivingInCambridge(List<Transaction> transactions) {
        System.out.println("\nPrint all transactions’ values from the traders living in Cambridge");

        List<Integer> transactionValues = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(toList());

        System.out.println(transactionValues);
    }

    private static void areAnyTradersBasedInMilan(List<Transaction> transactions) {
        System.out.println("\nAre any traders based in Milan?");

        boolean areTradersInMilan = transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));

        System.out.println(areTradersInMilan);
    }

    private static void allTradersSortedAlphabetically(List<Transaction> transactions) {
        System.out.println("\nAll traders' names sorted alphabetically");

        String tradersNames = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(joining());

        System.out.println(tradersNames);
    }

    private static void allTradersFromCambridgeSortedByName(List<Transaction> transactions) {
        System.out.println("\nAll traders from Cambridge sorted by name");

        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        System.out.println(traders);
    }

    private static void uniqueCities(List<Transaction> transactions) {
        System.out.println("\nAll unique cities where traders work");

        List<String> uniqueCities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());

        System.out.println(uniqueCities);
    }

    private static void allIn2011(List<Transaction> transactions) {
        System.out.println("All transactions in year 2011 sorted by value (small to high)");

        List<Transaction> allIn2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(allIn2011);
    }

}
