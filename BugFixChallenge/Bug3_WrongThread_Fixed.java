import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Bug 3 - FIXED: Wrong Thread (GUI updated from background thread)
 *
 * ORIGINAL BUG: A background thread called timeLabel.setText(...) directly.
 * Swing is not thread-safe — mutating component state from outside the EDT
 * causes visual glitches, race conditions, or crashes.
 *
 * FIX: Keep the while-loop running on the background thread, but wrap every
 * UI mutation inside SwingUtilities.invokeLater(...) so it executes on the EDT.
 */
public class Bug3_WrongThread_Fixed extends JFrame {

    private JLabel timeLabel;
    private JButton startButton;
    private boolean running = false;

    public Bug3_WrongThread_Fixed() {
        setTitle("Bug 3 Fix - EDT-Safe Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("Time: ");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        startButton = new JButton("Start Clock");

        startButton.addActionListener(e -> {
            running = true;
            new Thread(() -> {
                while (running) {
                    // FIX: Post UI updates onto the EDT instead of calling them here
                    final Date now = new Date();
                    SwingUtilities.invokeLater(() -> timeLabel.setText("Time: " + now));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        break;
                    }
                }
            }).start();
        });

        add(timeLabel);
        add(startButton);
        setSize(350, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug3_WrongThread_Fixed().setVisible(true);
        });
    }
}
