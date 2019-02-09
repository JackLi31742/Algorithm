package tree;

public class ResultType {
	public TreeNode node;
    public boolean existA, existB;
    public ResultType(TreeNode node, boolean existA, boolean existB) {
        this.node = node;
        this.existA = existA;
        this.existB = existB;
    }
}
