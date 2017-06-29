package task1.domain;

import lombok.Data;

@Data
public class Tree implements ITree {
    private final String name;
    private final Branch trunk;

    public Tree(String name, Branch trunk) {
        this.name = name;
        this.trunk = trunk;
    }


}
