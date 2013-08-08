package hr.fer.zemris.linearalgebra.vector;

import hr.fer.zemris.linearalgebra.exception.UnmodifiableObjectException;


/**
 * Razred vektor.
 * Razred nasljeđuje ApstractVevtor.
 * Razred omogućuje operacije s vektorima.
 * @author Karlo Knezevic, karlo.knezevic@fer.hr
 *
 */
public class Vector extends AbstractVector {
	
	private double[] elements;
	private int dimension;
	//ukoliko je true, vektor nije moguće mijenjati
	private boolean readOnly;

	/**
	 * Konstruktor.
	 * Prima polje elemenata i sprema referencu na polje!
	 * Pretpostavljeno je da se vektor može mijenjati.
	 * @param elements elementi vektora
	 */
	public Vector(double[] elements) {
		super();
		this.elements = elements;
		dimension = elements.length;
		readOnly = false;
	}
	
	/**
	 * Konstruktor.
	 * Prima zastavicu je li vektor promjenjiv, koristi li se referenca na
	 * predano polje elemenata i polje elemenata vektora.
	 * @param immutable je li vektor promjenjiv?
	 * @param useReference koristi li se referenca na predano polje elemenata?
	 * @param elements elementi vektora
	 */
	public Vector(boolean immutable, boolean useReference, double[] elements) {
		this.readOnly = immutable;
		dimension = elements.length;
		if (useReference) {
			this.elements = elements;
		} else {
			this.elements = new double[elements.length];
			System.arraycopy(elements, 0, this.elements, 0, elements.length);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#get(int)
	 */
	@Override
	public double get(int index) {
		return elements[index];
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#set(int, double)
	 */
	@Override
	public IVector set(int index, double value) {
		if (readOnly) {
			throw new UnmodifiableObjectException("Can not change immutable elements.");
		}
		elements[index] = value;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#getDimension()
	 */
	@Override
	public int getDimension() {
		return dimension;
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#copy()
	 */
	@Override
	public IVector copy() {
		return new Vector(false, false, this.elements);
	}

	/*
	 * (non-Javadoc)
	 * @see hr.fer.zemris.linearna.vector.AbstractVector#newInstance(int)
	 */
	@Override
	public IVector newInstance(int dimension) {
		//ako korisnik zada negativan index, bit će izbačena iznimka da je nevaljan
		//argument i to je ok; korisnik mora znati da ne može stvoriti vektor od
		//negativnog broja elemenata
		return new Vector(new double[dimension]);
	}
	
	/**
	 * Metoda prima tekstrualni opis vektora te vraća objekt tipa Vector.
	 * Format zapisa: elementi
	 * Elementi vektora odvojeni su razmakom (dozvoljen varijabilan broj razmaka
	 * između elemenata).
	 * @param stringVector tekstualni opis vektora
	 * @return ovjekt tipa Vector
	 */
	public static Vector parseSimple(String stringVector) {
		String[] stringElements = stringVector.trim().split("\\s+");
		double[] elements = new double[stringElements.length];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = Double.parseDouble(stringElements[i]);
		}
		return new Vector(elements);
	}
	
}
