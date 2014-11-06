package SceneGraph;

public class Trapezoid extends SceneObject
{
	double[][]	vTrapezoid			=
		{
		{ 0.75, -0.75, -0.25, 0.25, 0.75, -0.75, -0.25, 0.25, 0.0 },
		{ 0.5, 0.5, -0.5, -0.5, 0.5, 0.5, -0.5, -0.5, 0.0 },
		{ 0.5, 0.5, 0.5, 0.5, -0.5, -0.5, -0.5, -0.5, 0.0 },
		{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 } };

	//store vertices so we can reset
	double[][]	verticesTrapezoid	=
		{
		{ 0.75, -0.75, -0.25, 0.25, 0.75, -0.75, -0.25, 0.25, 0.0 },
		{ 0.5, 0.5, -0.5, -0.5, 0.5, 0.5, -0.5, -0.5, 0.0 },
		{ 0.5, 0.5, 0.5, 0.5, -0.5, -0.5, -0.5, -0.5, 0.0 },
		{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 } };
	
	int			facesTrapezoid[][]	=
			//Each pair should add up to 180 degrees
			{
			{ 0, 1, 2, 3 }, //-- front 
			{ 5, 4, 7, 6 }, //-- back
			{ 1, 5, 6, 2 }, //-- left
			{ 4, 0, 3, 7 }, //-- right
			{ 6, 7, 3, 2 }, //-- top
			{ 1, 0, 4, 5 }	//-- bottom
			};


	// -- construct the Trapazoid
	public Trapezoid()
	{
		v = vTrapezoid;
		vertices = verticesTrapezoid;
		faces = facesTrapezoid;
		colorX = new int[4];
		colorY = new int[4];
	}

}
