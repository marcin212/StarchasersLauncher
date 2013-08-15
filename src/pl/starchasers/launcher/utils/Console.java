package pl.starchasers.launcher.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Console extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static JTextArea outputField;
	public Console() {
		try {
			setTitle("Console");
			setResizable(true);
			setSize(new Dimension(700, 300));
			przyciski();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void updateTextArea(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				outputField.append(text);
			}
		});
	}
	private void przyciski() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		outputField = new JTextArea();
		JScrollPane scroll = new JScrollPane(outputField);
		JButton button = new JButton("Clear!");
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(button, BorderLayout.PAGE_END);
		add(panel);
		button.addActionListener(this);
		OutputStream out = new OutputStream() {
			@Override
			public void write(final int b) throws IOException {
				updateTextArea(String.valueOf((char) b));
			}
			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				updateTextArea(new String(b, off, len));
			}
			@Override
			public void write(byte[] b) throws IOException {
				write(b, 0, b.length);
			}
		};
		System.setOut(new PrintStream(out, true));
		System.setErr(new PrintStream(out, true));
	}
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		switch (cmd) {
		case "Clear!":
			outputField.setText("");
			break;
		};
	}
}
