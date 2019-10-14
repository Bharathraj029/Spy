package com.spy.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.openqa.selenium.WebDriver;

import com.spy.constants.Constant;
import com.spy.jqueryInject.Jquery;
import com.spy.utilities.DragDrop;
import com.spy.utilities.ElementWriter;
import com.spy.utilities.GenerateCode;
import com.spy.utilities.IndivElemProperties;
import com.spy.utilities.StartBrowser;
import com.spy.utilities.TestDataFunction;
import com.spy.utilities.Validation;

import net.miginfocom.swing.MigLayout;

public class SpyMain extends JFrame {

	public static JFrame jframe;
	static JFrame jframe2;
	JPanel panel, buttonPanel1, buttonPanel, panel2, panel3, panelUpDown;
	static WebDriver driver;
	JMenuBar bar;
	JMenu menu;
	JMenuItem item;
	JButton btn, gc, proceed, closeBtn, upBtn, downBtn, deleteBtn, deleteAllBtn;
	JTable table1;
	DefaultTableModel dtm1;
	JScrollPane pane;
	JLabel methodName, deleteIcon, loader, iconLabel, classLabel, diaLabel, descLabel;
	static JTextField mathodNameValue;
	static JTextField classValue, descField;

	File configFile;
	static JProgressBar progress;
	List<String> list1 = new ArrayList<String>();
	List<String> li = new ArrayList<String>();
	JDialog dialog;
	JComboBox<String> comboFunActClk,comboFunActSend,comboFuncActDropdwn;
	TableColumn typFunctClk,typFunctSen,typFunctdropdwn;
	
