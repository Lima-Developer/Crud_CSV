import Domain.Interfaces.CSV;

import java.util.Scanner;

public class Main implements CSV {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 7) {
            System.out.println("\n================ Menu =================");
            System.out.println("1- Cadastrar banco de dados");
            System.out.println("2- Exibir banco de dados");
            System.out.println("3- Inserir registro");
            System.out.println("4- Atualizar registo");
            System.out.println("5- Deletar registro");
            System.out.println("6- Recuperar registros removidos");
            System.out.println("7- Sair");
            System.out.println("=======================================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Create Table
                    System.out.print("Informe o nome do arquivo .csv: ");

                    String filePath = scanner.nextLine();
                    CSV.readCSV(filePath);
                    break;
                case 2:
                    // Select Table
                    continue;
                case 3:
                    // Insert Register
                    continue;
                case 4:
                    // Update Register
                    continue;
                case 5:
                    // Delete Register
                case 6:
                    // Undelete Register
                case 7:
                    System.out.println("Saindo do programa");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
