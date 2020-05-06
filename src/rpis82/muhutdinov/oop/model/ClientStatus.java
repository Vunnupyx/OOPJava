package rpis82.muhutdinov.oop.model;

public enum ClientStatus {
    GOOD(0),
    GOLD(3),
    PLATINUM(5),
    RISKY(-2),
    BAD(-4);

    private int creditScoreBound;

    private ClientStatus(int creditScoreBound) {
        this.creditScoreBound = creditScoreBound;
    }

    public int getCreditScoreBound() {
        return creditScoreBound;
    }
}
