import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class FormPesquisaFuncionario extends JFrame
{
    public FormPesquisaFuncionario()
    {
        setTitle("Pesquisar funcionário");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                FormCadastroFuncionario f = new FormCadastroFuncionario();
            }
        });

        JTextField fieldPesquisa = new JTextField();
        JButton pesquisarFunc = new JButton("Pesquisar Funcionário");

        ArrayList<String[]> funcionariosTabela = new ArrayList<>();
        for(Funcionario f : Funcionario.getListaFuncionario()){
            funcionariosTabela.add(new String[]{f.getNome(),f.getIdade()+" anos",f.getCpfFunc(),f.getTelefoneFunc(),String.valueOf(f.getIdFunc())});
        }
        JTable tabelaFuncionarios = new JTable(funcionariosTabela.toArray(new String[funcionariosTabela.size()][]),new String[]{"Nome","Idade","CPF","Telefone","ID"});

        JPanel gridPanel = new JPanel(new GridLayout(2,1));
        JScrollPane scrollPane = new JScrollPane(tabelaFuncionarios);
        JPanel painel2 = new JPanel(new GridLayout(4,1));
        painel2.add(new JPanel());
        painel2.add(fieldPesquisa);
        painel2.add(new JPanel());
        painel2.add(pesquisarFunc);

        gridPanel.add(scrollPane);
        gridPanel.add(painel2);

        add(new JPanel(),BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.SOUTH);
        add(new JPanel(),BorderLayout.WEST);
        add(new JPanel(),BorderLayout.EAST);
        add(gridPanel,BorderLayout.CENTER);

        pesquisarFunc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                funcionariosTabela.clear();
                for(Funcionario f : Funcionario.getListaFuncionario()){
                    if(f.getNome().startsWith(fieldPesquisa.getText())){
                        funcionariosTabela.add(new String[]{f.getNome(),f.getIdade()+" anos",f.getCpfFunc(),f.getTelefoneFunc(),String.valueOf(f.getIdFunc())});
                    }
                }
                if(funcionariosTabela.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Não foi encontrado na pesquisa o que você solicitou!");
                }
                else if(fieldPesquisa.getText().equals("")){
                    funcionariosTabela.clear();
                    JOptionPane.showMessageDialog(null,"Digite alguma coisa!");
                    for(Funcionario f : Funcionario.getListaFuncionario()){
                        funcionariosTabela.add(new String[]{f.getNome(),f.getIdade()+" anos",f.getCpfFunc(),f.getTelefoneFunc(),String.valueOf(f.getIdFunc())});
                    }
                    tabelaFuncionarios.setModel(new DefaultTableModel(funcionariosTabela.toArray(new String[funcionariosTabela.size()][]),new String[]{"Nome","Idade","CPF","Telefone","ID"}));
                }
                else{
                    tabelaFuncionarios.setModel(new DefaultTableModel(funcionariosTabela.toArray(new String[funcionariosTabela.size()][]),new String[]{"Nome","Idade","CPF","Telefone","ID"}));
                }
            }
        });

        setVisible(true);
    }
}