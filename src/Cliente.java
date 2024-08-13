import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Cliente extends Pessoa implements IPessoa{
    private String cpfCliente,emailCliente,telefoneCliente;
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static ArrayList<Produto> carinho = new ArrayList<>();
    public static void addCarinho(Produto p)
    {carinho.add(p);}

    public static void setCarinho(ArrayList<Produto> carinho) {
        Cliente.carinho = carinho;
    }

    public static void removeCarinho(Produto p)
    {
        carinho.remove(p);
    }

    public static ArrayList<Produto> getCarinho() {
        return carinho;
    }


    public String exibir()
    {
        return "Nome do cliente: "+getNome()+"\nIdade do cliente: "+getIdade()+"\nCPF do cliente: "+cpfCliente+"\nTelefone do cliente: "+telefoneCliente+"\nE-mail do cliente: "+emailCliente+"\n";
    }

    public Cliente(String nome, int idade, String cpfCliente, String emailCliente, String telefoneCliente) {
        super(nome, idade);
        if(cpfCliente.length()==11)
        {
            this.cpfCliente = cpfCliente;
        }
        else
        {
            System.out.println("cpf digitado errado");
            throw new LengthException();
        }

        try{

            if(Integer.parseInt(telefoneCliente)<0)
            {
                System.out.println("Número digitado errado");
                throw new InvalidParameterException();
            }
            this.telefoneCliente = telefoneCliente;
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Não pode ter letras no telefone");

        }
        try{
            int h=0;

            for (int i=0; i<emailCliente.length();i++)
            {
                if('@'==emailCliente.charAt(i))
                {
                    h=1;
                    break;
                }
            }
            if (h==0)
            {
                throw new InvalidParameterException();
            }
            else if(h==1){
                this.emailCliente = emailCliente;
            }
        }
        catch(InvalidParameterException e)
        {
            System.out.println("Email digitado errado");
        }

    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public static void cadastrar(Cliente cliente)
    {
        listaClientes.add(cliente);
    }

    public static void excluir(String cpfCliente)
    {
        ArrayList<Cliente> listaTemp = new ArrayList<>();
        for(Cliente c : listaClientes){
            if(c.cpfCliente.equals(cpfCliente)){
                listaTemp.add(c);
            }
        }
        if(listaTemp.isEmpty()){
            System.out.println("Não foi encontrado nenhum CPF igual ao solicitado!");
        }
        else{
            listaClientes.removeAll(listaTemp);
        }
    }

    public void setCpfCliente(String cpfCliente) {
       if(cpfCliente.length()==11)
       {
        this.cpfCliente = cpfCliente;
    }
       else
       {
           System.out.println("cpf digitado errado");
           throw new LengthException();
       }
    }

    public void setEmailCliente(String emailCliente) {
        int h=0;

        for (int i=0; i<emailCliente.length();i++)
        {
            if('@'==emailCliente.charAt(i))
            {
                h=1;
            }
        }
        if (h==0)
        {
            System.out.println("Email digitado errado");
            throw new InvalidParameterException();
        }
    }

    public void setTelefoneCliente(String telefoneCliente) {
        try{

            if(Integer.parseInt(telefoneCliente)<0)
            {
                System.out.println("Número digitado errado");
                    throw new InvalidParameterException();
            }
        this.telefoneCliente = telefoneCliente;
    }
    catch (NumberFormatException ex)
    {
        System.out.println("Não pode ter letras no telefone");
        throw new NumberFormatException();
    }
    }
    public static void buscarClientes(String filtro)
    {
        int h=0;
        for(Cliente p:listaClientes)
        {
            if(p.getNome().startsWith(filtro))
            {
                h=1;
                System.out.println(p.exibir()+"---------------------\n");
            }
        }
        if (h==0)
        {
            System.out.println("Nenhum nome encontrado");
        }
    }
    public static String maiorIdade()
    {
        ArrayList<Cliente> idade = new ArrayList<>();
        int m=listaClientes.getFirst().getIdade();
        for(Cliente c:listaClientes)
        {
            if(c.getIdade()==m)
            {
                idade.add(c);
            }
        else if(c.getIdade()>m)
        {
            idade.clear();
            idade.add(c);
            m=c.getIdade();
        }
        }
        String a="";
for (Cliente p:idade)
{
    a+=p.exibir()+"\n";
}
        if(idade.size()>1)
        {
            System.out.println("As pessoas com maior idade no sistema são:\n");
        }
        else if(idade.size()==1)
        {
            System.out.println("A pessoa com maior idade no sistema é:\n");
        }
        else
        {
            a = "Não tem pessoas no sistema";
        }
        return a;
    }
    public static String menorIdade()
    {
        ArrayList<Cliente> idade = new ArrayList<>();
        int m;
        m=listaClientes.getFirst().getIdade();
        for(Cliente c:listaClientes)
        {

            if(c.getIdade()<m)
            {
                idade.clear();
                idade.add(c);
            }
            else if(c.getIdade()==m)
            {
                idade.add(c);
            }
        }
        String a="";
        for (Cliente p:idade)
        {
            a+=p.exibir()+"\n";
        }
        if(idade.size()>1)
        {
            System.out.println("As pessoas com menor idade no sistema são:\n");
        }
        else if(idade.size()==1)
        {
            System.out.println("A pessoa com menor idade no sistema é:\n");
        }
        else
        {
            a = "Não tem pessoas no sistema";
        }
        return a;
    }
    public static int clientesMenosDe18()
    {
     int m=0;
        for(Cliente a:listaClientes)
        {

            if(a.getIdade()<18)
            {
                m++;
            }
        }
    return m;
    }
    public static int clientesMaisDe60()
    {
        int m=0;
        for(Cliente c:listaClientes)
        {

            if(c.getIdade()>60)
            {
                m++;
            }
        }
        return m;
    }
    public static int mediaDeIdades()
    {
        if(listaClientes.size()>0)
        {        int m=0;
        for(Cliente a:listaClientes)
        {
            m+=a.getIdade();
        }
        return m/listaClientes.size();
    }
    else
        {
            System.out.println("Não tem nenhum cliente cadastrado");
          throw new EmptyStackException();
        }
    }
    public static void limpar()
    {
        listaClientes.clear();
    }
    public static void exibirLista()
    {
        for (Cliente a:listaClientes)
        {
            System.out.println(a.exibir());
        }
    }
    public static ArrayList<Cliente> getListaClientes(){
        return listaClientes;
    }
}