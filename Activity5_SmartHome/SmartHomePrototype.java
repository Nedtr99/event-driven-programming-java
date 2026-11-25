import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Activity 5 Prototype — Smart Home Controller
 * Demonstrates at least 3 interactive components, 3+ event handlers,
 * state display, and proper EDT usage via SwingUtilities.invokeLater().
 */
public class SmartHomePrototype extends JFrame {

    private JLabel statusLabel;
    private JTextArea eventLog;
    private JToggleButton lightsToggle;
    private JToggleButton securityToggle;
    private JButton doorButton;

    public SmartHomePrototype() {
        setTitle("Smart Home Controller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Status Panel (NORTH) -------------------------------------------
        statusLabel = new JLabel("System Ready");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusLabel, BorderLayout.NORTH);

        // --- Event Log (CENTER) ---------------------------------------------
        eventLog = new JTextArea(10, 30);
        eventLog.setEditable(false);
        eventLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(eventLog), BorderLayout.CENTER);

        // --- Control Panel (SOUTH) ------------------------------------------
        JPanel controlPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lights toggle (Event Handler #1)
        lightsToggle = new JToggleButton("Lights: OFF");
        lightsToggle.addActionListener(e -> {
            if (lightsToggle.isSelected()) {
                lightsToggle.setText("Lights: ON");
                logEvent("Lights turned ON");
                statusLabel.setText("Lights are on");
            } else {
                lightsToggle.setText("Lights: OFF");
                logEvent("Lights turned OFF");
                statusLabel.setText("Lights are off");
            }
        });

        // Security toggle (Event Handler #2)
        securityToggle = new JToggleButton("Security: OFF");
        securityToggle.addActionListener(e -> {
            if (securityToggle.isSelected()) {
                securityToggle.setText("Security: ARMED");
                logEvent("Security system ARMED");
                statusLabel.setText("Security armed");
            } else {
                securityToggle.setText("Security: OFF");
                logEvent("Security system DISARMED");
                statusLabel.setText("Security disarmed");
            }
        });

        // Door sensor button (Event Handler #3)
        doorButton = new JButton("Simulate Door Open");
        doorButton.addActionListener(e -> {
            logEvent("Door sensor triggered");
            if (securityToggle.isSelected()) {
                logEvent("ALARM! Intruder detected!");
                statusLabel.setText("ALARM ACTIVE!");
                getContentPane().setBackground(Color.RED);
            } else {
                logEvent("Door opened (no alarm)");
                statusLabel.setText("Door opened");
            }
        });

        controlPanel.add(lightsToggle);
        controlPanel.add(securityToggle);
        controlPanel.add(doorButton);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(450, 400);
        setLocationRelativeTo(null);
    }

    /** Appends a timestamped message to the event log and scrolls to bottom. */
    private void logEvent(String event) {
        String timestamp = new Date().toString();
        eventLog.append("[" + timestamp + "] " + event + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SmartHomePrototype().setVisible(true);
        });
    }
}
