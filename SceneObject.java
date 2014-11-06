package SceneGraph;

import java.awt.Color;
import java.awt.Graphics2D;

public class SceneObject
{
	public double[][]	v;
	public double[][]	vertices;
	public int[][]		faces;
	public int[]		colorX;
	public int[]		colorY;
	public double[]		center = new double[3];

	public Color[]		colors	=
								{ new Color(255, 0, 0), //-- red
			new Color(0, 0, 255), //-- blue
			new Color(0, 255, 0), //-- green
			new Color(255, 255, 0), //-- yellow
			new Color(255, 200, 0), //-- orange
			new Color(255, 175, 175), //-- pink
			new Color(255, 255, 255) }; //-- white

	public SceneObject()
	{
	}

	public void draw(Graphics2D g2d, Color c)
	{
		// -- draw the edges
		for (int i = 0; i < faces.length; i++)
		{
			int k;
			for (k = 0; k < faces[0].length - 1; k++)
			{
				if (isVisble(i) == true)
				{
					color(g2d, i); //-- color the face
					g2d.setColor(c); //-- reset the drawing color for wire frame
					g2d.drawLine((int) v[0][faces[i][k]], (int) v[1][faces[i][k]], (int) v[0][faces[i][k + 1]], (int) v[1][faces[i][k + 1]]);
				}
			}
			if (isVisble(i) == true)
				g2d.drawLine((int) v[0][faces[i][k]], (int) v[1][faces[i][k]], (int) v[0][faces[i][0]], (int) v[1][faces[i][0]]);

			//-- old implementation
			//for (int i = 0; i < edges.length; i++)
			//{
			//	g2d.drawLine((int) v[0][edges[i][0]], (int) v[1][edges[i][0]], (int) v[0][edges[i][1]], (int) v[1][edges[i][1]]);
			//}
		}

	}

	//-- determind visible sides
	public boolean isVisble(int f)
	{
		boolean result = false;
		double viewer[] =
		{ 0, 0, -1 };

		//-- get 3 vertices
		int v00 = faces[f][0];
		int v01 = faces[f][1];
		int v02 = faces[f][faces[f].length - 1];

		//-- get 9 points
		double x0 = v[0][v00]; //-- x coordinate of v00
		double y0 = v[1][v00]; //-- y coordinate of v00
		double z0 = v[2][v00]; //-- z coordinate of v00

		double x1 = v[0][v01]; //-- x coordinate of v01
		double y1 = v[1][v01]; //-- y coordinate of v01
		double z1 = v[2][v01]; //-- z coordinate of v01

		double x2 = v[0][v02]; //-- x coordinate of v02
		double y2 = v[1][v02]; //-- y coordinate of v02
		double z2 = v[2][v02]; //-- z coordinate of v02

		//-- get vectors
		double v0x = x1 - x0;
		double v0y = y1 - y0;
		double v0z = z1 - z0;

		double v1x = x2 - x0;
		double v1y = y2 - y0;
		double v1z = z2 - z0;

		//-- compute the surface normal (cross product)
		double crossx = v0y * v1z - v0z * v1y;
		double crossy = v0z * v1x - v0x * v1z;
		double crossz = v0x * v1y - v0y * v1x;
		//System.out.println(crossx + ", " + crossy + ", " + crossz); //--to test

		//-- compute the angle between the surface normal and the viewer
		double dotprod = (crossx * viewer[0] + crossy * viewer[1] + crossz * viewer[2])
				/ Math.sqrt(Math.pow(crossx, 2) + Math.pow(crossy, 2) + Math.pow(crossz, 2))
				* Math.sqrt(Math.pow(viewer[0], 2) + Math.pow(viewer[1], 2) + Math.pow(viewer[2], 2));
		//System.out.println(dotprod); //-- to test

		//-- compute aCos and convert to Degress
		double aCos = Math.acos(dotprod);
		double angle = Math.toDegrees(aCos);
		//System.out.println("face #" + f + ":" + angle); //-- to test

		if (angle <= 90 || angle <= -90)
			result = true;
		return result;
	}

	//-- color
	public void color(Graphics2D g2d, int i)
	{
		g2d.setColor(colors[i]);
		for (int j = 0; j < colorX.length; j++)
		{
			colorX[j] = (int) v[0][faces[i][j]];
			colorY[j] = (int) v[1][faces[i][j]];
		}
		g2d.fillPolygon(colorX, colorY, colorX.length);
	}

