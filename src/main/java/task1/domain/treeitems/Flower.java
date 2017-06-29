package task1.domain.treeitems;

import lombok.Data;

@Data
public class Flower implements ITreeItem {
    private String hexColor = "#ffff00";
    private double size = 1;
}
