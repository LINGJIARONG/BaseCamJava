package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;

import serial.test;
import ult.Database;
import ult.ReadFile;
import serial.*;

public class Vue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ----------------------------- les attributs
	// ------------------------------------------
	/**
	 * la largeur de la fenètre
	 */
	private int largeur = 1200;

	/**
	 * la hauteur de la fenètre
	 */
	private int hauteur = 800;

	/**
	 * Pour afficher les datas de la nacelle
	 */
	private JPanel dataControl;

	/**
	 * Pour afficher le diagramme des datas récupérées par la capteur gyrometre
	 */
	private JPanel dataDiagramme;

	/**
	 * Pour afficher les informations des trois axes
	 */
	JPanel[] axeInfo = new JPanel[3];
	private int begin = 0;
	/**
	 * Pour afficher les trois angles des trois axes
	 */
	protected JSlider[] angles = new JSlider[3];

	/**
	 * le modele qu'on a défini dans la classe Modele.java
	 */
	private Modele m;

	/**
	 * Pour écrire les angles des 3 axes dans la nacelle
	 */
	private JButton write;

	/**
	 * Pour calibrer des PID-POWER-NBPOLE
	 */
	private JButton calibration;

	/**
	 * Pour connecter avec la nacelle
	 */
	private JButton connect;

	/**
	 * Pour disconnecter avec la nacelle
	 */
	private JButton disconnect;

	/**
	 * Pour lancer le mode detumbling
	 */
	private JButton start;

	/**
	 * Pour arrêter le mode detumbling
	 */
	private JButton stop;

	/*
	 * les PID pour les trois axes
	 */
	private JLabel[] P = new JLabel[3];
	private JLabel[] I = new JLabel[3];
	private JLabel[] D = new JLabel[3];
	private GrapheThread thread;
	/*
	 * Les puissances et les nombres de pôles de chaque axe
	 */
	private JLabel[] puissance = new JLabel[3];
	private JLabel[] nbPole = new JLabel[3];

	private JPanel[] PID = new JPanel[3];
	private JPanel[] PP = new JPanel[3];// puissance et nbPole

	JTextField PValue[] = new JTextField[3];
	JTextField IValue[] = new JTextField[3];
	JTextField DValue[] = new JTextField[3];

	JTextField PuissanceValue[] = new JTextField[3];
	JTextField nbPoleValue[] = new JTextField[3];
	/**
	 * pour vérifier que si les buttons 'start' et 'stop' sont déjà ajoutés ou pas.
	 */
	private boolean already = false;

	// ---------------------------------- le constructeur
	// ------------------------------------------

	public Vue() {

		m = new Modele();
		setSize(largeur, hauteur);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Controlleur de La Nacelle");

		setLayout(new GridLayout());
		dataControl = new JPanel();
		dataDiagramme = new JPanel();// panel de presatation de dataDiagramme

		// les choix des modes
		String[] modeStrings = { "2.mode detumbling ", "1.control mode " };
		JComboBox<String> modeList = new JComboBox<>(modeStrings);
		modeList.setSelectedIndex(1);

		// panel pour les bouttons de commande
		JPanel button = new JPanel();
		write = new JButton("Write");
		calibration = new JButton("Calibration");
		connect = new JButton("connect");
		disconnect = new JButton("disconnect");
		start = new JButton("start");
		stop = new JButton("stop");
		button.setLayout(new GridLayout(1, 4));
		button.add(write);
		button.add(calibration);

		write.addActionListener(e -> {
			m.setAngleRoll(angles[0].getValue());
			m.setAnglePitch(angles[1].getValue());
			m.setAngleYaw(angles[2].getValue());

			String ligne = m.getAngleRoll() + "\t" + m.getAnglePitch() + "\t" + m.getAngleYaw() + "\n";
			System.out.println(ligne);
			try {
				test.consigne(m.getAngleRoll() * 2, m.getAnglePitch() * 2, m.getAngleYaw() * 2);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		// Un Panel pour mettre les buttons de façon 'up and down'
		JPanel upDown1 = new JPanel();
		upDown1.setLayout(new GridLayout(2, 1));
		upDown1.add(start);
		upDown1.add(stop);
		JPanel upDown = new JPanel();
		upDown.setLayout(new GridLayout(3, 1));
		upDown.add(modeList);
		upDown.add(connect);
		upDown.add(disconnect);
		button.add(upDown);

		Container c = getContentPane();
		c.add(dataControl);
		c.add(dataDiagramme);
		// dataControl.setBackground(new Color(0.99f,0.9f,0.5f));
		dataDiagramme.setBackground(new Color(107, 142, 35));
		dataControl.setLayout(new GridLayout(4, 1));

		// ajouter les infos dans chaque roll Panel
		for (int i = 0; i < 3; i++) {
			axeInfo[i] = new JPanel();
			axeInfo[i].setLayout(new GridLayout(3, 1));
			angles[i] = new JSlider();
			axeInfo[i].add(angles[i]);
			angles[i].setMajorTickSpacing(25);
			angles[i].setMinorTickSpacing(5);
			angles[i].setValue(0);
			angles[i].setToolTipText("slide to change the angle");
			angles[i].setMinimum(-45);
			angles[i].setMaximum(45);
			angles[i].setPaintTicks(true);
			angles[i].setPaintLabels(true);

			P[i] = new JLabel("P : ", JLabel.RIGHT);
			I[i] = new JLabel("I : ", JLabel.CENTER);
			D[i] = new JLabel("D : ", JLabel.LEFT);
			PValue[i] = new JTextField(5);
			IValue[i] = new JTextField(5);
			DValue[i] = new JTextField(5);
			PID[i] = new JPanel();
			PID[i].add(P[i]);
			PID[i].add(PValue[i]);
			PID[i].add(I[i]);
			PID[i].add(IValue[i]);
			PID[i].add(D[i]);
			PID[i].add(DValue[i]);
			axeInfo[i].add(PID[i]);
			puissance[i] = new JLabel("Puissance : ", JLabel.RIGHT);
			nbPole[i] = new JLabel("Nombre de pôles : ", JLabel.LEFT);
			PuissanceValue[i] = new JTextField(5);
			nbPoleValue[i] = new JTextField(5);
			PP[i] = new JPanel();
			PP[i].add(puissance[i]);
			PP[i].add(PuissanceValue[i]);
			PP[i].add(nbPole[i]);
			PP[i].add(nbPoleValue[i]);
			axeInfo[i].add(PP[i]);
		}

		/*
		 * Ajouter 3 axes info Panel et les buttons dans le dataControl Panel
		 */
		dataControl.add(axeInfo[0]);
		dataControl.add(axeInfo[1]);
		dataControl.add(axeInfo[2]);
		dataControl.add(button);

		Border titleRoll = BorderFactory.createTitledBorder("ROLL");
		axeInfo[0].setBorder(titleRoll);
		angles[0].setBorder(BorderFactory.createTitledBorder("Angle"));
		angles[0].setName("Roll Angle");
		angles[0].setBackground(new Color(255, 235, 205));
		angles[0].setOpaque(true);
		PID[0].setBackground(new Color(255, 235, 205));
		PP[0].setBackground(new Color(255, 235, 205));

		Border titlePitch = BorderFactory.createTitledBorder("PITCH");
		axeInfo[1].setBorder(titlePitch);
		angles[1].setBorder(BorderFactory.createTitledBorder("Angle"));
		angles[1].setName("Pitch Angle");
		angles[1].setBackground(new Color(240, 255, 240));
		PID[1].setBackground(new Color(240, 255, 240));
		PP[1].setBackground(new Color(240, 255, 240));
		angles[1].setOpaque(true);

		Border titleYaw = BorderFactory.createTitledBorder("YAW");
		axeInfo[2].setBorder(titleYaw);
		angles[2].setBorder(BorderFactory.createTitledBorder("Angle"));
		angles[2].setName("Yaw Angle");
		angles[2].setBackground(new Color(230, 230, 250));
		angles[2].setOpaque(true);
		PID[2].setBackground(new Color(230, 230, 250));
		PP[2].setBackground(new Color(230, 230, 250));

		calibration.addActionListener(e -> {
			m.setP_Roll(Integer.parseInt(this.PValue[0].getText()));
			m.setP_Pitch(Integer.parseInt(this.PValue[1].getText()));
			m.setP_Yaw(Integer.parseInt(this.PValue[2].getText()));

			m.setI_Roll(Integer.parseInt(this.IValue[0].getText()));
			m.setI_Pitch(Integer.parseInt(this.IValue[1].getText()));
			m.setI_Yaw(Integer.parseInt(this.IValue[2].getText()));

			m.setD_Roll(Integer.parseInt(this.DValue[0].getText()));
			m.setD_Pitch(Integer.parseInt(this.DValue[1].getText()));
			m.setD_Yaw(Integer.parseInt(this.DValue[2].getText()));

			m.setPowerRoll(Integer.parseInt(this.PuissanceValue[0].getText()));
			m.setPowerPitch(Integer.parseInt(this.PuissanceValue[1].getText()));
			m.setPowerYaw(Integer.parseInt(this.PuissanceValue[2].getText()));

			m.setNbPoleRoll(Integer.parseInt(this.nbPoleValue[0].getText()));
			m.setNbPolePitch(Integer.parseInt(this.nbPoleValue[1].getText()));
			m.setNbPoleYaw(Integer.parseInt(this.nbPoleValue[2].getText()));

			try {
				PidControl.controlPid(m.getP_Roll(), m.getI_Roll(), m.getD_Roll(), m.getPowerRoll(), m.getNbPoleRoll(),
						m.getP_Pitch(), m.getI_Pitch(), m.getD_Pitch(), m.getPowerPitch(), m.getNbPolePitch(),
						m.getP_Yaw(), m.getI_Yaw(), m.getD_Yaw(), m.getPowerYaw(), m.getNbPoleYaw());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		start.addActionListener(e -> {
			new ReadFile();
			for (int i = begin; i < Database.list.size() - 1; i++) {
				begin++;
				long interval = (long) (Database.list.get(i + 1).getTime() - Database.list.get(i).getTime());
				try {
					Thread.sleep(interval * 500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					test.consigneSpeed(Database.list.get(i).getVx(), Database.list.get(i).getVy(),
							Database.list.get(i).getVz());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		connect.addActionListener(e -> {
			try {
				test.connect();
				thread = new GrapheThread(dataDiagramme);
				thread.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();

			}

		});
		disconnect.addActionListener(e -> {
			try {
				thread.cut();
				test.disconnect();
				//System.exit(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		modeList.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			String cmdName = (String) cb.getSelectedItem();
			if ("2.mode detumbling ".equals(cmdName)) {
				write.setVisible(false);
				calibration.setVisible(false);
				angles[0].setEnabled(false);
				angles[1].setEnabled(false);
				angles[2].setEnabled(false);
				stop.setVisible(false);
				start.setVisible(true);
				if (!already) {
					button.add(upDown1);
					already = true;
				}

			} else {
				write.setVisible(true);
				calibration.setVisible(true);
				angles[0].setEnabled(true);
				angles[1].setEnabled(true);
				angles[2].setEnabled(true);
				stop.setVisible(false);
				start.setVisible(false);
			}
		});

	}

}
