package trabalho_poo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {

    private int idCliente;
    private String nome;
    private int nif;

    public Cliente() {
    }

    public Cliente(int nif) {
        this.nif = nif;
    }

    public Cliente(int idCliente, String nome, int nif) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.nif = nif;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public int getNif() {
        return nif;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    @Override
    public String toString() {
        return "\nId do Cliente: " + idCliente + "\nNome Cliente: " + nome + "\nNIF Cliente" + nif + "\n";
    }

    public int pesquisaCliente(int nif, ArrayList<Cliente> clientes) {
        int nif_cliente = 0;
        for (Cliente c : clientes) {
            if (c.getNif() == nif) {
                nif_cliente = c.getNif();
                return nif_cliente;
            }
        }

        return -1;
    }

    public String printNomeCliente(int nif, ArrayList<Cliente> clientes) {
        String nome_cliente = "";
        for (Cliente c : clientes) {
            if (c.getNif() == nif) {
                nome_cliente = c.getNome();
                return nome_cliente;
            } 
        }
        return " ";
    }

    public ArrayList<Cliente> readFromFile(String file) throws FileNotFoundException {

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

    public ArrayList<Cliente> insertCliente(int id, String nome, int nif) throws IOException {
        Scanner s = new Scanner(System.in);
        ArrayList<Cliente> cliente = new ArrayList<>();
        FileAction fileAction = new FileAction();
        int inserir_cliente = 1;
        boolean haveData = fileAction.checkFile("clientes");

        if (haveData) {
            cliente = readFromFile("clientes");
        } else {
            System.out.println("Lista vazia");
        }

        
        
        while (inserir_cliente != 0) {

            if (cliente.isEmpty()) {
                id = 1;
            } else {
                id = cliente.size() + 1;
            }

            System.out.println("Insira o nome do cliente: ");
            nome = s.next();
            System.out.println("Insira o NIF do cliente: ");
            nif = s.nextInt();
            Cliente c = new Cliente(id, nome, nif);
            cliente.add(c);

            System.out.println("Deseja inserir mais algum cliente?\n");
            System.out.println("1 - SIM\n");
            System.out.println("0 - NAO\n");
            inserir_cliente = s.nextInt();

            String infoCliente = id + "|" + nome + "|" + nif;
            fileAction.writeFile("clientes", infoCliente);
        }

        return cliente;
    }
}
