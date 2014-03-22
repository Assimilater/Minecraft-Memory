package hw6;

//A *very* simple binary tree structure
public class Question {
	private Question ifYes, ifNo;
	private String Prompt;
	private int numLeft;
	
	public Question(String toAsk, int isLeft) {
		Prompt = toAsk;
		numLeft = isLeft;
		ifYes = null;
		ifNo = null;
	}
	
	// Accessors and Mutators
	public Question getIfYes() { return ifYes; }
	public void setIfYes(Question ifYes) { this.ifYes = ifYes; }
	public Question getIfNo() { return ifNo; }
	public void setIfNo(Question ifNo) { this.ifNo = ifNo; }
	public String getPrompt() { return Prompt; }
	public void setPrompt(String prompt) { Prompt = prompt; }
	public int getNumLeft() { return numLeft; }
	public void setNumLeft(int numLeft) { this.numLeft = numLeft; }
}
