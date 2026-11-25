import javax.swing.*;

/**
 * Bug 1 - FIXED: Blocking the EDT
 *
 * ORIGINAL BUG: Thread.sleep(5000) runs on the Event Dispatch Thread (EDT),
 * freezing the entire GUI for 5 seconds — no dragging, clicking, or repainting.
 *
 * FIX: Run the long operation in a background thread with new Thread(...).start(),
 * then dispatch the UI update back onto the EDT with SwingUtilities.invokeLater(...).
 */
public class Bug1_BlockingEDT_Fixed extends JFrame {

    private JLabel statusLabel;
    private JButton processButton;

    public Bug1_BlockingEDT_Fixed() {
        setTitle("Bug 1 Fix - Non-Blocking EDT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        statusLabel = new JLabel("Ready");
        processButton = new JButton("Process");

        processButton.addActionListener(e -> {
            statusLabel.setText("Processing...");

            // FIX: Offload the 5-second work to a background thread
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // FIX: Update the label on the EDT (not the background thread)
                SwingUtilities.invokeLater(() -> statusLabel.setText("Done!"));
            }).start();
        });

        add(statusLabel);
        add(processButton);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug1_BlockingEDT_Fixed().setVisible(true);
        });
    }
}
