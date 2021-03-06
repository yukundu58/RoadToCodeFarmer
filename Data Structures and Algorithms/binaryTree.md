# Binary Tree
1. 对任何一棵二叉树T，如果叶节点个数为a, 度为2的结点数为b，则a=b+1.
2. [二叉树分类](https://www.geeksforgeeks.org/binary-tree-set-3-types-of-binary-tree/)：full, complete, perfect, balanced, degenerate
3. TreeNode
```
public class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
```
4. 树的遍历
* DFS
  * 前序(pre-order)：先根后左再右
  * 中序(in-order)：先左后根再右
  * 后序(post-order)：先左后右再根
* BFS 先访问根节点，沿着树的宽度遍历子节点，直到所有节点均被访问为止。
  * 层序(level-order)
* 前/中/后序遍历使用递归，也就是栈的思想对二叉树进行遍历，广度优先一般使用队列的思想对二叉树进行遍历。
* 如果已知中序遍历和前序遍历或者后序遍历，那么就可以完全恢复出原二叉树结构。其中最为关键的是前序遍历中第一个一定是根，而后序遍历最后一个一定是根，中序遍历在得知根节点后又可进一步递归得知左右子树的根节点。但是这种方法也是有适用范围的：元素不能重复！否则无法完成定位。

__pre-order__
```
// recursive
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorderTraversalHelper(root, res);
        return res;
    }
    private void preorderTraversalHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorderTraversalHelper(root.left, res);
        preorderTraversalHelper(root.right, res);
    }
}

// iterative
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
        return res;
    }
}
```


__in-order__
```
// recursive
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversalHelper(root, res);
        return res;
    }
    private void inorderTraversalHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderTraversalHelper(root.left, res);
        res.add(root.val);
        inorderTraversalHelper(root.right, res);
    }
}

// iterative
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }
        }
        return res;
    }
}
```

__post-order__
```
// recursive
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversalHelper(root, res);
        return res;
    }
    private void postorderTraversalHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorderTraversalHelper(root.left, res);
        postorderTraversalHelper(root.right, res);
        res.add(root.val);
    }
}

// iterative
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        // 注意add(index, element) 是 List<> interface的method，
        // addFirst(element)是Deque里的method。如果想用addFirst，则
        // 需要申明 LinkedList<Integer> res = new LinkedList<Integer>()
        // 或者 Deque<Integer> res = new LinkedList<Integer>();
        // 由于一直往最头部加元素，所以用LinkedList比ArrayList快。
        List<Integer> res = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                res.add(0, node.val); 
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }
        return res;
    }
}
```

__level-order__
```
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            // 如果不需要知道每一层具体有哪些，可以不需要size，直接BFS，返回的是List<Integer>而不是List<List<Integer>>
            int size = queue.size(); 
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                temp.add(node.val);
            }
            res.add(temp);
        }
        return res;
    }
}

```
5. __BST__ 使用中序遍历可得到有序数组，这是二叉查找树的又一个重要特征。
