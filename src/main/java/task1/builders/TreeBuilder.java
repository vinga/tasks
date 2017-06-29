package task1.builders;

import task1.domain.Branch;
import task1.domain.Tree;
import task1.domain.treeitems.ITreeItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeBuilder {
    ITreeRecipe treeRecipe = ITreeRecipe.LEAVES_TREE;
    private String name;

    public TreeBuilder(String name) {
        this.name = name;
    }

    public TreeBuilder withTreeRecipe(ITreeRecipe treeRecipe) {
        this.treeRecipe = treeRecipe;
        return this;
    }

    public Tree build() {
        int level = 0;
        Branch trunk = newBranch(level);
        addBranchesOrOtherThings(level, trunk);
        return new Tree(name, trunk);
    }

    private void addBranchesOrOtherThings(int level, Branch parentBranch) {
        int noOfBranches = treeRecipe.getChildBranchesCount(level);

        if (noOfBranches > 0) {
            addBranches(level, parentBranch, noOfBranches);
        } else
            addBranchItems(parentBranch);
    }

    private void addBranches(int level, Branch parentBranch, int noOfBranches) {
        List<Branch> branchesList = new ArrayList<>();
        for (int i = 0; i < noOfBranches; i++) {
            Branch branch = newBranch(level + 1);
            addBranchesOrOtherThings(level + 1, branch);
            branchesList.add(branch);
        }
        parentBranch.setChildBranches(Collections.unmodifiableList(branchesList));
    }

    private void addBranchItems(Branch branch) {
        if (branch.getChildBranches().isEmpty()) {
            List<ITreeItem> endpoints = treeRecipe.newEndpointsOnSingleBranch(branch);
            if (!endpoints.isEmpty())
                branch.setEndpoints(Collections.unmodifiableList(endpoints));
        }
    }

    private Branch newBranch(int level) {
        Branch branch = new Branch();
        branch.setLength(treeRecipe.getBranchLength(level));
        branch.setBranchXAngle(treeRecipe.getBranchXAngle(level));
        branch.setBranchYAngle(treeRecipe.getBranchYAngle(level));
        branch.setInitialWidth(treeRecipe.getBranchInitialWidth(level));
        return branch;
    }
}
