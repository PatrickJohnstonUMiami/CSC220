package lab03;

import java.util.Scanner;

 
/* Class SGTNode */
class SGTNode    
{
    SGTNode right, left, parent;
    int value;
 
    /* Constructor */
    public SGTNode(int val)
    {
        value = val;
    }
}
 
/* Class ScapeGoatTree */
class ScapeGoatTree
{
	
    private SGTNode root;
    private int n, q;
 
    /* Constructor */    
    public ScapeGoatTree()
    {
        root = null;
        // size = 0
        n = 0;        
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root == null;
    }
    /* Function to clear  tree */
    public void makeEmpty()
    {
        root = null;
        n = 0;
    }    
	public void printSideways(){
		printSideways(root, 0);
	}

	// post: prints in reversed preorder the tree with given root, indenting each line to the given level
	private void printSideways(SGTNode root, int level){
		if (root != null){
			printSideways(root.right, level+1);
			for (int i = 0; i < level; i++){
				System.out.print("       ");
			}
			System.out.println(root.value);
			printSideways(root.left, level+1);
		}
	}
    /* Function to count number of nodes recursively */
    private int size(SGTNode r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += size(r.left);
            l += size(r.right);
            return l;
        }
    }
    /* Functions to search for an element */
    public boolean search(int val)
    {
        return search(root, val);
    }
    /* Function to search for an element recursively */
    private boolean search(SGTNode r, int val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            int rval = r.value;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    /* Function to return current size of tree */   
    public int size() 
    {
        return n;
    }
    /* Function for inorder traversal */
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(SGTNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.print(r.value +" ");
            inorder(r.right);
        }
    }
    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(root);
    }
    private void preorder(SGTNode r)
    {
        if (r != null)
        {
            System.out.print(r.value +" ");
            preorder(r.left);             
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(root);
    }
    private void postorder(SGTNode r)
    {
        if (r != null)
        {
            postorder(r.left);             
            postorder(r.right);
            System.out.print(r.value +" ");
        }
    }     
    private static final int log32(int q) 
    {
        final double log23 = 2.4663034623764317;
        return (int)Math.ceil(log23*Math.log(q));
    }
    /* Function to insert an element */
    public boolean add(int x) 
    {
        /* first do basic insertion keeping track of depth */
        SGTNode u = new SGTNode(x);
        int d = addWithDepth(u);
        if (d > log32(q)) {
            /* depth exceeded, find scapegoat */
            SGTNode w = u.parent;
            while (3*size(w) <= 2*size(w.parent))
                w = w.parent;
            rebuild(w.parent);
        }
        return d >= 0;
    }
    /* Function to rebuild tree from node u */
    protected void rebuild(SGTNode u) 
    {
        int ns = size(u);
        SGTNode p = u.parent;
        SGTNode[] a = new SGTNode[ns];
        packIntoArray(u, a, 0);
        if (p == null) 
        {
            root = buildBalanced(a, 0, ns);
            root.parent = null;
        } 
        else if (p.right == u) 
        {
            p.right = buildBalanced(a, 0, ns);
            p.right.parent = p;
        } 
        else 
        {
            p.left = buildBalanced(a, 0, ns);
            p.left.parent = p;
        }
    }
    /* Function to packIntoArray */
    protected int packIntoArray(SGTNode u, SGTNode[] a, int i) 
    {
        if (u == null) 
        {
            return i;
        }
        i = packIntoArray(u.left, a, i);
        a[i++] = u;
        return packIntoArray(u.right, a, i);
    }
    /* Function to build balanced nodes */
    protected SGTNode buildBalanced(SGTNode[] a, int i, int ns) 
    {
        if (ns == 0)
            return null;
        int m = ns / 2;
        a[i + m].left = buildBalanced(a, i, m);
        if (a[i + m].left != null)
            a[i + m].left.parent = a[i + m];
        a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
        if (a[i + m].right != null)
            a[i + m].right.parent = a[i + m];
        return a[i + m];
    }
    /* Function add with depth */
    public int addWithDepth(SGTNode u) 
    {
        SGTNode w = root;
        if (w == null) 
        {
            root = u;
            n++; 
            q++;
            return 0;
        }
        boolean done = false;
        int d = 0;
        do {
 
            if (u.value < w.value) 
            {
                if (w.left == null) 
                {
                    w.left = u;
                    u.parent = w;
                    done = true;
                } 
                else 
                {
                    w = w.left;
                }
            } 
            else if (u.value > w.value) 
            {
                if (w.right == null) 
                {
                    w.right = u;
                    u.parent = w;
                    done = true;
                }
                w = w.right;
            } 
            else 
            {
                return -1;
            }
            d++;
        } while (!done);
        n++; 
        q++;
        return d;
    }
}