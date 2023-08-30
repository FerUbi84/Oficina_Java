package trabalho_poo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class Avaria implements Serializable {

    private int idAvaria;
    private int idTipoProduto;
    private int nifCliente;
    private int idTipoAvaria;

    Avaria() {
    }

    Avaria(int idAvaria) {
        this.idAvaria = idAvaria;
    }

    Avaria(int idAvaria, int idTipoProduto, int nifCliente, int idTipoAvaria) {
        this.idAvaria = idAvaria;
        this.idTipoProduto = idTipoProduto;
        this.nifCliente = nifCliente;
        this.idTipoAvaria = idTipoAvaria;
    }

    public Avaria(int idAvaria, int idTipoProduto, int idTipoAvaria) {
        this.idAvaria = idAvaria;
        this.idTipoProduto = idTipoProduto;
        this.idTipoAvaria = idTipoAvaria;
    }

    public int getNifCliente() {
        return nifCliente;
    }

    public int getIdAvaria() {
        return idAvaria;
    }

    public int getIdTipoAvaria() {
        return idTipoAvaria;
    }

    public int getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(int idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    public void setIdTipoAvaria(int idTipoAvaria) {
        this.idTipoAvaria = idTipoAvaria;
    }

    public void setIdAvaria(int idAvaria) {
        this.idAvaria = idAvaria;
    }

    public int pesquisaAvaria(int nif, ArrayList<Avaria> avaria) {
        int id_avaria = 0;
        for (Avaria a : avaria) {
            if (a.getNifCliente() == nif) {
                id_avaria = a.getIdAvaria();
                return id_avaria;
            }
        }

        return -1;
    }

    /**
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     *
     * Retorna teste
     */
    public ArrayList<Avaria> readFromFileAvaria(String file) throws FileNotFoundException {

        File insertFile = new File(file + ".txt");
        Scanner scanner = new Scanner(insertFile);

        ArrayList<Avaria> avaria = new ArrayList<Avaria>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] items = line.split("\\|");

            int idAvaria = Integer.parseInt(items[0]);
            int tipoProduto = Integer.parseInt(items[1]);
            int nifCliente = Integer.parseInt(items[2]);
            int tipoAvaria = Integer.parseInt(items[2]);

            Avaria av = new Avaria(idAvaria, tipoProduto, nifCliente, tipoAvaria);
            avaria.add(av);

        }
        scanner.close();
        return avaria;
    }

    public ArrayList<Avaria> insertAvaria(int idAvaria, int idTipoProduto, int nifCliente, int idTipoAvaria) throws IOException {
        Scanner s = new Scanner(System.in);
        ArrayList<Avaria> avaria = new ArrayList<Avaria>();
        FileAction fileAction = new FileAction();
        int nif_c = 0;
        boolean haveData = fileAction.checkFile("avarias");
        int inserir_avaria = 1;

        if (haveData) {
            avaria = readFromFileAvaria("avarias");
        } else {
            System.out.println("Lista vazia");
        }

        while (inserir_avaria != 0) {

            if (avaria.isEmpty()) {
                idAvaria = 1;
            } else {
                idAvaria = avaria.size() + 1;
            }

            System.out.println("Insira o tipo de produto:\n1-eletrodomestico\n2-som/video\n3-informática\n4-telemóveis\n");
            idTipoProduto = s.nextInt();

            //VERIFICA SE EXISTE o cliente na lista 
            Cliente c = new Cliente();
            ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
            listaClientes = c.readFromFile("clientes");

            System.out.println("Pesquise o cliente pelo nif:");
            nifCliente = s.nextInt();
            nif_c = c.pesquisaCliente(nifCliente, listaClientes);
            while (nif_c == -1) {
                System.out.println("Cliente enixistente, tente de novo: ");
                nifCliente = s.nextInt();
                nif_c = c.pesquisaCliente(nifCliente, listaClientes);
            }
            c.setNif(nif_c);
            //ENDOF VERIFICA SE EXISTE o cliente na lista

            System.out.println("Insira o tipo de Avaria:\n1-queda\n2-curto-circuito\n3-mau uso");
            idTipoAvaria = s.nextInt();

            Avaria a = new Avaria(idAvaria, idTipoProduto, nif_c, idTipoAvaria);
            avaria.add(a);

            System.out.println("Deseja inserir mais algum cliente?\n");
            System.out.println("1 - SIM\n");
            System.out.println("0 - NAO\n");
            inserir_avaria = s.nextInt();

            String infoAvaria = idAvaria + "|" + idTipoProduto + "|" + nifCliente + "|" + idTipoAvaria;
            fileAction.writeFile("avarias", infoAvaria);
        }

        return avaria;
    }

    public String nomeTipoProduto(int tipo) {

        String nome = null;

        switch (tipo) {
            case 1:
                nome = "electrodoméstico";
                break;
            case 2:
                nome = "som/video";
                break;
            case 3:
                nome = "informática";
                break;
            case 4:
                nome = "telemóveis";
                break;
        }

        return nome;
    }

    public String nomeTipoAvaria(int tipo) {

        String nome = null;

        switch (tipo) {
            case 1:
                nome = "queda";
                break;
            case 2:
                nome = "curto-circuito";
                break;
            case 3:
                nome = "mau uso";
                break;
        }

        return nome;
    }

    public String fichaAvaria(int nif, ArrayList<Avaria> avaria) throws FileNotFoundException {
        String id_avaria = "";
        String nif_c = "";
        String nome_cli = "";
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cliente c = new Cliente();
        String tipoProduto = "";
        String tipoAvaria = "";
        int encontrado = 0;

        clientes = c.readFromFile("clientes");
        for (Avaria a : avaria) {

            nif_c = String.valueOf(nif);

            if (nif == a.getNifCliente()) {
                encontrado = 1;
                id_avaria = String.valueOf(a.idAvaria);
                nif_c = String.valueOf(nif);
                tipoProduto = nomeTipoProduto(a.getIdTipoProduto());
                tipoAvaria = nomeTipoAvaria(a.getIdAvaria());

            }
        }
        nome_cli = c.printNomeCliente(nif, clientes);

        if (encontrado == 1) {
            return "Ficha da Avaria\nID avaria:" + id_avaria + "\nNome Cliente:" + nome_cli + "  Nif Cliente:" + nif_c + "\nTipo de Produto:" + tipoProduto + "\nTipo de Avaria:" + tipoAvaria;
        } else {
            return "Cliente não existente nas fichas de avarias";
        }
    }

    @Override
    public String toString() {
        String nome_cliente = "";
        Cliente cliente = new Cliente();
        String tipo_produto = "", tipo_avaria = "";
        try {
            ArrayList<Cliente> clientes = new ArrayList<Cliente>();
            clientes = cliente.readFromFile("clientes");
            nome_cliente = cliente.printNomeCliente(nifCliente, clientes);
            tipo_produto = nomeTipoProduto(idTipoProduto);
            tipo_avaria = nomeTipoAvaria(idTipoAvaria);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Avaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "\nID Avaria=" + idAvaria + ",\nTipo de Produto=" + tipo_produto + ",\nCliente=" + nome_cliente + "\tNIF Cliente=" + nifCliente + "\nTipo de Avaria=" + tipo_avaria + "\n";
    }
}