	/* -- Transformation 
	      0: translate
	      1: scale
	-- */
	public void transform(double xFactor, double yFactor, double zFactor, int transformation)
	{
		double trans[][] = null;

		int center = v[0].length - 1;

		switch (transformation)
		{
			case 0: // -- translate
				// -- translation matrix
				double translate[][] =
				{
				{ 1, 0, 0, xFactor },
				{ 0, 1, 0, yFactor },
				{ 0, 0, 1, zFactor },
				{ 0, 0, 0, 1 } };
				trans = translate;
				break;
			case 1: // -- scale
			{
				// -- translation to origin
				double trans0[][] =
				{
				{ 1, 0, 0, -v[0][center] },
				{ 0, 1, 0, -v[1][center] },
				{ 0, 0, 1, -v[2][center] },
				{ 0, 0, 0, 1 } };
				// -- scale
				double scale[][] =
				{
				{ xFactor, 0, 0, 0 },
				{ 0, yFactor, 0, 0 },
				{ 0, 0, zFactor, 0 },
				{ 0, 0, 0, 1 } };
				// -- translate back to point
				double trans1[][] =
				{
				{ 1, 0, 0, v[0][center] },
				{ 0, 1, 0, v[1][center] },
				{ 0, 0, 1, v[2][center] },
				{ 0, 0, 0, 1 } };

				// -- build transformation matrix    trans1 X scale X trans0
				trans = matmult(scale, trans0);
				trans = matmult(trans1, trans);
			}
				break;
		}
		v = matmult(trans, v);
	}

	/* -- Rotation 
	    0: rotate x
	    1: rotate y
	    2: rotate z
	-- */
	public void rotation(double factor, int transformation)
	{
		double trans[][] = null;

		int center = v[0].length - 1;

		switch (transformation)
		{

			case 0: // -- rotate x
			{
				// -- degrees to radius
				double theta = factor * (Math.PI / 180.0);

				// -- translation to origin
				double trans0[][] =
				{
				{ 1, 0, 0, -v[0][center] },
				{ 0, 1, 0, -v[1][center] },
				{ 0, 0, 1, -v[2][center] },
				{ 0, 0, 0, 1 } };
				// -- rotate about X axis
				double rot[][] =
				{
				{ 1, 0, 0, 0 },
				{ 0, Math.cos(theta), -Math.sin(theta), 0 },
				{ 0, Math.sin(theta), Math.cos(theta), 0 },
				{ 0, 0, 0, 1 } };
				// -- translate back to point
				double trans1[][] =
				{
				{ 1, 0, 0, v[0][center] },
				{ 0, 1, 0, v[1][center] },
				{ 0, 0, 1, v[2][center] },
				{ 0, 0, 0, 1 } };

				// -- build transformation matrix    trans1 X rot X trans0
				trans = matmult(rot, trans0);
				trans = matmult(trans1, trans);
			}
				break;
			case 1: // -- rotate y
			{
				// -- degrees to radius
				double theta = factor * (Math.PI / 180.0);

				// -- translation to origin
				double trans0[][] =
				{
				{ 1, 0, 0, -v[0][center] },
				{ 0, 1, 0, -v[1][center] },
				{ 0, 0, 1, -v[2][center] },
				{ 0, 0, 0, 1 } };
				// -- rotate about Y axis
				double rot[][] =
				{
				{ Math.cos(theta), 0, Math.sin(theta), 0 },
				{ 0, 1, 0, 0 },
				{ -Math.sin(theta), 0, Math.cos(theta), 0 },
				{ 0, 0, 0, 1 } };
				// -- translate back to point
				double trans1[][] =
				{
				{ 1, 0, 0, v[0][center] },
				{ 0, 1, 0, v[1][center] },
				{ 0, 0, 1, v[2][center] },
				{ 0, 0, 0, 1 } };

				// -- build transformation matrix    trans1 X rot X trans0
				trans = matmult(rot, trans0);
				trans = matmult(trans1, trans);
			}
				break;
			case 2: // -- rotate z
			{
				// -- degrees to radius
				double theta = factor * (Math.PI / 180.0);

				// -- translation to origin
				double trans0[][] =
				{
				{ 1, 0, 0, -v[0][center] },
				{ 0, 1, 0, -v[1][center] },
				{ 0, 0, 1, -v[2][center] },
				{ 0, 0, 0, 1 } };
				// -- rotate about Z axis
				double rot[][] =
				{
				{ Math.cos(theta), -Math.sin(theta), 0, 0 },
				{ Math.sin(theta), Math.cos(theta), 0, 0 },
				{ 0, 0, 1, 0 },
				{ 0, 0, 0, 1 } };
				// -- translate back to point
				double trans1[][] =
				{
				{ 1, 0, 0, v[0][center] },
				{ 0, 1, 0, v[1][center] },
				{ 0, 0, 1, v[2][center] },
				{ 0, 0, 0, 1 } };

				// -- build transformation matrix    trans1 X rot X trans0
				trans = matmult(rot, trans0);
				trans = matmult(trans1, trans);
			}
				break;
		}
		v = matmult(trans, v);
	}

