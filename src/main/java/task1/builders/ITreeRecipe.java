package task1.builders;

import task1.domain.Branch;
import task1.domain.treeitems.Cone;
import task1.domain.treeitems.Flower;
import task1.domain.treeitems.ITreeItem;
import task1.domain.treeitems.Leaf;
import task1.domain.treeitems.Needle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Determines how tree is about to grow
 */
public interface ITreeRecipe {
    int getChildBranchesCount(int level);

    double getBranchLength(int level);

    double getBranchXAngle(int level);

    double getBranchYAngle(int level);

    double getBranchInitialWidth(int level);

    List<ITreeItem> newEndpointsOnSingleBranch(Branch branch);

    class DefaultTreeRecipe implements ITreeRecipe {
        private Random rand = new Random();

        protected DefaultTreeRecipe() {

        }

        @Override
        public List<ITreeItem> newEndpointsOnSingleBranch(Branch branch) {
            List<ITreeItem> endpoints = new ArrayList<>();
            int leavesCount = closedRange(10, 100);
            for (int i = 0; i < leavesCount; i++) {
                Leaf leaf = new Leaf();
                leaf.setSize(closedRange(1., 2.));
                leaf.setHexColor("#00ff00");
                endpoints.add(leaf);
            }
            return endpoints;
        }

        @Override
        public int getChildBranchesCount(int level) {
            if (level > 5)
                return 0;
            else if (level == 4)
                return closedRange(3, 4);
            else
                return closedRange(1, 3);
        }

        @Override
        public double getBranchLength(int level) {
            if (level > 5)
                return closedRange(20 / level, 30 / level);
            else return closedRange(130, 500);
        }

        @Override
        public double getBranchXAngle(int level) {
            if (level == 0)
                return 0;
            return closedRange(5., 20.);
        }

        @Override
        public double getBranchYAngle(int level) {
            if (level == 0)
                return 0;
            return closedRange(0., 360.);
        }

        @Override
        public double getBranchInitialWidth(int level) {
            if (level == 0)
                return closedRange(15, 30);
            return closedRange(2. / level, 3. / level);
        }

        protected int closedRange(int min, int max) {
            return rand.nextInt((max - min) + 1) + min;
        }

        protected double closedRange(double min, double max) {
            return rand.nextDouble() * (max - min) + min;
        }
    }

    ;
    ITreeRecipe LEAVES_TREE = new DefaultTreeRecipe();
    ITreeRecipe NEEDLES_TREE_WITH_CONES = new DefaultTreeRecipe() {
        @Override
        public List<ITreeItem> newEndpointsOnSingleBranch(Branch branch) {
            List<ITreeItem> endpoints = new ArrayList<>();
            int leavesCount = closedRange(10, 100);
            for (int i = 0; i < leavesCount; i++) {
                Needle leaf = new Needle();
                leaf.setSize(closedRange(1., 2.));
                leaf.setHexColor("#00ff00");
                endpoints.add(leaf);
            }

            int conesCount = closedRange(0, 10);
            for (int i = 0; i < conesCount; i++) {
                Cone cone = new Cone();
                cone.setSize(closedRange(2., 4.));
                endpoints.add(cone);
            }

            return endpoints;
        }
    };

    ITreeRecipe PINK_FLOWERS_ONLY = new DefaultTreeRecipe() {

        @Override
        public List<ITreeItem> newEndpointsOnSingleBranch(Branch branch) {
            List<ITreeItem> endpoints = new ArrayList<>();
            int flowersCount = closedRange(10, 50);
            for (int i = 0; i < flowersCount; i++) {
                Flower flower = new Flower();
                flower.setSize(2);
                flower.setHexColor("#ff69b4");
                endpoints.add(flower);
            }
            return endpoints;
        }
    };
}
