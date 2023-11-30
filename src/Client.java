public  class Client {
    private int id;
    private static int nextClientId = 1;

    public Client() {
        this.id = nextClientId;
        nextClientId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
