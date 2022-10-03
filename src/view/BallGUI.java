package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import provided.utils.view.TabbedFrame;

/**
 * The GUI hosting BallWorld.
 * @param <TDropListItem1> : the type of the drop-down options.
 * @param <TDropListItem2> the second type of the drop-down options
 */
public class BallGUI<TDropListItem1, TDropListItem2> extends JFrame {

	/**
	 * The serial version ID for BallGUI.
	 */
	private static final long serialVersionUID = 6359772238177963596L;
	/**
	 * The content panel.
	 */
	private JPanel contentPane;
	/**
	 * The JPanel where balls are displayed.
	 */
	private final JPanel canvasPnl = new JPanel() {
		/**
		 * Auto-generated serial ID.
		 */
		private static final long serialVersionUID = -6952656931251224807L;

		/**
		* Overridden paintComponent method to paint a ball in the panel.
		* @param g The Graphics object to paint on.
		**/
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//Painting to adapter
			modelUpdtAdpt.update(g);
		}
	};
	/**
	 * The JPanel where the controls are.
	 */
	private final JPanel controlPnl = new JPanel();
	/**
	 * Button to clear all balls.
	 */
	private final JButton btnClear = new JButton("Clear All");
	/**
	 * The button to make a Switcher Ball appear.
	 */
	private final JButton btnMakeSwitcher = new JButton("Make Switcher");
	/**
	 * Determines the type of the Ball created from pressing btnMakeBall, and the strategy of the Switcher Balls when btnSwitcher is pressed.
	 */
	private final JComboBox<TDropListItem1> boxType1 = new JComboBox<TDropListItem1>();
	/**
	 * When btnCombine is pressed, combine boxType1 and boxType2 to make a new strategy.
	 */
	private final JComboBox<TDropListItem1> boxType2 = new JComboBox<TDropListItem1>();
	/**
	 * Switches the Switcher Balls' strategy to boxType1.
	 */
	private final JButton btnSwitcher = new JButton("Switch!");
	/**
	 * Combines boxType1 and boxType2 into a new strategy.
	 */
	private final JButton btnCombine = new JButton("Combine!");
	/**
	 * Makes a ABall with the strategy of boxType1.
	 */
	private final JButton btnMakeBall = new JButton("Make Selected Ball");
	/**
	 * When btnAdd is pressed, add txtInput as an option in boxType1 and boxType2.
	 */
	private final JTextField txtUpdateStrat = new JTextField();
	/**
	 * Adds txtInput as an option in boxType1 and boxType2.
	 */
	private final JButton btnAddUpdate = new JButton("Add to lists");
	/**
	 * The view to model control adapter.
	 */
	private IModelControlAdapter<TDropListItem1, TDropListItem2> modelCtrlAdpt = IModelControlAdapter.MAKE_NULL();
	/**
	 * The view to model update adapter.
	 */
	private IModelUpdateAdapter modelUpdtAdpt = IModelUpdateAdapter.NULL_OBJECT;
	/**
	 * A label that displays the current strategy for the switcher balls.
	 */
	private final JLabel lblSwitcherStrat = new JLabel("Default");
	/**
	 * A label that introduces the current strategy.
	 */
	private final JLabel lblSwitcherInfo = new JLabel("Current switcher strategy:");
	/**
	 * The panel containing the basic information.
	 */
	private final JPanel panelUpdateStrat = new JPanel();
	/**
	 * The panel containing the dropdown selectors.
	 */
	private final JPanel panelDropdowns = new JPanel();
	/**
	 * The panel containing the switcher controls.
	 */
	private final JPanel panelSwitcher = new JPanel();
	/**
	 * The panel containing the paint strategy controls.
	 */
	private final JPanel panelPaintStrat = new JPanel();
	/**
	 * The text field to input a new paint strategy in.
	 */
	private final JTextField txtPaintStrat = new JTextField();
	/**
	 * The button to click to add a paint strategy.
	 */
	private final JButton btnAddPaint = new JButton("Add to lists");
	/**
	 * A label for the paint strategy section.
	 */
	private final JLabel lblPaint = new JLabel("Paint Strategy");
	/**
	 * A label for the update strategy section.
	 */
	private final JLabel lblUpdate = new JLabel("Update Strategy");
	/**
	 * The panel containing the interact strategy controls.
	 */
	private final JPanel panelInteractStrat = new JPanel();
	/**
	 * A label for the interact strategy section.
	 */
	private final JLabel lblInteract = new JLabel("Interact Strategy");
	/**
	 * The text field to input a new interact strategy in.
	 */
	private final JTextField txtInteractStrat = new JTextField();
	/**
	 * The button to click to add an interact strategy.
	 */
	private final JButton btnAddInteract = new JButton("Add to lists");
	/**
	 * The panel containing the criteria strategy controls.
	 */
	private final JPanel panelCriteriaStrat = new JPanel();
	/**
	 * A label for the criteria strategy section.
	 */
	private final JLabel lblCriteria = new JLabel("Criteria Strategy");
	/**
	 * The button to click to add a criteria strategy.
	 */
	private final JButton btnAddCriteria = new JButton("Add to lists");
	/**
	 * The text field to input a new criteria strategy in,
	 */
	private final JTextField txtCriteriaStrat = new JTextField();
	/**
	 * The panel containing the ball type controls.
	 */
	private final JPanel panelBallType = new JPanel();
	/**
	 * A label for the ball type section.
	 */
	private final JLabel lblBallType = new JLabel("Ball Type");
	/**
	 * The text field to input a new ball type.
	 */
	private final JTextField txtBallType = new JTextField();
	/**
	 * The button to click to add a new ball type.
	 */
	private final JButton btnBallType = new JButton("Add New Type");
	/**
	 * A droplist containing added ball types.
	 */
	private final JComboBox<TDropListItem2> boxBallType = new JComboBox<TDropListItem2>();
	/**
	 * The panel containing the ConfigAlgo controls.
	 */
	private final JPanel panelConfigAlgo = new JPanel();
	/**
	 * The text field to input a new ConfigAlgo in.
	 */
	private final JTextField txtConfigAlgo = new JTextField();
	/**
	 * The button to click to add a ConfigAlgo.
	 */
	private final JButton btnAddConfigAlgo = new JButton("Add to lists");
	/**
	 * A label for the ConfigAlgo section.
	 */
	private final JLabel lblConfigAlgo = new JLabel("Configuration Algorithm");
	
	/**
	 * The frame that hold all the configuration tabs.
	 */
	private TabbedFrame configFrame = new TabbedFrame("Config Frame");
	
	/**
	 * Create the GUI.
	 * @param modelCtrlAdpt : the initialized control adapter.
	 * @param modelUpdtAdpt : the initialized update adapter.
	 */
	public BallGUI(IModelControlAdapter<TDropListItem1, TDropListItem2> modelCtrlAdpt, IModelUpdateAdapter modelUpdtAdpt) {
		this.modelCtrlAdpt = modelCtrlAdpt;
		this.modelUpdtAdpt = modelUpdtAdpt;
		initGUI();
	}

	/**
	 * Initializes the GUI.
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		canvasPnl.setBackground(Color.CYAN);
		canvasPnl.setToolTipText("This is where the balls will be shown.");
		contentPane.add(canvasPnl, BorderLayout.CENTER);
		
		controlPnl.setBackground(Color.LIGHT_GRAY);
		controlPnl.setToolTipText("The panel with the controls.");
		controlPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(controlPnl, BorderLayout.NORTH);
		
		panelBallType.setBackground(Color.LIGHT_GRAY);
		panelBallType.setLayout(new GridLayout(4, 1, 0, 5));
		panelBallType.setToolTipText("The basic controls for ball types strategies");
		
		panelBallType.add(lblBallType);
		lblBallType.setToolTipText("Ball type section");
		lblBallType.setHorizontalAlignment(SwingConstants.CENTER);
		lblBallType.setVerticalAlignment(SwingConstants.BOTTOM);
		
		panelBallType.add(txtBallType);
		txtBallType.setText(modelCtrlAdpt.getDefaultBallType());
		txtBallType.setColumns(10);
		txtBallType.setToolTipText("Please input a ball type");
		
		panelBallType.add(btnBallType);
		btnBallType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtBallType.getText().isBlank()) {
					TDropListItem2 type = modelCtrlAdpt.addBallType(txtBallType.getText());
					if (type != null) {
						boxBallType.addItem(type);
						boxBallType.setSelectedIndex(boxBallType.getItemCount() - 1);
					}
				}
			}
		});
		btnBallType.setToolTipText("Add the ball type to the dropdown");
		
		boxBallType.setToolTipText("Select a ball type");
		panelBallType.add(boxBallType);
		controlPnl.add(panelBallType);
		
		panelPaintStrat.setToolTipText("The basic controls for paint strategies");
		panelPaintStrat.setBackground(Color.LIGHT_GRAY);
		panelPaintStrat.setLayout(new GridLayout(3, 1, 0, 5));
		controlPnl.add(panelPaintStrat);
		
		lblPaint.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPaint.setToolTipText("Paint strategy section");
		lblPaint.setHorizontalAlignment(SwingConstants.CENTER);
		panelPaintStrat.add(lblPaint);

		txtPaintStrat.setToolTipText("Input a paint strategy");
		txtPaintStrat.setText(modelCtrlAdpt.getDefaultPaintStrategy());
		txtPaintStrat.setColumns(10);
		panelPaintStrat.add(txtPaintStrat);
		
		btnAddPaint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtPaintStrat.getText().isBlank()) {
					TDropListItem1 strat = modelCtrlAdpt.addPaintStrategy(txtPaintStrat.getText());
					if (strat != null) {
						boxType1.addItem(strat);
						boxType1.setSelectedIndex(boxType1.getItemCount() - 1);
						boxType2.addItem(strat);
					}
				}
			}
		});
		btnAddPaint.setToolTipText("Adds the inputted paint strategy to the list");
		btnAddPaint.setBackground(Color.YELLOW);
		panelPaintStrat.add(btnAddPaint);
		
		panelUpdateStrat.setToolTipText("The basic controls for update strategies");
		panelUpdateStrat.setBackground(Color.LIGHT_GRAY);
		panelUpdateStrat.setLayout(new GridLayout(3, 0, 0, 5));
		controlPnl.add(panelUpdateStrat);
		
		lblUpdate.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUpdate.setToolTipText("Update strategy section");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		panelUpdateStrat.add(lblUpdate);
		
		txtUpdateStrat.setToolTipText("Input an update strategy");
		txtUpdateStrat.setText(modelCtrlAdpt.getDefaultUpdateStrategy());
		txtUpdateStrat.setColumns(10);
		panelUpdateStrat.add(txtUpdateStrat);
		
		btnAddUpdate.setBackground(Color.YELLOW);
		btnAddUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtUpdateStrat.getText().isBlank()) {
					TDropListItem1 strat = modelCtrlAdpt.addUpdateStrategy(txtUpdateStrat.getText());
					if (strat != null) {
						boxType1.addItem(strat);
						boxType1.setSelectedIndex(boxType1.getItemCount() - 1);
						boxType2.addItem(strat);
					}
				}
			}
		});
		btnAddUpdate.setToolTipText("Adds the inputted update strategy to the list");
		panelUpdateStrat.add(btnAddUpdate);
		panelCriteriaStrat.setToolTipText("The basic controls for criteria strategies");
		panelCriteriaStrat.setBackground(Color.LIGHT_GRAY);
		
		controlPnl.add(panelCriteriaStrat);
		panelCriteriaStrat.setLayout(new GridLayout(0, 1, 0, 5));
		lblCriteria.setToolTipText("Criteria strategy section");
		lblCriteria.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCriteria.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelCriteriaStrat.add(lblCriteria);
		
		txtCriteriaStrat.setText(modelCtrlAdpt.getDefaultCriteriaStrategy());
		txtCriteriaStrat.setToolTipText("Input a criteria strategy");
		txtCriteriaStrat.setColumns(10);
		panelCriteriaStrat.add(txtCriteriaStrat);
		
		btnAddCriteria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtCriteriaStrat.getText().isBlank()) {
					TDropListItem1 strat = modelCtrlAdpt.addCriteriaStrategy(txtCriteriaStrat.getText());
					if (strat != null) {
						boxType1.addItem(strat);
						boxType1.setSelectedIndex(boxType1.getItemCount() - 1);
						boxType2.addItem(strat);
					}
				}
			}
		});
		btnAddCriteria.setToolTipText("Adds the inputted criteria strategy to the list");
		btnAddCriteria.setBackground(Color.YELLOW);
		
		panelCriteriaStrat.add(btnAddCriteria);
		
		panelInteractStrat.setToolTipText("The basic controls for interact strategies");
		panelInteractStrat.setBackground(Color.LIGHT_GRAY);
		panelInteractStrat.setLayout(new GridLayout(3, 1, 0, 5));
		controlPnl.add(panelInteractStrat);
		
		lblInteract.setToolTipText("Interact strategy section");
		lblInteract.setVerticalAlignment(SwingConstants.BOTTOM);
		lblInteract.setHorizontalAlignment(SwingConstants.CENTER);
		panelInteractStrat.add(lblInteract);
		
		txtInteractStrat.setToolTipText("Input an interact strategy");
		txtInteractStrat.setText(modelCtrlAdpt.getDefaultInteractStrategy());
		txtInteractStrat.setColumns(10);
		panelInteractStrat.add(txtInteractStrat);
		
		btnAddInteract.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtInteractStrat.getText().isBlank()) {
					TDropListItem1 strat = modelCtrlAdpt.addInteractStrategy(txtInteractStrat.getText());
					if (strat != null) {
						boxType1.addItem(strat);
						boxType1.setSelectedIndex(boxType1.getItemCount() - 1);
						boxType2.addItem(strat);
					}
				}
			}
		});
		btnAddInteract.setToolTipText("Adds the inputted interact strategy to the list");
		btnAddInteract.setBackground(Color.YELLOW);
		panelInteractStrat.add(btnAddInteract);
		
		panelConfigAlgo.setToolTipText("The basic controls for configuration algorithms");
		panelConfigAlgo.setBackground(Color.LIGHT_GRAY);
		panelConfigAlgo.setLayout(new GridLayout(3, 1, 0, 5));
		controlPnl.add(panelConfigAlgo);
		
		lblConfigAlgo.setToolTipText("Config algo section");
		lblConfigAlgo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblConfigAlgo.setHorizontalAlignment(SwingConstants.CENTER);
		panelConfigAlgo.add(lblConfigAlgo);
		
		txtConfigAlgo.setToolTipText("Input a config algo");
		txtConfigAlgo.setText(modelCtrlAdpt.getDefaultConfigAlgo());
		txtConfigAlgo.setColumns(10);
		panelConfigAlgo.add(txtConfigAlgo);
		
		btnAddConfigAlgo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtConfigAlgo.getText().isBlank()) {
					TDropListItem1 strat = modelCtrlAdpt.addConfigAlgo(txtConfigAlgo.getText());
					if (strat != null) {
						boxType1.addItem(strat);
						boxType1.setSelectedIndex(boxType1.getItemCount() - 1);
						boxType2.addItem(strat);
					}
				}
			}
		});
		btnAddConfigAlgo.setToolTipText("Adds the inputted config algo to the list");
		btnAddConfigAlgo.setBackground(Color.YELLOW);
		panelConfigAlgo.add(btnAddConfigAlgo);
		
		panelDropdowns.setToolTipText("The strategy choices");
		panelDropdowns.setBackground(Color.LIGHT_GRAY);
		panelDropdowns.setLayout(new GridLayout(4, 0, 0, 5));
		controlPnl.add(panelDropdowns);
		
		btnMakeBall.setBackground(Color.GREEN);
		btnMakeBall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelCtrlAdpt.makeBall(boxBallType.getItemAt(boxBallType.getSelectedIndex()), boxType1.getItemAt(boxType1.getSelectedIndex()));
			}
		});
		btnMakeBall.setToolTipText("Makes the ABall in boxType1.");
		panelDropdowns.add(btnMakeBall);
		
		boxType1.setToolTipText("The type of ABall to spawn or have Switchers change to.");
		panelDropdowns.add(boxType1);
		boxType2.setToolTipText(
				"When btnCombine is pressed, this type is combined with boxType1 to create a new strategy.");
		panelDropdowns.add(boxType2);

		btnCombine.setBackground(Color.MAGENTA);
		btnCombine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TDropListItem1 strat = modelCtrlAdpt.combineStrategies(boxType1.getItemAt(boxType1.getSelectedIndex()),
						boxType2.getItemAt(boxType2.getSelectedIndex()));
				boxType1.addItem(strat);
				boxType1.setSelectedIndex(boxType1.getItemCount() - 1);
				boxType2.addItem(strat);
			}
		});
		btnCombine.setToolTipText("Combines the strategies in boxType1 and boxType2.");
		panelDropdowns.add(btnCombine);
		
		panelSwitcher.setToolTipText("The switcher controls");
		panelSwitcher.setBackground(Color.LIGHT_GRAY);
		panelSwitcher.setLayout(new GridLayout(4, 0, 0, 5));
		
		lblSwitcherInfo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSwitcherInfo.setToolTipText("Current switcher strategy::");
		lblSwitcherInfo.setHorizontalAlignment(SwingConstants.CENTER);
		panelSwitcher.add(lblSwitcherInfo);
		
		lblSwitcherStrat.setVerticalAlignment(SwingConstants.TOP);
		lblSwitcherStrat.setToolTipText("The current switcher strategy");
		lblSwitcherStrat.setHorizontalAlignment(SwingConstants.CENTER);
		panelSwitcher.add(lblSwitcherStrat);
		
		btnMakeSwitcher.setBackground(Color.CYAN);
		btnMakeSwitcher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelCtrlAdpt.makeSwitcherBall(boxBallType.getItemAt(boxBallType.getSelectedIndex()));
			}
		});
		btnMakeSwitcher.setToolTipText("Makes a Switcher ABall.");
		panelSwitcher.add(btnMakeSwitcher);
		
		btnSwitcher.setBackground(Color.CYAN);
		btnSwitcher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelCtrlAdpt.switchStrategy(boxType1.getItemAt(boxType1.getSelectedIndex()));
				lblSwitcherStrat.setText(boxType1.getItemAt(boxType1.getSelectedIndex()).toString());
			}
		});
		btnSwitcher.setToolTipText("Makes all Switcher Balls change strategy to whatever is in boxType1.");
		panelSwitcher.add(btnSwitcher);
		
		controlPnl.add(panelSwitcher);
		
		btnClear.setBackground(Color.RED);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelCtrlAdpt.clearBalls();
			}
		});
		btnClear.setToolTipText("Clears all balls in the canvasPnl.");
		controlPnl.add(btnClear);
	}

	/**
	 * @return the canvas panel.
	 */
	public Container getCanvas() {
		return this.canvasPnl;
	}

	/**
	 * Repaints the center panel.
	 */
	public void update() {
		canvasPnl.repaint();
	}

	/**
	 * Starts the GUI.
	 */
	public void start() {
		setVisible(true);
	}
	
	/**
	 * Adds a component to the GUI.
	 * 
	 * @param label the String label
	 * @param fac a Component factory
	 */
	public void addComponent(String label, Supplier<JComponent> fac) {
		this.configFrame.start();
		this.configFrame.addComponentFac(label, fac);
	}
}
