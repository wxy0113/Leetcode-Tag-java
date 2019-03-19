public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
     
    // Recursive： 
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null || p == null) return null;
        
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left == null) ? root : left;
        }
    }
    
    // Iterative: 在树中找到p点，并随时记录p点的中序遍历（左根右）下一个点
    // 若右儿子不为null，则在其右子树中找最左的点
    // 若有儿子为null，则返回其successor
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        TreeNode temp = null;
        while (root != null && root.val != p.val) {
            if (root.val > p.val) {
                temp = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        if (root == null) return null;
        if (root.right == null) {
            return temp;
        }
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}