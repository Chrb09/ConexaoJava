package controle;

import java.awt.*;
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
    
    JLabel lblCodigo, lblNome, lblData, lblTelefone, lblEmail;
    JTextField txtCodigo, txtNome,txtEmail;
    JButton primeiro, anterior, proximo, ultimo;
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
        lblTelefone = new JLabel("Telefone");
        lblEmail = new JLabel("Email:");
        
        txtCodigo = new JTextField("");
        txtNome = new JTextField("");
        txtEmail = new JTextField("");
        txtData = new JFormattedTextField(mData);
        txtTelefone = new JFormattedTextField(mTelefone);
        
        primeiro = new JButton("Primeiro");
        anterior = new JButton("Anterior");
        proximo = new JButton("Próximo");
        ultimo = new JButton("Último");
                
        tableCliente = new javax.swing.JTable();
        scrollCliente = new javax.swing.JScrollPane();
        
        lblCodigo.setBounds(50,20,50,20);
        lblNome.setBounds(50,50,50,20);
        lblEmail.setBounds(50,80,50,20);
        lblData.setBounds(50,110,50,20);
        lblTelefone.setBounds(50,140,50,20);
        txtCodigo.setBounds(120,20,50,20);
        txtNome.setBounds(120,50,150,20);
        txtEmail.setBounds(120,80,150,20);
        txtData.setBounds(120,110,70,20);
        txtTelefone.setBounds(120,140,100,20);
        tableCliente.setBounds(50, 200, 550, 199);
        scrollCliente.setBounds(50,200,550,199);
        
        primeiro.setBounds(50, 170, 100, 20);
        anterior.setBounds(150, 170, 100, 20);
        proximo.setBounds(250, 170, 100, 20);
        ultimo.setBounds(350, 170, 100, 20);
        
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
        
        tableCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableCliente.setFont(new java.awt.Font("Arial", 1, 12));
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
        new String [] {"Código", "Nome", "Data Nascimento", "Telefone", "Email"})
        {
        boolean[] canEdit = new boolean []{
        false, false, false, false, false};
        
        public boolean isCellEditable(int rowIndex, int columnIndex){
        return canEdit [columnIndex];}
        });
        scrollCliente.setViewportView(tableCliente);
        tableCliente.setAutoCreateRowSorter(true);
        
        setSize(675, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        
        con_cliente.executaSQL("select * from tbclientes order by cod");
        preencherTabela();
        posicionarRegistro();
    }   
    
    public void preencherTabela() {
        tableCliente.getColumnModel().getColumn(0).setPreferredWidth(4);
        tableCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableCliente.getColumnModel().getColumn(2).setPreferredWidth(11);
        tableCliente.getColumnModel().getColumn(3).setPreferredWidth(14);
        tableCliente.getColumnModel().getColumn(4).setPreferredWidth(100);

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
    public static void main(String args[]){
        Formulario app = new Formulario();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}