class Predicate {
    public static final TernaryIntPredicate ALL_DIFFERENT = (a, b, c) -> a != b && a != c && b != c;

    @FunctionalInterface
    public interface TernaryIntPredicate {
        // Write a method here
        boolean test(int a, int b, int c);
    }

    public static void main(String[] args) {
        System.out.println(ALL_DIFFERENT.test(1, 2, 2));
    }
}