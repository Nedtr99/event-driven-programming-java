import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bug 4 - FIXED: Memory Leak
 *
 * ORIGINAL BUG: removeAll() removed buttons from the panel visually, but the
 * createdButtons list still held strong references to those JButton objects.
 * The garbage collector could never reclaim them, causing unbounded memory growth.
 *
 * FIX: Call createdButtons.clear() alongside removeAll() so both the UI hierarchy
 * and the Java reference are released.
 */
public class Bug4_MemoryLeak_Fixed extends JFrame {

    private List<JButton> createdButtons = new ArrayList<>();
    private JPanel buttonPanel;

    public Bug4_MemoryLeak_Fixed() {
        setTitle("Bug 4 Fix - No More Memory Leak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Button");
        addButton.addActionListener(e -> {
            JButton newButton = new JButton("Button " + createdButtons.size());
            createdButtons.add(newButton);
            buttonPanel.add(newButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        JButton removeButton = new JButton("Remove All");
        removeButton.addActionListener(e -> {
            // FIX: Remove from the UI AND clear the backing list
            buttonPanel.removeAll();
            buttonPanel.revalidate();
            buttonPanel.repaint();
            createdButtons.clear();   // <-- was missing — now releases GC roots
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(addButton);
        controlPanel.add(removeButton);

        add(buttonPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        setSize(500, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug4_MemoryLeak_Fixed().setVisible(true);
        });
    }
}
