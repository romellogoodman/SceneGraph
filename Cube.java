package SceneGraph;

public class Cube extends SceneObject
{
	double[][]	vCube			=
								{
								{ 0.5, -0.5, -0.5, 0.5, 0.5, -0.5, -0.5, 0.5, 0.0 },
								{ 0.5, 0.5, -0.5, -0.5, 0.5, 0.5, -0.5, -0.5, 0.0 },
								{ 0.5, 0.5, 0.5, 0.5, -0.5, -0.5, -0.5, -0.5, 0.0 },
								{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 } };

	//store vertices so we can reset
	double[][]	verticesCube	=
								{
								{ 0.5, -0.5, -0.5, 0.5, 0.5, -0.5, -0.5, 0.5, 0.0 },
								{ 0.5, 0.5, -0.5, -0.5, 0.5, 0.5, -0.5, -0.5, 0.0 },
								{ 0.5, 0.5, 0.5, 0.5, -0.5, -0.5, -0.5, -0.5, 0.0 },
								{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 } };

	int			facesCube[][]	=
								//Each pair should add up to 180 degrees
								{
								{ 0, 1, 2, 3 }, //-- front 
								{ 5, 4, 7, 6 }, //-- back
								{ 1, 5, 6, 2 }, //-- left
								{ 4, 0, 3, 7 }, //-- right
								{ 6, 7, 3, 2 }, //-- top
								{ 1, 0, 4, 5 }	//-- bottom
								};
	double centerCube[] = {vCube[0][8], vCube[1][8], vCube[2][8]} ;

	// -- construct the cube
	public Cube()
	{
		v = vCube;
		vertices = verticesCube;
		faces = facesCube;
		colorX = new int[4];
		colorY = new int[4];
		center = centerCube;
	}
}
