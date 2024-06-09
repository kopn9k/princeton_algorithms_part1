package Percolation;

//my implementation of quick union find alg for practise
public class QuickUnionFindPassCompression {
    private int[] items;
    private int[] treeRanks;

    public QuickUnionFindPassCompression(int size) {
        this.items = new int[size];
        this.treeRanks = new int[size];
        for (int i = 0; i < size; i++) {
            this.items[i] = i;
            this.treeRanks[i] = 0;
        }
    }

    public int find(int index) {
        while (index != items[index]) {
            items[index] = items[items[index]];
            index = items[index];
        }
        return index;
    }

    public void union(int index1, int index2) {
        int index1Root = find(index1);
        int index2Root = find(index2);
        if (treeRanks[index1Root] > treeRanks[index2Root]) {
            items[index2Root] = index1Root;
        }
        else if (treeRanks[index1Root] < treeRanks[index2Root]) {
            items[index1Root] = index2Root;
        }
        else {
            items[index2Root] = index1Root;
            treeRanks[index1Root]++;
        }
    }

    public static void main(String[] args) {
        QuickUnionFindPassCompression quickUnionFindPassCompression = new QuickUnionFindPassCompression(6);
        quickUnionFindPassCompression.union(4, 3);
        quickUnionFindPassCompression.union(4, 5);
        quickUnionFindPassCompression.union(1, 2);
        quickUnionFindPassCompression.union(1, 4);
        quickUnionFindPassCompression.union(0, 5);
        for (int i = 0; i < 6; i++) {
            if (quickUnionFindPassCompression.find(i) != quickUnionFindPassCompression.find(0)) System.out.println("aaaaaa");
        }
    }
}
