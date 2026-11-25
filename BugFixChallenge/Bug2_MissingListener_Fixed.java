import javax.swing.*;
import java.awt.*;

/**
 * Bug 2 - FIXED: Missing Listener
 *
 * ORIGINAL BUG: No ActionListener was registered on the increment button,
 * so clicking it produced no effect at all.
 *
 * FIX: Add an ActionListener that increments the counter and updates the label.
 */
public class Bug2_MissingListener_Fixed extends JFrame {

    private JLabel countLabel;
    private JButton incrementButton;
    private int count = 0;

    public Bug2_MissingListener_Fixed() {
        setTitle("Bug 2 Fix - Missing Listener Added");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        countLabel = new JLabel("Count: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 20));

        incrementButton = new JButton("Increment");

        // FIX: Register the ActionListener that was previously missing
        incrementButton.addActionListener(e -> {
            count++;
            countLabel.setText("Count: " + count);
        });

        add(countLabel);
        add(incrementButton);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug2_MissingListener_Fixed().setVisible(true);
        });
    }
}
