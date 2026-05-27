import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    /* vou fazer um arquivo contendo os items das roleta com suas probabilidades, depois,
    vou mandar isso para a betList e ela vai escolher entre os 100 ou seja quantos items forem
    no .txt. Isso vai criar uma espécie de probabilidade maior do que está agora
    mais parecido com as usadas em caça níqueis reais. */

    static double bet(double betAmount, Player player){

        ArrayList<String> items = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("🍒", 0);
        map.put("🍋", 1);
        map.put("🍇", 2);
        map.put("🍉", 3);
        map.put("🔔", 4);
        map.put("➖", 5);
        map.put("⭐", 6);
        map.put("7", 7);

        String filePath = "items.txt";

        try(BufferedReader reader = new  BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                items.add(line);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }
        catch (IOException e){
            System.out.println("Alguma coisa deu errado!");
        }

        Random rand = new Random();

        ArrayList<String> betList = new ArrayList<>();

        int indexLuck = -1;

        ArrayList<Integer> indexItemsEmojis = new ArrayList<>();

        //Abaixo mandar o emoji random para o array betList
        for(int i = 0; i < 3; i++){
            String randomEmoji = items.get(rand.nextInt(items.size()));
            betList.add(randomEmoji);

            int indexEmoji = map.get(randomEmoji);
            indexItemsEmojis.add(indexEmoji);
        }

        System.out.println();
        //Mostrar emojis
        for(String emoji : betList){
            System.out.print(" | " + emoji);
        }
        System.out.print(" | \n");
        System.out.println();

        // Caso acerte 3 items
        if(betList.get(0).equals(betList.get(1)) &&
           betList.get(1).equals(betList.get(2))) {

            String winner3Items = betList.getFirst();

            indexLuck = map.getOrDefault(winner3Items, -1);

            switch (indexLuck){
                case 0 -> { // 🍒
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 2) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 2;
                }
                case 1 -> { // 🍋
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 3) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 3;
                }
                case 2 -> { // 🍇
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 5) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 5;
                }
                case 3 -> { // 🍉
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 8) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 8;
                }
                case 4 -> { // 🔔
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 12) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 12;
                }
                case 5 -> { // ➖
                    System.out.println("Você ganhou um grande prêmio!!");
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 20) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 20;
                }
                case 6 -> { // ⭐
                    System.out.println("!!!SORTE-SORTE-SORTE!!!");
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 50) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 50;
                }
                case 7 -> { // 7
                    System.out.println("!!!!Você ganhou o prêmio máximo!!!!");
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 100) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 100;
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

            indexLuck = map.getOrDefault(winner2Items, -1);

            switch (indexLuck){

                case 0 -> { // 🍒
                    System.out.println("Aposta ressarcida!");
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 1) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 1;
                }
                case 1 -> { // 🍋
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 1.50) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 1.50;
                }
                case 2 -> { // 🍇
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 2) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 2;
                }
                case 3 -> { // 🍉
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 3) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 3;
                }
                case 4 -> { // 🔔
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 5) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 5;
                }
                case 5 -> { // ➖
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 8) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 8;
                }
                case 6 -> { // ⭐
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 15) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 15;
                }
                case 7 -> { // 7
                    System.out.println("Você ganhou um bom prêmio!!");
                    System.out.println("Parabéns, você ganhou R$" + ((betAmount * 25) - betAmount));
                    player.adicionarVitorias();
                    return betAmount * 25;
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