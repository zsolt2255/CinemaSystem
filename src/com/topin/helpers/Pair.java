package com.topin.helpers;

public class Pair<L,R> {

    private final L left;
    private final R right;

    /**
     * @param left
     * @param right
     */
    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * @return L
     */
    public L getLeft() { return left; }

    /**
     * @return L
     */
    public L getKey() { return left; }

    /**
     * @return R
     */
    public R getRight() { return right; }

    /**
     * @return R
     */
    public R getValue() { return right; }

    /**
     * @return int
     */
    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    /**
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pair)) {
            return false;
        }

        Pair pairo = (Pair) object;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }

}