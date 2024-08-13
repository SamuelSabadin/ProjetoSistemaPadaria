import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Produto
{
    private static ArrayList<Produto> listaProduto = new ArrayList<>();
    private String descricao, nome_produto, pesquisaProd, codigo_barras;
    //private String pesquisa_produto;
    private Double preco;
    private int validade;
    private ImageIcon imagem =new ImageIcon();

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }
    public void novasetimagem(String s)
    {
        if(s.isEmpty())
        {
            this.imagem=new ImageIcon("src/pao.png");
        }
        else {this.imagem= new ImageIcon(s);}
    }
    public ImageIcon getImagem() {
        return imagem;
    }

    private Scanner scannerPesquisa = new Scanner(System.in);
public Produto()
{

}

    public static ArrayList<Produto> getListaProduto()
    {
        return listaProduto;
    }
    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getNome_produto()
    {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto)
    {
        this.nome_produto = nome_produto;
    }

    public Double getPreco()
    {
        return preco;
    }

    public void setPreco(Double preco)
    {
        this.preco = preco;
    }

    public int getValidade()
    {
        return validade;
    }

    public void setValidade(int validade)
    {
        this.validade = validade;
    }

    public String getCodigo_barras()
    {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras)
    {
        this.codigo_barras = codigo_barras;
    }

    public String exibir()
    {
        return "Nome do produto: "+getNome_produto()+"\nDescrição do produto: "+getDescricao()+"\nCódigo de barras: "+getCodigo_barras()+"\nPreco do produto: R$ "+String.format("%.2f",getPreco())+"\nValidade do produto: "+getValidade()+"\n";
    }

    public Produto(String descricao, String nome_produto, Double preco, int validade, String codigo_barras) {
        try {
            if (Double.parseDouble(codigo_barras) < 0)
                throw new IllegalArgumentException("Código de barras inválido, valor negativo!");
            else if(codigo_barras.length()!=13)
                throw new LengthException();
            else
                this.codigo_barras = codigo_barras;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        catch(LengthException e){
            System.out.println("Digite corretamente o código de barras!");
        }

        try {
            if (preco < 0)
                throw new IllegalArgumentException("Preço do produto inválido, valor negativo!");
            else
                this.preco = preco;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (validade <= 0)
                throw new IllegalArgumentException("Validade do produto inválida, deve ser maior que zero!");
            else
                this.validade = validade;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (descricao == null)
                throw new IllegalArgumentException("Descrição do produto inválida, não pode ser nula ou vazia!");
            else
                this.descricao = descricao;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (nome_produto == null)
                throw new IllegalArgumentException("Nome do produto inválido, não pode ser nulo ou vazio!");
            else
                this.nome_produto = nome_produto;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

        public static void cadastrar (Produto produto)
        {
            listaProduto.add(produto);
        }
        public static void excluir (String codigo_barras)
        {
            ArrayList<Produto> listaTemp = new ArrayList<>();
            for(Produto p : listaProduto){
                if(p.codigo_barras.equals(codigo_barras)){
                    listaTemp.add(p);
                }
            }
            listaProduto.removeAll(listaTemp);
        }

        public void pesquisa_produto ()
        {
            int qtd = 0;
            pesquisaProd = scannerPesquisa.nextLine();
            for(Produto p : listaProduto)
            {
                if(p.getNome_produto().startsWith(pesquisaProd)||p.getCodigo_barras().startsWith(pesquisaProd)||p.getDescricao().startsWith(pesquisaProd))
                {
                    System.out.println(p.exibir());
                    qtd=1;
                }
            }
            if(qtd == 0)
            {
                System.out.println("Nenhum produto foi encontrado...");
            }
        }
        public static Produto maisCaro()
        {
            Produto maisCaro = new Produto();
            maisCaro.setPreco(0.0);
            for (Produto p : listaProduto)
            {
                if (p.getPreco() >= maisCaro.getPreco())
                {
                    maisCaro = p;
                }
            }
            return maisCaro;
        }

        public static String informaMaisCaro()
        {
            String a="";
        if(listaProduto.isEmpty())
        {
            System.out.println("Não tem nenhum produto no sistema");
        }
        else
        {
            System.out.println("O(s) produto(s) mais caro(s) é(são):\n");
            for(Produto p : listaProduto)
            {
                if(p.getPreco()==maisCaro().getPreco())
                {
                    a+=p.exibir()+"\n";
                }
            }
        }
        return a;
        }

        public static String informaMaisBarato()
        {
            String a = "";
            if(listaProduto.isEmpty())
            {
                System.out.println("Não tem nenhum produto no sistema");
            }
            else {
            double x = listaProduto.get(0).getPreco();
            for(Produto p : listaProduto)
            {
                if(p.getPreco()<=x)
                {
                    x = p.getPreco();
                }
            }
                System.out.println("O(s) produto(s) mais barato(s) é(são):\n");
            for(Produto p : listaProduto)
            {
                if(p.getPreco() == x)
                {
                    a+=p.exibir()+"\n";
                }
            }
        }
        return a;
        }
        public static double media()
        {
            double m=0;
            for(Produto p:listaProduto)
            {
                m+=p.getPreco();
            }
            return m/listaProduto.size();
        }
        public static String informaMedia()
        {
            String a = "";
            if(listaProduto.isEmpty())
            {
                System.out.println("Não tem nenhum produto no sistema");
            }
            else {
            a = "A média de preços é: R$ "+String.format("%.2f",Produto.media());
        }
        return a;
        }
    public static String informaAcimaMedia()
        {
            String a = "";
            if(listaProduto.isEmpty())
            {
                System.out.println("Não tem nenhum produto no sistema");
            }
            else {
            System.out.println("Os que estão acima da média:\n");
            for(Produto p:listaProduto)
            {
                if (p.getPreco()>Produto.media())
                {
                    a+=p.exibir();
                }
            }
        }
            return a;
}
        public static void exibirProdutos()
        {
            for (Produto a:listaProduto)
            {
                System.out.println(a.exibir());
            }
        }
}