package task1.domain.treeitems;

import lombok.Data;

@Data
public class Needle implements ITreeItem {
    private String hexColor = "#00bb00";
    private double size = 1;
}
