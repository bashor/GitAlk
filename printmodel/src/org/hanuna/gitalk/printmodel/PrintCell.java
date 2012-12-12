package org.hanuna.gitalk.printmodel;

import org.hanuna.gitalk.common.ReadOnlyList;
import org.jetbrains.annotations.NotNull;

/**
 * @author erokhins
 */
public interface PrintCell {
    public int countCell();

    @NotNull
    public ReadOnlyList<ShortEdge> getUpEdges();

    @NotNull
    public ReadOnlyList<ShortEdge> getDownEdges();

    @NotNull
    public ReadOnlyList<SpecialCell> getSpecialCell();
}