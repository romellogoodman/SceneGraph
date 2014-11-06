package SceneGraph;

public class Pyramid extends SceneObject
{
	double[][]	vPyramid			=
									{
									{ 0.5, -0.5, 0.0, 0.0, 0.0 },
									{ 0.5, 0.5, 0.5, -0.5, 0.0 },
									{ 0.5, 0.5, -0.5, 0.0, 0.0 },
									{ 1.0, 1.0, 1.0, 1.0, 1.0 } };

	//store vertices so we can reset
	double[][]	verticesPyramid		=
									{
									{ 0.5, -0.5, 0.0, 0.0, 0.0 },
									{ 0.5, 0.5, 0.5, -0.5, 0.0 },
									{ 0.5, 0.5, -0.5, 0.0, 0.0 },
									{ 1.0, 1.0, 1.0, 1.0, 1.0 } };

	int			facesPyramid[][]	=
									{
									{ 3, 1, 0 }, //-- front
									{ 0, 2, 3 }, //-- left
									{ 2, 1, 3 }, //-- right
									{ 0, 1, 2 }	 //-- bottom
									};

	// -- construct the pyramid
	public Pyramid()
	{
		v = vPyramid;
		vertices = verticesPyramid;
		faces = facesPyramid;
		colorX = new int[3];
		colorY = new int[3];
	}
}
