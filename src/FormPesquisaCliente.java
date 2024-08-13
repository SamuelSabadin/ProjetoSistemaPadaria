import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class FormPesquisaCliente extends JFrame
{
    public FormPesquisaCliente()
    {
        setTitle("Pesquisar cliente");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Info");
        JMenuItem menuItem = new JMenuItem("Pesquisa");
        menu.add(menuItem);
        menuBar.add(menu);

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"(MI)Maior idade\n(MJ)Mais jovem\n(M60)Maiores de 60 anos\n(M18)Menores de 18 anos\n(MEI)Média de idades");
            }
        });

        JTextField fieldPesquisa = new JTextField();
        JButton pesquisarCliente = new JButton("Pesquisar Cliente");

        ArrayList<String[]> clientesTabela = new ArrayList<>();
        for(Cliente c : Cliente.getListaClientes()){
            clientesTabela.add(new String[]{c.getNome(),c.getIdade()+" anos",c.getCpfCliente(),c.getEmailCliente(),c.getTelefoneCliente()});
        }
        JTable tabelaClientes = new JTable(clientesTabela.toArray(new String[clientesTabela.size()][]),new String[]{"Nome","Idade","CPF","E-mail","Telefone"});

        JPanel gridPanel = new JPanel(new GridLayout(2,1));
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        JPanel painel2 = new JPanel(new GridLayout(4,1));
        painel2.add(new JPanel());
        painel2.add(fieldPesquisa);
        painel2.add(new JPanel());
        painel2.add(pesquisarCliente);

        gridPanel.add(scrollPane);
        gridPanel.add(painel2);

        add(menuBar,BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.SOUTH);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
        add(gridPanel,BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                FormCadastroCliente f = new FormCadastroCliente();
            }
        });

        pesquisarCliente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clientesTabela.clear();
                for(Cliente c : Cliente.getListaClientes()){
                    if(c.getNome().startsWith(fieldPesquisa.getText())){
                        clientesTabela.add(new String[]{c.getNome(),c.getIdade()+" anos",c.getCpfCliente(),c.getEmailCliente(),c.getTelefoneCliente()});
                    }
                }
                if(fieldPesquisa.getText().equals("")){
                    clientesTabela.clear();
                    JOptionPane.showMessageDialog(null,"Digite alguma coisa!");
                    for(Cliente c : Cliente.getListaClientes()){
                        clientesTabela.add(new String[]{c.getNome(),c.getIdade()+" anos",c.getCpfCliente(),c.getEmailCliente(),c.getTelefoneCliente()});
                    }
                    tabelaClientes.setModel(new DefaultTableModel(clientesTabela.toArray(new String[clientesTabela.size()][]),new String[]{"Nome","Idade","CPF","E-mail","Telefone"}));
                }
                else if(fieldPesquisa.getText().equals("MI")){
                    JOptionPane.showMessageDialog(null,Cliente.maiorIdade());
                }
                else if(fieldPesquisa.getText().equals("MJ")){
                    JOptionPane.showMessageDialog(null,Cliente.menorIdade());
                }
                else if(fieldPesquisa.getText().equals("M60")){
                    JOptionPane.showMessageDialog(null,"Total de clientes maiores de 60 anos: "+Cliente.clientesMaisDe60());
                }
                else if(fieldPesquisa.getText().equals("M18")){
                    JOptionPane.showMessageDialog(null,"Total de clientes menores de 18 anos: "+Cliente.clientesMenosDe18());
                }
                else if(fieldPesquisa.getText().equals("MEI")){
                    JOptionPane.showMessageDialog(null,"Média de idades: "+Cliente.mediaDeIdades());
                }
                else if(clientesTabela.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Não foi encontrado na pesquisa o que você solicitou!");
                }
                else{
                    tabelaClientes.setModel(new DefaultTableModel(clientesTabela.toArray(new String[clientesTabela.size()][]),new String[]{"Nome","Idade","CPF","E-mail","Telefone"}));
                }
            }
        });
        /*(MI)Maior idade
        (MJ)Mais jovem
        (M60)Maiores de 60 anos
        (M18)Menores de 18 anos
        (MEI)Média de idades*/
        setVisible(true);
    }
}