package task1.domain;

import lombok.Data;
import task1.domain.treeitems.ITreeItem;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Branch {
    private double length;
    private double initialWidth;
    private double branchOffset;
    private double branchXAngle;
    private double branchYAngle;
    private List<Branch> childBranches = null;

    private List<ITreeItem> treeItems = null; // leaves, fruits, flowers, cones

    public List<Branch> getChildBranches() {
        return (childBranches != null) ? childBranches :
                Collections.emptyList();
    }

    public List<ITreeItem> getTreeItems() {
        return (treeItems != null) ? treeItems :
                Collections.emptyList();
    }

    public List<ITreeItem> collectAllTreeItems() {
        final List<ITreeItem> res =
                this.getChildBranches().stream().flatMap(cb -> cb.collectAllTreeItems().stream()).collect(Collectors.toList());
        res.addAll(getTreeItems());
        return res;
    }
}
