package task1.builders;

import org.junit.Test;
import task1.domain.ITree;
import task1.domain.treeitems.Needle;

import static org.junit.Assert.*;

public class TreeBuilderTest {

    @Test
    public void testIfNeedlesArePresent() {
        ITree maple = new TreeBuilder("Klon").build();
        ITree sosna = new TreeBuilder("Sosna").withTreeRecipe(ITreeRecipe.NEEDLES_TREE_WITH_CONES).build();
        assertTrue(maple.getTrunk().collectAllTreeItems().stream().noneMatch(e->e instanceof Needle));
        assertTrue(sosna.getTrunk().collectAllTreeItems().stream().anyMatch(e->e instanceof Needle));
    }

}