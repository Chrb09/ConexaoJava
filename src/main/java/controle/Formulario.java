package controle;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import conexao.Conexao;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class Formulario extends JFrame{
    
    Conexao con_cliente;
    
    JLabel lblCodigo, lblNome, lblData, lblTelefone, lblEmail, lblPesquisa;
    JTextField txtCodigo, txtNome,txtEmail, txtPesquisa;
    JButton primeiro, anterior, proximo, ultimo, limpar, gravar, alterar, excluir, sair, pesquisar;
    JFormattedTextField txtData, txtTelefone;
    MaskFormatter mData, mTelefone;
    
    JTable tableCliente;
    JScrollPane scrollCliente;
    
    public Formulario(){
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Conexão Java com MySql");
        Container tela = getContentPane();
        tela.setLayout(null);
        ImageIcon icone = new ImageIcon("sql.png");
        setIconImage(icone.getImage());
        setResizable(false);
        
        try{
            mData = new MaskFormatter("##/##/####");
            mTelefone = new MaskFormatter("(##)####-####");
            mData.setPlaceholderCharacter('_');
            mTelefone.setPlaceholderCharacter('_');
        }
        catch(ParseException excp){}
        
        lblCodigo = new JLabel("Código:");
        lblNome = new JLabel("Nome:");
        lblData = new JLabel("Data:");
        lblTelefone = new JLabel("Telefone:");
        lblEmail = new JLabel("Email:");
        lblPesquisa = new JLabel("Pesquisar nome:");
        
        txtCodigo = new JTextField("");
        txtNome = new JTextField("");
        txtEmail = new JTextField("");
        txtPesquisa = new JTextField("");
        txtData = new JFormattedTextField(mData);
        txtTelefone = new JFormattedTextField(mTelefone);
        
        primeiro = new JButton("Primeiro");
        anterior = new JButton("Anterior");
        proximo = new JButton("Próximo");
        ultimo = new JButton("Último");
        limpar = new JButton("Novo Registro");
        gravar = new JButton("Gravar");
        alterar = new JButton("Alterar");
        excluir = new JButton("Excluir");
        pesquisar = new JButton("Pesquisar");
        sair = new JButton("Sair");
                
        tableCliente = new javax.swing.JTable();
        scrollCliente = new javax.swing.JScrollPane();
        
        lblCodigo.setBounds(75,20,100,25);
        lblNome.setBounds(75,50,100,25);
        lblEmail.setBounds(75,80,100,25);
        lblData.setBounds(75,110,100,25);
        lblTelefone.setBounds(75,140,100,25);
        lblPesquisa.setBounds(75,475,200,25);
        txtCodigo.setBounds(225,20,200,25);
        txtNome.setBounds(225,50,200,25);
        txtEmail.setBounds(225,80,200,25);
        txtData.setBounds(225,110,200,25);
        txtTelefone.setBounds(225,140,200,25);
        txtPesquisa.setBounds(225,475,200,25);
        tableCliente.setBounds(50, 230, 550, 230);
        scrollCliente.setBounds(50,230,550,230);
        
        primeiro.setBounds(75, 190, 125, 28);
        anterior.setBounds(200, 190, 125, 28);
        proximo.setBounds(325, 190, 125, 28);
        ultimo.setBounds(450, 190, 125, 28);
        limpar.setBounds(450,20,125,28);
        gravar.setBounds(450,50,125,28);
        alterar.setBounds(450,80,125,28);
        excluir.setBounds(450,110,125,28);
        sair.setBounds(450,140,125,28);
        pesquisar.setBounds(450, 475, 125, 28);
        
        
        
        tela.add(lblCodigo);
        tela.add(lblNome);
        tela.add(lblData);
        tela.add(lblTelefone);
        tela.add(lblEmail);
        tela.add(txtCodigo);
        tela.add(txtNome);
        tela.add(txtData);
        tela.add(txtTelefone);
        tela.add(txtEmail);
        tela.add(tableCliente);
        tela.add(scrollCliente);
        tela.add(primeiro);
        tela.add(anterior);
        tela.add(proximo);
        tela.add(ultimo);
        tela.add(limpar);
        tela.add(gravar);
        tela.add(alterar);
        tela.add(excluir);
        tela.add(pesquisar);
        tela.add(sair);
        tela.add(lblPesquisa);
        tela.add(txtPesquisa);
        
        primeiro.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                con_cliente.resultset.first();
                mostrar_Dados();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro: "+erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        anterior.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                con_cliente.resultset.previous();
                mostrar_Dados();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro: "+erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        proximo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                con_cliente.resultset.next();
                mostrar_Dados();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro: "+erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        ultimo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                con_cliente.resultset.last();
                mostrar_Dados();
            }catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro: "+erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        limpar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            txtCodigo.setText("");
            txtNome.setText("");
            txtData.setText("");
            txtTelefone.setText("");
            txtEmail.setText("");
            txtCodigo.requestFocus();
        }
    });
        gravar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

        }
    });
        
        alterar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

        }
    });
        
        excluir.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

        }
    });
        
        pesquisar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

        }
    });
        
        sair.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

        }
    });
        tableCliente.setFocusable(false);
        tableCliente.setIntercellSpacing(new Dimension(0, 0));
        tableCliente.setRowHeight(25);
        tableCliente.setGridColor(new Color(240, 240, 240));
        tableCliente.setSelectionBackground(new Color(217, 235, 249));
        tableCliente.getTableHeader().setFont(new Font("", Font.BOLD, 12));
        tableCliente.setSelectionForeground(Color.BLACK);
        
        tableCliente.setModel(new javax.swing.table.DefaultTableModel(
        new Object [] [] {
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
        new String [] {"Cod", "Nome", "Data Nascimento", "Telefone", "Email"})
        {
        boolean[] canEdit = new boolean []{
        false, false, false, false, false};
        
        public boolean isCellEditable(int rowIndex, int columnIndex){
        return canEdit [columnIndex];}
        });
        scrollCliente.setViewportView(tableCliente);
        tableCliente.setAutoCreateRowSorter(true);
        
        setSize(675, 560);
        setVisible(true);
        setLocationRelativeTo(null);
        
        con_cliente.executaSQL("select * from tbclientes order by cod");
        
        preencherTabela();
        posicionarRegistro();
    }   
    
    public void preencherTabela() {
        tableCliente.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableCliente.getColumnModel().getColumn(3).setPreferredWidth(90);
        tableCliente.getColumnModel().getColumn(4).setPreferredWidth(130);

        DefaultTableModel modelo = (DefaultTableModel) tableCliente.getModel();
        modelo.setNumRows(0);

        try {
            con_cliente.resultset.beforeFirst();
            while(con_cliente.resultset.next()) {
                modelo.addRow(new Object[] {
                    con_cliente.resultset.getString("cod"),
                    con_cliente.resultset.getString("nome"),
                    con_cliente.resultset.getString("dt_nasc"),
                    con_cliente.resultset.getString("telefone"),
                    con_cliente.resultset.getString("email")
                });
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "\n Erro ao listar dados da tabela!! :\n " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void posicionarRegistro(){
        try{
            con_cliente.resultset.first();
            mostrar_Dados();
        }catch(SQLException erro){
        JOptionPane.showMessageDialog(null,"Não foi possível posicionar no primeiro registro: "+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void mostrar_Dados(){
        try{
            txtCodigo.setText(con_cliente.resultset.getString("cod"));
            txtNome.setText(con_cliente.resultset.getString("nome"));
            txtData.setText(con_cliente.resultset.getString("dt_nasc"));
            txtTelefone.setText(con_cliente.resultset.getString("telefone"));
            txtEmail.setText(con_cliente.resultset.getString("email"));
        }catch(SQLException erro){
        JOptionPane.showMessageDialog(null,"Não localizou dados: "+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void main(String args[]) throws Exception{
        
        String theme= "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        UIManager.setLookAndFeel(theme);
        Formulario app = new Formulario();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}