package hw6;

public class Q20_Program {
	private static Question head, victory, failure;
	
	public static void main(String[] args) {
		// Temporary pseudo-looping variables
		Question parent, child;
		
		// Create head, victory and failure questions
		head = new Question("Think of a musical instrument! Are you ready?", 4);
		victory = new Question("Yay! I got it! Play again?", 0);
		failure = new Question("Aww! You got me! Play again?", 0);
		
		// Leave ifNo as null for victory and failure, at which point the program will close
		victory.setIfYes(head);
		failure.setIfYes(head);
		
		// Loop on head until they say yes
		head.setIfNo(head);
		
		// Populate the binary tree with data
		head.setIfYes(new Question("Does it have strings?", 3));
		
		parent = head.getIfYes(); // Does it have strings?
		parent.setIfYes(new Question("Is it percussive?", 2)); // Goto line 29
		parent.setIfNo(new Question("Is it made of brass?", 2)); // Goto line 36
		
		child = parent.getIfYes(); // Is it percussive?
		child.setIfYes(new Question("Does it have keys?", 1));
		child.setIfNo(new Question("Is it played under the chin?", 1));
		
		Guess(child.getIfYes(), "a piano", "a harp"); // Does it have keys?
		Guess(child.getIfNo(), "a violin", "a cello"); // Is it played under the chin?
		
		child = parent.getIfNo(); // Is it made of brass?
		child.setIfYes(new Question("Does it have valve pistons?", 1));
		child.setIfNo(new Question("Do you blow through a reed when playing it?", 1));
		
		Guess(child.getIfYes(), "a trumpet", "a trombone"); // Does it have valve pistons?
		Guess(child.getIfNo(), "an oboe", "a clarinet"); // Does it have valve pistons?
		
		GUI app = new GUI(head);
	}
	
	private static void Guess(Question branch, String guessYes, String guessNo) {
		branch.setIfYes(new Question("Is it " + guessYes + "?", 0));
		branch.getIfYes().setIfYes(victory);
		branch.getIfYes().setIfNo(failure);
		
		branch.setIfNo(new Question("Is it " + guessNo + "?", 0));
		branch.getIfNo().setIfYes(victory);
		branch.getIfNo().setIfNo(failure);
	}
}
