import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class FormPesquisaProduto extends JFrame
{
    public FormPesquisaProduto()
    {
        setTitle("Pesquisar produto");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e)
            {
                FormCadastroProduto f = new FormCadastroProduto();
            }
        });



        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Info");
        JMenuItem menuItem = new JMenuItem("Pesquisa");
        menu.add(menuItem);
        menuBar.add(menu);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"(MC)Mais caro\n(MB)Mais barato\n(MP)Média de preço\n(AM)Preços acima da média");
            }
        });

        JTextField fieldPesquisa = new JTextField();
        JButton pesquisarProd = new JButton("Pesquisar Produto");

        /*fieldPesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SwingUtilities.invokeLater(pesquisarProd::doClick);
                    System.out.println("Operação concluída...");
                }
            }
        });*/

        ArrayList<String[]> produtosTabela = new ArrayList<>();
        for(Produto p : Produto.getListaProduto()){
            produtosTabela.add(new String[]{p.getNome_produto(),p.getDescricao(),"R$ "+String.format("%.2f",p.getPreco()),String.valueOf(p.getCodigo_barras()),p.getValidade()+" dias"});
        }
        JTable tabelaProdutos = new JTable(produtosTabela.toArray(new String[produtosTabela.size()][]),new String[]{"Nome","Descrição","Preço","Código de Barras","Validade"});

        JPanel gridPanel = new JPanel(new GridLayout(2,1));
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        JPanel painel2 = new JPanel(new GridLayout(4,1));
        painel2.add(new JPanel());
        painel2.add(fieldPesquisa);
        painel2.add(new JPanel());
        painel2.add(pesquisarProd);

        gridPanel.add(scrollPane);
        gridPanel.add(painel2);

        add(menuBar,BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.SOUTH);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
        add(gridPanel,BorderLayout.CENTER);

        pesquisarProd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                produtosTabela.clear();
                for(Produto p : Produto.getListaProduto()){
                    if(String.valueOf(p.getCodigo_barras()).startsWith(fieldPesquisa.getText())||p.getNome_produto().startsWith(fieldPesquisa.getText())||p.getDescricao().startsWith(fieldPesquisa.getText())){
                        produtosTabela.add(new String[]{p.getNome_produto(),p.getDescricao(),"R$ "+String.format("%.2f",p.getPreco()),String.valueOf(p.getCodigo_barras()),p.getValidade()+" dias"});
                    }
                }
                if(fieldPesquisa.getText().equals("")){
                    produtosTabela.clear();
                    JOptionPane.showMessageDialog(null,"Digite alguma coisa!");
                    for(Produto p : Produto.getListaProduto()){
                        produtosTabela.add(new String[]{p.getNome_produto(),p.getDescricao(),"R$ "+String.format("%.2f",p.getPreco()),String.valueOf(p.getCodigo_barras()),p.getValidade()+" dias"});
                    }
                    tabelaProdutos.setModel(new DefaultTableModel(produtosTabela.toArray(new String[produtosTabela.size()][]),new String[]{"Nome","Descrição","Preço","Código de Barras","Validade"}));
                }
                else if(fieldPesquisa.getText().equals("MC"))
                {
                    JOptionPane.showMessageDialog(null,Produto.informaMaisCaro());
                }
                else if(fieldPesquisa.getText().equals("MB"))
                {
                    JOptionPane.showMessageDialog(null,Produto.informaMaisBarato());
                }
                else if(fieldPesquisa.getText().equals("MP"))
                {
                    JOptionPane.showMessageDialog(null,Produto.informaMedia());
                }
                else if(fieldPesquisa.getText().equals("AM"))
                {
                    JOptionPane.showMessageDialog(null,Produto.informaAcimaMedia());
                }
                else if(produtosTabela.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Não foi encontrado na pesquisa o que você solicitou!");
                }
                else{
                    tabelaProdutos.setModel(new DefaultTableModel(produtosTabela.toArray(new String[produtosTabela.size()][]),new String[]{"Nome","Descrição","Preço","Código de Barras","Validade"}));
                }
            }
        });

        setVisible(true);
    }
}