package org.hanuna.gitalk.graph.graph_elements;

/**
 * @author erokhins
 */
public class Branch {
    private static int count = 0;

    public static void clearCountBranch() {
        count = 0;
    }

    private final int numberOfBranch;

    public Branch() {
        numberOfBranch = count;
        count++;
    }

    public int getNumberOfBranch() {
        return numberOfBranch;
    }

    public boolean younger(Branch another) {
        return this.getNumberOfBranch() < another.getNumberOfBranch();
    }
}