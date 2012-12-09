package org.hanuna.gitalk.graph.mutable_graph;

import org.hanuna.gitalk.common.ReadOnlyList;
import org.hanuna.gitalk.common.compressedlist.Replace;
import org.hanuna.gitalk.graph.Graph;
import org.hanuna.gitalk.graph.GraphPiece;
import org.hanuna.gitalk.graph.graph_elements.NodeRow;
import org.hanuna.gitalk.graph.graph_elements.GraphElement;
import org.hanuna.gitalk.graph.mutable_graph.graph_controller.GraphPieceController;
import org.hanuna.gitalk.graph.mutable_graph.graph_controller.SimpleGraphPieceController;
import org.hanuna.gitalk.graph.mutable_graph.graph_elements_impl.MutableNodeRow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erokhins
 */
public class MutableGraph implements Graph {
    private final GraphPieceController pieceController = new SimpleGraphPieceController(this);
    private final List<MutableNodeRow> allRows;
    private final List<MutableNodeRow> visibleRows;

    public MutableGraph(List<MutableNodeRow> allRows) {
        this.allRows = allRows;
        this.visibleRows = new ArrayList<MutableNodeRow>(allRows);
        updateRowIndex();
    }

    public Replace fixRowIndex(int from, int to) {
        if (from < 0 || to >= allRows.size() || from > to) {
            throw new IllegalArgumentException("from: " + from + "to: " + to);
        }
        MutableNodeRow upRow = visibleRows.get(from);
        MutableNodeRow downRow = visibleRows.get(to);
        for (int i = upRow.getLogIndex(); i <= downRow.getLogIndex(); i++) {
            allRows.get(i).updateVisibleNodes();
        }
        updateRowIndex();
        return new Replace(from, to, downRow.getRowIndex() - upRow.getRowIndex() - 1);
    }

    private void updateRowIndex() {
        visibleRows.clear();
        int rowIndex = 0;
        for (MutableNodeRow row : allRows) {
            if (row.hasVisibleNodes()) {
                row.setRowIndex(rowIndex);
                rowIndex++;
                visibleRows.add(row);
            }
        }
    }

    @NotNull
    @Override
    public ReadOnlyList<NodeRow> getNodeRows() {
        return ReadOnlyList.<NodeRow>newReadOnlyList(visibleRows);
    }

    @Nullable
    @Override
    public GraphPiece relatePiece(@NotNull GraphElement graphElement) {
        return pieceController.relatePiece(graphElement);
    }


}