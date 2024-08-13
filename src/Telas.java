import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Telas {


    //----------------------------Vitrine--------------------------------------


    private static ArrayList<JPanel> JlistaProdutos = new ArrayList<>();
    private static JFrame jvitrine = new JFrame("Padoca");

    public static void InicializaVitrine()
    {
        jvitrine.setSize(800, 600);
        jvitrine.setTitle("Padoca");
        jvitrine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jvitrine.setLocationRelativeTo(null);
        jvitrine.setMinimumSize(new Dimension(650,610));
        jvitrine.setLayout(new BorderLayout());
        jvitrine.add(Telas.Svitrine(),BorderLayout.CENTER);
        jvitrine.setResizable(false);

    }
    public static JPanel produto(Produto produto)
    {
        JPanel novaPanel = new JPanel();
        JLabel novalabel1 = new JLabel();
        JLabel novalabel2 = new JLabel();
        JLabel novalabel3 = new JLabel();

        novalabel2.setFont(new Font("Serif",Font.BOLD,20));

        if(produto.getImagem().getImage()==null)
        {
            produto.setImagem(new ImageIcon("src/pao.png"));
        }

        Image imagem = produto.getImagem().getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Icon novaImagem = new ImageIcon(imagem);

        novaPanel.setPreferredSize(new Dimension(250, 300));
        novaPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;

        novaPanel.setVisible(true);

        novalabel1.setPreferredSize(new Dimension(200, 200));
        novalabel1.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel1.setIcon(novaImagem);
        c.gridx = 1;
        c.gridy = 1;
        novaPanel.add(novalabel1,c);

        novalabel2.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel2.setText(produto.getNome_produto());
        c.gridy = 2;
        novaPanel.add(novalabel2,c);


        novalabel3.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel3.setText("R$ "+String.format("%.2f",produto.getPreco()));
        c.gridy = 3;
        novaPanel.add(novalabel3,c);

        JButton botao =new JButton("Adicionar ao carrinho");
        botao.setVisible(true);
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Telas.carinho1(produto);
            }
        });
        c.gridy = 4;
        novaPanel.add(botao,c);

        return novaPanel;
    }


    public static void listaProd()
    {
        JlistaProdutos = new ArrayList<>();

        for(Produto p:Produto.getListaProduto())
        {
            JlistaProdutos.add(produto(p));
        }

    }

    public static JScrollPane Svitrine()
    {
        JPanel pvitrine = new JPanel();

        listaProd();
        pvitrine.setPreferredSize(new Dimension(800,200*Produto.getListaProduto().size()+20));
        pvitrine.setLayout(new FlowLayout());
        for (JPanel p : JlistaProdutos) {
            pvitrine.add(p);}

        JScrollPane scrollBar = new JScrollPane(pvitrine);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setVisible(true);
        return scrollBar;
    }
    public static JFrame getJvitrine() {
        return jvitrine;
    }

    public static void atulizaV()
    {
        jvitrine = new JFrame();
        Telas.InicializaVitrine();
    }






    //-----------------------------------JSOBRE--------------------------------//






    private static JFrame jsobre = new JFrame();
    private static JLabel lsobre1 = new JLabel();
    private static JLabel lsobre2 = new JLabel();
    private static JLabel lsobre3 = new JLabel();
    private static JLabel lsobre4 = new JLabel();
    private static JLabel paneltext = new JLabel();

    public static void TextoSobre()
    {
        Font fonte = new Font("Serif",Font.BOLD,25);
        lsobre1.setFont(fonte);
        lsobre2.setFont(fonte);
        lsobre3.setFont(fonte);
        lsobre4.setFont(fonte);
        lsobre1.setText("Integrantes do grupo:");
        lsobre2.setText("Luis Felipe de Freitas");
        lsobre3.setText("Samuel Bortoloti Sabadin");
        lsobre4.setText("Lucas Antunes Soares");
        paneltext.setPreferredSize(new Dimension(300,300));
        paneltext.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 1;
        c1.gridy = 1;
        paneltext.add(lsobre1,c1);
        c1.gridy = 2;
        paneltext.add(lsobre2,c1);
        c1.gridy = 3;
        paneltext.add(lsobre3,c1);
        c1.gridy = 4;
        paneltext.add(lsobre4,c1);
        paneltext.setVisible(true);
    }

    public static void InicializaJsobre()
    {

        TextoSobre();
        jsobre.setSize(500,400);
        jsobre.setResizable(false);
        jsobre.setLocationRelativeTo(null);
        jsobre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jsobre.setLayout(new FlowLayout());
        jsobre.add(paneltext,BorderLayout.CENTER);
        jsobre.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                atulizaV();
                setI(1);
                troca();
            }
        });
    }

    public static JFrame getJsobre() {
        return jsobre;
    }




    //-----------------------------------------Carrinho1----------------------------------

    public static JFrame carinho1(Produto produto1)
    {
        JPanel epainel = new JPanel();
        JLabel novalabel1 = new JLabel();
        JLabel novalabel2 = new JLabel();
        JLabel novalabel3 = new JLabel();
        JLabel novalabel4 = new JLabel();


        novalabel2.setFont(new Font("Serif",Font.BOLD,15));

        if(produto1.getImagem().getImage()==null)
        {
            produto1.setImagem(new ImageIcon("src/pao.png"));
        }

        Image imagem = produto1.getImagem().getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        Icon novaImagem = new ImageIcon(imagem);

        epainel.setPreferredSize(new Dimension(400, 300));
        epainel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;

        epainel.setVisible(true);

        novalabel1.setPreferredSize(new Dimension(150, 150));
        novalabel1.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel1.setIcon(novaImagem);
        c.gridx = 1;
        c.gridy = 1;
        epainel.add(novalabel1,c);

        novalabel2.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel2.setText(produto1.getNome_produto());
        c.gridy = 2;
        epainel.add(novalabel2,c);

        novalabel3.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel3.setText(produto1.getDescricao());
        c.gridy=3;
        epainel.add(novalabel3,c);

        novalabel4.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel4.setText(produto1.getPreco() + " R$");
        c.gridy = 4;
        epainel.add(novalabel4,c);


        JPanel dpainel = new JPanel();
        dpainel.setSize(200,100);

        JLabel label1 = new JLabel("Deseja adicionar este item ao carrinho?");
        dpainel.setLayout(new GridBagLayout());
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridy = 0;
        c3.gridx = 0;

        dpainel.add(label1,c3);

        JButton buta1 = new JButton("Adicionar ao carrinho");

        c3.insets = new Insets(50,0,0,0);
        c3.gridy=1;
        dpainel.add(buta1,c3);

        JFrame carinho = new JFrame();
        carinho.setSize(500,400);
        carinho.setVisible(true);
        carinho.setResizable(false);
        carinho.setLocationRelativeTo(null);
        carinho.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridy=0;
        c2.gridx=0;
        carinho.add(epainel,c2);
        c2.gridx=1;
        c2.insets = new Insets(0,50,0,0);
        carinho.add(dpainel,c2);

        buta1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente.getCarinho().add(produto1);
                carinho.dispose();
            }
        });


        return carinho;
    }










    //---------------------------------------Carrinho2-----------------------------------



    private static JFrame carrinho = new JFrame();
    private static ArrayList<JPanel> listaCar = new ArrayList<>();
    private static JButton bt = new JButton();

    public static double precoTotal()
    {
        double d=0;
        for(Produto p:Cliente.getCarinho())
        {
            d+=p.getPreco();
        }
        return d;
    }

    public static void Inicializabt()
    {
        bt.setSize(250,50);
        bt.setText("Comprar tudo(preço total "+Telas.precoTotal()+" R$)");

        //remover listeners existentes
        Arrays.stream(bt.getActionListeners()).forEach(al -> bt.removeActionListener(al));

        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("action performed");
                Cliente.setCarinho(new ArrayList<>());
                int confirma = JOptionPane.showConfirmDialog(null,"Confirmar compra?");
                if(confirma == JOptionPane.YES_OPTION){
                    carrinho.dispose();
                    Telas.atulizaCar();
                    carrinho.setJMenuBar(barra);
                    carrinho.setVisible(true);
                    System.out.println("Compra efetuada");
                    return;
                }
                System.out.println("Compra não efetuada");
            }
        });
    }


    public static JPanel produtoCar(Produto produto)
    {
        JPanel novaPanel = new JPanel();
        JLabel novalabel1 = new JLabel();
        JLabel novalabel2 = new JLabel();
        JLabel novalabel3 = new JLabel();

        novalabel2.setFont(new Font("Serif",Font.BOLD,20));

        if(produto.getImagem().getImage()==null)
        {
            produto.setImagem(new ImageIcon("src/pao.png"));
        }

        Image imagem = produto.getImagem().getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Icon novaImagem = new ImageIcon(imagem);

        novaPanel.setPreferredSize(new Dimension(250, 300));
        novaPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;

        novaPanel.setVisible(true);

        novalabel1.setPreferredSize(new Dimension(200, 200));
        novalabel1.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel1.setIcon(novaImagem);
        c.gridx = 1;
        c.gridy = 1;
        novaPanel.add(novalabel1,c);

        novalabel2.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel2.setText(produto.getNome_produto());
        c.gridy = 2;
        novaPanel.add(novalabel2,c);


        novalabel3.setHorizontalAlignment(SwingConstants.CENTER);
        novalabel3.setText("R$ "+String.format("%.2f",produto.getPreco()));
        c.gridy = 3;
        novaPanel.add(novalabel3,c);

        JButton botao =new JButton("Comprar");
        botao.setVisible(true);
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Telas.produtoComprar(produto);
            }
        });
        c.gridy = 4;
        novaPanel.add(botao,c);

        return novaPanel;
    }


    public static JScrollPane SCar()
    {
        JPanel PCar = new JPanel();
        PCar.setPreferredSize(new Dimension(800,200*Produto.getListaProduto().size()+20));
        PCar.setLayout(new FlowLayout());
        for (Produto p : Cliente.getCarinho()) {
            PCar.add(produtoCar(p));}

        JScrollPane scrollBar = new JScrollPane(PCar);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setVisible(true);
        return scrollBar;
    }


    public static void InicializaCarinho()
    {
        Telas.Inicializabt();
        carrinho.setSize(800, 600);
        carrinho.setTitle("Padoca");
        carrinho.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        carrinho.setLocationRelativeTo(null);
        carrinho.setMinimumSize(new Dimension(650,610));
        carrinho.setLayout(new BorderLayout());
        carrinho.add(Telas.SCar(),BorderLayout.CENTER);
        carrinho.setResizable(false);
        carrinho.add(bt,BorderLayout.PAGE_END);
        carrinho.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                atulizaV();
                setI(1);
                troca();
            }
        });
    }

    public static void atulizaCar()
    {
        carrinho = new JFrame();
        Telas.InicializaCarinho();
    }

    //--------------------------------------Carinho3--------------------------------------




    public static JFrame produtoComprar(Produto pp)
    {
        JFrame ff = new JFrame();
        JPanel p = new JPanel();
        JLabel ll = new JLabel("Comprar esse item?");
        JButton bb = new JButton("Comprar");

        ff.setVisible(true);
        ff.setSize(200,250);
        ff.setResizable(false);
        ff.setLocationRelativeTo(null);
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        p.add(ll,c);
        c.gridy = 1;
        c.insets = new Insets(50,0,0,0);
        p.add(bb,c);
        ff.add(p);

        bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente.removeCarinho(pp);
                carrinho.dispose();
                Telas.atulizaCar();
                carrinho.setJMenuBar(barra);
                carrinho.setVisible(true);
                ff.dispose();
            }
        });

        return ff;

    }







    //--------------------------------------Cadastro---------------------------------------

    private static JFrame jcadastro = new JFrame();
    private static JPanel pcadastro = new JPanel(new GridBagLayout());

    public static void inicializaCadastro()
    {
        pcadastro.setSize(200,300);
        Insets espacamento = new Insets(0,0,30,0);
        Insets semEspacamento = new Insets(0,0,0,0);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        JPasswordField text2 = new JPasswordField();
        text2.setPreferredSize(new Dimension(250,25));
        JTextField text1 = new JTextField();
        text1.setPreferredSize(new Dimension(250,25));
        JLabel label1 = new JLabel("Digite o ID de funcionário");
        JLabel label2 = new JLabel("Digite a senha de funcionário");
        JButton button = new JButton("Log-in");

        jcadastro.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e)
            {
                jcadastro.dispose();
                Telas.atulizaV();
                Telas.setI(1);
                Telas.troca();
            }
        });

        c.insets = espacamento;
        pcadastro.add(label1,c);
        c.gridy = 1;
        pcadastro.add(text1,c);
        c.gridy = 2;
        pcadastro.add(label2,c);
        c.gridy = 3;
        pcadastro.add(text2,c);
        c.gridy = 4;
        pcadastro.add(button,c);
        jcadastro.setLayout(new BorderLayout());
        jcadastro.add(pcadastro,BorderLayout.CENTER);
        jcadastro.setSize(500,500);
        jcadastro.setLocationRelativeTo(null);
        jcadastro.setResizable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean admin = false;
                for(Funcionario f : Funcionario.getListaFuncionario())
                {
                    if(String.valueOf(f.getIdFunc()).equals(text1.getText()) && String.valueOf(text2.getPassword()).equals(f.getSenhaFunc())){
                        admin=true;
                        text1.setText(null);
                        text2.setText(null);
                        break;
                    }
                }
                if(admin==false)
                    JOptionPane.showMessageDialog(null,"Log-in de funcionário incorreto!");
                else{
                    jcadastro.setVisible(false);
                    jsobre.setVisible(false);
                    jvitrine.setVisible(false);
                    carrinho.setVisible(false);
                    FormMenu f = new FormMenu();
                }

            }
        });

    }





    //---------------------------------Inicialização--------------------------------------


    private static int i=2;

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        Telas.i = i;
    }

    public static void Inicializa() {
        InicializaCarinho();
        inicializaMenus();
        Telas.inicializaCadastro();
        Telas.InicializaJsobre();
        Telas.InicializaVitrine();
        jvitrine.setJMenuBar(barra);
        jvitrine.setVisible(true);
    }
    public static void troca()
    {
        if(i==1)
        {
            jvitrine.setJMenuBar(barra);
            jvitrine.setVisible(true);
            jsobre.setVisible(false);
            jcadastro.setVisible(false);
            carrinho.setVisible(false);

        }
        if(i==2)
        {
            jsobre.setJMenuBar(barra);
            jsobre.setVisible(true);
            jvitrine.setVisible(false);
            jcadastro.setVisible(false);
            carrinho.setVisible(false);
        }
        if(i==3)
        {
            jcadastro.setJMenuBar(barra);
            jcadastro.setVisible(true);
            jvitrine.setVisible(false);
            jsobre.setVisible(false);
            carrinho.setVisible(false);
        }
        if(i==4)
        {
            carrinho.dispose();
            Telas.atulizaCar();
            carrinho.setJMenuBar(barra);
            carrinho.repaint();
            carrinho.setVisible(true);
            jvitrine.setVisible(false);
            jsobre.setVisible(false);
            jcadastro.setVisible(false);
        }
    }



    //---------------------menus-----------------------------------------
    private static JMenuBar barra = new JMenuBar();
    private static JMenu menu1 = new JMenu("Produto");
    private static JMenuItem item1 = new JMenuItem("Vitrine");
    private static JMenuItem item12 = new JMenuItem("Carrinho");
    private static JMenu menu2 = new JMenu("informações");
    private static JMenuItem item2 = new JMenuItem("Sobre nós");
    private static JMenu menu3 = new JMenu("Cadastro");
    private static JMenuItem item3 = new JMenuItem("Sou funcionario");
    public static void inicializaMenus()
    {
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Telas.setI(1);
                troca();
                Telas.getJvitrine().revalidate();
                Telas.getJvitrine().repaint();
            }
        });
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Telas.setI(3);
                troca();
                Telas.getJvitrine().revalidate();
                Telas.getJvitrine().repaint();
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Telas.setI(2);
                troca();
                Telas.getJsobre().revalidate();
                Telas.getJsobre().repaint();
            }
        });
        item12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Telas.setI(4);
                troca();
                Telas.getJsobre().revalidate();
                Telas.getJsobre().repaint();
            }
        });
        menu1.add(item1);
        menu1.add(item12);
        menu2.add(item2);
        menu3.add(item3);

        barra.add(menu1);
        barra.add(menu2);
        barra.add(menu3);
    }
}