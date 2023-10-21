package visitorPlay.Util;

import visitorPlay.Util.Visitor;

/**
 * @author Adrian Jimenez
 *
 * MyElement is a utility class which expects
 * to be visited by some visitor and stores
 * a sentence
 */

public class MyElement implements Element{

	/**
	 * The sentence contained within this object
	 */
	private String sentence;

	/**
	 * Constructs a MyElement object
	 * @param sentenceIn The sentence to be stored
	 * in this object
	 */
	public MyElement(String sentenceIn){
		sentence = sentenceIn;
	}

	/**
	 * Returns the sentence stored in this object
	 */
	public String getSentence(){
		return sentence;
	}

	/**
	 * Accepts a visitor
	 * @param visitor The visitor that will
	 * be performing some algorithm on the
	 * sentence stored in this class
	 */
	@Override
	public void accept(Visitor visitor){
		visitor.visit(this);
	}

	/**
	 * Returns the hashcode of this object
	 */
	@Override
	public int hashCode(){
		return 2;
	}

	/**
	 * Returns a string representation of this object
	 */
	@Override
	public String toString(){
		return "MyElement";
	}

}
