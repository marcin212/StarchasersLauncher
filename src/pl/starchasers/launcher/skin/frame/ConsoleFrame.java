package pl.starchasers.launcher.skin.frame;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleFrame {

	private JFrame consoleframe;
	private JTextArea console;
	private PrintStream out = new PrintStream(new OutputStream() {
		@Override
		public void write(int arg0) throws IOException {
			console.append(String.valueOf((char) arg0));
		}
	});

	public ConsoleFrame() {
		consoleframe = new JFrame();
		console = new JTextArea(40, 30);
	    JScrollPane sbrText = new JScrollPane(console);
	    sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		consoleframe.setSize(600, 300);
		consoleframe.add(sbrText);
		consoleframe.setVisible(true);
	}

	public PrintStream getOut() {
		return out;
	}

}
