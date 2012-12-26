package org.hanuna.gitalk.swing_ui.progress;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import java.awt.*;

import static org.hanuna.gitalk.swing_ui.progress.ProgressModel.State;

/**
 * @author erokhins
 */
public class ProgressFrame extends JFrame {
    private final static int MAX_PROGRESS_VALUE = 1000;
    private final JProgressBar progressBar = new JProgressBar();
    private final JLabel label = new JLabel();


    public ProgressFrame(@NotNull ProgressModel progressModel, @NotNull String startMessage) {
        progressModel.setUpdater(new UpdaterImp());
        label.setText(startMessage);
        packElements();
    }

    private void packElements() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Progress");
        progressBar.setMinimum(0);
        progressBar.setMaximum(MAX_PROGRESS_VALUE);
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        panel.add(progressBar);
        panel.add(label);
        setContentPane(panel);
        setMinimumSize(new Dimension(250, 70));

        pack();
        updateLocation();
    }

    public void updateLocation() {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameDimension = this.getSize();
        int x = screenDimension.width / 2 - frameDimension.width / 2;
        int y = screenDimension.height / 2 - frameDimension.height / 2;
        setLocation(x, y);
    }

    private class UpdaterImp implements ProgressModel.Updater {
        @Override
        public void runUpdate(@NotNull final State state, @NotNull final String message, final float progress) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (state == State.HIDE) {
                        ProgressFrame.this.setVisible(false);
                        return;
                    }
                    label.setText(message);
                    if (state == State.UNREFINED_PROGRESS) {
                        progressBar.setIndeterminate(true);
                    } else {
                        progressBar.setIndeterminate(false);
                        int pr = Math.round(MAX_PROGRESS_VALUE * progress);
                        progressBar.setValue(pr);
                    }
                    ProgressFrame.this.setVisible(true);
                    ProgressFrame.this.getContentPane().repaint();
                }
            });
        }
    }

}