package br.com.hachitecnologia.cloudmessage;

import java.io.IOException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class EnviaMensagem extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private static final String ID_DISPOSITIVO_GCM = "APA91bEhrI8q0asCULKQhbW1Y5UGBj60VB_iL2hfqN-Eqki_Uxvlj4IBTxcLAzXWYwP_nJDfF40mxBryt_WTuT_9isnWpNxvGkVFKgoIkEPwUr8n88xGbYIGuJgjqey2Bxx9vcPsRGfmEpzhhUIKNJSnUlayLV_y1CMqFMAU57v-wG4jGeOPX34";
	private static final String ID_DISP_GCM = "APA91bF9bScIazO5sye8wwsqDIe3nl8ksEhmIyn_fjeHbeSfEQnE7qV8BZjLHcMRTPgPj_wMYW8DQ6o-yuBfvm2u2svuKVKPYPow5Qga3KBKmbElEYGQTY1uJIHyMhomfDGp_gMsY1QgHEPj3CMXixgvZV0NTtaUBchiKvLza4RcYPMkCFReonc";
	private static final String API_KEY = "AIzaSyCdaIu19TE8qJ1aJ_DdtMLxwroW1cUqNLk";
	private static javax.swing.JButton jButton1;
	private static javax.swing.JLabel jLabel1;
	private static javax.swing.JTextField jTextField1;

	public EnviaMensagem() {
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jLabel1.setText("Mensagem");
		jButton1.setText("Enviar");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel1).addGap(18, 18, 18)
								.addComponent(jTextField1).addContainerGap())
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(154, Short.MAX_VALUE)
								.addComponent(jButton1).addGap(154, 154, 154)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(24, 24, 24)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(jButton1)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		Sender sender = new Sender(API_KEY);
		String msg = jTextField1.getText();
		Message message = new Message.Builder().collapseKey("1").timeToLive(1).delayWhileIdle(true).addData("price", msg).build();
		Result result = null;
		Result result1 = null;
		try {
			result = sender.send(message, ID_DISPOSITIVO_GCM, 1);
			result1 = sender.send(message, ID_DISP_GCM, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result != null)
			System.out.println(result.toString());
	}

	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EnviaMensagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnviaMensagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnviaMensagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnviaMensagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnviaMensagem().setVisible(true);
            }
        });
	}
}

