import java.util.List;

class RelationshipComputer<T> {

    public Relationship computeRelationship(List<T> l1, List<T> l2) {
        if (l1.equals(l2))
            return Relationship.EQUAL;

        if (l1.size() < l2.size()) {
            int windowSlides = l2.size() - l1.size();
            for (int i = 0; i <= windowSlides; i++) {
                List<T> subList = l2.subList(i, l1.size() + i);
                if (l1.equals(subList))
                    return Relationship.SUBLIST;
            }
        } else if (l1.size() > l2.size()) {
            int windowSlides = l1.size() - l2.size();
            for (int i = 0; i <= windowSlides; i++) {
                List<T> subList = l1.subList(i, l2.size() + i);
                if (l2.equals(subList))
                    return Relationship.SUPERLIST;
            }

        }

        return Relationship.UNEQUAL;
    }
}