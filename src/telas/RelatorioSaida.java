/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import classes.ConexaoPostgresql;
import classes.Funcoes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Estoque
 */
public class RelatorioSaida extends javax.swing.JInternalFrame {
    
    private ConexaoPostgresql Conexao;
    private Funcoes Fun;

    /**
     * Creates new form RelatorioSaida
     */
    public RelatorioSaida() {
        initComponents();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelatorioSaida().setVisible(true);
            }
        });
    }

    public void centralizar(JDesktopPane dp) {
        Dimension ds = dp.getSize();
        Dimension dw = this.getSize();
        this.setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
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
        jtfCodigoFornecedor = new javax.swing.JFormattedTextField();
        jlbFornecedor = new javax.swing.JLabel();
        jtfNomeFornecedor = new javax.swing.JFormattedTextField();
        btnImprimir = new javax.swing.JButton();
        jdcDataDanfe = new com.toedter.calendar.JDateChooser();
        jlbDataDanfe = new javax.swing.JLabel();
        jrbFornecedor = new javax.swing.JRadioButton();
        jrbDanfe = new javax.swing.JRadioButton();
        jrbDataDanfe = new javax.swing.JRadioButton();
        jlbDanfe = new javax.swing.JLabel();
        jtfDanfe = new javax.swing.JFormattedTextField();

        setClosable(true);
        setTitle("Relatório de Saída");

        jtfCodigoFornecedor.setEnabled(false);
        jtfCodigoFornecedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCodigoFornecedorFocusLost(evt);
            }
        });
        jtfCodigoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfCodigoFornecedorKeyPressed(evt);
            }
        });

        jlbFornecedor.setText("Fornecedor (F12)");

        jtfNomeFornecedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfNomeFornecedor.setEnabled(false);

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jdcDataDanfe.setEnabled(false);

        jlbDataDanfe.setText("Data da DANFE");

        buttonGroup1.add(jrbFornecedor);
        jrbFornecedor.setSelected(true);
        jrbFornecedor.setText("Fornecedor");
        jrbFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrbFornecedorMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbDanfe);
        jrbDanfe.setText("DANFE");
        jrbDanfe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrbDanfeMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrbDataDanfe);
        jrbDataDanfe.setText("Data da DANFE");
        jrbDataDanfe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrbDataDanfeMouseClicked(evt);
            }
        });

        jlbDanfe.setText("DANFE");

        jtfDanfe.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfDanfe, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDanfe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbDataDanfe)
                    .addComponent(jdcDataDanfe, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtfCodigoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jrbDanfe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbDataDanfe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbFornecedor))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbDanfe)
                    .addComponent(jrbDataDanfe)
                    .addComponent(jrbFornecedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlbDanfe)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfDanfe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlbDataDanfe)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdcDataDanfe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbFornecedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfCodigoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfCodigoFornecedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodigoFornecedorFocusLost

        if (!(jtfCodigoFornecedor.getText().trim().equals(""))) {

            jtfNomeFornecedor.setText(NomeFornecedor(Integer.parseInt(jtfCodigoFornecedor.getText())));

        }
    }//GEN-LAST:event_jtfCodigoFornecedorFocusLost

    private void jtfCodigoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCodigoFornecedorKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){

            jtfNomeFornecedor.setText(NomeFornecedor(Integer.parseInt(jtfCodigoFornecedor.getText())));

        }

        if(evt.getKeyCode() == KeyEvent.VK_F12){

            SubPesquisaPessoa A = new SubPesquisaPessoa();
            A.setVisible(true);
            JDesktopPane JDP = this.getDesktopPane();
            JDP.add(A);
            A.centralizar(JDP);
            try{
                A.setSelected(true);
                A.SetSubRelatorioSaida(this);
                A.Validador("RelatorioSaida");
            }catch (Exception E){
                JOptionPane.showMessageDialog(null, "Erro: " + E);
            }
            A.setVisible(true);

        }
    }//GEN-LAST:event_jtfCodigoFornecedorKeyPressed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        BuscaAgenda();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jrbFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrbFornecedorMouseClicked
        Opcao();
    }//GEN-LAST:event_jrbFornecedorMouseClicked

    private void jrbDanfeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrbDanfeMouseClicked
        Opcao();
    }//GEN-LAST:event_jrbDanfeMouseClicked

    private void jrbDataDanfeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrbDataDanfeMouseClicked
        Opcao();
    }//GEN-LAST:event_jrbDataDanfeMouseClicked

    public void BuscaAgenda(){
        
        try{
            
            String SQL;          
            
            SQL = "select ts.pedido, ts.danfe, to_char(ts.data_danfe, 'DD/MM/YYYY') as datadanfe, to_char(ts.data_sistema, 'DD/MM/YYYY') as datasistema, ";
            SQL = SQL + "(tp.nome) as fornecedor, ts.obs, (tpr.nome) as produto, tsi.qtde, tsi.valor, (tsi.qtde*tsi.valor) as subtotal ";
            SQL = SQL + "from t_saida ts ";
            SQL = SQL + "inner join t_pessoa tp on tp.codigo = ts.fornecedor_cod ";
            SQL = SQL + "inner join t_saida_item tsi on tsi.saida_cod = ts.codigo ";
            SQL = SQL + "inner join t_produto tpr on tpr.codigo = tsi.produto_cod ";
            SQL = SQL + "where ts.exclusao = 0 and tsi.exclusao = 0 ";
            
            if (jrbDanfe.isSelected()){
                SQL = SQL + "and te.danfe = '" + jtfDanfe.getText() + "' ";
            }
            
            if (jrbDataDanfe.isSelected()){            
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String myDate = sdf.format(jdcDataDanfe.getDate());  
                SQL = SQL + "and te.data_danfe = '" + myDate + "' ";
            }
            
            if (jrbFornecedor.isSelected()){
                SQL = SQL + "and tp.codigo = " + jtfCodigoFornecedor.getText() + " ";
            }

            Conexao = new ConexaoPostgresql();
            Conexao.Conectar() ;
            Conexao.setSQL(SQL);
            
            System.out.println(SQL);

            JRResultSetDataSource jrRS = new JRResultSetDataSource(Conexao.Consultar());
            InputStream is = getClass().getResourceAsStream("/relatorios/RelSaida.jasper");

            JasperPrint print = JasperFillManager.fillReport(is, new HashMap(), jrRS);
            JasperViewer viewer = new JasperViewer(print, false);            

            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewer.setVisible(true);

            Conexao.Desconectar();
            
        } catch (Exception Erro) {
            JOptionPane.showMessageDialog(null, "Erro na busca: " + Erro);
        }
        
    }
    
    public String NomeFornecedor (int CodigoFabricante){
    
        String Fabricante = null;
        
        Fun = new Funcoes();
        Fabricante = Fun.NomePessoa(CodigoFabricante);
        
        return Fabricante;        
    
    }
    
    public void NomeFornecedorViaF12 (int CodigoFabricante){
        
        jtfCodigoFornecedor.setText(String.valueOf(CodigoFabricante));
        jtfNomeFornecedor.setText(NomeFornecedor(CodigoFabricante));
        
    }
    
    private void Opcao (){
        
        if (jrbDanfe.isSelected()){
            jtfDanfe.setEnabled(true);
            jdcDataDanfe.setEnabled(false);
            jtfCodigoFornecedor.setEnabled(false);
        }
        
        if (jrbDataDanfe.isSelected()){
            jtfDanfe.setEnabled(false);
            jdcDataDanfe.setEnabled(true);
            jtfCodigoFornecedor.setEnabled(false);
        }
        
        if (jrbFornecedor.isSelected()){
            jtfDanfe.setEnabled(false);
            jdcDataDanfe.setEnabled(false);            
            jtfCodigoFornecedor.setEnabled(true);
        }
        
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jdcDataDanfe;
    private javax.swing.JLabel jlbDanfe;
    private javax.swing.JLabel jlbDataDanfe;
    private javax.swing.JLabel jlbFornecedor;
    private javax.swing.JRadioButton jrbDanfe;
    private javax.swing.JRadioButton jrbDataDanfe;
    private javax.swing.JRadioButton jrbFornecedor;
    private javax.swing.JFormattedTextField jtfCodigoFornecedor;
    private javax.swing.JFormattedTextField jtfDanfe;
    private javax.swing.JFormattedTextField jtfNomeFornecedor;
    // End of variables declaration//GEN-END:variables
}