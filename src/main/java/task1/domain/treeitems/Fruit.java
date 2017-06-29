package task1.domain.treeitems;

import lombok.Data;

@Data
public class Fruit implements ITreeItem {
    private String hexColor = "#ff0000";
    private double size = 1;
}
