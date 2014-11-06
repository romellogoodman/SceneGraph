package SceneGraph;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ControlPanel extends JPanel
{

	private GraphicsFrame	gf;

	static JTextField		xTransField		= new JTextField();
	static JTextField		yTransField		= new JTextField();
	static JTextField		zTransField		= new JTextField();
	private JTextField		xScaleField		= new JTextField();
	private JTextField		yScaleField		= new JTextField();
	private JTextField		zScaleField		= new JTextField();
	private JTextField		xAxisRotField	= new JTextField();
	private JTextField		yAxisRotField	= new JTextField();
	private JTextField		zAxisRotField	= new JTextField();
	private JTextField		angleField		= new JTextField();
	private JLabel			scaleLabel		= new JLabel("Scale");
	private JLabel			xScaleLabel		= new JLabel("X-Scale");
	private JLabel			yScaleLabel		= new JLabel("Y-Scale");
	private JLabel			zScaleLabel		= new JLabel("Z-Scale");
	private JLabel			transLabel		= new JLabel("Translate");
	private JLabel			xTransLabel		= new JLabel("X-Trans");
	private JLabel			yTransLabel		= new JLabel("Y-Trans");
	private JLabel			zTransLabel		= new JLabel("Z-Trans");
	private JLabel			rotLabel		= new JLabel("Rotations");
	private JLabel			xAxisLabel		= new JLabel("X-Axis");
	private JLabel			yAxisLabel		= new JLabel("Y-Axis");
	private JLabel			zAxisLabel		= new JLabel("Z-Axis");
	private JLabel			angleLabel		= new JLabel("Angle");
	private int mode = 0;
	static SceneObject sceneObject = new SceneObject();

	public ControlPanel(GraphicsFrame _gf)
	{
		gf = _gf;

		setLayout(new GridLayout(20, 1, 2, 2));

		JRadioButton cubeButton = new JRadioButton("Cube");
		cubeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				sceneObject = gf.cube;
			}
		});
		
		JRadioButton pyramidButton = new JRadioButton("Pyramid");
		pyramidButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				sceneObject = gf.pyramid;
			}
		});
		
		JRadioButton prismButton = new JRadioButton("Trapezoid");
		prismButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				sceneObject = gf.trapezoid;
			}
		});
		
		JRadioButton diamondButton = new JRadioButton("Diamond");
		diamondButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				sceneObject = gf.diamond;
			}
		});

		JButton scaleButton = new JButton("Scale");
		scaleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				double x = Double.parseDouble(xScaleField.getText());
				double y = Double.parseDouble(yScaleField.getText());
				double z = Double.parseDouble(zScaleField.getText());
				sceneObject.transform(x, y, z, 1);
				gf.getGraphicPanel().repaint();
			}
		});

		JButton translateButton = new JButton("Translate");
		translateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				double x = Double.parseDouble(getxTransField().getText());
				double y = Double.parseDouble(yTransField.getText());
				double z = Double.parseDouble(getzTransField().getText());
				sceneObject.transform(x, y, z, 0);
				gf.getGraphicPanel().repaint();
			}
		});

		JButton rotateXButton = new JButton("Rotate X");
		rotateXButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				double param = Double.parseDouble(angleField.getText());
				sceneObject.rotation(param, 0);
				gf.getGraphicPanel().repaint();
			}
		});
		JButton rotateYButton = new JButton("Rotate Y");
		rotateYButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				double param = Double.parseDouble(angleField.getText());
				sceneObject.rotation(param, 1);
				gf.getGraphicPanel().repaint();
			}
		});
		JButton rotateZButton = new JButton("Rotate Z");
		rotateZButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				double param = Double.parseDouble(angleField.getText());
				sceneObject.rotation(param, 2);
				gf.getGraphicPanel().repaint();
			}
		});

		JButton arbitraryButton = new JButton("Arbitrary");
		arbitraryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				double angle = Double.parseDouble(angleField.getText());
				double x = Double.parseDouble(xAxisRotField.getText());
				double y = Double.parseDouble(yAxisRotField.getText());
				double z = Double.parseDouble(zAxisRotField.getText());
				double[][] pointTwo =
				{
				{ x },
				{ y },
				{ z } };
				sceneObject.arbRotation(pointTwo, angle);
				gf.getGraphicPanel().repaint();
			}
		});

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				sceneObject.v = sceneObject.vertices;
				gf.getGraphicPanel().repaint();
			}
		});
		
		JButton centerButton = new JButton("Center");
		centerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				double x = gf.getWidth() / 2;
				double y = gf.getHeight() / 2;
				double z = 10;
				sceneObject.transform(sceneObject.center[0], sceneObject.center[1], sceneObject.center[2], 0);
				//sceneObject.transform(x, y, z, 0);
				gf.getGraphicPanel().repaint();
			}
		});
		
		//-- for rotating
		final ActionListener rotate = new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
//				double paramX = Double.parseDouble(xTransField.getText());
//				double paramY = Double.parseDouble(yTransField.getText());
//				double paramZ = Double.parseDouble(zTransField.getText());
//				System.out.println("X: " + paramX + " Y: " + paramY + " Z: " + paramZ);
//				
//				sceneObject.rotation(paramX, 0);
//				sceneObject.rotation(paramY, 1);
//				sceneObject.rotation(paramZ, 2);
				
				double param = Double.parseDouble(angleField.getText());
				sceneObject.rotation(param, 0);
				sceneObject.rotation(param, 1);
				sceneObject.rotation(param, 2);
				gf.getGraphicPanel().repaint();
			}
		};
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						gf.getGraphicPanel().getAnimationTimer().addActionListener(rotate);
						gf.getGraphicPanel().getAnimationTimer().start();
				
					}
				}
			);
		

		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						gf.getGraphicPanel().getAnimationTimer().stop();
					}
				}
			);
		
		//-- shapes
		add(cubeButton);
		add(pyramidButton);
		add(diamondButton);
		add(prismButton);

		//-- trans
		add(transLabel);
		add(translateButton);
		add(xTransLabel);
		add(getxTransField());
		add(yTransLabel);
		add(yTransField);
		add(zTransLabel);
		add(getzTransField());

		//-- scale
		add(scaleLabel);
		add(scaleButton);
		add(xScaleLabel);
		add(xScaleField);
		add(yScaleLabel);
		add(yScaleField);
		add(zScaleLabel);
		add(zScaleField);

		//-- rotate
		add(rotLabel);
		add(arbitraryButton);
		add(angleLabel);
		//angleField.setText("Angle"); //to distinguish while figuring out JLabels
		add(angleField);
		add(xAxisLabel);
		add(xAxisRotField);
		add(yAxisLabel);
		add(yAxisRotField);
		add(zAxisLabel);
		add(zAxisRotField);
		add(rotateXButton);
		add(rotateYButton);
		add(rotateZButton);

		//-- animation and reset and reset
		add(resetButton);
		add(startButton);
		add(stopButton);
		//add(centerButton);
	}

	public void setAngleField(JTextField angleField)
	{
		this.angleField = angleField;
	}

	public JTextField getxTransField()
	{
		return xTransField;
	}

	public void setxTransField(JTextField xTransField)
	{
		this.xTransField = xTransField;
	}

	public JTextField getzTransField()
	{
		return zTransField;
	}

	public void setzTransField(JTextField zTransField)
	{
		this.zTransField = zTransField;
	}

}