	public SpyMain() {

		// https://natgrid-mxtst04.maximo.com/maximo/webclient/login/login.jsp
		 StartBrowser st = new StartBrowser("https://test.salesforce.com/", "chrome");
		/*StartBrowser st = new StartBrowser("https://natgrid-mxtst04.maximo.com/maximo/webclient/login/login.jsp",
				"chrome");
		*/driver = st.open();

		jframe = new JFrame(Constant.WELCOME_TO_POM_CREATION);
		// jframe.setPreferredSize(new Dimension(300, 400));
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel(new MigLayout());

		bar = new JMenuBar();

		menu = new JMenu("Options");

		item = new JMenuItem("Object Spy");
		//ClassLoader classLoader = this.getClass().getClassLoader();
		//configFile = new File(classLoader.getResource("deletered.png").getFile());
		btn = new JButton("Object Spy");
		btn.setPreferredSize(new Dimension(40, 40));
		ClassLoader cldr = this.getClass().getClassLoader();
		java.net.URL imageURL = cldr.getResource("ajax-loader.gif");
		ImageIcon imageIcon = new ImageIcon(imageURL);
		iconLabel = new JLabel();
		iconLabel.setIcon(imageIcon);
		loader = new JLabel("Loading...");
		iconLabel.setVisible(false);
		loader.setVisible(false);
		gc = new JButton("Generate Code");
		gc.setPreferredSize(new Dimension(40, 40));
		panel.add(gc, "gapleft 3");
		panel.add(btn, "gapleft 3");
		panel.add(iconLabel);
		panel.add(loader);
		jframe.add(panel);
		jframe.validate();
		jframe.repaint();

		jframe.pack();
		// jframe.setLayout(null);
		jframe.setSize(400, 300);
		jframe.setVisible(true);

		gc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == gc) {

					jframe2 = new JFrame("Generate Code");
					panel2 = new JPanel(new MigLayout());
					panel3 = new JPanel(new MigLayout());
					buttonPanel1 = new JPanel(new MigLayout("", "[center]"));
					buttonPanel = new JPanel(new MigLayout("", "[center]"));
					panelUpDown = new JPanel(new MigLayout("wrap 3"));
					methodName = new JLabel("Method Name *");
					mathodNameValue = new JTextField("", 50);
					classLabel = new JLabel("Class Name *");
					classValue = new JTextField("", 15);
					descLabel = new JLabel("Description");
					descField = new JTextField("", 15);

					table1 = new JTable();
					dtm1 = new DefaultTableModel();
					int height = 3;
					int width = 10;
					table1.setIntercellSpacing(new Dimension(width, height));
					table1.setRowHeight(30);
					table1.setModel(dtm1);
					dtm1.addColumn("Field Name");
					dtm1.addColumn("By");
					dtm1.addColumn("Value");
					dtm1.addColumn("Test Data");
					dtm1.addColumn("Perform");
					dtm1.addColumn("Functions");
					
					for (Entry<String, Map<String, String>> entry : IndivElemProperties.maped.entrySet()) {

						System.out.println("Parent key:" + entry.getKey());

						Map<String, String> entry2 = entry.getValue();
						for (Entry<String, String> entry3 : entry2.entrySet()) {
							System.out.println("Chile key :" + entry3.getKey());
							
							if(entry2.get("Perform").equalsIgnoreCase("SendKeys")) {
								typFunctSen=table1.getColumnModel().getColumn(5);
								String[] senfKeyvalues= {"JavaScript","SendKey"};
								comboFunActSend=new JComboBox<String>(senfKeyvalues);
								dtm1.addRow(new Object[] { entry.getKey(), entry2.get("BY"), entry2.get("Element"),
										entry2.get("Test Data"), entry2.get("Perform"),"" });
	
								typFunctSen.setCellEditor(new DefaultCellEditor(comboFunActSend));
								//table1.setColumnSelectionAllowed(true);
								/*dtm1.fireTableDataChanged();
								
								table1.setModel(dtm1);
								panel.updateUI();
*/
							
							}else if(entry2.get("Perform").equalsIgnoreCase("Click")) {
								typFunctClk=table1.getColumnModel().getColumn(5);
								String[] clkKeyvalues= {"JavaScript","click"};
								comboFunActClk=new JComboBox<String>(clkKeyvalues);
								dtm1.addRow(new Object[] { entry.getKey(), entry2.get("BY"), entry2.get("Element"),
										entry2.get("Test Data"), entry2.get("Perform"),"" });
	
								typFunctClk.setCellEditor(new DefaultCellEditor(comboFunActClk));
	
							}else if(entry2.get("Perform").equalsIgnoreCase("Drop Down")) {
								typFunctdropdwn=table1.getColumnModel().getColumn(5);
								String[] dropDownKeyvalues= {"Text","Index","Value"};
								comboFuncActDropdwn=new JComboBox<String>(dropDownKeyvalues);
								dtm1.addRow(new Object[] { entry.getKey(), entry2.get("BY"), entry2.get("Element"),
										entry2.get("Test Data"), entry2.get("Perform"),"" });
	
								typFunctdropdwn.setCellEditor(new DefaultCellEditor(comboFuncActDropdwn));
	
							}else {
								
							}
							
							
							break;
						}

					}

					dtm1.fireTableDataChanged();
					table1.setColumnSelectionAllowed(true);
					table1.setModel(dtm1);

					proceed = new JButton("Proceed");
					closeBtn = new JButton("Close");
					deleteAllBtn = new JButton("Delete All");
					upBtn = new JButton("Up");
					downBtn = new JButton("Down");
					deleteBtn = new JButton("Delete");
					panel2.add(methodName, "gapleft 3");
					panel2.add(mathodNameValue, "wrap 2");
					panel2.add(classLabel, "gapleft 3");
					panel2.add(classValue, "wrap 2");
					panel2.add(descLabel, "gapleft 3");
					panel2.add(descField, "wrap 2");
					panel3.add(table1, "gapleft 3");
					buttonPanel.add(closeBtn, "gapleft 3");
					buttonPanel.add(proceed, "gapleft 3");
					buttonPanel.add(deleteAllBtn, "gapleft 3");
					panelUpDown.add(upBtn, "gapright 3,wrap 10");
					panelUpDown.add(downBtn, "gapright 3,wrap 10");
					panelUpDown.add(deleteBtn, "gapright 3,wrap 10");

					panel3.add(panelUpDown, "dock east");
					panel2.add(panel3, "dock south");

					panel2.add(buttonPanel, "dock south");

					pane = new JScrollPane(table1);
					Font f = new Font("Arial", Font.BOLD, 15);
					JTableHeader header = table1.getTableHeader();
					header.setFont(f);
					pane.setPreferredSize(new Dimension(770, 400));
					table1.setColumnSelectionAllowed(true);
					table1.setRowSelectionAllowed(false);
					panel3.add(pane, "gapleft 3");
					jframe2.add(panel2);
					jframe2.validate();
					jframe2.repaint();

					jframe2.pack();
					jframe2.setSize(800, 800);
					jframe2.setVisible(true);

				}

				deleteAllBtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int input = JOptionPane.showOptionDialog(null,
								"Are you sure you want to delete all the added elements ?", "Alert:Element remove",
								JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

						if (input == JOptionPane.OK_OPTION) {
							for (int i = 0; i < dtm1.getRowCount(); i++) {
								IndivElemProperties.maped.remove(dtm1.getValueAt(i, 0).toString());
								// dtm1.setRowCount(0);
								// dtm1.removeRow(i);
							}

							while (dtm1.getRowCount() > 0) {

								dtm1.removeRow(0);

							}

						} else {

						}

					}
				});

				downBtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						DragDrop.moveDownwards(table1);

					}
				});
				upBtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						DragDrop.moveUpwards(table1);

					}
				});

				deleteBtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {

						int row = table1.getSelectedRow();
						IndivElemProperties.maped.remove(dtm1.getValueAt(row, 0).toString());
						dtm1.removeRow(row);

					}
				});
				closeBtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						jframe2.dispose();

					}
				});
				proceed.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						new SwingWorker<Void, String>() {

							@Override
							protected Void doInBackground() throws Exception {
								TestDataFunction td = new TestDataFunction(dtm1, classValue, Constant.EXECEL_PATH, mathodNameValue,
										descField);
								td.start();
								boolean valChk = Validation.validateField(mathodNameValue.getText(),
										classValue.getText());
								String toWrite = "package com.temp.pages;";
								// String title = "Login";

								if (valChk == false) {

								} else {

									boolean validateSpec = Validation.validateSpecilChar(mathodNameValue.getText(),
											classValue.getText());

									if (validateSpec == true) {
										dialog = new JDialog();
										diaLabel = new JLabel("Please wait while we generate the code...");
										dialog.setLocationRelativeTo(null);
										dialog.setTitle("Please Wait...");
										dialog.add(diaLabel);
										dialog.pack();
										dialog.setVisible(true);
										dialog.setSize(new Dimension(400, 60));

										List<String> listofVar = new ArrayList<String>();
										try {
											String path = ElementWriter.createPath(driver, classValue.getText());
											GenerateCode gc = new GenerateCode();
											gc.chkImportWritePackage(path, toWrite, classValue.getText());

											for (int i = 0; i < dtm1.getRowCount(); i++) {
												// for (int j = 0; j < dtm1.getColumnCount(); j++) {
												System.out.println("Element:::"+dtm1.getValueAt(i, 1).toString());
												gc.writeElement(dtm1.getValueAt(i, 0).toString(),
														dtm1.getValueAt(i, 1).toString(),
														dtm1.getValueAt(i, 2).toString(), path);
												// }
											}

											gc.fileWritePageInit(classValue.getText(), path);
											for (int j = 0; j < dtm1.getRowCount(); j++) {

												listofVar = gc.fileWriteMethod(path, dtm1.getValueAt(j, 4).toString(),
														dtm1.getValueAt(j, 0).toString(),dtm1.getValueAt(j, 5).toString());
												System.out.println("List is :" + listofVar);
											}

											for (int k = 0; k < dtm1.getRowCount(); k++) {

												list1.add("String "
														+ dtm1.getValueAt(k, 0).toString().toLowerCase().substring(0, 1)
														+ dtm1.getValueAt(k, 0).toString()
																.substring(1,
																		dtm1.getValueAt(k, 0).toString().length() - 1)
																.replaceAll("[^a-zA-Z0-9]", "").toLowerCase());

											}
											
											while(list1.size()>0) {
												int coun=0;
												int append=0;
												StringBuilder sb2=new StringBuilder();
												
												if(list1.size()<7) {
													gc.writeMethodName(list1,listofVar, path, mathodNameValue.getText());
													list1.clear();
													break;
												}
												else {
													
												
												Iterator<String> itr=list1.iterator();
												while(itr.hasNext()) {
													
													coun++;
													List<String> li1=new ArrayList<String>();
													li1.add(itr.next());
													if(coun==7) {
														if(append==0) {
															sb2.append(mathodNameValue.getText());
															
														}else {
															++append;
															sb2.append(mathodNameValue.getText()+String.valueOf(append));
														}
														List<Object> list2 = listofVar.stream().limit(7).collect(Collectors.toList());
														gc.writeMethodName(li1, path, sb2.toString(),list2);
														sb2.setLength(0);
														list2.clear();
														li1.clear();
														
													}else {
														
														continue;
													}
													
												}
											}
													
											   
											
										}
											synchronized (td) {

												td.wait();
												dialog.setVisible(false);
												SpyMain.infoBox("File Generated Successfully.",
														"File Generation");
											}
											listofVar.clear();
											IndivElemProperties.maped.clear();
											}
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								}
								return null;

							}

							@Override
							protected void done() {

							}
						}.execute();

					}
				});

			}

		});

		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if (event.getSource() == btn) {

					iconLabel.setVisible(true);
					loader.setVisible(true);

					new SwingWorker<Void, String>() {
						@Override
						protected Void doInBackground() throws Exception {
							// Worken hard or hardly worken...
							ClassLoader cldPath = this.getClass().getClassLoader();
							File file=new File(cldPath.getResource("test.js").getFile());
							String fil="C:\\Users\\bs009\\Desktop\\personal\\Jquery\\test.js";
							Jquery.retId(fil, driver);
							jframe.setState(Frame.ICONIFIED);
							Thread.sleep(2000);
							return null;
						}

						@Override
						protected void done() {
							iconLabel.setVisible(false);
							loader.setVisible(false);
						}
					}.execute();

				}
			}
		});

	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void setFunctionDrop(String perform) {
		
		if(perform.equalsIgnoreCase("SendKeys")) {
			
			
		}else if(perform.equalsIgnoreCase("Click")) {
			
		}else if(perform.equalsIgnoreCase("Drop Down")) {
			
		}else {}
		
		
	}

	public static void main(String[] args) {

		SpyMain ap = new SpyMain();
		IndivElemProperties getFr = new IndivElemProperties(driver);
		getFr.start();

	}

}
