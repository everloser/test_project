package com.gmail.everloser12;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class UIFrame extends JFrame
	{
		public static final int TEXT_ROWS = 30;
		public static final int TEXT_COLUMNS = 50;
		private JTextArea textArea;
		private JTextField textField;
		private final String defaultText = "text to find";
		private String find = new String();
		private JComboBox<String> sortCombo;
		private JFileChooser chooser;
		private JDialog dialog;
		private JDialog dialog1;
		private Action connectAction;
		private Action grafAction;
		private Action calcAction;
		private JMenu sortMenu;
		private int opane = -1;
		private SimulatedActivity activity;
		private Timer cancelMonitor;
		private ProgressMonitor progressDialog;

		public UIFrame()
			{

				try
					{
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
						SwingUtilities.updateComponentTreeUI(UIFrame.this);
					} catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "Can not set LookAndFeel " + e.getMessage());
					}

				connectAction = new TwoImageAction("Download", "/download-icon.png", "/download-icons.png")
					{
						public void actionPerformed(ActionEvent event)
							{
								connectAction.setEnabled(false);
								textArea.setText("Please, wait for downloading and parsing...\n");
								opane = JOptionPane.showOptionDialog(UIFrame.this,
										"select the type of file to download and parse", "Download data",
										JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
										new Object[] { "XML /w.SAX", "JSON /w.simple", "JSON /w.GSON" }, "XML /w.SAX");

								activity = new SimulatedActivity();
								activity.execute();
								progressDialog = new ProgressMonitor(UIFrame.this, "Download and Parsing", null, 0, 10);
								cancelMonitor.start();
							}
					};

				chooser = new JFileChooser();

				Action exportAction = new TwoImageAction("Export", "/export-icon.png", "/export-icons.png")
					{
						public void actionPerformed(ActionEvent event)
							{
									{
										chooser.setSelectedFile(new File("export.txt"));
										if (chooser.showSaveDialog(UIFrame.this) == JFileChooser.APPROVE_OPTION)
											{
												try
													{
														FileOutputStream out = new FileOutputStream(
																chooser.getSelectedFile());
														PrintWriter out1 = new PrintWriter(out, true);
														out1.print(textArea.getText());
														out1.close();
														out.close();
														;
													} catch (Exception e)
													{
														JOptionPane.showMessageDialog(null,
																"Can not write file " + e.getMessage());
													}
											}
									}
							}
					};

				Action exitAction = new TwoImageAction("Exit", "/exit-icon.png", "/exit-icons.png")
					{
						public void actionPerformed(ActionEvent event)
							{
								System.exit(0);
							}
					};

				grafAction = new TwoImageAction("Show Graphics", "/graf-icon.png", "/graf-icons.png")
					{
						public void actionPerformed(ActionEvent event)
							{
								DrawComponent draw = new DrawComponent();
								int pane = JOptionPane.showOptionDialog(UIFrame.this, draw, null,
										JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
										new Object[] { "Print to image file", "Close" }, "Close");
								if (pane == 0)
									{
										chooser.setSelectedFile(new File("graf.gif"));
										if (chooser.showSaveDialog(UIFrame.this) == JFileChooser.APPROVE_OPTION)
											{
												try
													{
														BufferedImage image = new BufferedImage(500, 400,
																BufferedImage.TYPE_INT_ARGB);
														draw.paint(image.getGraphics());
														ImageIO.write(image, "gif", chooser.getSelectedFile());
													} catch (Exception e)
													{
														JOptionPane.showMessageDialog(null,
																"Can not write file " + e.getMessage());
													}
											}
									}

							}
					};

				grafAction.setEnabled(false);

				calcAction = new TwoImageAction("Calculate", "/calc-icon.png", "/calc-icons.png")
					{
						public void actionPerformed(ActionEvent event)
							{
								Manage.calculate();
								textArea.setText(Manage.getRoot().toString());
								calcAction.setEnabled(false);
							}
					};

				calcAction.setEnabled(false);

				Action clearAction = new TwoImageAction("Clear Data", "/clear-icon.png", "/clear-icons.png")
					{
						public void actionPerformed(ActionEvent event)
							{
								textArea.setText("");
								textField.setText(defaultText);
								Manage.setRoot(null);
								connectAction.setEnabled(true);
								grafAction.setEnabled(false);
								calcAction.setEnabled(false);
								sortMenu.setEnabled(false);
								sortCombo.setEnabled(false);
							}
					};

				Action findAction = new TwoImageAction("Search", "/find-icon.png", "/find-icons.png")
					{
						public void actionPerformed(ActionEvent event)
							{
								find = textField.getText();
								textField.setText("");
								find(find, textArea);
							}
					};

				// construct a File menu

				JMenuBar mbar = new JMenuBar();
				setJMenuBar(mbar);
				JMenu fileMenu = new JMenu("File");
				mbar.add(fileMenu);

				fileMenu.add(connectAction);
				fileMenu.add(calcAction);
				fileMenu.add(exportAction);
				fileMenu.add(grafAction);
				fileMenu.add(clearAction);

				ButtonGroup group = new ButtonGroup();

				JRadioButtonMenuItem name1 = new JRadioButtonMenuItem("by name");
				JRadioButtonMenuItem gold1 = new JRadioButtonMenuItem("by salary");
				name1.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
							{
								Manage.sortName();
								textArea.setText(Manage.getRoot().toString());
							}
					});
				gold1.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
							{
								Manage.sortSalary();
								textArea.setText(Manage.getRoot().toString());
							}
					});

				group.add(name1);
				group.add(gold1);
				sortMenu = new JMenu("Sort");
				sortMenu.add(name1);
				sortMenu.add(gold1);
				sortMenu.setEnabled(false);

				fileMenu.addSeparator();
				fileMenu.add(sortMenu);
				JMenuItem mFindItem = new JMenuItem("Find");
				mFindItem.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
							{
								find = JOptionPane.showInputDialog(UIFrame.this, "Please supply a search query",
										"Find");
								if (find != null)
									find(find, textArea);

							}
					});
				fileMenu.add(mFindItem);

				fileMenu.addSeparator();
				fileMenu.add(exitAction);
				JMenu helpMenu = new JMenu("Help");
				JMenuItem worksItem = new JMenuItem("How it works");
				worksItem.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
							{
								if (dialog == null)
									dialog = new HIWorksDialog(UIFrame.this);
								dialog.setVisible(true);
							}
					});
				JMenuItem aboutItem = new JMenuItem("About");
				aboutItem.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
							{
								if (dialog1 == null) //
									dialog1 = new AboutDialog(UIFrame.this);
								dialog1.setVisible(true);
							}
					});
				helpMenu.add(worksItem);
				helpMenu.add(aboutItem);
				mbar.add(helpMenu);

				// construct a ToolBar

				JToolBar bar = new JToolBar();
				bar.add(connectAction);
				bar.add(calcAction);
				bar.add(exportAction);
				bar.add(grafAction);

				sortCombo = new JComboBox<>();
				sortCombo.addItem("by name");
				sortCombo.addItem("by salary");
				sortCombo.setEnabled(false);
				sortCombo.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
							{
								switch (sortCombo.getItemAt(sortCombo.getSelectedIndex()))
								{
								case ("by name"):
									Manage.sortName();
									textArea.setText(Manage.getRoot().toString());
									break;
								case ("by salary"):
									Manage.sortSalary();
									textArea.setText(Manage.getRoot().toString());
									break;
								}
							}
					});

				textField = new JTextField();
				textField.setText(defaultText);
				textField.setColumns(25);
				textField.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
							{
								find = textField.getText();
								textField.setText("");
								find(find, textArea);
							}
					});

				bar.add(clearAction);
				bar.addSeparator();
				bar.add(new JLabel("Sort "));
				bar.add(sortCombo);
				bar.addSeparator();
				bar.add(textField);
				bar.add(findAction);
				bar.addSeparator();
				bar.add(exitAction);
				add(bar, BorderLayout.NORTH);

				textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
				textArea.setEditable(false);
				add(new JScrollPane(textArea), BorderLayout.CENTER);
				pack();

				cancelMonitor = new Timer(600, new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
							{
								if (progressDialog.isCanceled())
									{
										activity.cancel(true);
										textArea.setText("");
										Manage.setRoot(null);
										connectAction.setEnabled(true);
										cancelMonitor.stop();
									} else if (activity.isDone() && Manage.getRoot() != null)
									{
										progressDialog.close();
										connectAction.setEnabled(false);
										grafAction.setEnabled(true);
										calcAction.setEnabled(true);
										sortMenu.setEnabled(true);
										sortCombo.setEnabled(true);
										textArea.setText(Manage.getRoot().toString());
										cancelMonitor.stop();
									} else if (activity.isDone() && Manage.getRoot() == null)
									{
										progressDialog.close();
										cancelMonitor.stop();
										textArea.setText("");
										connectAction.setEnabled(true);
									}else
									{
										progressDialog.setProgress(activity.getProgress());
									}
							}
					});
			}

		public static void find(String find, JTextArea textArea)
			{
				String fff = textArea.getText();
				int a = 0;
				int f = 0;
				textArea.requestFocus();
				textArea.setSelectionColor(Color.MAGENTA);
				while (f != -1 && find.length() != 0)
					{
						f = fff.indexOf(find, a);
						if (f > -1)
							{
								textArea.select(f, f + find.length());
								int o = JOptionPane.showConfirmDialog(null,
										"Search for the next match with \"" + find + "\"???", "Find",
										JOptionPane.OK_CANCEL_OPTION);
								if (o == 2 || o == -1)
									f = -1;
							}
						a = f + 1;
					}
			}

		class SimulatedActivity extends SwingWorker<Void, Integer>
			{
				private int current = 0;

				/**
				 * Download, parse and constructs the simulated activity that
				 * increments a counter from 0 to a 10
				 */

				protected Void doInBackground() throws Exception
					{

						switch (opane)
						{
						case 0:
							{
								Manage.parseXML();
								break;
							}
						case 1:
							{
								Manage.parseJSONSimpl();
								break;
							}
						case 2:
							{
								Manage.parseJSONGson();
								break;
							}
						}
						if (Manage.getRoot() != null)
							{
								try
									{
										while (current < 10)
											{
												Thread.sleep(300);
												current++;
												textArea.append("................\n");
												setProgress(current);
											}
									} catch (InterruptedException e)
									{
									}
							}
						return null;
					}
			}
	}

class TwoImageAction extends AbstractAction
	{
		public TwoImageAction(String name, String licon, String sicon)
			{
				URL calc = getClass().getResource(licon);
				URL scalc = getClass().getResource(sicon);
				putValue(Action.NAME, name);
				putValue(Action.SMALL_ICON, new ImageIcon(scalc));
				putValue(Action.LARGE_ICON_KEY, new ImageIcon(calc));
				putValue(Action.SHORT_DESCRIPTION, name);
			}

		public void actionPerformed(ActionEvent event)
			{
			}
	}
