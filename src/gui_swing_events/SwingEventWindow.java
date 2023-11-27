import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingEventWindow extends JFrame implements ActionListener {
    private JTextField txtNum = new JTextField(20);
    private JButton calculateButton = new JButton("Calculate");
    private JTextField resultTextField = new JTextField(20);
    private JRadioButton rdoSum = new JRadioButton("AutoSum", true);
    private JRadioButton rdoAvg = new JRadioButton("Average");
    private JRadioButton rdoMax = new JRadioButton("Maximum");
    private JRadioButton rdoMin = new JRadioButton("Minimum");
    private ButtonGroup rdoGroup = new ButtonGroup();

    public SwingEventWindow() {
        super("Excel Formulas Window");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Group radio buttons
        rdoGroup.add(rdoSum);
        rdoGroup.add(rdoAvg);
        rdoGroup.add(rdoMax);
        rdoGroup.add(rdoMin);

        // Adding components to the frame
        add(new JLabel("Excel Functions"));
        add(txtNum);
        add(rdoSum);
        add(rdoAvg);
        add(rdoMax);
        add(rdoMin);
        add(calculateButton);
        add(new JLabel("Result:"));
        add(resultTextField);

        // Registering listeners
        calculateButton.addActionListener(this);
        resultTextField.setEditable(false);

        // Set JFrame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Fetching user input and creating an Excel object with it
            Excel excel = new Excel(txtNum.getText());
            double result = 0;
            // Determine which radio button is selected and perform the calculation
            if (rdoSum.isSelected()) {
                result = excel.findTotal();
            } else if (rdoAvg.isSelected()) {
                result = excel.findAvg();
            } else if (rdoMax.isSelected()) {
                result = excel.findMax();
            } else if (rdoMin.isSelected()) {
                result = excel.findMin();
            }
            // Display the result in the result text field
            resultTextField.setText(String.format("%.2f", result));
        } catch (IllegalArgumentException iae) {
            resultTextField.setText(iae.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingEventWindow();
            }
        });
    }
}
