// ============================================================
// INTERACTIVE REGISTRATION FORM
// ============================================================
// A complete registration form built programmatically (since we
// can't use NetBeans drag-and-drop here). Includes validation,
// radio buttons, checkboxes, combo box, password field, and
// confirm/clear/exit actions.
// ============================================================
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationForm extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private JCheckBox sportsCheck, musicCheck, readingCheck;
    private JComboBox<String> countryBox;
    private JTextArea commentsArea;
    private JLabel statusLabel;
    private ButtonGroup genderGroup;
    private List<JComponent> allInputComponents = new ArrayList<>();

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ---- Header ----
        JLabel titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // ---- Main form panel ----
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        // Full Name
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(25);
        allInputComponents.add(nameField);
        formPanel.add(nameField, gbc);
        row++;

        // Email
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(25);
        allInputComponents.add(emailField);
        formPanel.add(emailField, gbc);
        row++;

        // Password
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(25);
        allInputComponents.add(passwordField);
        formPanel.add(passwordField, gbc);
        row++;

        // Gender
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel();
        genderGroup = new ButtonGroup();
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        formPanel.add(genderPanel, gbc);
        row++;

        // Interests
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Interests:"), gbc);
        gbc.gridx = 1;
        JPanel interestsPanel = new JPanel();
        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");
        interestsPanel.add(sportsCheck);
        interestsPanel.add(musicCheck);
        interestsPanel.add(readingCheck);
        allInputComponents.addAll(List.of(sportsCheck, musicCheck, readingCheck));
        formPanel.add(interestsPanel, gbc);
        row++;

        // Country
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Country:"), gbc);
        gbc.gridx = 1;
        countryBox = new JComboBox<>(new String[]{"Kenya", "Tanzania", "Uganda", "Rwanda"});
        formPanel.add(countryBox, gbc);
        row++;

        // Comments
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Comments:"), gbc);
        gbc.gridx = 1;
        commentsArea = new JTextArea(3, 25);
        allInputComponents.add(commentsArea);
        JScrollPane commentScroll = new JScrollPane(commentsArea);
        formPanel.add(commentScroll, gbc);
        row++;

        add(formPanel, BorderLayout.CENTER);

        // ---- Buttons ----
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(34, 139, 34));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setFocusPainted(false);

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setFocusPainted(false);

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(200, 50, 50));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setFocusPainted(false);

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // ---- Status label ----
        statusLabel = new JLabel("Status: Ready", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(statusLabel, BorderLayout.PAGE_END);

        // ---- EVENT HANDLERS ----

        submitButton.addActionListener(e -> handleSubmit());
        clearButton.addActionListener(e -> handleClear());
        exitButton.addActionListener(e -> handleExit());

        setVisible(true);
    }

    // ---------------------------------------------------------------
    // Submit handler with validation
    // ---------------------------------------------------------------
    private void handleSubmit() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validation
        if (name.isEmpty()) {
            statusLabel.setText("Error: Please enter your name");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            statusLabel.setText("Error: Please enter a valid email");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (password.length() < 6) {
            statusLabel.setText("Error: Password must be at least 6 characters");
            statusLabel.setForeground(Color.RED);
            return;
        }

        // Gather gender
        String gender = "Not specified";
        if (maleRadio.isSelected()) gender = "Male";
        else if (femaleRadio.isSelected()) gender = "Female";
        else if (otherRadio.isSelected()) gender = "Other";

        // Gather interests
        StringBuilder interests = new StringBuilder();
        if (sportsCheck.isSelected()) interests.append("Sports ");
        if (musicCheck.isSelected()) interests.append("Music ");
        if (readingCheck.isSelected()) interests.append("Reading ");

        // Gather country
        String country = (String) countryBox.getSelectedItem();

        // Gather comments
        String comments = commentsArea.getText().trim();

        // Show confirmation
        String message = "Registration Successful!\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Interests: " + (interests.length() > 0 ? interests.toString().trim() : "None") + "\n" +
                "Country: " + country + "\n" +
                "Comments: " + (comments.isEmpty() ? "None" : comments);

        JOptionPane.showMessageDialog(this, message,
                "Registration Complete",
                JOptionPane.INFORMATION_MESSAGE);

        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new Color(0, 128, 0));
    }

    // ---------------------------------------------------------------
    // Clear handler
    // ---------------------------------------------------------------
    private void handleClear() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        countryBox.setSelectedIndex(0);
        commentsArea.setText("");
        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(Color.BLACK);
    }

    // ---------------------------------------------------------------
    // Exit handler
    // ---------------------------------------------------------------
    private void handleExit() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // ---------------------------------------------------------------
    // Entry point
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationForm::new);
    }
}
