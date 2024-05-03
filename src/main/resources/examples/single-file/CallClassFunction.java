class CallClassFunction {
    public void helloWorld() {
        System.out.println("Hello, World!");
    }

    public static void main(String[] args) {
        CallClassFunction callClassFunction = new CallClassFunction();;
        callClassFunction.helloWorld();
    }
}