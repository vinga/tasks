package task1;

import task1.domain.ITree;
import task1.builders.ITreeRecipe;
import task1.builders.TreeBuilder;

public class Main {


    public static void main(String[] args) {
        ITree maple = new TreeBuilder("Klon").build();
        ITree sosna = new TreeBuilder("Sosna").withTreeRecipe(ITreeRecipe.NEEDLES_TREE_WITH_CONES).build();
        ITree pinkFlowers = new TreeBuilder("Drzewo z różowymi kwiatami")
                .withTreeRecipe(ITreeRecipe.PINK_FLOWERS_ONLY).build();

        System.out.println(maple.toString());
        System.out.println(sosna.toString());
    }
}
