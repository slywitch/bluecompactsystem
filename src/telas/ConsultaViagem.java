/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import classes.ConexaoPostgresql;
import classes.Funcoes;
import classes.TextDocument;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estoque
 */
public class ConsultaViagem extends javax.swing.JInternalFrame {

    private ConexaoPostgresql Conexao;
    private ConexaoPostgresql Conexao2;
    private Funcoes Fun;    
    private TextDocument TD;
    private DefaultTableModel tableModel;
    
    String Codigo;
    
    /**
     * Creates new form ConsultaViagem
     */
    public ConsultaViagem() {
        initComponents();
        Limpar();
        Opcao();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaViagem().setVisible(true);
            }
        });
    }

    public void centralizar(JDesktopPane dp) {
        Dimension ds = dp.getSize();
        Dimension dw = this.getSize();
        this.setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }
    
    public void CarregarDados(){
        
        String SQL;
        
        SQL = "select tv.codigo, to_char(tv.data_abrir,'DD/MM/YYYY') as dataabrir, ";
        SQL = SQL + "to_char(tv.data_finalizar,'DD/MM/YYYY') as datafinalizar, ";
        SQL = SQL + "tv.destino, tv.tecnico_cod, (tp.nome) as tecnico ";
        SQL = SQL + "from t_viagem tv ";
        SQL = SQL + "inner join t_pessoa tp on tp.codigo = tv.tecnico_cod ";
        SQL = SQL + "where tv.exclusao = 0 ";
        
        if (jrbTecnico.isSelected()){
            SQL = SQL + "and tv.tecnico_cod = " + jtfCodigoTecnico.getText() + " ";
        } else if (jrbDestino.isSelected()){
            SQL = SQL + "and tv.destino like '% " + jtfDestino.getText() + "%'";
        } else if (jrbData.isSelected()){
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String myDate = sdf.format(jdcData.getDate());  
            
            SQL = SQL + "and tv.data_abrir = '" + myDate + "' ";
        }
        
        Conexao = new ConexaoPostgresql();
        Conexao.Conectar() ;
        Conexao.setSQL(SQL);

        ResultSet RS = Conexao.Consultar();
        
        try{
            while (RS.next()){
                DefaultTableModel Tabela = (DefaultTableModel) jTable2.getModel();
                Object[] CarLinha = {RS.getString("codigo"),RS.getString("tecnico"),RS.getString("destino"),RS.getString("dataabrir"),RS.getString("datafinalizar"),};
                Tabela.addRow(CarLinha);
            }
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação: " + Erro);
        }
      
        Conexao.Desconectar();
        
    }
    
    public void CarregarDadosItens(String Codigo) {
        
        String SQL2;
        
        SQL2 = "select tvi.codigo, tvi.produto_cod, (tpr.nome) as produto, tvi.qtde_liberada, tvi.qtde_devolvida ";
        SQL2 = SQL2 + "from t_viagem_item tvi ";
        SQL2 = SQL2 + "inner join t_produto tpr on tpr.codigo = tvi.produto_cod ";
        SQL2 = SQL2 + "where tvi.codigo_viagem = " + Codigo + " ";
        SQL2 = SQL2 + "and tvi.exclusao = 0 ";
        
        Conexao2 = new ConexaoPostgresql();
        Conexao2.Conectar() ;
        Conexao2.setSQL(SQL2);

        ResultSet RS2 = Conexao2.Consultar();
        
        try{
            while (RS2.next()){
                
                int Saldo = Integer.parseInt(RS2.getString("qtde_liberada")) - Integer.parseInt(RS2.getString("qtde_devolvida"));
                
                DefaultTableModel Tabela = (DefaultTableModel) jTable1.getModel();
                Object[] CarLinha = {RS2.getString("codigo"),RS2.getString("produto_cod"),RS2.getString("produto"),RS2.getString("qtde_liberada"),RS2.getString("qtde_devolvida"),Saldo};
                Tabela.addRow(CarLinha);
                
            }            
        }catch (Exception Erro){
            JOptionPane.showMessageDialog(null, "Erro na validação: " + Erro);
        }
        
        Conexao2.Desconectar();      
        
    }
    
    private void Limpar (){
        
        jtfCodigoTecnico.setText("");
        jtfNomeTecnico.setText("");
        jtfDestino.setText("");
        jdcData.setDate(new java.util.Date());
        
        DefaultTableModel tableModel =(DefaultTableModel) jTable2.getModel();  
        tableModel.setNumRows(0);

        DefaultTableModel tableModel2 =(DefaultTableModel) jTable1.getModel();  
        tableModel2.setNumRows(0);
        
    }
    
    private void AtualizaSaldo(){
        
        int linha_total = jTable1.getRowCount();
        DefaultTableModel tabela = (DefaultTableModel) jTable1.getModel();
        int contador = 1;

        while(contador <= linha_total){

            int linha = contador - 1;

            String Qtde, Qtde_D;
            int Saldo;

            Fun = new Funcoes();

            Qtde = jTable1.getValueAt(linha, 4).toString();
            Qtde_D = jTable1.getValueAt(linha, 5).toString();
            Saldo = Integer.parseInt(Qtde) - Integer.parseInt(Qtde_D);
                        
            tabela.setValueAt(String.valueOf(Saldo), linha, 6);
            
            contador++;

        }
        
    }
    
    private void Opcao (){
        
        if (jrbTecnico.isSelected()){
            jtfCodigoTecnico.setEnabled(true);
            jtfDestino.setEnabled(false);
            jdcData.setEnabled(false);
        }
        
        if (jrbDestino.isSelected()){
            jtfCodigoTecnico.setEnabled(false);
            jtfDestino.setEnabled(true);
            jdcData.setEnabled(false);
        }
        
        if (jrbData.isSelected()){
            jtfCodigoTecnico.setEnabled(false);
            jtfDestino.setEnabled(false);
            jdcData.setEnabled(true);
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jdcData = new com.toedter.calendar.JDateChooser();
        jtfCodigoTecnico = new javax.swing.JFormattedTextField();
        jtfNomeTecnico = new javax.swing.JFormattedTextField();
        jtfDestino = new javax.swing.JFormattedTextField();
        jlbData = new javax.swing.JLabel();
        jlbTecnico = new javax.swing.JLabel();
        jlbDestino = new javax.swing.JLabel();
        jrbTecnico = new javax.swing.JRadioButton();
        jrbDestino = new javax.swing.JRadioButton();
        jrbData = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jbtConsultar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Consulta Viagem");

        jdcData.setEnabled(false);

        jtfCodigoTecnico.setEnabled(false);
        jtfCodigoTecnico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCodigoTecnicoFocusLost(evt);
            }
        });
        jtfCodigoTecnico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfCodigoTecnicoKeyPressed(evt);
            }
        });

        jtfNomeTecnico.setEnabled(false);

        jtfDestino.setEnabled(false);

        jlbData.setText("Data da Viagem");

        jlbTecnico.setText("Técnico (F12)");

        jlbDestino.setText("Destino");

        buttonGroup1.add(jrbTecnico);
        jrbTecnico.setSelected(true);
        jrbTecnico.setText("Técnico");
        jrbTecnico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrbTecnicoMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbDestino);
        jrbDestino.setText("Destino");
        jrbDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrbDestinoMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbData);
        jrbData.setText("Data da Viagem");
        jrbData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrbDataMouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Código", "Produto", "Qtde", "Devolvida", "Saldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Técnico", "Destino", "Data Inicial", "Data Final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        jbtConsultar.setText("Consultar");
        jbtConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(jrbTecnico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbDestino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbData)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbTecnico)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfCodigoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfNomeTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbDestino)
                            .addComponent(jtfDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbData)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtConsultar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbTecnico)
                    .addComponent(jrbDestino)
                    .addComponent(jrbData))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtConsultar)
                            .addComponent(jdcData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbTecnico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfCodigoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfNomeTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConsultarActionPerformed

        CarregarDados();
        
    }//GEN-LAST:event_jbtConsultarActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        
        DefaultTableModel tableModel2 =(DefaultTableModel) jTable1.getModel();  
        tableModel2.setNumRows(0); 
        
        Codigo = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
        
        CarregarDadosItens(String.valueOf(Codigo));
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jtfCodigoTecnicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCodigoTecnicoKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){

            jtfNomeTecnico.setText(NomeTecnico(Integer.parseInt(jtfCodigoTecnico.getText())));

        }

        if(evt.getKeyCode() == KeyEvent.VK_F12){

            SubPesquisaPessoa A = new SubPesquisaPessoa();
            A.setVisible(true);
            JDesktopPane JDP = this.getDesktopPane();
            JDP.add(A);
            A.centralizar(JDP);
            try{
                A.setSelected(true);
                A.SetSubConsultaViagem(this);
                A.Validador("ConsultaViagem");
            }catch (Exception E){
                JOptionPane.showMessageDialog(null, "Erro: " + E);
            }
            A.setVisible(true);

        }
        
    }//GEN-LAST:event_jtfCodigoTecnicoKeyPressed

    private void jrbTecnicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrbTecnicoMouseClicked
        Opcao();
    }//GEN-LAST:event_jrbTecnicoMouseClicked

    private void jrbDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrbDestinoMouseClicked
        Opcao();
    }//GEN-LAST:event_jrbDestinoMouseClicked

    private void jrbDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrbDataMouseClicked
        Opcao();
    }//GEN-LAST:event_jrbDataMouseClicked

    private void jtfCodigoTecnicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodigoTecnicoFocusLost
        
        if (!(jtfCodigoTecnico.getText().trim().equals(""))) {

            jtfNomeTecnico.setText(NomeTecnico(Integer.parseInt(jtfCodigoTecnico.getText())));

        }
        
    }//GEN-LAST:event_jtfCodigoTecnicoFocusLost

    public String NomeTecnico (int CodigoTecnico){
    
        String Tecnico = null;
        
        Fun = new Funcoes();
        Tecnico = Fun.NomePessoa(CodigoTecnico);
        
        return Tecnico;        
    
    }
    
    public void NomeTecnicoViaF12 (int CodigoTecnico){
        
        jtfCodigoTecnico.setText(String.valueOf(CodigoTecnico));
        jtfNomeTecnico.setText(NomeTecnico(CodigoTecnico));
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton jbtConsultar;
    private com.toedter.calendar.JDateChooser jdcData;
    private javax.swing.JLabel jlbData;
    private javax.swing.JLabel jlbDestino;
    private javax.swing.JLabel jlbTecnico;
    private javax.swing.JRadioButton jrbData;
    private javax.swing.JRadioButton jrbDestino;
    private javax.swing.JRadioButton jrbTecnico;
    private javax.swing.JFormattedTextField jtfCodigoTecnico;
    private javax.swing.JFormattedTextField jtfDestino;
    private javax.swing.JFormattedTextField jtfNomeTecnico;
    // End of variables declaration//GEN-END:variables
}
