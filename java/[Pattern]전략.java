public enum 전략 {
    NONE {
        @Override
        public int applyDiscount(final int price) {
            return price -1;
        }
    },
    FIX_1000_AMOUNT {
        @Override
        public int applyDiscount(final int price) {
            return Math.max(price - 1000, 0);
        }
    };

    public abstract int applyDiscount(final int price);
}
