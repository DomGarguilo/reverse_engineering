package com.monkeylearn;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextArea;

public class Client {

	private JFrame frame, error, inputF;
	private JLabel lblEnter;
	private JButton btnClassify;
	private JPanel contentPane;
	private JFileChooser openFile;
	private File inputFile;
	private Scanner reader;
	private String inputFileString;

	/**
	 * Launch the application.
	 */
	
	static Classifier classifier = new Classifier();
	static JLabel lblCatInput = new JLabel("");
	static JLabel lblPredInput = new JLabel("");
	static JLabel lblProbInput = new JLabel("");
	static JLabel lblConfInput = new JLabel("");
	static JTextArea textArea = new JTextArea();
	
	public static void main(String[] args) throws MonkeyLearnException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
		});
		
		
	}

	/**
	 * Create the application.
	 * @throws MonkeyLearnException 
	 */
	public Client() throws MonkeyLearnException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws MonkeyLearnException 
	 */
	private void initialize() throws MonkeyLearnException {
		frame = new JFrame();
		frame.setBounds(300, 300, 720, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		error = new JFrame();
		error.setBounds(200,200,400,400);
		error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputF = new JFrame();
		inputF.setBounds(300,300,500,500);
		inputF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputFileString = "";
		
		openFile = new JFileChooser();
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Food");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(368, 49, 145, 45);
		contentPane.add(lblNewLabel);
		
		
		lblEnter = new JLabel("LLVM Classifier");
		lblEnter.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnter.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblEnter.setBounds(255, 13, 191, 52);
		frame.getContentPane().add(lblEnter);
		
		btnClassify = new JButton();
		btnClassify.setBounds(193, 392, 316, 33);
		btnClassify.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(btnClassify);
		btnClassify.setText("Classify");
		
		JLabel lblThisClassifierWill = new JLabel("This classifier will predict whether the input LLVM file contains merge sort");
		lblThisClassifierWill.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisClassifierWill.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThisClassifierWill.setBounds(39, 78, 623, 33);
		frame.getContentPane().add(lblThisClassifierWill);
		
		JLabel lblEnterTextBelow = new JLabel("Enter text below or upload a text file to classify");
		lblEnterTextBelow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterTextBelow.setBounds(12, 145, 310, 26);
		frame.getContentPane().add(lblEnterTextBelow);
		
		JButton btnUploadFile = new JButton("Upload File");
		btnUploadFile.setBounds(326, 147, 97, 25);
		frame.getContentPane().add(btnUploadFile);
		
		
		
		btnUploadFile.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
					    "TXT files", "txt");
					openFile.setFileFilter(filter);
					openFile.setCurrentDirectory(null);
					int returnVal = openFile.showOpenDialog(inputF);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
					   System.out.println("You chose to open this file: " +
							   openFile.getSelectedFile().getName());
					   inputFile = new File(openFile.getSelectedFile().getAbsolutePath());
					   try {
						reader = new Scanner(inputFile);
						
						while (reader.hasNext()) {
							
							inputFileString = inputFileString + reader.next() + " ";
							
						}
						
						//textField.setText("");
						//textField.setText(inputFileString);
						textArea.setText("");
						textArea.setText(inputFileString);
						//System.out.println(inputFileString);
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					}
			  
				
			}
			});
		
		
		
		JLabel lblPrediction = new JLabel("Prediction:");
		lblPrediction.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrediction.setBounds(154, 481, 119, 33);
		frame.getContentPane().add(lblPrediction);
		
		JLabel lblProbability = new JLabel("Probability:");
		lblProbability.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProbability.setBounds(154, 565, 119, 33);
		frame.getContentPane().add(lblProbability);
		
		JLabel lblConfidence = new JLabel("Confidence:");
		lblConfidence.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConfidence.setBounds(427, 481, 119, 33);
		frame.getContentPane().add(lblConfidence);
		
		JLabel lblCategoryId = new JLabel("Category ID:");
		lblCategoryId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategoryId.setBounds(427, 565, 119, 33);
		frame.getContentPane().add(lblCategoryId);
		lblPredInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		lblPredInput.setBounds(255, 481, 153, 25);
		frame.getContentPane().add(lblPredInput);
		lblProbInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		lblProbInput.setBounds(255, 565, 153, 25);
		frame.getContentPane().add(lblProbInput);
		
		
		lblConfInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfInput.setBounds(536, 481, 126, 25);
		frame.getContentPane().add(lblConfInput);
		lblCatInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		lblCatInput.setBounds(536, 565, 126, 25);
		frame.getContentPane().add(lblCatInput);
		
		
		textArea.setBounds(12, 184, 678, 195);
		frame.getContentPane().add(textArea);
		textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		
		btnClassify.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String[] input = {""};
				//input[0] = textField.getText().toString();
				input[0] = textArea.getText().toString();
				try {
					JSONObject json = classifier.getResult(input);
					lblCatInput.setText(Long.toString((long)json.get("category_id")));
					lblConfInput.setText(Double.toString((Double)json.get("confidence")));
					lblProbInput.setText(Double.toString((Double)json.get("probability")));
					lblPredInput.setText((String)json.get("label"));
					//lblEnter.setText();
				} catch (MonkeyLearnException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(error, "You must input something", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			  
				
			}
			});
		
	}
}
