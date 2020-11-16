import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

class RelationshipComputer<T> {

    private BiFunction<List<?>, List<?>, Relationship> strategy;

    public RelationshipComputer() {
        this.strategy = StrategyE.S1;
    }

    public RelationshipComputer(BiFunction<List<?>, List<?>, Relationship> strategy) {
        this.strategy = strategy;
    }

    public Relationship computeRelationship(List<T> l1, List<T> l2) {
        return strategy.apply(l1, l2);
    }

    enum StrategyE implements BiFunction<List<?>, List<?>, Relationship> {
        S1 {
            @Override
            public Relationship apply(List<?> l1, List<?> l2) {
                if (l1.equals(l2))
                    return Relationship.EQUAL;

                if (l1.size() < l2.size()) {
                    int windowSlides = l2.size() - l1.size();
                    for (int i = 0; i <= windowSlides; i++) {
                        List<?> subList = l2.subList(i, l1.size() + i);
                        if (l1.equals(subList))
                            return Relationship.SUBLIST;
                    }
                } else if (l1.size() > l2.size()) {
                    int windowSlides = l1.size() - l2.size();
                    for (int i = 0; i <= windowSlides; i++) {
                        List<?> subList = l1.subList(i, l2.size() + i);
                        if (l2.equals(subList))
                            return Relationship.SUPERLIST;
                    }

                }
                return Relationship.UNEQUAL;
            }
        },
        S2 {
            @Override
            public Relationship apply(List<?> l1, List<?> l2) {
                if (l1.equals(l2))
                    return Relationship.EQUAL;

                if (Collections.indexOfSubList(l2, l1) >= 0)
                    return Relationship.SUBLIST;
                else if (Collections.indexOfSubList(l1, l2) >= 0)
                    return Relationship.SUPERLIST;

                return Relationship.UNEQUAL;
            }
        }
    }
}