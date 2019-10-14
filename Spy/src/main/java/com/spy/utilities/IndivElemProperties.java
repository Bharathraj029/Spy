package com.spy.utilities;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.spy.jqueryInject.Jquery;
import com.spy.main.SpyMain;
import com.spy.screenOne.AddBtnFunctionality;

import net.miginfocom.swing.MigLayout;

public class IndivElemProperties extends Thread {

	protected static final int CHECK_COL = 1;
	WebDriver driver;
	JTextField fieldValue, textFieldForTestdata;
	JLabel fieldName, testData, operation, iconLabel, loader;
	JFrame jframe, jFrame2;
	JPanel panel, buttonPanel1, buttonPanel;
	JButton yesBtn;
	String url;
	JMenuBar bar;
	JMenu menu;
	JButton btn2, btn3, addRowBtn, deleteBtn, addBtn;
	JTable table;
	DefaultTableModel dtm;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	StringBuilder sb = new StringBuilder();
	String ele;
	String eleType;
	JScrollPane pane;
	String[] str;
	String[] typeOfElem;
	JComboBox<String> comboBox,types;
	List<String> listVal;
	TableColumn combo;
	StringBuilder value = new StringBuilder();
	JButton close, highlight, proceed;
	Map<String, String> mapping = new HashMap<String, String>();
	Map<String, String> map = new HashMap<String, String>();
	Map<String, String> mapAdd = new HashMap<String, String>();
	public static Map<String, Map<String, String>> maped = new HashMap<String, Map<String, String>>();
	// static Map<String, String> maped=new HashMap<String, String>();
	// String closePath = "C:\\Users\\bs009\\Desktop\\personal\\Jquery\\close.js";
	Boolean check;
	JCheckBox checkBox;
	TableColumn typCol;
	public IndivElemProperties(WebDriver driver) {

		this.driver = driver;
		// this.jFrame2=frame;

	}

