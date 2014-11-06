package SceneGraph;

public class Diamond extends SceneObject
{
	double[][]	vDiamond			=
									{
									{ 0.5, -0.5, 0.0, 0.0, 0.0, 0.0 },
									{ 0.5, 0.5, 0.5, -0.5, 1.5, 0.5 },
									{ 0.5, 0.5, -0.5, 0.0, 0.0, 0.0 },
									{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 } };

	//store vertices so we can reset
	double[][]	verticesDiamond		=
									{
									{ 0.5, -0.5, 0.0, 0.0, 0.0, 0.0 },
									{ 0.5, 0.5, 0.5, -0.5, 1.5, 0.5 },
									{ 0.5, 0.5, -0.5, 0.0, 0.0, 0.0 },
									{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 } };

	int			facesDiamond[][]	=
									{
									//-- top sides
									{ 3, 1, 0 }, //-- front
									{ 0, 2, 3 }, //-- left
									{ 2, 1, 3 }, //-- right
						
									//-- bottom sides
									{ 0, 1, 4 }, //-- front
									{ 4, 2, 0 }, //-- left
									{ 4, 1, 2 }, //-- right
									};

	// -- construct the pyramid
	public Diamond()
	{
		v = vDiamond;
		vertices = verticesDiamond;
		faces = facesDiamond;
		colorX = new int[3];
		colorY = new int[3];
	}
}
