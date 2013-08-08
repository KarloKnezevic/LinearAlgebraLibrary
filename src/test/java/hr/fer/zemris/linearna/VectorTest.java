package hr.fer.zemris.linearna;

import static org.junit.Assert.*;
import hr.fer.zemris.linearalgebra.exception.IncompatibleOperandException;
import hr.fer.zemris.linearalgebra.exception.UnmodifiableObjectException;
import hr.fer.zemris.linearalgebra.matrix.IMatrix;
import hr.fer.zemris.linearalgebra.vector.IVector;
import hr.fer.zemris.linearalgebra.vector.Vector;

import org.junit.Test;

/**
 * Testovi za razred Vector.
 * Testirane su sve metode osim metoda toString i toArray jer su
 * one indeirektno testirane kroz primjere u paketu Demo.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class VectorTest {

	/**
	 * Metoda vraća element s određenog indeksa.
	 */
	@Test
	public void testGet() {
		IVector v = Vector.parseSimple(" 0 -1   8   9");
		assertEquals(-1, v.get(1), 1E-9);
	}
	
	/**
	 * Metoda postavlja element na određen indeks.
	 */
	@Test
	public void testSet() {
		IVector v = Vector.parseSimple(" 0 -1   8   9");
		v.set(3, -1234);
		assertEquals(-1234, v.get(3), 1E-9);
	}
	
	/**
	 * Ukoliko se pokuša primijeniti element u vektoru koji je 
	 * nepromjenjiv, izbacuje se iznimka.
	 */
	@Test(expected=UnmodifiableObjectException.class)
	public void testSetErrorIfTrySetImmutableVector() {
		IVector v = new Vector(true, true, new double[] {0,1,2,3,4,5});
		v.set(3, -1234);
	}
	
	/**
	 * Meotda vraća dimenziju vektora.
	 */
	@Test
	public void testGetDimension() {
		IVector v = Vector.parseSimple(" 0 -1   8   9 234 465 67 1");
		assertEquals(8, v.getDimension());
	}
	
	/**
	 * Meotda kopira vektor i kopirani vektor nema nikave
	 * veze s originalnim vektorom.
	 */
	@Test
	public void testCopy() {
		IVector v = Vector.parseSimple(" 0 -1   8   9");
		IVector copy = v.copy();
		assertFalse(v == copy);
	}
	
	/**
	 * Kopiranje dijela vektora: kopira se onoliko elemenata koliko
	 * je zadano.
	 */
	@Test
	public void testCopyPart() {
		IVector v = Vector.parseSimple(" -12 5   7   6");
		IVector copy = v.copyPart(3);
		assertTrue(copy.get(0)==v.get(0) && 
				copy.get(1)==v.get(1) && copy.get(2)==v.get(2) 
				&& copy.getDimension() == 3);
	}
	
	/**
	 * Ukoliko je prilikom kopiranja zadano više elemenata nego što originalni
	 * vektor sadrži, ostatak elemenata postavlja se na 0.
	 */
	@Test
	public void testCopyPartWithZerros() {
		IVector v = Vector.parseSimple(" -12 5   7   6");
		IVector copy = v.copyPart(10);
		assertTrue(copy.get(0)==v.get(0) && 
				copy.get(3)==v.get(3) && copy.get(7)==0
				&& copy.getDimension() == 10);
	}
	
	/**
	 * Stvaranje novog primjera tipa Vektor. Novi primjer nema
	 * nikakve veze s originalnim vektorom.
	 */
	@Test
	public void testNewInstance() {
		IVector v = Vector.parseSimple(" 0 -1   8   9");
		IVector copy = v.newInstance(30);
		assertTrue(copy.getDimension()==30 && copy.get(28)==0);
	}
	
	/**
	 * Zabrajanje vektora na originalni vektor.
	 */
	@Test
	public void testAddVector() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9");
		IVector v2 = Vector.parseSimple(" 8 2   -8   11");
		v1.add(v2);
		assertArrayEquals(new double[] {8, 1, 0, 20}, v1.toArray(), 1E-9);
	}
	
	/**
	 * Ukoliko se pokušaju zbrojiti vektori različitih deimenzija,
	 * izbacuje se iznimka.
	 */
	@Test(expected=IncompatibleOperandException.class)
	public void testAddVectorException() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9");
		IVector v2 = Vector.parseSimple(" 8 2   -8   11 4");
		v1.add(v2);
	}
	
	/**
	 * Zbrajanje vektora rezultira novim vektorom koji čiji elementi
	 * predstavljaju zbroj.
	 */
	@Test
	public void testnAddVectorRightAddition() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9");
		IVector v2 = Vector.parseSimple(" 8 2   -8   11");
		IVector v3 = v1.nAdd(v2);
		assertArrayEquals(new double[] {8, 1, 0, 20}, v3.toArray(), 1E-9);
	}
	
	/**
	 * Zbrajanje vektora metodom nAdd ne mijenja originalni vektor.
	 */
	@Test
	public void testnAddVectorFirstVectorUncanged() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9");
		IVector copy = v1.copy(); 
		IVector v2 = Vector.parseSimple(" 8 2   -8   11");
		v1.nAdd(v2);
		assertArrayEquals(copy.toArray(), v1.toArray(), 1E-9);
	}
	
	/**
	 * Oduzimanje vektora.
	 */
	@Test
	public void testSubVector() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9");
		IVector v2 = Vector.parseSimple(" 8 2   -8   11");
		v1.sub(v2);
		assertArrayEquals(new double[] {-8, -3, 16, -2}, v1.toArray(), 1E-9);
	}
	
	/**
	 * Oduzimanje vektora nejednakih dimenzija rezultira iznimkom.
	 */
	@Test(expected=IncompatibleOperandException.class)
	public void testSubVectorException() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9 8345");
		IVector v2 = Vector.parseSimple(" 8 2   -8   11");
		v1.sub(v2);
	}
	
	/**
	 * Oduzimanje vektora rezultira novim vektorom čiji elementi
	 * su jednaki razlici.
	 */
	@Test
	public void testnSubVectorFirstVectorUncanged() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9");
		IVector copy = v1.copy(); 
		IVector v2 = Vector.parseSimple(" 8 2   -8   11");
		v1.nSub(v2);
		assertArrayEquals(copy.toArray(), v1.toArray(), 1E-9);
	}
	
	/**
	 * Množenje skalarom.
	 */
	@Test
	public void testScalarMulitply() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9");
		v1.scalarMultiply(5);
		assertArrayEquals(new double[] {0, -5, 40, 45}, v1.toArray(), 1E-9);
	}
	
	/**
	 * Množenje skalarom, ali bez promjene originalnog vektora.
	 */
	@Test
	public void testnScalarMulitplyVectorFirstVectorUncanged() {
		IVector v1 = Vector.parseSimple(" 0 -1   8   9");
		IVector copy = v1.copy(); 
		v1.nScalarMultiply(5);
		assertArrayEquals(copy.toArray(), v1.toArray(), 1E-9);
	}
	
	/**
	 * Norma vektora.
	 */
	@Test
	public void testNorm() {
		IVector v1 = Vector.parseSimple(" 1 1   1   1");
		assertEquals(2, v1.norm(), 1E-9);
	}
	
	/**
	 * Normalizacija nul vektora je i dalje nul vektor.
	 */
	@Test
	public void testNormalize() {
		IVector v1 = Vector.parseSimple(" 0 0   0   0");
		v1.normalize();
		assertArrayEquals(new double[] {0, 0, 0, 0}, v1.toArray(), 1E-9);
	}
	
	/**
	 * Normalizacija se ne vrši nad originalnim vektorom.
	 */
	@Test
	public void testnNormalize() {
		IVector v1 = Vector.parseSimple(" 1 1   1   1");
		v1.nNormalize();
		assertArrayEquals(new double[] {1, 1, 1, 1}, v1.toArray(), 1E-9);
	}
	
	/**
	 * Kosinus kuta između vektora.
	 */
	@Test
	public void testCosine() {
		IVector v1 = Vector.parseSimple(" 1 1   1   1");
		IVector v2 = Vector.parseSimple(" -1 1   -1   1");
		assertEquals(0, v1.cosine(v2), 1E-9);
	}
	
	/**
	 * Traženje kosinusa kuta između vektora nejednakih dimenzija rezultira
	 * iznimkom.
	 */
	@Test(expected=IncompatibleOperandException.class)
	public void testCosineException() {
		IVector v1 = Vector.parseSimple(" 1 1   1   1");
		IVector v2 = Vector.parseSimple(" -1 1   -1   1 1");
		v1.cosine(v2);
	}
	
	/**
	 * Traženje kosinusa kuta između nul vektora rezultira iznimkom.
	 */
	@Test(expected=IncompatibleOperandException.class)
	public void testCosineExceptionZerroVector() {
		IVector v1 = Vector.parseSimple(" 0 0  0   0");
		IVector v2 = Vector.parseSimple(" -1 1   -1   1");
		v1.cosine(v2);
	}
	
	/**
	 * Vektorski produkt.
	 */
	@Test
	public void testVectorProduct() {
		IVector v1 = Vector.parseSimple("  1  2 3  ");
		IVector v2 = Vector.parseSimple(" 4  5 6  ");
		IVector v3 = v1.nVectorProduct(v2);
		assertArrayEquals(new double[] {-3, 6, -3}, v3.toArray(), 1E-9);
		
	}
	
	/**
	 * Dozvoljen je vektorski produkt nad vektorima dimenzije 3.
	 */
	@Test(expected=IncompatibleOperandException.class)
	public void testVectorProductException() {
		IVector v1 = Vector.parseSimple("  1  2 3  1");
		IVector v2 = Vector.parseSimple(" 4  5 6  7");
		v1.nVectorProduct(v2);
		
	}
	
	/**
	 * Vraćanje iz homogenih koordinata u ne homogene koordinate.
	 */
	@Test
	public void testnFromaHomogeneus() {
		IVector v1 = Vector.parseSimple(" 5  10 15  5");
		IVector v2 = v1.nFromHomogeneus();
		assertArrayEquals(new double[] {1, 2, 3}, v2.toArray(), 1E-9);
		
	}
	
	/**
	 * Vraćanje iz homogenih koordinata u ne homogene koordinate.
	 * Ukoliko je homogena koordinata jednaka 0, dobiva se nul vektor.
	 */
	@Test
	public void testnFromaHomogeneusIfZerro() {
		IVector v1 = Vector.parseSimple(" 5  10 15  0");
		IVector v2 = v1.nFromHomogeneus();
		assertArrayEquals(new double[] {0, 0, 0}, v2.toArray(), 1E-9);
		
	}
	
	/**
	 * Pretvaranje vektora u jednoretčanu matricu.
	 */
	@Test
	public void testToRowMatrix() {
		IVector v1 = Vector.parseSimple(" 5  10 15  5");
		IMatrix m = v1.toRowMatrix(true);
		v1.set(2, 12345);
		assertEquals(m.get(0, 2), 12345, 1E-9);
		
	}
	
	/**
	 * Pretvaranje vektora u jednoretčanu matricu bez live view.
	 */
	@Test
	public void testToRowMatrixNoLiveView() {
		IVector v1 = Vector.parseSimple(" 5  10 15  5");
		IMatrix m = v1.toRowMatrix(false);
		v1.set(2, 12345);
		assertTrue(m.get(0, 2) != 12345);
		
	}
	
	/**
	 * Pretvaranje vektora u jednostupčanu matricu.
	 */
	@Test
	public void testToColumnMatrix() {
		IVector v1 = Vector.parseSimple(" 5  10 15  5");
		IMatrix m = v1.toColumnMatrix(true);
		v1.set(2, 12345);
		assertEquals(m.get(2, 0), 12345, 1E-9);
		
	}
	
	/**
	 * Pretvaranje vektora u jednostupčanu matricu bez live view.
	 */
	@Test
	public void testToColumnMatrixNoLiveView() {
		IVector v1 = Vector.parseSimple(" 5  10 15  5");
		IMatrix m = v1.toColumnMatrix(false);
		v1.set(2, 12345);
		assertTrue(m.get(2, 0) != 12345);
		
	}
	
	/**
	 * Defaultni ispis.
	 */
	@Test
	public void testToString() {
		IVector v1 = Vector.parseSimple(" 5  10 15  5");
		String s = v1.toString();
		assertEquals("[5,000, 10,000, 15,000, 5,000]", s);
	}

}
