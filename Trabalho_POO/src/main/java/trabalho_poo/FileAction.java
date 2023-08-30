package trabalho_poo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class FileAction {

    public String printTofile(String palavra) {
        return palavra;
    }

    public String create_file(String nome) {

        try {
            File file = new File(nome + ".txt");
            if (file.createNewFile()) {
                System.out.println("Ficheiro criado com o nome" + file.getName());
            } else {
                System.out.println(file.getName() + " já existe");
            }
        } catch (IOException e) {
            System.err.println("Erro criação");
            e.printStackTrace();
        }
        return nome;
    }

  
    public boolean writeFile(String nameFile, String data) {
        boolean flag = false;
        try {
            FileWriter writeFile = new FileWriter(nameFile + ".txt", true);
            BufferedWriter writer = new BufferedWriter(writeFile);
            writer.append(data);
            writer.newLine();
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

    /**
     *
     * @param file
     * @return
     * @throws FileNotFoundException Método do tipo ArrayList que recebe como
     * parâmetro o nome do ficheiro
     */
    public static ArrayList<Cliente> readFromFile(String file) throws FileNotFoundException {

        File insertFile = new File(file + ".txt");
        Scanner scanner = new Scanner(insertFile);

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] items = line.split("\\|");

            int idCliente = Integer.parseInt(items[0]);
            String nome = items[1];
            int nif = Integer.parseInt(items[2]);

            Cliente cliente = new Cliente(idCliente, nome, nif);
            clientes.add(cliente);

        }
        scanner.close();
        return clientes;

    }

    public boolean checkFile(String nameFile) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(nameFile + ".txt"));
        if (br.readLine() == null) {
            System.out.println("ficheiro vazio");
            return false;
        } else {
            System.out.println("ficheiro com dados");
            return true;
        }
    }

}
