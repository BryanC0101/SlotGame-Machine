import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static String RESET = "\u001B[0m";
    static String DOURADO = "\u001B[33m";
    static String NEGRITO = "\u001B[1m";
    static String VERDE = "\u001B[32m";
    static String AZUL = "\u001B[34m";
    static String ROXO = "\u001B[35m";
    static String VERMELHO = "\u001B[31m";
    static String CIANO = "\u001B[36m";
    /* vou fazer um arquivo contendo os items das roleta com suas probabilidades, depois,
    vou mandar isso para a betList e ela vai escolher entre os 100 ou seja quantos items forem
    no .txt. Isso vai criar uma espécie de probabilidade maior do que está agora
    mais parecido com as usadas em caça níqueis reais. */


    /*
    Possíveis artifícios para comprar na loja:
    1. invencível por 1 rodada (não perde nada caso não ganhe)
    2. ganha 10% a mais por 3 rodadas
     */

    /*
    Caso o usuário chegue a 0 de dinheiro, um agiota vai vir perguntar se ele quer mais dinheiro.
    Caso ele queira (uma quantia de até R$100)
     */


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
                items.add(line.trim());
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

        System.out.println("GIRANDO!!!");
        System.out.println("Boa Sorte!");

        for(int rodada = 0; rodada < 15; rodada++){
            String temp1 = items.get(rand.nextInt(items.size()));
            String temp2 = items.get(rand.nextInt(items.size()));
            String temp3 = items.get(rand.nextInt(items.size()));
            System.out.print("\r [" + temp1 + " | " + temp2 + " | " + temp3 + "]");

            try {
                Thread.sleep(150);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\rRESULTADO FINAL:");
        for(String emoji : betList){
            System.out.print(" | " + emoji);
        }
        System.out.print(" | \n\n");

        // Caso acerte 3 items
        if(betList.get(0).equals(betList.get(1)) &&
           betList.get(1).equals(betList.get(2))) {

            String winner3Items = betList.getFirst();

            indexLuck = map.getOrDefault(winner3Items, -1);

            switch (indexLuck){
                case 0 -> { // 🍒
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 2) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 2;
                }
                case 1 -> { // 🍋
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 3) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 3;
                }
                case 2 -> { // 🍇
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 5) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 5;
                }
                case 3 -> { // 🍉
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 8) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 8;
                }
                case 4 -> { // 🔔
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 12) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 12;
                }
                case 5 -> { // ➖
                    System.out.println(ROXO + "Você ganhou um grande prêmio!!" + RESET);
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 20) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 20;
                }
                case 6 -> { // ⭐
                    System.out.println(AZUL + "!!!SORTE-SORTE-SORTE!!!" + RESET);
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 50) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 50;
                }
                case 7 -> { // 7
                    System.out.println(DOURADO + "!!!!Você ganhou o prêmio máximo!!!!" + RESET);
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 100) - betAmount) + RESET);
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
                    System.out.println(CIANO + "Aposta ressarcida!" + RESET);
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 1) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 1;
                }
                case 1 -> { // 🍋
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 1.50) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 1.50;
                }
                case 2 -> { // 🍇
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 2) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 2;
                }
                case 3 -> { // 🍉
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 3) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 3;
                }
                case 4 -> { // 🔔
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 5) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 5;
                }
                case 5 -> { // ➖
                    System.out.println(VERDE + "SORTE" + RESET);
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 8) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 8;
                }
                case 6 -> { // ⭐
                    System.out.println(CIANO + "!!SORTE-SORTE!!" + RESET);
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 15) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 15;
                }
                case 7 -> { // 7
                    System.out.println(DOURADO + "Você ganhou um bom prêmio!!" + RESET);
                    System.out.println(VERDE + "Parabéns, você ganhou R$" + ((betAmount * 25) - betAmount) + RESET);
                    player.adicionarVitorias();
                    return betAmount * 25;
                }
            }
        }
        System.out.println(VERMELHO + "Que pena, você perdeu R$" + betAmount + RESET);
        player.adicionarDerrotas();
        return 0;

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(100.00, 0, 0);


        System.out.println(DOURADO + "♦ ♣  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ♠ ♥" + RESET);
        System.out.println(NEGRITO + "               BEM-VINDO AO CASSINO                " + RESET);
        System.out.println(VERDE + "          [ Insira sua aposta e gire! ]            " + RESET);
        System.out.println(DOURADO + "♦ ♣  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ♠ ♥\n" + RESET);

        int response = 1;

        while (response >= 1 && response < 4) {
            System.out.println(AZUL + "Seu saldo atual é: R$" + player.getSaldo() + "\n" + RESET);
            System.out.println(VERDE + "O que deseja fazer? " + RESET);
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
                    System.out.println("\n" + ROXO + "━━━━━━━━━ HISTÓRICO JOGADOR ━━━━━━━━━" + RESET);
                    System.out.println("  " + VERDE + "Vitórias: " + RESET + player.getVitorias());
                    System.out.println("  " + VERMELHO + "Derrotas: " + RESET + player.getDerrotas());
                    System.out.println(ROXO + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" + RESET);
                }
                case 3 -> System.out.println("Loja para abrir");
                case 4 -> {
                    System.out.println(AZUL + "Seu saldo final foi de: R$" + player.getSaldo() + RESET);
                    System.out.println("Volte sempre!");
                }
            }

        }
    }
}