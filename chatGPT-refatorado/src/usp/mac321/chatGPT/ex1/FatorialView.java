package usp.mac321.chatGPT.ex1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FatorialView extends JFrame {
    private static final long serialVersionUID = 6265594525071196118L;
	private JTextField inputField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public FatorialView() {
    	configuraJanela();
    	criaComponentesPainel();
    	montaPainel();
        calculateButton.addActionListener(new BotaoNaEscuta());
    }

    private void configuraJanela() {
        setTitle("Cálculo de Fatorial");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void criaComponentesPainel() {
        inputField = new JTextField(10);
        calculateButton = new JButton("Calcular");
        resultLabel = new JLabel("Resultado: ");
    }
    
    private void montaPainel() {
        // Layout da interface
        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite um número:"));
        panel.add(inputField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        add(panel);   	
    }
    
    private class BotaoNaEscuta implements ActionListener{
        public BotaoNaEscuta() {
        }

        private int leNumero() {
    	    String inputText = inputField.getText();
            int n = Integer.parseInt(inputText);
            if (n < 0) {
                throw new IllegalArgumentException("O número deve ser não negativo.");
            }
    	    return n;
        }
     
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
            	int number = leNumero();
                BigDecimal result = FatorialController.calculaFatorial(number);
                resultLabel.setText("Resultado: " + result.toString());
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(FatorialView.this, 
                		"Por favor, insira um número inteiro válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(FatorialView.this, ex.getMessage(), 
                		"Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

      
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FatorialView().setVisible(true);
            }
        });
    }
}
