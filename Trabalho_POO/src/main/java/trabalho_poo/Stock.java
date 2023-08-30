package trabalho_poo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Stock {

    private int qtdPecas;
    private double preco;

    public Stock() {
    }

    public int getQtdPecas() {
        return qtdPecas;
    }

    public void setQtdPecas(int qtdPecas) {
        this.qtdPecas = qtdPecas;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Stock readFromFile(String file) throws FileNotFoundException {

        File insertFile = new File(file + ".txt");
        Scanner scanner = new Scanner(insertFile);
        Stock stock = new Stock();
        

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] items = line.split("\\|");

            int qtdStock = Integer.parseInt(items[0]);
            Double valor = Double.parseDouble(items[1]);

            stock.setQtdPecas(qtdStock);
            stock.setPreco(valor);

        }
        System.out.println(stock.getQtdPecas());
        scanner.close();
        return stock;
    }

    public boolean updateStock(String nameFile, String data) {
        boolean flag = false;
        try {
            FileWriter writer = new FileWriter(nameFile + ".txt", false);
            writer.write(data);
            writer.close();
            System.out.println("Informação gravada com sucesso.");
            flag = true;
        } catch (IOException e) {
            System.err.println("Erro na escrita");
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public void stockFile() throws IOException {

        String stockPecas = qtdPecas + "|" + preco;
        updateStock("stock", stockPecas);
    }

    public int comprarStock(int quantidade) throws IOException {
        readFromFile("stock");
        qtdPecas += quantidade;
        stockFile();
        return qtdPecas;
    }
    
    public int utilizarpecas(int quantidade) throws IOException {
        readFromFile("stock");
        qtdPecas -= quantidade;
        stockFile();
        return qtdPecas;
    }

    @Override
    public String toString() {
        return "Stock\n" + "Quantidade Peças=" + qtdPecas + "\nPreço peça=" + preco + "€\n";
    }
    
    
}
