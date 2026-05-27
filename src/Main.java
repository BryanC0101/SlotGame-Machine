import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static double bet(double betAmount, Player player){
        Random rand = new Random();
        String[] bets = {"🍇", "🍉", "🔔", "⭐", "7"};
        ArrayList<String> betList = new ArrayList<>();

        int indexLuck = 0;

        for(int i = 0; i < 3; i++){
            betList.add(bets[rand.nextInt(bets.length)]);
        }

        //Mostrar emojis
        System.out.println();
        for(String i : betList){
            System.out.print(" | ");
            System.out.print(i);
        }
        System.out.print(" | \n");
        System.out.println();

        // Caso acerte 3 items
        if(betList.get(0).equals(betList.get(1)) &&
           betList.get(1).equals(betList.get(2))) {

            String winner3Items = betList.getFirst();

            for(int i = 0; i < bets.length; i++){
                if(bets[i].equals(winner3Items)){
                    indexLuck = i;
                    break;
                }
            }

            switch (indexLuck){
                case 0 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 2) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 2;
                }
                case 1 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 3) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 3;
                }
                case 2 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 5) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 5;
                }
                case 3 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 10) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 10;
                }
                case 4 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 20) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 20;
                }
            }
        }

        // Caso acerte 2 items
        if(betList.get(0).equals(betList.get(1)) ||
           betList.get(1).equals(betList.get(2)) ||
           betList.get(0).equals(betList.get(2))) {

            String winner2Items = "";

            if(betList.get(0).equals(betList.get(1))) {
                winner2Items = betList.getFirst();
            }
            else if(betList.get(0).equals(betList.get(2))) {
                winner2Items = betList.getFirst();
            }
            else if(betList.get(1).equals(betList.get(2))) {
                winner2Items = betList.get(1);
            }

            for(int i = 0; i < bets.length; i++){
                if(bets[i].equals(winner2Items)){
                    indexLuck = i;
                    break;
                }
            }

            switch (indexLuck){

                case 0 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 1.25) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 1.25;
                }
                case 1 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 1.50) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 1.50;
                }
                case 2 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 2) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 2;
                }
                case 3 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 3) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 3;
                }
                case 4 -> {
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 5) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 5;
                }
            }
        }
        System.out.println("Que pena, você perdeu R$" + betAmount);
        player.adicionarDerrotas();
        return 0;

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(100.00, 0, 0);

        System.out.println("********************************");
        System.out.println("      Bem vindo ao Cassino");
        System.out.println("********************************");

        int response = 1;

        while (response >= 1 && response < 4) {
            System.out.println("Seu saldo atual é: R$" + player.getSaldo() + "\n");
            System.out.println("O que deseja fazer? ");
            System.out.println("""
                1. Girar
                2. Estatísticas
                3. Loja
                4. Sair
                """);
            System.out.print("Digite sua resposta: ");

            double betAmount;

            boolean valid = false;

            while(!valid){
                try{
                    response = scanner.nextInt();
                    valid = true;
                }
                catch(InputMismatchException e){
                    System.out.println("Digite um valor válido!");
                    scanner.nextLine();
                }
            }

            switch (response){
                case 1 -> {
                    if (player.getSaldo() <= 0){
                        System.out.println("Você não possui fundos para esta ação!");
                    }else {
                        boolean validBet = false;
                        while (!validBet)
                            try{
                                System.out.println("Quantos deseja apostar? ");
                                int responseBet = scanner.nextInt();
                                if ((player.getSaldo() - responseBet) >= 0){
                                    betAmount = responseBet;

                                    player.setSaldo(player.getSaldo() - betAmount);

                                    double resultado = bet(betAmount, player);

                                    player.setSaldo(player.getSaldo() + resultado);

                                    validBet = true;
                                }else {
                                    System.out.println("Sua aposta é mais alta que seu saldo, tente novamente!");
                                    scanner.nextLine();
                                }

                            }
                        catch (InputMismatchException e){
                            System.out.println("Por favor, digite uma aposta válida!");
                            scanner.nextLine();
                        }


                    }
                }
                case 2 -> {
                    System.out.println("Histórico: ");
                    System.out.println("Vitórias: " + player.getVitorias());
                    System.out.println("Derrotas: " + player.getDerrotas());
                }
                case 3 -> System.out.println("Loja para abrir");
                case 4 -> {
                    System.out.println("Seu saldo final foi de: R$" + player.getSaldo());
                    System.out.println("Volte sempre!");
                }
            }

        }
    }
}