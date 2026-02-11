/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gui_yasmeen;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author user
 */
public class registration extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(registration.class.getName());
  
   public void deleteSelectedPatient() {
    int row = jTable1.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(this, "اختاري مريض من الجدول");
        return;
    }

    int patientId = Integer.parseInt(
            jTable1.getValueAt(row, 0).toString()
    );

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "هل أنتِ متأكدة من حذف المريض؟",
            "تأكيد الحذف",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) return;

    try {
        // حذف من جدول patient
        String sql1 = "DELETE FROM patient WHERE patient_id = ?";
           Connection con = database.getConnection();
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setInt(1, patientId);
        ps1.executeUpdate();

        JOptionPane.showMessageDialog(this, "تم حذف المريض بنجاح");
        load(); // تحديث الجدول

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
    public void searchPatientByFullName() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    String fullName = txt.getText().trim();

    if (fullName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "أدخلي الاسم الكامل");
        return;
    }

    try {
        String sql = "SELECT "
                + "patient.patient_id, person.person_id, "
                + "person.fname, person.mname, person.lname, "
                + "person.gender, person.address, person.db, person.phone, "
                + "patient.doctor_fname, patient.doctor_lname, patient.registrationdate "
                + "FROM patient "
                + "INNER JOIN person ON patient.person_id = person.person_id "
                + "WHERE CONCAT(person.fname,' ',person.mname,' ',person.lname) LIKE ?";
  Connection con = database.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + fullName + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getInt("patient_id"),
                rs.getInt("person_id"),
                rs.getString("fname"),
                rs.getString("mname"),
                rs.getString("lname"),
                rs.getString("gender"),
                rs.getString("address"),
                rs.getDate("db"),
                
                rs.getString("phone"),
                rs.getString("doctor_fname"),
                rs.getString("doctor_lname"),
                rs.getDate("registrationdate")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
public void  load(){
       try {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        String sql = """
            SELECT
                patient.patient_id,
                person.person_id,
                person.fname,
                person.mname,
                person.lname,
                person.gender,
                person.address,
                person.db,
              
                person.phone,
                patient.doctor_fname,
                patient.doctor_lname,
                patient.registrationdate
            FROM patient
            INNER JOIN person
            ON patient.person_id = person.person_id
        """;

        Connection con = database.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("patient_id"),
                rs.getInt("person_id"),
                rs.getString("fname"),
                rs.getString("mname"),
                rs.getString("lname"),
                rs.getString("gender"),
                rs.getString("address"),
                rs.getDate("db"),
                
                rs.getString("phone"),
                rs.getString("doctor_fname"),
                rs.getString("doctor_lname"),
                rs.getDate("registrationdate")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}


    /**
     * Creates new form registration
     */
    public registration() {
 initComponents();

    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[] {
        "patient_id", "person_id", "fname", "mname", "lname",
        "gender", "address", "db", "phone",
        "doctor_fname", "doctor_lname", "registrationdate"
    });
    jTable1.setModel(model);

    load();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jButton8.setFont(new java.awt.Font("Perpetua", 1, 24)); // NOI18N
        jButton8.setText("generate report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);
        jButton8.setBounds(370, 740, 290, 40);

        jButton1.setBackground(new java.awt.Color(182, 227, 249));
        jButton1.setFont(new java.awt.Font("Perpetua", 0, 48)); // NOI18N
        jButton1.setText("AvilableTest");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(30, 180, 270, 70);

        jButton2.setBackground(new java.awt.Color(182, 227, 249));
        jButton2.setFont(new java.awt.Font("Perpetua", 0, 48)); // NOI18N
        jButton2.setText("Registration");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(30, 280, 270, 70);

        jButton3.setBackground(new java.awt.Color(182, 227, 249));
        jButton3.setFont(new java.awt.Font("Perpetua", 0, 48)); // NOI18N
        jButton3.setText("Delete Patient");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(30, 390, 300, 70);

        jButton4.setBackground(new java.awt.Color(182, 227, 249));
        jButton4.setFont(new java.awt.Font("Perpetua", 0, 48)); // NOI18N
        jButton4.setText("Salary");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(30, 500, 270, 70);

        jButton5.setBackground(new java.awt.Color(182, 227, 249));
        jButton5.setFont(new java.awt.Font("Perpetua", 0, 48)); // NOI18N
        jButton5.setText("Invoice");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(30, 600, 270, 70);

        jButton6.setBackground(new java.awt.Color(182, 227, 249));
        jButton6.setFont(new java.awt.Font("Perpetua", 0, 48)); // NOI18N
        jButton6.setText("Resalts");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(30, 700, 270, 70);

        jTable1.setBackground(new java.awt.Color(182, 227, 249));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(360, 120, 1130, 140);

        txt.setBackground(new java.awt.Color(182, 227, 249));
        txt.setText("Enter name");
        txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActionPerformed(evt);
            }
        });
        jPanel1.add(txt);
        txt.setBounds(1050, 290, 250, 40);

        jButton7.setBackground(new java.awt.Color(182, 227, 249));
        jButton7.setFont(new java.awt.Font("Perpetua", 1, 24)); // NOI18N
        jButton7.setText("Search ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(1310, 290, 110, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\OneDrive\\Desktop\\ATHER.jpg")); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-50, 0, 1670, 980);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        register_patient l = new register_patient();
        l.setLocation(600, 300);
       l.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    l.setVisible(true); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
          invoice l = new invoice();
           l.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        l.setLocation(600, 300);
       


    l.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        test liveView = new test();
         liveView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    liveView.setLocation(600, 300);
  


    liveView.setVisible(true);

   
   


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    

deleteSelectedPatient();
    


    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
  searchPatientByFullName();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
            salary liveView = new salary();
    liveView.setLocation(600, 400);
     liveView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    liveView.setVisible(true);

    
   
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
           result liveView = new result();
    liveView.setLocation(600, 300);
     liveView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    liveView.setVisible(true);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
       try (InputStream ins = new FileInputStream(new File("Simple_Blue.jrxml"))) {
    JasperDesign jd = JRXmlLoader.load(ins);
    JasperReport jr = JasperCompileManager.compileReport(jd);
    Connection con = database.getConnection();
    JasperPrint jp = JasperFillManager.fillReport(jr, null, con);

    JFrame f = new JFrame("employee");
    f.getContentPane().add(new JRViewer(jp));
    f.setSize(800, 600);
    f.setLocationRelativeTo(null);
    f.setVisible(true);

} catch(Exception ex) {
    ex.printStackTrace(); // <--- مهم جدًا لطباعة أي خطأ
    JOptionPane.showMessageDialog(this, "خطأ في عرض التقرير: " + ex.getMessage());
}

    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new registration().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}