	public void arbRotation(double[][] point2, double angle) //takes Point(axis of rot) & anlge of rot
	{
		double trans[][] = null;
		int center = v[0].length - 1;

		//P1
		double point1[][] =
		{
		{ 0 },
		{ 0 },
		{ 0 } };
		//Vector U (P2 - P1)
		double[][] U =
		{
		{ point2[0][0] - point1[0][0] },
		{ point2[1][0] - point1[1][0] },
		{ point2[2][0] - point1[2][0] } };
		//Magnitude of U
		double magU = Math.sqrt(U[0][0] * U[0][0] + U[1][0] * U[1][0] + U[2][0] * U[2][0]);
		//Vector V or Array of Alphas x=0, y=1, z=2
		double[][] alpha =
		{
		{ U[0][0] / magU }, //alpha x alpha[0][0]
				{ U[1][0] / magU }, //alpha y alpha[1][0]
				{ U[2][0] / magU } }; //alpha z alpha[2][0]
		double d = Math.sqrt(alpha[1][0] * alpha[1][0] + alpha[2][0] * alpha[2][0]);

		//transformation - p
		double transnegp[][] =
		{
		{ 1, 0, 0, -(v[0][center]) },
		{ 0, 1, 0, -(v[1][center]) },
		{ 0, 0, 1, -(v[2][center]) },
		{ 0, 0, 0, 1 } };
		//rotation x  theta x
		double rotxthetx[][] =
		{
		{ 1, 0, 0, 0 },
		{ 0, (alpha[2][0] / d), -(alpha[1][0] / d), 0 },
		{ 0, (alpha[1][0] / d), (alpha[2][0] / d), 0 },
		{ 0, 0, 0, 1 } };
		//roation y theta y
		double rotythety[][] =
		{
		{ d, 0, -(alpha[0][0] / d), 0 },
		{ 0, 1, 0, 0 },
		{ (alpha[0][0] / d), 0, d, 0 },
		{ 0, 0, 0, 1 } };
		//roation z theta
		double rotzthet[][] =
		{
		{ Math.cos(angle), -Math.sin(angle), 0, 0 },
		{ Math.sin(angle), Math.cos(angle), 0, 0 },
		{ 0, 0, 1, 0 },
		{ 0, 0, 0, 1 } };
		//roation y - theta y
		double rotynegthety[][] =
		{
		{ d, 0, (alpha[0][0] / d), 0 },
		{ 0, 1, 0, 0 },
		{ -(alpha[0][0] / d), 0, d, 0 },
		{ 0, 0, 0, 1 } };
		//rotation x  -theta x
		double rotxnegthetx[][] =
		{
		{ 1, 0, 0, 0 },
		{ 0, (alpha[2][0] / d), (alpha[1][0] / d), 0 },
		{ 0, -(alpha[1][0] / d), (alpha[2][0] / d), 0 },
		{ 0, 0, 0, 1 } };
		//transformation p
		double transp[][] =
		{
		{ 1, 0, 0, v[0][center] },
		{ 0, 1, 0, v[1][center] },
		{ 0, 0, 1, v[2][center] },
		{ 0, 0, 0, 1 } };

		trans = matmult(transnegp, rotxthetx); //T(-P) & Rx
		trans = matmult(rotythety, trans); //Ry
		trans = matmult(rotzthet, trans); //Rz
		trans = matmult(rotynegthety, trans); //Ry-
		trans = matmult(rotxnegthetx, trans); //Rx-
		trans = matmult(transp, trans); //T(P)
		v = matmult(trans, v); //final matrix mult

		printMat(trans);
	}

	// -- Standard matrix multiplication routine
	public double[][] matmult(double A[][], double B[][])
	{
		int rowsA = A.length;
		int colsA = A[0].length;
		int rowsB = B.length;
		int colsB = B[0].length;

		if (colsA != rowsB)
		{
			return null;
		}

		double C[][] = new double[rowsA][colsB];

		for (int i = 0; i < rowsA; ++i)
		{
			for (int j = 0; j < colsB; ++j)
			{
				for (int k = 0; k < rowsB; ++k)
				{
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return C;
	}

	public static void printMat(double A[][])
	{
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A[i].length; j++)
			{
				System.out.print(A[i][j] + "    ");
			}
			System.out.println();
		}
	}
}
