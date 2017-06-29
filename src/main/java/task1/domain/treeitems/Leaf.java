package task1.domain.treeitems;

import lombok.Data;

@Data
public class Leaf implements ITreeItem {
    private String hexColor = "#00ff00";
    private double size = 1;
}
