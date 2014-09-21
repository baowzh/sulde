package com.mongolia.website.util;

public class Decorator {
	
	private Print  print;
	/**
	 * 
	 * @param tagname
	 */
	public void printTagHead(String tagname) {
		print.print("<" + tagname + ">");
	}

	/**
	 * 
	 * @param tagname
	 */
	public void printTagTail(String tagname) {
		print.print("<" + tagname + "/>");
	}

	/**
	 * 
	 * @param content
	 */
	public void printTagContent(String content) {
		print.print(content);
	}

	public void println() {
		print.println("");
	}

	public void printHellWord() {
		print.println("hello world");
		this.printTagHead("A");
		this.printTagContent("hello world");
		this.printTagTail("A");
		this.println();
		this.printTagHead("BODY");
		this.printTagContent("hello world");
		this.printTagTail("BODY");
		this.println();

	}

	public static void main(String args[]) {
		Decorator decorator = new Decorator();
		decorator.printHellWord();

	}

}