	public void run() {

		while (true) {

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			String xpath = (String) js1.executeScript("return window.localStorage.getItem('items');");
			setObjectType set = new setObjectType();

			if (xpath != null) {
				String[] str = xpath.split(",");
				if (str.length > 2) {

					// if (str.length > 0) {
					System.out.println(xpath);
					addFrame();

					int countr = 1;

					for (int i = 0; i <= str.length - 1; i++) {

						System.out.println("Values are :" + str[i]);
						combo = table.getColumnModel().getColumn(1);
						String objtType = null;
						sb = sb.append(str[i]);

						if (sb.toString().startsWith("{Id:")) {

							objtType = "Id";
							value.append(sb.toString().substring(4, str[i].length() - 1));
							System.out.println("Id is : " + value.toString());
							dtm.addRow(new Object[] { objtType, value.toString(), Boolean.FALSE });
							mapping.put(objtType, value.toString());

						} else if (sb.toString().startsWith("{Name:")) {
							objtType = "Name";
							value.append(sb.toString().substring(6, str[i].length() - 1));

							System.out.println("Name is : " + value.toString());
							dtm.addRow(new Object[] { objtType, value.toString() });
							mapping.put(objtType, value.toString());
						} else if (sb.toString().startsWith("{Field:")) {

							System.out.println("Inside field name value");
							String field = sb.toString().substring(7, sb.toString().length() - 1);
							System.out.println("Field name:" + field);
							fieldValue.setText(field);
						} else if (sb.toString().startsWith("{class:")) {

							objtType = "Class Name";
							value.append(sb.toString().substring(7, str[i].length() - 1));

							System.out.println("Class Name is : " + value.toString());
							dtm.addRow(new Object[] { objtType, value.toString() });
							mapping.put(objtType, value.toString());

						}

						else {

							objtType = "Xpath" + countr;
							countr++;

							value.append(str[i].toString().substring(0, str[i].length()));
							System.out.println("Xpath is : " + value.toString());
							dtm.addRow(new Object[] { objtType, value.toString() });
							mapping.put(objtType, value.toString());
						}

						sb.setLength(0);
						value.setLength(0);

					}
					frameDesign();

					try {
						Thread.sleep(4000);

						js1.executeScript("window.localStorage.removeItem('items');");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else if (str.length == 1) {

					addFrame();
					frameDesign();
					try {
						Thread.sleep(4000);
						js1.executeScript("window.localStorage.removeItem('items');");
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}

	}

	public void addFrame() {
		jframe = new JFrame();
		jframe.setPreferredSize(new Dimension(800, 800));
		panel = new JPanel(new MigLayout());
		bar = new JMenuBar();
		highlight = new JButton("Highlight");
		table = new JTable();
		checkBox = new JCheckBox();
		dtm = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {

				return columnIndex == 2 ? Boolean.class : super.getColumnClass(columnIndex);
			}

			@Override
			public Object getValueAt(int row, int column) {
				if (super.getValueAt(row, column) == null) {
					return Boolean.FALSE;
				} else {
					return super.getValueAt(row, column);
				}

			}
		};
		int height = 3;
		int width = 10;
		table.setIntercellSpacing(new Dimension(width, height));
		table.setRowHeight(30);
		table.setModel(dtm);

		bar.add(highlight, "gapleft 3");
		bar.setPreferredSize(new Dimension(30, 30));
		jframe.add(panel);
		jframe.setJMenuBar(bar);
		jframe.pack();
		jframe.setVisible(true);
		dtm.addColumn("Object Type");
		dtm.addColumn("Object Property");
		dtm.addColumn("Choose");
		typCol=table.getColumnModel().getColumn(0);
		fieldName = new JLabel("Field Name:  ");
		fieldValue = new JTextField("", 15);
		testData = new JLabel("Test Data:");
		textFieldForTestdata = new JTextField("", 15);
		operation = new JLabel("Perform: ");
		String[] choose = { "SendKeys", "Click","Drop Down" };
		comboBox = new JComboBox<String>(choose);

	}

	public void frameDesign() {
		table.setColumnSelectionAllowed(true);
		//table.setRowSelectionAllowed(true);
		dtm.fireTableDataChanged();
		
		table.setModel(dtm);

		buttonPanel = new JPanel(new MigLayout("", "[center]"));
		buttonPanel1 = new JPanel(new MigLayout("", "[center]"));
		ClassLoader cldr = this.getClass().getClassLoader();
		java.net.URL imageURL = cldr.getResource("ajax-loader.gif");
		ImageIcon imageIcon = new ImageIcon(imageURL);
		iconLabel = new JLabel();
		iconLabel.setIcon(imageIcon);
		// imageIcon.setImageObserver(iconLabel);

		loader = new JLabel("Loading...");
		iconLabel.setVisible(false);
		loader.setVisible(false);

		close = new JButton("Close");
		addBtn = new JButton("Add");
		addRowBtn = new JButton("Add Row");
		
		deleteBtn = new JButton("Delete Row");
		buttonPanel1.add(table, "gapleft 3");
		pane = new JScrollPane(table);
		Font f = new Font("Arial", Font.BOLD, 15);
		JTableHeader header = table.getTableHeader();
		header.setFont(f);
		pane.setPreferredSize(new Dimension(770, 200));

		buttonPanel1.add(pane, "gapleft 3");
		buttonPanel.add(close, "gapleft 3");
		buttonPanel.add(addBtn, "gapleft 3");
		buttonPanel.add(addRowBtn, "gapleft 3");
		buttonPanel.add(deleteBtn, "gapleft 3");
		buttonPanel.add(iconLabel);
		buttonPanel.add(loader);
		panel.add(fieldName, "gapleft 3");
		panel.add(fieldValue, "wrap 2");

		panel.add(testData, "gapleft 3");

		panel.add(textFieldForTestdata, "wrap 2");
		panel.add(operation, "gapleft 3");
		panel.add(comboBox, "wrap 2");
		panel.add(buttonPanel, "dock south");
		panel.add(buttonPanel1, "dock south");
		panel.updateUI();

		
		deleteBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			int row=table.getSelectedRow();
			dtm.removeRow(row);
				
			}
		});
		addRowBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String[] typesValues = { "Id", "Name","Class Name","Xpath" };
				types = new JComboBox<String>(typesValues);
				
				
				//typCol.setCellEditor(new DefaultCellEditor(types));
				
				
				dtm.addRow(new Object[] { "", "", Boolean.FALSE });
				
				typCol.setCellEditor(new DefaultCellEditor(types));
				table.setColumnSelectionAllowed(true);
				dtm.fireTableDataChanged();
				
				table.setModel(dtm);
				panel.updateUI();


			}
		});
		
		close.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if (event.getSource() == close) {
					try {
						ClassLoader cldPath = this.getClass().getClassLoader();
						File file = new File(cldPath.getResource("close.js").getFile());

						Jquery.close(file.toString(), driver);
						jframe.dispose();
						SpyMain.jframe.setState(Frame.NORMAL);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("sort row button");
					jframe.dispose();
					SpyMain.jframe.setState(Frame.NORMAL);

				}
			}
		});

		highlight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int counter = 0;
				int rowNum = 0;
				if (e.getSource() == highlight) {

					for (int i = 0; i < dtm.getRowCount(); i++) {

						for (int j = 0; j < dtm.getColumnCount(); j++) {

							System.out.println("Value in checkbOx are ::" + dtm.getValueAt(i, 2));

							if (Boolean.valueOf(dtm.getValueAt(i, 2).toString()) == true) {
								counter += 1;
								// System.out.println("counter in if loop:;"+counter1);
								rowNum = i;
								break;
							} else {

								break;
							}
						}

					}
					if (counter == 0) {

						AddBtnFunctionality.infoBox("Please select one value.", "Error");

					} else if (counter > 1) {
						AddBtnFunctionality.infoBox("Please select one value.", "Error");
					}

					else {

						if (dtm.getValueAt(rowNum, 0).toString().equalsIgnoreCase("Id")) {

							WebElement elem = driver.findElement(By.id(dtm.getValueAt(rowNum, 1).toString()));
							try {
								highlight(driver, elem);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if (dtm.getValueAt(rowNum, 0).toString().equalsIgnoreCase("Name")) {

							WebElement elem = driver.findElement(By.name(dtm.getValueAt(rowNum, 1).toString()));
							try {
								highlight(driver, elem);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else if (dtm.getValueAt(rowNum, 0).toString().contains("Xpath")) {

							WebElement elem = driver.findElement(By.xpath(dtm.getValueAt(rowNum, 1).toString()));
							try {
								highlight(driver, elem);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else if (dtm.getValueAt(rowNum, 0).toString().equalsIgnoreCase("class ")) {

							WebElement elem = driver.findElement(By.className(dtm.getValueAt(rowNum, 1).toString()));
							try {
								highlight(driver, elem);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}

					}
				}
			}
		});

		addBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent eve) {

				if (eve.getSource() == addBtn) {
					iconLabel.setVisible(true);
					loader.setVisible(true);
					new SwingWorker<Void, String>() {

						@Override
						protected Void doInBackground() throws Exception {

							int counter1 = 0;
							int rowNum = 0;
							int colNum;
							for (int i = 0; i < dtm.getRowCount(); i++) {

								for (int j = 0; j < dtm.getColumnCount(); j++) {

									System.out.println("Value in checkbOx are ::" + dtm.getValueAt(i, 2));

									if (Boolean.valueOf(dtm.getValueAt(i, 2).toString()) == true) {
										counter1 += 1;
										// System.out.println("counter in if loop:;"+counter1);
										rowNum = i;
										break;
									} else {

										break;
									}

									/*
									 * mapAdd.put(dtm.getValueAt(i, j).toString(), dtm.getValueAt(i, j +
									 * 1).toString()); break;
									 */
									// break;
								}

							}
							System.out.println("Row  num:" + rowNum);

							if (counter1 == 0) {

								AddBtnFunctionality.infoBox("Please select one value.", "Error");

							} else if (counter1 > 1) {
								AddBtnFunctionality.infoBox("Please select one value.", "Error");
							}

							else {

								mapAdd.put(dtm.getValueAt(rowNum, 0).toString(), dtm.getValueAt(rowNum, 1).toString());

								Map<String, String> tree = new TreeMap<String, String>(mapAdd);

								for (Map.Entry<String, String> entry : tree.entrySet()) {
									System.out.println("Key in id:" + entry.getKey());
									System.out.println("Value of id:" + entry.getValue());

									System.out.println("Key value selected ::" + entry.getKey());
									if (entry.getKey().startsWith("Id")) {
										System.out.println("Key in id:" + entry.getKey());
										System.out.println("Value of id:" + entry.getValue());
										AddBtnFunctionality add = new AddBtnFunctionality(fieldValue.getText(),
												entry.getKey(), textFieldForTestdata.getText(),
												comboBox.getSelectedItem().toString(), entry.getValue());

										check = add.validate();
										System.out.println("Validation :" + check);
										if (check) {

											maped.putAll(add.addFunction());
											AddBtnFunctionality.infoBox("Data added to repository", "Object repo");
											tree.clear();
											mapAdd.remove(dtm.getValueAt(rowNum, 0).toString());
											// SpyMain.jframe.setState(Frame.NORMAL);
										} else {

										}

										break;

									} else if (entry.getKey().startsWith("Name")) {
										System.out.println("Key in name:" + entry.getKey());
										System.out.println("Value of name:" + entry.getValue());
										AddBtnFunctionality add = new AddBtnFunctionality(fieldValue.getText(),
												entry.getKey(), textFieldForTestdata.getText(),
												comboBox.getSelectedItem().toString(), entry.getValue());
										check = add.validate();
										if (check) {

											maped.putAll(add.addFunction());
											AddBtnFunctionality.infoBox("Data added to repository", "Object repo");
											tree.clear();
											mapAdd.remove(dtm.getValueAt(rowNum, 0).toString());
											// SpyMain.jframe.setState(Frame.NORMAL);
										} else {

										}
										break;
									} else if (entry.getKey().startsWith("Class ")) {

										System.out.println("Key in name:" + entry.getKey());
										System.out.println("Value of name:" + entry.getValue());
										AddBtnFunctionality add = new AddBtnFunctionality(fieldValue.getText(),
												entry.getKey(), textFieldForTestdata.getText(),
												comboBox.getSelectedItem().toString(), entry.getValue());
										check = add.validate();
										if (check) {

											maped.putAll(add.addFunction());
											AddBtnFunctionality.infoBox("Data added to repository", "Object repo");
											tree.clear();
											mapAdd.remove(dtm.getValueAt(rowNum, 0).toString());
											// SpyMain.jframe.setState(Frame.NORMAL);
										} else {

										}
										break;
									}

									else if (entry.getKey().startsWith("Xpath")) {
										System.out.println("Key of xpath:" + entry.getKey());
										System.out.println("Value of xpath:" + entry.getValue());
										AddBtnFunctionality add = new AddBtnFunctionality(fieldValue.getText(),
												entry.getKey(), textFieldForTestdata.getText(),
												comboBox.getSelectedItem().toString(), entry.getValue());
										// add.addFunction();
										check = add.validate();
										if (check) {

											maped.putAll(add.addFunction());
											AddBtnFunctionality.infoBox("Data added to repository", "Object repo");
											tree.clear();
											mapAdd.remove(dtm.getValueAt(rowNum, 0).toString());
											// SpyMain.jframe.setState(Frame.NORMAL);
										} else {

										}

										// tree.clear();
										break;

									}
								}
							}

							// }

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

	public void highlight(WebDriver driver, WebElement elem) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].setAttribute('style', arguments[1]);", elem,
		// "color: red; border: 3px solid red;");

		// if (elem.getAttribute("style") != null)
		for (int i = 0; i < 3; i++) {
			jse.executeScript("arguments[0].style.border='3px solid red'", elem);
			Thread.sleep(1000);
			jse.executeScript("arguments[0].style.border=''", elem);

		}
		// jse.executeScript("arguments[0].setAttribute('style', arguments[1]);", elem,
		// "");

		// jse.executeScript("arguments[0].style.border='3px solid red'", elem);

	}
}
