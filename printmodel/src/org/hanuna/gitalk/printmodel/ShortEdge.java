package org.hanuna.gitalk.printmodel;

import org.hanuna.gitalk.graph.graph_elements.Edge;

/**
 * @author erokhins
 */
public class ShortEdge {
    private final Edge edge;
    private final int upPosition;
    private final int downPosition;
    private final boolean selected;

    public ShortEdge(Edge edge, int upPosition, int downPosition, boolean selected) {
        this.edge = edge;
        this.upPosition = upPosition;
        this.downPosition = downPosition;
        this.selected = selected;
    }

    public Edge getEdge() {
        return edge;
    }

    public boolean isUsual() {
        return edge.getType() == Edge.Type.USUAL;
    }

    public int getUpPosition() {
        return upPosition;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getDownPosition() {
        return downPosition;
    }
}